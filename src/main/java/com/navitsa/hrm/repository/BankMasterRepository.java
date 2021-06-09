package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.BankMaster;

public interface BankMasterRepository extends CrudRepository<BankMaster, String> {

	@Query(value = "SELECT (max(bm.bankid)+1) FROM BankMaster bm")
	public String getMaxID();

	@Query(value = "SELECT bm FROM BankMaster bm WHERE bm.company.comID = :companyId")
	public List<BankMaster> getAllBankByCompany(@Param("companyId") String companyId);
}
