package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.navitsa.hrm.entity.EmployeeCategory;

public interface EmployeeCategoryRepository extends CrudRepository<EmployeeCategory, String> {

	@Query(value = "SELECT (max(ec.catgoryID)+1) FROM EmployeeCategory ec")
	public String maxEcID();
	
	@Query(value = "SELECT ec FROM EmployeeCategory ec where ec.company.comID=:companyId")
	public List<EmployeeCategory> getAllCategoriesBycompanyID(@Param("companyId")String companyId);
}
