package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.QualificationMaster;

public interface QualificationRepository extends CrudRepository<QualificationMaster, String> {

	@Query(value = "SELECT (max(qm.qid)+1) FROM QualificationMaster qm")
	public String maxQaID();

	@Query(value = "SELECT qm FROM QualificationMaster qm WHERE qm.company.comID = :companyId")
	public List<QualificationMaster> getAllQualificationByCompany(@Param("companyId") String companyId);
}
