package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.ApplyLeave_Entity;
import com.navitsa.hrm.repository.ApplyLeave_Repository;


@Service
public class ApplyLeave_Service {

	
	
	@Autowired
	private ApplyLeave_Repository ALRepo;

	public ApplyLeave_Entity getAll(String leaveID) {

		return ALRepo.findById(leaveID).get();

	}

	public List<ApplyLeave_Entity> getAll() {

		return (List<ApplyLeave_Entity>) ALRepo.findAll();
	}
	
	public void applyLeave(ApplyLeave_Entity applyleave) {

		ALRepo.save(applyleave);

	}
	
	public String getMaxID() {
		if(ALRepo.getMaxID() == null) {
			return "1";
		} else {
			return ALRepo.getMaxID();
		}
	}
}
