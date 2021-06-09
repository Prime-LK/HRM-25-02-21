package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.DependentTypeMaster;

public interface DependentTypeMasterRepository extends CrudRepository<DependentTypeMaster, String> {

	@Query(value = "SELECT (max(dt.dTypeID)+1) FROM DependentTypeMaster dt")
	public String maxDTypeID();

	@Query(value = "SELECT dtm FROM DependentTypeMaster dtm WHERE dtm.company.comID = :companyId")
	public List<DependentTypeMaster> getAllDependentTypeByCompany(@Param("companyId") String companyId);
}
