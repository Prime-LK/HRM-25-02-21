package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.ProcessPayrollMaster;

public interface ProcessPayrollMasterRepository extends JpaRepository<ProcessPayrollMaster, String> {

	@Query(value = "SELECT (max(m.proPayrollMaID)+1) FROM ProcessPayrollMaster m")
	public String maxMID();
	
	@Query(value="select \r\n" + 
			"format(sum(fx_add_vals) + basic_sum,2) as monthly_basic\r\n" + 
			"from(select basic_sum,sum_fx_add_val as fx_add_vals,sum_var_add_val as var_add_vals\r\n" + 
			"from(select basic_sum,sum(tot_add_fx_value) as  sum_fx_add_val,sum(tot_add_var_value) as sum_var_add_val\r\n" + 
			"from(select e.basicSalary as basic_sum,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as tot_add_fx_value, \r\n" + 
			"0 as tot_add_var_value\r\n" + 
			"from employee_salary_details a \r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"right join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"left join employee_master d on e.Employee_ID = d.Employee_ID group by e.Employee_ID\r\n" + 
			"union\r\n" + 
			"select e.basicSalary as basic_sum,0 as tot_add_fx_value,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value' ,  a.Amount  ,0))as tot_add_var_value\r\n" + 
			"from emp_month_salary_details a \r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID group by a.Emp_ID)a)b) c;",nativeQuery=true)
	public String loadMonthlyBasic(@Param("Pay_Code_ID")String payCodeID);	
	
	//employee report 01 data 
	@Query(value="select\r\n" + 
			"emp,firstName,lastName,basicSalary, \r\n" + 
			"Budgatery,Attendence,Performance,Night,Target,Trainee,Other,Rigger,Sales,Transport,Site,Nopay,\r\n" + 
			"Festival,Isurance,Mobile,Salary_Advance,max(gross_salary) as gross,EPF8,Walfare,Laptop,Bike,PM,max(net_salary) net,EPF12,EPF3\r\n" + 
			"from(select\r\n" + 
			"a.Employee_ID as emp, c.Name as firstName,c.lastname as lastName, d.basicSalary as basicSalary,\r\n" + 
			"max(case when b.Description like 'Bud%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Budgatery,\r\n" + 
			"max(case when b.Description like 'Atten%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Attendence,  \r\n" + 
			"max(case when b.Description like 'Per%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Performance,\r\n" + 
			"max(case when b.Description like 'Night%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Night,\r\n" + 
			"max(case when b.Description like 'Targ%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Target,\r\n" + 
			"max(case when b.Description like 'Train%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Trainee, \r\n" + 
			"max(case when b.Description like 'Other%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Other,  \r\n" + 
			"max(case when b.Description like 'Rigger%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Rigger,\r\n" + 
			"max(case when b.Description like 'Sales%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Sales,\r\n" + 
			"max(case when b.Description like 'Transpo%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Transport,\r\n" + 
			"max(case when b.Description like 'Site%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Site, \r\n" + 
			"max(case when b.Description like 'Nopay%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Nopay, \r\n" + 
			"max(case when b.Description like 'Festiv%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Festival,\r\n" + 
			"max(case when b.Description like 'Insura%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Isurance, \r\n" + 
			"max(case when b.Description like 'Mobile%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Mobile, \r\n" + 
			"max(case when b.Description like 'Salary Ad%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Salary_Advance,  \r\n" + 
			"max(case when b.Description like 'EPF 8%' then if(b.Add_Deduct_Value != '0' or b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as EPF8,\r\n" + 
			"max(case when b.Description like 'Walfare%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Walfare, \r\n" + 
			"max(case when b.Description like 'Laptop%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Laptop, \r\n" + 
			"max(case when b.Description like 'Bike%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as Bike,  \r\n" + 
			"max(case when b.Description like 'PM%' then if(b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as PM,\r\n" + 
			"max(case when b.Description like 'EPF 12%' then if(b.Add_Deduct_Value != '0' or b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as EPF12,\r\n" + 
			"max(case when b.Description like 'EPF 3%' then if(b.Add_Deduct_Value != '0' or b.Add_Deduct_Value = '0', a.Amount, b.Add_Deduct_Value) else '-' end) as EPF3,\r\n" + 
			"'' as gross_salary,\r\n" + 
			"'' as net_salary\r\n" + 
			"from process_payroll_details a \r\n" + 
			"inner join pay_add_deduct_types b on a.Pay_Add_Deduct_Type_Code = b.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_master c on a.Employee_ID = c.Employee_ID\r\n" + 
			"inner join employee_details d on c.Employee_ID = d.Employee_ID \r\n" + 
			"group by emp\r\n" + 
			"union \r\n" + 
			"select \r\n" + 
			"emp,'' as firstName,'' as lastName, basicSalary,\r\n" + 
			"'' as Budgatery,'' as Attendence,  '' as Performance,'' as Night,'' as Target,'' as Trainee, '' as Other,  \r\n" + 
			"'' as Rigger,'' as Sales,'' as Transport,'' as Site, '' as Nopay, '' as Festival,'' as Isurance, '' as Mobile, \r\n" + 
			"'' as Salary_Advance,  '' as 'EPF8%','' as Walfare, '' as Laptop, '' as Bike,  '' as PM,'' as 'EPF12%','' as 'EPF3%',\r\n" + 
			"(((add_fixOrVar_basic_amount) + (add_fixOrVar_gross_amount) + (oth_fixOrVar_basic_amount)) + basicSalary) as gross_salary,\r\n" + 
			"((((add_fixOrVar_basic_amount) + (add_fixOrVar_gross_amount) + (oth_fixOrVar_basic_amount)) + basicSalary) - all_ded_amount) as net_salary\r\n" + 
			"from\r\n" + 
			"(SELECT \r\n" + 
			"a.Employee_ID as emp ,\r\n" + 
			"b.basicSalary as basicSalary,\r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as add_fixOrVar_basic_amount,\r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end) \r\n" + 
			"as add_fixOrVar_gross_amount,\r\n" + 
			"sum(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as oth_fixOrVar_basic_amount,\r\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') then\r\n" + 
			"if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value) else '-' end) as all_ded_amount\r\n" + 
			"FROM process_payroll_details a\r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details b on a.Employee_ID = b.Employee_ID\r\n" + 
			"group by a.Employee_ID)a group by emp)a group by emp order by emp",nativeQuery=true)
	public String[][] getReportData();
	
	@Query(value = "CALL allEmpsAndAllowanceBodyData()",nativeQuery=true)
	public String[][] getAllEmpWithAllowancesReportBodyData();
	
	@Query(value = "-- total basic salary\n" + 
			"SELECT empIdTemp, max(TotalBasicSalary), max(TotalEPFSalary), \n" + 
			"max(GrossSalary), max(TotalDeductions), max(NetSalary), '' as papaya\n" + 
			"from (\n" + 
			"select * from(select empIdTemp, format(max(total_bSalary + basicSalary),2) as TotalBasicSalary, '' as TotalEPFSalary,\n" + 
			"'' as GrossSalary, '' as TotalDeductions, '' as NetSalary, '' as papaya\n" + 
			"from(\n" + 
			"select a.Employee_ID as empIdTemp, '09' as columnCnt, 'TOTAL BASIC SALARY' as totBaSaDesc,\n" + 
			"c.basicSalary as basicSalary, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_bSalary, '' as papaya\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join designation_master h on c.Designation_ID = h.Designation_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
			"where a.Company_ID =:Company_ID group by empIdTemp\n" + 
			"union all \n" + 
			"select a.Employee_ID as empIdTemp, '09' as columnCnt, 'TOTAL BASIC SALARY' as totBaSaDesc,\n" + 
			"c.basicSalary as basicSalary, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_bSalary, '' as papaya\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID group by empIdTemp)a group by empIdTemp)b \n" + 
			"UNION ALL\n" + 
			"-- Total EPF Salary\n" + 
			"SELECT  empIdTemp, '' as TotalBasicSalary,\n" + 
			"format(max(total_epf_salary + basicSalary) - max(add_fixOrVar_basic_amount),2) as TotalEPFSalary,\n" + 
			"'' as GrossSalary, '' as TotalDeductions, '' as NetSalary, '' as papaya\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '12' as columnCnt, 'TOTAL EPF SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_epf_salary, c.basicSalary as basicSalary, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else null end) \n" + 
			"as add_fixOrVar_basic_amount\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID group by empIdTemp \n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '12' as columnCnt, 'TOTAL EPF SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_epf_salary, c.basicSalary as basicSalary, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as add_fixOrVar_basic_amount\n" + 
			"from process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID group by empIdTemp) a group by empIdTemp\n" + 
			"UNION ALL\n" + 
			"-- gross salary\n" + 
			"SELECT empIdTemp, '' as TotalBasicSalary, '' as TotalEPFSalary,\n" + 
			"format(max(total_epf_salary + gross_salary + basicSalary) - max(add_fixOrVar_basic_amount),2) as GrossSalary,\n" + 
			"'' as TotalDeductions, '' as NetSalary, '' as papaya\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '16' as columnCnt, 'GROSS SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '' end) \n" + 
			"as total_epf_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end) \n" + 
			"as gross_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else null end) \n" + 
			"as add_fixOrVar_basic_amount, c.basicSalary as basicSalary, '' as blank2 \n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '16' as columnCnt, 'GROSS SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '' end) \n" + 
			"as total_epf_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end) \n" + 
			"as gross_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '' end) \n" + 
			"as add_fixOrVar_basic_amount, c.basicSalary as basicSalary, '' as blank2\n" + 
			"from process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID group by empIdTemp) a group by empIdTemp\n" + 
			"UNION ALL\n" + 
			"SELECT * from(\n" + 
			"select empIdTemp, '' TotalBasicSalary, '' TotalEPFSalary, '' as GrossSalary,\n" + 
			"format(max(total_deductions),2) as TotalDeductions, '' as NetSalary, '' as papaya\n" + 
			"from(\n" + 
			"select a.Employee_ID as empIdTemp, '20' as columnCnt, 'TOTAL DEDUCTIONS' as totBaSaDesc,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then  a.Amount else '' end) \n" + 
			"as total_deductions \n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
			"where a.Company_ID =:Company_ID group by empIdTemp\n" + 
			"union all \n" + 
			"select a.Employee_ID as empIdTemp, '20' as columnCnt, 'TOTAL DEDUCTIONS' as totBaSaDesc,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end) as total_deductions\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID group by empIdTemp)a group by empIdTemp)b \n" + 
			"UNION ALL\n" + 
			"SELECT empIdTemp, '' as TotalBasicSalary, '' as TotalEPFSalary, '' as GrossSalary, '' as TotalDeductions,\n" + 
			"format((((add_fixOrVar_basic_amount) + (add_fixOrVar_gross_amount) + basicSalary) - all_ded_amount),2)  \n" + 
			"as NetSalary, '' as papaya\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '22' as columnCnt,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '-' end)  \n" + 
			"as add_fixOrVar_basic_amount, \n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '-' end)  \n" + 
			"as add_fixOrVar_gross_amount,  \n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') then \n" + 
			"a.Amount else '-' end) as all_ded_amount,\n" + 
			"b.basicSalary as basicSalary, '' as blank2\n" + 
			"from process_payroll_details a \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details b on a.Employee_ID = b.Employee_ID \n" + 
			"inner join designation_master h on b.Designation_ID = h.Designation_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID group by empIdTemp)a group by empIdTemp)b group by empIdTemp",nativeQuery=true)
	public String[][] getAllEmpWithAllowancesReportBodySumData(@Param("Company_ID")String comID);
	
	@Query(value = "CALL allEmpsAndAllowanceHeaderData()",nativeQuery=true)
	public String[][] getAllEmpWithAllowancesReportHeadData();
	
	@Query(value = "select max(label), max(tbsLabel), max(tepfsLabel), max(gsLabel), max(totDedLabel), max(nsLabel) from \n" + 
			"(select 'Label' as label, tbsLabel, '' as  tepfsLabel, '' as gsLabel, '' as totDedLabel, '' as nsLabel from(\n" + 
			"select 'Total basic salary' as tbsLabel\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
			"where a.Company_ID =:Company_ID group by tbsLabel\n" + 
			"union all \n" + 
			"select 'Total basic salary' as tbsLabel\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID group by tbsLabel)a group by tbsLabel \n" + 
			"UNION ALL\n" + 
			"-- Total EPF Salary\n" + 
			"SELECT 'Label' as label, '' as tbsLabel, tepfsLabel, '' as gsLabel, '' as totDedLabel, '' as nsLabel from (\n" + 
			"select 'Total EPF Salary' as tepfsLabel\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID group by tepfsLabel \n" + 
			"union all\n" + 
			"select 'Total EPF Salary' as tepfsLabel\n" + 
			"from process_payroll_details a \n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID group by tepfsLabel) a group by tepfsLabel\n" + 
			"UNION ALL\n" + 
			"-- gross salary\n" + 
			"SELECT 'Label' as label, '' as tbsLabel, '' as tepfsLabel, gsLabel, '' as totDedLabel, '' as nsLabel from (\n" + 
			"select 'Gross Salary' as gsLabel\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID group by gsLabel\n" + 
			"union all\n" + 
			"select 'Gross Salary' as gsLabel\n" + 
			"from process_payroll_details a\n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID group by gsLabel) a group by gsLabel\n" + 
			"UNION ALL\n" + 
			"-- total deduction\n" + 
			"select 'Label' as label, '' as tbsLabel, '' as tepfsLabel, '' as gsLabel, totDedLabel, '' as nsLabel\n" + 
			"from(\n" + 
			"select 'Total Deductions' as totDedLabel\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
			"where a.Company_ID =:Company_ID group by totDedLabel\n" + 
			"union all \n" + 
			"select 'Total Deductions' as totDedLabel\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID group by totDedLabel)a group by totDedLabel\n" + 
			"UNION ALL\n" + 
			"-- net salary label\n" + 
			"SELECT 'Label' as label, '' as tbsLabel, '' as tepfsLabel, '' as gsLabel, '' as totDedLabel, nsLabel from (\n" + 
			"select 'Net Salary' as nsLabel\n" + 
			"from process_payroll_details a \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID group by nsLabel)a group by nsLabel)b group by label",nativeQuery=true)
	public String[][] getAllEmpWithAllowancesReportHeaderSumData(@Param("Company_ID")String comID);
	
//	// end of employee report 01 data	
	
	//new paySlipData
//	@Query(value="-- basic salary\n" + 
//			"SELECT * from (select \n" + 
//			"k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \n" + 
//			"h.Designation as designaion,'BASIC SALARY' as add_fixOrVar_basic_desc, \n" + 
//			"format(c.basicSalary,2) as add_fixOrVar_basic_amount,'' as monthVal, '' as yearVal\n" + 
//			"from process_payroll_details a \n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"union all\n" + 
//			"select k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \n" + 
//			"h.Designation as designaion, 'BASIC SALARY' as add_fixOrVar_basic_desc, \n" + 
//			"format(c.basicSalary,2) as add_fixOrVar_basic_amount,'' as monthVal, '' as yearVal  \n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc)a \n" + 
//			"UNION ALL\n" + 
//			"-- addtion depends on basic salary\n" + 
//			"SELECT * from (select \n" + 
//			"k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department,h.Designation as designaion, \n" + 
//			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
//			"as add_fixOrVar_basic_desc, \n" + 
//			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
//			"as add_fixOrVar_basic_amount,'' as monthVal, '' as yearVal\n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID     \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"union all\n" + 
//			"select k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, h.Designation as designaion,\n" + 
//			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
//			"as add_fixOrVar_basic_desc, \n" + 
//			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
//			"as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal\n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code   \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc)a \n" + 
//			"UNION ALL \n" + 
//			"-- total basic salary\n" + 
//			"SELECT * from(select company_name, processDate, fName, lname, empId, epfNo, basicSalary, department, designaion,\n" + 
//			"'TOTAL BASIC SALARY' as totBaSaDesc, format(max(total_bSalary + basicSalary),2), '' as monthVal, '' as yearVal\n" + 
//			"from(select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo, c.basicSalary as basicSalary, d.Department as department,\n" + 
//			"h.Designation as designaion, 'TOTAL BASIC SALARY' as totBaSaDesc,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as total_bSalary,\n" + 
//			"'' as monthVal, '' as yearVal \n" + 
//			"FROM process_payroll_details a\n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID\n" + 
//			"union all \n" + 
//			"select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \n" + 
//			"h.Designation as designaion, 'TOTAL BASIC SALARY' as totBaSaDesc,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as total_bSalary,'' as monthVal, '' as yearVal \n" + 
//			"FROM process_payroll_details a\n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID)a )b\n" + 
//			"UNION ALL\n" + 
//			"-- deductions depends on basic salary\n" + 
//			"SELECT company_name, processDate, fName, lname,  \n" + 
//			"empId, epfNo, basicSalary,  department, designaion, add_fixOrVar_basic_desc, add_fixOrVar_basic_amount,\n" + 
//			"monthVal, yearVal\n" + 
//			"from (select k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department,h.Designation as designaion, \n" + 
//			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType', f.Description, f.Description) else '' end) \n" + 
//			"as add_fixOrVar_basic_desc, \n" + 
//			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType',\n" + 
//			"concat(\"(\",format(a.Amount,2),\")\"), concat(\"(\",format(f.Add_Deduct_Value,2),\")\")) else '' end) \n" + 
//			"as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal  \n" + 
//			"from process_payroll_details a \n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID    \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"union all\n" + 
//			"select k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, h.Designation as designaion,\n" + 
//			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
//			"as add_fixOrVar_basic_desc, \n" + 
//			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', \n" + 
//			"concat(\"(\",format(a.Amount,2),\")\"), concat(\"(\",format(f.Add_Deduct_Value,2),\")\")) else '' end) \n" + 
//			"as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal  \n" + 
//			"from process_payroll_details a \n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc)a \n" + 
//			"UNION ALL\n" + 
//			"-- line\n" + 
//			"SELECT '' as company_name,'' as processDate,'' as fName, '' as lname,  \n" + 
//			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \n" + 
//			"'' as designation, \"\" as add_fixOrVar_basic_desc, \n" + 
//			"\"......................\" as add_fixOrVar_basic_amount,'' as monthVal, '' as yearVal\n" + 
//			"FROM process_payroll_details a\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
//			"where a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"UNION ALL\n" + 
//			"-- Total EPF Salary\n" + 
//			"SELECT company_name, processDate, fName, lname, empId, epfNo, basicSalary, department, designaion, totEpfSaDesc,\n" + 
//			"format(max(total_epf_salary + basicSalary) - max(add_fixOrVar_basic_amount),2) as EPF_Salary, '' as monthVal, '' as yearVal\n" + 
//			"from (select i.Company_Name as company_name, b.Process_Date as processDate, e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \n" + 
//			"h.Designation as designaion, 'TOTAL EPF SALARY' as totEpfSaDesc,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as total_epf_salary,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else null end) \n" + 
//			"as add_fixOrVar_basic_amount,'' as monthVal, '' as yearVal \n" + 
//			"FROM process_payroll_details a\n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID \n" + 
//			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID\n" + 
//			"union all\n" + 
//			"select i.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \n" + 
//			"h.Designation as designaion, 'TOTAL EPF SALARY' as totEpfSaDesc,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as total_epf_salary,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal \n" + 
//			"from process_payroll_details a\n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID) a group by totEpfSaDesc\n" + 
//			"UNION ALL\n" + 
//			"-- Earnings label\n" + 
//			"SELECT '' as company_name,'' as processDate,'' as fName, '' as lname,  \n" + 
//			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \n" + 
//			"'' as designation, \"EARNINGS\" as add_fixOrVar_basic_desc, \n" + 
//			"'' as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal \n" + 
//			"from process_payroll_details a\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
//			"where a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"UNION ALL\n" + 
//			"-- All Earnings\n" + 
//			"SELECT * from(select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname, \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, h.Designation as designaion, \n" + 
//			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
//			"as add_fixOrVar_gross_desc,\n" + 
//			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end)  \n" + 
//			"as add_fixOrVar_gross_amount,'' as monthVal, '' as yearVal \n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_gross_desc\n" + 
//			"union all \n" + 
//			"select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname, \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, h.Designation as designaion,\n" + 
//			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
//			"as add_fixOrVar_gross_desc,\n" + 
//			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
//			"as add_fixOrVar_gross_amount, '' as monthVal, '' as yearVal\n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_gross_desc)a\n" + 
//			"UNION ALL\n" + 
//			"-- line\n" + 
//			"SELECT '' as company_name,'' as processDate,'' as fName, '' as lname,  \n" + 
//			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \n" + 
//			"'' as designation, \"\" as add_fixOrVar_basic_desc, \n" + 
//			"\"......................\" as add_fixOrVar_basic_amount,'' as monthVal, '' as yearVal\n" + 
//			"FROM process_payroll_details a\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
//			"where a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"UNION ALL\n" + 
//			"-- Gross salary\n" + 
//			"SELECT company_name, processDate, fName, lname, empId, epfNo, basicSalary, department, designaion, totEpfSaDesc,\n" + 
//			"format(max(total_epf_salary + gross_salary + basicSalary) - max(add_fixOrVar_basic_amount),2) as EPF_Salary, '' as monthVal, '' as yearVal\n" + 
//			"from (select i.Company_Name as company_name, b.Process_Date as processDate, e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \n" + 
//			"h.Designation as designaion, 'GROSS SALARY' as totEpfSaDesc,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as total_epf_salary,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as gross_salary,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else null end) \n" + 
//			"as add_fixOrVar_basic_amount,'' as monthVal, '' as yearVal \n" + 
//			"FROM process_payroll_details a\n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID \n" + 
//			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID\n" + 
//			"union all\n" + 
//			"select i.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \n" + 
//			"h.Designation as designaion, 'GROSS SALARY' as totEpfSaDesc,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as total_epf_salary,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as gross_salary,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal \n" + 
//			"from process_payroll_details a\n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID) a group by totEpfSaDesc\n" + 
//			"UNION ALL\n" + 
//			"-- Deduiction label\n" + 
//			"SELECT '' as company_name,'' as processDate,'' as fName, '' as lname,  \n" + 
//			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \n" + 
//			"'' as designation, \"DEDUCTIONS\" as add_fixOrVar_basic_desc, \n" + 
//			"'' as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal \n" + 
//			"from process_payroll_details a\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
//			"where a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"UNION ALL\n" + 
//			"-- Deductions Depends on Gross Salary\n" + 
//			"SELECT company_name, processDate, fName, lname,  \n" + 
//			"empId, epfNo, basicSalary,  department, designaion, add_fixOrVar_basic_desc, add_fixOrVar_basic_amount,monthVal, yearVal\n" + 
//			"from (select \n" + 
//			"k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department,h.Designation as designaion, \n" + 
//			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
//			"as add_fixOrVar_basic_desc, \n" + 
//			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType', format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
//			"as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal  \n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID    \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"union all\n" + 
//			"select k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, h.Designation as designaion,\n" + 
//			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
//			"as add_fixOrVar_basic_desc, \n" + 
//			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if((f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType'), format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
//			"as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal  \n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code  \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc)a \n" + 
//			"UNION ALL\n" + 
//			"-- line\n" + 
//			"SELECT '' as company_name,'' as processDate,'' as fName, '' as lname,  \n" + 
//			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \n" + 
//			"'' as designation, \"\" as add_fixOrVar_basic_desc, \n" + 
//			"\"......................\" as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal\n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
//			"where a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"UNION ALL\n" + 
//			"-- Total Deduction\n" + 
//			"SELECT * from(select company_name, processDate, fName, lname, empId, epfNo, basicSalary, department, designaion,\n" + 
//			"'TOTAL DEDUCTIONS' as totBaSaDesc, format(max(total_deductions),2), '' as monthVal, '' as yearVal\n" + 
//			"from(select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo, c.basicSalary as basicSalary, d.Department as department,\n" + 
//			"h.Designation as designaion, 'TOTAL DEDUCTIONS' as totBaSaDesc,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as total_deductions, '' as monthVal, '' as yearVal \n" + 
//			"FROM process_payroll_details a\n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID\n" + 
//			"union all \n" + 
//			"select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \n" + 
//			"h.Designation as designaion, 'TOTAL DEDUCTIONS' as totBaSaDesc,\n" + 
//			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
//			"as total_deductions,'' as monthVal, '' as yearVal \n" + 
//			"FROM process_payroll_details a\n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID)a )b\n" + 
//			"UNION ALL\n" + 
//			"-- line\n" + 
//			"SELECT \n" + 
//			"'' as company_name,'' as processDate,'' as fName, '' as lname,  \n" + 
//			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \n" + 
//			"'' as designation, '' as add_fixOrVar_basic_desc, \n" + 
//			"'......................' as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal \n" + 
//			"from process_payroll_details a\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
//			"where a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"UNION ALL\n" + 
//			"-- Net Pay\n" + 
//			"SELECT  '' as company_name,'' as processDate,'' as fName, '' as lname,  \n" + 
//			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, designaion, 'NET PAY' as netSalaryDesc, \n" + 
//			"format(((((add_fixOrVar_basic_amount) + (add_fixOrVar_gross_amount) + (oth_fixOrVar_basic_amount)) + basicSalary) - all_ded_amount),2)  \n" + 
//			"as net_salary, '' as monthVal, '' as yearVal\n" + 
//			"from (select \n" + 
//			"a.Employee_ID as emp, b.basicSalary as basicSalary, h.Designation as designaion, \n" + 
//			"'' as totEpfSaDesc,'' as total_epf_salary,'' as grossSaDesc,'' as gross_salary,'' as taxSaDesc,'' as taxableSalary, \n" + 
//			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \n" + 
//			"as add_fixOrVar_basic_amount, \n" + 
//			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \n" + 
//			"as add_fixOrVar_gross_amount, \n" + 
//			"sum(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \n" + 
//			"as oth_fixOrVar_basic_amount, \n" + 
//			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') then \n" + 
//			"if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value) else '-' end) as all_ded_amount,\n" + 
//			"'' as monthVal, '' as yearVal  \n" + 
//			"from process_payroll_details a \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
//			"inner join employee_details b on a.Employee_ID = b.Employee_ID \n" + 
//			"inner join designation_master h on b.Designation_ID = h.Designation_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID)a \n" + 
//			"UNION ALL\n" + 
//			"-- Double line\n" + 
//			"SELECT '' as company_name,'' as processDate,'' as fName, '' as lname,  \n" + 
//			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \n" + 
//			"'' as designation, \"\" as add_fixOrVar_basic_desc, \n" + 
//			"\"::::::::::::::::::::::\" as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal\n" + 
//			"from process_payroll_details a\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
//			"where a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"UNION ALL\n" + 
//			"-- Label Company Contribution\n" + 
//			"SELECT '' as company_name,'' as processDate,'' as fName, '' as lname,  \n" + 
//			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \n" + 
//			"'' as designation, 'COMPANY CONTRIBUTION' as add_fixOrVar_basic_desc, \n" + 
//			"'' as add_fixOrVar_basic_amount, '' as monthVal, '' as yearVal \n" + 
//			"from process_payroll_details a \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
//			"where a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"UNION ALL\n" + 
//			"SELECT * from (select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, h.Designation as designaion, \n" + 
//			"if(f.Add_Deduct_Status = 'other' , f.Description, '') as all_oth_desc,  \n" + 
//			"(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"then if(if(f.Add_Deduct_Type = 'fixedType', format(a.Amount,2), format(f.Add_Deduct_Value,2)), format(a.Amount,2), format(f.Add_Deduct_Value,2))else '' end) as all_oth_amount,\n" + 
//			"'' as monthVal, '' as yearVal  \n" + 
//			"FROM process_payroll_details a\n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by all_oth_desc\n" + 
//			"union all \n" + 
//			"select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, h.Designation as designaion, \n" + 
//			"if(f.Add_Deduct_Status = 'other' , f.Description, '') as all_oth_desc,  \n" + 
//			"(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
//			"then if(if(f.Add_Deduct_Type = 'fixedType', format(a.Amount,2), format(f.Add_Deduct_Value,2)), format(a.Amount,2), format(f.Add_Deduct_Value,2))else '' end) as all_oth_amount,\n" + 
//			"'' as monthVal, '' as yearVal  \n" + 
//			"FROM process_payroll_details a\n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by all_oth_desc) a\n" + 
//			"UNION ALL\n" + 
//			"-- bank details\n" + 
//			"SELECT * from (select \n" + 
//			"k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department,\n" + 
//			"h.Designation as designaion, \n" + 
//			"\"Bank A/C No.\" as add_fixOrVar_basic_desc, i.Bank_Account as add_fixOrVar_basic_amount,\n" + 
//			"'' as monthVal, '' as yearVal\n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
//			"inner join bank_master j on i.Bank_ID = j.Bank_ID  \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc \n" + 
//			"union all\n" + 
//			"select k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department,\n" + 
//			"h.Designation as designaion,\n" + 
//			"\"Bank A/C No.\" as add_fixOrVar_basic_desc, i.Bank_Account as add_fixOrVar_basic_amount,\n" + 
//			" '' as monthVal, '' as yearVal \n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code  \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
//			"inner join bank_master j on i.Bank_ID = j.Bank_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"union all\n" + 
//			"select k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department,\n" + 
//			"h.Designation as designaion, \n" + 
//			"\"Account Name  :\" as add_fixOrVar_basic_desc, concat(i.Name,\" \",i.lastname) as add_fixOrVar_basic_amount,\n" + 
//			"'' as monthVal, '' as yearVal\n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
//			"inner join bank_master j on i.Bank_ID = j.Bank_ID  \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc \n" + 
//			"union all\n" + 
//			"select k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department,\n" + 
//			"h.Designation as designaion,\n" + 
//			"\"Account Name  :\" as add_fixOrVar_basic_desc, concat(i.Name,\" \",i.lastname) as add_fixOrVar_basic_amount,\n" + 
//			" '' as monthVal, '' as yearVal\n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code  \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
//			"inner join bank_master j on i.Bank_ID = j.Bank_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc\n" + 
//			"union all\n" + 
//			"select k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department,\n" + 
//			"h.Designation as designaion,\n" + 
//			"\"Branch Name  :\" as add_fixOrVar_basic_desc, j.Bank_Name as add_fixOrVar_basic_amount,\n" + 
//			" '' as monthVal, '' as yearVal\n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
//			"inner join bank_master j on i.Bank_ID = j.Bank_ID  \n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc \n" + 
//			"union all\n" + 
//			"select k.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \n" + 
//			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department,\n" + 
//			"h.Designation as designaion,\n" + 
//			"\"Branch Name  :\" as add_fixOrVar_basic_desc, j.Bank_Name as add_fixOrVar_basic_amount,\n" + 
//			"'' as monthVal, '' as yearVal \n" + 
//			"FROM process_payroll_details a \n" + 
//			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
//			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
//			"inner join department d on c.Department_ID = d.Department_ID\n" + 
//			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
//			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code  \n" + 
//			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
//			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
//			"inner join bank_master j on i.Bank_ID = j.Bank_ID\n" + 
//			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
//			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID group by add_fixOrVar_basic_desc)a;",nativeQuery=true)
//	public String[][] paySlipData(@Param("Employee_ID")String empID, @Param("Company_ID")String comID);
//	
	
	
	@Query(value="call Payslip_employees(:payperodid,:dept,:Company_ID,:Employee_ID)",nativeQuery=true)
	public String[][] paySlipData(@Param("payperodid")String payperodid,@Param("dept")String dept,@Param("Employee_ID")String empID, @Param("Company_ID")String comID);
	
	@Query(value="call paySheet(:dept,:Company_ID)",nativeQuery=true)	
	public String[][] getpaySheet(@Param("dept")String dept,@Param("Company_ID")String comID);
	
	@Query(value="select a.Company_Name from company_master a where a.Company_ID =:Company_ID",nativeQuery=true)
	public String loggedComapanyName(@Param("Company_ID")String comID);
	//emp payalip data data end
	
	//all employees paySlips
	@Query(value = "CALL Payslip_multiple_employees()",nativeQuery=true)
	public String[][] getAllEmpsPaySlips(@Param("Company_ID")String comID);
	
	//related department paySlip
	@Query(value = "-- empNo & EPFNo\n" + 
			"select * from (select * from (select a.Employee_ID as empIdTemp,\n" + 
			"'01' as columnCnt, 'Emp. No.  :' as empNoLabel, a.Employee_ID as empId, \n" + 
			"'EPF No.  : ' as epfNoLabel, c.Epf_No as epfNo, '' as blank1, '' as lank2\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID  group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, \n" + 
			"'01' as columnCnt, 'Emp. No.  :' as empNoLabel, a.Employee_ID as empId, \n" + 
			"'EPF No.  : ' as epfNoLabel, c.Epf_No as epfNo, '' as blank1, '' as lank2\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a  \n" + 
			"UNION ALL\n" + 
			"-- year and month\n" + 
			"SELECT * from( \n" + 
			"select a.Employee_ID as empIdTemp,'02' as columnCnt, 'Year :' as empNoLabel, Year(b.Process_Date) as yearVal, \n" + 
			"'Month  : ' as epfNoLabel, monthname(b.Process_Date) as epfNo, '' as blank1, '' as lank2\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID  group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'02' as columnCnt, 'Year :' as empNoLabel, Year(b.Process_Date) as yearVal, \n" + 
			"'Month  : ' as epfNoLabel, monthname(b.Process_Date) as epfNo, '' as blank1, '' as lank2\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by yearVal)a\n" + 
			"UNION ALL\n" + 
			"-- name\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp,'03' as columnCnt, 'Name  :' as nameLabel, concat(e.name,\" \",e.lastname) as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'03' as columnCnt, 'Department  :' as nameLabel, d.Department as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID  group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- department\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp,'04' as columnCnt, 'Department  :' as nameLabel, d.Department as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'04' as columnCnt, 'Name  :' as nameLabel, concat(e.name,\" \",e.lastname) as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- designation\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp,'05' as columnCnt, 'Designation  :' as nameLabel, f.Designation as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join designation_master f on c.Designation_ID = f.Designation_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'05' as columnCnt, 'Designation  :' as nameLabel, f.Designation as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"inner join designation_master f on c.Designation_ID = f.Designation_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '06' as columnCnt, \n" + 
			"'------------------------------------------' as nameLabel, \n" + 
			"'------------------------------------------' as empName, \n" + 
			"'-------------------------' as epfNo, '------------------------' as fName,\n" + 
			"'------------------------' as lname, '----------------------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'06' as columnCnt, \n" + 
			"'------------------------------------------' as nameLabel, \n" + 
			"'------------------------------------------' as empName, \n" + 
			"'--------------------------' as epfNo, '-------------------------' as fName,\n" + 
			"'--------------------------' as lname, '----------------------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- basic salary\n" + 
			"SELECT * from (\n" + 
			"select a.Employee_ID as empIdTemp, '07' as columnCnt, 'BASIC SALARY  :' as basicSalaryLabel, \n" + 
			"'' as blank1, '' as lank2, '' as blank3, '' as lank4, format(c.basicSalary,2) as basicSalaryAmt\n" + 
			"from process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '07' as columnCnt, 'BASIC SALARY  :' as basicSalaryLabel, \n" + 
			"'' as blank1, '' as lank2, '' as blank3, '' as lank4, format(c.basicSalary,2) as basicSalaryAmt\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- addtion depends on basic salary\n" + 
			"SELECT * from (select * from (select a.Employee_ID as empIdTemp, '08' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
			"as add_fixOrVar_basic_amount\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code   \n" + 
			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID)a group by empIdTemp, add_fixOrVar_basic_desc\n" + 
			"union all\n" + 
			"select * from (select a.Employee_ID as empIdTemp, '08' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
			"as add_fixOrVar_basic_amount\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code   \n" + 
			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID)a group by empIdTemp, add_fixOrVar_basic_desc )a \n" + 
			"UNION ALL\n" + 
			"-- total basic salary\n" + 
			"SELECT * from\n" + 
			"(select \n" + 
			"empIdTemp, '09' as columnCnt, 'TOTAL BASIC SALARY' as totBaSaDesc,\n" + 
			"'' as basicSalary, '' as blank2, '' as blank3, '' as blank4, format(max(total_bSalary + basicSalary),2) as totalBS\n" + 
			"from(\n" + 
			"select a.Employee_ID as empIdTemp, '09' as columnCnt, 'TOTAL BASIC SALARY' as totBaSaDesc,\n" + 
			"c.basicSalary as basicSalary, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_bSalary\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all \n" + 
			"select a.Employee_ID as empIdTemp, '09' as columnCnt, 'TOTAL BASIC SALARY' as totBaSaDesc,\n" + 
			"c.basicSalary as basicSalary, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_bSalary\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a group by empIdTemp)b \n" + 
			"UNION ALL\n" + 
			"-- deductions depends on basic salary\n" + 
			"SELECT  empIdTemp, columnCnt, add_fixOrVar_basic_desc, blank1, blank2, blank3, blank4, add_fixOrVar_basic_amount\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '10' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then concat(\"(\",format(a.Amount,2),\")\") else '' end) \n" + 
			"as add_fixOrVar_basic_amount \n" + 
			"from process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID    \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp,add_fixOrVar_basic_desc\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '10' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4, \n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary' then concat(\"(\",format(a.Amount,2),\")\") else '' end) \n" + 
			"as add_fixOrVar_basic_amount \n" + 
			"from process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp,add_fixOrVar_basic_desc)a\n" + 
			"UNION ALL\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '11' as columnCnt, \n" + 
			"'------------------------------------------' as nameLabel, \n" + 
			"'------------------------------------------' as empName, \n" + 
			"'--------------------------' as epfNo, '-------------------------' as fName,\n" + 
			"'--------------------------' as lname, '----------------------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'11' as columnCnt, \n" + 
			"'------------------------------------------' as nameLabel, \n" + 
			"'------------------------------------------' as empName, \n" + 
			"'--------------------------' as epfNo, '-------------------------' as fName,\n" + 
			"'--------------------------' as lname, '----------------------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"-- Total EPF Salary\n" + 
			"SELECT  empIdTemp, columnCnt, totEpfSaDesc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"format(max(total_epf_salary + basicSalary) - max(add_fixOrVar_basic_amount),2) as EPF_Salary\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '12' as columnCnt, 'TOTAL EPF SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_epf_salary, c.basicSalary as basicSalary, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else null end) \n" + 
			"as add_fixOrVar_basic_amount\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp \n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '12' as columnCnt, 'TOTAL EPF SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_epf_salary, c.basicSalary as basicSalary, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as add_fixOrVar_basic_amount\n" + 
			"from process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp) a group by empIdTemp\n" + 
			"UNION ALL\n" + 
			"-- line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '13' as columnCnt, 'EARNINGS' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID  \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '13' as columnCnt, 'EARNINGS' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID  \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- All Earnings\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '14' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_gross_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end)  \n" + 
			"as add_fixOrVar_gross_amount\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join department d on c.Department_ID = d.Department_ID  \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp,add_fixOrVar_gross_desc\n" + 
			"union all \n" + 
			"select a.Employee_ID as empIdTemp, '14' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_gross_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end) \n" + 
			"as add_fixOrVar_gross_amount\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp,add_fixOrVar_gross_desc)a \n" + 
			"UNION ALL\n" + 
			"-- line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '15' as columnCnt, '' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '15' as columnCnt, '' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, '-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"-- gross salary\n" + 
			"SELECT empIdTemp, columnCnt,totEpfSaDesc, '' as blamk1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"format(max(total_epf_salary + gross_salary + basicSalary) - max(add_fixOrVar_basic_amount),2) as EPF_Salary\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '16' as columnCnt, 'GROSS SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '' end) \n" + 
			"as total_epf_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end) \n" + 
			"as gross_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else null end) \n" + 
			"as add_fixOrVar_basic_amount, c.basicSalary as basicSalary, '' as blank2 \n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '16' as columnCnt, 'GROSS SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '' end) \n" + 
			"as total_epf_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end) \n" + 
			"as gross_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '' end) \n" + 
			"as add_fixOrVar_basic_amount, c.basicSalary as basicSalary, '' as blank2\n" + 
			"from process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp) a group by empIdTemp\n" + 
			"UNION ALL\n" + 
			"-- Deduction Label\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '17' as columnCnt, 'DEDUCTIONS' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID  \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '17' as columnCnt, 'DEDUCTIONS' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"-- deductions\n" + 
			"SELECT empIdTemp, columnCnt, add_fixOrVar_basic_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4, add_fixOrVar_basic_amount\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '18' as columnCnt, \n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, \n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType', format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
			"as add_fixOrVar_basic_amount \n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID    \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp,add_fixOrVar_basic_desc\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '18' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, \n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if((f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType'), format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
			"as add_fixOrVar_basic_amount \n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join department d on c.Department_ID = d.Department_ID   \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp,add_fixOrVar_basic_desc)a \n" + 
			"UNION ALL\n" + 
			"-- line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '19' as columnCnt, '' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '19' as columnCnt, '' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, '-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"-- total deductions\n" + 
			"SELECT * from(\n" + 
			"select empIdTemp, columnCnt, 'TOTAL DEDUCTIONS' as totBaSaDesc,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4, format(max(total_deductions),2)\n" + 
			"from(\n" + 
			"select a.Employee_ID as empIdTemp, '20' as columnCnt, 'TOTAL DEDUCTIONS' as totBaSaDesc,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then  a.Amount else '' end) \n" + 
			"as total_deductions \n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join department d on c.Department_ID = d.Department_ID  \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all \n" + 
			"select a.Employee_ID as empIdTemp, '20' as columnCnt, 'TOTAL DEDUCTIONS' as totBaSaDesc,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end) as total_deductions\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a group by empIdTemp)b \n" + 
			"UNION ALL\n" + 
			"-- line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '21' as columnCnt, '' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'21' as columnCnt, '' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName,'' as lname, \n" + 
			"'-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"SELECT empIdTemp, columnCnt, 'NET PAY' as netSalaryDesc, \n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"format((((add_fixOrVar_basic_amount) + (add_fixOrVar_gross_amount) + basicSalary) - all_ded_amount),2)  \n" + 
			"as net_salary\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '22' as columnCnt,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '-' end)  \n" + 
			"as add_fixOrVar_basic_amount, \n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '-' end)  \n" + 
			"as add_fixOrVar_gross_amount,  \n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') then \n" + 
			"a.Amount else '-' end) as all_ded_amount,\n" + 
			"c.basicSalary as basicSalary, '' as blank2\n" + 
			"from process_payroll_details a \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a group by empIdTemp\n" + 
			"UNION ALL\n" + 
			"-- double line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '23' as columnCnt, '' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '::::::::::::::::::' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID  \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '23' as columnCnt, '' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, ':::::::::::::::::::' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"-- company contribution label\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '24' as columnCnt, 'COMPANY COMTRIBUTION' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '24' as columnCnt, 'COMPANY COMTRIBUTION' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"SELECT * from (\n" + 
			"select a.Employee_ID as empIdTemp,'25' as columnCnt, \n" + 
			"if(f.Add_Deduct_Status = 'other' , f.Description, '') as all_oth_desc,  \n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"then a.Amount else '' end) as all_oth_amount \n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp,all_oth_desc\n" + 
			"union all \n" + 
			"select a.Employee_ID as empIdTemp,'25' as columnCnt,\n" + 
			"if(f.Add_Deduct_Status = 'other' , f.Description, '') as all_oth_desc,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,  \n" + 
			"(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"then a.Amount else '' end) as all_oth_amount\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp,all_oth_desc)a\n" + 
			"UNION ALL\n" + 
			"-- bank details\n" + 
			"select \n" + 
			"a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Bank A/C No.\" as add_fixOrVar_basic_desc, i.Bank_Account as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID  \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp, add_fixOrVar_basic_desc \n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Bank A/C No.\" as add_fixOrVar_basic_desc, i.Bank_Account as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp, add_fixOrVar_basic_desc\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Account Name  :\" as add_fixOrVar_basic_desc, concat(i.Name,\" \",i.lastname) as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID  \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp, add_fixOrVar_basic_desc \n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Account Name  :\" as add_fixOrVar_basic_desc, concat(i.Name,\" \",i.lastname) as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp, add_fixOrVar_basic_desc\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Branch Name  :\" as add_fixOrVar_basic_desc, j.Bank_Name as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID  \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp, add_fixOrVar_basic_desc \n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Branch Name  :\" as add_fixOrVar_basic_desc, j.Bank_Name as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and c.Department_ID =:Department_ID group by empIdTemp, add_fixOrVar_basic_desc)a \n" + 
			"order by empIdTemp,columnCnt",nativeQuery=true)
	public String[][] getEmpRelatedDepartmentPaySlip(@Param("Company_ID")String comID, @Param("Department_ID")String depID);
	
	//paySlip related startdate and end date
	@Query(value = "-- empNo & EPFNo\n" + 
			"select * from (select * from (select a.Employee_ID as empIdTemp,\n" + 
			"'01' as columnCnt, 'Emp. No.  :' as empNoLabel, a.Employee_ID as empId, \n" + 
			"'EPF No.  : ' as epfNoLabel, c.Epf_No as epfNo, '' as blank1, '' as lank2\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, \n" + 
			"'01' as columnCnt, 'Emp. No.  :' as empNoLabel, a.Employee_ID as empId, \n" + 
			"'EPF No.  : ' as epfNoLabel, c.Epf_No as epfNo, '' as blank1, '' as lank2\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"-- year and month\n" + 
			"SELECT * from( \n" + 
			"select a.Employee_ID as empIdTemp,'02' as columnCnt, 'Year :' as empNoLabel, Year(b.Process_Date) as yearVal, \n" + 
			"'Month  : ' as epfNoLabel, monthname(b.Process_Date) as epfNo, '' as blank1, '' as lank2\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date  group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'02' as columnCnt, 'Year :' as empNoLabel, Year(b.Process_Date) as yearVal, \n" + 
			"'Month  : ' as epfNoLabel, monthname(b.Process_Date) as epfNo, '' as blank1, '' as lank2\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by yearVal)a\n" + 
			"UNION ALL\n" + 
			"-- name\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp,'03' as columnCnt, 'Name  :' as nameLabel, concat(e.name,\" \",e.lastname) as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join pay_periods f on a.Pay_Period_ID = f.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and f.Start_Date ='2020-02-01' and f.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'03' as columnCnt, 'Department  :' as nameLabel, d.Department as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join pay_periods f on a.Pay_Period_ID = f.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and f.Start_Date ='2020-02-01' and f.End_Date =:End_Date group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- department\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp,'04' as columnCnt, 'Department  :' as nameLabel, d.Department as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join pay_periods f on a.Pay_Period_ID = f.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and f.Start_Date ='2020-02-01' and f.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'04' as columnCnt, 'Name  :' as nameLabel, concat(e.name,\" \",e.lastname) as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join department d on c.Department_ID = d.Department_ID\n" + 
			"inner join pay_periods f on a.Pay_Period_ID = f.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and f.Start_Date ='2020-02-01' and f.End_Date =:End_Date group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- designation\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp,'05' as columnCnt, 'Designation  :' as nameLabel, f.Designation as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join designation_master f on c.Designation_ID = f.Designation_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'05' as columnCnt, 'Designation  :' as nameLabel, f.Designation as empName,'' as epfNo,\n" + 
			"'' as fName,'' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"inner join designation_master f on c.Designation_ID = f.Designation_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '06' as columnCnt, \n" + 
			"'------------------------------------------' as nameLabel, \n" + 
			"'------------------------------------------' as empName, \n" + 
			"'-------------------------' as epfNo, '------------------------' as fName,\n" + 
			"'------------------------' as lname, '----------------------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'06' as columnCnt, \n" + 
			"'------------------------------------------' as nameLabel, \n" + 
			"'------------------------------------------' as empName, \n" + 
			"'--------------------------' as epfNo, '-------------------------' as fName,\n" + 
			"'--------------------------' as lname, '----------------------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- basic salary\n" + 
			"SELECT * from (\n" + 
			"select a.Employee_ID as empIdTemp, '07' as columnCnt, 'BASIC SALARY  :' as basicSalaryLabel, \n" + 
			"'' as blank1, '' as lank2, '' as blank3, '' as lank4, format(c.basicSalary,2) as basicSalaryAmt\n" + 
			"from process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '07' as columnCnt, 'BASIC SALARY  :' as basicSalaryLabel, \n" + 
			"'' as blank1, '' as lank2, '' as blank3, '' as lank4, format(c.basicSalary,2) as basicSalaryAmt\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- addtion depends on basic salary\n" + 
			"SELECT * from (select * from (select a.Employee_ID as empIdTemp, '08' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
			"as add_fixOrVar_basic_amount\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code   \n" + 
			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date)a group by empIdTemp, add_fixOrVar_basic_desc\n" + 
			"union all\n" + 
			"select * from (select a.Employee_ID as empIdTemp, '08' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
			"as add_fixOrVar_basic_amount\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code   \n" + 
			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date)a group by empIdTemp, add_fixOrVar_basic_desc )a \n" + 
			"UNION ALL\n" + 
			"-- total basic salary\n" + 
			"SELECT * from\n" + 
			"(select \n" + 
			"empIdTemp, '09' as columnCnt, 'TOTAL BASIC SALARY' as totBaSaDesc,\n" + 
			"'' as basicSalary, '' as blank2, '' as blank3, '' as blank4, format(max(total_bSalary + basicSalary),2) as totalBS\n" + 
			"from(\n" + 
			"select a.Employee_ID as empIdTemp, '09' as columnCnt, 'TOTAL BASIC SALARY' as totBaSaDesc,\n" + 
			"c.basicSalary as basicSalary, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_bSalary\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all \n" + 
			"select a.Employee_ID as empIdTemp, '09' as columnCnt, 'TOTAL BASIC SALARY' as totBaSaDesc,\n" + 
			"c.basicSalary as basicSalary, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_bSalary\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp)a group by empIdTemp)b \n" + 
			"UNION ALL\n" + 
			"-- deductions depends on basic salary\n" + 
			"SELECT  empIdTemp, columnCnt, add_fixOrVar_basic_desc, blank1, blank2, blank3, blank4, add_fixOrVar_basic_amount\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '10' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then concat(\"(\",format(a.Amount,2),\")\") else '' end) \n" + 
			"as add_fixOrVar_basic_amount \n" + 
			"from process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID    \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp,add_fixOrVar_basic_desc\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '10' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4, \n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary' then concat(\"(\",format(a.Amount,2),\")\") else '' end) \n" + 
			"as add_fixOrVar_basic_amount \n" + 
			"from process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp,add_fixOrVar_basic_desc)a\n" + 
			"UNION ALL\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '11' as columnCnt, \n" + 
			"'------------------------------------------' as nameLabel, \n" + 
			"'------------------------------------------' as empName, \n" + 
			"'--------------------------' as epfNo, '-------------------------' as fName,\n" + 
			"'--------------------------' as lname, '----------------------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'11' as columnCnt, \n" + 
			"'------------------------------------------' as nameLabel, \n" + 
			"'------------------------------------------' as empName, \n" + 
			"'--------------------------' as epfNo, '-------------------------' as fName,\n" + 
			"'--------------------------' as lname, '----------------------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date ='2020-02-01' and d.End_Date =:End_Date group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"-- Total EPF Salary\n" + 
			"SELECT  empIdTemp, columnCnt, totEpfSaDesc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"format(max(total_epf_salary + basicSalary) - max(add_fixOrVar_basic_amount),2) as EPF_Salary\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '12' as columnCnt, 'TOTAL EPF SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_epf_salary, c.basicSalary as basicSalary, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else null end) \n" + 
			"as add_fixOrVar_basic_amount\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp \n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '12' as columnCnt, 'TOTAL EPF SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as total_epf_salary, c.basicSalary as basicSalary, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \n" + 
			"as add_fixOrVar_basic_amount\n" + 
			"from process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp) a group by empIdTemp\n" + 
			"UNION ALL\n" + 
			"-- line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '13' as columnCnt, 'EARNINGS' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '13' as columnCnt, 'EARNINGS' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID  \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp)a\n" + 
			"UNION ALL\n" + 
			"-- All Earnings\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '14' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_gross_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end)  \n" + 
			"as add_fixOrVar_gross_amount\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp,add_fixOrVar_gross_desc\n" + 
			"union all \n" + 
			"select a.Employee_ID as empIdTemp, '14' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_gross_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end) \n" + 
			"as add_fixOrVar_gross_amount\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp,add_fixOrVar_gross_desc)a \n" + 
			"UNION ALL\n" + 
			"-- line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '15' as columnCnt, '' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID = :Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '15' as columnCnt, '' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, '-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"-- gross salary\n" + 
			"SELECT empIdTemp, columnCnt,totEpfSaDesc, '' as blamk1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"format(max(total_epf_salary + gross_salary + basicSalary) - max(add_fixOrVar_basic_amount),2) as EPF_Salary\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '16' as columnCnt, 'GROSS SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '' end) \n" + 
			"as total_epf_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end) \n" + 
			"as gross_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else null end) \n" + 
			"as add_fixOrVar_basic_amount, c.basicSalary as basicSalary, '' as blank2 \n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '16' as columnCnt, 'GROSS SALARY' as totEpfSaDesc,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '' end) \n" + 
			"as total_epf_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end) \n" + 
			"as gross_salary,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '' end) \n" + 
			"as add_fixOrVar_basic_amount, c.basicSalary as basicSalary, '' as blank2\n" + 
			"from process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID \n" + 
			"inner join company_master i on a.Company_ID = i.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp) a group by empIdTemp\n" + 
			"UNION ALL\n" + 
			"-- Deduction Label\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '17' as columnCnt, 'DEDUCTIONS' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '17' as columnCnt, 'DEDUCTIONS' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"-- deductions\n" + 
			"SELECT empIdTemp, columnCnt, add_fixOrVar_basic_desc, '' as blank1, '' as blank2, '' as blank3, '' as blank4, add_fixOrVar_basic_amount\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '18' as columnCnt, \n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, \n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType', format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
			"as add_fixOrVar_basic_amount \n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID    \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp,add_fixOrVar_basic_desc\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '18' as columnCnt,\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \n" + 
			"as add_fixOrVar_basic_desc, \n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if((f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType'), format(a.Amount,2), format(f.Add_Deduct_Value,2)) else '' end) \n" + 
			"as add_fixOrVar_basic_amount \n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID   \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp,add_fixOrVar_basic_desc)a \n" + 
			"UNION ALL\n" + 
			"-- line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '19' as columnCnt, '' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '19' as columnCnt, '' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, '-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"-- total deductions\n" + 
			"SELECT * from(\n" + 
			"select empIdTemp, columnCnt, 'TOTAL DEDUCTIONS' as totBaSaDesc,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4, format(max(total_deductions),2)\n" + 
			"from(\n" + 
			"select a.Employee_ID as empIdTemp, '20' as columnCnt, 'TOTAL DEDUCTIONS' as totBaSaDesc,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then  a.Amount else '' end) \n" + 
			"as total_deductions \n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID  \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all \n" + 
			"select a.Employee_ID as empIdTemp, '20' as columnCnt, 'TOTAL DEDUCTIONS' as totBaSaDesc,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '' end) as total_deductions\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp)a group by empIdTemp)b \n" + 
			"UNION ALL\n" + 
			"-- line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '21' as columnCnt, '' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'21' as columnCnt, '' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName,'' as lname, \n" + 
			"'-----------------' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"SELECT empIdTemp, columnCnt, 'NET PAY' as netSalaryDesc, \n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"format((((add_fixOrVar_basic_amount) + (add_fixOrVar_gross_amount) + basicSalary) - all_ded_amount),2)  \n" + 
			"as net_salary\n" + 
			"from (\n" + 
			"select a.Employee_ID as empIdTemp, '22' as columnCnt,\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then a.Amount else '-' end)  \n" + 
			"as add_fixOrVar_basic_amount, \n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then a.Amount else '-' end)  \n" + 
			"as add_fixOrVar_gross_amount,  \n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') then \n" + 
			"a.Amount else '-' end) as all_ded_amount,\n" + 
			"c.basicSalary as basicSalary, '' as blank2\n" + 
			"from process_payroll_details a \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join designation_master h on c.Designation_ID = h.Designation_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp)a group by empIdTemp\n" + 
			"UNION ALL\n" + 
			"-- double line\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '23' as columnCnt, '' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '::::::::::::::::::' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '23' as columnCnt, '' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, ':::::::::::::::::::' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"-- company contribution label\n" + 
			"SELECT * from(\n" + 
			"select a.Employee_ID as empIdTemp, '24' as columnCnt, 'COMPANY COMTRIBUTION' as nameLabel, '' as empName, \n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID \n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp, '24' as columnCnt, 'COMPANY COMTRIBUTION' as nameLabel, '' as empName,\n" + 
			"'' as epfNo, '' as fName, '' as lname, '' as abc\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID   \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp)a \n" + 
			"UNION ALL\n" + 
			"SELECT * from (\n" + 
			"select a.Employee_ID as empIdTemp,'25' as columnCnt, \n" + 
			"if(f.Add_Deduct_Status = 'other' , f.Description, '') as all_oth_desc,  \n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,\n" + 
			"(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"then a.Amount else '' end) as all_oth_amount \n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp,all_oth_desc\n" + 
			"union all \n" + 
			"select a.Employee_ID as empIdTemp,'25' as columnCnt,\n" + 
			"if(f.Add_Deduct_Status = 'other' , f.Description, '') as all_oth_desc,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4,  \n" + 
			"(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \n" + 
			"then a.Amount else '' end) as all_oth_amount\n" + 
			"FROM process_payroll_details a\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp,all_oth_desc)a\n" + 
			"UNION ALL\n" + 
			"-- bank details\n" + 
			"SELECT \n" + 
			"a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Bank A/C No.\" as add_fixOrVar_basic_desc, i.Bank_Account as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID  \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp, add_fixOrVar_basic_desc \n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Bank A/C No.\" as add_fixOrVar_basic_desc, i.Bank_Account as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp, add_fixOrVar_basic_desc\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Account Name  :\" as add_fixOrVar_basic_desc, concat(i.Name,\" \",i.lastname) as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID  \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp, add_fixOrVar_basic_desc \n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Account Name  :\" as add_fixOrVar_basic_desc, concat(i.Name,\" \",i.lastname) as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp, add_fixOrVar_basic_desc\n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Branch Name  :\" as add_fixOrVar_basic_desc, j.Bank_Name as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID  \n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp, add_fixOrVar_basic_desc \n" + 
			"union all\n" + 
			"select a.Employee_ID as empIdTemp,'26' as columnCnt,\n" + 
			"\"Branch Name  :\" as add_fixOrVar_basic_desc, j.Bank_Name as add_fixOrVar_basic_amount,\n" + 
			"'' as blank1, '' as blank2, '' as blank3, '' as blank4\n" + 
			"FROM process_payroll_details a \n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master i on a.Employee_ID = i.Employee_ID\n" + 
			"inner join bank_master j on i.Bank_ID = j.Bank_ID\n" + 
			"inner join pay_periods d on a.Pay_Period_ID = d.Pay_Period_ID\n" + 
			"inner join company_master k on a.Company_ID = k.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and d.Start_Date =:Start_Date and d.End_Date =:End_Date group by empIdTemp, add_fixOrVar_basic_desc)a \n" + 
			"order by empIdTemp,columnCnt",nativeQuery=true)
			public String[][] getPayslipRelatedStartDateAndEndDate(@Param("Company_ID")String comID, @Param("Start_Date")String startDate, @Param("End_Date")String endDate);
	
	//load processPayroll tables data
	//table 01
	@Query(value="select \n" + 
			"count(empId), format(sum(basicSalary),2), format(sum(perEmpAddition),2), format(sum(perEmpDeduction),2), format(sum(perEmpOther),2)\n" + 
			"from(select empId, empName, lname, basicSalary,\n" + 
			"sum(fixAddBaValAmt) + sum(fixAddGrValAmt) + (basicSalary * sum(fixAddBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixAddGrPerAmt))/100) as perEmpAddition,\n" + 
			"sum(fixDedBaValAmt) + sum(fixDedGrValAmt) + (basicSalary * sum(fixDedBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixDedGrPerAmt))/100) as perEmpDeduction,\n" + 
			"sum(fixOthBaValAmt) + sum(fixOthGrValAmt) + (basicSalary * sum(fixOthBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixOthGrPerAmt))/100) as perEmpOther\n" + 
			"from (select e.Employee_ID as empId, d.Name as empName,d.lastname as lname, e.basicSalary as basicSalary,\n" + 
			"-- Fixed Addition --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddGrValAmt, \n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddGrPerAmt,         \n" + 
			"-- Fixed Deduction -------------- \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedGrValAmt, \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedGrPerAmt, \n" + 
			"-- Fixed Other -------------- \n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthGrPerAmt \n" + 
			"FROM employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"where a.Company_ID =:Company_ID group by empId\n" + 
			"UNION ALL\n" + 
			"select a.Emp_ID as empId, b.Name as empName,b.lastname as lname, e.basicSalary as basicSalary,  \n" + 
			"-- Variable Addition --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,0)) as varAddGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddGrPerAmt, \n" + 
			"-- Variable Deduction --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedGrValAmt,  \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedGrPerAmt,  \n" + 
			"-- Variable Other --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthGrPerAmt  \n" + 
			"FROM emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code  \n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID  \n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID and a.Company_ID =:Company_ID group by empId) a group by empId order by empId)b",nativeQuery=true)
	public String[][] loadTable01Data(@Param("Pay_Code_ID")String payCodeID, @Param("Company_ID")String comID);
	
	//table 02 data
	@Query(value="SELECT empId, empName, lname, basicSalary,\n" + 
			"format(sum(fixAddBaValAmt) + sum(fixAddGrValAmt) + (basicSalary * sum(fixAddBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixAddGrPerAmt))/100),2) as perEmpAddition,\n" + 
			"format(sum(fixDedBaValAmt) + sum(fixDedGrValAmt) + (basicSalary * sum(fixDedBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixDedGrPerAmt))/100),2) as perEmpDeduction,\n" + 
			"format(sum(fixOthBaValAmt) + sum(fixOthGrValAmt) + (basicSalary * sum(fixOthBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixOthGrPerAmt))/100),2) as perEmpOther\n" + 
			"from (select e.Employee_ID as empId, d.Name as empName,d.lastname as lname, e.basicSalary as basicSalary,\n" + 
			"-- Fixed Addition --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddGrValAmt, \n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddGrPerAmt,         \n" + 
			"-- 0 as varAddBaValAmt,0 as varAddGrValAmt, 0 as varAddBaPerAmt,0 as varAddGrPerAmt,\n" + 
			"-- Fixed Deduction -------------- \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedGrValAmt, \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedGrPerAmt, \n" + 
			"-- 0 as varDedBaValAmt,0 as varDedGrValAmt, 0 as varDedBaPerAmt,0 as varDedGrPerAmt,\n" + 
			"-- Fixed Other -------------- \n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthGrPerAmt \n" + 
			"-- 0 as varOthBaValAmt,0 as varOthGrValAmt, 0 as varOthBaPerAmt,0 as varOthGrPerAmt  \n" + 
			"FROM employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"where a.Company_ID =:Company_ID group by empId\n" + 
			"UNION ALL\n" + 
			"select a.Emp_ID as empId, b.Name as empName,b.lastname as lname, e.basicSalary as basicSalary,  \n" + 
			"-- Variable Addition --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,0)) as varAddGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddGrPerAmt, \n" + 
			"-- Variable Deduction --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedGrValAmt,  \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedGrPerAmt,  \n" + 
			"-- Variable Other --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthGrPerAmt  \n" + 
			"FROM emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code  \n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID  \n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID and a.Company_ID =:Company_ID group by empId) a group by empId",nativeQuery=true)
	public String[][] loadTable02Data(@Param("Pay_Code_ID")String payCodeID, @Param("Company_ID")String comID);
	
	//table 03
	@Query(value="select e.Employee_ID as empId, d.Name as empName,d.lastname as lname, e.basicSalary as basicSalary,\n" + 
			"-- Fixed Addition --------------\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,'')) as fixAddBaValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddBaValAmt,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,'')) as fixAddGrValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddGrValAmt,      \n" + 
			"'' as varAddBaValDesc,'' as varAddBaValAmt,'' as varAddGrValDesc,'' as varAddGrValAmt, \n" + 
			"-- Fixed Deduction -------------- \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,'')) as fixDedBaValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedBaValAmt,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,'')) as fixDedGrValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedGrValAmt, \n" + 
			"'' as varDedBaValDesc,'' as varDedBaValAmt,'' as varDedGrValDesc,'' as varDedGrValAmt,   \n" + 
			"-- Fixed Other -------------- \n" + 
			"(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,'')) as fixOthBaValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthBaValAmt,\n" + 
			"(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,'')) as fixOthGrValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthGrValAmt, \n" + 
			"'' as varOthBaValDesc,'' as varOthBaValAmt,'' as varOthGrValDesc,'' as varOthGrValAmt \n" + 
			"from employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"where a.Employee_ID =:Emp_ID and a.Company_ID =:Company_ID \n" + 
			"union all\n" + 
			"select a.Emp_ID as empId, b.Name as empName,b.lastname as lname, e.basicSalary as basicSalary,  \n" + 
			"-- Variable Addition --------------\n" + 
			"'' as fixAddBaValDesc,'' as fixAddBaValAmt,'' as fixAddGrValDesc,'' as fixAddGrValAmt, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,'')) as varAddBaValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddBaValAmt,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,'')) as varAddGrValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddGrValAmt, \n" + 
			"-- Variable Deduction --------------\n" + 
			"'' as fixDedBaValDesc,'' as fixDedBaValAmt,'' as fixDedGrValDesc,'' as fixDedGrValAmt, \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,'')) as varDedBaValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedBaValAmt,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,'')) as varDedGrValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedGrValAmt,  \n" + 
			"-- Variable Other --------------\n" + 
			"'' as fixOthBaValDesc,'' as fixOthBaValAmt,'' as fixOthGrValDesc,'' as fixOthGrValAmt,  \n" + 
			"(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,'')) as varOthBaValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthBaValAmt,\n" + 
			"(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,'')) as varOthGrValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthGrValAmt \n" + 
			"from emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code  \n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID  \n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID and a.Emp_ID =:Emp_ID and a.Company_ID =:Company_ID",nativeQuery=true)
	public String[][] loadTable03Data(@Param("Pay_Code_ID")String payCodeID, @Param("Emp_ID")String empID, @Param("Company_ID")String comID);
	
	@Query(value="select * from(select\n" + 
			"e.Employee_ID as empId, d.Name as empName,d.lastname as lname, e.basicSalary as basicSalary\n" + 
			"from employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID\n" + 
			"where a.Employee_ID =:Emp_ID and a.Company_ID =:Company_ID\n" + 
			"union all\n" + 
			"select \n" + 
			"a.Emp_ID as empId, b.Name as empName,b.lastname as lname, e.basicSalary as basicSalary\n" + 
			"from emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code  \n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID \n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID and a.Emp_ID =:Emp_ID and a.Company_ID =:Company_ID) a group by empId",nativeQuery=true)
	public String[][] loadTable03BasicData(@Param("Pay_Code_ID")String payCodeID, @Param("Emp_ID")String empID, @Param("Company_ID")String comID);
	
	//calculation priority value
	@Query(value="select ((sum(grossVar) + sum(grossfix)) + basicSalary) as EPFCal\n" + 
			"from (select e.basicSalary as basicSalary,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary = 'grossSalary' \n" + 
			"and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as grossfix,\n" + 
			"'' as grossVar \n" + 
			"from employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"where a.Employee_ID =:Emp_ID and a.Company_ID =:Company_ID\n" + 
			"union all  \n" + 
			"select e.basicSalary as basicSalary,'' as grossFix,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary = 'grossSalary' \n" + 
			"and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as  grossVar \n" + 
			"from emp_month_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code  \n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID and a.Emp_ID =:Emp_ID and a.Company_ID =:Company_ID)a",nativeQuery=true)
	public String CalPriorotyData(@Param("Pay_Code_ID")String payCodeID, @Param("Emp_ID")String empID,
			@Param("Company_ID")String comID);
	
	//dedGrossPerValues
	@Query(value="select dedPerGrossDesc, dedPerGross \r\n" + 
			"from (select	\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Description ,null)) \r\n" + 
			"as dedPerGrossDesc,		\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Add_Deduct_Value ,null)) \r\n" + 
			"as dedPerGross\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"where a.Employee_ID =:Employee_ID)a where dedPerGrossDesc is not null",nativeQuery=true)
	public String[][] dedGrossPerValues(@Param("Employee_ID")String empID);
	
	
//	calculation priority values	
	//addGrossValues
	@Query(value="SELECT addPerGrossDesc, addPerGross \n" + 
			"from (select	\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type= 'fixedType',  c.Description ,null)) \n" + 
			"as addPerGrossDesc,  \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type= 'fixedType',  c.Add_Deduct_Value,null)) \n" + 
			"as addPerGross\n" + 
			"FROM employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID\n" + 
			"UNION ALL\n" + 
			"SELECT	\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type= 'variableType',  c.Description ,null)) \n" + 
			"as addPerGrossDesc,  \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage'\n" + 
			" and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type= 'variableType',  a.Amount  ,null)) \n" + 
			"as addPerGross\n" + 
			"FROM emp_month_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Emp_ID =:Employee_ID and a.Company_ID =:Company_ID)a where addPerGrossDesc is not null",nativeQuery=true)
	public String[][] addGrossPerValues(@Param("Employee_ID")String empID,@Param("Company_ID")String comID);
	//Deductions
	@Query(value = "select dedPerGrossDesc, dedPerGross \n" + 
			"from (SELECT \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' \n" + 
			"and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'fixedType', c.Description ,null))  \n" + 
			"as dedPerGrossDesc,		\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' \n" + 
			"and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,null)) \n" + 
			"as dedPerGross \n" + 
			"FROM employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID\n" + 
			"UNION ALL\n" + 
			"SELECT \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' \n" + 
			"and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'variableType', c.Description ,null))  \n" + 
			"as dedPerGrossDesc,		\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' \n" + 
			"and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'variableType', a.Amount ,null)) \n" + 
			"as dedPerGross \n" + 
			"FROM emp_month_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"where a.Emp_ID =:Employee_ID and a.Company_ID =:Company_ID)a where dedPerGrossDesc is not null", nativeQuery=true)
	public String[][] getFixAndVariableDedGrossPercentageValues(@Param("Employee_ID")String empID,@Param("Company_ID")String comID);
	
	//otherGrossPerValue
	@Query(value="select othPerGrossDesc, othPerGross \n" + 
			"from (select	 \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' \n" + 
			"and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'fixedType', c.Description ,null))\n" + 
			"as othPerGrossDesc,		 \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' \n" + 
			"and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,null))\n" + 
			"as othPerGross  \n" + 
			"from employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Employee_ID =:Employee_ID  and a.Company_ID =:Company_ID\n" + 
			"UNION ALL\n" + 
			"select	 \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' \n" + 
			"and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'variableType', c.Description ,null))\n" + 
			"as othPerGrossDesc,		 \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' \n" + 
			"and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'variableType', a.Amount ,null))\n" + 
			"as othPerGross  \n" + 
			"from emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Emp_ID =:Employee_ID and a.Company_ID =:Company_ID)a where othPerGross is not null",nativeQuery=true)
	public String[][] getFixAndVariableOthGrossPercentageValues(@Param("Employee_ID")String empID, @Param("Company_ID")String comID);
	
	//depends on basic salary
	@Query(value = "select a.basicSalary from employee_details a where a.Employee_ID =:Employee_ID "
			+ " and a.Company_ID =:Company_ID",nativeQuery=true)
	public String getBasicSalaryCalculatePercentageValues(@Param("Employee_ID")String empID,@Param("Company_ID")String comID);
	
	//additions values
	@Query(value = "SELECT addPerGrossDesc, addPerGross \n" + 
			"from (select	\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'fixedType',  c.Description ,null)) \n" + 
			"as addPerGrossDesc,  \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'fixedType',  c.Add_Deduct_Value,null)) \n" + 
			"as addPerGross\n" + 
			"FROM employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID\n" + 
			"UNION ALL\n" + 
			"SELECT	\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'variableType',  c.Description ,null)) \n" + 
			"as addPerGrossDesc,  \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage'\n" + 
			" and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'variableType',  a.Amount  ,null)) \n" + 
			"as addPerGross\n" + 
			"FROM emp_month_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Emp_ID =:Employee_ID and a.Company_ID =:Company_ID)a where addPerGrossDesc is not null",nativeQuery=true)
	public String[][] getFixAndVariableAddBasicPercentageValues(@Param("Employee_ID")String empID,@Param("Company_ID")String comID);
	
	//deductions values
	@Query(value = "SELECT addPerGrossDesc, addPerGross \n" + 
			"from (select	\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'fixedType',  c.Description ,null)) \n" + 
			"as addPerGrossDesc,  \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'fixedType',  c.Add_Deduct_Value,null)) \n" + 
			"as addPerGross\n" + 
			"FROM employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID\n" + 
			"UNION ALL\n" + 
			"SELECT	\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'variableType',  c.Description ,null)) \n" + 
			"as addPerGrossDesc,  \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage'\n" + 
			" and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'variableType',  a.Amount  ,null)) \n" + 
			"as addPerGross\n" + 
			"FROM emp_month_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Emp_ID =:Employee_ID and a.Company_ID =:Company_ID)a where addPerGrossDesc is not null",nativeQuery=true)
	public String[][] getFixAndVariableDedBasicPercentageValues(@Param("Employee_ID")String empID,@Param("Company_ID")String comID);
	
	//others values
	@Query(value = "SELECT addPerGrossDesc, addPerGross \n" + 
			"from (select	\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'fixedType',  c.Description ,null)) \n" + 
			"as addPerGrossDesc,  \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'fixedType',  c.Add_Deduct_Value,null)) \n" + 
			"as addPerGross\n" + 
			"FROM employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Employee_ID =:Employee_ID and a.Company_ID =:Company_ID\n" + 
			"UNION ALL\n" + 
			"SELECT	\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage'\n" + 
			"and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'variableType',  c.Description ,null)) \n" + 
			"as addPerGrossDesc,  \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage'\n" + 
			" and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type= 'variableType',  a.Amount  ,null)) \n" + 
			"as addPerGross\n" + 
			"FROM emp_month_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"where a.Emp_ID =:Employee_ID and a.Company_ID =:Company_ID)a where addPerGrossDesc is not null;",nativeQuery=true)
	public String[][] getFixAndVariableOthBasicPercentageValues(@Param("Employee_ID")String empID,@Param("Company_ID")String comID);
	
	@Query(value="select \n" + 
			"count(empId) as emps, format(sum(basicSalary),2) as totalBasicSalary, format(sum(perEmpAddition),2) as totalaAdditions,\n" + 
			"format(sum(perEmpDeduction),2) as totalDeductions, format(sum(perEmpOther),2) as totalOthers, \n" + 
			"format(sum(grossSalary),2) as totalGross, format((sum(grossSalary) - sum(perEmpDeduction)),2) as netSalary\n" + 
			"from(select empId, empName, lname, basicSalary,\n" + 
			"sum(fixAddBaValAmt) + sum(fixAddGrValAmt) + (basicSalary * sum(fixAddBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixAddGrPerAmt))/100) as perEmpAddition,\n" + 
			"sum(fixDedBaValAmt) + sum(fixDedGrValAmt) + (basicSalary * sum(fixDedBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixDedGrPerAmt))/100) as perEmpDeduction,\n" + 
			"sum(fixOthBaValAmt) + sum(fixOthGrValAmt) + (basicSalary * sum(fixOthBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixOthGrPerAmt))/100) as perEmpOther,\n" + 
			"(sum(fixAddGrValAmt) + basicSalary) as grossSalary\n" + 
			"from (select e.Employee_ID as empId, d.Name as empName,d.lastname as lname, e.basicSalary as basicSalary,\n" + 
			"-- Fixed Addition --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddGrValAmt, \n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddGrPerAmt,         \n" + 
			"-- Fixed Deduction -------------- \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedGrValAmt, \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedGrPerAmt, \n" + 
			"-- Fixed Other -------------- \n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthGrPerAmt \n" + 
			"FROM employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"where a.Company_ID =:Company_ID group by empId\n" + 
			"UNION ALL\n" + 
			"select a.Emp_ID as empId, b.Name as empName,b.lastname as lname, e.basicSalary as basicSalary,  \n" + 
			"-- Variable Addition --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,0)) as varAddGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddGrPerAmt, \n" + 
			"-- Variable Deduction --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedGrValAmt,  \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedGrPerAmt,  \n" + 
			"-- Variable Other --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthGrPerAmt  \n" + 
			"FROM emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code  \n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID  \n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID and a.Company_ID =:Company_ID group by empId) a group by empId order by empId)b;",nativeQuery=true)
	public String[][] getMoProPCTabbleData(@Param("Pay_Code_ID")String payCodeID,@Param("Company_ID")String comID);
	
	@Query(value="select * from (select\n" + 
			"e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,null)) as allowDesc, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,null)) as alloAmt \n" + 
			"from employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID \n" + 
			"where a.Company_ID =:Company_ID) a where allowDesc is not null \n" + 
			"UNION ALL \n" + 
			"select * from (select \n" + 
			"e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,null)) as allowDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,null)) as alloAmt \n" + 
			"from employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID \n" + 
			"where a.Company_ID =:Company_ID) a where allowDesc is not null \n" + 
			"UNION ALL \n" + 
			"select * from (select\n" + 
			"e.Employee_ID as empId,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID,  \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,null)) as allowDesc, \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,null)) as alloAmt \n" + 
			"from employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID\n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID \n" + 
			"where a.Company_ID =:Company_ID) a where allowDesc is not null  \n" + 
			"UNION ALL \n" + 
			"select * from (select\n" + 
			"e.Employee_ID as empId,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID,   \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,null)) as allowDesc, \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,null)) as alloAmt \n" + 
			"from employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID \n" + 
			"where a.Company_ID =:Company_ID) a where allowDesc is not null\n" + 
			"UNION ALL\n" + 
			"select * from (select \n" + 
			"e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID,   \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,null)) as allowDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,null)) as alloAmt\n" + 
			"from employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID \n" + 
			"where a.Company_ID =:Company_ID) a where allowDesc is not null\n" + 
			"UNION ALL\n" + 
			"select * from (select\n" + 
			"e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID,   \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,null)) as allowDesc, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,null)) as alloAmt \n" + 
			"from employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID\n" + 
			"where a.Company_ID =:Company_ID) a where allowDesc is not null \n" + 
			"UNION ALL\n" + 
			"select * from (select \n" + 
			"e.Employee_ID as empId,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID,   \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,null)) as allowDesc, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,null)) as alloAmt\n" + 
			"from emp_month_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and a.Pay_Code_ID =:Pay_Code_ID) a where allowDesc is not null \n" + 
			"UNION ALL \n" + 
			"select * from (select \n" + 
			"e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID,   \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,null)) as allowDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,null)) as alloAmt\n" + 
			"from emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and a.Pay_Code_ID =:Pay_Code_ID) a where allowDesc is not null \n" + 
			"UNION ALL\n" + 
			"select * from (select\n" + 
			"e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID,   \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,null)) as allowDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,null)) as alloAmt\n" + 
			"from emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and a.Pay_Code_ID =:Pay_Code_ID) a where allowDesc is not null \n" + 
			"UNION ALL \n" + 
			"select * from (select \n" + 
			"e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID,   \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,null)) as allowDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,null)) as alloAmt\n" + 
			"from emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID\n" + 
			"where a.Company_ID =:Company_ID and a.Pay_Code_ID =:Pay_Code_ID) a where allowDesc is not null \n" + 
			"UNION ALL\n" + 
			"select * from (select \n" + 
			"e.Employee_ID as empId,\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID,    \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,null)) as allowDesc, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,null)) as alloAmt\n" + 
			"from emp_month_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and a.Pay_Code_ID =:Pay_Code_ID) a where allowDesc is not null \n" + 
			"UNION ALL \n" + 
			"select * from (select\n" + 
			"e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Pay_Add_Deduct_Type_Code ,null)) as allowID,    \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,null)) as allowDesc, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,null)) as alloAmt \n" + 
			"from emp_month_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and a.Pay_Code_ID =:Pay_Code_ID) a where allowDesc is not null",nativeQuery=true)
	public String[][] sampleSave(@Param("Pay_Code_ID")String payCodeID,@Param("Company_ID")String comID);
	
	@Query(value = "select * from(select \n" + 
			"-- e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  c.Pay_Add_Deduct_Type_Code ,null))  \n" + 
			"as addPerGrossID, \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  c.Add_Deduct_Value ,null))  \n" + 
			"as addPerGross \n" + 
			"from employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID\n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID\n" + 
			"where a.Company_ID =:Company_ID)a where addPerGrossID is not null group by addPerGrossID\n" + 
			"UNION ALL\n" + 
			"select * from(select \n" + 
			"-- e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  c.Pay_Add_Deduct_Type_Code ,null))  \n" + 
			"as addPerGrossID, \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  a.Amount ,null))  \n" + 
			"as addPerGross \n" + 
			"from emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID\n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID\n" + 
			"where a.Company_ID =:Company_ID)a where addPerGrossID is not null group by addPerGrossID;",nativeQuery=true)
	public String[][] dedGrossPerCal(@Param("Company_ID")String comID);
	
	@Query(value="select * from(select \n" + 
			"-- e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  c.Pay_Add_Deduct_Type_Code ,null))  \n" + 
			"as addPerGrossID, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  c.Add_Deduct_Value ,null))  \n" + 
			"as addPerGross \n" + 
			"from employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID\n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID\n" + 
			"where a.Company_ID =:Company_ID)a where addPerGrossID is not null group by addPerGrossID\n" + 
			"UNION ALL\n" + 
			"select * from(select \n" + 
			"-- e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  c.Pay_Add_Deduct_Type_Code ,null))  \n" + 
			"as addPerGrossID, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  a.Amount ,null))  \n" + 
			"as addPerGross \n" + 
			"from emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID\n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID\n" + 
			"where a.Company_ID =:Company_ID)a where addPerGrossID is not null group by addPerGrossID",nativeQuery=true)
	public String[][] addGrossPerCal(@Param("Company_ID")String comID);
	
	@Query(value="select * from(select \n" + 
			"-- e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  c.Pay_Add_Deduct_Type_Code ,null))  \n" + 
			"as addPerGrossID, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  c.Add_Deduct_Value ,null))  \n" + 
			"as addPerGross \n" + 
			"from employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID\n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID\n" + 
			"where a.Company_ID =:Company_ID)a where addPerGrossID is not null group by addPerGrossID\n" + 
			"UNION ALL\n" + 
			"select * from(select \n" + 
			"-- e.Employee_ID as empId, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  c.Pay_Add_Deduct_Type_Code ,null))  \n" + 
			"as addPerGrossID, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType'\n" + 
			"and Is_On_Basic_Salary =  'grossSalary',  a.Amount ,null))  \n" + 
			"as addPerGross \n" + 
			"from emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID\n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID\n" + 
			"where a.Company_ID =:Company_ID)a where addPerGrossID is not null group by addPerGrossID",nativeQuery=true)
	public String[][] otherGrossPerCal(@Param("Company_ID")String comID);
	
	@Query(value = "SELECT emp, sum(grossTotVal) + basicSalary as calPri\n" + 
			"from(select e.Employee_ID as emp, d.Name as empName, e.basicSalary as basicSalary, \n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary = 'grossSalary' \n" + 
			"and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,0)) \n" + 
			"as grossTotVal \n" + 
			"from employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID\n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID\n" + 
			"where f.Company_ID =:Company_ID group by emp \n" + 
			"UNION ALL \n" + 
			"SELECT a.Emp_ID as emp, d.Name as empName, e.basicSalary as basicSalary, \n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary = 'grossSalary' \n" + 
			"and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,0))  \n" + 
			"as  grossTotValVar \n" + 
			"from emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"inner join company_master f on a.Company_ID = f.Company_ID\n" + 
			"where f.Company_ID =:Company_ID group by emp )a group by emp order by emp;", nativeQuery=true)
	public String[][] calPriEmpList(@Param("Company_ID")String comID);
	
	@Query(value = "select \n" + 
			"count(empId) as emps, format(sum(basicSalary),2) as totalBasicSalary, \n" + 
			"format(sum(perEmpAddition),2) as totalaAdditions, format(sum(perEmpDeduction),2) as totalDeductions\n" + 
			"from(select empId, empName, lname, basicSalary,\n" + 
			"sum(fixAddBaValAmt) + sum(fixAddGrValAmt) + (basicSalary * sum(fixAddBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixAddGrPerAmt))/100) as perEmpAddition,\n" + 
			"sum(fixDedBaValAmt) + sum(fixDedGrValAmt) + (basicSalary * sum(fixDedBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixDedGrPerAmt))/100) as perEmpDeduction,\n" + 
			"sum(fixOthBaValAmt) + sum(fixOthGrValAmt) + (basicSalary * sum(fixOthBaPerAmt)/100) + (((sum(fixAddGrValAmt) + basicSalary) * sum(fixOthGrPerAmt))/100) as perEmpOther,\n" + 
			"(sum(fixAddGrValAmt) + basicSalary) as grossSalary\n" + 
			"from (select e.Employee_ID as empId, d.Name as empName,d.lastname as lname, e.basicSalary as basicSalary,\n" + 
			"-- Fixed Addition --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddGrValAmt, \n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixAddGrPerAmt,         \n" + 
			"-- Fixed Deduction -------------- \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedGrValAmt, \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixDedGrPerAmt, \n" + 
			"-- Fixed Other -------------- \n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as fixOthGrPerAmt \n" + 
			"FROM employee_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID  \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID \n" + 
			"where a.Company_ID =:Company_ID group by empId\n" + 
			"UNION ALL\n" + 
			"select a.Emp_ID as empId, b.Name as empName,b.lastname as lname, e.basicSalary as basicSalary,  \n" + 
			"-- Variable Addition --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,0)) as varAddGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varAddGrPerAmt, \n" + 
			"-- Variable Deduction --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedGrValAmt,  \n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varDedGrPerAmt,  \n" + 
			"-- Variable Other --------------\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthBaValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthGrValAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'basicSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthBaPerAmt,\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'percentage' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as varOthGrPerAmt  \n" + 
			"FROM emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code  \n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID  \n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID and a.Company_ID =:Company_ID group by empId) a group by empId order by empId)b",nativeQuery=true)
	public String[][] saveDataMonthProcessMaster(@Param("Pay_Code_ID")String payCodeID,@Param("Company_ID")String comID);

	
	@Query(value = "CALL processPayroll(:payCodeID,:comID)",nativeQuery=true)
	public String[][]  getProcessPayroll(@Param("payCodeID")String payCodeID,@Param("comID")String comID);
	
}
