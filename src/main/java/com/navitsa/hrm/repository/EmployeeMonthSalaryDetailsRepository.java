package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeMonthSalaryDetails;
import com.navitsa.hrm.entity.EmployeeMonthSalaryDetailsPK;

public interface EmployeeMonthSalaryDetailsRepository extends CrudRepository<EmployeeMonthSalaryDetails, EmployeeMonthSalaryDetailsPK> {
	
	//saveListAllEmp
	@Query(value="SELECT em FROM Employee em")
	public List<Employee> getEmpsToEmpMoSaDetails();
	
	//getSelectedEmpToGrid
	@Query(value = "SELECT e FROM Employee e WHERE e.empID=:empID")
	public Employee loadSelectedEmp(@Param("empID") String empID);
	
	@Query(value = "SELECT r FROM EmployeeMonthSalaryDetails r WHERE r.monthDePk.payType.deductTypeCode =:deductTypeCode "
			+ "AND r.monthDePk.payCodeid.payCodeID =:payCodeID")
	public List<EmployeeMonthSalaryDetails> updateListDetails(@Param("deductTypeCode")String deductTypeCode,
			@Param("payCodeID")String payCodeID); 
	
	
	@Query(value = "SELECT e FROM EmployeeMonthSalaryDetails e WHERE e.company.comID=:compid")
	public List<EmployeeMonthSalaryDetails> loadAllEmployeeMonthSalaryDetailsBycompid(@Param("compid") String compid);

	@Query(value = "SELECT e FROM EmployeeMonthSalaryDetails e WHERE e.company.comID=:compid and e.monthDePk.payType.deductTypeCode like :adddedtype and e.monthDePk.payCodeid.payCodeID=:payCode ")
	public List<EmployeeMonthSalaryDetails> getEmpmonthowancesGrid(@Param("payCode") String payCode,@Param("adddedtype") String adddedtype,@Param("compid") String comID);

}
