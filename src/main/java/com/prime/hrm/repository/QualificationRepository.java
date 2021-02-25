package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.QualificationMaster;

public interface QualificationRepository extends CrudRepository<QualificationMaster, String> {

	@Query(value = "SELECT (max(qm.qid)+1) FROM QualificationMaster qm")
	public String maxQaID();
}
