package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.MonthProcessMaster;
import com.navitsa.hrm.repository.MonthProcessMasterRepository;

@Service
public class MonthProcessMasterService {

	@Autowired
	private MonthProcessMasterRepository moProMaRepo;
	
	public String getMID() {
		if(moProMaRepo.maxMID() == null) {
			return "1";
		} else {
			return moProMaRepo.maxMID();
		}
	}
	
	public void saveMoProMaster(MonthProcessMaster a) {
		moProMaRepo.save(a);
	}
	
	public List<MonthProcessMaster> saveMoProMas(List<MonthProcessMaster> list) {
		return (List<MonthProcessMaster>) moProMaRepo.saveAll(list);
	}
	
	public void deleteAllDataMoProMas() {
		moProMaRepo.deleteAll();
	}
}
