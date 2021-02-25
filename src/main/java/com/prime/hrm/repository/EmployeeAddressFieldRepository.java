package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.EmployeeAddressField;

public interface EmployeeAddressFieldRepository extends CrudRepository<EmployeeAddressField, String> {

	@Query(value = "SELECT (max(af.fieldId)+1) FROM EmployeeAddressField af")
	public String maxAfID();
	
}
