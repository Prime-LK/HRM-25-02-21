package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.DependentPK;
import com.navitsa.hrm.entity.EmployeeDependent;

public interface EmployeeDependentRepository extends CrudRepository<EmployeeDependent, DependentPK> {


	//getElementsBYCompositeID
	@Query(value = "SELECT cm FROM EmployeeDependent cm WHERE cm.dependentPK.empID.empID  =:empID AND cm.dependentPK.dTypeID.dTypeID =:dTypeID")
	public EmployeeDependent setEmployeeDependentDetails(@Param("empID")String empID,@Param("dTypeID")String dTypeID);
	
	//get related emp 
	@Query(value="SELECT ed FROM EmployeeDependent ed WHERE ed.dependentPK.empID.empID=:empID")
	public List<EmployeeDependent> getEmps(@Param("empID")String empID);
	
	@Query(value="SELECT ed.dependentPK.empID.empID as empid, ed.dependentPK.empID.name as fname, ed.dependentPK.empID.lastname as lname,"
			+ " ed.dependentPK.dTypeID.dType as dtype, ed.name as dname, DATE_FORMAT(ed.dob,'%d/%m/%Y') as dDob,"
			+ " ed.conNo as contact FROM EmployeeDependent ed")
	String [][] getDepReportData();
	
}
