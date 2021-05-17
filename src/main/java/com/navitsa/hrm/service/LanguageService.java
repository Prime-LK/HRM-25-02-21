package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeLanguage;
import com.navitsa.hrm.entity.EmployeeSkill;
import com.navitsa.hrm.entity.LanguageMaster;
import com.navitsa.hrm.repository.EmployeeLanguageRepository;
import com.navitsa.hrm.repository.EmployeeRepository;
import com.navitsa.hrm.repository.LanguageMasterRepository;

@Service
public class LanguageService{

	@Autowired
	private LanguageMasterRepository lmRepo;
	
	@Autowired
	private EmployeeLanguageRepository elRepo;
	
	@Autowired
	private EmployeeRepository eRepo;
	
	//employee master-------------------------------------
	
	public String maxLmID() {
		if(lmRepo.maxLmID() == null) {
			return "1";
		} else {
		return lmRepo.maxLmID();
		}
	}
	
	public void saveLMaster(LanguageMaster lm) {
		lmRepo.save(lm);
	}

	public List<LanguageMaster> getAllLm() {
		return (List<LanguageMaster>) lmRepo.findAll();
	}
	
	public LanguageMaster getLanguage(String lid) {
		return lmRepo.findById(lid).get();
	}

	
	//employee language-------------------------------------
	
	public void saveEmpLa(EmployeeLanguage el) {
		elRepo.save(el);
	}

	public List<EmployeeLanguage> getAllEl() {
		return (List<EmployeeLanguage>) elRepo.findAll();
	}
	
	public List<LanguageMaster> getAlllm() {
		return (List<LanguageMaster>) lmRepo.findAll();
	}
	
	public List<Employee> getAllEmp() {
		return (List<Employee>) eRepo.findAll();
	}
	
//	public EmployeeLanguage getEmpLa(String id) {
//		return elRepo.findById(id).get();
//	}
	
	public EmployeeLanguage getdata(String empID,String languageId) {
		return elRepo.setEmployeeLanguageDetails(empID, languageId);
	}
	
	public List<EmployeeLanguage> searchEmployeeLanguage(String empID) {
		
		return elRepo.searchEmployeeLanguageDetails(empID);
	}
}
