package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.EmployeeExtraActivity;
import com.prime.hrm.entity.ExtraActivityTypePK;

public interface EmployeeExtraActivityRepository extends CrudRepository<EmployeeExtraActivity, ExtraActivityTypePK> {


	//getElementsBYCompositeID
		@Query(value = "SELECT cm FROM EmployeeExtraActivity cm WHERE cm.actPK.empID.empID  =:empID AND cm.actPK.eType.actTypeID =:actTypeID")
		public EmployeeExtraActivity setEmployeeExtraActivityDetails(@Param("empID")String empID,@Param("actTypeID")String actTypeID);
		
		@Query(value = "SELECT  s FROM EmployeeExtraActivity s WHERE s.actPK.empID.empID = :empID")
		public List<EmployeeExtraActivity> searchEmployeeExtraActivityDetails(@Param("empID") String empID);
}
