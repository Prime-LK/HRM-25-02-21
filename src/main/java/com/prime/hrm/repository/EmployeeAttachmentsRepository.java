package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.EmployeeAttachments;
import com.prime.hrm.entity.EmployeeAttachmentsPK;

public interface EmployeeAttachmentsRepository extends CrudRepository<EmployeeAttachments, EmployeeAttachmentsPK>{

	
	//getElementByID
	@Query(value = "SELECT cm FROM EmployeeAttachments cm WHERE cm.employeeAttachmentsPK.empID.empID  =:empID AND cm.employeeAttachmentsPK.attachmentID =:attachmentID")
	public EmployeeAttachments setEmployeeAttachmentsDetails(@Param("empID")String empID,@Param("attachmentID")String attachmentID);
	
	
	//get max id
	@Query(value = "SELECT (max(ed.employeeAttachmentsPK.attachmentID)+1) FROM EmployeeAttachments ed")
	public String maxEmployeeAttachmentsID();
	
	
	//loadEmps
	@Query(value="SELECT ea FROM  EmployeeAttachments ea WHERE ea.employeeAttachmentsPK.empID.empID=:empID")
	public List<EmployeeAttachments> getEmps(@Param("empID")String empID);
}
