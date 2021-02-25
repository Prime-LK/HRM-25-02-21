package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.EmployeeAddress;
import com.prime.hrm.entity.EmployeeExtraActivity;
import com.prime.hrm.entity.EmployeeSkill;
import com.prime.hrm.entity.ExtraActivityType;
import com.prime.hrm.repository.EmployeeExtraActivityRepository;
import com.prime.hrm.repository.ExtraActivityTypeRepository;

@Service
public class ExtraActivityService {

	@Autowired
	private ExtraActivityTypeRepository atRepo;
	
	@Autowired
	private EmployeeExtraActivityRepository eetRepo;
	
	//extra type activity---------------------------------------
	
	public String maxAtID() {
		if(atRepo.maxActTypeID() == null) {
			return "1";
		} else {
		return atRepo.maxActTypeID();
		}
	}
	
	public void saveAType(ExtraActivityType at) {
		atRepo.save(at);
	}

	public List<ExtraActivityType> getAllAt() {
		return (List<ExtraActivityType>) atRepo.findAll();
	}
	
	public ExtraActivityType getAType(String id) {
		return atRepo.findById(id).get();
	}
	
	//employee extra activity------------------------------------------
	
	public void saveEet(EmployeeExtraActivity eet) {
		eetRepo.save(eet);
	}

	public List<EmployeeExtraActivity> getAllEet() {
		return (List<EmployeeExtraActivity>) eetRepo.findAll();
	}
	
//	public EmployeeExtraActivity getEet(String id) {
//		return eetRepo.findById(id).get();
//	}
	
	public EmployeeExtraActivity getEmployeeExtraActivity(String empID,String actTypeID) {
		return eetRepo.setEmployeeExtraActivityDetails(empID, actTypeID);
	}
	
	//load EXTRA ACTIVITY details according to employeeID to empextra activity jsp
			public List<EmployeeExtraActivity> searchEmployeeExtraActivity(String empID) {
				
				return eetRepo.searchEmployeeExtraActivityDetails(empID);
			}
}
