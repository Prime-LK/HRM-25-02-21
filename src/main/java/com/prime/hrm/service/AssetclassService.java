package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.Assestclass;
import com.prime.hrm.entity.Depreciationgroup;
import com.prime.hrm.repository.AssestclassRepository;
import com.prime.hrm.repository.DepreciationgroupRepository;

@Service
public class AssetclassService {

	@Autowired
	private AssestclassRepository AsRepo;

	@Autowired
	private DepreciationgroupRepository depreciationgroupRepository;

	public void savell(Assestclass asset) {
		AsRepo.save(asset);
	}

	public Assestclass getDetailsofDeprection(String Depreciation_Type_Code) {
		return AsRepo.findById(Depreciation_Type_Code).get();
	}

	public String getMaxBMID() {
		if (depreciationgroupRepository.getMaxID() == null) {
			return "1";
		} else {
			return depreciationgroupRepository.getMaxID();
		}
	}

	public List<Depreciationgroup> getAlldata() {
		return (List<Depreciationgroup>) depreciationgroupRepository.findAll();
	}

	public List<Assestclass> getAllRm() {
		return (List<Assestclass>) AsRepo.findAll();
		// return AsRepo;
	}

	public Assestclass getRm(String class_Code) {
		return AsRepo.findById(class_Code).get();
	}
}
