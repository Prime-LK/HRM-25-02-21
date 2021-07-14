package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.PayAddDeductTypes;

public interface PayAddDeductTypesRepository extends CrudRepository<PayAddDeductTypes, String> {

	@Query(value="SELECT (max(dt.deductTypeCode)+1) FROM PayAddDeductTypes dt")
	public String getMaxID();
	
	@Query(value="SELECT emM FROM PayAddDeductTypes emM WHERE emM.deductTypeCode=:deductTypeCode")
	public PayAddDeductTypes getAddDedTypeRelatedSdetail(@Param("deductTypeCode")String deductTypeCode);
	
	@Query(value="SELECT t FROM PayAddDeductTypes t WHERE t.addDeType = 'variableType'")
	public List<PayAddDeductTypes> getVariableType();
	
	@Query(value="select a from PayAddDeductTypes a")
	public List<PayAddDeductTypes> getAllAllowanceTypes();
	
	@Query(value = "SELECT a.Pay_Add_Deduct_Type_Code, a.Description FROM pay_add_deduct_types a",nativeQuery=true)
	public String[][] getAllowanceTypes();
	
	@Query(value="SELECT t FROM PayAddDeductTypes t WHERE t.company.comID=:compid")
	public List<PayAddDeductTypes> getAllDetailsbyCompid(@Param("compid")String compid);
	
	@Query(value="SELECT t FROM PayAddDeductTypes t WHERE t.company.comID=:compid and t.isActive='active'")
	public List<PayAddDeductTypes> getAllActiveDetailsbyCompid(@Param("compid")String compid);
		
	@Query(value="SELECT t FROM PayAddDeductTypes t WHERE t.company.comID=:compid and t.isActive='active' and t.calculationMethod='N/A'"
			+ " and t.addDeType='variableType' ")
	public List<PayAddDeductTypes> getAllouncetypeMonthly(@Param("compid")String compid);
	
	
	
}
