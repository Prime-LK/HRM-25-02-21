package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.MonthProcessPayCode;
import com.prime.hrm.repository.MonthProcessPayCodeRepository;

@Service
public class MonthProcessPayCodeService {

	@Autowired
	private MonthProcessPayCodeRepository moProPCRepo;
	
	public void saveMonthProcessPayCode(MonthProcessPayCode details) {
		moProPCRepo.save(details);
	}
	
	public String[][] saveEachEmpWithAllowance() {
		return moProPCRepo.saveEachEmpWithAllowance();
	}
	
	public List<String> adiitionsVariable() {
		return moProPCRepo.adiitionsVariable();
	} 
	
	public List<MonthProcessPayCode> saveList(List<MonthProcessPayCode> list) {
		return moProPCRepo.saveAll(list);
	}
	
	public void deleteAllData() {
		moProPCRepo.deleteAll();
	}
	
  }
