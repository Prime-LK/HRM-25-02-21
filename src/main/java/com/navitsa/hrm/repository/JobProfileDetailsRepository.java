package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.JobProfileDetails;

public interface JobProfileDetailsRepository extends CrudRepository<JobProfileDetails, String> {

	@Query(value = "SELECT (max(j.jobProfileID)+1) FROM JobProfileDetails j ")
	public String maxJobDetailsID();

	@Query(value = "SELECT jpd FROM JobProfileDetails jpd WHERE jpd.company.comID = :companyId")
	public List<JobProfileDetails> getAllJobProfileDetailsByCompany(@Param("companyId") String companyId);
}
