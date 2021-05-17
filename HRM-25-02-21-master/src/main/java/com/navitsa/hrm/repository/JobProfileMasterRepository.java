package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.JobProfileMaster;

public interface JobProfileMasterRepository extends CrudRepository<JobProfileMaster, String> {

	@Query(value="SELECT (max(pm.profileID)+1) FROM JobProfileMaster pm")
	public String profileID();
}
