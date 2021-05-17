package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.EmployeeMembership;
import com.navitsa.hrm.entity.EmployeeQualification;
import com.navitsa.hrm.entity.MembershipInformation;
import com.navitsa.hrm.entity.QualificationMaster;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.MembershipService;
import com.navitsa.hrm.service.QualificationService;

@Controller
public class QualificationController {

	@Autowired
	private QualificationService qmService;

	@Autowired
	private EmployeeService empService;

	@Autowired
	public MembershipService membershipService;

	// qualification
	// master-----------------------------------------------------------

	@RequestMapping(value = "/qualificationMaster", method = RequestMethod.GET)
	public String getQauPage(Map<String, Object> map) {
		map.put("saveQualification", new QualificationMaster());
		QualificationMaster qm = new QualificationMaster();
		qm.setQid("00000".substring(qmService.maxQmID().length()) + qmService.maxQmID());
		map.put("saveQualification", qm);
		return "hrm/qualificationMaster";
	}

	@ModelAttribute("qMaster")
	public List<QualificationMaster> getAllQm() {
		return qmService.getAllQm();
	}

	@RequestMapping(value = "/saveQualification", method = RequestMethod.POST)
	public String saveQm(@Valid @ModelAttribute("saveQualification") QualificationMaster qm, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/qualificationMaster";
		} else {
			try {
				qmService.saveQm(qm);
				return "redirect:/hrm/qualificationMaster";
			} catch (Exception e) {
				System.out.println("Details not saved");
			}
		}
		return "hrm/qualificationMaster";
	}

	@RequestMapping(value = "/updateQm", method = RequestMethod.GET)
	public ModelAndView updateQm(@RequestParam String qid) {
		ModelAndView mav = new ModelAndView("hrm/qualificationMaster");
		QualificationMaster qm = qmService.getQa(qid);
		mav.addObject("saveQualification", qm);
		return mav;
	}

	// employee
	// qualification----------------------------------------------------------

	// load page with employee id using save button
	@RequestMapping(value = "/employeeQualification", method = RequestMethod.GET)
	public String getEqPagewithid(Map<String, Object> map) {
//		map.put("eid", eid);
		map.put("empMem", new EmployeeMembership());
		map.put("empQua", new EmployeeQualification());
		return "hrm/employeeQualification";
	}

	@ModelAttribute("allEmpQua")
	public List<EmployeeQualification> getAllEmpQua() {
		return qmService.getAllEq();
	}

	@ModelAttribute("allMi")
	public List<MembershipInformation> getAllMem() {
		return membershipService.getAllMi();
	}

	@ModelAttribute("MembershipList")
	public List<EmployeeMembership> getAllMembershipInfo() {
		return membershipService.getAllMembership();
	}

	@RequestMapping(value = "/saveEmpQua", method = RequestMethod.POST)
	public String saveEq(@Valid @ModelAttribute("empQua") EmployeeQualification eq, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/employeeQualification";
		} else {
			try {
				qmService.saveEq(eq);
			} catch (Exception e) {
				System.out.println(e);
			}
			return "redirect:/hrm/employeeQualification";
		}

	}

	@RequestMapping(value = "/updateEq", method = RequestMethod.GET)
	public ModelAndView updateEq(@RequestParam("empID") String empID, @RequestParam("qid") String qid) {
		ModelAndView mav = new ModelAndView("employeeQualification");
		try {
			EmployeeQualification emp = qmService.setqdata(empID, qid);
			mav.addObject("empQua", emp);
		} catch (Exception e) {
			System.out.println(e);
		}
		return mav;
	}

	@GetMapping("/getQDtails")
	public @ResponseBody List<EmployeeQualification> loadGrid(@RequestParam("empID") String empID) {
		List<EmployeeQualification> qua = qmService.searchEmployeeQualification(empID);
		System.out.println("hello " + empID);
		return qua;
	}

}
