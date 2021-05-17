package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.MonthProcessMaster;

public interface MonthProcessMasterRepository extends CrudRepository<MonthProcessMaster, String> {

	@Query(value = "SELECT (max(m.MoProMasterID)+1) FROM MonthProcessMaster m")
	public String maxMID();
}
