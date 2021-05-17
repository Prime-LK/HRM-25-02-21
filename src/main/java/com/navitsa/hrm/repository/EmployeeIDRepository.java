package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.EmployeeID;
import com.navitsa.hrm.entity.EmployeeIDPK;



public interface EmployeeIDRepository extends CrudRepository<EmployeeID, EmployeeIDPK>{
	
	//getElementsBYCompositeID
	@Query(value = "SELECT cm FROM EmployeeID cm WHERE cm.employeeIDPK.empID.empID  =:eid AND cm.employeeIDPK.employeeIdDocument.docTypeId =:empdoc AND cm.employeeIDPK.docid =:docid")
	public EmployeeID setEmployeeIDDetails(@Param("eid")String eid,@Param("empdoc")String empdoc,@Param("docid")String docid);
	
	//getmaxDocumentID
	@Query(value = "SELECT (max(e.employeeIDPK.docid)+1) FROM EmployeeID e")
	public String maxEmployeeDocMAXID();
	
	@Query(value = "SELECT  s FROM EmployeeID s WHERE s.employeeIDPK.empID.empID = :empID")
	public List<EmployeeID> searchEmployeeIdDetails(@Param("empID") String empID);
}
