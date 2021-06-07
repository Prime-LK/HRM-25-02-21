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
import com.navitsa.hrm.entity.EmployeeMembership;
import com.navitsa.hrm.entity.EmployeeQualification;
import com.navitsa.hrm.entity.MembershipInformation;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.MembershipService;

@Controller
public class MembershipController {

	@Autowired
	private MembershipService miService;

	@Autowired
	private EmployeeService emService;

	// membership
	// info----------------------------------------------------------------------------------

	@RequestMapping(value = "/MembershipType", method = RequestMethod.GET)
	public String loadPage(Map<String, Object> map) {
		map.put("membershipTypeForm", new MembershipInformation());
		MembershipInformation mi = new MembershipInformation();
		mi.setMemID("00000".substring(miService.maxMiID().length()) + miService.maxMiID());
		map.put("membershipTypeForm", mi);
		return "hrm/membershipInformation";
	}

	@ModelAttribute("allMi")
	public List<MembershipInformation> getAllMInfo() {
		return miService.getAllMi();
	}

	@ModelAttribute("emp")
	public List<Employee> getAllEm() {
		return emService.getAllEmp();
	}

	@RequestMapping(value = "/saveMembershipType", method = RequestMethod.POST)
	public String saveMembershipType(@Valid @ModelAttribute("membershipTypeForm") MembershipInformation mi,
			BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/membershipInformation";
		} else {
			miService.saveMinfo(mi);
			return "redirect:/MembershipType";
		}

	}

	@RequestMapping(value = "/UpdateMembershipType", method = RequestMethod.GET)
	public ModelAndView updateMembershipType(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/membershipInformation");
		MembershipInformation mi = miService.getMInfo(id);
		mav.addObject("membershipTypeForm", mi);
		return mav;
	}

//	emp memberhsip-----------------------------------------------------------------------------------

	@RequestMapping(value = "/employeeMembership", method = RequestMethod.GET)
	public String loadempPagewithid(Map<String, Object> map) {
		map.put("empMem", new EmployeeMembership());
//		map.put("eid",eid);
		return "hrm/employeeMembership";
	}

	// save employee memebership data
	@RequestMapping(value = "/empMembershipACT", method = RequestMethod.POST)
	public String saveempMembership(@Valid @ModelAttribute("empMem") EmployeeMembership empM, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/employeeQualification";
		} else {
			miService.saveEmpMembership(empM);
			return "redirect:/hrm/employeeQualification";
		}

	}

	// load saved data as a list
	@ModelAttribute("MembershipList")
	public List<EmployeeMembership> getAllMembershipInfo() {
		return miService.getAllMembership();
	}

	// edit membership data
	@RequestMapping(value = "/updateMembership", method = RequestMethod.GET)
	public ModelAndView editDATaofm(@RequestParam("eid") String eid, @RequestParam("memID") String memID) {
		ModelAndView mav = new ModelAndView("hrm/employeeMembershipEdit");
		try {
			EmployeeMembership mID = miService.getMemIDDataByID(eid, memID);
			mav.addObject("empMem", mID);
		} catch (Exception e) {
			System.out.println(e);
		}
		return mav;
	}

	@RequestMapping(value = "/getMDtails", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeMembership> comboTable(@RequestParam String empID) {
		List<EmployeeMembership> listEmployeeMembership = miService.searchEmployeeMembership(empID);
		return listEmployeeMembership;
	}
}
