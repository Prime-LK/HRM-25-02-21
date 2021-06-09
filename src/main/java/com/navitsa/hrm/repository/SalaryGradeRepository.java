package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.SalaryGrade;

public interface SalaryGradeRepository extends CrudRepository<SalaryGrade, String> {

	// get max id
	@Query(value = "SELECT (max(s.gradeID)+1) FROM SalaryGrade s ")
	public String maxSgid();

	@Query(value = "SELECT sg FROM SalaryGrade sg WHERE sg.company.comID = :companyId")
	public List<SalaryGrade> getAllSalaryGradeByCompany(@Param("companyId") String companyId);
}
