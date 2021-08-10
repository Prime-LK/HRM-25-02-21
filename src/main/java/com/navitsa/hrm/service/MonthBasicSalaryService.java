package com.navitsa.hrm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.MonthBasicSalary;
import com.navitsa.hrm.repository.MonthBasicSalaryRepository;

@Service
public class MonthBasicSalaryService {
	
	@Autowired
	private MonthBasicSalaryRepository monthBasicSalaryRepository;
	
	public List<MonthBasicSalary> getmonthlyetfreport(String payPeriod,String employeeId,String copid){
		
		return monthBasicSalaryRepository.getmonthlyetfreport(payPeriod,employeeId,copid);//,dep,dis,
	}
	
	

}
