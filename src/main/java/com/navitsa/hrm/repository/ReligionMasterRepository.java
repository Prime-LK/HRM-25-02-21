package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.ReligionMaster;

public interface ReligionMasterRepository extends CrudRepository<ReligionMaster, String> {

	@Query(value = "SELECT (max(rm.rid)+1) FROM ReligionMaster rm")
	public String maxRmID();
	
}
