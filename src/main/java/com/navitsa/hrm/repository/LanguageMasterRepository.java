package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.LanguageMaster;

public interface LanguageMasterRepository extends CrudRepository<LanguageMaster, String> {

	@Query(value = "SELECT (max(lm.lid)+1) FROM LanguageMaster lm")
	public String maxLmID();

	@Query(value = "SELECT lm FROM LanguageMaster lm WHERE lm.company.comID = :companyId")
	public List<LanguageMaster> getAllLanguageByCompany(@Param("companyId") String companyId);
}
