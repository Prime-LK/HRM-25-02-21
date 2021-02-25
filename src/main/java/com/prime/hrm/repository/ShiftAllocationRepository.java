package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.ShiftAllocation;
import com.prime.hrm.entity.ShiftAllocationPK;

public interface ShiftAllocationRepository extends CrudRepository<ShiftAllocation, ShiftAllocationPK> {

	@Query(value = "SELECT \r\n" + "    DATE_FORMAT(shift_allocation.date, \"%d-%m-%Y\"),\r\n" + "    calander.Types,\r\n"
			+ "    shift_allocation.shift_id,\r\n" + "    shift_allocation.shift_name,\r\n"
			+ "    TIME_FORMAT(shift_allocation.start_time, \"%H:%i\"),\r\n" + "    TIME_FORMAT(shift_allocation.end_time, \"%H:%i\"),\r\n"
			+ "    shift_allocation.employee_id,\r\n" + "    shift_allocation.employee_name,\r\n"
			+ "    shift_allocation.department_id,\r\n" + "    shift_allocation.department_name\r\n" + "FROM\r\n"
			+ "    ((shift_allocation\r\n"
			+ "    INNER JOIN calander ON shift_allocation.date = calander.Date));", nativeQuery = true)
	public List<String> loadShiftAllocation();

	@Query(value = "SELECT * FROM shift_allocation WHERE shift_allocation.date=:date AND shift_allocation.shift_id=:shiftId AND shift_allocation.employee_id=:employeeId", nativeQuery = true)
	public ShiftAllocation findShiftAllocationByDetails(@Param("date") String date, @Param("shiftId") String shiftId,
			@Param("employeeId") String employeeId);
	
	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%d-%m-%Y\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n" + 
			"FROM ((shift_allocation\r\n" + 
			"INNER JOIN calander ON shift_allocation.date = calander.Date)\r\n" + 
			"INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id) \r\n" + 
			"WHERE employee_attendance.date BETWEEN :startDate AND :endDate", nativeQuery = true)
	public String[][] loadShiftDetailReportByDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%d-%m-%Y\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n" + 
			"FROM ((shift_allocation\r\n" + 
			"INNER JOIN calander ON shift_allocation.date = calander.Date)\r\n" + 
			"INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id) \r\n" + 
			"WHERE employee_attendance.date BETWEEN :startDate AND :endDate AND shift_allocation.department_id = :departmentId", nativeQuery = true)
	public String[][] loadShiftDetailReportByDepartment(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("departmentId") String departmentId);

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%d-%m-%Y\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n" + 
			"FROM ((shift_allocation\r\n" + 
			"INNER JOIN calander ON shift_allocation.date = calander.Date)\r\n" + 
			"INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id) \r\n" + 
			"WHERE employee_attendance.date BETWEEN :startDate AND :endDate AND shift_allocation.shift_id = :shiftId", nativeQuery = true)
	public String[][] loadShiftDetailReportByShift(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("shiftId") String shiftId);

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%d-%m-%Y\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n" + 
			"FROM ((shift_allocation\r\n" + 
			"INNER JOIN calander ON shift_allocation.date = calander.Date)\r\n" + 
			"INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id) \r\n" + 
			"WHERE employee_attendance.date BETWEEN :startDate AND :endDate AND shift_allocation.department_id = :departmentId AND shift_allocation.employee_id = :employeeId", nativeQuery = true)
	public String[][] loadShiftDetailReportByEmployee(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("departmentId") String departmentId, @Param("employeeId") String employeeId);

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%d-%m-%Y\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n" + 
			"FROM ((shift_allocation\r\n" + 
			"INNER JOIN calander ON shift_allocation.date = calander.Date)\r\n" + 
			"INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id) \r\n" + 
			"WHERE employee_attendance.date BETWEEN :startDate AND :endDate AND shift_allocation.department_id = :departmentId AND shift_allocation.shift_id = :shiftId", nativeQuery = true)
	public String[][] loadShiftDetailReportByDepartmentAndShift(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("departmentId") String departmentId, @Param("shiftId") String shiftId);

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%d-%m-%Y\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n" + 
			"FROM ((shift_allocation\r\n" + 
			"INNER JOIN calander ON shift_allocation.date = calander.Date)\r\n" + 
			"INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id) \r\n" + 
			"WHERE employee_attendance.date BETWEEN :startDate AND :endDate AND shift_allocation.department_id = :departmentId AND shift_allocation.employee_id = :employeeId AND shift_allocation.shift_id = :shiftId", nativeQuery = true)
	public String[][] loadShiftDetailReportByEmployeeAndShift(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("departmentId") String departmentId, @Param("employeeId") String employeeId, @Param("shiftId") String shiftId);
	
	
	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%d-%m-%Y\"), calander.Types,\r\n" + 
			"shift_allocation.shift_id, shift_allocation.shift_name,\r\n" + 
			"TIME_FORMAT(shift_allocation.start_time, \"%H:%i\"), TIME_FORMAT(shift_allocation.end_time, \"%H:%i\"),\r\n" +  
			"shift_allocation.employee_id, shift_allocation.employee_name,\r\n" + 
			"shift_allocation.department_id, shift_allocation.department_name FROM\r\n" + 
			"shift_allocation\r\n" + 
			"INNER JOIN calander ON shift_allocation.date = calander.Date\r\n" + 
			"WHERE shift_allocation.date BETWEEN :startDate AND :endDate AND shift_allocation.shift_id = :shiftId", nativeQuery = true)
	public String[][] loadShiftsByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("shiftId") String shiftId);
}
