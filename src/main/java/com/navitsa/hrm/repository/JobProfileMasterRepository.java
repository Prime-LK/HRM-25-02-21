package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.JobProfileMaster;

public interface JobProfileMasterRepository extends CrudRepository<JobProfileMaster, String> {

	@Query(value = "SELECT (max(pm.profileID)+1) FROM JobProfileMaster pm")
	public String profileID();

	@Query(value = "SELECT jpm FROM JobProfileMaster jpm WHERE jpm.company.comID = :companyId")
	public List<JobProfileMaster> getAllJobProfileByCompany(@Param("companyId") String companyId);
}
