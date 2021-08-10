package com.navitsa.hrm.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.MonthBasicSalary;
import com.navitsa.hrm.entity.MonthBasicSalaryPK;

public interface MonthBasicSalaryRepository extends CrudRepository<MonthBasicSalary,MonthBasicSalaryPK> {
	
	@Query(value="SELECT m FROM MonthBasicSalary m WHERE m.monthBasicSalaryPK.payPeriodID.payPeriodID=:payPeriod and m.monthBasicSalaryPK.employeeID.empID like :employeeId and m.companyID.comID=:comid")
	public List<MonthBasicSalary> getmonthlyetfreport(@Param("payPeriod")String payPeriod,@Param("employeeId")String employeeId,@Param("comid")String comid);//String dep,String dis

}
