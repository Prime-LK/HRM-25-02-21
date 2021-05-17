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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeLanguage;
import com.navitsa.hrm.entity.EmployeeSkill;
import com.navitsa.hrm.entity.LanguageMaster;
import com.navitsa.hrm.service.LanguageService;

@Controller
public class LanguageController {

	@Autowired
	private LanguageService lmService;

	// language master----------------------------------------------------

	@RequestMapping(value = "/languageMaster", method = RequestMethod.GET)
	public String getLanPage(Map<String, Object> map) {
		map.put("saveLanguage", new LanguageMaster());
		LanguageMaster lm = new LanguageMaster();
		lm.setLid("00000".substring(lmService.maxLmID().length()) + lmService.maxLmID());
		map.put("maxLmID", lm);
		map.put("saveLanguage", lm);
		return "hrm/languageMaster";
	}

	@RequestMapping(value = "/saveLanguage", method = RequestMethod.POST)
	public String saveLanguage(@Valid @ModelAttribute("saveLanguage") LanguageMaster lm, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/languageMaster";
		} else {
			try {
				lmService.saveLMaster(lm);
				return "redirect:/hrm/languageMaster";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/languageMaster";
	}

	@RequestMapping(value = "/updateLanguage", method = RequestMethod.GET)
	public ModelAndView updateLanguage(@RequestParam String lid) {
		ModelAndView mav = new ModelAndView("languageMaster");
		LanguageMaster lm = lmService.getLanguage(lid);
		mav.addObject("saveLanguage", lm);
		return mav;
	}

	@ModelAttribute("lMaster")
	public List<LanguageMaster> getAllLm() {
		return lmService.getAllLm();
	}

	// employee language----------------------------------------------------

//	@RequestMapping(value="/employeeLanguagewithid", method= RequestMethod.GET)
//	public String getEmpLaPagewithid(Map<String,Object>map, @RequestParam("eid") String eid) {
//		map.put("saveEmpLa", new EmployeeLanguage());
//		EmployeeLanguage el = new EmployeeLanguage();
//		map.put("saveEmpLa", el);
//		map.put("eid", eid);
//		return "employeeLanguage";
//	}
//	
	@RequestMapping(value = "/saveEmpLa", method = RequestMethod.POST)
	public String saveEmpLa(@Valid @ModelAttribute("saveEmpLan") EmployeeLanguage el, BindingResult br) {
		if (br.hasErrors()) {
			try {
			return "hrm/employeeSkill";
			} catch(Exception e) {
				System.out.println("Jsp Not Fuund");
			}
		} else {
			try {
				lmService.saveEmpLa(el);

			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return "redirect:/hrm/employeeSkill";
	}

	@RequestMapping(value = "/updateEmpLa", method = RequestMethod.GET)
	public ModelAndView updateEmpLa(@RequestParam("empID") String empID,
			@RequestParam("languageId") String languageId) {
		ModelAndView mav = new ModelAndView("hrm/employeeLanguageEdit");
		try {
		EmployeeLanguage el = lmService.getdata(empID, languageId);
		mav.addObject("saveEmpLan", el);
		}  catch(Exception e) {
//			System.out.println(e);
		}
		return mav;
	}

	@ModelAttribute("empLa")
	public List<EmployeeLanguage> getAllEmpLa() {
		return lmService.getAllEl();
	}

	@ModelAttribute("las")
	public List<LanguageMaster> getAllEmpLm() {
		return lmService.getAlllm();
	}

	@ModelAttribute("emp")
	public List<Employee> getAllEmp() {
		return lmService.getAllEmp();
	}

	// load emp lanaguage jsp
	@RequestMapping(value = "/employeeLanguage", method = RequestMethod.GET)
	public String getEmpLaPage(Map<String, Object> map) {
		map.put("saveEmpLa", new EmployeeLanguage());
		EmployeeLanguage el = new EmployeeLanguage();
		map.put("saveEmpLa", el);

		return "hrm/employeeLanguage";
	}

	@RequestMapping(value = "/getlanguageDtails", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeLanguage> comboTable(@RequestParam String empID) {
		List<EmployeeLanguage> listEmployeeLanguage = lmService.searchEmployeeLanguage(empID);
		return listEmployeeLanguage;
	}
}
