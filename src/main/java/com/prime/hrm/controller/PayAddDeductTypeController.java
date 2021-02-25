package com.prime.hrm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prime.hrm.entity.PayAddDeductTypes;
import com.prime.hrm.service.PayAddDeductTypeService;

@Controller
public class PayAddDeductTypeController {

	@Autowired
	private PayAddDeductTypeService deService;
	
	@GetMapping("/payAddDeductTypes")
	public String getPage(Map<String,Object>map) {
		map.put("deductForm", new PayAddDeductTypes());
		PayAddDeductTypes type = new PayAddDeductTypes();
		type.setDeductTypeCode("00000".substring(deService.getMaxID().length()) + deService.getMaxID());
		map.put("deductForm", type);
		return "payAddDeductTypes";
	}
	
	@PostMapping("/saveDeType")
	public String saveDeductType(@ModelAttribute("deductForm")PayAddDeductTypes deductType) {
		try {
			deService.saveDeductType(deductType);
			return "redirect:/payAddDeductTypes";
		} catch(Exception e) {
			System.out.println("Details Not Saved");
		}
		return "payAddDeductTypes";
	}
	
	@ModelAttribute("deducatTypes")
	public List<PayAddDeductTypes> getAllDetails() {
		return deService.getAllDetails();
	}
	
	@GetMapping("/updateDeductForm")
	public ModelAndView updateDetails(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView("payAddDeductTypes");
		PayAddDeductTypes type = deService.updateDeductType(id);
		mav.addObject("deductForm", type);
		return mav;
	}
	
	@ModelAttribute("allAddDed")
	public List<PayAddDeductTypes> getAll() {
		return deService.getAllDetails();
	}
}
