package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.repository.DepartmentMasterRepository;

@Service
public class DepartmentService {

	@Autowired
	public DepartmentMasterRepository depRepo;
	
	public String getDID() {
		if(depRepo.getMaxID() == null) {
			return "1";
		} else {
			return depRepo.getMaxID();
		}
	}
	
	public DepartmentMaster saveDepData(DepartmentMaster depMaster) {
		return depRepo.save(depMaster);
	}
	
	public DepartmentMaster getID(String depID) {
		return depRepo.findById(depID).get();
	}
	
	public List<DepartmentMaster> getAllDep() {
		return (List<DepartmentMaster>) depRepo.findAll();
	}
	
	public List<DepartmentMaster> getallsavedDep(){
		return (List<DepartmentMaster>) depRepo.getallsaveddepartment();
	}
	
	public DepartmentMaster getDepartmentByIdAndCompany(String departmentId, String companyId) {
		return depRepo.getDepartmentByIdAndCompany(departmentId, companyId);
	}
	
	public List<DepartmentMaster> getDepartmentsByCompany(String companyId) {
		return (List<DepartmentMaster>) depRepo.getDepartmentsByCompany(companyId);
	}
}
