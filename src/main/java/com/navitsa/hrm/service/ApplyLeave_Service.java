package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.ApplyLeaveDetail;
import com.navitsa.hrm.entity.ApplyLeave_Entity;
import com.navitsa.hrm.repository.ApplyLeaveDetailRepository;
import com.navitsa.hrm.repository.ApplyLeave_Repository;


@Service
public class ApplyLeave_Service {

	@Autowired
	private ApplyLeave_Repository ALRepo;
	
	@Autowired
	private ApplyLeaveDetailRepository ALDRepo;

	public ApplyLeave_Entity getAll(String leaveID) {

		return ALRepo.findById(leaveID).get();

	}

	public List<ApplyLeave_Entity> getAll() {

		return (List<ApplyLeave_Entity>) ALRepo.findAll();
	}
	
	public void applyLeave(ApplyLeave_Entity applyleave) {

		ALRepo.save(applyleave);

	}
	
	public String getMaxID() {
		if(ALRepo.getMaxID() == null) {
			return "1";
		} else {
			return ALRepo.getMaxID();
		}
	}
	
	public List<ApplyLeave_Entity> getappliedLeaves(String dep_id,String employee_id) {
		
		return ALRepo.findAllByDepID(dep_id,employee_id);
	}

	public List<ApplyLeave_Entity> getappliedLeavesByEmployee(String employeeID) {

		return ALRepo.findAllByEmployeeID(employeeID);
	}

	public int getSumOfApprovedLeaves(String employeeID,String leaveTypeID) {
		
		if(ALRepo.getSumOfApprovedLeaves(employeeID,leaveTypeID) == null) {
			return 0;
		} else {
			return Integer.valueOf(ALRepo.getSumOfApprovedLeaves(employeeID,leaveTypeID));
		}
		
	}
	
	public int getSumOfApprovedLeavesHalf(String employeeID,String leaveTypeID) {
		
		if(ALRepo.getSumOfApprovedLeavesHalf(employeeID,leaveTypeID) == null) {
			return 0;
		} else {
			return Integer.valueOf(ALRepo.getSumOfApprovedLeavesHalf(employeeID,leaveTypeID));
		}

	}

	public void updateApprovedStatus(String applyLeaveID) {
		ALRepo.updateApprovedStatus(applyLeaveID);	
	}
	
	public ApplyLeave_Entity findAppliedLeaveByEmployee(String employeeID) {

		return ALRepo.getByEmployeeID(employeeID);
	}

	public List<ApplyLeaveDetail> getTotalApprovedLeaveBy(String startDate, String endDate, String leaveID) {
		// TODO Auto-generated method stub
		return ALDRepo.getTotalApprovedLeaveBy(startDate,endDate,leaveID);
	}

}
