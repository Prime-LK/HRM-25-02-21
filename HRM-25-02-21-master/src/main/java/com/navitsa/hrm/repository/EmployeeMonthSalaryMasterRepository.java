package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.EmployeeMonthSalaryMaster;
import com.navitsa.hrm.entity.EmployeeMonthSalaryMasterPK;

public interface EmployeeMonthSalaryMasterRepository extends CrudRepository<EmployeeMonthSalaryMaster, EmployeeMonthSalaryMasterPK> {

	@Query(value="SELECT (max(em.sMasterPK.payCode)+1) FROM EmployeeMonthSalaryMaster em")
	public String getMaxID();
}
