package com.prime.hrm.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.Setting;

public interface SettingRepository extends CrudRepository<Setting, String>{
	
	@Query(value="SELECT s FROM Setting s WHERE s.multipayperiod = 'Yes' AND s.multipaycode = 'Yes' OR "
			+ "s.multipayperiod = 'No' AND s.multipaycode = 'Yes' OR "
			+ "s.multipayperiod = 'Yes' AND s.multipaycode = 'No' OR "
			+ "s.multipayperiod = 'No' AND s.multipaycode = 'No'")
	public Setting loadRelatedHeader();
	
	@Query(value = "SELECT s FROM Setting s WHERE s.multipaycode = 'No'")
	public Setting showOrDisablePayCode();
	
	//leave and attendence chaer queries
	@Query(value = "select lcase(c.Department) as department_name\r\n" + 
			"from employee_attendance a\r\n" + 
			"inner join employee_details b on a.employee_id = b.Employee_ID\r\n" + 
			"inner join department c on b.Department_ID = c.Department_ID \r\n" + 
			"where a.date =:date group by c.Department_ID\r\n" + 
			"order by c.Department_ID asc", nativeQuery= true)
	public String[][] getDepartmentNameToChart(@Param("date")String date);
	
	@Query(value="select * from (select * from(select * from\r\n" + 
			"(select lcase(c.Department) as department_name,'Presented Employees' as labelPreEmp,count(a.employee_id) as employee_count\r\n" + 
			"from employee_attendance a\r\n" + 
			"inner join employee_details b on a.employee_id = b.Employee_ID\r\n" + 
			"inner join department c on b.Department_ID = c.Department_ID \r\n" + 
			"where a.date =:date group by department_name order by department_name asc)a order by department_name) a \r\n" + 
			"union all \r\n" + 
			"select * from(select lcase(a.department_name) as department_name, 'Abcent Employees' as labelPreEmp,\r\n" + 
			"count(a.employee_id) as employee_count \r\n" + 
			"from shift_allocation a where not exists \r\n" + 
			"(select * from employee_attendance b where a.employee_id = b.employee_id and b.date =:date)\r\n" + 
			"and a.date =:date group by a.department_name order by a.department_name asc)a \r\n" + 
			"union all\r\n" + 
			"select * from(select lcase(c.Department) as department_name, 'Leave Approved Employees' as labelPreEmp,\r\n" + 
			"count(approved) as approved_leaves from employee_attendance a\r\n" + 
			"inner join employee_details b on a.Employee_ID = b.employee_ID\r\n" + 
			"inner join department c on b.Department_ID = c.Department_ID \r\n" + 
			"where a.date =:date group by department_name order by department_name asc)a )b order by department_name",nativeQuery=true)
	public String[][] getChartDateRelatedDep(@Param("date")String date);
	
	@Query(value = "select * from\r\n" + 
			"(select a.employee_id as empID, d.name as fName, d.lastname as lName, e.shift_name as shiftName, e.date as date\r\n" + 
			"from employee_attendance a\r\n" + 
			"inner join employee_details b on a.employee_id = b.Employee_ID\r\n" + 
			"inner join department c on b.Department_ID = c.Department_ID \r\n" + 
			"inner join employee_master d on a.employee_id = d.Employee_ID\r\n" + 
			"inner join shift_allocation e on a.date = e.date\r\n" + 
			"where a.date =:date and c.Department_ID =:depID group by empID order by empID asc)a\r\n" + 
			"union all\r\n" + 
			"select * from \r\n" + 
			"(select b.employee_id as empID, b.name as fName, b.lastname as lName, a.shift_name as shiftName, \r\n" + 
			"a.date as date\r\n" + 
			"from shift_allocation a\r\n" + 
			"inner join employee_master b on a.employee_id = b.Employee_ID\r\n" + 
			"inner join department c on a.department_id = c.Department_ID \r\n" + 
			" where not exists \r\n" + 
			"(select * from employee_attendance b where a.employee_id = b.employee_id and b.date =:date)\r\n" + 
			"and a.date =:date and c.Department_ID =:depID group by empID order by empID asc) a\r\n" + 
			"union all\r\n" + 
			"select a.employee_id as empID, e.name as fName, e.lastname as lName, d.shift_name, a.date as date\r\n" + 
			"from employee_attendance a\r\n" + 
			"inner join employee_details b on a.Employee_ID = b.employee_ID\r\n" + 
			"inner join department c on b.Department_ID = c.Department_ID \r\n" + 
			"inner join shift_allocation d on  a.date = d.date\r\n" + 
			"inner join employee_master e on a.employee_id = e.Employee_ID \r\n" + 
			"where a.date =:date and c.department_id =:depID group by empID order by empID asc",nativeQuery=true)
	public String[][] getChartMoreData(@Param("date")String date, @Param("depID")String depID);

}