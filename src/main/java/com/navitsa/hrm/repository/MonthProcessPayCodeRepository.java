package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.navitsa.hrm.entity.MonthProcessPayCode;
import com.navitsa.hrm.entity.MonthProcessPayCodePK;

public interface MonthProcessPayCodeRepository extends JpaRepository<MonthProcessPayCode, MonthProcessPayCodePK> {

	@Query(value="select\r\n" + 
			"emp_count,emp_Name,basic_sum,\r\n" + 
			"tot_add_var_value_id,tot_add_var_value_desc,tot_add_var_value\r\n" + 
			"from (\r\n" + 
			"select\r\n" + 
			"emp_count,emp_Name,basic_sum,\r\n" + 
			"tot_add_fx_id,tot_add_fx_desc,tot_add_fx_value,\r\n" + 
			"tot_basic_add_per_id,tot_basic_add_per_desc,tot_basic_add_per_full ,\r\n" + 
			"tot_gross_add_per_id,tot_gross_add_per_desc,tot_gross_add_per_full,\r\n" + 
			"tot_add_var_value_id,tot_add_var_value_desc,tot_add_var_value,\r\n" + 
			"tot_basic_var_add_per_id,tot_basic_var_add_per_desc,tot_basic_var_add_per_full,\r\n" + 
			"tot_gross_var_add_per_id,tot_gross_var_add_per_desc,tot_gross_var_add_per_full,\r\n" + 
			"tot_ded_fx_value_id,tot_ded_fx_value_desc,tot_ded_fx_value,\r\n" + 
			"tot_gross_ded_per_id,tot_gross_ded_per_desc,tot_gross_ded_per_full,\r\n" + 
			"tot_basic_ded_per_id,tot_basic_ded_per_desc,tot_basic_ded_per_full,\r\n" + 
			"tot_ded_var_value_id,tot_ded_var_value_desc,tot_ded_var_value,\r\n" + 
			"tot_basic_var_ded_per_id,tot_basic_var_ded_per_desc,tot_basic_var_ded_per_full,\r\n" + 
			"tot_gross_var_ded_per_id,tot_gross_var_ded_per_desc,tot_gross_var_ded_per_full,\r\n" + 
			"tot_oth_fx_value_id,tot_oth_fx_value_desc,tot_oth_fx_value,\r\n" + 
			"tot_gross_oth_per_id,tot_gross_oth_per_desc,tot_gross_oth_per_full,\r\n" + 
			"tot_basic_oth_per_id,tot_basic_oth_per_desc,tot_basic_oth_per_full,\r\n" + 
			"tot_oth_var_value_id,tot_oth_var_value_desc,tot_oth_var_value,\r\n" + 
			"tot_basic_var_oth_per_id,tot_basic_var_oth_per_desc,tot_basic_var_oth_per_full,\r\n" + 
			"tot_gross_var_oth_per_id,tot_gross_var_oth_per_desc,tot_gross_var_oth_per_full,\r\n" + 
			"(final_gross) as final_gross\r\n" + 
			"\r\n" + 
			"from(select e.Employee_ID as emp_count, e.basicSalary as basic_sum,d.Name as emp_Name,\r\n" + 
			"-- ------------------------------------------------------------------------------------------------------------ fx add\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code ,'')) as tot_add_fx_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.description ,'')) as tot_add_fx_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', format(c.Add_Deduct_Value,2) ,'')) as tot_add_fx_value,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_basic_add_per_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_add_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * c.Add_Deduct_Value / 100),2) ,'')) as tot_basic_add_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_gross_add_per_id, -- show already\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_add_per_desc, -- show already\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary',  c.Add_Deduct_Value ,''))  as tot_gross_add_per_full, -- show already\r\n" + 
			"'' as tot_add_var_value_id,'' as tot_add_var_value_desc,'' as tot_add_var_value,\r\n" + 
			"'' as tot_basic_var_add_per_id,'' as tot_basic_var_add_per_desc,'' as tot_basic_var_add_per_full,\r\n" + 
			"'' as tot_gross_var_add_per_id,'' as tot_gross_var_add_per_desc,'' as tot_gross_var_add_per_full,\r\n" + 
			"-- --------------------------------------------------------------------------------------------------------------------- fx ded\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code ,'')) as tot_ded_fx_value_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', c.description ,'')) as tot_ded_fx_value_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', format(c.Add_Deduct_Value,2) ,'')) as tot_ded_fx_value,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_basic_ded_per_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_ded_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * c.Add_Deduct_Value) / 100,2) ,'')) as tot_basic_ded_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_gross_ded_per_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_ded_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Add_Deduct_Value ,'')) as tot_gross_ded_per_full,\r\n" + 
			"'' as tot_ded_var_value_id,'' as tot_ded_var_value_desc,'' as tot_ded_var_value,\r\n" + 
			"'' as tot_basic_var_ded_per_id,'' as tot_basic_var_ded_per_desc,'' as tot_basic_var_ded_per_full,\r\n" + 
			"'' as tot_gross_var_ded_per_id,'' as tot_gross_var_ded_per_desc,'' as tot_gross_var_ded_per_full,\r\n" + 
			"-- ---------------------------------------------------------------------------------------------------------------------- fx other\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code,'')) as tot_oth_fx_value_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.description ,'')) as tot_oth_fx_value_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', format(c.Add_Deduct_Value,2) ,'')) as tot_oth_fx_value,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_basic_oth_per_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_oth_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * c.Add_Deduct_Value) / 100,2) ,'')) as tot_basic_oth_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_gross_oth_per_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_oth_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Add_Deduct_Value ,'')) as tot_gross_oth_per_full,\r\n" + 
			"'' as tot_oth_var_value_id,'' as tot_oth_var_value_desc,'' as tot_oth_var_value,\r\n" + 
			"'' as tot_basic_var_oth_per_id,'' as tot_basic_var_oth_per_desc,'' as tot_basic_var_oth_per_full,\r\n" + 
			"'' as tot_gross_var_oth_per_id,'' as tot_gross_var_oth_per_desc,'' as tot_gross_var_oth_per_full,\r\n" + 
			"'' as final_gross\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"right join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"left join employee_master d on e.Employee_ID = d.Employee_ID\r\n" + 
			"union all\r\n" + 
			"select a.Emp_ID as emp_count,e.basicSalary as basic_sum, b.Name as emp_Name,\r\n" + 
			"-- ------------------------------------------------------------------------------------------------------------------- var add\r\n" + 
			"'' as tot_add_fx_id,'' as tot_add_fx_desc,'' as tot_add_fx_value,\r\n" + 
			"'' as tot_basic_add_per_id,'' as tot_basic_add_per_desc,'' as tot_basic_add_per_full,\r\n" + 
			"'' as tot_gross_add_per_id,'' as tot_gross_add_per_desc,'' as tot_gross_add_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code ,null)) as tot_add_var_value_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.description ,null)) as tot_add_var_value_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value' ,  a.Amount  ,null))as tot_add_var_value,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_basic_var_add_per_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_var_add_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * a.Amount) / 100,2) ,'')) as tot_basic_var_add_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_gross_var_add_per_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_var_add_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,'')) as tot_gross_var_add_per_full,\r\n" + 
			"-- --------------------------------------------------------------------------------------------------------------------- var ded\r\n" + 
			"'' as tot_ded_fx_value_id,'' as tot_ded_fx_value_desc,'' as tot_ded_fx_value,\r\n" + 
			"'' as tot_gross_ded_per_id,'' as tot_gross_ded_per_desc,'' as tot_gross_ded_per_full,\r\n" + 
			"'' as tot_basic_ded_per_id,'' as tot_basic_ded_per_desc,'' as tot_basic_ded_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code ,'')) as tot_ded_var_value_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', c.description ,'')) as tot_ded_var_value_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', a.Amount ,'')) as tot_ded_var_value,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_basic_var_ded_per_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_var_ded_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * a.Amount) / 100,2) ,'')) as tot_basic_var_ded_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_gross_var_ded_per_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_var_ded_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,'')) as tot_gross_var_ded_per_full,\r\n" + 
			"-- ------------------------------------------------------------------------------------------------------------------- var other\r\n" + 
			"'' as tot_oth_fx_value_id,'' as tot_oth_fx_value_desc,'' as tot_oth_fx_value,\r\n" + 
			"'' as tot_gross_oth_per_id,'' as tot_gross_oth_per_desc,'' as tot_gross_oth_per_full,\r\n" + 
			"'' as tot_basic_oth_per_id,'' as tot_basic_oth_per_desc,'' as tot_basic_oth_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code ,'')) as tot_oth_var_value_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.description ,'')) as tot_oth_var_value_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', a.Amount ,'')) as tot_oth_var_value,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_basic_var_oth_per_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_var_oth_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * a.Amount) / 100,2) ,'')) as tot_basic_var_oth_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Pay_Add_Deduct_Type_Code ,'')) as tot_gross_var_oth_per_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_var_oth_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,'')) as tot_gross_var_oth_per_full,\r\n" + 
			"'' as final_gross\r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID\r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"union all\r\n" + 
			"select\r\n" + 
			"emp_count, basicSalary,'' as emp_Name,\r\n" + 
			"-- ------------------------------------------------------------------------------------------------------------ fx add\r\n" + 
			"'' as tot_add_fx_id,''as tot_add_fx_desc,'' as tot_add_fx_value,'' as tot_basic_add_per_id,'' as tot_basic_add_per_desc,\r\n" + 
			"'' as tot_basic_add_per_full, '' as tot_gross_add_per_id, -- show already\r\n" + 
			"'' as tot_gross_add_per_desc, -- show already\r\n" + 
			"''  as tot_gross_add_per_full, -- show already\r\n" + 
			"'' as tot_add_var_value_id,'' as tot_add_var_value_desc,'' as tot_add_var_value,\r\n" + 
			"'' as tot_basic_var_add_per_id,'' as tot_basic_var_add_per_desc,'' as tot_basic_var_add_per_full,\r\n" + 
			"'' as tot_gross_var_add_per_id,'' as tot_gross_var_add_per_desc,'' as tot_gross_var_add_per_full,\r\n" + 
			"-- --------------------------------------------------------------------------------------------------------------------- fx ded\r\n" + 
			"'' as tot_ded_fx_value_id,'' as tot_ded_fx_value_desc,'' as tot_ded_fx_value,	'' as tot_basic_ded_per_id,\r\n" + 
			"'' as tot_basic_ded_per_desc,'' as tot_basic_ded_per_full,		'' as tot_gross_ded_per_id,	'' as tot_gross_ded_per_desc,\r\n" + 
			"'' as tot_gross_ded_per_full, '' as tot_ded_var_value_id,'' as tot_ded_var_value_desc,'' as tot_ded_var_value,\r\n" + 
			"'' as tot_basic_var_ded_per_id,'' as tot_basic_var_ded_per_desc,'' as tot_basic_var_ded_per_full,\r\n" + 
			"'' as tot_gross_var_ded_per_id,'' as tot_gross_var_ded_per_desc,'' as tot_gross_var_ded_per_full,\r\n" + 
			"-- ---------------------------------------------------------------------------------------------------------------------- fx other\r\n" + 
			"'' as tot_oth_fx_value_id,'' as tot_oth_fx_value_desc,'' as tot_oth_fx_value,'' as tot_basic_oth_per_id,'' as tot_basic_oth_per_desc,\r\n" + 
			"'' as tot_basic_oth_per_full,'' as tot_gross_oth_per_id,'' as tot_gross_oth_per_desc, '' as tot_gross_oth_per_full,\r\n" + 
			"'' as tot_oth_var_value_id,'' as tot_oth_var_value_desc,'' as tot_oth_var_value,\r\n" + 
			"'' as tot_basic_var_oth_per_id,'' as tot_basic_var_oth_per_desc,'' as tot_basic_var_oth_per_full,\r\n" + 
			"'' as tot_gross_var_oth_per_id,'' as tot_gross_var_oth_per_desc,'' as tot_gross_var_oth_per_full,\r\n" + 
			"((sum(gross1) + sum(gross2)) + basicSalary) as final_gross\r\n" + 
			"from\r\n" + 
			"(select\r\n" + 
			"d.Employee_ID as emp_count, d.basicSalary as basicSalary,\r\n" + 
			"(if(b.Add_Deduct_Status = 'addition' and b.Is_On_Basic_Salary = 'grossSalary' and b.Is_Percentage = 'value',  a.Amount , '')) as gross1,\r\n" + 
			"'' as gross2\r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types b on a.Pay_Add_Deduct_Type_Code = b.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_master c on a.Emp_ID = c.Employee_ID\r\n" + 
			"inner join employee_details d on c.Employee_ID = d.Employee_ID\r\n" + 
			"union all\r\n" + 
			"select\r\n" + 
			"d.Employee_ID as emp_count, d.basicSalary as basicSalary,\r\n" + 
			"'' as gross1,\r\n" + 
			"(if(b.Add_Deduct_Status = 'addition' and b.Is_On_Basic_Salary = 'grossSalary'  and b.Is_Percentage = 'value',  b.Add_Deduct_Value , '')) as gross2\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types b on a.Pay_Add_Deduct_Type_Code = b.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_master c on a.Employee_ID = c.Employee_ID\r\n" + 
			"inner join employee_details d on c.Employee_ID = d.Employee_ID )a\r\n" + 
			"group by emp_count order by emp_count) b order by emp_count) c order by emp_count",nativeQuery=true)
	public String[][] saveEachEmpWithAllowance();
	
	@Query(value="select tot_add_var_value_id from\r\n" + 
			"(select a.Emp_ID as emp_count,e.basicSalary as basic_sum, b.Name as emp_Name, \r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code ,null)) as tot_add_var_value_id,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.description ,'')) as tot_add_var_value_desc, \r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value' ,  a.Amount  ,''))as tot_add_var_value\r\n" + 
			"from emp_month_salary_details a \r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID) a\r\n" + 
			"where tot_add_var_value_id is not null ",nativeQuery=true)
	public List<String> adiitionsVariable();
	
}
