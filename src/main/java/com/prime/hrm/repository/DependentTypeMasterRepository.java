package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.DependentTypeMaster;

public interface DependentTypeMasterRepository extends CrudRepository<DependentTypeMaster, String> {

	@Query(value = "SELECT (max(dt.dTypeID)+1) FROM DependentTypeMaster dt")
	public String maxDTypeID();
}
