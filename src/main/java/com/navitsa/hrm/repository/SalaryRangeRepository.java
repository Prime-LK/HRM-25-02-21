package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.SalaryRange;

public interface SalaryRangeRepository extends CrudRepository<SalaryRange, String> {

	// get max id
	@Query(value = "SELECT (max(s.salaryRangeID)+1) FROM SalaryRange s ")
	public String maxSRid();

	@Query(value = "SELECT sr FROM SalaryRange sr WHERE sr.company.comID = :companyId")
	public List<SalaryRange> getAllSalaryRangeByCompany(@Param("companyId") String companyId);

	@Query(value = "SELECT sr FROM SalaryRange sr WHERE sr.salaryGrade.gradeID = :gradeId AND sr.company.comID = :companyId")
	public List<SalaryRange> getAllSalaryRangeByGradeAndCompany(@Param("gradeId") String gradeId, @Param("companyId") String companyId);
}
