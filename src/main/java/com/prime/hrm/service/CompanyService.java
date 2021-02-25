package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.CompanyMaster;
import com.prime.hrm.repository.CompanyMasterRepository;

@Service
public class CompanyService {

	@Autowired
	public CompanyMasterRepository comRepo;
	
	public String getComMaxID() {
		if(comRepo.getMaxID() == null) {
			return "1";
		} else {
			return comRepo.getMaxID();
		}
	}
	
	public void saveCompanyData(CompanyMaster a) {
		comRepo.save(a);
	}
	
	public CompanyMaster getOneData(String id) {
		return comRepo.findById(id).get();
	}
	
	public List<CompanyMaster> getAllComDetails() {
		return comRepo.findAll();
	}
	
	
	
	
}
