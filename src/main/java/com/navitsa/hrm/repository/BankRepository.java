package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.Bank;

public interface BankRepository extends CrudRepository<Bank, String> {

	@Query(value = "SELECT (max(ba.branchID)+1) FROM Bank ba")
	public String getID();

//	//getElementByIDs
//	@Query(value = "SELECT b FROM Bank b WHERE b.bankPK.bankid.bankid  =:bankid AND b.bankPK.branchid =:branchid")
//	public Bank setBankDetails(@Param("bankid")String bankid,@Param("branchid")String branchid);
//	
//	//get branch according to bank
//	@Query(value = "SELECT b FROM Bank b WHERE b.bankPK.bankid.bankid =:bank_Code")
//	public List<Bank> getbranch(@Param("bank_Code")String bank_Code);

	// getbranch according to bank in registration
	@Query(value = "SELECT e FROM Bank e WHERE e.bankid.bankid=:bank_Code")
	public List<Bank> findbranch(@Param("bank_Code") String bank_Code);

	@Query(value = "SELECT b FROM Bank b WHERE b.company.comID = :companyId")
	public List<Bank> getAllBankBranchByCompany(@Param("companyId") String companyId);
	
	@Query(value = "SELECT b FROM Bank b WHERE b.bankid.bankid = :bankId AND b.company.comID = :companyId")
	public List<Bank> getAllBankBranchByBankAndCompany(@Param("bankId") String bankId, @Param("companyId") String companyId);
	
	@Query(value = "SELECT b FROM Bank b WHERE b.bankid.bankid = :bankId order by b.branch asc")
	public List<Bank> getAllBankBranchByBank(@Param("bankId") String bankId);
}
