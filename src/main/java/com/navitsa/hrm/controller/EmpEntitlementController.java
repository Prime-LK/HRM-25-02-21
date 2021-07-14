package com.navitsa.hrm.controller;

import java.io.Console;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.Assestclass;
import com.navitsa.hrm.entity.Depreciationgroup;
import com.navitsa.hrm.entity.EmpEntitlementsClass;
import com.navitsa.hrm.entity.EmployeeCategory;
import com.navitsa.hrm.entity.LeaveType;
import com.navitsa.hrm.service.EmpEntitlementService;
import com.navitsa.hrm.service.EmployeeLevelService;
import com.navitsa.hrm.service.LeaveTypeService;

@SuppressWarnings("unused")
@Controller
public class EmpEntitlementController {

	@Autowired
	private EmpEntitlementService empEntService;

	@Autowired
	private LeaveTypeService leaveClassService;

	@Autowired
	private EmployeeLevelService employeeLevelService;

	@RequestMapping(value = "/employeeEntitlements", method = RequestMethod.GET)
	public String openEmployeeEntitlements(Map<String, Object> model) {
		model.put("entitlement", new EmpEntitlementsClass());
		model.put("entitlementAll", empEntService.getAll());

		return "hrm/EmployeeEntil";
	}

	@RequestMapping(value = "/EmpLeave", method = RequestMethod.GET)
	public String empLeave(Map<String, Object> model) {
		model.put("entitlement", new EmpEntitlementsClass());
		model.put("entitlementAll", empEntService.getAll());

		return "hrm/EmpLeaves";
	}

//	@ModelAttribute("leaveAll")
//	public List<LeaveType> showleaves() {
//		return leaveClassService.getAllLeaves();
//
//	}

	@ModelAttribute("allCat")
	public List<EmployeeCategory> getAllEmployeeCategoryByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return employeeLevelService.getAllEmployeeCategoryByCompany(companyId);

	}

	@ModelAttribute("EmpEntitlementsClass")
	public List<EmpEntitlementsClass> getAll() {
		return empEntService.getAll();
	}

	@RequestMapping(value = "/saveentitlement", method = RequestMethod.POST)
	public String saveentitlement(@ModelAttribute("entitlement") EmpEntitlementsClass entitlement,
			RedirectAttributes redirectAttributes) {

		try {
			empEntService.saveentitlement(entitlement);
			redirectAttributes.addFlashAttribute("success", 1);
			return "redirect:/employeeEntitlements";
		} catch (Exception e) {
			System.out.println(e);
		}
		return "hrm/EmployeeEntil";

	}

	@RequestMapping(value = "/UpdateEmp", method = RequestMethod.GET)
	public ModelAndView UpdateEmp(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/EmployeeEntil");
		try {
			EmpEntitlementsClass ef = empEntService.getAll(id);
			mav.addObject("entitlement", ef);
		} catch (Exception e) {
			System.out.println(e);
		}

		return mav;
	}

}
