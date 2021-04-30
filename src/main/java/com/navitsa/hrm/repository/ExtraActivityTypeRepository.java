package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.ExtraActivityType;

public interface ExtraActivityTypeRepository extends CrudRepository<ExtraActivityType, String> {

	@Query(value = "SELECT (max(at.actTypeID)+1) FROM ExtraActivityType at")
	public String maxActTypeID();
}
