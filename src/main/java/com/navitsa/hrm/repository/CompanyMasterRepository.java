package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.navitsa.hrm.entity.CompanyMaster;

public interface CompanyMasterRepository extends JpaRepository<CompanyMaster, String> {

	@Query(value="SELECT (max(cm.comID)+1) FROM CompanyMaster cm")
	public String getMaxID();
}
