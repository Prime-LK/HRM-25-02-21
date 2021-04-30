package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.EmployeeType;

public interface EmployeeTypeRepository extends CrudRepository<EmployeeType, String> {

	@Query(value = "SELECT (max(et.tid)+1) FROM EmployeeType et")
	public String maxTypeID();
	
}
