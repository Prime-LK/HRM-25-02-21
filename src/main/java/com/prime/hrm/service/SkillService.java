package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeAddress;
import com.prime.hrm.entity.EmployeeSkill;
import com.prime.hrm.entity.SkillMaster;
import com.prime.hrm.repository.EmployeeRepository;
import com.prime.hrm.repository.EmployeeSkillRepository;
import com.prime.hrm.repository.SkillMasterRepository;

@Service
public class SkillService {

	@Autowired
	private SkillMasterRepository skillRepo;
	
	@Autowired
	private EmployeeSkillRepository empSkRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	//SkillMaster-----------------
	public String maxSkillID() {
		if(skillRepo.maxSkillID() == null) {
			return "1";
		} else {
		return skillRepo.maxSkillID();
		}
	}
	
	public SkillMaster getSkill(String sid) {
		return skillRepo.findById(sid).get();
	}
	
	public void saveSkill(SkillMaster sm) {
		 skillRepo.save(sm);
	}
	
	public List<SkillMaster> getAllSkills() {
		return (List<SkillMaster>) skillRepo.findAll();
	}
	
	//emp ependent-----------------------------------------
	
	public EmployeeSkill getSId(String empID,String sid) {
		return empSkRepo.setEmployeeSkillDetails(empID, sid);
	}
	
	public void saveEmpSkill(EmployeeSkill es) {
		empSkRepo.save(es);
	}
	
	public List<EmployeeSkill> getAllEmpSkill() {
		return (List<EmployeeSkill>) empSkRepo.findAll();
	}
	
	public List<Employee> getAllEmps() {
		return (List<Employee>) empRepo.findAll();
	}
	
	//load address details according to employeeID to empskill jsp
		public List<EmployeeSkill> searchEmployeeSkill(String empID) {
			
			return empSkRepo.searchEmployeeSkillDetails(empID);
		}
		
		//skill report
		public List<EmployeeSkill> getSkillTypeToReport(String sid) {
			return empSkRepo.getSkillData(sid);
		}
			
		public String[][] getDataToReport(String sid) {
			return empSkRepo.getReportToData(sid);
		}	
}
