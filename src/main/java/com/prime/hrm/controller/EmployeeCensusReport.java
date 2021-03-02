package com.prime.hrm.controller;

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

import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeDetails;
import com.prime.hrm.report.CensusReportBean;
import com.prime.hrm.report.CensusReportNewBean;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeService;
import com.prime.hrm.utils.ReportViewe;

@Controller("EmployeeCensusReport")
public class EmployeeCensusReport {

	@Autowired
	private EmployeeService empService;

	@Autowired
	private DepartmentService depService;

	@GetMapping("/censusReport")
	public String getPage() {
		return "censusReport";
	}

	@ModelAttribute("allEmp")
	public List<Employee> getAllEmployee() {
		return empService.getAllEmp();
	}

	@ModelAttribute("allEmpDetails")
	public List<EmployeeDetails> getAllEmployeeDe() {
		return empService.getAllEmpDetails();
	}

	@ModelAttribute("deps")
	public List<DepartmentMaster> getAllDeps() {
		return depService.getAllDep();
	}

	// Census Report of Related Department
	@PostMapping("/submitCReport")
	public ModelAndView printQuaReport(@RequestParam String depID, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = "Census Report: " + depID;
		String[][] result = empService.filterEemployee(depID);
		List<CensusReportBean> list = new ArrayList<>();
		for (int i = 0; i < result.length; i++) {
			CensusReportBean emp = new CensusReportBean();
			emp.setEmpid(result[i][0]);
			emp.setFname(result[i][1]);
			emp.setLname(result[i][2]);
			emp.setContact(result[i][3]);
			emp.setEmail(result[i][4]);
			emp.setDob(result[i][5]);
			emp.setDep(result[i][6]);
			emp.setContact2(result[i][7]);
			emp.setContact3(result[i][8]);
			emp.setEmpDesignation(result[i][9]);
			emp.setEmpJoinedDate(result[i][10]);
			emp.setEmpResignedDate(result[i][11]);
			emp.setEstatus(result[i][12]);

			list.add(emp);
//	    	   System.out.println(emp.getEmpid() + " " + emp.getContact2()+ " " + emp.getContact3());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("depID", depID);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("censusReport2.jasper", fileName, list, params, response);
		ModelAndView mav = new ModelAndView("censusReportRelatedDepartmentView");
		mav.addObject("pdfViewCRD", report);
		return mav;

	}

	// joined date based report
	@PostMapping("/submitjoinedDateBasedReport")
	public ModelAndView printjoinedbasedReport(@RequestParam("joinedDate") String joinedDate,
			@RequestParam("joinedDate2") String joinedDate2, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileName = "Census Report: " + joinedDate + "-" + joinedDate2;
		String[][] result = empService.getCensusReportDatabyfilteringemployeebasedonjoinedDate(joinedDate, joinedDate2);
		List<CensusReportBean> list = new ArrayList<>();
		for (int i = 0; i < result.length; i++) {
			CensusReportBean emp = new CensusReportBean();
			emp.setEmpid(result[i][0]);
			emp.setFname(result[i][1]);
			emp.setLname(result[i][2]);
			emp.setContact(result[i][3]);
			emp.setEmail(result[i][4]);
			emp.setDob(result[i][5]);
			emp.setDep(result[i][6]);
			emp.setContact2(result[i][7]);
			emp.setContact3(result[i][8]);
			emp.setEmpDesignation(result[i][9]);
			emp.setEmpJoinedDate(result[i][10]);
			emp.setEmpResignedDate(result[i][11]);
			emp.setEstatus(result[i][12]);

			list.add(emp);
//		    	   System.out.println(emp.getEmpid() + " " + emp.getContact2()+ " " + emp.getContact3());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("joinedDate", joinedDate);
		params.put("joinedDate2", joinedDate2);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("censusReport3.jasper", fileName, list, params, response);
		ModelAndView mav = new ModelAndView("censusReportRelatedJoinedDateView");
		mav.addObject("pdfViewCRJD", report);
		return mav;
	}

	// Census report related resign date
	@PostMapping("/submitresignDateBasedReport")
	public ModelAndView printresignDatebasedReport(@RequestParam("resignDate") String resignDate,
			@RequestParam("resignDate2") String resignDate2, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileName = "Census Report: " + resignDate + "-" + resignDate2;
		String[][] result = empService.getCensusReportDatabyfilteringemployeebasedonresignDate(resignDate, resignDate2);
		List<CensusReportBean> listOfResignEmp = new ArrayList<>();
		for (int i = 0; i < result.length; i++) {
			CensusReportBean emp = new CensusReportBean();
			emp.setEmpid(result[i][0]);
			emp.setFname(result[i][1]);
			emp.setLname(result[i][2]);
			emp.setContact(result[i][3]);
			emp.setEmail(result[i][4]);
			emp.setDob(result[i][5]);
			emp.setDep(result[i][6]);
			emp.setContact2(result[i][7]);
			emp.setContact3(result[i][8]);
			emp.setEmpDesignation(result[i][9]);
			emp.setEmpJoinedDate(result[i][10]);
			emp.setEmpResignedDate(result[i][11]);
			emp.setEstatus(result[i][12]);

			listOfResignEmp.add(emp);
//		    	   System.out.println(emp.getEmpid() + " " + emp.getContact2()+ " " + emp.getContact3());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("resignDate", resignDate);
		params.put("resignDate2", resignDate2);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("censusReport4.jasper", fileName, listOfResignEmp, params,
				response);
		ModelAndView mav = new ModelAndView("censusReportRelatedResignDateView");
		mav.addObject("pdfViewCRRD", report);
		return mav;
	}

	// Census report all employees
	@PostMapping("/submitC2Report")
	public ModelAndView handleForexRequest(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileName = "Census Report";
		String[][] result = empService.getData();
		List<CensusReportNewBean> empSummaryReportArray = new ArrayList<>();
		for (int i = 0; i < result.length; i++) {
			CensusReportNewBean emp = new CensusReportNewBean();
			emp.setEmpid(result[i][0]);
			emp.setFname(result[i][1]);
			emp.setLname(result[i][2]);
			emp.setContact(result[i][3]);
			emp.setEmail(result[i][4]);
			emp.setDob(result[i][5]);
			emp.setEmpDesignation(result[i][6]);
			emp.setEmpJoinedDate(result[i][7]);
			emp.setEmpResignedDate(result[i][8]);
			emp.setEstatus(result[i][9]);
			empSummaryReportArray.add(emp);
//	    	   System.out.println(emp.getEmpid() + " " + emp.getFname()+ " " + emp.getLname());
		}
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("censusReport.jasper", fileName, empSummaryReportArray,
				null, response);
		ModelAndView mav = new ModelAndView("censusReportAllEmpsView");
		mav.addObject("pdfViewCRAE", report);
		return mav;
	}

}
