package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.LocationMaster;
import com.navitsa.hrm.repository.LocationMasterRepository;

@Service
public class LocationService {

	@Autowired
	private LocationMasterRepository locService;
	
	public List<LocationMaster> getAllLocations() {
		return (List<LocationMaster>) locService.findAll();
	}
}
