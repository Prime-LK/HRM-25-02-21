package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.EmployeeEntitlement;
import com.navitsa.hrm.repository.EmpEntitlementRepository;

@Service
public class EmpEntitlementService {

	@Autowired
	private EmpEntitlementRepository empEntRepo;

	//@Autowired
	//private EmployeeCategoryRepository empCatRepo;

	//@Autowired
	//private LeaveTypeRepository leaveTypeRepo;

	public EmployeeEntitlement getAll(String ent_ID) {
		
		return empEntRepo.findById(ent_ID).get();
		
	}

	public List<EmployeeEntitlement> getAll() {
		
		return (List<EmployeeEntitlement>) empEntRepo.findAll();
	}

	public void saveentitlement(EmployeeEntitlement empentitlements) {

		empEntRepo.save(empentitlements);

	}

	public String findByLeaveTypeEmployeeType(String leaveTypeID, String employeeType, String companyID) {
		
		return empEntRepo.findByLeaveTypeEmployeeType(leaveTypeID,employeeType, companyID);
	}
	
	public List<EmployeeEntitlement> findAllByCompanyId(String companyID) {

		return empEntRepo.findAllByCompanyId(companyID);
	}

}
