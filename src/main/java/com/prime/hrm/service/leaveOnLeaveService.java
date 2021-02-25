package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.LeaveOnLeaveEntity;
import com.prime.hrm.repository.EmployeeDetailsRepository;
import com.prime.hrm.repository.LeaveClassReository;
import com.prime.hrm.repository.LeaveOnLeaveRepository;


@Service
public class leaveOnLeaveService {

	@Autowired
	private LeaveOnLeaveRepository LoLRepo;

	@Autowired
	private EmployeeDetailsRepository EmpRepo;

	@Autowired
	private LeaveClassReository leaveRepo;

	public Object getAll() {
		return (List<LeaveOnLeaveEntity>) LoLRepo.findAll();
	}

	public void saveAll(LeaveOnLeaveEntity leave) {

		LoLRepo.save(leave);

	}

	public LeaveOnLeaveEntity loadData(String empID) {
		// TODO Auto-generated method stub
		return null;
	}

	public EmployeeDetailsRepository getEmpRepo() {
		return EmpRepo;
	}

	public void setEmpRepo(EmployeeDetailsRepository empRepo) {
		EmpRepo = empRepo;
	}

	

}
