package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.ProcessPayrollDetails;
import com.navitsa.hrm.repository.ProcessPayrollDetailsRepository;

@Service
public class ProcessPayrollDetailsService {

	@Autowired
	private ProcessPayrollDetailsRepository proPaDeRepo;
	
	public List<ProcessPayrollDetails> saveListOfDetails(List<ProcessPayrollDetails> details) {
		return (List<ProcessPayrollDetails>) proPaDeRepo.saveAll(details);
	} 
	
	public List<ProcessPayrollDetails> getAllRecords() {
		return (List<ProcessPayrollDetails>) proPaDeRepo.findAll();
	}
	
	public void saveObjData(ProcessPayrollDetails detail) {
		 proPaDeRepo.save(detail);
	}
	
	public void deleteAllProcessPayrollDetailsData() {
		proPaDeRepo.deleteAll();
	}
}
