package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.SalaryRange;

public interface SalaryRangeRepository extends CrudRepository<SalaryRange, String> {
		
	//get max id
			@Query(value = "SELECT (max(s.salaryRangeID)+1) FROM SalaryRange s ")
			public String maxSRid();

}
