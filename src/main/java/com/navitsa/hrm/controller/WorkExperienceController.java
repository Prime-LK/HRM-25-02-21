package com.navitsa.hrm.controller;

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

import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeWorkExperience;
import com.navitsa.hrm.entity.EmployeeWorkExperiencePK;
import com.navitsa.hrm.service.WorkExperienceService;

@Controller
public class WorkExperienceController {

	@Autowired
	private WorkExperienceService expService;

	@RequestMapping(value = "/WorkExperience", method = RequestMethod.GET)
	public String getWePage(Map<String, Object> map) {
		map.put("saveWexp", new EmployeeWorkExperience());
		EmployeeWorkExperience we = new EmployeeWorkExperience();
		map.put("saveWexp", we);
		EmployeeWorkExperiencePK wepk = new EmployeeWorkExperiencePK();
		wepk.setExpId("00000".substring(expService.maxExpID().length()) + expService.maxExpID());
		map.put("expid", wepk);
		return "hrm/workExperience";
	}

	@RequestMapping(value = "/saveExperience", method = RequestMethod.POST)
	public String saveExperience(@Valid @ModelAttribute("saveWexp") EmployeeWorkExperience we, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/workExperience";
		} else {
			try {
				expService.saveEmpExp(we);
				return "redirect:/WorkExperience";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/workExperience";
	}

	@RequestMapping(value = "/updateWexp", method = RequestMethod.GET)
	public ModelAndView updateWexp(@RequestParam("empID") String empID, @RequestParam("expID") String expID) {
		ModelAndView mav = new ModelAndView("hrm/workExperience");
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
