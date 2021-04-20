package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.ProcessPayrollDetails;
import com.prime.hrm.entity.ProcessPayrollDetailsPK;

public interface ProcessPayrollDetailsRepository extends CrudRepository<ProcessPayrollDetails, ProcessPayrollDetailsPK> {

	@Query(value = "select b.Pay_Add_Deduct_Type_Code, b.Description from process_payroll_details a \n" + 
			"inner join pay_add_deduct_types b on a.Pay_Add_Deduct_Type_Code = b.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Pay_Add_Deduct_Type_Code =:Pay_Add_Deduct_Type_Code group by b.Description", nativeQuery=true)
	public String[][] getAllowanceName(@Param("Pay_Add_Deduct_Type_Code")String allID);
	
	@Query(value="select a.Pay_Add_Deduct_Type_Code, b.Description from process_payroll_details a \n" + 
			"inner join pay_add_deduct_types b on a.Pay_Add_Deduct_Type_Code = b.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Pay_Add_Deduct_Type_Code in (:Allowance)\n" + 
			"group by a.Pay_Add_Deduct_Type_Code",nativeQuery=true)
	public String[][] getSelectedAllowanceDetails(@Param("Allowance")String allo);
	
	@Query(value = "CALL dynamic_view3(:sdate);",nativeQuery=true)
	public String[][] getSAtableData(@Param("sdate")String allID);
}
