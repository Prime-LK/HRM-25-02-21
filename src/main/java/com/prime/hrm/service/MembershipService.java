package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.EmployeeID;
import com.prime.hrm.entity.EmployeeMembership;
import com.prime.hrm.entity.EmployeeSkill;
import com.prime.hrm.entity.MembershipInformation;
import com.prime.hrm.repository.EmployeeMembershipRepository;
import com.prime.hrm.repository.MembershipInformationRepository;

@Service
public class MembershipService {

	@Autowired
	private MembershipInformationRepository memRepo;

	@Autowired
	private EmployeeMembershipRepository employeeMembershipRepo;

	public String maxMiID() {
		if (memRepo.maxMiID() == null) {
			return "1";
		} else {
			return memRepo.maxMiID();
		}
	}

	public List<MembershipInformation> getAllMi() {
		return (List<MembershipInformation>) memRepo.findAll();
	}

	public void saveMinfo(MembershipInformation mi) {
		memRepo.save(mi);
	}

	public MembershipInformation getMInfo(String id) {
		return memRepo.findById(id).get();
	}

	// ---Employee Memebeship jsp------
	// save
	public void saveEmpMembership(EmployeeMembership emp) {
		employeeMembershipRepo.save(emp);
	}

	// get saved data as list
	public List<EmployeeMembership> getAllMembership() {
		return (List<EmployeeMembership>) employeeMembershipRepo.findAll();
	}

	// edit employeeMembershipDATA
	public EmployeeMembership getMemIDDataByID(String eid, String memID) {
		return employeeMembershipRepo.setmMenbershipDetails(eid, memID);
	}

	// load address details according to employeeID to empskill jsp
	public List<EmployeeMembership> searchEmployeeMembership(String empID) {

		return employeeMembershipRepo.searchEmployeeMembershipDetails(empID);
	}
}
