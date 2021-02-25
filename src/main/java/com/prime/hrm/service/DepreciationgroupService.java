package com.prime.hrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.Depreciationgroup;
import com.prime.hrm.repository.DepreciationgroupRepository;


@Service
public class DepreciationgroupService {

	@Autowired
	private DepreciationgroupRepository DepRepo;
	
	
	public  void savedep(Depreciationgroup deprec) 
	{
		DepRepo.save(deprec);
	}


	
}