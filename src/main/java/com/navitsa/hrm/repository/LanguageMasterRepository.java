package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.LanguageMaster;

public interface LanguageMasterRepository extends CrudRepository<LanguageMaster, String> {

	@Query(value = "SELECT (max(lm.lid)+1) FROM LanguageMaster lm")
	public String maxLmID();
	
}
