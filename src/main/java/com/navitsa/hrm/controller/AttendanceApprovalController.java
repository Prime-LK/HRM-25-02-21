package com.navitsa.hrm.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.navitsa.hrm.entity.ApplyLeave_Entity;
import com.navitsa.hrm.entity.AttendanceSummary;
import com.navitsa.hrm.entity.AttendanceSummaryDetail;
import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.PayPeriods;
import com.navitsa.hrm.service.ApplyLeave_Service;
import com.navitsa.hrm.service.AttendanceProcessService;
import com.navitsa.hrm.service.AttendanceSummaryService;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeService;
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
	
	// load payPeriods data
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
	
	@GetMapping("/attendanceApproval")
	public String LoadForm() {
			
		return "hrm/attendanceApproval";
	}

	@RequestMapping(value="/approveAttendanceProcess", method=RequestMethod.POST)
	public void approveAttendance(
			@RequestParam String payPeriodID, 
			@RequestParam String employeeID,
			HttpSession session) throws ParseException {
		
		//List<AttendanceSheet> ls = attendanceProcessService.findBy(payPeriodID,employeeID);
		//EmployeeDetails ed =  empService.getEmployeeDetailsByEmployeeID(employeeID);
		
		String companyID=(String) session.getAttribute("company.comID");
		CompanyMaster cm = new CompanyMaster();
		cm.setComID(companyID);
		Employee em = new Employee(employeeID);
		
		double totalOTHr = attendanceProcessService.getTotalOT(payPeriodID, employeeID);	
		int totalLateMn = attendanceProcessService.getTotalLate(payPeriodID,employeeID);					
		int totalLeave = attendanceProcessService.getTotalLeave(payPeriodID, employeeID);
				
		ApplyLeave_Entity leave = applyLeaveService.findAppliedLeaveByEmployee(employeeID);
		PayPeriods payPeriod = payService.getPayPeriods(payPeriodID);		
		int totalApprovedLeave =  applyLeaveService.getTotalApprovedLeaveBy(
				payPeriod.getStartDate(),
				payPeriod.getEndDate(),
				leave.getLeaveID());
		
		int noPayDays = totalLeave - totalApprovedLeave;
		
		AttendanceSummary summary = new AttendanceSummary();
		summary.setAttendanceSummaryId("00000".substring(attendanceSummaryService.getMaxID().length()) + attendanceSummaryService.getMaxID());
		summary.setTotalOtHours(totalOTHr);
		summary.setTotalLateMinutes(totalLateMn);
		summary.setTotalLeave(totalLeave);
		summary.setNoPayDays(noPayDays);
		summary.setPayPeriod(payPeriod);
		summary.setEmployee(em);
		summary.setCompany(cm);
		attendanceSummaryService.saveSummary(summary);
		
		String dayTypes[]={"SUNDAY","HOLIDAY","HALFDAY","WORKDAY"};
		
		List<AttendanceSummaryDetail> ls = new ArrayList<AttendanceSummaryDetail>();
		
		for(int i=0;i<dayTypes.length;i++) {

			String otHours =  attendanceProcessService.getTotalOTByDayType(dayTypes[i], payPeriodID, employeeID);
			AttendanceSummaryDetail summaryDetail = new AttendanceSummaryDetail();
			summaryDetail.setAttendanceSummaryHeaderId(summary);
			summaryDetail.setDayType(dayTypes[i]);
			summaryDetail.setOtHours(Double.valueOf(otHours));
			ls.add(summaryDetail);
		}
		
		attendanceSummaryService.saveSummaryDetail(ls);
	
	}
}
