package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.EmployeeWorkExperience;
import com.navitsa.hrm.entity.EmployeeWorkExperiencePK;

public interface EmployeeWorkExperienceRepository extends CrudRepository<EmployeeWorkExperience, EmployeeWorkExperiencePK> {

	@Query(value = "SELECT (max(ew.employeeWorkExperiencePK.expId)+1) FROM EmployeeWorkExperience ew")
	public String maxExpID();
	
	//getElementByID
		@Query(value = "SELECT cm FROM EmployeeWorkExperience cm WHERE cm.employeeWorkExperiencePK.empID.empID  =:empID AND cm.employeeWorkExperiencePK.expId =:expId")
		public EmployeeWorkExperience setEmployeeWorkExperiencecDetails(@Param("empID")String empID,@Param("expId")String expId);
		
		//loadEmp
		@Query(value="SELECT we FROM  EmployeeWorkExperience we WHERE we.employeeWorkExperiencePK.empID.empID=:empID")
		public List<EmployeeWorkExperience> getEmps(@Param("empID")String empID);
}
