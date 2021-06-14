package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.AttendanceTxtFileHeader;

public interface AttendanceTxtFileHeaderRepo extends CrudRepository<AttendanceTxtFileHeader, String> {

	//@Query(value = "SELECT h FROM AttendanceTxtFileHeader h WHERE h.employeeId =:employeeNo")
	//public AttendanceTxtFileHeader findByEmployeeNo(@Param("employeeNo") String employeeNo);


}
