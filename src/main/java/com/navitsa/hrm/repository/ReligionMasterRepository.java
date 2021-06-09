package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.ReligionMaster;

public interface ReligionMasterRepository extends CrudRepository<ReligionMaster, String> {

	@Query(value = "SELECT (max(rm.rid)+1) FROM ReligionMaster rm")
	public String maxRmID();

	@Query(value = "SELECT rm FROM ReligionMaster rm where rm.company.comID=:companyId")
	public List<ReligionMaster> getAllReligionBycompanyID(@Param("companyId") String companyId);
}
