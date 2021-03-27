package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.DesignationMaster;

public interface DesignationMasterRepository extends CrudRepository<DesignationMaster, String> {

	@Query(value = "SELECT (max(d.did)+1) FROM DesignationMaster d ")
	public String maxDesignationID();
	
	@Query(value="SELECT dm FROM DesignationMaster dm WHERE dm.company.comID = :companyId")
	public List<DesignationMaster> getAllDesignationsByCompany(@Param("companyId") String companyId);
}
