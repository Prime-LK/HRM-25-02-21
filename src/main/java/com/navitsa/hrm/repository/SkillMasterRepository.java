package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.SkillMaster;

public interface SkillMasterRepository extends CrudRepository<SkillMaster, String> {

	@Query(value = "SELECT (max(sm.sid)+1) FROM SkillMaster sm")
	public String maxSkillID();

	@Query(value = "SELECT sm FROM SkillMaster sm WHERE sm.company.comID = :companyId")
	public List<SkillMaster> getAllSkillByCompany(@Param("companyId") String companyId);
}
