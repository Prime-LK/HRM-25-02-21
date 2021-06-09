package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.EmployeeType;
import com.navitsa.hrm.entity.EmployeeWorkExperience;

public interface EmployeeTypeRepository extends CrudRepository<EmployeeType, String> {

	@Query(value = "SELECT (max(et.tid)+1) FROM EmployeeType et")
	public String maxTypeID();

	@Query(value = "SELECT et FROM  EmployeeType et WHERE et.company.comID=:companyId")
	public List<EmployeeType> getAllTypesByCompanny(@Param("companyId") String companyId);
}
