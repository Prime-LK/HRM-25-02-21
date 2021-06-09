package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.EmployeeCategory;
import com.navitsa.hrm.entity.EmployeeType;
import com.navitsa.hrm.service.EmployeeLevelService;

@Controller
public class EmployeeLevelController {

	@Autowired
	private EmployeeLevelService elService;

	// employee type---------------------------------------------------

	@RequestMapping(value = "/EmployeeType", method = RequestMethod.GET)
	public String loadPage(Map<String, Object> map) {
		map.put("employeeTypeForm", new EmployeeType());
		EmployeeType type = new EmployeeType();
		type.setTid("00000".substring(elService.maxTypeID().length()) + elService.maxTypeID());
		map.put("employeeTypeForm", type);
		return "hrm/employeeType";
	}

	@ModelAttribute("allType")
	public List<EmployeeType> getAllEmployeeTypeByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return elService.getAllEmployeeTypeByCompany(companyId);
	}

	@RequestMapping(value = "/saveEmployeeType", method = RequestMethod.POST)
	public String saveEmployeeType(@Valid @ModelAttribute("employeeTypeForm") EmployeeType type, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/employeeType";
		} else {
			elService.saveType(type);
			return "redirect:/EmployeeType";
		}

	}

	@RequestMapping(value = "/UpdateEmployeeType", method = RequestMethod.GET)
	public ModelAndView updateEmployeeType(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/employeeType");
		EmployeeType ty = elService.getType(id);
		mav.addObject("employeeTypeForm", ty);
		return mav;
	}

	// employee category-------------------------------------------------------

	@RequestMapping(value = "/EmployeeCategory", method = RequestMethod.GET)
	public String loadCatPage(Map<String, Object> map) {
		map.put("employeeCategoryForm", new EmployeeCategory());
		EmployeeCategory cat = new EmployeeCategory();
		cat.setCatgoryID("00000".substring(elService.maxEcID().length()) + elService.maxEcID());
		map.put("employeeCategoryForm", cat);
		return "hrm/employeeCategory";
	}

	@ModelAttribute("allCat")
	public List<EmployeeCategory> getAllCat() {
		return elService.getAllCat();
	}

	@RequestMapping(value = "/saveEmployeeCategory", method = RequestMethod.POST)
	public String saveEmployeeCategory(@Valid @ModelAttribute("employeeCategoryForm") EmployeeCategory cat,
			BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/employeeCategory";
		} else {
			elService.saveCat(cat);
			return "redirect:/EmployeeCategory";
		}

	}

	@RequestMapping(value = "/UpdateEmployeeCategory", method = RequestMethod.GET)
	public ModelAndView updateEmployeeCategory(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/employeeCategory");
		EmployeeCategory cat = elService.getCat(id);
		mav.addObject("employeeCategoryForm", cat);
		return mav;
	}
}
