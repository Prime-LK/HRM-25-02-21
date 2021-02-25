/**
 * 
 */
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
import org.springframework.web.servlet.ModelAndView;
import com.prime.hrm.entity.leaveClass;
import com.prime.hrm.service.LeaveclassService;


@SuppressWarnings("unused")
@Controller
public class leaveController {
	
	@Autowired
	private LeaveclassService LeaveclassService;
	

	
	
	@RequestMapping(value = "/leaveOpen", method = RequestMethod.GET)
	public String createrNewUser(Map<String, Object> model) {
		model.put("leave", new leaveClass());
		model.put("leaveAll", LeaveclassService.getAllLeaves());
		
		return "leaves";
	}

	@RequestMapping(value = "/saveleave", method = RequestMethod.POST)
	public String saveLeave(@ModelAttribute("leave") leaveClass leave) {
		
		
		LeaveclassService.saveLeave(leave);
		return "redirect:/leaveOpen";
	}
	
	@RequestMapping(value="/Updatename", method= RequestMethod.GET)
	public ModelAndView updatename(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("leaves");
		leaveClass leaves = LeaveclassService.getRm(id);
		mav.addObject("leave", leaves);
		return mav;
	}
		

}


