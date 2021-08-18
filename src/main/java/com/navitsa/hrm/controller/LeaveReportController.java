package com.navitsa.hrm.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.ApplyLeave;
import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.DesignationMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.service.ApplyLeave_Service;
import com.navitsa.hrm.service.CompanyService;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeService;


import com.navitsa.hrm.utils.ReportViewe;

@Controller
public class LeaveReportController {
	

	
	@Autowired
	private ApplyLeave_Service applyLeaveService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	CompanyService companyService;
	
	@GetMapping("/leaveSummaryReport")
	public String getCompanyPage(Map<String, Object> map) {
		map.put("companyMasterObject", new CompanyMaster());
		
	
		return "hrm/leaveReport";
	}
	
	
	
	@ModelAttribute("departmentETFAttLisRpt")
	public List<DepartmentMaster> getDepartment(HttpSession session) {
		String companyId = session.getAttribute("company.comID") + "";
		List<DepartmentMaster> listDept = departmentService.getDepartmentsByCompany(companyId);
		return listDept;
	}
	
	@ModelAttribute("designationAttenETFLisRpt")
	public List<DesignationMaster> getDesignationMaster(HttpSession session){
		String companyId=session.getAttribute("company.comID")+"";

		List<DesignationMaster> listdec = empService.getAllDesignationsByCompany(companyId);
		return listdec;
	}
	
	@RequestMapping(value = "/getEmployeeListETFRpt", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getEmployeeListrpt(@RequestParam String dep,@RequestParam String dis, HttpSession session) {
		String companyId = session.getAttribute("company.comID") + "";
		List<Employee> listallemploy = empService.getEmployeeListrpt(dep, dis, "%", "%", "%", "%", "%", companyId);
		return listallemploy;
	}
	

//	@GetMapping("/leaveReport")
//	 public ModelAndView getLeaveSummaryReport(@RequestParam String dep_id,
//			 @RequestParam String employee_id,
//			 HttpSession session,HttpServletResponse response)
//	 {
//		ModelAndView mav = new ModelAndView("hrm/leaveReport");
//		
//		System.out.println("Employee id "+employee_id);
//		
//		List<ApplyLeave> appliedLeaves = applyLeaveService.getappliedLeaves(dep_id,employee_id);
//		for(ApplyLeave x : appliedLeaves) {
//			//System.out.println(x.getDesc());
//		}
//		
//		DepartmentMaster dm = departmentService.getID(dep_id);
//		
//		 //set parameters to report
//		 Map<String,Object> params = new HashMap<>();
//		 params.put("departmentName",dm.getDepartment() );
//		 params.put("companyName", dm.getCompany().getComName());
//		 params.put("companyAddress", dm.getCompany().getAddress());
//		 params.put("companyContactNo", dm.getCompany().getConNo() );
//
//		 
//		 ReportViewe view =new ReportViewe();
//		 String pdf_result = null;
//		 
//		try {
//			pdf_result = view.pdfReportViewInlineSystemOpen("leave_report.jasper","leave_report",appliedLeaves,params,response);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		mav.addObject("pdfViewEq", pdf_result);
//		return mav;
//		
//	 }
	
	 @PostMapping("/leaveReport")
	 public ModelAndView getmonthlyetfreport(@RequestParam String dep,@RequestParam String dis,@RequestParam String employeeId,@RequestParam boolean empgroup ,HttpServletRequest request, 
			 HttpServletResponse response, HttpSession session) throws Exception {
		 String companyId = session.getAttribute("company.comID") + "";
		 CompanyMaster companyMaster=companyService.findbyCompanyid(companyId);
		 String fileName = "Leave Report: " ;
		 
		  
		 
		 List<ApplyLeave> result = applyLeaveService.getleavefreport(employeeId, companyId,empgroup);
		 
		 Map<String, Object> params = new HashMap<>();	
		 	params.put("companny", companyMaster.getComName());
			params.put("address", companyMaster.getAddress());
		 	ReportViewe review=new ReportViewe();
		 	
	        String report = review.pdfReportViewInlineSystemOpen("employeeLeaveSummeryReport.jasper", fileName, result, params, response);
	        ModelAndView mav = new ModelAndView("hrm/leaveReport");
	        mav.addObject("pdfViewEq",report);
	        return mav;
	        
	       
			
	        
	        
			 
	        
	        
	 }
}
