package com.prime.hrm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prime.hrm.entity.EmployeeSkill;
import com.prime.hrm.entity.SkillMaster;
import com.prime.hrm.report.SkillReportBean;
import com.prime.hrm.service.SkillService;
import com.prime.hrm.utils.ReportViewe;

@Controller("SkillReportController")
public class SkillReportController {

	@Autowired
	private SkillService skillService;

	@GetMapping("/skillReport")
	public String loadPage() {
		return "skillReport";
	}

	@ModelAttribute("allSkill")
	public List<SkillMaster> getSkill() {
		return skillService.getAllSkills();
	}

	@ModelAttribute("allSkEmps")
	public List<EmployeeSkill> getEmps() {
		return skillService.getAllEmpSkill();
	}

	// print Employee summary report
	@PostMapping("/submitSkillReport")
	public ModelAndView printQuaReport(@RequestParam String sid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = "Skill Report: " + sid;
		String[][] result = skillService.getDataToReport(sid);
		List<SkillReportBean> list = new ArrayList<>();
		for (int i = 0; i < result.length; i++) {
			SkillReportBean ed = new SkillReportBean();
			ed.setEmpid(result[i][0]);
			ed.setFname(result[i][1]);
			ed.setLname(result[i][2]);
			ed.setsType(result[i][3]);
			ed.setProfeciency(result[i][4]);
			list.add(ed);
//			System.out.println(ed.getEmpid() + " " + ed.getFname() + " " + ed.getsType());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("sid", sid);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("skillReport.jasper", fileName, 
				list, params, response);
		ModelAndView mav = new ModelAndView("skillReportRelatedSkillView");
		mav.addObject("pdfViewESRRS", report);
		return mav;

	}

}
