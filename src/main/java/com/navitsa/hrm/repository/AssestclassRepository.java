package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.Assestclass;

public interface AssestclassRepository extends CrudRepository<Assestclass, String> {

	@Query(value = "SELECT (max(rm.class_Code)+1) FROM Assestclass rm")
	public String getAssestsMaxID();
	
}
