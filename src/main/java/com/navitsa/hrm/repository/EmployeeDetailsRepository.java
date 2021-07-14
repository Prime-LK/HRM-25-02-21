package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.EmployeeDetailsPK;

public interface EmployeeDetailsRepository extends CrudRepository<EmployeeDetails, EmployeeDetailsPK> {

	@Query(value = "SELECT (max(ed.detailsPK.detailID)+1) FROM EmployeeDetails ed")
	public String maxDetailsID();
	
	@Query(value="SELECT ed FROM EmployeeDetails ed WHERE ed.location.loid=:loid")
	public List<EmployeeDetails> getEMpRelatedLocation(@Param("loid") String loid);
	
	@Query(value="SELECT ed FROM EmployeeDetails ed WHERE ed.detailsPK.empID.empID=:empID")
	public EmployeeDetails editDetails(@Param("empID") String empID);
	
	@Query(value="SELECT ed FROM EmployeeDetails ed WHERE ed.empType.tid=:tid")
	public List<EmployeeDetails> getEmpRelatedType(@Param("tid") String tid);
	
	@Query(value="SELECT ed FROM EmployeeDetails ed WHERE ed.category.catgoryID=:catgoryID")
	public List<EmployeeDetails> getEmpRelatedCategory(@Param("catgoryID") String catgoryID);
	
	@Query(value="SELECT er FROM EmployeeDetails er WHERE er.department.depID=:depID")
	public List<EmployeeDetails> filterEmp(@Param("depID") String depID);
	
	@Query(value="SELECT er FROM EmployeeDetails er WHERE  er.department.depID=:depID")
	public List<EmployeeDetails> passEmpRelatedDep(@Param("depID") String depID);
	
	@Query(value="SELECT dr.detailsPK.empID.empID as empid, dr.detailsPK.empID.name as fname,"
			+ " dr.detailsPK.empID.lastname as lname, dr.detailsPK.empID.contact_num1 as contact,"
			+ " dr.detailsPK.empID.email as email, DATE_FORMAT(dr.detailsPK.empID.dob,'%d/%m/%Y') as dob,"
			+ " dr.department.department as dep, dr.detailsPK.empID.contact_num2 as contact2, dr.detailsPK.empID.emergency_Contact_No as contact3 , dr.designation.designation as empDesignation ,  dr.joinedDate as empJoinedDate , dr.resignDate as empResignedDate , dr.status as estatus FROM EmployeeDetails dr WHERE dr.department.depID=:depID")
	public String[][] getCensusReportDetails(@Param("depID") String depID);
	
	@Query(value = "select ed.Employee_ID as empid, em.name as fname, em.lastname as lname, dtm.Dependent_Type as dtype, edt.Name as dname,\r\n" + 
			" DATE_FORMAT(edt.DOB,'%d/%m/%Y') as dDob, edt.Contact_No as contact\r\n" + 
			"from employee_details ed \r\n" + 
			"inner join employee_master em on em.Employee_ID = ed.Employee_ID \r\n" + 
			"inner join emp_dependents edt on edt.Employee_ID = em.Employee_ID\r\n" + 
			"inner join dependent_type_master dtm on dtm.Dependent_Type_ID = edt.Dependent_Type_ID\r\n" + 
			" where ed.Department_ID =:Department_ID", nativeQuery=true)
	public String[][] filterEmpDependents(@Param("Department_ID") String Department_ID);
	
	@Query(value="select * from employee_details ed \r\n" + 
			"inner join employee_master em on em.Employee_ID = ed.Employee_ID\r\n" + 
			"inner join emp_dependents edt on edt.Employee_ID = em.Employee_ID\r\n" + 
			"inner join dependent_type_master dtm on dtm.Dependent_Type_ID = edt.Dependent_Type_ID\r\n" + 
			"where ed.Department_ID=:Department_ID", nativeQuery=true)
	public List<EmployeeDetails> loadFilteringData(@Param("Department_ID") String Department_ID);
	
	//get details to contact report
	
	@Query(value="SELECT er.detailsPK.empID.empID as empid , er.detailsPK.empID.name as fname , er.detailsPK.empID.lastname as lname , er.detailsPK.empID.address as empAdd , er.detailsPK.empID.contact_num1 as contact_h , er.detailsPK.empID.contact_num2 as contact_c , er.detailsPK.empID.email as empemail , er.detailsPK.empID.city as empcity , er.detailsPK.empID.state as empstate  FROM EmployeeDetails er WHERE  er.department.depID=:depID")
	public String[][] filterContact(@Param("depID") String depID);
	
	
	@Query(value="SELECT er FROM EmployeeDetails er WHERE  er.department.depID=:depID")
	public List<EmployeeDetails> filteremp(@Param("depID") String depID);
	
	//get employee according to joined date 
	@Query(value="SELECT er FROM EmployeeDetails er WHERE er.joinedDate BETWEEN  :joinedDate AND :joinedDate2")
	public List<EmployeeDetails> filterEmpdoj(@Param("joinedDate") String joinedDate , @Param("joinedDate2") String joinedDate2);
	
	//get report based on joined date 
	@Query(value="SELECT er.detailsPK.empID.empID as empid, er.detailsPK.empID.name as fname , er.detailsPK.empID.lastname as lname ,er.detailsPK.empID.contact_num1 as contact , er.detailsPK.empID.email as email, DATE_FORMAT(er.detailsPK.empID.dob,'%d/%m/%Y') as dob, er.department.department as dep, er.detailsPK.empID.contact_num2 as contact2, er.detailsPK.empID.emergency_Contact_No as contact3 , er.designation.designation as empDesignation ,er.joinedDate as empJoinedDate , er.resignDate as empResignedDate , er.status as estatus FROM EmployeeDetails er WHERE er.joinedDate BETWEEN  :joinedDate AND :joinedDate2")
	public  String[][]  filterEmpdojforReport(@Param("joinedDate") String joinedDate , @Param("joinedDate2") String joinedDate2);
	
	//get employee based on resigned date on jsp
	@Query(value="SELECT er FROM EmployeeDetails er WHERE er.resignDate BETWEEN  :resignDate AND :resignDate2")
	public List<EmployeeDetails> filterEmpdateofresign(@Param("resignDate") String resignDate , @Param("resignDate2") String resignDate2);
	
	//get employee based on resigned date to report
	@Query(value="SELECT er.detailsPK.empID.empID as empid, er.detailsPK.empID.name as fname , er.detailsPK.empID.lastname as lname ,er.detailsPK.empID.contact_num1 as contact , er.detailsPK.empID.email as email, DATE_FORMAT(er.detailsPK.empID.dob,'%d/%m/%Y') as dob, er.department.department as dep, er.detailsPK.empID.contact_num2 as contact2, er.detailsPK.empID.emergency_Contact_No as contact3 , er.designation.designation as empDesignation ,er.joinedDate as empJoinedDate , er.resignDate as empResignedDate , er.status as estatus FROM EmployeeDetails er WHERE er.resignDate BETWEEN  :resignDate AND :resignDate2")
	public  String[][]  filterEmpdateOfResignDate(@Param("resignDate") String resignDate , @Param("resignDate2") String resignDate2);
	
	//get related employee based on Department to employee salary details jsp
	@Query(value="SELECT er FROM EmployeeDetails er WHERE er.department.depID=:depID")
	public List<EmployeeDetails> filterEmpbasedONDep(@Param("depID") String depID);
	
	//get employee based on location to employee salary details
	@Query(value="SELECT er FROM EmployeeDetails er WHERE er.location.loid=:loid")
	public List<EmployeeDetails> filterEmpbasedONLocation(@Param("loid") String loid);
	
	//get employee based on category to employee salary details
	@Query(value="SELECT er FROM EmployeeDetails er WHERE er.category.catgoryID=:catgoryID")
	public List<EmployeeDetails> filterEmpbasedONCategory(@Param("catgoryID") String catgoryID);
	
	//get employee based on types to employee salary details
	@Query(value="SELECT er FROM EmployeeDetails er WHERE er.empType.tid=:tid")
	public List<EmployeeDetails> filterEmpbasedONtypes(@Param("tid") String tid);
	
	//get employee  to employee salary details
	@Query(value="SELECT er FROM EmployeeDetails er WHERE er.detailsPK.empID.empID=:empID")
	public List<EmployeeDetails> filterEmployee(@Param("empID") String empID);
	
	
	//get all employee for employee salary details 
    @Query(value = "SELECT e FROM EmployeeDetails e")
	public List<EmployeeDetails> loadAllEmployee();
	
    
    //get all employee
    @Query(value="SELECT er.detailsPK.empID.empID as empid, er.detailsPK.empID.name as fname , er.detailsPK.empID.lastname as lname ,er.detailsPK.empID.contact_num1 as contact , er.detailsPK.empID.email as email, DATE_FORMAT(er.detailsPK.empID.dob,'%d/%m/%Y') as dob, er.designation.designation as empDesignation ,er.joinedDate as empJoinedDate , er.resignDate as empResignedDate , er.status as estatus FROM EmployeeDetails er")
	public String[][] getReportData();
	
	@Query(value = "select\r\n" + 
			"emp_count,emp_Name,basic_sum,\r\n" + 
			"tot_basic_add_per_desc,tot_basic_add_per_full ,\r\n" + 
			"tot_gross_add_per_desc,tot_gross_add_per_full,\r\n" + 
			"tot_add_fx_desc,tot_add_fx_value,\r\n" + 
			"tot_add_var_value_desc,tot_add_var_value,\r\n" + 
			"tot_basic_var_add_per_desc,tot_basic_var_add_per_full, \r\n" + 
			"tot_gross_var_add_per_desc, tot_gross_var_add_per_full,\r\n" + 
			"tot_basic_ded_per_desc,tot_basic_ded_per_full,\r\n" + 
			"tot_gross_ded_per_desc, tot_gross_ded_per_full,\r\n" + 
			"tot_ded_fx_value_desc,tot_ded_fx_value, \r\n" + 
			"tot_ded_var_value_desc,tot_ded_var_value, \r\n" + 
			"tot_basic_var_ded_per_desc,tot_basic_var_ded_per_full, \r\n" + 
			"tot_gross_var_ded_per_desc,tot_gross_var_ded_per_full,\r\n" + 
			"tot_oth_fx_value_desc,tot_oth_fx_value,\r\n" + 
			"tot_gross_oth_per_desc,tot_gross_oth_per_full,\r\n" + 
			"tot_basic_oth_per_desc,tot_basic_oth_per_full,\r\n" + 
			"tot_oth_var_value_desc,tot_oth_var_value,\r\n" + 
			"tot_basic_var_oth_per_desc,tot_basic_var_oth_per_full,\r\n" + 
			"tot_gross_var_oth_per_desc,tot_gross_var_oth_per_full	\r\n" + 
			"from\r\n" + 
			"(select \r\n" + 
			"e.Employee_ID as emp_count, \r\n" + 
			"e.basicSalary as basic_sum,\r\n" + 
			"d.Name as emp_Name,\r\n" + 
			"-- ------------------------------------------------------------------------------------------------------------ fx add\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.description ,'')) as tot_add_fx_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', format(c.Add_Deduct_Value,2) ,'')) as tot_add_fx_value, \r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_add_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * c.Add_Deduct_Value / 100),2) ,'')) as tot_basic_add_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_add_per_desc, -- show already\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary',  c.Add_Deduct_Value ,''))  as tot_gross_add_per_full, -- show already\r\n" + 
			"'' as tot_add_var_value_desc,'' as tot_add_var_value,\r\n" + 
			"'' as tot_basic_var_add_per_desc,'' as tot_basic_var_add_per_full,\r\n" + 
			"'' as tot_gross_var_add_per_desc,'' as tot_gross_var_add_per_full,\r\n" + 
			"-- --------------------------------------------------------------------------------------------------------------------- fx ded\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', c.description ,'')) as tot_ded_fx_value_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', format(c.Add_Deduct_Value,2) ,'')) as tot_ded_fx_value,			\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_ded_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * c.Add_Deduct_Value) / 100,2) ,'')) as tot_basic_ded_per_full,			\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_ded_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Add_Deduct_Value ,'')) as tot_gross_ded_per_full, \r\n" + 
			"'' as tot_ded_var_value_desc,'' as tot_ded_var_value,\r\n" + 
			"'' as tot_basic_var_ded_per_desc,'' as tot_basic_var_ded_per_full,\r\n" + 
			"'' as tot_gross_var_ded_per_desc,'' as tot_gross_var_ded_per_full,\r\n" + 
			"-- ---------------------------------------------------------------------------------------------------------------------- fx other\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.description ,'')) as tot_oth_fx_value_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', format(c.Add_Deduct_Value,2) ,'')) as tot_oth_fx_value,			\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_oth_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * c.Add_Deduct_Value) / 100,2) ,'')) as tot_basic_oth_per_full,			\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_oth_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.Add_Deduct_Value ,'')) as tot_gross_oth_per_full,\r\n" + 
			"'' as tot_oth_var_value_desc,'' as tot_oth_var_value,\r\n" + 
			"'' as tot_basic_var_oth_per_desc,'' as tot_basic_var_oth_per_full,\r\n" + 
			"'' as tot_gross_var_oth_per_desc,'' as tot_gross_var_oth_per_full\r\n" + 
			"from employee_salary_details a \r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code \r\n" + 
			"right join employee_details e on a.Employee_ID = e.Employee_ID\r\n" + 
			"left join employee_master d on e.Employee_ID = d.Employee_ID  where a.Employee_ID =:Employee_ID\r\n" + 
			"union\r\n" + 
			"select \r\n" + 
			"a.Emp_ID as emp_count,\r\n" + 
			"e.basicSalary as basic_sum, \r\n" + 
			"b.Name as emp_Name,\r\n" + 
			"-- ------------------------------------------------------------------------------------------------------------------- var add\r\n" + 
			"'' as tot_add_fx_desc,'' as tot_add_fx_value,\r\n" + 
			"'' as tot_basic_add_per_desc,'' as tot_basic_add_per_full,\r\n" + 
			"'' as tot_gross_add_per_desc,'' as tot_gross_add_per_full, \r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value', c.description ,'')) as tot_add_var_value_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'value' ,  a.Amount  ,''))as tot_add_var_value, \r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_var_add_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * a.Amount) / 100,2) ,'')) as tot_basic_var_add_per_full,			\r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_var_add_per_desc, \r\n" + 
			"(if(c.Add_Deduct_Status = 'addition' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,'')) as tot_gross_var_add_per_full,\r\n" + 
			"-- --------------------------------------------------------------------------------------------------------------------- var ded\r\n" + 
			"'' as tot_gross_ded_per_desc,'' as tot_gross_ded_per_full,\r\n" + 
			"'' as tot_basic_ded_per_desc,'' as tot_basic_ded_per_full,\r\n" + 
			"'' as tot_ded_fx_value_desc,'' as tot_ded_fx_value,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', c.description ,'')) as tot_ded_var_value_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'value', a.Amount ,'')) as tot_ded_var_value,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_var_ded_per_desc, \r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * a.Amount) / 100,2) ,'')) as tot_basic_var_ded_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_var_ded_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'deduction' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,'')) as tot_gross_var_ded_per_full,\r\n" + 
			"-- ------------------------------------------------------------------------------------------------------------------- var other\r\n" + 
			"'' as tot_gross_oth_per_desc,'' as tot_gross_oth_per_full,\r\n" + 
			"'' as tot_basic_oth_per_desc,'' as tot_basic_oth_per_full,\r\n" + 
			"'' as tot_oth_fx_value_desc,'' as tot_oth_fx_value,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', c.description ,'')) as tot_oth_var_value_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'value', a.Amount ,'')) as tot_oth_var_value,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', c.description ,'')) as tot_basic_var_oth_per_desc, \r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'basicSalary', format((e.basicSalary * a.Amount) / 100,2) ,'')) as tot_basic_var_oth_per_full,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', c.description ,'')) as tot_gross_var_oth_per_desc,\r\n" + 
			"(if(c.Add_Deduct_Status = 'other' and c.Is_Percentage = 'percentage' and Is_On_Basic_Salary =  'grossSalary', a.Amount ,'')) as tot_gross_var_oth_per_full\r\n" + 
			"from emp_month_salary_details a \r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_master b on a.Emp_ID = b.Employee_ID \r\n" + 
			"inner join employee_details e on a.Emp_ID = e.Employee_ID\r\n" +  
			"where a.Emp_ID =:Employee_ID) a;" ,nativeQuery=true)
	public String[][] processPayRollReport(@Param("Employee_ID")String empID);
	
	@Query(value = "select sum(tot_ded_var_value) \r\n" + 
			"from(select \r\n" + 
			"(if(Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value',  a.Amount + b.basicSalary ,'')) as tot_ded_var_value\r\n" + 
			"from emp_month_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details b on a.Emp_ID = b.Employee_ID\r\n" + 
			"where a.Emp_ID =:Employee_ID\r\n" + 
			"union \r\n" + 
			"select \r\n" + 
			"(if(Is_On_Basic_Salary =  'grossSalary' and c.Is_Percentage = 'value',  c.Add_Deduct_Value ,'')) as tot_ded_fx_value\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types c on a.Pay_Add_Deduct_Type_Code = c.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join employee_details b on a.Employee_ID = b.Employee_ID\r\n" + 
			"where a.Employee_ID =:Employee_ID)a;",nativeQuery=true)
	public String[][] getCalPriForSumReport(@Param("Employee_ID")String empID);
	
	@Query(value="select\r\n" + 
			"b.Description as desci\r\n" + 
			"from employee_salary_details a\r\n" + 
			"inner join pay_add_deduct_types b on a.Pay_Add_Deduct_Type_Code = b.Pay_Add_Deduct_Type_Code\r\n" + 
			"group by desci",nativeQuery=true)
	public String[][] sampleReportData();
	
	@Query(value="select c.Employee_ID as emp_id, c.Name, d.basicSalary,\r\n" + 
			"max(if((e.Description like 'Bud%' or e.Description like 'bud%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount , '')) \r\n" + 
			"as Budgetary_Allowance,\r\n" + 
			"max(if((e.Description like 'Atten%' or e.Description like 'atten%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Attendence_Allowance,\r\n" + 
			"max(if((e.Description like 'Risk%' or e.Description like 'risk%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,''))\r\n" + 
			"as Risk_Allowance,\r\n" + 
			"max(if((e.Description like 'Performan%' or e.Description like 'performan%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount ,'')) \r\n" + 
			"as Performance_Allowance,\r\n" + 
			"max(if((e.Description like 'Night%' or e.Description like 'night%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Night_Allowance,\r\n" + 
			"max(if((e.Description like 'Target%' or e.Description like 'target%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Target_Allowance,\r\n" + 
			"max(if((e.Description like 'Train%' or e.Description like 'trainee%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Trainee_Allowance,\r\n" + 
			"max(if((e.Description like 'Other%' or e.Description like 'other%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Other_Allowance,\r\n" + 
			"max(if((e.Description like 'Rigger%' or e.Description like 'rigger%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Rigger_Allowance,\r\n" + 
			"max(if((e.Description like 'Sales%' or e.Description like 'sales%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Sales_Allowance,\r\n" + 
			"max(if((e.Description like 'Transport%' or e.Description like 'transport%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Transport_Allowance,\r\n" + 
			"max(if((e.Description like 'Site%' or e.Description like 'site%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Site_Allowance,\r\n" + 
			"max(if((e.Description like 'Nopay%' or e.Description like 'nopay%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Nopay_Allowance,\r\n" + 
			"max(if((e.Description like 'Festival%' or e.Description like 'festival%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Festival_Allowance,\r\n" + 
			"max(if((e.Description like 'Insurance%' or e.Description like 'insurance%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Insurance_Allowance,\r\n" + 
			"max(if((e.Description like 'Mobile%' or e.Description like 'mobile%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Mobile_Allowance,\r\n" + 
			"max(if((e.Description like 'Salary%' or e.Description like 'salary%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), b.Amount,'')) \r\n" + 
			"as Salary_Allowance,\r\n" + 
			"max(if((e.Description like 'EPF%' or e.Description like 'epf%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), \r\n" + 
			"e.Add_Deduct_Value, '')) as EPF,\r\n" + 
			"max(if((e.Description like 'Welfare%' or e.Description like 'welfare%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), e.Add_Deduct_Value, '')) \r\n" + 
			"as Welfare,\r\n" + 
			"max(if((e.Description like 'Bike  %' or e.Description like 'bike%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), e.Add_Deduct_Value, '')) \r\n" + 
			"as Bike_Allowance,\r\n" + 
			"max(if((e.Description like 'Laptop %' or e.Description like 'laptop%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), e.Add_Deduct_Value, '')) \r\n" + 
			"as laptop_Allowance,\r\n" + 
			"max(if((e.Description like 'PM%' or e.Description like 'pm%') and \r\n" + 
			"(e.Add_Deduct_Type = 'variableType' or e.Add_Deduct_Type = 'fixedType'), e.Add_Deduct_Value, ''))\r\n" + 
			"as PM\r\n" + 
			"from employee_salary_details a \r\n" + 
			"inner join emp_month_salary_details b on a.Employee_ID = b.Emp_ID\r\n" + 
			"inner join employee_master c on a.Employee_ID = c.Employee_ID \r\n" + 
			"inner join employee_details d on a.Employee_ID = d.Employee_ID\r\n" + 
			"inner join pay_add_deduct_types e on a.Pay_Add_Deduct_Type_Code = e.Pay_Add_Deduct_Type_Code \r\n" + 
			"or b.Pay_Add_Deduct_Type_Code = e.Pay_Add_Deduct_Type_Code\r\n" + 
			"group by c.Employee_ID order by c.Employee_ID",nativeQuery=true)
	public String[][] newPayrollReport();
	
	@Query(value="SELECT er FROM EmployeeDetails er WHERE er.department.depID=:departmentId AND er.company.comID=:companyId")
	public List<EmployeeDetails> filterEmployeesByDepartmentAndCompany(@Param("departmentId") String departmentId, @Param("companyId") String companyId);
	
	@Query(value = "SELECT COALESCE(concat(ed.detailsPK.empID.empID,''),' ') as empno,COALESCE(concat(ed.detailsPK.empID.name,' ',ed.detailsPK.empID.lastname),' ') as name,COALESCE(concat(ed.detailsPK.empID.address,' ',ed.detailsPK.empID.city),' ') as address,COALESCE(concat(ed.detailsPK.empID.contact_num1,''),' ') as tel,COALESCE(concat(ed.detailsPK.empID.contact_num2,''),' ') as phone,COALESCE(concat(ed.detailsPK.empID.email,''),' ') as email,COALESCE(concat(ed.department.department,''),' ') as depar,COALESCE(concat(ed.empType.type,''),' ') as empty,COALESCE(concat(ed.designation.designation,''),' ') as des FROM EmployeeDetails ed WHERE  ed.department.depID like :dep and ed.empType.tid like :emptyp and ed.designation.did like :dis AND ed.detailsPK.empID.empID like :empid and ed.detailsPK.empID.company.comID=:comID and ed.status='ACTIVE' order by ed.department.depID,ed.empType.tid,ed.designation.did")
	public String[][] getEmployeeListrptPrivew(@Param("dep")String dep,@Param("dis")String dis,@Param("emptyp")String emptyp,@Param("empid") String empid,@Param("comID") String companyId);
	
	@Query(value = "SELECT COALESCE(concat(ed.detailsPK.empID.empID,''),' ') as empno,COALESCE(concat(ed.detailsPK.empID.name,' ',ed.detailsPK.empID.lastname),' ') as name,COALESCE(concat(ed.detailsPK.empID.address,' ',ed.detailsPK.empID.city),' ') as address,COALESCE(concat(ed.detailsPK.empID.contact_num1,''),' ') as tel,COALESCE(concat(ed.detailsPK.empID.contact_num2,''),' ') as phone,COALESCE(concat(ed.detailsPK.empID.email,''),' ') as email,COALESCE(concat(ed.department.department,''),' ') as depar,COALESCE(concat(ed.empType.type,''),' ') as empty,COALESCE(concat(ed.designation.designation,''),' ') as des, COALESCE(ed.category.catgoryID,' '), COALESCE(ed.detailsPK.empID.religion.rid,' '),COALESCE(ed.detailsPK.empID.mStatus,' ')"
			+ ",COALESCE(ed.detailsPK.empID.id_Number,' '),COALESCE(ed.basicSalary,'0'),COALESCE(ed.detailsPK.empID.dob,' '),COALESCE(ed.joinedDate,' ')  FROM EmployeeDetails ed "
			+ "WHERE  ed.category.catgoryID like :empcat AND ed.detailsPK.empID.religion.rid like :religion AND ed.detailsPK.empID.mStatus like :civista and ed.department.depID like :dep and ed.empType.tid like :emptyp and ed.designation.did like :dis AND ed.detailsPK.empID.empID like :empid and ed.detailsPK.empID.company.comID=:comID and ed.status='ACTIVE' order by ed.department.depID,ed.empType.tid,ed.designation.did")
	public String[][] getEmployeeListrptPrivewbyreligion(@Param("dep")String dep,@Param("dis")String dis,@Param("emptyp")String emptyp,@Param("empid") String empid,@Param("empcat") String empcat,@Param("religion")  String religion,@Param("civista")  String civista,@Param("comID") String companyId);

	
	@Query(value="SELECT * FROM employee_details WHERE Employee_ID=:employeeID",nativeQuery = true)
	public EmployeeDetails getEmployeeDetailsByEmployeeID(@Param("employeeID") String employeeID);
	
	@Query(value="SELECT ed FROM EmployeeDetails ed WHERE ed.detailsPK.empID.empID = :employeeId AND ed.company.comID = :companyId")
	public EmployeeDetails findEmployeeDetailsByEmployeeIdAndCompany(@Param("employeeId") String employeeId, @Param("companyId") String companyId);

	@Query(value="SELECT * FROM employee_details WHERE Company_ID=:companyID",nativeQuery = true)
	public List<EmployeeDetails> getEmployeeDetailsByCompanyID(@Param("companyID") String companyID);
	
	@Query(value="SELECT er FROM EmployeeDetails er WHERE er.company.comID=:companyID and detailsPK.empID.empID=:empno")
	public List<EmployeeDetails> getEmployeeDetailsByEmpid(@Param("companyID") String companyID,@Param("empno") String empno);
	
}
