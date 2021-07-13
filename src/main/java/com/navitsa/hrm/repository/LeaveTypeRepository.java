package com.navitsa.hrm.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.LeaveType;


public interface LeaveTypeRepository extends CrudRepository <LeaveType, String> {

	@Query(value = "SELECT (max(rm.leaveCode)+1) FROM LeaveType rm")
	public String getMaxL();

	@Query(value = "SELECT t FROM LeaveType t WHERE t.company.comID =:companyId")
	public List<LeaveType> getLeaveTypesByCompany(@Param("companyId") String companyId);

}

