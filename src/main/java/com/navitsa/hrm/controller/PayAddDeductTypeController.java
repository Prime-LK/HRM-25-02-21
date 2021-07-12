package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.PayAddDeductTypes;
import com.navitsa.hrm.service.PayAddDeductTypeService;

@Controller
public class PayAddDeductTypeController {

	@Autowired
	private PayAddDeductTypeService deService;
	
	@GetMapping("/payAddDeductTypes")
	public String getPage(Map<String,Object>map,HttpSession session) {
		map.put("deductForm", new PayAddDeductTypes());
		
		PayAddDeductTypes type = new PayAddDeductTypes();
		type.setDeductTypeCode("00000".substring(deService.getMaxID().length()) + deService.getMaxID());
		
		String companyID=(String) session.getAttribute("company.comID");
		CompanyMaster cm = new CompanyMaster();
		cm.setComID(companyID);
		type.setCompany(cm);
		
		map.put("deductForm", type);
		return "hrm/payAddDeductTypes";
	}
	
	@PostMapping("/saveDeType")
	public String saveDeductType(@ModelAttribute("deductForm") PayAddDeductTypes deductType,
			RedirectAttributes redirectAttributes) {
		try {
			
			
			if(deductType.getOnBaSalary()==null) {
				deductType.setOnBaSalary("FALSE");
			}
			
			if(deductType.getOnGrSalary()==null) {
				deductType.setOnGrSalary("FALSE");
			}
			
			if(deductType.getIsOnEPFSalary()==null) {
				deductType.setIsOnEPFSalary("FALSE");
			}
			
			deService.saveDeductType(deductType);
			
        	redirectAttributes.addFlashAttribute("success", 1);
	        return "redirect:/payAddDeductTypes";
	        
		} catch(Exception e) {
			System.out.println(e);
			redirectAttributes.addFlashAttribute("success", 0);
		}
		return "hrm/payAddDeductTypes";
	}
	
	@ModelAttribute("deducatTypes")
	public List<PayAddDeductTypes> getAllDetails() {
		return deService.getAllDetails();
	}
	
	@GetMapping("/updateDeductForm")
	public ModelAndView updateDetails(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView("hrm/payAddDeductTypes");
		PayAddDeductTypes type = deService.updateDeductType(id);
		mav.addObject("deductForm", type);
		return mav;
	}
	
	@ModelAttribute("allAddDed")
	public List<PayAddDeductTypes> getAll(HttpSession session) {
		
		String companyID=(String) session.getAttribute("company.comID");			
		return deService.getAllDetailsbyCompid(companyID);
	}
}
