package com.navitsa.hrm.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.ProcessPayroll;

public interface ProcessPayrollRepository extends CrudRepository<ProcessPayroll, String>{
	
	@Query(value = "SELECT (max(p.processPayrollID)+1) FROM ProcessPayroll p")
	public String maxProcessPayrollID();
	

}
