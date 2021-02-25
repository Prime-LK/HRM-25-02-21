package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.ApplyLeave_Entity;
import com.prime.hrm.repository.ApplyLeave_Repository;


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
	
	public void savepage(ApplyLeave_Entity applyleave) {

		ALRepo.save(applyleave);

	}
}
