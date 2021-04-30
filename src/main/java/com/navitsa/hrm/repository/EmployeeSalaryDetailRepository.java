package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.EmployeeSalaryDetail;
import com.navitsa.hrm.entity.EmployeeSalaryDetailPK;

public interface EmployeeSalaryDetailRepository extends CrudRepository<EmployeeSalaryDetail, EmployeeSalaryDetailPK> {

	// load data according to empID on employee salary details jsp
	@Query(value = "SELECT ed FROM EmployeeSalaryDetail ed WHERE ed.empdetailPK.empID.empID=:empID")
	public List<EmployeeSalaryDetail> loadempsalaryDetails(@Param("empID") String empID);

	// update employee salary details data according to empID and deductTypeCode

	@Query(value = "SELECT ed FROM EmployeeSalaryDetail ed WHERE ed.empdetailPK.empID.empID=:empID AND ed.empdetailPK.payAddeductTypes.deductTypeCode=:deductTypeCode")
	public EmployeeSalaryDetail updateEmpsalaryDetails(@Param("empID") String empID,
			@Param("deductTypeCode") String deductTypeCode);

	@Query(value = "select * from employee_salary_details esd inner join pay_add_deduct_types ty \r\n"
			+ "on esd.Pay_Add_Deduct_Type_Code = ty.Pay_Add_Deduct_Type_Code inner join employee_master em\r\n"
			+ "on esd.Employee_ID = em.Employee_ID inner join employee_details ed\r\n"
			+ "on em.Employee_ID = ed.Employee_ID inner join department d\r\n"
			+ "on ed.Department_ID = d.Department_ID where ed.Department_ID=:Department_ID\r\n"
			+ "and ty.Pay_Add_Deduct_Type_Code=:Pay_Add_Deduct_Type_Code and esd.Is_Active='Active'", nativeQuery = true)
	public List<EmployeeSalaryDetail> getEmpToEmpMoSaDe(@Param("Department_ID") String depID,
			@Param("Pay_Add_Deduct_Type_Code") String deductTypeCode);

	@Query(value = "select * from employee_salary_details esd inner join pay_add_deduct_types ty \r\n"
			+ "on esd.Pay_Add_Deduct_Type_Code = ty.Pay_Add_Deduct_Type_Code inner join employee_master em\r\n"
			+ "on esd.Employee_ID = em.Employee_ID inner join employee_details ed\r\n"
			+ "on em.Employee_ID = ed.Employee_ID inner join location l\r\n"
			+ "on ed.Location = l.Location_ID where ed.Location=:Location \r\n"
			+ "and esd.Pay_Add_Deduct_Type_Code=:Pay_Add_Deduct_Type_Code and esd.Is_Active='Active'", nativeQuery = true)
	public List<EmployeeSalaryDetail> getEmpRelatedLoc(@Param("Location") String loid,
			@Param("Pay_Add_Deduct_Type_Code") String deductTypeCode);

	@Query(value = "select * from employee_salary_details esd inner join pay_add_deduct_types ty \r\n"
			+ "on esd.Pay_Add_Deduct_Type_Code = ty.Pay_Add_Deduct_Type_Code inner join employee_master em\r\n"
			+ "on esd.Employee_ID = em.Employee_ID inner join employee_details ed\r\n"
			+ "on em.Employee_ID = ed.Employee_ID inner join emp_categories c\r\n"
			+ "on ed.Emp_Category_ID = c.Emp_Category_ID where ed.Emp_Category_ID=:Emp_Category_ID \r\n"
			+ "and esd.Pay_Add_Deduct_Type_Code=:Pay_Add_Deduct_Type_Code and esd.Is_Active='Active'", nativeQuery = true)
	public List<EmployeeSalaryDetail> getEmpRelatedCat(@Param("Emp_Category_ID") String catgoryID,
			@Param("Pay_Add_Deduct_Type_Code") String deductTypeCode);

	@Query(value = "select * from employee_salary_details esd inner join pay_add_deduct_types ty \r\n"
			+ "on esd.Pay_Add_Deduct_Type_Code = ty.Pay_Add_Deduct_Type_Code inner join employee_master em\r\n"
			+ "on esd.Employee_ID = em.Employee_ID inner join employee_details ed\r\n"
			+ "on em.Employee_ID = ed.Employee_ID inner join employee_types t\r\n"
			+ "on ed.Emp_Type_ID = t.Emp_Type_ID where ed.Emp_Type_ID=:Emp_Type_ID \r\n"
			+ "and esd.Pay_Add_Deduct_Type_Code=:Pay_Add_Deduct_Type_Code and esd.Is_Active='Active'", nativeQuery = true)
	public List<EmployeeSalaryDetail> getEmpRelatedType(@Param("Emp_Type_ID") String tid,
			@Param("Pay_Add_Deduct_Type_Code") String deductTypeCode);
	
	//get the employee salary details based departmnt to jasper report
	@Query(value = "select s.Employee_ID as empiD , s.Inactive_From as inactive_form , s.Added_Date as added_date, s.Added_User as added_user, s.Effective_Date as efectiveDate, s.Inactive_User as inactiveUser ,s.Is_Active as active ,p.Description as addDeduct ,dep.Department as empDep, e.Name as empFname , e.lastname  as empLname ,  p.Add_Deduct_Type as addDeductType,Add_Deduct_Status as addDedStatus,Add_Deduct_Value as aDsvalue\r\n" + 
			"from employee_salary_details s inner join employee_details d\r\n" + 
			"on s.Employee_ID = d.Employee_ID\r\n" + 
			"inner join hrm.employee_master e on s.Employee_ID = e.Employee_ID\r\n" + 
			"inner join hrm.pay_add_deduct_types p on s.Pay_Add_Deduct_Type_Code = p.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join hrm.department dep on d.Department_ID= dep.Department_ID where d.Department_ID=:depID and s.Pay_Add_Deduct_Type_Code=:deductTypeCode and s.Is_Active= 'Active'", nativeQuery = true)
	public String[][] getSalarydetailsbasedondepID(@Param("depID") String depID , @Param("deductTypeCode") String deductTypeCode);
	
	//get the employee salary details based on location to jasper report
	@Query(value = "select s.Employee_ID as empiD , s.Inactive_From as inactive_form , s.Added_Date as added_date, s.Added_User as added_user, s.Effective_Date as efectiveDate, s.Inactive_User as inactiveUser ,s.Is_Active as active ,p.Description,l.Location as emplocation, e.Name as empFname , e.lastname  as empLname, p.Add_Deduct_Type as addDeductType,Add_Deduct_Status as addDedStatus,Add_Deduct_Value as aDsvalue\r\n" + 
			"from hrm.employee_salary_details s inner join hrm.employee_details d\r\n" + 
			"on s.Employee_ID = d.Employee_ID \r\n" + 
			"inner join hrm.employee_master e on s.Employee_ID = e.Employee_ID\r\n" + 
			"inner join hrm.pay_add_deduct_types p on s.Pay_Add_Deduct_Type_Code = p.Pay_Add_Deduct_Type_Code\r\n" + 
			"inner join hrm.location l on d.Location = l.Location_ID where d.Location=:loid and s.Pay_Add_Deduct_Type_Code=:deductTypeCode and s.Is_Active='Active'\r\n" + 
			"",nativeQuery = true)
	public String[][] getSalarydetailsbasedonLocation(@Param("loid") String loid , @Param("deductTypeCode") String deductTypeCode);
	
	//get the employee salary details based on employee category to jasper report
	@Query(value ="select s.Employee_ID as empiD , s.Inactive_From as inactive_form , s.Added_Date as added_date, s.Added_User as added_user, s.Effective_Date as efectiveDate, s.Inactive_User as inactiveUser ,s.Is_Active as active ,p.Description as addDeduct ,c.Emp_Category as empCategory, e.Name as empFname , e.lastname  as empLname,  p.Add_Deduct_Type as addDeductType,Add_Deduct_Status as addDedStatus,Add_Deduct_Value as aDsvalue\r\n" + 
			"from employee_salary_details s inner join employee_details d\r\n" + 
			"on s.Employee_ID = d.Employee_ID\r\n" + 
			"inner join hrm.employee_master e on s.Employee_ID = e.Employee_ID\r\n" + 
			"inner join hrm.pay_add_deduct_types p on s.Pay_Add_Deduct_Type_Code = p.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join hrm.emp_categories c on d.Emp_Category_ID = c.Emp_Category_ID  where c.Emp_Category_ID=:catgoryID and s.Pay_Add_Deduct_Type_Code=:deductTypeCode and s.Is_Active='Active'", nativeQuery =true)
	
	public String[][] getSalarydetailsbasedoncatgoryID(@Param("catgoryID") String catgoryID , @Param("deductTypeCode") String deductTypeCode);
	
	//get salary details based on employee type
	@Query(value ="select s.Employee_ID as empiD , s.Inactive_From as inactive_form , s.Added_Date as added_date, s.Added_User as added_user, s.Effective_Date as efectiveDate, s.Inactive_User as inactiveUser ,s.Is_Active as active ,p.Description as addDeduct , t.Employee_Type as empType, e.Name as empFname , e.lastname  as empLname, p.Add_Deduct_Type as addDeductType,Add_Deduct_Status as addDedStatus,Add_Deduct_Value as aDsvalue\r\n" + 
			"from employee_salary_details s inner join employee_details d on s.Employee_ID = d.Employee_ID\r\n" + 
			"inner join hrm.employee_master e on s.Employee_ID = e.Employee_ID\r\n" + 
			"inner join hrm.pay_add_deduct_types p on s.Pay_Add_Deduct_Type_Code = p.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join hrm.employee_types t on  d.Emp_Category_ID = t.Emp_Type_ID where t.Emp_Type_ID=:tid and s.Pay_Add_Deduct_Type_Code=:deductTypeCode and s.Is_Active='Active'" , nativeQuery = true)
	public String [][] getSalarydetailsbasedontypeID(@Param("tid") String tid , @Param("deductTypeCode") String deductTypeCode);
	
	//get salary details based on employee type
	@Query(value ="select s.Employee_ID as empiD , s.Inactive_From as inactive_form , s.Added_Date as added_date, s.Added_User as added_user, s.Effective_Date as efectiveDate, s.Inactive_User as inactiveUser ,s.Is_Active as active ,p.Description as addDeduct , t.Employee_Type as emptype,c.Emp_Category as empCategory ,dep.Department as empDep, e.Name as empFname , e.lastname  as empLname, p.Add_Deduct_Type as addDeductType,Add_Deduct_Status as addDedStatus,Add_Deduct_Value as aDsvalue\r\n" + 
			"from employee_salary_details s inner join employee_details d on s.Employee_ID = d.Employee_ID\r\n" + 
			"inner join hrm.employee_master e on s.Employee_ID = e.Employee_ID\r\n" + 
			"inner join hrm.pay_add_deduct_types p on s.Pay_Add_Deduct_Type_Code = p.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join hrm.department dep on d.Department_ID= dep.Department_ID\r\n" + 
			"inner join hrm.emp_categories c on d.Emp_Category_ID = c.Emp_Category_ID \r\n" + 
			"inner join hrm.employee_types t on  d.Emp_Category_ID = t.Emp_Type_ID where s.Pay_Add_Deduct_Type_Code=:deductTypeCode order by d.Department_ID\r\n" + 
			"" , nativeQuery = true)
	public String [][] getSalarydetailsbasedonPay_Add_Deduct_Type_Code(@Param("deductTypeCode") String deductTypeCode);
	
	//get all employees to salary details report
	@Query(value ="select s.Employee_ID as empiD , s.Inactive_From as inactive_form , s.Added_Date as added_date, s.Added_User as added_user, s.Effective_Date as efectiveDate, s.Inactive_User as inactiveUser ,s.Is_Active as active ,p.Description as addDeduct , e.Name as empFname , e.lastname  as empLname, p.Add_Deduct_Type as addDeductType,Add_Deduct_Status as addDedStatus,Add_Deduct_Value as aDsvalue\r\n" + 
			"from employee_salary_details s inner join employee_details d on s.Employee_ID = d.Employee_ID\r\n" + 
			"inner join hrm.employee_master e on s.Employee_ID = e.Employee_ID\r\n" + 
			"inner join hrm.pay_add_deduct_types p on s.Pay_Add_Deduct_Type_Code = p.Pay_Add_Deduct_Type_Code; \r\n" + 
			"", nativeQuery = true)
	public String[][] getAllemployeesTosalaryDetailsREPORT();
	
	//load both active and inactive salary details report based on department
	@Query(value = "select s.Employee_ID as empiD , s.Inactive_From as inactive_form , s.Added_Date as added_date, s.Added_User as added_user, s.Effective_Date as efectiveDate, s.Inactive_User as inactiveUser ,s.Is_Active as active ,p.Description as addDeduct ,dep.Department as empDep, e.Name as empFname , e.lastname  as empLname,  p.Add_Deduct_Type as addDeductType,Add_Deduct_Status as addDedStatus,Add_Deduct_Value as aDsvalue\r\n" + 
			"from employee_salary_details s inner join employee_details d\r\n" + 
			"on s.Employee_ID = d.Employee_ID\r\n" + 
			"inner join hrm.employee_master e on s.Employee_ID = e.Employee_ID\r\n" + 
			"inner join hrm.pay_add_deduct_types p on s.Pay_Add_Deduct_Type_Code = p.Pay_Add_Deduct_Type_Code \r\n" + 
			"inner join hrm.department dep on d.Department_ID= dep.Department_ID where d.Department_ID=:depID and s.Pay_Add_Deduct_Type_Code=:deductTypeCode", nativeQuery = true)
	public String[][] getSalarydetailsbasedondepIDinactive(@Param("depID") String depID , @Param("deductTypeCode") String deductTypeCode);
	
	//get all the employee salary details based on location to jasper report
		@Query(value = "select s.Employee_ID as empiD , s.Inactive_From as inactive_form , s.Added_Date as added_date, s.Added_User as added_user, s.Effective_Date as efectiveDate, s.Inactive_User as inactiveUser ,s.Is_Active as active ,p.Description,l.Location as emplocation, e.Name as empFname , e.lastname  as empLname,  p.Add_Deduct_Type as addDeductType,Add_Deduct_Status as addDedStatus,Add_Deduct_Value as aDsvalue\r\n" + 
				"from hrm.employee_salary_details s inner join hrm.employee_details d\r\n" + 
				"on s.Employee_ID = d.Employee_ID \r\n" + 
				"inner join hrm.employee_master e on s.Employee_ID = e.Employee_ID\r\n" + 
				"inner join hrm.pay_add_deduct_types p on s.Pay_Add_Deduct_Type_Code = p.Pay_Add_Deduct_Type_Code\r\n" + 
				"inner join hrm.location l on d.Location = l.Location_ID where d.Location=:loid and s.Pay_Add_Deduct_Type_Code=:deductTypeCode\r\n" + 
				"",nativeQuery = true)
		public String[][] getSalarydetailsbasedonLocationall(@Param("loid") String loid , @Param("deductTypeCode") String deductTypeCode);
		
		//get the employee salary details based on employee category to jasper report
		@Query(value ="select s.Employee_ID as empiD , s.Inactive_From as inactive_form , s.Added_Date as added_date, s.Added_User as added_user, s.Effective_Date as efectiveDate, s.Inactive_User as inactiveUser ,s.Is_Active as active ,p.Description as addDeduct ,c.Emp_Category as empCategory, e.Name as empFname , e.lastname  as empLname,  p.Add_Deduct_Type as addDeductType,Add_Deduct_Status as addDedStatus,Add_Deduct_Value as aDsvalue\r\n" + 
				"from employee_salary_details s inner join employee_details d\r\n" + 
				"on s.Employee_ID = d.Employee_ID\r\n" + 
				"inner join hrm.employee_master e on s.Employee_ID = e.Employee_ID\r\n" + 
				"inner join hrm.pay_add_deduct_types p on s.Pay_Add_Deduct_Type_Code = p.Pay_Add_Deduct_Type_Code \r\n" + 
				"inner join hrm.emp_categories c on d.Emp_Category_ID = c.Emp_Category_ID  where c.Emp_Category_ID=:catgoryID and s.Pay_Add_Deduct_Type_Code=:deductTypeCode", nativeQuery =true)
		
		public String[][] getSalarydetailsbasedonallcatgoryID(@Param("catgoryID") String catgoryID , @Param("deductTypeCode") String deductTypeCode);
		
		
		//get salary details based on employee type
		@Query(value ="select s.Employee_ID as empiD , s.Inactive_From as inactive_form , s.Added_Date as added_date, s.Added_User as added_user, s.Effective_Date as efectiveDate, s.Inactive_User as inactiveUser ,s.Is_Active as active ,p.Description as addDeduct , t.Employee_Type as empType, e.Name as empFname , e.lastname  as empLname,  p.Add_Deduct_Type as addDeductType,Add_Deduct_Status as addDedStatus,Add_Deduct_Value as aDsvalue\r\n" + 
				"from employee_salary_details s inner join employee_details d on s.Employee_ID = d.Employee_ID\r\n" + 
				"inner join hrm.employee_master e on s.Employee_ID = e.Employee_ID\r\n" + 
				"inner join hrm.pay_add_deduct_types p on s.Pay_Add_Deduct_Type_Code = p.Pay_Add_Deduct_Type_Code \r\n" + 
				"inner join hrm.employee_types t on  d.Emp_Category_ID = t.Emp_Type_ID where t.Emp_Type_ID=:tid and s.Pay_Add_Deduct_Type_Code=:deductTypeCode" , nativeQuery = true)
		public String [][] getallSalarydetailsbasedontypeID(@Param("tid") String tid , @Param("deductTypeCode") String deductTypeCode);
		
}
