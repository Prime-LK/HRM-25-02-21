package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.ProcessPayrollMaster;

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
	// end of employee report 01 data
	
	// emp payslip data start
	@Query(value="select * from (SELECT \r\n" + 
			"g.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \r\n" + 
			"as add_fixOrVar_basic_desc, \r\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \r\n" + 
			"as add_fixOrVar_basic_amount \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join company_master g on c.Company_ID = g.Company_ID   \r\n" + 
			"where a.Employee_ID =:Employee_ID group by add_fixOrVar_basic_desc\r\n" + 
			"union all\r\n" + 
			"SELECT g.Company_Name as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')\r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \r\n" + 
			"as add_fixOrVar_basic_desc, \r\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end) \r\n" + 
			"as add_fixOrVar_basic_amount \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join company_master g on c.Company_ID = g.Company_ID   \r\n" + 
			"where a.Employee_ID =:Employee_ID group by add_fixOrVar_basic_desc)a \r\n" + 
			"union all  \r\n" + 
			"select * from(select company_name, processDate, fName, lname, empId, epfNo, basicSalary, department, \r\n" + 
			"'Total basic Salary' as totBaSaDesc,max(total_bSalary) \r\n" + 
			"from(select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"'Total basic Salary' as totBaSaDesc,\r\n" + 
			"max(if(f.Add_Deduct_Status = 'addition' and f.Is_On_Basic_Salary = 'basicSalary', f.Add_Deduct_Value, '') + c.basicSalary) as total_bSalary\r\n" + 
			"FROM process_payroll_details a\r\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"where a.Employee_ID =:Employee_ID\r\n" + 
			"union all SELECT '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"'Total basic Salary' as totBaSaDesc,\r\n" + 
			"max(if(f.Add_Deduct_Status = 'addition' and f.Is_On_Basic_Salary = 'basicSalary', f.Add_Deduct_Value, '') + c.basicSalary) as total_bSalary\r\n" + 
			"FROM process_payroll_details a\r\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"where a.Employee_ID =:Employee_ID) a group by totBaSaDesc)b \r\n" + 
			"union all \r\n" + 
			"select * from\r\n" + 
			"(select company_name, processDate, fName, lname, empId, epfNo, basicSalary, department, totEpfSaDesc, max(total_epf_salary)\r\n" + 
			"from (select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"'Total EPF Salary' as totEpfSaDesc,\r\n" + 
			"max(if(f.Add_Deduct_Status = 'addition' and f.Is_On_Basic_Salary = 'basicSalary', f.Add_Deduct_Value, '') + c.basicSalary) as total_epf_salary\r\n" + 
			"FROM process_payroll_details a\r\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"where a.Employee_ID =:Employee_ID\r\n" + 
			"union all \r\n" + 
			"SELECT '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"'Total EPF Salary' as totEpfSaDesc,\r\n" + 
			"max(if(f.Add_Deduct_Status = 'addition' and f.Is_On_Basic_Salary = 'basicSalary', f.Add_Deduct_Value, '') + c.basicSalary) as total_epf_salary\r\n" + 
			"FROM process_payroll_details a\r\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"where a.Employee_ID =:Employee_ID) a group by totEpfSaDesc) b  \r\n" + 
			"union all \r\n" + 
			"select * from(select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname, \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \r\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \r\n" + 
			"as add_fixOrVar_gross_desc,\r\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end)  \r\n" + 
			"as add_fixOrVar_gross_amount\r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code  \r\n" + 
			"where a.Employee_ID =:Employee_ID group by add_fixOrVar_gross_desc\r\n" + 
			"union all select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname, \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \r\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', f.Description, f.Description) else '' end) \r\n" + 
			"as add_fixOrVar_gross_desc,\r\n" + 
			"(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '' end)  \r\n" + 
			"as add_fixOrVar_gross_amount\r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code  \r\n" + 
			"where a.Employee_ID =:Employee_ID group by add_fixOrVar_gross_desc)a \r\n" + 
			"union all \r\n" + 
			"select  '' as company_name,'' as processDate,'' as fName, '' as lname,  \r\n" + 
			"emp,'' as epfNo, basicSalary, '' as department,'Gross Salary' as grossSaDesc, \r\n" + 
			"(((add_fixOrVar_basic_amount) + (add_fixOrVar_gross_amount) + (oth_fixOrVar_basic_amount)) + basicSalary) as gross_salary\r\n" + 
			"from(SELECT  \r\n" + 
			"a.Employee_ID as emp ,b.basicSalary as basicSalary, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as add_fixOrVar_basic_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as add_fixOrVar_gross_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as oth_fixOrVar_basic_amount \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_details b on a.Employee_ID = b.Employee_ID \r\n" + 
			"where a.Employee_ID =:Employee_ID)a where emp=:Employee_ID\r\n" + 
			"union all\r\n" + 
			"select * from(select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,\r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"'Taxable Salary' as taxSaDesc,'0.00' as taxableSalary \r\n" + 
			"FROM process_payroll_details a\r\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"where a.Employee_ID =:Employee_ID group by taxSaDesc \r\n" + 
			"union all select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,\r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"'Taxable Salary' as taxSaDesc,'0.00' as taxableSalary \r\n" + 
			"FROM process_payroll_details a\r\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID  \r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"where a.Employee_ID =:Employee_ID group by taxSaDesc) a \r\n" + 
			"union all \r\n" + 
			"select * from (select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname, \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"if(f.Add_Deduct_Status = 'deduction' , f.Description, '') as all_ded_desc, \r\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \r\n" + 
			"then if(if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value), a.Amount, f.Add_Deduct_Value)else '' end) as all_ded_amount \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID\r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"where a.Employee_ID =:Employee_ID group by all_ded_desc\r\n" + 
			"union all\r\n" + 
			"select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname, \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department, \r\n" + 
			"if(f.Add_Deduct_Status = 'deduction' , f.Description, '') as all_ded_desc, \r\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \r\n" + 
			"then if(if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value), a.Amount, f.Add_Deduct_Value)else '' end) as all_ded_amount \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID\r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID \r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID  \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"where a.Employee_ID =:Employee_ID group by all_ded_desc)a\r\n" + 
			"union all \r\n" + 
			"select * from(select '' as company_name,'' as processDate,'' as fName, '' as lname,\r\n" + 
			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \r\n" + 
			"'Total Deduction' as totDedDesc, (sum(all_ded_amount)) + 0 as all_deductions\r\n" + 
			"from(SELECT\r\n" + 
			"if(f.Add_Deduct_Status = 'deduction' , f.Description, null) as all_ded_desc, \r\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \r\n" + 
			"then if(if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value), a.Amount, f.Add_Deduct_Value)else null end) as all_ded_amount \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID\r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\r\n" + 
			"where a.Employee_ID =:Employee_ID) a \r\n" + 
			"union all \r\n" + 
			"select  \r\n" + 
			"'' as company_name,'' as processDate,'' as fName, '' as lname, \r\n" + 
			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \r\n" + 
			"'' as totDedDesc, '' as all_deductions \r\n" + 
			"from(SELECT \r\n" + 
			"if(f.Add_Deduct_Status = 'deduction' , f.Description, null) as all_ded_desc,  \r\n" + 
			"(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"then if(if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value), a.Amount, f.Add_Deduct_Value)else null end) as all_ded_amount \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"where a.Employee_ID =:Employee_ID) a group by totDedDesc order by totDedDesc)a\r\n" + 
			"union all  \r\n" + 
			"select * from(select * from (select \r\n" + 
			"'' as company_name,'' as processDate,'' as fName, '' as lname, \r\n" + 
			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \r\n" + 
			"'Balance B/F' as balanceBFDesc,'0.00' as balanceBFAmt \r\n" + 
			"from(SELECT \r\n" + 
			"a.Employee_ID as emp, b.basicSalary as basicSalary, \r\n" + 
			"'' as totEpfSaDesc,'' as total_epf_salary,'' as grossSaDesc,'' as gross_salary \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details b on a.Employee_ID = b.Employee_ID \r\n" + 
			"where a.Employee_ID =:Employee_ID)a group by balanceBFDesc order by balanceBFDesc desc)a\r\n" + 
			"union all \r\n" + 
			"select  \r\n" + 
			"'' as company_name,'' as processDate,'' as fName, '' as lname,  \r\n" + 
			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, \r\n" + 
			"'' as balanceBFDesc,'' as balanceBFAmt \r\n" + 
			"from(SELECT \r\n" + 
			"a.Employee_ID as emp, b.basicSalary as basicSalary, \r\n" + 
			"'' as totEpfSaDesc,'' as total_epf_salary,'' as grossSaDesc,'' as gross_salary \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_details b on a.Employee_ID = b.Employee_ID \r\n" + 
			"where a.Employee_ID =:Employee_ID)a group by balanceBFDesc order by balanceBFDesc asc)a \r\n" + 
			"union all  \r\n" + 
			"select  '' as company_name,'' as processDate,'' as fName, '' as lname,  \r\n" + 
			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, 'Net Salary' as netSalaryDesc, \r\n" + 
			"((((add_fixOrVar_basic_amount) + (add_fixOrVar_gross_amount) + (oth_fixOrVar_basic_amount)) + basicSalary) - all_ded_amount)  \r\n" + 
			"as net_salary from (SELECT \r\n" + 
			"a.Employee_ID as emp, b.basicSalary as basicSalary, \r\n" + 
			"'' as totEpfSaDesc,'' as total_epf_salary,'' as grossSaDesc,'' as gross_salary,'' as taxSaDesc,'' as taxableSalary, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as add_fixOrVar_basic_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as add_fixOrVar_gross_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as oth_fixOrVar_basic_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') then \r\n" + 
			"if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value) else '-' end) as all_ded_amount \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_details b on a.Employee_ID = b.Employee_ID \r\n" + 
			"where a.Employee_ID =:Employee_ID)a \r\n" + 
			"union all \r\n" + 
			"select '' as company_name,'' as processDate,'' as fName, '' as lname, \r\n" + 
			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, 'Balance C/F' as balanceCFDesc,'0.00' as balanceCFAmt \r\n" + 
			"from(SELECT  a.Employee_ID as emp, b.basicSalary as basicSalary, \r\n" + 
			"'' as totEpfSaDesc,'' as total_epf_salary,'' as grossSaDesc,'' as gross_salary,'' as taxSaDesc,'' as taxableSalary,\r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end) \r\n" + 
			"as add_fixOrVar_basic_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as add_fixOrVar_gross_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as oth_fixOrVar_basic_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') then \r\n" + 
			"if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value) else '-' end) as all_ded_amount \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_details b on a.Employee_ID = b.Employee_ID \r\n" + 
			"where a.Employee_ID =:Employee_ID)a -- limit\r\n" + 
			"union all \r\n" + 
			"select * from(select * from(select \r\n" + 
			"'' as company_name,'' as processDate,'' as fName, '' as lname,\r\n" + 
			"'' as empId, '' as epfNo, '' as basicSalary, '' as department, 'Emp Bank Name' as emoBaName,\r\n" + 
			"((((add_fixOrVar_basic_amount) + (add_fixOrVar_gross_amount) + (oth_fixOrVar_basic_amount)) + basicSalary) - all_ded_amount)\r\n" + 
			"as bankAmt \r\n" + 
			"from (SELECT  \r\n" + 
			"a.Employee_ID as emp, b.basicSalary as basicSalary,\r\n" + 
			"'' as totEpfSaDesc,'' as total_epf_salary,'' as grossSaDesc,'' as gross_salary,'' as taxSaDesc,'' as taxableSalary,\r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as add_fixOrVar_basic_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as add_fixOrVar_gross_amount,\r\n" + 
			"sum(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as oth_fixOrVar_basic_amount,\r\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') then\r\n" + 
			"if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value) else '-' end) as all_ded_amount \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_details b on a.Employee_ID = b.Employee_ID\r\n" + 
			"where a.Employee_ID =:Employee_ID)a group by emoBaName order by emoBaName)a \r\n" + 
			"union all \r\n" + 
			"select  \r\n" + 
			"'' as company_name,'' as processDate,'' as fName, '' as lname, '' as empId, '' as epfNo, '' as basicSalary,  \r\n" + 
			"'' as department, '' as emoBaName,'' as bankAmt\r\n" + 
			"from (SELECT \r\n" + 
			"a.Employee_ID as emp, b.basicSalary as basicSalary,\r\n" + 
			"'' as totEpfSaDesc,'' as total_epf_salary,'' as grossSaDesc,'' as gross_salary,'' as taxSaDesc,'' as taxableSalary,\r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as add_fixOrVar_basic_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'addition' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') \r\n" + 
			"and f.Is_On_Basic_Salary = 'grossSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as add_fixOrVar_gross_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"and f.Is_On_Basic_Salary = 'basicSalary'  then if(f.Add_Deduct_Type = 'variableType', a.Amount, f.Add_Deduct_Value) else '-' end)  \r\n" + 
			"as oth_fixOrVar_basic_amount, \r\n" + 
			"sum(case when f.Add_Deduct_Status = 'deduction' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType') then \r\n" + 
			"if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value) else '-' end) as all_ded_amount \r\n" + 
			"FROM process_payroll_details a \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_details b on a.Employee_ID = b.Employee_ID \r\n" + 
			"where a.Employee_ID =:Employee_ID)a order by emoBaName)a \r\n" + 
			"union all\r\n" + 
			"select * from (select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department,\r\n" + 
			"if(f.Add_Deduct_Status = 'other' , f.Description, '') as all_oth_desc,  \r\n" + 
			"(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"then if(if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value), a.Amount, f.Add_Deduct_Value)else '' end) as all_oth_amount \r\n" + 
			"FROM process_payroll_details a\r\n" + 
			"inner join month_process_paycodes b on a.Pay_Code_ID = b.Pay_Code_ID \r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"where a.Employee_ID =:Employee_ID group by all_oth_desc\r\n" + 
			"union all select '' as company_name,b.Process_Date as processDate,e.Name as fName, e.lastname as lname,  \r\n" + 
			"e.Employee_ID as empId,c.Epf_No as epfNo,c.basicSalary as basicSalary, d.Department as department,\r\n" + 
			"if(f.Add_Deduct_Status = 'other' , f.Description, '') as all_oth_desc,  \r\n" + 
			"(case when f.Add_Deduct_Status = 'other' and (f.Add_Deduct_Type = 'variableType' or f.Add_Deduct_Type = 'fixedType')  \r\n" + 
			"then if(if(f.Add_Deduct_Type = 'fixedType', a.Amount, f.Add_Deduct_Value), a.Amount, f.Add_Deduct_Value)else '' end) as all_oth_amount \r\n" + 
			"FROM process_payroll_details a\r\n" + 
			"inner join month_process_master b on a.Pay_Period_ID = b.Pay_Period_ID \r\n" + 
			"inner join employee_details c on a.Employee_ID = c.Employee_ID \r\n" + 
			"inner join department d on c.Department_ID = d.Department_ID\r\n" + 
			"inner join employee_master e on a.Employee_ID = e.Employee_ID \r\n" + 
			"inner join pay_add_deduct_types f on a.Pay_Add_Deduct_Type_Code = f.Pay_Add_Deduct_Type_Code \r\n" + 
			"where a.Employee_ID =:Employee_ID group by all_oth_desc) a",nativeQuery=true)
	public String[][] paySlipData(@Param("Employee_ID")String empID);	
	
	//emp payalip data data end
	
	//load processPayroll tables data
	//table 01
	@Query(value="select \r\n" + 
			"count(empId) as emps, format(sum(basicSalary),2) as basicSalary,\r\n" + 
			"format(sum(fixAddVal + additionBasicPer + additionGrossPer + addVarVal + addVarBasicPer + additionGrossPerVar),2) as addition,\r\n" + 
			"format(sum(fixDedVal + dedFixrBasicPer + deductionGrossPer + tot_ded_var_value + dedVarBasicPer + deductionGrossPerVar),2) as deduction,\r\n" + 
			"format(sum(othVal + otherBasicPer + othGrossPer + tot_oth_var_value + OthVarBasicPer + otherGrossPerVar),2) as other\r\n" + 
			"from \r\n" + 
			"(select \r\n" + 
			"empId,empName,basicSalary,\r\n" + 
			"-- additions\r\n" + 
			"-- fix\r\n" + 
			"max(addVal) as fixAddVal,\r\n" + 
			"(basicSalary * max(addPerBasic))/100 as additionBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(addPerGross)/100 as additionGrossPer,\r\n" + 
			"-- var \r\n" + 
			"max(tot_add_var_value) as addVarVal,\r\n" + 
			"(basicSalary * max(tot_basic_var_add_per_full))/100 as addVarBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(tot_gross_var_add_per_full)/100 as additionGrossPerVar,\r\n" + 
			"-- deductions\r\n" + 
			"-- fix\r\n" + 
			"max(dedVal) as fixDedVal,\r\n" + 
			"(basicSalary * max(dedPerBasic))/100 as dedFixrBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(dedPerGross)/100 as deductionGrossPer,\r\n" + 
			"-- var\r\n" + 
			"max(tot_ded_var_value) as tot_ded_var_value,\r\n" + 
			"(basicSalary * max(tot_basic_var_ded_per_full))/100 as dedVarBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(tot_gross_var_ded_per_full)/100 as deductionGrossPerVar,\r\n" + 
			"-- other\r\n" + 
			"-- fix\r\n" + 
			"max(othVal) as othVal,\r\n" + 
			"(basicSalary * max(othPerBasic))/100 as otherBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(othPerGross)/100 as othGrossPer,\r\n" + 
			"-- var\r\n" + 
			"max(tot_oth_var_value) as tot_oth_var_value,\r\n" + 
			"(basicSalary * max(tot_basic_var_oth_per_full))/100 as OthVarBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(tot_gross_var_oth_per_full)/100 as otherGrossPerVar\r\n" + 
			"from\r\n" + 
			"(select\r\n" + 
			"e.Employee_ID as empId, d.Name as empName, e.basicSalary as basicSalary,\r\n" + 
			"-- -------------- gross fix & var\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as grossTotVal,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as grossTotDedVal,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as  grossTotOthVal,\r\n" + 
			"0 as grossTotValVar,\r\n" + 
			"0 as grossTotDedValVar,\r\n" + 
			"0 as grossTotValOthVar,\r\n" + 
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
			"0 as grossVal,\r\n" + 
			"0 as grossTotDedVal,\r\n" + 
			"0 as grossTotOthVal,\r\n" + 
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
			"where f.Pay_Code_ID =:Pay_Code_ID group by a.Emp_ID) a group by empId order by empId)b",nativeQuery=true)
	public String[][] loadTable01Data(@Param("Pay_Code_ID")String payCodeID);
	
	//table 02 data
	@Query(value="select \r\n" + 
			"empId as emps,empName as empName,lname as lastName, sum(basicSalary) as basicSalary,\r\n" + 
			"format(sum(fixAddVal + additionBasicPer + additionGrossPer + addVarVal + addVarBasicPer + additionGrossPerVar),2) as addition,\r\n" + 
			"format(sum(fixDedVal + dedFixrBasicPer + deductionGrossPer + tot_ded_var_value + dedVarBasicPer + deductionGrossPerVar),2) as deduction,\r\n" + 
			"format(sum(othVal + otherBasicPer + othGrossPer + tot_oth_var_value + OthVarBasicPer + otherGrossPerVar),2) as other\r\n" + 
			"from \r\n" + 
			"(select \r\n" + 
			"empId,empName,lname,basicSalary,\r\n" + 
			"-- additions\r\n" + 
			"-- fix\r\n" + 
			"max(addVal) as fixAddVal,\r\n" + 
			"(basicSalary * max(addPerBasic))/100 as additionBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(addPerGross)/100 as additionGrossPer,\r\n" + 
			"-- var \r\n" + 
			"max(tot_add_var_value) as addVarVal,\r\n" + 
			"(basicSalary * max(tot_basic_var_add_per_full))/100 as addVarBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(tot_gross_var_add_per_full)/100 as additionGrossPerVar,\r\n" + 
			"-- deductions\r\n" + 
			"-- fix\r\n" + 
			"max(dedVal) as fixDedVal,\r\n" + 
			"(basicSalary * max(dedPerBasic))/100 as dedFixrBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(dedPerGross)/100 as deductionGrossPer,\r\n" + 
			"-- var\r\n" + 
			"max(tot_ded_var_value) as tot_ded_var_value,\r\n" + 
			"(basicSalary * max(tot_basic_var_ded_per_full))/100 as dedVarBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(tot_gross_var_ded_per_full)/100 as deductionGrossPerVar,\r\n" + 
			"-- other\r\n" + 
			"-- fix\r\n" + 
			"max(othVal) as othVal,\r\n" + 
			"(basicSalary * max(othPerBasic))/100 as otherBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(othPerGross)/100 as othGrossPer,\r\n" + 
			"-- var\r\n" + 
			"max(tot_oth_var_value) as tot_oth_var_value,\r\n" + 
			"(basicSalary * max(tot_basic_var_oth_per_full))/100 as OthVarBasicPer,\r\n" + 
			"((max(grossTotValVar) + max(grossTotVal)) + basicSalary) * max(tot_gross_var_oth_per_full)/100 as otherGrossPerVar\r\n" + 
			"from\r\n" + 
			"(select\r\n" + 
			"e.Employee_ID as empId, d.Name as empName,d.lastname as lname, e.basicSalary as basicSalary,\r\n" + 
			"-- -------------- gross fix & var\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as grossTotVal,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'deduction' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as grossTotDedVal,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'other' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) as  grossTotOthVal,\r\n" + 
			"0 as grossTotValVar,\r\n" + 
			"0 as grossTotDedValVar,\r\n" + 
			"0 as grossTotValOthVar,\r\n" + 
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
			"a.Emp_ID as empId, b.Name as empName,b.lastname as lname, e.basicSalary as basicSalary, \r\n" + 
			"-- -------------- gross fix & var\r\n" + 
			"0 as grossVal,\r\n" + 
			"0 as grossTotDedVal,\r\n" + 
			"0 as grossTotOthVal,\r\n" + 
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
			"where f.Pay_Code_ID =:Pay_Code_ID group by a.Emp_ID) a group by empId order by empId)b group by emps order by emps",nativeQuery=true)
	public String[][] loadTable02Data(@Param("Pay_Code_ID")String payCodeID);
	
	//table 03
	@Query(value="select\n" + 
			"e.Employee_ID as empId, d.Name as empName,d.lastname as lname, e.basicSalary as basicSalary,\n" + 
			"-- Fixed Addition --------------\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,'')) as addValDesc, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as addVal,  \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'fixedType',  c.Description ,'')) as addPerBasicDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'fixedType',  c.Add_Deduct_Value ,'')) as addPerBasic,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'fixedType',  c.Description ,''))  as addPerGrossDesc,  \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'fixedType',  c.Add_Deduct_Value ,''))  as addPerGross, \n" + 
			"'' as addVarValDesc ,'' as addVarVal,'' as addVarBasicDesc,'' as addVarBasic, '' as addVarGrossDesc,'' as addVarGross,\n" + 
			"-- Fixed Deduction --------------\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Description ,'')) as dedValDesc,	\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as dedVal,	\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'fixedType',  c.Description ,'')) as dedPerBasicDesc,		\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'fixedType',  c.Add_Deduct_Value ,'')) as dedPerBasic,	 \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'fixedType', c.Description ,'')) as dedPerGrossDesc,		 \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as dedPerGross, \n" + 
			"'' as dedVarValDesc, '' as dedVarVal,'' as dedVarBasicDesc,'' as dedVarBasic,  '' as dedVarGrossDesc,'' as dedVarGross, \n" + 
			"-- Fixed Other --------------\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.Description ,'')) as othValDesc,	 \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,'')) as othVal,\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'fixedType',  c.Description ,'')) as othPerBasicDesc,			\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'fixedType',  c.Add_Deduct_Value ,'')) as othPerBasic,	\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'fixedType', c.Description ,'')) as othPerGrossDesc,		\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'fixedType', c.Add_Deduct_Value ,'')) as othPerGross,\n" + 
			"'' as othVarValDesc,'' as othVarVal, '' as othVarBasicDesc,'' as othVarBasic, '' as othVarGrossDesc,'' as othVarGross  \n" + 
			"from employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID \n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID\n" + 
			"where a.Employee_ID =:Emp_ID and a.Company_ID =:Company_ID\n" + 
			"union all\n" + 
			"select \n" + 
			"a.Emp_ID as empId, b.Name as empName,b.lastname as lname, e.basicSalary as basicSalary,  \n" + 
			"-- Variable Addition --------------\n" + 
			"'' as addValDesc,'' as addVal,'' as addPerBasicDesc,'' as addPerBasic, '' as addPerGrossDesc,'' as dedPerGross, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType' ,  c.Description,''))as addVarValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value' ,  a.Amount  ,''))as addVarVal, \n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'variableType',  c.Description ,'')) as addVarBasicDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'variableType',  a.Amount ,'')) as addVarBasic,	\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'variableType', c.Description ,'')) as addVarGrossDesc,	\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as addVarGross, \n" + 
			"-- Variable Deduction --------------\n" + 
			"'' as dedValDesc,'' as dedVal, '' as dedPerBasicDesc, '' as dedPerBasic,'' as dedPerGrossDesc,'' as dedPerGross,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', c.Description ,'')) as dedVarValDesc, \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as dedVarVal,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'variableType', c.Description ,'')) as dedVarBasicDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as dedVarBasic, \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'variableType', c.Description ,''))	as dedVarGrossDesc,  \n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'variableType', a.Amount ,''))	as dedVarGross, \n" + 
			"-- Variable Other --------------	 \n" + 
			"'' as othValDesc,'' as othVal, '' as othPerBasicDesc,'' as othPerBasic, '' as othPerGrossDesc,'' as othPerGross, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.Description ,'')) as othVarValDesc,\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', a.Amount ,'')) as othVarVal, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'variableType', c.Description ,'')) as othVarBasicDesc,  \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary' and c.Add_Deduct_Type = 'variableType', a.Amount ,'')) as othVarBasic, \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'variableType', c.Description ,'')) as othVarGrossDesc,   \n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary' and c.Add_Deduct_Type = 'variableType', a.Amount ,''))	as othVarGross \n" + 
			"from emp_month_salary_details a \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code  \n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID \n" + 
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
	@Query(value="select ((sum(grossVar) + sum(grossfix)) + basicSalary) as EPFCal\r\n" + 
			"from (select\r\n" + 
			"e.basicSalary as basicSalary,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,'')) as grossfix,\r\n" + 
			"'' as grossVar\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID\r\n" + 
			"where a.Employee_ID =:Emp_ID\r\n" + 
			"union all \r\n" + 
			"select \r\n" + 
			"e.basicSalary as basicSalary,'' as grossFix,\r\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', a.Amount ,'')) as  grossVar\r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID and a.Emp_ID =:Emp_ID)a;",nativeQuery=true)
	public String CalPriorotyData(@Param("Pay_Code_ID")String payCodeID, @Param("Emp_ID")String empID);
	
	//otherGrossPerValue
	@Query(value="select othPerGrossDesc, othPerGross \r\n" + 
			"from (select	\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Description ,null))\r\n" + 
			"as othPerGrossDesc,		\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Add_Deduct_Value ,null))\r\n" + 
			"as othPerGross \r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"where a.Employee_ID =:Employee_ID)a where othPerGross is not null",nativeQuery=true)
	public String[][] otherGrossPerValues(@Param("Employee_ID")String empID);
	
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
	
	//addGrossValues
	@Query(value="select addPerGrossDesc, addPerGross \r\n" + 
			"from (select	\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary',  c.Description ,null))  \r\n" + 
			"as addPerGrossDesc, \r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary',  c.Add_Deduct_Value ,null))\r\n" + 
			"as addPerGross\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"where a.Employee_ID =:Employee_ID)a where addPerGrossDesc is not null",nativeQuery=true)
	public String[][] addGrossPerValues(@Param("Employee_ID")String empID);
	
	@Query(value="select emps,format(basicSalary,2) as basicSalary,format((addition + other) + basicSalary,2) as grossSalary, \r\n" + 
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
	public String[][] getMoProPCTabbleData(@Param("Pay_Code_ID")String payCodeID);
	
	@Query(value="-- fix allowances\r\n" + 
			"-- addition\r\n" + 
			"select * from(select\r\n" + 
			"e.Employee_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code ,null)) as addValID, \r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,null)) as addVal\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID)a where addValID is not null\r\n" + 
			"union all\r\n" + 
			"select * from(select\r\n" + 
			"e.Employee_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary',  c.Pay_Add_Deduct_Type_Code ,null)) \r\n" + 
			"as addPerBasicID,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary',  c.Add_Deduct_Value ,null)) \r\n" + 
			"as addPerBasic\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID)a where addPerBasicID is not null\r\n" + 
			"-- deduction\r\n" + 
			"union all\r\n" + 
			"select * from(select\r\n" + 
			"e.Employee_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code ,null)) as dedValID,	\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,null)) as dedVal	\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID)a where dedValID is not null\r\n" + 
			"union all\r\n" + 
			"select * from(select\r\n" + 
			"e.Employee_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary',  c.Pay_Add_Deduct_Type_Code ,null)) \r\n" + 
			"as dedPerBasicID,			\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary',  c.Add_Deduct_Value ,null)) \r\n" + 
			"as dedPerBasic\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID)a where dedPerBasicID is not null\r\n" + 
			"union all\r\n" + 
			"-- ohters\r\n" + 
			"select * from(select\r\n" + 
			"e.Employee_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code ,null)) as othValID,				\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,null)) as othVal\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID)a where othValID is not null\r\n" + 
			"union all\r\n" + 
			"select * from(select\r\n" + 
			"e.Employee_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary',  c.Pay_Add_Deduct_Type_Code ,null)) \r\n" + 
			"as othPerBasicID,				\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary',  c.Add_Deduct_Value ,null)) \r\n" + 
			"as othPerBasic\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID)a where othPerBasicID is not null\r\n" + 
			"union all\r\n" + 
			"-- variables allowances\r\n" + 
			"-- addition\r\n" + 
			"select * from(select \r\n" + 
			"a.Emp_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value' , c.Pay_Add_Deduct_Type_Code ,null))\r\n" + 
			"as tot_add_var_value_ID, \r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value' ,  a.Amount  ,null))\r\n" + 
			"as tot_add_var_value\r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID)a where tot_add_var_value_ID is not null\r\n" + 
			"union all \r\n" + 
			"select * from(select \r\n" + 
			"a.Emp_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.Pay_Add_Deduct_Type_Code ,null)) \r\n" + 
			"as tot_basic_var_add_per_full_ID,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary',  a.Amount ,null)) \r\n" + 
			"as tot_basic_var_add_per_full\r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID)a where tot_basic_var_add_per_full_ID is not null\r\n" + 
			"union all\r\n" + 
			"select * from(select \r\n" + 
			"a.Emp_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Pay_Add_Deduct_Type_Code ,null)) \r\n" + 
			"as tot_gross_var_add_per_full_ID,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,null)) \r\n" + 
			"as tot_gross_var_add_per_full\r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID)a where tot_gross_var_add_per_full_ID is not null\r\n" + 
			"-- deductions\r\n" + 
			"union all\r\n" + 
			"select * from(select \r\n" + 
			"a.Emp_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code ,null)) as tot_ded_var_value_ID,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', a.Amount ,null)) as tot_ded_var_value\r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID)a where tot_ded_var_value_ID is not null\r\n" + 
			"union all\r\n" + 
			"select * from(select \r\n" + 
			"a.Emp_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.Pay_Add_Deduct_Type_Code ,null)) \r\n" + 
			"as tot_basic_var_ded_per_full_ID,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', a.Amount ,null)) \r\n" + 
			"as tot_basic_var_ded_per_full\r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID)a where tot_basic_var_ded_per_full_ID is not null\r\n" + 
			"union all\r\n" + 
			"select * from(select \r\n" + 
			"a.Emp_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Pay_Add_Deduct_Type_Code ,null))	\r\n" + 
			"as tot_gross_var_ded_per_full_ID, \r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,null))	\r\n" + 
			"as tot_gross_var_ded_per_full \r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID)a where tot_gross_var_ded_per_full_ID is not null\r\n" + 
			"-- others\r\n" + 
			"union all\r\n" + 
			"select * from(select \r\n" + 
			"a.Emp_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.Pay_Add_Deduct_Type_Code ,null)) as tot_oth_var_value_ID, \r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', a.Amount ,null)) as tot_oth_var_value\r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID)a where tot_oth_var_value_ID is not null\r\n" + 
			"union all \r\n" + 
			"select * from(select \r\n" + 
			"a.Emp_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.Pay_Add_Deduct_Type_Code ,null)) \r\n" + 
			"as tot_basic_var_oth_per_full_ID,  \r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', a.Amount ,null)) \r\n" + 
			"as tot_basic_var_oth_per_full\r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID)a where tot_basic_var_oth_per_full_ID is not null\r\n" + 
			"union all\r\n" + 
			"select * from(select \r\n" + 
			"a.Emp_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary',  c.Pay_Add_Deduct_Type_Code ,null))	\r\n" + 
			"as tot_gross_var_oth_per_full_ID,   \r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,null))	\r\n" + 
			"as tot_gross_var_oth_per_full \r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID  \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" + 
			"inner join pay_codes f on a.Pay_Code_ID = f.Pay_Code_ID \r\n" + 
			"where f.Pay_Code_ID =:Pay_Code_ID)a where tot_gross_var_oth_per_full_ID is not null;",nativeQuery=true)
	public String[][] sampleSave(@Param("Pay_Code_ID")String empID);
	
	@Query(value = "select * from(select\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary',  c.Pay_Add_Deduct_Type_Code ,null)) \r\n" + 
			"as dedPerGrossID,			\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Add_Deduct_Value ,null)) \r\n" + 
			"as dedPerGross\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID)a where dedPerGrossID is not null "
			+ "group by dedPerGrossID",nativeQuery=true)
	public String[][] dedGrossPerCal();
	
	@Query(value="select * from(select\r\n" + 
			"e.Employee_ID as empId,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary',  c.Pay_Add_Deduct_Type_Code ,null))  \r\n" + 
			"as addPerGrossID, \r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary',  c.Add_Deduct_Value ,null))  \r\n" + 
			"as addPerGross\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID)a where addPerGrossID is not null\r\n" + 
			"group by addPerGrossID",nativeQuery=true)
	public String[][] addGrossPerCal();
	
	@Query(value="select * from(select\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Pay_Add_Deduct_Type_Code ,null)) \r\n" + 
			"as othPerGrossID,				\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Add_Deduct_Value ,null)) \r\n" + 
			"as othPerGross\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID)a where othPerGrossID is not null\r\n" + 
			"group by othPerGrossID",nativeQuery=true)
	public String[][] otherGrossPerCal();
	
	@Query(value = "select\n" + 
			"emp, sum(grossTotVal) + basicSalary as calPri\n" + 
			"from(select\n" + 
			"e.Employee_ID as emp, d.Name as empName, e.basicSalary as basicSalary,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', c.Add_Deduct_Value ,0)) \n" + 
			"as grossTotVal\n" + 
			"from employee_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Employee_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID group by emp\n" + 
			"union all\n" + 
			"select\n" + 
			"a.Emp_ID as emp, d.Name as empName, e.basicSalary as basicSalary,\n" + 
			"sum(if(c.Add_Deduct_Status = 'addition' and c.Is_On_Basic_Salary = 'grossSalary' and c.Is_Percentage = 'value', a.Amount ,0)) \n" + 
			"as  grossTotValVar\n" + 
			"from emp_month_salary_details a\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\n" + 
			"inner join employee_master d on e.Employee_ID = d.Employee_ID group by emp)a group by emp order by emp", nativeQuery=true)
	public String[][] calPriEmpList();

}
