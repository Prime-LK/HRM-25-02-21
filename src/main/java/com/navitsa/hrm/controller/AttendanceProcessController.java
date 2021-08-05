package com.navitsa.hrm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.ApplyLeave;
import com.navitsa.hrm.entity.AttendanceSheet;
import com.navitsa.hrm.entity.AttendanceTxtFileDetail;
import com.navitsa.hrm.entity.AttendanceTxtFileHeader;
import com.navitsa.hrm.entity.CalanderEntity;
import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.DesignationMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeAttendance;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.PayPeriods;
import com.navitsa.hrm.entity.ShiftAllocation;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.report.AttendanceSheetBeen;
import com.navitsa.hrm.report.EmployeeContactListingReportBeen;
import com.navitsa.hrm.report.EmployeeListingReportBeen;
import com.navitsa.hrm.service.ApplyLeave_Service;
import com.navitsa.hrm.service.AttendanceProcessService;
import com.navitsa.hrm.service.AttendanceTxtFileReadingService;
import com.navitsa.hrm.service.CalanderService;
import com.navitsa.hrm.service.CompanyService;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeAttendanceService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.PayService;
import com.navitsa.hrm.service.ShiftAllocationService;
import com.navitsa.hrm.utils.ReportViewe;

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

	@Autowired
	private ApplyLeave_Service applyLeaveService;
	
	@Autowired
	private CompanyService companyService;

	/*
	 * @ModelAttribute("payPeriodsList") public List<PayPeriods> getPeriods() {
	 * return payService.getPayPeriods(); }
	 * 
	 * @ModelAttribute("EmpAll") public List<Employee> findAllEmp(){ return
	 * empService.getAllEmp(); }
	 * 
	 * @ModelAttribute("DepAll") public List<DepartmentMaster> findAllDepartments(){
	 * return departmentService.getAllDep(); }
	 */

	@GetMapping("/attendanceProcess")
	public ModelAndView LoadForm(HttpSession session) {

		ModelAndView mav = new ModelAndView("hrm/attendanceProcess");
		try {
			String companyID = (String) session.getAttribute("company.comID");
			mav.addObject("payPeriodsList", payService.getPayPeriodsBycompanyid(companyID));
			mav.addObject("DepAll", departmentService.getAllDepartmentByCompany(companyID));

		} catch (Exception e) {

		}

		return mav;
	}

	public List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startdate);
		calendar.add(Calendar.DATE, 1);

		while (calendar.getTime().before(enddate)) {
			Date result = calendar.getTime();
			dates.add(result);
			calendar.add(Calendar.DATE, 1);
		}

		dates.add(enddate);
		return dates;
	}

	public List<Date> removeHolidays(Date startdate, Date enddate, List<Date> holidays) {
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startdate);

		while (calendar.getTime().before(enddate)) {
			Boolean flag = false;
			Date result = calendar.getTime();

			for (Date x : holidays) {
				if (result.compareTo(x) == 0) {
					flag = true;
					break;

				}
			}

			if (flag == false)
				dates.add(result);

			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}

	@RequestMapping(value = "/saveAttendanceProcess", method = RequestMethod.POST)
	public ModelAndView getDatesBetweenPayPeriod(@RequestParam String payPeriodID, @RequestParam String employeeID,
			HttpSession session) throws ParseException {

		String companyID = (String) session.getAttribute("company.comID");
		CompanyMaster cm = new CompanyMaster();
		cm.setComID(companyID);

		/* -------------------------------------------- */

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");

		PayPeriods payPeriod = payService.getPayPeriods(payPeriodID);
		String fromDate = null;
		String toDate = null;
		Date from_date = null;
		Date to_date = null;
		fromDate = payPeriod.getStartDate();
		toDate = payPeriod.getEndDate();
		from_date = sdf.parse(fromDate);
		to_date = sdf.parse(toDate);

		// List<Date> holidays =
		// calanderService.getHolidays(payPeriod.getStartDate(),payPeriod.getEndDate());
		// List<Date> calendarDates = removeHolidays(d1, d2, holidays);
		List<Date> workingDays = getDaysBetweenDates(from_date, to_date);

		/* -------------------------------------------- */

		// empService.getEmp(employeeID);
		// EmployeeDetails ed = empService.getEmployeeDetailsByEmployeeID(employeeID);
		List<EmployeeDetails> edList = empService.getEmployeeDetailsByCompanyID(companyID);
		for (EmployeeDetails ed : edList) {

			ShiftMaster sm = new ShiftMaster();
			String shiftIn = null;
			String shiftOut = null;
			boolean continueShift = false;

			if (ed.getShiftmaster() != null) {
				// fixed shift
				shiftIn = ed.getShiftmaster().getStartTime();
				shiftOut = ed.getShiftmaster().getEndTime();
				continueShift = ed.getShiftmaster().isContinuing();
				sm = ed.getShiftmaster();
			}

			List<AttendanceTxtFileHeader> txtFileHeaderList = txtFileReadingService.getTxtFileHeader(companyID);
			List<AttendanceTxtFileDetail> allAttendanceRecords = new ArrayList<AttendanceTxtFileDetail>();
			for (AttendanceTxtFileHeader txtHeader : txtFileHeaderList) {
				List<AttendanceTxtFileDetail> attendanceRecords = txtFileReadingService.getAttendanceRecords(
						payPeriod.getStartDate(), payPeriod.getEndDate(), ed.getEpfNo(), txtHeader.getHeaderId());
				if (!attendanceRecords.isEmpty())
					allAttendanceRecords.addAll(attendanceRecords);
			}

			List<EmployeeAttendance> manualAttendanceRecords = manulaAttendanceService.getAttendanceRecords(
					payPeriod.getStartDate(), payPeriod.getEndDate(), ed.getDetailsPK().getEmpID().getEmpID(),
					companyID);

			// this list use for save all data
			List<AttendanceSheet> attendanceSheetlist = new ArrayList<AttendanceSheet>();

			for (Date workingDay : workingDays) {

				List<String> timeInOut = new ArrayList<String>();
				timeInOut.clear();

				for (AttendanceTxtFileDetail txtFile : allAttendanceRecords) {
					if (workingDay.compareTo(sdf.parse(txtFile.getInoutDate())) == 0) {

						timeInOut.add(txtFile.getInoutTime());
					}
				}

				for (EmployeeAttendance et : manualAttendanceRecords) {
					if (workingDay.compareTo(sdf.parse(et.getDate())) == 0) {

						timeInOut.add(et.getOnTime());
						timeInOut.add(et.getOffTime());
					}
				}

				if (!timeInOut.isEmpty()) {
					long inOutDiff = 0;
					inOutDiff = stf.parse(timeInOut.get(timeInOut.size() - 1)).getTime()
							- stf.parse(timeInOut.get(0)).getTime();
					long inOutDiffMin = inOutDiff / 60000;
					if (inOutDiffMin < 30)
						timeInOut.clear();
				}

				if (ed.getShiftmaster() == null) {
					ShiftAllocation shift = shiftAllocationService.getShiftBy(sdf.format(workingDay), ed.getEpfNo(),
							companyID);
					if (shift != null) {
						shiftIn = shift.getShiftAllocationPK().getShiftmaster().getStartTime();
						shiftOut = shift.getShiftAllocationPK().getShiftmaster().getEndTime();
						continueShift = shift.getShiftAllocationPK().getShiftmaster().isContinuing();
						sm = shift.getShiftAllocationPK().getShiftmaster();

					} else {
						shiftIn = null;
						shiftOut = null;
						continueShift = false;
						sm = null;
					}

				}

				CalanderEntity isHoliday = null;
				isHoliday = calanderService.isHoliday(sdf.format(workingDay), companyID);
				if (ed.getShiftmaster() != null) {
					if (isHoliday == null) {
						// not a holiday
						shiftIn = ed.getShiftmaster().getStartTime();
						shiftOut = ed.getShiftmaster().getEndTime();
						sm = ed.getShiftmaster();
					} else {

						if (isHoliday.getType().equalsIgnoreCase("Half")) {
							long difference = 0;
							difference = stf.parse(shiftOut).getTime() - stf.parse(shiftIn).getTime();
							long diffMinutes = difference / 60000;
							long halfDayMin = diffMinutes / 2;
							Calendar c1 = Calendar.getInstance();
							c1.setTime(stf.parse(shiftIn));
							c1.add(Calendar.MINUTE, (int) halfDayMin);

							shiftOut = stf.format(c1.getTime());
							shiftIn = ed.getShiftmaster().getStartTime();
							sm = ed.getShiftmaster();
						} else {
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

				if (!timeInOut.isEmpty()) {

					Date time_in = null;
					Date time_out = null;
					String timeIn = null;
					String timeOut = null;

					if (continueShift == true) {
						timeIn = timeInOut.get(timeInOut.size() - 1);
						time_in = stf.parse(timeInOut.get(timeInOut.size() - 1));

						Calendar calendar = Calendar.getInstance();
						calendar.setTime(workingDay);
						calendar.add(Calendar.DATE, 1);

						List<String> timeInOut1 = new ArrayList<String>();
						timeInOut1.clear();

						for (AttendanceTxtFileDetail txtFile : allAttendanceRecords) {

							if (calendar.getTime().compareTo(sdf.parse(txtFile.getInoutDate())) == 0) {

								timeInOut1.add(txtFile.getInoutTime());
							}
						}

						/*
						 * for(EmployeeAttendance et : manualAttendanceRecords) {
						 * if(calendar.getTime().compareTo(sdf.parse(et.getDate()))==0) {
						 * 
						 * timeInOut1.add(et.getOnTime()); timeInOut1.add(et.getOffTime()); } }
						 */
						if (!timeInOut1.isEmpty()) {
							timeOut = timeInOut1.get(0);
							time_out = stf.parse(timeInOut1.get(0));
						}
					} else {

						timeIn = timeInOut.get(0);
						timeOut = timeInOut.get(timeInOut.size() - 1);
						time_in = stf.parse(timeInOut.get(0));
						time_out = stf.parse(timeInOut.get(timeInOut.size() - 1));
					}

					attendanceSheet.setTimeIn(timeIn);
					attendanceSheet.setTimeOut(timeOut);

					ApplyLeave leave = applyLeaveService.findLeaveBy(ed.getDetailsPK().getEmpID().getEmpID(), companyID,
							sdf.format(workingDay));
					if (leave == null) {
						if (shiftIn == null || shiftOut == null) {
							int ot_hours = 0;
							if (time_out != null)
								ot_hours = (int) ((time_out.getTime() - time_in.getTime()) / 60000);

							// if(ot_hours<0)
							// attendanceSheet.setOtHrsExtra(Math.abs(ot_hours));
							// else
							attendanceSheet.setOtHrsExtra(ot_hours);

						} else {
							Date shift_in = stf.parse(shiftIn);
							Date shift_out = stf.parse(shiftOut);

							int LateMinIn = (int) ((time_in.getTime() - shift_in.getTime()) / 60000);
							int LateMinOut = 0;
							if (time_out != null)
								LateMinOut = (int) ((shift_out.getTime() - time_out.getTime()) / 60000);

							if (LateMinIn > 0)
								attendanceSheet.setLateMinIn(LateMinIn);
							else
								attendanceSheet.setLateMinIn(0);
							if (LateMinOut > 0)
								attendanceSheet.setLateMinOut(LateMinOut);
							else
								attendanceSheet.setLateMinOut(0);

							int ot_normal = 0;
							if (time_out != null)
								ot_normal = (int) ((time_out.getTime() - shift_out.getTime()) / 60000);

							if (ot_normal > 0)
								attendanceSheet.setOtHrsNormal(ot_normal);
							else
								attendanceSheet.setOtHrsNormal(0);
						}
					}

				} else {
					// No time in out in txt file
				}

				if (isHoliday != null) {
					attendanceSheet.setDayType(isHoliday.getDescription());
					// System.out.println(isHoliday.getDescription());
				} else
					attendanceSheet.setDayType("WORKDAY");

				attendanceSheet.setCompany(cm);
				attendanceSheet.setEmployee(ed.getDetailsPK().getEmpID());
				attendanceSheet.setPayPeriod(payPeriod);
				attendanceSheet.setSpcNSAEntitlement("No");
				attendanceSheetlist.add(attendanceSheet);

			}

			// txt file header loop

			attendanceProcessService.saveAll(attendanceSheetlist);

		} // employee loop

		return new ModelAndView("hrm/attendanceProcess", "filesuccess", "Attendance process successfully completed !");

	}

	@GetMapping("/getEmployeesByDepID")
	public @ResponseBody List<EmployeeDetails> getEmployeesByDepID(@RequestParam("depID") String departmentId,
			HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		// System.out.println(companyId);
		List<EmployeeDetails> ls = employeeService.filterEmployeesByDepartmentAndCompany(departmentId, companyId);
		return ls;
	}

	@GetMapping("/AttendenceReport")
	public String AttendenceReport() {

		return "hrm/AttendenceReport";
	}

	@ModelAttribute("payPeriodPayShip")
	public List<PayPeriods> getPayPeriods(HttpSession session) {
		String companyId = session.getAttribute("company.comID") + "";
		List<PayPeriods> payPeriods = payService.getPayPeriodsBycompanyid(companyId);
		return payPeriods;
	}

	@ModelAttribute("departmentEmpAttLisRpt")
	public List<DepartmentMaster> getDepartment(HttpSession session) {
		String companyId = session.getAttribute("company.comID") + "";
		List<DepartmentMaster> listDept = departmentService.getDepartmentsByCompany(companyId);
		return listDept;
	}
	@ModelAttribute("designationAttenEmpLisRpt")
	public List<DesignationMaster> getDesignationMaster(HttpSession session){
		String companyId=session.getAttribute("company.comID")+"";

		List<DesignationMaster> listdec = employeeService.getAllDesignationsByCompany(companyId);
		return listdec;
	}
	@RequestMapping(value = "/getEmployeeListrptAttnds", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getEmployeeListrpt(@RequestParam String dep,@RequestParam String dis, HttpSession session) {
		String companyId = session.getAttribute("company.comID") + "";
		List<Employee> listallemploy = employeeService.getEmployeeListrpt(dep, dis, "%", "%", "%", "%", "%", companyId);
		return listallemploy;
	}
		
		 @RequestMapping(value="/attendenceSheet", method=RequestMethod.GET)
			public ModelAndView attendenceSheet(@RequestParam String dep,@RequestParam String dis,@RequestParam String employeeId,@RequestParam String payPeriod,@RequestParam String fromdate,@RequestParam String todate,@RequestParam String empgroup,HttpServletRequest request,
					HttpServletResponse response,HttpSession session) throws Exception {
		
					
				String companyId=session.getAttribute("company.comID")+"";
				CompanyMaster companyMaster=companyService.findbyCompanyid(companyId);
					
			
			
				List<AttendanceSheet> result1 = attendanceProcessService.getAttendanceReportByPayPeriod(payPeriod,dep,dis,employeeId,fromdate,todate, companyId);

			List<AttendanceSheetBeen> attendanceSheetBeen = new ArrayList<AttendanceSheetBeen>();
			for (AttendanceSheet result: result1) {
				AttendanceSheetBeen empconLisbe = new AttendanceSheetBeen();
				empconLisbe.setAttendanceSheetId(result.getAttendanceSheetId()+"");
				empconLisbe.setDate(result.getDate());
				empconLisbe.setType(result.getDayType());
				empconLisbe.setShift(result.getShift()+"");
				empconLisbe.setShiftIn(result.getShiftIn());
				empconLisbe.setShiftOut(result.getShiftOut());
				empconLisbe.setTimeIn(result.getTimeIn());
				empconLisbe.setTimeOut(result.getTimeOut());
				empconLisbe.setLunch1In(result.getLunch1In());
				empconLisbe.setLunch1Out(result.getLunch1Out());
				empconLisbe.setLunch2In(result.getLunch2In());
				empconLisbe.setLunch2Out(result.getLunch2Out());
				empconLisbe.setLateMinIn(result.getLateMinIn()+"");
				empconLisbe.setLateMinOut(result.getLateMinOut()+"");
				empconLisbe.setLateMinLunch(result.getLateMinLunch()+"");
				empconLisbe.setOtHrsNormal(result.getOtHrsNormal()+"");
				empconLisbe.setOtHrsExtra(result.getOtHrsExtra()+"");
				empconLisbe.setSpcNSAEntitlement(result.getSpcNSAEntitlement());
				empconLisbe.setPayPeriod(result.getPayPeriod().getDesc());
				empconLisbe.setEpf(result.getEmployee().getEpfNo());
				empconLisbe.setEmployee(result.getEmployee().getName()+"."+result.getEmployee().getLastname());
//	
				attendanceSheetBeen.add(empconLisbe);
	//System.out.println("abcd="+result.getDayType());
			}

			
			Map<String, Object> params = new HashMap<>();
			params.put("companny", companyMaster.getComName());
			params.put("address", companyMaster.getAddress());
			
			
			String reportname="";
			System.out.println("empgroup="+empgroup);
			if(empgroup.equals("Yes")) {
				reportname="AttendenceReport.jasper";
			}
			else {
				reportname="AttendenceReportonsheetanyemploy.jasper";
			}
			
			
			
			
			ReportViewe review = new ReportViewe();
			String report = review.pdfReportViewInlineSystemOpen(reportname, "", attendanceSheetBeen, params, response);
			ModelAndView mav = new ModelAndView("hrm/AttendenceReport");
			mav.addObject("pdfViewEq", report);
			return mav;
		} 
		 @RequestMapping(value = "/getpayperoideAttends", method = RequestMethod.GET)
		 public @ResponseBody PayPeriods loadPayPeriodsbypayPeriodID(@RequestParam String payPeriodID) {
			 
			 return payService.loadPayPeriodsbypayPeriodID(payPeriodID);
		 }
}
