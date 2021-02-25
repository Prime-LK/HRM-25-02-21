package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.EmployeeSalaryMaster;
import com.prime.hrm.entity.EmployeeSalaryMasterPK;

public interface EmployeeSalaryMasterReporsitory extends CrudRepository<EmployeeSalaryMaster, EmployeeSalaryMasterPK> {

	@Query(value="SELECT (max(es.empSalaryPK.processID)+1) FROM EmployeeSalaryMaster es")
	public String getMaxID();
	
	//load table
	@Query(value="SELECT es FROM EmployeeSalaryMaster es WHERE es.empSalaryPK.empID.empID=:empID")
	public List<EmployeeSalaryMaster> loadGrid(@Param("empID")String empID);
	
	//updateRecord
	@Query(value="SELECT es FROM EmployeeSalaryMaster es WHERE es.empSalaryPK.empID.empID=:empID AND es.empSalaryPK.processID=:processID")
	public EmployeeSalaryMaster updateRecord(@Param("empID")String empID,@Param("processID")String processID);
}
