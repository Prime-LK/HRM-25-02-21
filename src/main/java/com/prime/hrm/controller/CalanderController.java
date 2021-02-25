package com.prime.hrm.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prime.hrm.entity.CalanderEntity;
import com.prime.hrm.service.CalanderService;

@Controller
public class CalanderController {

	@Autowired
	private CalanderService calanderService;

	@RequestMapping(value = "/calanderOpen", method = RequestMethod.GET)
	public String calanderOpen(Map<String, Object> model) {
		model.put("calander", new CalanderEntity());
		model.put("calanderAll", calanderService.getAll());

		return "Calander";
	}

	@GetMapping("/calenderDetails")
	@ResponseBody
	public CalanderEntity getCalenderDetails(@RequestParam("date") String date) {
		CalanderEntity Cal = calanderService.getCalenderDetails(date);
		return Cal;
	}
	
	@PostMapping("/postcalendar")
	@ResponseBody
	public CalanderEntity setCalenderDetails(@RequestParam("date") String date) {
		CalanderEntity year = calanderService.setCalenderDetails(date);
		return year;
	}

	/*
	 * @RequestMapping(value = "/postcalendar", method = RequestMethod.POST) public
	 * CalanderEntity setCalenderDetails(@RequestParam("date") String date) {
	 * CalanderEntity year = calanderService.setCalenderDetails(date); return year;
	 * 
	 * }
	 */
	
	@RequestMapping(value = "/saveCalander", method = RequestMethod.POST)
	public String savecalander(@ModelAttribute("calander") CalanderEntity calander) {
		calanderService.savecalander(calander);
		return "redirect:/calanderOpen";
	}

	@RequestMapping(value = "/UpdateCalander", method = RequestMethod.GET)
	public ModelAndView updatecalander(@RequestParam String date) {
		ModelAndView mav = new ModelAndView("Calander");
		CalanderEntity Calander = calanderService.getRm(date);
		mav.addObject("calander", Calander);
		return mav;
	}

}
