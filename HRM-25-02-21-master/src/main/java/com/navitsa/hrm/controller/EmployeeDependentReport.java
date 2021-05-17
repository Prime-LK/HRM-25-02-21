package com.navitsa.hrm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.EmployeeDependent;
import com.navitsa.hrm.report.EmployeeDependentReportBean;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.DependentService;
import com.navitsa.hrm.utils.ReportViewe;

@Controller("EmployeeDependentReport")
public class EmployeeDependentReport {

	@Autowired
	private DependentService depService;

	@Autowired
	private DepartmentService depaService;

	@ModelAttribute("allEmpDep")
	public List<EmployeeDependent> getAllDep() {
		return depService.getAllEmpDep();
	}

	@ModelAttribute("depMaster")
	public List<DepartmentMaster> getDeps() {
		return depaService.getAllDep();
	}

	@GetMapping("/dependentReport")
	public String getPage() {
		return "hrm/dependentReport";
	}

	// print dependent summary report
	@PostMapping("/submitDReport")
	public ModelAndView printQuaReport(@RequestParam String depID,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = "Dependent Report: " + depID;
		String[][] result = depService.filterEmpDependents(depID);
		List<EmployeeDependentReportBean> list = new ArrayList<>();
		for (int i = 0; i < result.length; i++) {
			EmployeeDependentReportBean ed = new EmployeeDependentReportBean();
			ed.setEmpid(result[i][0]);
			ed.setFname(result[i][1]);
			ed.setLname(result[i][2]);
			ed.setDtype(result[i][3]);
			ed.setDname(result[i][4]);
			ed.setdDob(result[i][5]);
			ed.setContact(result[i][6]);
			list.add(ed);
//			System.out.println(ed.getEmpid() + " " + ed.getFname() + " " + ed.getDtype());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("depID", depID);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("dependentReport.jasper", fileName, list, params, response);
		ModelAndView mav = new ModelAndView("hrm/dependentReportRelatedDepartmentView");
		mav.addObject("pdfViewEDRRD", report);
		return mav;
	}

	@PostMapping("/submitReport")
	public ModelAndView handleForexRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = "All Employees Dependent Report";
		String[][] result = depService.getDepReportData();
		List<EmployeeDependentReportBean> equipmentSummaryReportArray = new ArrayList<>();
		for (int i = 0; i < result.length; i++) {
			EmployeeDependentReportBean ed = new EmployeeDependentReportBean();
			ed.setEmpid(result[i][0]);
			ed.setFname(result[i][1]);
			ed.setLname(result[i][2]);
			ed.setDtype(result[i][3]);
			ed.setDname(result[i][4]);
			ed.setdDob(result[i][5]);
			ed.setContact(result[i][6]);
			equipmentSummaryReportArray.add(ed);
//			System.out.println(ed.getEmpid() + " " + ed.getFname() + " " + ed.getDtype());
		}
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("dependentReport2.jasper", fileName, equipmentSummaryReportArray, null, response);
		ModelAndView mav = new ModelAndView("hrm/dependentReportAllEmployeesView");
		mav.addObject("pdfViewEDRAE", report);
		return mav;
	}
}
