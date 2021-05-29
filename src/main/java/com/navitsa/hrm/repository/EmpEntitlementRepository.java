package com.navitsa.hrm.repository;




import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.EmpEntitlementsClass;

public interface EmpEntitlementRepository extends CrudRepository <EmpEntitlementsClass, String>{

	@Query(value="SELECT e.leaveAmount FROM EmpEntitlementsClass e WHERE e.empCategory.catgoryID =:employeeCategory AND e.leaveType.leaveCode =:leaveTypeID")
	public String findByIDs(@Param("leaveTypeID") String leaveTypeID, @Param("employeeCategory") String employeeCategory);
	
	
}
