package com.prime.hrm.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prime.hrm.entity.Depreciationgroup;
import com.prime.hrm.entity.Location;
import com.prime.hrm.repository.LocationRepository;
@Controller

public class LocationController
{
	@Autowired
	private LocationRepository locationrepository;
	
@RequestMapping(value = "/location", method = RequestMethod.GET)
	
	public String loaddpPage(Map<String, Object> map) {
		map.put("loc", new Location());
		Location lc = new Location();
		
		return "location";
}

@RequestMapping(value = "/location", method = RequestMethod.POST)
public String savedep(@Valid @ModelAttribute("loc")Location loc, BindingResult br) {
	if (br.hasErrors()) {
		return "location";
	} 
	else 
	{
		
		locationrepository.save(loc);
			return "redirect:/location";
	}
}
	
}
