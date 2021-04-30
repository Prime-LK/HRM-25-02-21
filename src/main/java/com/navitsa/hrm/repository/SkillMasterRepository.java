package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.SkillMaster;

public interface SkillMasterRepository extends CrudRepository<SkillMaster, String> {

	@Query(value = "SELECT (max(sm.sid)+1) FROM SkillMaster sm")
	public String maxSkillID();
}
