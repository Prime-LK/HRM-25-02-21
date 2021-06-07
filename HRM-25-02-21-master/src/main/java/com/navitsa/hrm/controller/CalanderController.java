package com.navitsa.hrm.controller;


import java.util.ArrayList;
import java.util.List;
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

import com.navitsa.hrm.entity.CalanderEntity;
import com.navitsa.hrm.service.CalanderService;

@Controller
public class CalanderController {

	@Autowired
	private CalanderService calanderService;

	@RequestMapping(value = "/calanderOpen", method = RequestMethod.GET)
	public String calanderOpen(Map<String, Object> model) {
		model.put("calander", new CalanderEntity());
		model.put("calanderAll", calanderService.getAll());

		return "hrm/Calander";
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
	public String savecalander(@ModelAttribute("calander") CalanderEntity calander,
			/* @RequestParam("ID") Integer ID, */
			@RequestParam("date") ArrayList<String> date, @RequestParam("calander_ID") ArrayList<String> calander_ID,
			@RequestParam("description") ArrayList<String> description, @RequestParam("types") ArrayList<String> types,
			@RequestParam(value = "company_ID", required = false) String company_ID,
			@RequestParam(value = "status", required = false) String status) {

		List<CalanderEntity> list = new ArrayList<CalanderEntity>();

		for (int dat = 1, cID = 0, dec = 0, type = 0; dat < date.size() && cID < calander_ID.size()
				&& dec < description.size() && type < types.size(); dat++, cID++, dec++, type++) {
				
				CalanderEntity d = new CalanderEntity();
				
				d.setDate(date.get(dat));
				d.setCalander_ID(calander_ID.get(cID));
				d.setDescription(description.get(dec));
				d.setTypes(types.get(type));
				d.setStatus("");
				d.setCompany_ID("");

			  list.add(d);
			 
		}

		calanderService.saveEmpMoDe(list);
		
		return "redirect:/calanderOpen";
	}

	@RequestMapping(value = "/UpdateCalander", method = RequestMethod.GET)
	public ModelAndView updatecalander(@RequestParam String date) {
		ModelAndView mav = new ModelAndView("hrm/Calander");
		CalanderEntity Calander = calanderService.getRm(date);
		mav.addObject("calander", Calander);
		return mav;
	}

}
