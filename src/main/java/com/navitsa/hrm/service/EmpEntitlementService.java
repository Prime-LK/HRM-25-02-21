package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.EmpEntitlementsClass;
import com.navitsa.hrm.entity.EmployeeCategory;
import com.navitsa.hrm.entity.leaveClass;
import com.navitsa.hrm.repository.EmpEntitlementRepository;
import com.navitsa.hrm.repository.EmployeeCategoryRepository;
import com.navitsa.hrm.repository.LeaveClassReository;

@Service
public class EmpEntitlementService {

	@Autowired
	private EmpEntitlementRepository empEntRepo;

	@Autowired
	private EmployeeCategoryRepository empCatRepo;

	@Autowired
	private LeaveClassReository leaveClassReository;

	public EmpEntitlementsClass getAll(String ent_ID) {

		return empEntRepo.findById(ent_ID).get();

	}

	public List<EmpEntitlementsClass> getAll() {

		return (List<EmpEntitlementsClass>) empEntRepo.findAll();
	}

	public EmpEntitlementsClass getAlldata(String category) {
		return empEntRepo.findById(category).get();
	}

	public List<EmployeeCategory> getAlldata() {
		return (List<EmployeeCategory>) empCatRepo.findAll();
	}

	public EmpEntitlementsClass getAllLeaves(String leaveType) {
		return empEntRepo.findById(leaveType).get();
	}

	public List<leaveClass> getAllLeaves() {
		return (List<leaveClass>) leaveClassReository.findAll();
	}

	public void saveentitlement(EmpEntitlementsClass empentitlements) {

		empEntRepo.save(empentitlements);

	}

	public String findByIDs(String leaveTypeID, String employeeCategory) {
		
		return empEntRepo.findByIDs(leaveTypeID,employeeCategory);
	}

}
