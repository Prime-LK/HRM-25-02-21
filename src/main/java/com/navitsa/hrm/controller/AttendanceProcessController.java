package com.navitsa.hrm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.AttendanceSheet;
import com.navitsa.hrm.entity.AttendanceTxtFileDetail;
import com.navitsa.hrm.entity.AttendanceTxtFileHeader;
import com.navitsa.hrm.entity.CalanderEntity;
import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.PayPeriods;
import com.navitsa.hrm.entity.ShiftAllocation;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.service.AttendanceProcessService;
import com.navitsa.hrm.service.AttendanceTxtFileReadingService;
import com.navitsa.hrm.service.CalanderService;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeAttendanceService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.PayService;
import com.navitsa.hrm.service.ShiftAllocationService;

@Controller
public class AttendanceProcessController {

	@Autowired
	private PayService payService;
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private CalanderService calanderService;
	
	@Autowired
	private AttendanceTxtFileReadingService txtFileReadingService;
	
	@Autowired
	private ShiftAllocationService shiftAllocationService;

	@Autowired
	private AttendanceProcessService attendanceProcessService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeAttendanceService manulaAttendanceService;
	
/*
	@ModelAttribute("payPeriodsList")
	public List<PayPeriods> getPeriods() {
		return payService.getPayPeriods();
	}
	
	@ModelAttribute("EmpAll")
	public List<Employee> findAllEmp(){
		return empService.getAllEmp();
	}
	
	@ModelAttribute("DepAll")
	public List<DepartmentMaster> findAllDepartments(){
		return departmentService.getAllDep();
	}
*/	
	
	@GetMapping("/attendanceProcess")
	public ModelAndView LoadForm(HttpSession session) {
		
		ModelAndView mav=new ModelAndView("hrm/attendanceProcess");
		try {
			String companyID=(String) session.getAttribute("company.comID");	
			mav.addObject("payPeriodsList", payService.getPayPeriodsBycompanyid(companyID));
			mav.addObject("DepAll", departmentService.getAllDepartmentByCompany(companyID));
			
		} catch (Exception e) {
			
		}

		return mav;
	}
	
	public List<Date> getDaysBetweenDates(Date startdate, Date enddate)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(startdate);
	    calendar.add(Calendar.DATE, 1);
	 
	    while (calendar.getTime().before(enddate))
	    {
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
	    
	    dates.add(enddate);
	    return dates;
	}
	
	public List<Date> removeHolidays(Date startdate, Date enddate, List<Date> holidays)
	{
	    List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(startdate);
	    
	    while (calendar.getTime().before(enddate))
	    {
		    Boolean flag = false;
	        Date result = calendar.getTime();
	        
	        for(Date x : holidays) {
	        	if(result.compareTo(x)==0) {
	        		flag = true;
	        		break;
	        		
	        	}
	        }
	        
	        if(flag == false)
	        	dates.add(result);
	        
	        
	        calendar.add(Calendar.DATE, 1);
	    }
	    return dates;
	}
	
	@RequestMapping(value="/saveAttendanceProcess", method=RequestMethod.POST)
	public ModelAndView getDatesBetweenPayPeriod(
			@RequestParam String payPeriodID, 
			@RequestParam String employeeID,
			HttpSession session) throws ParseException {
		
		String companyID=(String) session.getAttribute("company.comID");
		CompanyMaster cm = new CompanyMaster();
		cm.setComID(companyID);
		
		/* -------------------------------------------- */
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
		
		PayPeriods payPeriod = payService.getPayPeriods(payPeriodID);
		String fromDate = null; String toDate = null; Date from_date = null; Date to_date = null;
		fromDate = payPeriod.getStartDate();
		toDate = payPeriod.getEndDate();
		from_date = sdf.parse(fromDate);
		to_date = sdf.parse(toDate);
		
		//List<Date> holidays = calanderService.getHolidays(payPeriod.getStartDate(),payPeriod.getEndDate());
		//List<Date> calendarDates = removeHolidays(d1, d2, holidays);
		List<Date> workingDays = getDaysBetweenDates(from_date, to_date);		
		
		/* -------------------------------------------- */
		
		//empService.getEmp(employeeID);
		//EmployeeDetails ed =  empService.getEmployeeDetailsByEmployeeID(employeeID);	
		List<EmployeeDetails> edList =  empService.getEmployeeDetailsByCompanyID(companyID);
		for(EmployeeDetails ed :edList) {
			
			ShiftMaster sm = new ShiftMaster();
			String shiftIn = null; 
			String shiftOut = null;
			boolean continueShift = false;
			
			if(ed.getShiftmaster()!=null) {
				// fixed shift
				shiftIn =  ed.getShiftmaster().getStartTime();
				shiftOut = ed.getShiftmaster().getEndTime();
				continueShift = ed.getShiftmaster().isContinuing();
				sm = ed.getShiftmaster();
			}
			
			
			// this list use for save all data
			List<AttendanceSheet> attendanceSheetlist = new ArrayList<AttendanceSheet>();
			
			List<AttendanceTxtFileHeader> txtFileHeaderList = txtFileReadingService.getTxtFileHeader(companyID);
			b:for(AttendanceTxtFileHeader txtHeader : txtFileHeaderList) {

				List<AttendanceTxtFileDetail> attendanceRecords =  txtFileReadingService.getAttendanceRecords(payPeriod.getStartDate(),payPeriod.getEndDate(),ed.getEpfNo(),txtHeader.getHeaderId());
				//List<EmployeeAttendance> manualAttendanceRecords = manulaAttendanceService.getAttendanceRecords(payPeriod.getStartDate(),payPeriod.getEndDate(),employeeID);
				
				if(attendanceRecords.isEmpty())
					continue b;
				
				
				for(Date workingDay : workingDays) {
					
					List<String> timeInOut = new ArrayList<String>();
					timeInOut.clear();
					
					for(AttendanceTxtFileDetail txtFile : attendanceRecords) {
						if(workingDay.compareTo(sdf.parse(txtFile.getInoutDate()))==0) {

							timeInOut.add(txtFile.getInoutTime());
						}	
					}
					
		/*			for(EmployeeAttendance et : manualAttendanceRecords) {
						if(workingDay.compareTo(sdf.parse(et.getDate()))==0) {

							timeInOut.add(et.getOnTime());
							timeInOut.add(et.getOffTime());
						}	
					}
		*/			
					if(ed.getShiftmaster() == null) {
						ShiftAllocation shift = shiftAllocationService.getShiftBy(sdf.format(workingDay),ed.getEpfNo(),companyID);
						if(shift !=null) {
							shiftIn = shift.getShiftAllocationPK().getShiftmaster().getStartTime();
							shiftOut = shift.getShiftAllocationPK().getShiftmaster().getEndTime();
							continueShift = shift.getShiftAllocationPK().getShiftmaster().isContinuing();
							sm = shift.getShiftAllocationPK().getShiftmaster();
							
						}else {
							shiftIn = null;
							shiftOut = null;
							continueShift = false;
							sm = null;
						}
						
					}
					
					CalanderEntity isHoliday = null;
					if(ed.getShiftmaster() != null) {
						
						isHoliday = calanderService.isHoliday(sdf.format(workingDay),companyID);
						if(isHoliday  == null) {
							// not a holiday
							shiftIn = ed.getShiftmaster().getStartTime();
							shiftOut =ed.getShiftmaster().getEndTime();
							sm = ed.getShiftmaster();
						}else {
							
							if(isHoliday.getType().equalsIgnoreCase("Half")) {
								long difference = 0;				
								difference = stf.parse(shiftOut).getTime() - stf.parse(shiftIn).getTime();
								long diffMinutes = difference / 60000;
								long halfDayMin = diffMinutes/2;
								Calendar c1 = Calendar.getInstance();
								c1.setTime(stf.parse(shiftIn));
								c1.add(Calendar.MINUTE, (int) halfDayMin);
								
								shiftOut = stf.format(c1.getTime());
								shiftIn =ed.getShiftmaster().getStartTime();
								sm = ed.getShiftmaster();
							}else {
								shiftIn = null;
								shiftOut = null;
								sm = null;
							}
						}			
					}

					
					AttendanceSheet attendanceSheet = new AttendanceSheet();
					attendanceSheet.setDate(sdf.format(workingDay));
					attendanceSheet.setShift(sm);
					attendanceSheet.setShiftIn(shiftIn);
					attendanceSheet.setShiftOut(shiftOut);
					
					if(!timeInOut.isEmpty()) {
						
						Date time_in = null;
						Date time_out = null;
						String timeIn = null;
						String timeOut = null;
						
						if(continueShift==true) {
							timeIn = timeInOut.get(timeInOut.size()-1);
							time_in = stf.parse(timeInOut.get(timeInOut.size()-1));
							
						    Calendar calendar = Calendar.getInstance();
						    calendar.setTime(workingDay);
						    calendar.add(Calendar.DATE, 1);

							List<String> timeInOut1 = new ArrayList<String>();
							timeInOut1.clear();
							
							for(AttendanceTxtFileDetail txtFile : attendanceRecords) {
								
								if(calendar.getTime().compareTo(sdf.parse(txtFile.getInoutDate()))==0) {

									timeInOut1.add(txtFile.getInoutTime());
								}	
							}
							
		/*					for(EmployeeAttendance et : manualAttendanceRecords) {
								if(calendar.getTime().compareTo(sdf.parse(et.getDate()))==0) {

									timeInOut1.add(et.getOnTime());
									timeInOut1.add(et.getOffTime());
								}	
							}
		*/					
							if(!timeInOut1.isEmpty()) {
								timeOut = timeInOut1.get(0);
								time_out = stf.parse(timeInOut1.get(0));
								}
						}else {
							
							timeIn = timeInOut.get(0);
							timeOut = timeInOut.get(timeInOut.size()-1);
							time_in = stf.parse(timeInOut.get(0));
							time_out = stf.parse(timeInOut.get(timeInOut.size()-1));
						}

						attendanceSheet.setTimeIn(timeIn);
						attendanceSheet.setTimeOut(timeOut);
						
						if(shiftIn==null || shiftOut==null)
						{
							int ot_hours = 0;					
							if(time_out !=null)
								ot_hours = (int) ((time_out.getTime() - time_in.getTime())/60000);
							
							//if(ot_hours<0)
								//attendanceSheet.setOtHrsExtra(Math.abs(ot_hours));
							//else
								attendanceSheet.setOtHrsExtra(ot_hours);
							
						}else {
							Date shift_in = stf.parse(shiftIn);
							Date shift_out = stf.parse(shiftOut);
							
							int LateMinIn = (int) ((time_in.getTime() - shift_in.getTime() )/ 60000);
							int LateMinOut = 0;
							if(time_out !=null)
								LateMinOut = (int) ((shift_out.getTime() - time_out.getTime() )/ 60000);
							
							if(LateMinIn > 0)
								attendanceSheet.setLateMinIn(LateMinIn);
							else
								attendanceSheet.setLateMinIn(0);
							if(LateMinOut > 0)
								attendanceSheet.setLateMinOut(LateMinOut);
							else
								attendanceSheet.setLateMinOut(0);
							
							int ot_normal = 0;
							if(time_out !=null)
								ot_normal =  (int) ((time_out.getTime() - shift_out.getTime() )/ 60000);
							
							if(ot_normal > 0)
								attendanceSheet.setOtHrsNormal(ot_normal);
							else
								attendanceSheet.setOtHrsNormal(0);				
						}				
							
					}else {
						//No time in out in txt file
					}
						
					if(isHoliday  != null)
						attendanceSheet.setDayType(isHoliday.getDescription());
					else
						attendanceSheet.setDayType("WORKDAY");
				
					
					attendanceSheet.setCompany(cm);
					attendanceSheet.setEmployee(ed.getDetailsPK().getEmpID());
					attendanceSheet.setPayPeriod(payPeriod);
					attendanceSheet.setSpcNSAEntitlement("No");
					attendanceSheetlist.add(attendanceSheet);

				}
				
				
			}// txt file header loop
			
			attendanceProcessService.saveAll(attendanceSheetlist);
		
		}// employee loop
		
		return new ModelAndView("hrm/attendanceProcess","filesuccess","Attendance process successfully completed !");	
		
	}
	
	@GetMapping("/getEmployeesByDepID")
	public @ResponseBody List<EmployeeDetails> getEmployeesByDepID(
			@RequestParam("depID") String departmentId, HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		//System.out.println(companyId);
		List<EmployeeDetails> ls = employeeService.filterEmployeesByDepartmentAndCompany(departmentId, companyId);
		return ls;
	}	
	
	
}
