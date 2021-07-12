package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.PayAddDeductTypes;
import com.navitsa.hrm.repository.PayAddDeductTypesRepository;

@Service
public class PayAddDeductTypeService {

	@Autowired
	private PayAddDeductTypesRepository deTypeRepo;
	
	public String getMaxID() {
		if(deTypeRepo.getMaxID() == null) {
			return "1";
		} else {
			return deTypeRepo.getMaxID();
		}
	}
	
	public PayAddDeductTypes saveDeductType(PayAddDeductTypes deductType) {
		return deTypeRepo.save(deductType);
	}
	
	public List<PayAddDeductTypes> getAllDetails() {
		return (List<PayAddDeductTypes>) deTypeRepo.findAll();
	}
	
	public PayAddDeductTypes updateDeductType(String id) {
		return deTypeRepo.findById(id).get();
	}
	
	public List<PayAddDeductTypes> getAllDetailsbyCompid(String compid) {
		return  deTypeRepo.getAllDetailsbyCompid(compid);
	}
	public List<PayAddDeductTypes> getAllActiveDetailsbyCompid(String compid){
		return  deTypeRepo.getAllActiveDetailsbyCompid(compid);
	}
}
