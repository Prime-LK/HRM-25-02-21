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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeLanguage;
import com.prime.hrm.entity.EmployeeMembership;
import com.prime.hrm.entity.EmployeeQualification;
import com.prime.hrm.entity.MembershipInformation;
import com.prime.hrm.service.EmployeeService;
import com.prime.hrm.service.MembershipService;

@Controller
public class MembershipController {

	@Autowired
	private MembershipService miService;
	
	@Autowired
	private EmployeeService emService;
	
	//membership info----------------------------------------------------------------------------------
	
	@RequestMapping(value="/membershipInformation", method = RequestMethod.GET)
	public String loadPage(Map<String,Object> map) {
		map.put("memInforForm", new MembershipInformation());
		MembershipInformation mi = new MembershipInformation();
		mi.setMemID("00000".substring(miService.maxMiID().length()) + miService.maxMiID());
		map.put("memInforForm", mi);
		return "membershipInformation";
	}
	
	@ModelAttribute("allMi")
	public List<MembershipInformation> getAllMInfo() {
		return miService.getAllMi();
	}
	
	@ModelAttribute("emp")
	public List<Employee> getAllEm() {
		return emService.getAllEmp();
	}
	
	@RequestMapping(value = "/saveMInfo", method = RequestMethod.POST)
	public String saveMInfo(@Valid @ModelAttribute("memInforForm")MembershipInformation mi,BindingResult br) {
		if(br.hasErrors()) {
			return "membershipInformation";
		}else {
			miService.saveMinfo(mi);
			return "redirect:/membershipInformation";
		}
		
	}
	
	@RequestMapping(value = "/updateMi", method = RequestMethod.GET)
	public ModelAndView updateMiInfor(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("membershipInformation");
		MembershipInformation mi = miService.getMInfo(id);
		mav.addObject("memInforForm", mi);
		return mav;
	}
	
//	emp memberhsip-----------------------------------------------------------------------------------
	
	@RequestMapping(value="/employeeMembership", method = RequestMethod.GET)
	public String loadempPagewithid(Map<String,Object> map) {
		map.put("empMem", new EmployeeMembership());
//		map.put("eid",eid);
		return "employeeMembership";
	}
	
	//save employee memebership data
	@RequestMapping(value = "/empMembershipACT", method = RequestMethod.POST)
	public String saveempMembership(@Valid @ModelAttribute("empMem") EmployeeMembership empM ,
			BindingResult br) {
		if(br.hasErrors()) {
			return "employeeQualification";
		}else {
			miService.saveEmpMembership(empM);
			return "redirect:/employeeQualification";
		}
		
	}
	
	//load saved data as a list
	@ModelAttribute("MembershipList")
	public List<EmployeeMembership> getAllMembershipInfo() {
		return miService.getAllMembership();
	}
	
	//edit membership data
	@RequestMapping(value = "/updateMembership", method = RequestMethod.GET)
	public ModelAndView editDATaofm(@RequestParam("eid") String eid,@RequestParam("memID") String memID) {
		ModelAndView mav = new ModelAndView("employeeMembershipEdit");
		try {
		EmployeeMembership mID = miService.getMemIDDataByID(eid, memID);
		mav.addObject("empMem", mID);
		} catch (Exception e) {
			System.out.println(e);
		}
		return mav;
 }

	@RequestMapping(value="/getMDtails", method=RequestMethod.GET)
	public   @ResponseBody List<EmployeeMembership> comboTable(@RequestParam String empID ) {
	List<EmployeeMembership> listEmployeeMembership = miService.searchEmployeeMembership(empID);
	return listEmployeeMembership;
	}	
}
