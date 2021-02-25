package com.prime.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

//import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prime.hrm.entity.Assestclass;
import com.prime.hrm.entity.Depreciationgroup;
import com.prime.hrm.entity.EmpEntitlementsClass;
import com.prime.hrm.entity.EmployeeCategory;
import com.prime.hrm.entity.leaveClass;
import com.prime.hrm.service.EmpEntitlementService;
import com.prime.hrm.service.EmployeeLevelService;
import com.prime.hrm.service.LeaveclassService;

@SuppressWarnings("unused")
@Controller
public class EmpEntitlementController {

	@Autowired
	private EmpEntitlementService empEntService;

	@Autowired
	private LeaveclassService leaveClassService;

	@Autowired
	private EmployeeLevelService employeeLevelService;

	@RequestMapping(value = "/EmpEntitOpen", method = RequestMethod.GET)
	public String empOpen(Map<String, Object> model) {
		model.put("entitlement", new EmpEntitlementsClass());
		model.put("entitlementAll", empEntService.getAll());

		return "EmployeeEntil";
	}
	
	@RequestMapping(value = "/EmpLeave", method = RequestMethod.GET)
	public String empLeave(Map<String, Object>model) {
		model.put("entitlement", new EmpEntitlementsClass());
		model.put("entitlementAll", empEntService.getAll());
		
		return "EmpLeaves";
	}

	@ModelAttribute("leaveAll")
	public List<leaveClass> showleaves() {
		return leaveClassService.getAllLeaves();

	}

	@ModelAttribute("allCat")
	public List<EmployeeCategory> showEmpCat() {
		return employeeLevelService.getAllCat();

	}

	@ModelAttribute("EmpEntitlementsClass")
	public List<EmpEntitlementsClass> getAll() {
		return empEntService.getAll();
	}

	@RequestMapping(value = "/saveentitlement", method = RequestMethod.POST)
	public String saveentitlement(@ModelAttribute("entitlement") EmpEntitlementsClass entitlement) {
		empEntService.saveentitlement(entitlement);
		return "redirect:/EmpEntitOpen";
	}

	@RequestMapping(value = "/UpdateEmp", method = RequestMethod.GET)
	public ModelAndView UpdateEmp(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("EmployeeEntil");
		EmpEntitlementsClass ef = empEntService.getAll(id);
		mav.addObject("entitlement", ef);
		return mav;
	}

}
