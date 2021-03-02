package com.prime.hrm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.report.EmployeeContactSummaryReportBean;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeService;
import com.prime.hrm.utils.ReportViewe;

@Controller("contactReportController")
public class ContactReportController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService dep;

	@RequestMapping("/contactReport")
	public String employee(Map<String, Object> model) {
		List<DepartmentMaster> listEmployee = dep.getAllDep();
		model.put("listEmployee", listEmployee);
		return "contactReport";
	}
	//contact report related department
	@PostMapping("/contactRepo")
	public ModelAndView handleForexRequest(@RequestParam("depID") String depID, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = "Employee Contact Report: " + depID;
		String[][] result = employeeService.getEmployeefilteredcontactData(depID);
		List<EmployeeContactSummaryReportBean> employeeContactSummaryReportBeanArray = new ArrayList<>();
		for (int i = 0; i < result.length; i++) {
			EmployeeContactSummaryReportBean empC = new EmployeeContactSummaryReportBean();
			empC.setEmpid(result[i][0]);
			empC.setFname(result[i][1]);
			empC.setLname(result[i][2]);
			empC.setEmpAdd(result[i][3]);
			empC.setContact_h(result[i][4]);
			empC.setContact_c(result[i][5]);
			empC.setEmpemail(result[i][6]);
			empC.setEmpcity(result[i][7]);
			empC.setEmpstate(result[i][8]);

			employeeContactSummaryReportBeanArray.add(empC);
//			System.out.println(empC.getFname() + "-------" + empC.getContact_h());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("depID", depID);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("EmployeeContactSummaryReport.jasper", fileName, employeeContactSummaryReportBeanArray, params, response);
		ModelAndView mav = new ModelAndView("contactReportRelatedDepartmentView");
		mav.addObject("pdfViewCRRD", report);
		return mav;
	}

}
