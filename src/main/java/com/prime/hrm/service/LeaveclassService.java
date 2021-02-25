package com.prime.hrm.service;

import java.util.List;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.leaveClass;
import com.prime.hrm.repository.LeaveClassReository;



@Service
public class LeaveclassService {
	
	@Autowired
	private LeaveClassReository leaveClassRepository;
	
	public List<leaveClass> getAllLeaves(){
		
		return (List<leaveClass>)leaveClassRepository.findAll();
	}
	
	public void saveLeave(leaveClass leaves) {
		
		leaveClassRepository.save(leaves);
	}

	public leaveClass getRm(String leaveCode) {
		
		return leaveClassRepository.findById(leaveCode).get();
		
	}


}
