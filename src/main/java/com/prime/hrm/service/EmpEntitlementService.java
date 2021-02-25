package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.EmpEntitlementsClass;
import com.prime.hrm.entity.EmployeeCategory;
import com.prime.hrm.entity.leaveClass;
import com.prime.hrm.repository.EmpEntitlementRepository;
import com.prime.hrm.repository.EmployeeCategoryRepository;
import com.prime.hrm.repository.LeaveClassReository;

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

}
