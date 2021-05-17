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
import com.navitsa.hrm.entity.Location;
import com.navitsa.hrm.repository.LocationRepository;
@Controller

public class LocationController
{
	@Autowired
	private LocationRepository locationrepository;
	
@RequestMapping(value = "/location", method = RequestMethod.GET)
	
	public String loaddpPage(Map<String, Object> map) {
		map.put("loc", new Location());
		Location lc = new Location();
		
		return "hrm/location";
}

@RequestMapping(value = "/location", method = RequestMethod.POST)
public String savedep(@Valid @ModelAttribute("loc")Location loc, BindingResult br) {
	if (br.hasErrors()) {
		return "hrm/location";
	} 
	else 
	{
		
		locationrepository.save(loc);
			return "redirect:/hrm/location";
	}
}
	
}
