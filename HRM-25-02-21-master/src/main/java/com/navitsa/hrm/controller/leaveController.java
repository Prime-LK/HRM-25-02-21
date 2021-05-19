/**
 * 
 */
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.leaveClass;
import com.navitsa.hrm.service.LeaveclassService;


@SuppressWarnings("unused")
@Controller
public class leaveController {
	
	@Autowired
	private LeaveclassService LeaveclassService;
	
	@RequestMapping(value = "/leaveTypes", method = RequestMethod.GET)
	public String createrNewUser(Map<String, Object> model) {
		model.put("leave", new leaveClass());
		model.put("leaveAll", LeaveclassService.getAllLeaves());
		
		return "hrm/leaves";
	}

	@RequestMapping(value = "/saveleave", method = RequestMethod.POST)
	public String saveLeave(@ModelAttribute("leave") leaveClass leave,
			RedirectAttributes redirectAttributes) {
		
		try {
			
			LeaveclassService.saveLeave(leave);
			redirectAttributes.addFlashAttribute("success", 1);
			return "redirect:/leaveTypes";
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return "hrm/leaves";
	}
	
	@RequestMapping(value="/editLeaveType", method= RequestMethod.GET)
	public ModelAndView updatename(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/leaves");
		try {
			leaveClass leave = LeaveclassService.getLeaveTypeByCode(id);
			mav.addObject("leave", leave);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return mav;
	}
		

}


