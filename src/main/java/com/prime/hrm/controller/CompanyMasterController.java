package com.prime.hrm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prime.hrm.entity.CompanyMaster;
import com.prime.hrm.service.CompanyService;

@Controller
public class CompanyMasterController {

	@Autowired
	private CompanyService comService;
	
	@GetMapping("/companyMasterPage")
	public String getCompanyPage(Map<String,Object>map) {
		map.put("companyMasterObject", new CompanyMaster());
		CompanyMaster cm = new CompanyMaster();
		cm.setComID("00000".substring(comService.getComMaxID().length()) + comService.getComMaxID());
		map.put("maxComID", cm);
		map.put("companyMasterObject", cm);
		return "companyMaster";
	}
	
	@ModelAttribute("listOfCompanyDetails")
	public List<CompanyMaster> getAllComDetails() {
		return comService.getAllComDetails();
	}
	
	@PostMapping("/saveCompanyMasterData")
	public String saveComDetails(@ModelAttribute("companyMasterObject")CompanyMaster data,RedirectAttributes ra) {
		try {
			ra.addAttribute("success", 1);
			comService.saveCompanyData(data);
			return "redirect:/companyMasterPage";
		
		}catch(Exception e) {
			ra.addAttribute("success", 0);
			System.out.println(e);
		}
		return "companyMaster";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
