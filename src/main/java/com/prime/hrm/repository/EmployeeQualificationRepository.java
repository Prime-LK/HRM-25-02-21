package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.EmployeeQualification;
import com.prime.hrm.entity.QualificationPK;


public interface EmployeeQualificationRepository extends CrudRepository<EmployeeQualification, QualificationPK> {

	@Query(value = "SELECT eq FROM EmployeeQualification eq WHERE eq.quaPK.empID.empID=:empID AND eq.quaPK.qMaster.qid=:qid")
	public EmployeeQualification setEdit(@Param("empID")String empID,@Param("qid")String qid);
	
	@Query(value = "SELECT q FROM EmployeeQualification q WHERE q.quaPK.empID.empID=:empID")
	public List<EmployeeQualification> getEmpQualificationData(@Param("empID") String empID);
	
	//get data to jasper report
	@Query(value="	SELECT em.quaPK.empID.empID as empid,  em.quaPK.empID.name as empfname ,  em.quaPK.empID.lastname as emplname , em.quaPK.qMaster.qualification as qtype , em.desc as qdesc , DATE_FORMAT(em.award,'%d/%m/%Y') as qaward "
			+ " FROM EmployeeQualification em where em.quaPK.qMaster.qid =:qid")
	 String[][] getempQualificationSummaryReportData(@Param("qid")String qid);
	
	//get data to jsp report 
	@Query(value="	SELECT em FROM EmployeeQualification em order by em.quaPK.empID.empID , em.quaPK.qMaster.qualification")
	public List<EmployeeQualification> getfindAll();
	
//	//get data to jasper report
//	@Query(value=" SELECT  em.quaPK.empID.name as empfname ,  em.quaPK.empID.lastname as emplname , em.quaPK.qMaster.qualification as qtype , em.desc as qdesc , em.award as qaward "
//	+ " FROM EmployeeQualification em ")
//	String[][] getempQualificationSummaryReportData();
//
//	//get data to jsp report
//	@Query(value=" SELECT em FROM EmployeeQualification em order by em.quaPK.empID.empID , em.quaPK.qMaster.qualification")
//	public List<EmployeeQualification> getfindAll();
}
