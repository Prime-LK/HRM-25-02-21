package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.EmployeeIdDocument;


public interface EmployeeIdDocumentRepository extends CrudRepository<EmployeeIdDocument, String> {

	
	@Query(value = "SELECT (max(ed.docTypeId)+1) FROM EmployeeIdDocument ed")
	public String maxDocTypeID();
	
	
	
}
