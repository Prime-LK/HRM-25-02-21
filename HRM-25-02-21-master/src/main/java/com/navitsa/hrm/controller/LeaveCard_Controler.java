package com.navitsa.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.navitsa.hrm.service.ApplyLeave_Service;
import com.navitsa.hrm.service.LeaveCard_Service;

@Controller
public class LeaveCard_Controler {
	
	@Autowired
	private ApplyLeave_Service ALService;
	
	@Autowired
	private LeaveCard_Service LCService;
	
//	@RequestMapping(value = "/leaveCardOpen", method = RequestMethod.GET)
//	public String ALOpen(Map<String, Object>model) {
//		model.put("applyleave", new ApplyLeave_Entity());
//		model.put("applyleaveAll" , LCService.getAll());
//		
//		return "ApplyCancel_Leaves";
//	}
	
//	@ModelAttribute("date")
//	public List<ApplyLeave_Entity>showleaves(){
//		return ALService.getAll();
//	}
//	
//	@ModelAttribute("EmpAll")
//	public List<ApplyLeave_Entity>showEmp(){
//		return ALService.getAll();
//	}
//	
//	@RequestMapping(value = "/savePage", method = RequestMethod.POST)
//	public String savepage(@ModelAttribute("leaveCard")EmLeaveCard_Entity leaveCard) {
//	LCService.savepage(leaveCard);
//	
//	return "redirect:/leaveCardOpen";
//	}

}
