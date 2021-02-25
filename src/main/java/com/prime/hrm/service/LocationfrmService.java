package com.prime.hrm.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.prime.hrm.entity.Location;
import com.prime.hrm.repository.LocationRepository;

public class LocationfrmService {

	@Autowired
	private LocationRepository locationrepository;
	
	
	public  void savelocation(Location loc) 
	{
		locationrepository.save(loc);
	}

}
