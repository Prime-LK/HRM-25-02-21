package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.AddressPK;
import com.navitsa.hrm.entity.EmployeeAddress;



public interface EmployeeAddressRepository extends CrudRepository<EmployeeAddress, AddressPK> {
	
	//getElementsBYCompositeID
		@Query(value = "SELECT cm FROM EmployeeAddress cm WHERE cm.addPK.empID.empID  =:eid AND cm.addPK.field.fieldId =:fieldId")
		public EmployeeAddress setEmployeeAddressDetails(@Param("eid")String eid,@Param("fieldId")String fieldId);

	//GET DETAILS ACCORDING TO EMPLOYEE ID
		@Query(value = "SELECT  s FROM EmployeeAddress s WHERE s.addPK.empID.empID = :empID")
		public List<EmployeeAddress> searchaddressDetails(@Param("empID") String empID);
}
