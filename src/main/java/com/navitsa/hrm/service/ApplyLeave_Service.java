package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.ApplyLeaveDetail;
import com.navitsa.hrm.entity.ApplyLeave;
import com.navitsa.hrm.repository.ApplyLeaveDetailRepository;
import com.navitsa.hrm.repository.ApplyLeave_Repository;


@Service
public class ApplyLeave_Service {

	@Autowired
	private ApplyLeave_Repository ALRepo;
	
	@Autowired
	private ApplyLeaveDetailRepository ALDRepo;

	public ApplyLeave getAll(String leaveID) {

		return ALRepo.findById(leaveID).get();

	}

	public List<ApplyLeave> getAll() {

		return (List<ApplyLeave>) ALRepo.findAll();
	}
	
	public void applyLeave(ApplyLeave applyleave) {

		ALRepo.save(applyleave);

	}
	
/*	public String getMaxID() {
		if(ALRepo.getMaxID() == null) {
			return "1";
		} else {
			return ALRepo.getMaxID();
		}
	}
*/	
	public List<ApplyLeave> getappliedLeaves(String dep_id,String employee_id) {
		
		return ALRepo.findAllByDepID(dep_id,employee_id);
	}

	public List<ApplyLeave> getappliedLeaveByEmployee(String employeeID, String companyID) {

		return ALRepo.findAllByEmployeeID(employeeID,companyID);
	}

	public int getSumOfApprovedLeaves(String employeeID,String leaveTypeID, String companyID) {
		
		if(ALRepo.getSumOfApprovedLeaves(employeeID,leaveTypeID,companyID) == null) {
			return 0;
		} else {
			return Integer.valueOf(ALRepo.getSumOfApprovedLeaves(employeeID,leaveTypeID,companyID));
		}
		
	}
	
	public int getSumOfApprovedLeavesHalf(String employeeID,String leaveTypeID, String companyID) {
		
		if(ALRepo.getSumOfApprovedLeavesHalf(employeeID,leaveTypeID,companyID) == null) {
			return 0;
		} else {
			return Integer.valueOf(ALRepo.getSumOfApprovedLeavesHalf(employeeID,leaveTypeID,companyID));
		}
	}

	public void updateApprovedStatus(String applyLeaveID) {
		ALRepo.updateApprovedStatus(applyLeaveID);	
	}
	
	public List<ApplyLeave> findAppliedLeaveByEmployee(String employeeID, String companyID, String fromDate, String toDate) {

		return ALRepo.getByEmployeeID(employeeID,companyID,fromDate,toDate);
	}
	
	public ApplyLeave findLeaveBy(String employeeID, String companyID, String date) {

		return ALRepo.getLeaveBy(employeeID,companyID,date);
	}

//	public List<ApplyLeaveDetail> getTotalApprovedLeaveBy(String startDate, String endDate, String leaveID) {
//		return ALDRepo.getTotalApprovedLeaveBy(startDate,endDate,leaveID);
//	}

}
