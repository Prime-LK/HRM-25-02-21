package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.LeaveType;
import com.navitsa.hrm.repository.LeaveTypeRepository;



@Service
public class LeaveTypeService {
	
	@Autowired
	private LeaveTypeRepository leaveTypeRepo;
	
	public List<LeaveType> getLeaveTypesByCompany(String companyId){
		
		return (List<LeaveType>)leaveTypeRepo.getLeaveTypesByCompany(companyId);
	}
	
	public void saveLeaveType(LeaveType leaveType) {
		
		leaveTypeRepo.save(leaveType);
	}

	public LeaveType getLeaveTypeByCode(String leaveCode) {
		
		return leaveTypeRepo.findById(leaveCode).get();
		
	}


}
