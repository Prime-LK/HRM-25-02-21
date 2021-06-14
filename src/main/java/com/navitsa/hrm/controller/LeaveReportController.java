package com.navitsa.hrm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.ApplyLeave_Entity;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.service.ApplyLeave_Service;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.LeaveReportService;

import com.navitsa.hrm.utils.ReportViewe;

@Controller
public class LeaveReportController {
	
	@Autowired
	private LeaveReportService leaveReportService;
	
	@Autowired
	private ApplyLeave_Service applyLeaveService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeService empService;
	
	
	@GetMapping("/leaveSummaryReport")
	public String leaveReport() {
		
		return "hrm/leaveReport";
	}
	
	@ModelAttribute("DepAll")
	public List<DepartmentMaster> findAllDepartments(){
		return departmentService.getAllDep();
	}
	
	@ModelAttribute("EmpAll")
	public List<Employee> findAllEmp(){
		return empService.getAllEmp();
	}
	

	@GetMapping("/leaveReport")
	 public ModelAndView getLeaveSummaryReport(@RequestParam String dep_id,
			 @RequestParam String employee_id,
			 HttpSession session,HttpServletResponse response)
	 {
		ModelAndView mav = new ModelAndView("hrm/leaveReport");
		
		System.out.println("Employee id "+employee_id);
		
		List<ApplyLeave_Entity> appliedLeaves = applyLeaveService.getappliedLeaves(dep_id,employee_id);
		for(ApplyLeave_Entity x : appliedLeaves) {
			System.out.println(x.getDesc());
		}
		
		DepartmentMaster dm = departmentService.getID(dep_id);
		
		 //set parameters to report
		 Map<String,Object> params = new HashMap<>();
		 params.put("departmentName",dm.getDepartment() );
		 params.put("companyName", dm.getCompany().getComName());
		 params.put("companyAddress", dm.getCompany().getAddress());
		 params.put("companyContactNo", dm.getCompany().getConNo() );

		 
		 ReportViewe view =new ReportViewe();
		 String pdf_result = null;
		 
		try {
			pdf_result = view.pdfReportViewInlineSystemOpen("leave_report.jasper","leave_report",appliedLeaves,params,response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("pdfViewEq", pdf_result);
		return mav;
		
	 }
}
