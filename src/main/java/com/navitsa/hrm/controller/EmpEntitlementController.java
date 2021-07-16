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
import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.Depreciationgroup;
import com.navitsa.hrm.entity.EmployeeEntitlement;
import com.navitsa.hrm.entity.EmployeeType;
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
	private LeaveTypeService leaveTypeService;

	@Autowired
	private EmployeeLevelService employeeLevelService;


	@ModelAttribute("allEmpTypes")
	public List<EmployeeType> findAllEmpTypes(HttpSession session) {
		String companyId = (String) session.getAttribute("company.comID");
		return employeeLevelService.getAllEmployeeTypeByCompany(companyId);

	}
	
	@ModelAttribute("allLeaveTypes")
	public List<LeaveType> findAllLeaveTypes(HttpSession session) {
		String companyId = (String) session.getAttribute("company.comID");
		return leaveTypeService.getLeaveTypesByCompany(companyId);

	}
	
	@RequestMapping(value = "/employeeEntitlements", method = RequestMethod.GET)
	public String openEmployeeEntitlements(Map<String, Object> model, HttpSession session) {
		
		String companyID=(String) session.getAttribute("company.comID");
		model.put("entitlement", new EmployeeEntitlement());
		try {
			model.put("entitlementAll", empEntService.findAllByCompanyId(companyID));
		} catch (Exception e) {
		}
		
		return "hrm/employeeEntitlements";
	}
	

	@RequestMapping(value = "/saveEmpEntitlement", method = RequestMethod.POST)
	public String saveEmpEntitlement(@ModelAttribute("entitlement") EmployeeEntitlement entitlement,
			RedirectAttributes redirectAttributes,HttpSession session) {

		String companyID=(String) session.getAttribute("company.comID");
		CompanyMaster cm  = new CompanyMaster();
		cm.setComID(companyID);
		
		try {
			entitlement.setCompany(cm);
			empEntService.saveentitlement(entitlement);
			redirectAttributes.addFlashAttribute("success", 1);
			return "redirect:/employeeEntitlements";
		} catch (Exception e) {
			System.out.println(e);
		}
		return "hrm/employeeEntitlements";

	}

	@RequestMapping(value = "/editEmpEntitlement", method = RequestMethod.GET)
	public ModelAndView editEmpEntitlement(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/employeeEntitlements");
		try {
			EmployeeEntitlement obj = empEntService.getAll(id);
			mav.addObject("entitlement", obj);
		} catch (Exception e) {
			System.out.println(e);
		}

		return mav;
	}

}
