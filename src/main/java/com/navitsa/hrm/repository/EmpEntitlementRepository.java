package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.EmployeeEntitlement;

public interface EmpEntitlementRepository extends CrudRepository <EmployeeEntitlement, String>{

	@Query(value="SELECT e.leaveAmount FROM EmployeeEntitlement e "
			+ "WHERE e.employeeType.tid =:employeeType "
			+ "AND e.leaveType.leaveTypeID =:leaveTypeID AND e.company.comID=:companyID")
	public String findByLeaveTypeEmployeeType(@Param("leaveTypeID") String leaveTypeID, 
			@Param("employeeType") String employeeType, @Param("companyID") String companyID);

	@Query(value="SELECT e FROM EmployeeEntitlement e "
			+ "WHERE e.company.comID =:companyID")
	public List<EmployeeEntitlement> findAllByCompanyId(@Param("companyID") String companyID);
	
	
}
