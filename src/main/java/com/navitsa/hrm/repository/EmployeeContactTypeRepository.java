package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.EmployeeContactType;

public interface EmployeeContactTypeRepository extends CrudRepository<EmployeeContactType, String> {

	@Query(value = "SELECT (max(et.cTypeID)+1) FROM EmployeeContactType et")
	public String maxcTypeID();
}
