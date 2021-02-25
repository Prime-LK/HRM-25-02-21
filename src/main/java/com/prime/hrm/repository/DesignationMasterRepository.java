package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.DesignationMaster;

public interface DesignationMasterRepository extends CrudRepository<DesignationMaster, String> {

	

	@Query(value = "SELECT (max(d.did)+1) FROM DesignationMaster d ")
	public String maxDesignationID();
}
