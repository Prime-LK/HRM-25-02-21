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
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.EmployeeCategory;
import com.navitsa.hrm.entity.EmployeeType;
import com.navitsa.hrm.service.EmployeeLevelService;

@Controller
public class EmployeeLevelController {

	@Autowired
	private EmployeeLevelService elService;
	
	//employee type---------------------------------------------------
	
	@RequestMapping(value="/employeeType", method = RequestMethod.GET)
	public String loadPage(Map<String,Object> map) {
		map.put("typeForm", new EmployeeType());
		EmployeeType type = new EmployeeType();
		type.setTid("00000".substring(elService.maxTypeID().length()) + elService.maxTypeID());
		map.put("typeForm", type);
		return "hrm/employeeType";
	}
	
	@ModelAttribute("allType")
	public List<EmployeeType> getAllType() {
		return elService.getAllTy();
	}
	
	@RequestMapping(value = "/saveType", method = RequestMethod.POST)
	public String saveType(@Valid @ModelAttribute("typeForm")EmployeeType type, BindingResult br) {
		if(br.hasErrors()) {
			return "hrm/employeeType";
		}else {
			elService.saveType(type);
			return "redirect:/hrm/employeeType";
		}
		
	}
	
	@RequestMapping(value = "/updateType", method = RequestMethod.GET)
	public ModelAndView updateType(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("employeeType");
		EmployeeType ty = elService.getType(id);
		mav.addObject("typeForm", ty);
		return mav;
	}
	
	//employee category-------------------------------------------------------
	
	@RequestMapping(value="/employeeCategory", method = RequestMethod.GET)
	public String loadCatPage(Map<String,Object> map) {
		map.put("categoryForm", new EmployeeCategory());
		EmployeeCategory cat = new EmployeeCategory();
		cat.setCatgoryID("00000".substring(elService.maxEcID().length()) + elService.maxEcID());
		map.put("categoryForm", cat);
		return "hrm/employeeCategory";
	}
	
	@ModelAttribute("allCat")
	public List<EmployeeCategory> getAllCat() {
		return elService.getAllCat();
	}
	
	@RequestMapping(value = "/saveCat", method = RequestMethod.POST)
	public String saveCat(@Valid @ModelAttribute("categoryForm")EmployeeCategory cat, BindingResult br) {
		if(br.hasErrors()) {
			return "hrm/employeeCategory";
		}else {
			elService.saveCat(cat);
			return "redirect:/hrm/employeeCategory";
		}
		
	}
	
	@RequestMapping(value = "/updateCat", method = RequestMethod.GET)
	public ModelAndView updateCat(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/employeeCategory");
		EmployeeCategory cat = elService.getCat(id);
		mav.addObject("categoryForm", cat);
		return mav;
	}
}
