package com.navitsa.hrm.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.navitsa.hrm.entity.Depreciationgroup;
import com.navitsa.hrm.repository.DepreciationgroupRepository;
@Controller

public class DepreciationGroupController {

	@Autowired
	private DepreciationgroupRepository DepRepo;
	
	
	
	
@RequestMapping(value = "/depreciationgroup", method = RequestMethod.GET)
	
	public String loaddpPage(Map<String, Object> map) {
		map.put("Deprec", new Depreciationgroup());
		Depreciationgroup dep = new Depreciationgroup();
		//map.put("id", asset);
		return "hrm/depreciationgroup";
}
		
		@RequestMapping(value = "/DeprecGroup", method = RequestMethod.POST)
		public String savedep(@Valid @ModelAttribute("Deprec") Depreciationgroup Deprec, BindingResult br) {
			if (br.hasErrors()) {
				return "hrm/depreciationgroup";
			} 
			else 
			{
				
				    DepRepo.save(Deprec);
					return "redirect:/hrm/depreciationgroup";
			}
				/*catch (Exception e) 
				{
					System.out.println("Details Not Saved");
				}*/
			
	}
	
}
