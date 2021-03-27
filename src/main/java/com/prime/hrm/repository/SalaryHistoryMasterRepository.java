package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.SalaryHistoryMaster;
import com.prime.hrm.entity.SalaryHistoryMasterPK;

public interface SalaryHistoryMasterRepository extends JpaRepository<SalaryHistoryMaster, SalaryHistoryMasterPK> {

	@Query(value = "SELECT (max(a.saHisMaPK.id)+1) FROM SalaryHistoryMaster a")
	public String getShMaxID();
	
	@Query(value = "select emps,format(basicSalary,2) as basicSalary,addition,deduction,format((addition + other) + basicSalary,2) as grossSalary, \r\n" + 
			"format(((addition + other) + basicSalary) - deduction,2) as netSalary from\r\n" + 
			"(select \r\n" + 
			"count(empId) as emps, sum(basicSalary) as basicSalary,\r\n" + 
			"format(sum(fixAddVal + additionBasicPer + additionGrossPer + addVarVal + addVarBasicPer + additionGrossPerVar),2) as addition,\r\n" + 
			"format(sum(fixDedVal + dedFixrBasicPer + deductionGrossPer + tot_ded_var_value + dedVarBasicPer + deductionGrossPerVar),2) as deduction,\r\n" + 
			"format(sum(othVal + otherBasicPer + othGrossPer + tot_oth_var_value + OthVarBasicPer + otherGrossPerVar),2) as other\r\n" + 
			"from \r\n" + 
			"(select \r\n" + 
			"empId,empName,basicSalary,\r\n" + 
			"-- additions\r\n" + 
			"-- fix\r\n" + 
			"max(addVal) as fixAddVal,(basicSalary * max(addPerBasic))/100 as additionBasicPer,((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(addPerGross)/100 as additionGrossPer,\r\n" + 
			"-- var \r\n" + 
			"max(tot_add_var_value) as addVarVal,(basicSalary * max(tot_basic_var_add_per_full))/100 as addVarBasicPer,((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(tot_gross_var_add_per_full)/100 as additionGrossPerVar,\r\n" + 
			"-- deductions\r\n" + 
			"-- fix\r\n" + 
			"max(dedVal) as fixDedVal,(basicSalary * max(dedPerBasic))/100 as dedFixrBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(dedPerGross)/100 as deductionGrossPer,\r\n" + 
			"-- var\r\n" + 
			"max(tot_ded_var_value) as tot_ded_var_value,\r\n" + 
			"(basicSalary * max(tot_basic_var_ded_per_full))/100 as dedVarBasicPer,((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(tot_gross_var_ded_per_full)/100 as deductionGrossPerVar,\r\n" + 
			"-- other\r\n" + 
			"-- fix\r\n" + 
			"max(othVal) as othVal,(basicSalary * max(othPerBasic))/100 as otherBasicPer,((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(othPerGross)/100 as othGrossPer,\r\n" + 
			"-- var\r\n" + 
			"max(tot_oth_var_value) as tot_oth_var_value,(basicSalary * max(tot_basic_var_oth_per_full))/100 as OthVarBasicPer,((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(tot_gross_var_oth_per_full)/100 as otherGrossPerVar\r\n" + 
			"from\r\n" + 
			"(select\r\n" + 
			"e.Employee_ID as empId, d.Name as empName, e.basicSalary as basicSalary,\r\n" + 
			"-- -------------- gross fix & var\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as grossTotVal,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as grossTotDedVal,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as  grossTotOthVal,\r\n" + 
			"0 as grossTotValVar,0 as grossTotDedValVar,0 as grossTotValOthVar,\r\n" + 
			"-- --------------------------------------------------------------------------------------------------------------------\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as addVal, \r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary',  c.Add_Deduct_Value ,0)) as addPerBasic,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary',  c.Add_Deduct_Value ,0))  as addPerGross, \r\n" + 
			" 0 as tot_add_var_value,0 as tot_basic_var_add_per_full,0 as tot_gross_var_add_per_full,\r\n" + 
			"-- ---------------------------------------------------------------------------------------------------------------------\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as dedVal,			\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary',  c.Add_Deduct_Value ,0)) as dedPerBasic,			\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Add_Deduct_Value ,0)) as dedPerGross, \r\n" + 
			" 0 as tot_ded_var_value,0 as tot_basic_var_ded_per_full, 0 as tot_gross_var_ded_per_full,\r\n" + 
			"-- ---------------------------------------------------------------------------------------------------------------------\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as othVal,			\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary',  c.Add_Deduct_Value ,0)) as othPerBasic,			\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Add_Deduct_Value ,0)) as othPerGross,\r\n" + 
			" 0 as tot_oth_var_value,0 as tot_basic_var_oth_per_full,0 as tot_gross_var_oth_per_full \r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID group by e.Employee_ID\r\n" + 
			"union all \r\n" + 
			"select \r\n" + 
			"a.Emp_ID as empId, b.Name as empName, e.basicSalary as basicSalary, \r\n" + 
			"-- -------------- gross fix & var\r\n" + 
			"0 as grossVal,0 as grossTotDedVal,0 as grossTotOthVal,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', a.Amount ,0)) as  grossTotValVar,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', a.Amount ,0)) as  grossTotDedValVar,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', a.Amount ,0)) as  grossTotValOthVar,\r\n" + 
			"-- --------------------------------------------------------------------------------------------------------------------\r\n" + 
			"0 as addVal,0 as addPerBasic,0 as dedPerGross,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value' ,  a.Amount  ,0))as tot_add_var_value, \r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary',  a.Amount ,0)) as tot_basic_var_add_per_full,		\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,0)) as tot_gross_var_add_per_full, \r\n" + 
			"-- ---------------------------------------------------------------------------------------------------------------------\r\n" + 
			"0 as dedVal, 0 as dedPerBasic,0 as dedPerGross,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', a.Amount ,0)) as tot_ded_var_value,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', a.Amount ,0)) as tot_basic_var_ded_per_full, \r\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,0))	as tot_gross_var_ded_per_full, \r\n" + 
			"-- ----------------------------------------------------------------------------------------------------------------------	\r\n" + 
			"0 as othVal, 0 as othPerBasic,0 as othPerGross, \r\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', a.Amount ,0)) as tot_oth_var_value,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', a.Amount ,0)) as tot_basic_var_oth_per_full, \r\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,0))	as tot_gross_var_oth_per_full \r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID group by a.Emp_ID) a group by empId order by empId)b)c",nativeQuery=true)
	public String[][] saveSalaryHistoryMaster(@Param("Pay_Code_ID")String payCodeiID);
	
	@Query(value="SELECT a.Pay_Period_ID, b.Start_Date, b.End_Date from process_payroll_details a\n" + 
			"INNER JOIN pay_periods b ON a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"GROUP BY a.Pay_Period_ID", nativeQuery=true)
	public String[][] getProcessYearAndMonth();
}
