package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.AttendanceTxtFileDetail;

public interface AttendanceTxtFileDetailRepo extends CrudRepository<AttendanceTxtFileDetail, Integer> {

	@Query(value = "SELECT * FROM attendance_log_detail WHERE date(inout_date) > :startDate "
			+ "AND date(inout_date) <= :endDate "
			+ "AND employee_id=:employeeID "
			+ "AND attendance_log_header_id=:txtFileHeaderID",nativeQuery = true)
	public List<AttendanceTxtFileDetail> getAttendanceRecords(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("employeeID") String employeeID,
			@Param("txtFileHeaderID") String txtFileHeaderID);

}
