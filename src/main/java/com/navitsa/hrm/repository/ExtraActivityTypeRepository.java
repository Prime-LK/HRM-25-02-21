package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.ExtraActivityType;

public interface ExtraActivityTypeRepository extends CrudRepository<ExtraActivityType, String> {

	@Query(value = "SELECT (max(at.actTypeID)+1) FROM ExtraActivityType at")
	public String maxActTypeID();

	@Query(value = "SELECT eat FROM ExtraActivityType eat WHERE eat.company.comID = :companyId")
	public List<ExtraActivityType> getAllExtraActivityTypeByCompany(@Param("companyId") String companyId);
}
