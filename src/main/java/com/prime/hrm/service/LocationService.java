package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.LocationMaster;
import com.prime.hrm.repository.LocationMasterRepository;

@Service
public class LocationService {

	@Autowired
	private LocationMasterRepository locService;
	
	public List<LocationMaster> getAllLocations() {
		return (List<LocationMaster>) locService.findAll();
	}
}
