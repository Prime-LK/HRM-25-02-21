package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prime.hrm.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
	
	@Query(value = "SELECT em FROM Employee em WHERE em.name=:name AND em.password=:password AND em.company.comID=:comID")
	public List<Employee> findUser(@Param("name")String name,@Param("password")String password,@Param("comID")String comID);
	
	@Query(value = "SELECT e FROM Employee e WHERE e.name=:name")
	public List<Employee> findImg(@Param("name") String name);
	
	@Query(value = "SELECT e FROM Employee e WHERE e.empID=:empID")
	public List<Employee> loadTableToEmp(@Param("empID") String empID);

	//employee report
	@Query(value = "SELECT ed.detailsPK.empID.empID as empid, ed.detailsPK.empID.nationality.nationality as na,"
			+ " ed.detailsPK.empID.religion.religion as re, ed.detailsPK.empID.mStatus as marital,"
			+ " UCASE(ed.detailsPK.empID.name) as fname, ed.detailsPK.empID.dob as dob, ed.detailsPK.empID.gender as gender,"
			+ "  ed.detailsPK.empID.address as add, ed.detailsPK.empID.city as city,"
			+ " ed.detailsPK.empID.state as state, ed.detailsPK.empID.id_Number as idNumber,"
			+ " ed.detailsPK.empID.dl_number as dlNumber, ed.detailsPK.empID.passport_Number as passNumber,"
			+ " ed.detailsPK.empID.emergency_Contact_No as emeNo, ed.detailsPK.empID.blood_Group as bg,"
			+ " UCASE(ed.detailsPK.empID.lastname) as lname,"
			+ " ed.detailsPK.empID.contact_num1 as contact1, ed.detailsPK.empID.contact_num2 as contact2,"
			+ " ed.detailsPK.empID.email as email_emp, ed.category.category as category,"
			+ " ed.empType.type as eType, ed.salaryRange.range as sRange, ed.salaryGrade.grade as sGrade,"
			+ " ed.designation.designation as designation, ed.jobProfile.profile as jProfile,"
			+ " ed.joinedDate as jDate, ed.location.loid as location, ed.department.department as dep, ed.status as dStatus, "
			+ " ed.reporting as reporter , ed.detailsPK.empID.bankBranch_Code.bankid.bankName as empBank, ed.detailsPK.empID.bankBranch_Code.branch as empBranch, ed.detailsPK.empID.bank_Account as empAccount FROM EmployeeDetails ed WHERE ed.detailsPK.empID.empID=:empID ORDER BY"
			+ " ed.detailsPK.empID.empID")
	public String[][] getEmployeeReportData(@Param("empID") String empID);

	@Query(value=" SELECT  em.name as fname ,  em.lastname as lname ,em.address as empAdd , em.contact_num1 as contact_h , em.contact_num2 as contact_c , em.email as empemail "
			+ " FROM Employee em ")
			String[][] getempContactSummaryReportData();
	
	//empMonth salary details load emps
	@Query(value="SELECT em.empID as empID, em.name as name, em.lastname as lastname,"
			+ " em.contact_num1 as contact_num1, em.email as email, em.dob as dob FROM Employee em")
	public String[][] getEmpsToEmpMoSaDetails();

	@Query(value="SELECT a FROM Employee a")
	public List<Employee> getSearchDetails();

	@Query(value="select * from employee_master where concat(name,' ',lastname) =:name",nativeQuery=true)
	public Employee updateDetailsUsingEmpName(@Param("name")String name);
	
}




