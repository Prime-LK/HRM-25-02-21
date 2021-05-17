package com.navitsa.hrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.Depreciationgroup;
import com.navitsa.hrm.repository.DepreciationgroupRepository;


@Service
public class DepreciationgroupService {

	@Autowired
	private DepreciationgroupRepository DepRepo;
	
	
	public  void savedep(Depreciationgroup deprec) 
	{
		DepRepo.save(deprec);
	}


	
}