package com.navitsa.hrm.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.navitsa.hrm.entity.Location;
import com.navitsa.hrm.repository.LocationRepository;

public class LocationfrmService {

	@Autowired
	private LocationRepository locationrepository;
	
	
	public  void savelocation(Location loc) 
	{
		locationrepository.save(loc);
	}

}
