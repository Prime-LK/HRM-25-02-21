package com.prime.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeWorkExperience;
import com.prime.hrm.entity.EmployeeWorkExperiencePK;
import com.prime.hrm.service.WorkExperienceService;

@Controller
public class WorkExperienceController {

	@Autowired
	private WorkExperienceService expService;

	@RequestMapping(value = "/workExperience", method = RequestMethod.GET)
	public String getWePage(Map<String, Object> map) {
		map.put("saveWexp", new EmployeeWorkExperience());
		EmployeeWorkExperience we = new EmployeeWorkExperience();
		map.put("saveWexp", we);
		EmployeeWorkExperiencePK wepk = new EmployeeWorkExperiencePK();
		wepk.setExpId("00000".substring(expService.maxExpID().length()) + expService.maxExpID());
		map.put("expid", wepk);
		return "workExperience";
	}

	@RequestMapping(value = "/saveExperience", method = RequestMethod.POST)
	public String saveExperience(@Valid @ModelAttribute("saveWexp") EmployeeWorkExperience we, BindingResult br) {
		if (br.hasErrors()) {
			return "workExperience";
		} else {
			try {
				expService.saveEmpExp(we);
				return "redirect:/workExperience";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "workExperience";
	}

	@RequestMapping(value = "/updateWexp", method = RequestMethod.GET)
	public ModelAndView updateWexp(@RequestParam("empID") String empID, @RequestParam("expID") String expID) {
		ModelAndView mav = new ModelAndView("workExperience");
		EmployeeWorkExperience ew = expService.getEmployeeWorkExperienceDataByID(empID, expID);
		mav.addObject("saveWexp", ew);
		return mav;
	}

	@ModelAttribute("emp")
	public List<Employee> getAllLm() {
		return expService.getAllEmp();
	}

	@ModelAttribute("exp")
	public List<EmployeeWorkExperience> getAllExp() {
		return expService.getAllDExp();
	}

	// load emp related we
	@RequestMapping(value = "/getEmployee", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeWorkExperience> getEmps(@RequestParam String empID) {
		List<EmployeeWorkExperience> we = expService.getEmps(empID);
		return we;
	}
}
