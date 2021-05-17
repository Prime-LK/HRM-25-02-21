package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.EmployeeContactInfo;
import com.navitsa.hrm.entity.EmployeeContactInfoPK;

public interface EmployeeContactInfoRepository extends CrudRepository<EmployeeContactInfo, EmployeeContactInfoPK>{

	
	//getElementsBYCompositeID
		@Query(value = "SELECT ec FROM EmployeeContactInfo ec WHERE ec.employeeContactInfoPK.empID.empID  =:eid AND ec.employeeContactInfoPK.employeeContactType.cTypeID =:cTypeID")
		public EmployeeContactInfo setEmployeeContactDetails(@Param("eid")String eid,@Param("cTypeID")String cTypeID);
		
		@Query(value="SELECT ci FROM  EmployeeContactInfo ci WHERE ci.employeeContactInfoPK.empID.empID=:empID")
		public List<EmployeeContactInfo> getEmps(@Param("empID")String empID);
}
