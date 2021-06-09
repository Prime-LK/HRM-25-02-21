package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.MembershipInformation;

public interface MembershipInformationRepository extends CrudRepository<MembershipInformation, String> {

	@Query(value = "SELECT (max(mi.memID)+1) FROM MembershipInformation mi")
	public String maxMiID();

	@Query(value = "SELECT mi FROM MembershipInformation mi WHERE mi.company.comID = :companyId")
	public List<MembershipInformation> getAllMembershipInformationByCompany(@Param("companyId") String companyId);
}
