package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.CalanderEntity;
import com.navitsa.hrm.repository.CalanderRepository;

@Service
public class CalanderService {

	@Autowired
	private CalanderRepository calanderRepo;
	
	public List<CalanderEntity>getAll(){
		return(List<CalanderEntity>)calanderRepo.findAll();
	}

	public void savecalander(CalanderEntity calander) {
		
		calanderRepo.save(calander);
		
	}
	
	public CalanderEntity  getRm(String id) {
		
		return calanderRepo.findById(id).get();
	}

	public CalanderEntity getCalenderDetails(String date) {
		return calanderRepo.getCalanderDetails(date);
	}

	public CalanderEntity setCalenderDetails(String date) {
		return calanderRepo.setCalanderDetails(date);
	}
}
