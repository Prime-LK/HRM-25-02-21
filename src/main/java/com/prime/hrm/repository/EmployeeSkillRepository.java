package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.EmployeeSkill;
import com.prime.hrm.entity.EmployeeSkillPK;

public interface EmployeeSkillRepository extends CrudRepository<EmployeeSkill, EmployeeSkillPK> {

	// getElementsBYCompositeID
	@Query(value = "SELECT cm FROM EmployeeSkill cm WHERE cm.skillPK.empID.empID  =:empID AND cm.skillPK.sid.sid =:sid")
	public EmployeeSkill setEmployeeSkillDetails(@Param("empID") String empID, @Param("sid") String sid);

	// getEmployee skill data acording to "empID"
	@Query(value = "SELECT  s FROM EmployeeSkill s WHERE s.skillPK.empID.empID = :empID")
	public List<EmployeeSkill> searchEmployeeSkillDetails(@Param("empID") String empID);
	
	//skill report data
	@Query(value="SELECT es FROM EmployeeSkill es WHERE es.skillPK.sid.sid=:sid")
	public List<EmployeeSkill> getSkillData(@Param("sid") String sid);
	
	//get skill report data
	@Query(value="SELECT sr.skillPK.empID.empID as empid, sr.skillPK.empID.name as fname,"
			+ " sr.skillPK.empID.lastname as lname, sr.skillPK.sid.skill as sType,"
			+ " sr.sProficiency as profeciency FROM EmployeeSkill sr WHERE"
			+ " sr.skillPK.sid.sid=:sid ORDER BY sr.skillPK.empID.empID")
	public String[][] getReportToData(@Param("sid") String sid);
}
