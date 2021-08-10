package com.navitsa.hrm.controller;

import java.util.ArrayList;
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

import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.DesignationMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.MonthBasicSalary;
import com.navitsa.hrm.entity.PayPeriods;
import com.navitsa.hrm.service.CompanyService;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.MonthBasicSalaryService;
import com.navitsa.hrm.service.PayService;
import com.navitsa.hrm.utils.ReportViewe;

@Controller
public class MonthBasicSalaryController {
	
	@Autowired
	private MonthBasicSalaryService monthBasicSalaryService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	CompanyService companyService;
	
	
	@GetMapping("/MonthlyETFStatementReport")
	public String getCompanyPage(Map<String, Object> map) {
		map.put("companyMasterObject", new CompanyMaster());
		
	
		return "hrm/monthlyETFStatementReport";
	}
	
	
	
	@ModelAttribute("departmentETFAttLisRpt")
	public List<DepartmentMaster> getDepartment(HttpSession session) {
		String companyId = session.getAttribute("company.comID") + "";
		List<DepartmentMaster> listDept = departmentService.getDepartmentsByCompany(companyId);
		return listDept;
	}
	
	@ModelAttribute("payPeriodETF")
	public List<PayPeriods> getPayPeriods(HttpSession session) {
		String companyId = session.getAttribute("company.comID") + "";
		List<PayPeriods> payPeriods = payService.getPayPeriodsBycompanyid(companyId);
		return payPeriods;
	}

	@ModelAttribute("designationAttenETFLisRpt")
	public List<DesignationMaster> getDesignationMaster(HttpSession session){
		String companyId=session.getAttribute("company.comID")+"";

		List<DesignationMaster> listdec = employeeService.getAllDesignationsByCompany(companyId);
		return listdec;
	}
	
	
	@RequestMapping(value = "/getEmployeeListETF", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getEmployeeListrpt(@RequestParam String dep,@RequestParam String dis, HttpSession session) {
		String companyId = session.getAttribute("company.comID") + "";
		List<Employee> listallemploy = employeeService.getEmployeeListrpt(dep, dis, "%", "%", "%", "%", "%", companyId);
		return listallemploy;
	}
	
	
	 @PostMapping("/getmonthlyetfreport")
	 public ModelAndView getmonthlyetfreport(@RequestParam String payPeriod,@RequestParam String dep,@RequestParam String dis,@RequestParam String employeeId,HttpServletRequest request, 
			 HttpServletResponse response, HttpSession session) throws Exception {
		 String companyId = session.getAttribute("company.comID") + "";
		 CompanyMaster companyMaster=companyService.findbyCompanyid(companyId);
		 String fileName = "ETF Report: " ;
		 
		List<MonthBasicSalary> result =  monthBasicSalaryService.getmonthlyetfreport(payPeriod,employeeId,companyId); 
		
		 	Map<String, Object> params = new HashMap<>();	
		 	params.put("companny", companyMaster.getComName());
			params.put("address", companyMaster.getAddress());
		 	ReportViewe review=new ReportViewe();
		 	
	        String report = review.pdfReportViewInlineSystemOpen("monthlyETFStatementReport.jasper", fileName, result, params, response);
	        ModelAndView mav = new ModelAndView("hrm/monthlyETFStatementReport");
	        mav.addObject("pdfViewEq",report);
	        return mav;
	        
	       
			
	        
	        
			
	        
	        
	 }

}
