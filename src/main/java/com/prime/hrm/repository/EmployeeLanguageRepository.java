package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.EmployeeLanguage;
import com.prime.hrm.entity.LanguagePK;

public interface EmployeeLanguageRepository extends CrudRepository<EmployeeLanguage, LanguagePK> {
	
	@Query(value = "SELECT cm FROM EmployeeLanguage cm WHERE cm.lanPk.empID.empID  =:empID AND cm.lanPk.lid.lid =:languageId")
	public EmployeeLanguage setEmployeeLanguageDetails(@Param("empID")String empID,@Param("languageId")String languageId);
	
	@Query(value = "SELECT  s FROM EmployeeLanguage s WHERE s.lanPk.empID.empID = :empID")
	public List<EmployeeLanguage> searchEmployeeLanguageDetails(@Param("empID") String empID);

}
