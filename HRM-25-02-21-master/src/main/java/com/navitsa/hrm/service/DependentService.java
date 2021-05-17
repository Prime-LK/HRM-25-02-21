package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.DependentTypeMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeDependent;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.repository.DependentTypeMasterRepository;
import com.navitsa.hrm.repository.EmployeeDependentRepository;
import com.navitsa.hrm.repository.EmployeeDetailsRepository;
import com.navitsa.hrm.repository.EmployeeRepository;

@Service
public class DependentService {

	@Autowired
	private DependentTypeMasterRepository dTypeRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private EmployeeDependentRepository emDRepo;
	
	@Autowired
	private EmployeeDetailsRepository empDeRepo;

	//dependent type master----------------------------------------
	public List<DependentTypeMaster> getAllDType() {
		return (List<DependentTypeMaster>) dTypeRepo.findAll();
	}
	
	public String dTypeID() {
		if(dTypeRepo.maxDTypeID() == null) {
			return "1";
		} else {
			return dTypeRepo.maxDTypeID();
		}
	}
	
	public void saveDType(DependentTypeMaster dt) {
		dTypeRepo.save(dt);
	}
	
	public DependentTypeMaster getdType(String dTypeID) {
		return dTypeRepo.findById(dTypeID).get();
	}
	
	//emp dependents--------------------------------------
	
	public List<Employee> getAllEmp() {
		return (List<Employee>) empRepo.findAll();
	}

//	public EmployeeDependent getEmpDep(String id) {
//		return emDRepo.findById(id).get();
//	}
	
	public List<EmployeeDependent> getAllEmpDep() {
		return (List<EmployeeDependent>) emDRepo.findAll();
	}
	
	public void saveEmpDep(EmployeeDependent ed) {
		emDRepo.save(ed);
	}
	
	//edit data
	public EmployeeDependent getEdDataByID(String empID,String dTypeID) {
		return emDRepo.setEmployeeDependentDetails(empID, dTypeID);
	}
	//getEmps
	public List<EmployeeDependent> getEmps(String empID) {
		return emDRepo.getEmps(empID);
	}
	
	//dependent Report----------------------------------------------------	
	public String[][] getDepReportData() {
		return emDRepo.getDepReportData();
	}
	public String[][] filterEmpDependents(String Department_ID) {
		return empDeRepo.filterEmpDependents(Department_ID);
	}
	
	public List<EmployeeDetails> loadFilteringData(String Department_ID) {
		return empDeRepo.loadFilteringData(Department_ID);
	}
	
	
}
