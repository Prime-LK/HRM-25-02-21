package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.NationalityMaster;

public interface NationalityMasterRepository extends CrudRepository<NationalityMaster, String> {

	@Query(value = "SELECT (max(nm.nId)+1) FROM NationalityMaster nm")
	public String maxNmID();
}
