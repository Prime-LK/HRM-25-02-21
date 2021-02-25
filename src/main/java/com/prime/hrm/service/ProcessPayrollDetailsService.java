package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.ProcessPayrollDetails;
import com.prime.hrm.repository.ProcessPayrollDetailsRepository;

@Service
public class ProcessPayrollDetailsService {

	@Autowired
	private ProcessPayrollDetailsRepository proPaDeRepo;
	
	public List<ProcessPayrollDetails> saveListOfDetails(List<ProcessPayrollDetails> details) {
		return (List<ProcessPayrollDetails>) proPaDeRepo.saveAll(details);
	} 
	
	public void saveObjData(ProcessPayrollDetails detail) {
		 proPaDeRepo.save(detail);
	}
	
	public void deleteAllProcessPayrollDetailsData() {
		proPaDeRepo.deleteAll();
	}
}
