package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeAddress;
import com.navitsa.hrm.entity.EmployeeSkill;
import com.navitsa.hrm.entity.SkillMaster;
import com.navitsa.hrm.repository.EmployeeRepository;
import com.navitsa.hrm.repository.EmployeeSkillRepository;
import com.navitsa.hrm.repository.SkillMasterRepository;

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
