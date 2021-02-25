package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.NationalityMaster;
import com.prime.hrm.entity.ReligionMaster;
import com.prime.hrm.repository.NationalityMasterRepository;
import com.prime.hrm.repository.ReligionMasterRepository;

@Service
public class EthnicService {

	@Autowired
	private NationalityMasterRepository naRepo;
	
	@Autowired
	private ReligionMasterRepository reRepo;
	
	//religion master--------------------------------------------
	public String maxRmId() {
		if(reRepo.maxRmID() == null) {
			return "1";
		} else {
			 return reRepo.maxRmID();
		}
	}

	public List<ReligionMaster> getAllRm() {
		return (List<ReligionMaster>) reRepo.findAll();
	}
	
	public void saveRm(ReligionMaster rm) {
		reRepo.save(rm);
	}
	
	public ReligionMaster getRm(String rid) {
		return reRepo.findById(rid).get();
	} 
	
	//nationality master------------------------------------
	
	public String maxNaId() {
		if(naRepo.maxNmID() == null) {
			return "1";
		} else {
			 return naRepo.maxNmID();
		}
	}

	public List<NationalityMaster> getAllNa() {
		return (List<NationalityMaster>) naRepo.findAll();
	}
	
	public void saveNa(NationalityMaster na) {
		naRepo.save(na);
	}
	
	public NationalityMaster getNa(String nId) {
		return naRepo.findById(nId).get();
	}
	
}
