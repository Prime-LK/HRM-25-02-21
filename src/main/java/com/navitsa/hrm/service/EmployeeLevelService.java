package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.EmployeeCategory;
import com.navitsa.hrm.entity.EmployeeType;
import com.navitsa.hrm.repository.EmployeeCategoryRepository;
import com.navitsa.hrm.repository.EmployeeTypeRepository;

@Service
public class EmployeeLevelService {

	@Autowired
	private EmployeeTypeRepository typeRepo;

	@Autowired
	private EmployeeCategoryRepository catRepo;

	// employee type----------------------------

	public String maxTypeID() {
		if (typeRepo.maxTypeID() == null) {
			return "1";
		} else {
			return typeRepo.maxTypeID();
		}
	}

	public List<EmployeeType> getAllTy() {
		return (List<EmployeeType>) typeRepo.findAll();
	}

	public void saveType(EmployeeType ty) {
		typeRepo.save(ty);
	}

	public EmployeeType getType(String id) {
		return typeRepo.findById(id).get();
	}

	// employee category-----------------------------

	public String maxEcID() {
		if (catRepo.maxEcID() == null) {
			return "1";
		} else {
			return catRepo.maxEcID();
		}
	}

	public List<EmployeeCategory> getAllCat() {
		return (List<EmployeeCategory>) catRepo.findAll();
	}

	public void saveCat(EmployeeCategory ty) {
		catRepo.save(ty);
	}

	public EmployeeCategory getCat(String id) {
		return catRepo.findById(id).get();
	}

	public List<EmployeeType> getAllEmployeeTypeByCompany(String companyId) {
		return (List<EmployeeType>) typeRepo.getAllTypesByCompanny(companyId);
	}

	public List<EmployeeCategory> getAllEmployeeCategoryByCompany(String companyId) {
		return (List<EmployeeCategory>) catRepo.getAllCategoriesBycompanyID(companyId);
	}
}
