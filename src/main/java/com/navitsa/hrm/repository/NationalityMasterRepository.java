package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.NationalityMaster;

public interface NationalityMasterRepository extends CrudRepository<NationalityMaster, String> {

	@Query(value = "SELECT (max(nm.nId)+1) FROM NationalityMaster nm")
	public String maxNmID();

	@Query(value = "SELECT nm FROM NationalityMaster nm WHERE nm.company.comID = :companyId")
	public List<NationalityMaster> getAllNationalityByCompany(@Param("companyId") String companyId);
}
