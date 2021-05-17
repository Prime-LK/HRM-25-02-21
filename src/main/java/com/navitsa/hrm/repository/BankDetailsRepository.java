package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.BankDetails;
import com.navitsa.hrm.entity.BankDetailsPK;

public interface BankDetailsRepository extends CrudRepository<BankDetails, BankDetailsPK> {

	
	@Query(value="SELECT bd FROM BankDetails bd WHERE bd.bPK.emp.empID=:empID AND bd.bPK.acc=:acc")
	public BankDetails updateBankDetails(@Param("empID")String empID,@Param("acc")String acc);
	
	@Query(value="SELECT ba FROM  BankDetails ba WHERE ba.bPK.emp.empID=:empID")
	public List<BankDetails> getEmps(@Param("empID")String empID);
}
