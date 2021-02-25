package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.EmployeeCategory;

public interface EmployeeCategoryRepository extends CrudRepository<EmployeeCategory, String> {

	@Query(value = "SELECT (max(ec.catgoryID)+1) FROM EmployeeCategory ec")
	public String maxEcID();
	
}
