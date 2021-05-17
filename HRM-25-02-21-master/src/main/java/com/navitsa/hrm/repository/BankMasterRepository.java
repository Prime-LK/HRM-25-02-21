package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.BankMaster;

public interface BankMasterRepository extends CrudRepository<BankMaster, String> {

	@Query(value="SELECT (max(bm.bankid)+1) FROM BankMaster bm")
	public String getMaxID();
}
