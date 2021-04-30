package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeWorkExperience;
import com.navitsa.hrm.repository.EmployeeRepository;
import com.navitsa.hrm.repository.EmployeeWorkExperienceRepository;

@Service
public class WorkExperienceService {

	@Autowired
	private EmployeeWorkExperienceRepository expRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	
	//employee work experience--------------------------------------------
	
	public List<EmployeeWorkExperience> getAllDExp() {
		return (List<EmployeeWorkExperience>) expRepo.findAll();
	}
	
	public List<Employee> getAllEmp() {
		return (List<Employee>) empRepo.findAll();
	}
	
	public String maxExpID() {
		if(expRepo.maxExpID() == null) {
			return "1";
		} else {
			return expRepo.maxExpID();
		}
	}
	
	public void saveEmpExp(EmployeeWorkExperience we) {
		expRepo.save(we);
	}
	
//	public EmployeeWorkExperience getWexp(String id) {
//		return expRepo.findById(id).get();
//	}
	
	//edit employee workexperience jsp data
	public EmployeeWorkExperience getEmployeeWorkExperienceDataByID(String empID,String expId) {
		return expRepo.setEmployeeWorkExperiencecDetails(empID, expId);
	}
	
	//load Emps
	public List<EmployeeWorkExperience> getEmps(String empID) {
		return expRepo.getEmps(empID);
	}
}
