package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.MembershipInformation;

public interface MembershipInformationRepository extends CrudRepository<MembershipInformation, String> {

	
	@Query(value = "SELECT (max(mi.memID)+1) FROM MembershipInformation mi")
	public String maxMiID();
}
