package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.EmployeeIdDocument;

public interface EmployeeIdDocumentRepository extends CrudRepository<EmployeeIdDocument, String> {

	@Query(value = "SELECT (max(ed.docTypeId)+1) FROM EmployeeIdDocument ed")
	public String maxDocTypeID();

	@Query(value = "SELECT eid FROM EmployeeIdDocument eid WHERE eid.company.comID = :companyId")
	public List<EmployeeIdDocument> getAllEmployeeIdDocumentByCompany(@Param("companyId") String companyId);
}
