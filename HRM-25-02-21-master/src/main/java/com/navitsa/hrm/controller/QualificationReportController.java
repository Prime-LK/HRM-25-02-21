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

import com.navitsa.hrm.entity.QualificationMaster;
import com.navitsa.hrm.report.EmployeeQualificationSummaryReportBean;
import com.navitsa.hrm.service.QualificationService;
import com.navitsa.hrm.utils.ReportViewe;

@Controller("qualificationReportController")
public class QualificationReportController {

	@Autowired
	QualificationService qualificationService;

	// shown data in jsp
	@GetMapping("/qualificationReport")
	public String empqualification() {
		return "hrm/qualificationReport";
	}

	@ModelAttribute("allemQua")
	public List<QualificationMaster> getAllATypes() {
		return qualificationService.getAllQm();
	}

	// Employee Qualification Report
	@PostMapping("/quaificationRepo")
	public ModelAndView handleForexRequest(Model m, @RequestParam("qid") String qid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = "Qualification Report: " + qid;
		String[][] result = qualificationService.getEmpqualificationReportData(qid);
		List<EmployeeQualificationSummaryReportBean> employeeQualificationSummaryReportBeanArray = new ArrayList<>();
		for (int i = 0; i < result.length; i++) {
			EmployeeQualificationSummaryReportBean empC = new EmployeeQualificationSummaryReportBean();
			empC.setEmpid(result[i][0]);
			empC.setEmpfname(result[i][1]);
			empC.setEmplname(result[i][2]);
			empC.setQtype(result[i][3]);
			empC.setQdesc(result[i][4]);
			empC.setQaward(result[i][5]);
			employeeQualificationSummaryReportBeanArray.add(empC);
//			System.out.println(empC.getEmpfname() + "-------" + empC.getQtype());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("qid", qid);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("employeeQualificationReport.jasper", fileName, 
				employeeQualificationSummaryReportBeanArray, params, response);
		ModelAndView mav = new ModelAndView("hrm/qualificationReportRelatedQualificationView");
		mav.addObject("pdfViewEQRRQ", report);
		return mav;
	}
}
