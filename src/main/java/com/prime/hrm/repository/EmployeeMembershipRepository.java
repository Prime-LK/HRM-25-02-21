package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.EmployeeMembership;
import com.prime.hrm.entity.EmployeeMembershipPK;

public interface EmployeeMembershipRepository extends CrudRepository<EmployeeMembership, EmployeeMembershipPK>{
	
	//getElementsBYCompositeID
		@Query(value = "SELECT em FROM EmployeeMembership em WHERE em.employeeMembershipPK.empID.empID  =:eid AND em.employeeMembershipPK.membershipInformation.memID =:memID")
		public EmployeeMembership setmMenbershipDetails(@Param("eid")String eid,@Param("memID")String memID);
	
		@Query(value = "SELECT  s FROM EmployeeMembership s WHERE s.employeeMembershipPK.empID.empID = :empID")
		public List<EmployeeMembership> searchEmployeeMembershipDetails(@Param("empID") String empID);
}
