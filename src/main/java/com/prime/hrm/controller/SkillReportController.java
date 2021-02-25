package com.prime.hrm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeSkill;
import com.prime.hrm.entity.SkillMaster;
import com.prime.hrm.report.SkillReportBean;
import com.prime.hrm.service.EmployeeService;
import com.prime.hrm.service.SkillService;

@Controller("SkillReportController")
public class SkillReportController {

	@Autowired
	private SkillService skillService;
	
	@Autowired
	private EmployeeService empService;
	
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
	
	@GetMapping("/loadDropdown")
	public @ResponseBody List<EmployeeSkill> loadSkillDropdown(@RequestParam String sid) {
		List<EmployeeSkill> skill = skillService.getSkillTypeToReport(sid);
		return skill;
	}
	
	// print Employee summary report
	 @GetMapping("/submitSkillReport")
	 public String printQuaReport(@RequestParam String sid,Model m)
	 {
		 String[][] result = skillService.getDataToReport(sid);
		 
		 List<SkillReportBean> list = new ArrayList<>();
		 
		 for(int i=0; i<result.length;i++ )
		 {
			 SkillReportBean ed = new SkillReportBean();
			 	ed.setEmpid(result[i][0]);
			 	ed.setFname(result[i][1]);
			 	ed.setLname(result[i][2]);
			 	ed.setsType(result[i][3]);
			 	ed.setProfeciency(result[i][4]);
			 
	    	   list.add(ed);
	    	   System.out.println(ed.getEmpid() + " " + ed.getFname()+ " " + ed.getsType());
		 }
		 
		 m.addAttribute("skill", list);
		 
		return "printSkillReport";

	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
