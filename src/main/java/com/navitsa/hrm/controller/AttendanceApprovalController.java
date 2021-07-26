package com.navitsa.hrm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.ApplyLeave;
import com.navitsa.hrm.entity.AttendanceSheet;
import com.navitsa.hrm.entity.AttendanceSummary;
import com.navitsa.hrm.entity.AttendanceSummaryDetail;
import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.OtType;
import com.navitsa.hrm.entity.PayPeriods;
import com.navitsa.hrm.service.ApplyLeave_Service;
import com.navitsa.hrm.service.AttendanceProcessService;
import com.navitsa.hrm.service.AttendanceSummaryService;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.OtTypeService;
import com.navitsa.hrm.service.PayService;

@Controller
public class AttendanceApprovalController {
	
	@Autowired
	private AttendanceProcessService attendanceProcessService;
	
	@Autowired
	private AttendanceSummaryService attendanceSummaryService;
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private ApplyLeave_Service applyLeaveService;
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private OtTypeService otTypeService;
	
	
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
	
	@GetMapping("/attendanceApproval")
	public ModelAndView LoadForm(HttpSession session) {

		ModelAndView mav=new ModelAndView("hrm/attendanceApproval");
		try {
			String companyID=(String) session.getAttribute("company.comID");	
			mav.addObject("payPeriodsList", payService.getPayPeriodsBycompanyid(companyID));
			mav.addObject("DepAll", departmentService.getAllDepartmentByCompany(companyID));
			
		} catch (Exception e) {
			
		}

		return mav;
	}

	@RequestMapping(value="/approveAttendanceProcess", method=RequestMethod.POST)
	public ModelAndView approveAttendance(
			@RequestParam String payPeriodID,
			@RequestParam String employeeID,
			HttpSession session) throws ParseException {
		
		//List<AttendanceSheet> ls = attendanceProcessService.findBy(payPeriodID,employeeID);
		//EmployeeDetails ed =  empService.getEmployeeDetailsByEmployeeID(employeeID);
		
		String companyID=(String) session.getAttribute("company.comID");
		CompanyMaster cm = new CompanyMaster();
		cm.setComID(companyID);
		//Employee em = new Employee(employeeID);
		
		List<EmployeeDetails> edList =  empService.getEmployeeDetailsByCompanyID(companyID);
		for(EmployeeDetails ed :edList) {

			String employee_id = ed.getDetailsPK().getEmpID().getEmpID();
			double totalOTHr =0;
			double totalLateHr = 0;
			double totalOTMin = Double.valueOf(attendanceProcessService.getTotalOT(payPeriodID, employee_id,companyID));
			totalOTHr = totalOTMin/60;
			double totalLateMn = Double.valueOf(attendanceProcessService.getTotalLate(payPeriodID,employee_id,companyID));
			totalLateHr = totalLateMn/60;
			
			List<AttendanceSheet> leaveInfo = attendanceProcessService.getTotalLeave(payPeriodID, employee_id,companyID);
			int totalLeave = leaveInfo.size();
			
			PayPeriods payPeriod = payService.getPayPeriods(payPeriodID);
			List<ApplyLeave> leaveApproved = applyLeaveService.findAppliedLeaveByEmployee(employee_id,companyID,payPeriod.getStartDate(),payPeriod.getEndDate());
		
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
			int noPayDays = 0;
			double noPayHrs = 0;
			
			for(AttendanceSheet a :leaveInfo) {
				Date leaveDate = df.parse(a.getDate());
				boolean flag = false;
				
				for(ApplyLeave b :leaveApproved) {
					Date requestedDate = df.parse(b.getDate());				
					if(leaveDate.compareTo(requestedDate)==0) {
						flag = true;
					}
				}
				
				if(flag==false) {
					noPayDays++;
					long difference = 0;
					difference  = stf.parse(a.getShiftOut()).getTime() - stf.parse(a.getShiftIn()).getTime();
					long diffMinutes = difference / 60000;
					noPayHrs = noPayHrs + diffMinutes;
				}
				

			}
			
			noPayHrs = noPayHrs/60;
			
			AttendanceSummary summary = new AttendanceSummary();
			//summary.setAttendanceSummaryId("00000".substring(attendanceSummaryService.getMaxID().length()) + attendanceSummaryService.getMaxID());
			summary.setTotalOtHours(totalOTHr);
			summary.setTotalLateMinutes(totalLateHr);
			summary.setTotalLeave(totalLeave);
			summary.setNoPayDays(noPayDays);
			summary.setNoPayHours(noPayHrs);
			summary.setPayPeriod(payPeriod);
			summary.setEmployee(ed.getDetailsPK().getEmpID());
			summary.setCompany(cm);
			summary = attendanceSummaryService.saveSummary(summary);
			
			String dayTypes[]={"SUNDAY","HOLIDAY","SAT","WORKDAY"};		
			List<AttendanceSummaryDetail> ls = new ArrayList<AttendanceSummaryDetail>();
			
			for(int i=0;i<dayTypes.length;i++) {
				
				//String otTypes[] = {"5","6"}; 
				List<OtType> otTypes = otTypeService.findByDayType(dayTypes[i],companyID);
				
				List<AttendanceSheet> otlist =  attendanceProcessService.getOTByDayType(dayTypes[i], payPeriodID, employee_id,companyID);
				int otCount = otlist.size();
				String totalOtMin = attendanceProcessService.getTotalOTByDayType(dayTypes[i], payPeriodID, employee_id,companyID);
				double totalOtHrs = Double.valueOf(totalOtMin)/60;
				
				for (OtType otType : otTypes) {
					
					AttendanceSummaryDetail summaryDetail = new AttendanceSummaryDetail();
					summaryDetail.setAttendanceSummaryHeaderId(summary);
					summaryDetail.setDayType(dayTypes[i]);
					summaryDetail.setOtType(otType);
					
					if(otType.getCondition().equals("<=8"))
						summaryDetail.setOtHours(8*otCount);
					else if(otType.getCondition().equals(">8"))
						summaryDetail.setOtHours(totalOtHrs - (8*otCount));
					else
						summaryDetail.setOtHours(totalOtHrs);
					
					ls.add(summaryDetail);
				}
			
			}
			
			attendanceSummaryService.saveSummaryDetail(ls);
		}
		
		return new ModelAndView("hrm/attendanceApproval","filesuccess","Attendance successfully approved !");
	
	}
}
