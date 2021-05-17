package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.SalaryAnalyze;

public interface SalaryAnalyzeRepository extends JpaRepository<SalaryAnalyze, String> {

	@Query(value="SELECT (max(cm.saID)+1) FROM SalaryAnalyze cm")
	public String getMaxSaID();
	
	@Query(value = "select a.Pay_Add_Deduct_Type_Code from pay_add_deduct_types a",nativeQuery=true)
	public String[][] getListOfAllowances();
	
	@Query(value = "CALL salary_Analyze_Table_Header()",nativeQuery=true)
	public String[][] getSalaryAnalyzeTableHeaderData();
	
	@Query(value = "CALL salary_Analyze_Table_Data()",nativeQuery=true)
	public String[][] getSalaryAnalyzeTableBodyData();
	
//	@Query(value="select d.Department,\r\n" + 
//			"sum(case when e.Description like 'Bud%' then (b.Amount) else null end) as Budgatery,\r\n" + 
//			"sum(case when e.Description like 'Attendance%' then (b.Amount) else null end) as Attendance,\r\n" + 
//			"sum(case when e.Description like 'Risk %' then (b.Amount) else null end) as Risk,\r\n" + 
//			"sum(case when e.Description like 'Performance%' then (b.Amount) else null end) as Performance,\r\n" + 
//			"sum(case when e.Description like 'Night%' then (b.Amount) else null end) as Night,\r\n" + 
//			"sum(case when e.Description like 'Target%' then (b.Amount) else null end) as Target,\r\n" + 
//			"sum(case when e.Description like 'Trainee%' then (b.Amount) else null end) as Trainee,\r\n" + 
//			"sum(case when e.Description like 'Other%' then (b.Amount) else null end) as Other,\r\n" + 
//			"sum(case when e.Description like 'Rigger%' then (b.Amount) else null end) as Rigger,\r\n" + 
//			"sum(case when e.Description like 'Sales Commission%' then (b.Amount) else null end) as SalesCommission,\r\n" + 
//			"sum(case when e.Description like 'Transport%' then (b.Amount) else null end) as Transport,\r\n" + 
//			"sum(case when e.Description like 'Site%' then (b.Amount) else null end) as Site,\r\n" + 
//			"sum(case when e.Description like 'nopay%' then (b.Amount) else null end) as nopay,\r\n" + 
//			"sum(case when e.Description like 'Festival Advance%' then (b.Amount) else null end) as FestivalAdvance,\r\n" + 
//			"sum(case when e.Description like 'Insurance%' then (b.Amount) else null end) as Insurance,\r\n" + 
//			"sum(case when e.Description like 'Mobile Phone%' then (b.Amount) else null end) as MobilePhone,\r\n" + 
//			"sum(case when e.Description like 'Salary Advance%' then (b.Amount) else null end) as SalaryAdvance,\r\n" + 
//			"sum(case when e.Description like 'EPF 8%' then (b.Amount) else null end) as EPF_8,\r\n" + 
//			"sum(case when e.Description like 'Welfare Fund%' then (b.Amount) else null end) as WelfareFund,\r\n" + 
//			"sum(case when e.Description like 'laptop%' then (b.Amount) else null end) as Laptop,\r\n" + 
//			"sum(case when e.Description like 'bike%' then (b.Amount) else null end) as Bike,\r\n" + 
//			"sum(case when e.Description like 'PM%' then (b.Amount) else null end) as PM,\r\n" + 
//			"sum(case when e.Description like 'EPF 12%' then (b.Amount) else null end) as EPF_12,\r\n" + 
//			"sum(case when e.Description like 'EPF 3%' then (b.Amount) else null end) as EPF_3\r\n" + 
//			"from salary_analyze a\r\n" + 
//			"inner join process_payroll_details b on a.Pay_Add_Deduct_Type_Code = b.Pay_Add_Deduct_Type_Code\r\n" + 
//			"inner join department d on a.Department_ID = d.Department_ID \r\n" + 
//			"inner join pay_add_deduct_types e on a.Pay_Add_Deduct_Type_Code = e.Pay_Add_Deduct_Type_Code\r\n" + 
//			"group by a.Department_ID",nativeQuery=true)
//	public String[][] getSalaryAnalizerTableData02();
	
//	@Query(value="select * from(select 'Department' as department,\r\n" + 
//			"max(case when e.Description like 'Bud%' then e.Description else null end) as Budgatery,\r\n" + 
//			"max(case when e.Description like 'Attendance%' then e.Description else null end) as Attendance,\r\n" + 
//			"max(case when e.Description like 'Risk %' then e.Description else null end) as Risk,\r\n" + 
//			"max(case when e.Description like 'Performance%' then e.Description else null end) as Performance,\r\n" + 
//			"max(case when e.Description like 'Night%' then e.Description else null end) as Night,\r\n" + 
//			"max(case when e.Description like 'Target%' then e.Description else null end) as Target,\r\n" + 
//			"max(case when e.Description like 'Trainee%' then e.Description else null end) as Trainee,\r\n" + 
//			"max(case when e.Description like 'Other%' then e.Description else null end) as Other,\r\n" + 
//			"max(case when e.Description like 'Rigger%' then e.Description else null end) as Rigger,\r\n" + 
//			"max(case when e.Description like 'Sales Commission%' then e.Description else null end) as SalesCommission,\r\n" + 
//			"max(case when e.Description like 'Transport%' then e.Description else null end) as Transport,\r\n" + 
//			"max(case when e.Description like 'Site%' then e.Description else null end) as Site,\r\n" + 
//			"max(case when e.Description like 'nopay%' then e.Description else null end) as nopay,\r\n" + 
//			"max(case when e.Description like 'Festival Advance%' then e.Description else null end) as FestivalAdvance,\r\n" + 
//			"max(case when e.Description like 'Insurance%' then e.Description else null end) as Insurance,\r\n" + 
//			"max(case when e.Description like 'Mobile Phone%' then e.Description else null end) as MobilePhone,\r\n" + 
//			"max(case when e.Description like 'Salary Advance%' then e.Description else null end) as SalaryAdvance,\r\n" + 
//			"max(case when e.Description like 'EPF 8%' then e.Description else null end) as EPF_8,\r\n" + 
//			"max(case when e.Description like 'Welfare Fund%' then e.Description else null end) as WelfareFund,\r\n" + 
//			"max(case when e.Description like 'laptop%' then e.Description else null end) as Laptop,\r\n" + 
//			"max(case when e.Description like 'bike%' then e.Description else null end) as Bike,\r\n" + 
//			"max(case when e.Description like 'PM%' then e.Description else null end) as PM,\r\n" + 
//			"max(case when e.Description like 'EPF 12%' then e.Description else null end) as EPF_12,\r\n" + 
//			"max(case when e.Description like 'EPF 3%' then e.Description else null end) as EPF_3\r\n" + 
//			"from salary_analyze a\r\n" + 
//			"inner join process_payroll_details b on a.Pay_Add_Deduct_Type_Code = b.Pay_Add_Deduct_Type_Code\r\n" + 
//			"inner join department d on a.Department_ID = d.Department_ID \r\n" + 
//			"inner join pay_add_deduct_types e on a.Pay_Add_Deduct_Type_Code = e.Pay_Add_Deduct_Type_Code) b\r\n" + 
//			"group by department",nativeQuery=true)
//	public String[][] getSalaryAnalizerTableData02Header();
	
//	@Query(value="select * from(select 'Department' as department,\r\n" + 
//			"max(case when e.Description like 'Bud%' then e.Description else null end) as Budgatery,\r\n" + 
//			"max(case when e.Description like 'Attendance%' then e.Description else null end) as Attendance,\r\n" + 
//			"max(case when e.Description like 'Risk %' then e.Description else null end) as Risk,\r\n" + 
//			"max(case when e.Description like 'Performance%' then e.Description else null end) as Performance,\r\n" + 
//			"max(case when e.Description like 'Night%' then e.Description else null end) as Night,\r\n" + 
//			"max(case when e.Description like 'Target%' then e.Description else null end) as Target,\r\n" + 
//			"max(case when e.Description like 'Trainee%' then e.Description else null end) as Trainee,\r\n" + 
//			"max(case when e.Description like 'Other%' then e.Description else null end) as Other,\r\n" + 
//			"max(case when e.Description like 'Rigger%' then e.Description else null end) as Rigger,\r\n" + 
//			"max(case when e.Description like 'Sales Commission%' then e.Description else null end) as SalesCommission,\r\n" + 
//			"max(case when e.Description like 'Transport%' then e.Description else null end) as Transport,\r\n" + 
//			"max(case when e.Description like 'Site%' then e.Description else null end) as Site,\r\n" + 
//			"max(case when e.Description like 'nopay%' then e.Description else null end) as nopay,\r\n" + 
//			"max(case when e.Description like 'Festival Advance%' then e.Description else null end) as FestivalAdvance,\r\n" + 
//			"max(case when e.Description like 'Insurance%' then e.Description else null end) as Insurance,\r\n" + 
//			"max(case when e.Description like 'Mobile Phone%' then e.Description else null end) as MobilePhone,\r\n" + 
//			"max(case when e.Description like 'Salary Advance%' then e.Description else null end) as SalaryAdvance,\r\n" + 
//			"max(case when e.Description like 'EPF 8%' then e.Description else null end) as EPF_8,\r\n" + 
//			"max(case when e.Description like 'Welfare Fund%' then e.Description else null end) as WelfareFund,\r\n" + 
//			"max(case when e.Description like 'laptop%' then e.Description else null end) as Laptop,\r\n" + 
//			"max(case when e.Description like 'bike%' then e.Description else null end) as Bike,\r\n" + 
//			"max(case when e.Description like 'PM%' then e.Description else null end) as PM,\r\n" + 
//			"max(case when e.Description like 'EPF 12%' then e.Description else null end) as EPF_12,\r\n" + 
//			"max(case when e.Description like 'EPF 3%' then e.Description else null end) as EPF_3\r\n" + 
//			"from salary_analyze a\r\n" + 
//			"inner join process_payroll_details b on a.Pay_Add_Deduct_Type_Code = b.Pay_Add_Deduct_Type_Code\r\n" + 
//			"inner join department d on a.Department_ID = d.Department_ID \r\n" + 
//			"inner join pay_add_deduct_types e on a.Pay_Add_Deduct_Type_Code = e.Pay_Add_Deduct_Type_Code) b\r\n" + 
//			"group by department\r\n" + 
//			"union all\r\n" + 
//			"select d.Department,\r\n" + 
//			"sum(case when e.Description like 'Bud%' then (b.Amount) else null end) as Budgatery,\r\n" + 
//			"sum(case when e.Description like 'Attendance%' then (b.Amount) else null end) as Attendance,\r\n" + 
//			"sum(case when e.Description like 'Risk %' then (b.Amount) else null end) as Risk,\r\n" + 
//			"sum(case when e.Description like 'Performance%' then (b.Amount) else null end) as Performance,\r\n" + 
//			"sum(case when e.Description like 'Night%' then (b.Amount) else null end) as Night,\r\n" + 
//			"sum(case when e.Description like 'Target%' then (b.Amount) else null end) as Target,\r\n" + 
//			"sum(case when e.Description like 'Trainee%' then (b.Amount) else null end) as Trainee,\r\n" + 
//			"sum(case when e.Description like 'Other%' then (b.Amount) else null end) as Other,\r\n" + 
//			"sum(case when e.Description like 'Rigger%' then (b.Amount) else null end) as Rigger,\r\n" + 
//			"sum(case when e.Description like 'Sales Commission%' then (b.Amount) else null end) as SalesCommission,\r\n" + 
//			"sum(case when e.Description like 'Transport%' then (b.Amount) else null end) as Transport,\r\n" + 
//			"sum(case when e.Description like 'Site%' then (b.Amount) else null end) as Site,\r\n" + 
//			"sum(case when e.Description like 'nopay%' then (b.Amount) else null end) as nopay,\r\n" + 
//			"sum(case when e.Description like 'Festival Advance%' then (b.Amount) else null end) as FestivalAdvance,\r\n" + 
//			"sum(case when e.Description like 'Insurance%' then (b.Amount) else null end) as Insurance,\r\n" + 
//			"sum(case when e.Description like 'Mobile Phone%' then (b.Amount) else null end) as MobilePhone,\r\n" + 
//			"sum(case when e.Description like 'Salary Advance%' then (b.Amount) else null end) as SalaryAdvance,\r\n" + 
//			"sum(case when e.Description like 'EPF 8%' then (b.Amount) else null end) as EPF_8,\r\n" + 
//			"sum(case when e.Description like 'Welfare Fund%' then (b.Amount) else null end) as WelfareFund,\r\n" + 
//			"sum(case when e.Description like 'laptop%' then (b.Amount) else null end) as Laptop,\r\n" + 
//			"sum(case when e.Description like 'bike%' then (b.Amount) else null end) as Bike,\r\n" + 
//			"sum(case when e.Description like 'PM%' then (b.Amount) else null end) as PM,\r\n" + 
//			"sum(case when e.Description like 'EPF 12%' then (b.Amount) else null end) as EPF_12,\r\n" + 
//			"sum(case when e.Description like 'EPF 3%' then (b.Amount) else null end) as EPF_3\r\n" + 
//			"from salary_analyze a\r\n" + 
//			"inner join process_payroll_details b on a.Pay_Add_Deduct_Type_Code = b.Pay_Add_Deduct_Type_Code\r\n" + 
//			"inner join department d on a.Department_ID = d.Department_ID \r\n" + 
//			"inner join pay_add_deduct_types e on a.Pay_Add_Deduct_Type_Code = e.Pay_Add_Deduct_Type_Code\r\n" + 
//			"group by a.Department_ID",nativeQuery=true)
//	public String[][] salaryAnalizeReportData();
	
	// begin of fixed transactional details report
	@Query(value="select * from (\n" + 
			"select a.Employee_ID as empID, a.Employee_ID as tempEmpID, \n" + 
			"'' as fixAllDesc, b.name as fname, b.lastname as lname, \n" + 
			"'' as amount,'' as fixAlloID\n" + 
			"from process_payroll_details a\n" + 
			"inner join employee_master b on a.Employee_ID = b.Employee_ID\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join company_master d on a.Company_ID = d.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and a.Employee_ID =:Employee_ID group by empID\n" + 
			"union all\n" + 
			"select a.Employee_ID as empID, '' as tempEmpID,  \n" + 
			"(case when c.Add_Deduct_Type = 'fixedType' then c.Description else null end) as fixAllDesc,\n" + 
			"c.Calculation_Priority_Seq as calPriority, c.Add_Deduct_Value as relatedValues,\n" + 
			"a.Amount as amount,\n" + 
			"(case when c.Add_Deduct_Type = 'fixedType' then c.Pay_Add_Deduct_Type_Code else null end) as fixAlloID\n" + 
			"from process_payroll_details a\n" + 
			"inner join employee_master b on a.Employee_ID = b.Employee_ID\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join company_master d on a.Company_ID = d.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and a.Employee_ID =:Employee_ID) a where fixAlloID is not null;",nativeQuery=true)
	public String[][] getFTDataRelatedEmployee(@Param("Employee_ID")String empID,@Param("Company_ID")String comID);
	
	@Query(value="SELECT empID, tempID, colNo, fixAllDesc, max(fname), max(lname),'' as final_amount, fixAlloID \n" + 
			"from (select a.Employee_ID as empID,  a.Employee_ID as tempID, '' as colNo, \n" + 
			"'' as fixAllDesc, '' as fname, '' as lname, '' as amount, '' as fixAlloID\n" + 
			"from process_payroll_details a\n" + 
			"inner join company_master d on a.Company_ID = d.Company_ID \n" + 
			"where a.Company_ID =:Company_ID group by tempID\n" + 
			"UNION ALL \n" + 
			"SELECT \n" + 
			"a.Employee_ID as empID, '' as tempID, '01' as colNo, '' as fixAllDesc, b.name as fname, b.lastname as lname,\n" + 
			"'' as amount, '' as fixAlloID\n" + 
			"from process_payroll_details a \n" + 
			"inner join employee_master b on a.Employee_ID = b.Employee_ID \n" + 
			"inner join company_master d on a.Company_ID = d.Company_ID where a.Company_ID =:Company_ID\n" + 
			"group by empID) b group by empID\n" + 
			"UNION ALL\n" + 
			"SELECT * from (select a.Employee_ID as empID, '' as tempID, '02' as colNo, \n" + 
			"(case when c.Add_Deduct_Type = 'fixedType' then c.Description else null end) as fixAllDesc,\n" + 
			"c.Calculation_Priority_Seq as calPriority, c.Add_Deduct_Value as relatedValues,\n" + 
			"a.Amount as final_amount,\n" + 
			"(case when c.Add_Deduct_Type = 'fixedType' then c.Pay_Add_Deduct_Type_Code else null end) as fixAlloID\n" + 
			"from process_payroll_details a\n" + 
			"inner join employee_master b on a.Employee_ID = b.Employee_ID \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join company_master d on a.Company_ID = d.Company_ID where a.Company_ID =:Company_ID)a \n" + 
			"where fixAlloID is not null order by empID,colNo;",nativeQuery=true)
	public String[][] getAllEmpFTBodyData(@Param("Company_ID")String comID);
	
	@Query(value="SELECT empID, tempID, colNo, fixAllDesc, max(fname), max(lname), '' as final_amount, fixAlloID \n" + 
			"from (\n" + 
			"select a.Employee_ID as empID,  a.Employee_ID as tempID, '' as colNo, \n" + 
			"'' as fixAllDesc, '' as fname, '' as lname, '' as amount, '' as fixAlloID\n" + 
			"from process_payroll_details a\n" + 
			"inner join company_master d on a.Company_ID = d.Company_ID \n" + 
			"inner join employee_master b on a.Employee_ID = b.Employee_ID\n" + 
			"inner join employee_details e on b.Employee_ID = e.Employee_ID\n" + 
			"inner join department f on e.Department_ID = f.Department_ID\n" + 
			"where a.Company_ID =:Company_ID and e.Department_ID =:Department_ID group by tempID\n" + 
			"UNION ALL \n" + 
			"SELECT \n" + 
			"a.Employee_ID as empID, '' as tempID, '01' as colNo, '' as fixAllDesc, b.name as fname, b.lastname as lname,\n" + 
			"'' as amount, '' as fixAlloID\n" + 
			"from process_payroll_details a \n" + 
			"inner join employee_master b on a.Employee_ID = b.Employee_ID \n" + 
			"inner join employee_details e on b.Employee_ID = e.Employee_ID\n" + 
			"inner join department f on e.Department_ID = f.Department_ID\n" + 
			"inner join company_master d on a.Company_ID = d.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and e.Department_ID =:Department_ID group by empID) b group by empID\n" + 
			"UNION ALL\n" + 
			"SELECT * from (select a.Employee_ID as empID, '' as tempID, '02' as colNo, \n" + 
			"(case when c.Add_Deduct_Type = 'fixedType' then c.Description else null end) as fixAllDesc,\n" + 
			"c.Calculation_Priority_Seq as calPriority, c.Add_Deduct_Value as relatedValues,\n" + 
			"a.Amount as final_amount,\n" + 
			"(case when c.Add_Deduct_Type = 'fixedType' then c.Pay_Add_Deduct_Type_Code else null end) as fixAlloID\n" + 
			"from process_payroll_details a\n" + 
			"inner join employee_master b on a.Employee_ID = b.Employee_ID \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join employee_details e on b.Employee_ID = e.Employee_ID\n" + 
			"inner join department f on e.Department_ID = f.Department_ID\n" + 
			"inner join company_master d on a.Company_ID = d.Company_ID \n" + 
			"where a.Company_ID =:Company_ID and e.Department_ID =:Department_ID)a \n" + 
			"where fixAlloID is not null order by empID,colNo;",nativeQuery=true)
	public String[][] GetDataToFTDRRelatedDepartment(@Param("Department_ID")String depID,@Param("Company_ID")String comID);
	
	@Query(value="SELECT empID, tempID, colNo, fixAllDesc, max(fname), max(lname), '' as final_amount, fixAlloID \n" + 
			"from (\n" + 
			"select a.Employee_ID as empID,  a.Employee_ID as tempID, '' as colNo, \n" + 
			"'' as fixAllDesc, '' as fname, '' as lname, '' as amount, '' as fixAlloID\n" + 
			"from process_payroll_details a\n" + 
			"inner join company_master d on a.Company_ID = d.Company_ID \n" + 
			"inner join employee_master b on a.Employee_ID = b.Employee_ID\n" + 
			"inner join employee_details e on b.Employee_ID = e.Employee_ID\n" + 
			"inner join department f on e.Department_ID = f.Department_ID\n" + 
			"where a.Company_ID =:Company_ID group by tempID\n" + 
			"UNION ALL \n" + 
			"SELECT \n" + 
			"a.Employee_ID as empID, '' as tempID, '01' as colNo, '' as fixAllDesc, b.name as fname, b.lastname as lname,\n" + 
			"'' as amount, '' as fixAlloID\n" + 
			"from process_payroll_details a \n" + 
			"inner join employee_master b on a.Employee_ID = b.Employee_ID \n" + 
			"inner join company_master d on a.Company_ID = d.Company_ID \n" + 
			"inner join employee_details e on b.Employee_ID = e.Employee_ID\n" + 
			"inner join department f on e.Department_ID = f.Department_ID\n" + 
			"where a.Company_ID =:Company_ID\n" + 
			"group by empID) b group by empID\n" + 
			"UNION ALL\n" + 
			"SELECT * from (select a.Employee_ID as empID, '' as tempID, '02' as colNo, \n" + 
			"(case when c.Add_Deduct_Type = 'fixedType' then c.Description else null end) as fixAllDesc,\n" + 
			"c.Calculation_Priority_Seq as calPriority, c.Add_Deduct_Value as relatedValues,\n" + 
			"a.Amount as final_amount,\n" + 
			"(case when c.Add_Deduct_Type = 'fixedType' then c.Pay_Add_Deduct_Type_Code else null end) as fixAlloID\n" + 
			"from process_payroll_details a\n" + 
			"inner join employee_master b on a.Employee_ID = b.Employee_ID \n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\n" + 
			"inner join company_master d on a.Company_ID = d.Company_ID\n" + 
			"inner join employee_details e on b.Employee_ID = e.Employee_ID \n" + 
			"inner join department f on e.Department_ID = f.Department_ID\n" + 
			"where a.Company_ID =:Company_ID)a \n" + 
			"where fixAlloID is not null order by empID,colNo;",nativeQuery=true)
	public String[][] getDataRelatedAllDepartments(@Param("Company_ID")String comID);
	// end of fixed transactional details report
}
