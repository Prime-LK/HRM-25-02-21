package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.ShiftAllocation;
import com.navitsa.hrm.entity.ShiftAllocationPK;
import com.navitsa.hrm.report.ShiftAllocationBean;

public interface ShiftAllocationRepository extends CrudRepository<ShiftAllocation, ShiftAllocationPK> {

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%Y-%m-%d\") as date, calander.Types,\n"
			+ "shift_allocation.shift_id, shift_allocation.shift_name,\n"
			+ "TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") as start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") as end_time,\n"
			+ "shift_allocation.employee_id, shift_allocation.employee_name,\n"
			+ "shift_allocation.department_id, shift_allocation.department_name\n" + "FROM shift_allocation\n"
			+ "INNER JOIN calander ON shift_allocation.date = calander.Date", nativeQuery = true)
	public List<String> loadShiftAllocation();
	
	@Query(value = "SELECT * FROM shift_allocation WHERE shift_allocation.date=:date AND shift_allocation.shift_id=:shiftId AND shift_allocation.employee_id=:employeeId", nativeQuery = true)
	public ShiftAllocation findShiftAllocationByDetails(@Param("date") String date, @Param("shiftId") String shiftId,
			@Param("employeeId") String employeeId);

	@Query(value = "SELECT * FROM shift_allocation WHERE shift_allocation.date=:date AND shift_allocation.shift_id=:shiftId AND shift_allocation.employee_id=:employeeId AND shift_allocation.company_id=:companyId", nativeQuery = true)
	public ShiftAllocation findShiftAllocationByDetailsByCompany(@Param("date") String date,
			@Param("shiftId") String shiftId, @Param("employeeId") String employeeId,
			@Param("companyId") String companyId);

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%Y-%m-%d\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n"
			+ "FROM ((shift_allocation\r\n" + "INNER JOIN calander ON shift_allocation.date = calander.Date AND shift_allocation.company_id = calander.CompanyID)\r\n"
			+ "INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id AND shift_allocation.company_id = employee_attendance.company_id) \r\n"
			+ "WHERE employee_attendance.date BETWEEN :startDate AND :endDate AND shift_allocation.company_id = :companyId", nativeQuery = true)
	public String[][] loadShiftDetailReportByDate(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("companyId") String companyId);

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%Y-%m-%d\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n"
			+ "FROM ((shift_allocation\r\n" + "INNER JOIN calander ON shift_allocation.date = calander.Date AND shift_allocation.company_id = calander.CompanyID)\r\n"
			+ "INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id AND shift_allocation.company_id = employee_attendance.company_id) \r\n"
			+ "WHERE employee_attendance.date BETWEEN :startDate AND :endDate AND shift_allocation.department_id = :departmentId AND shift_allocation.company_id = :companyId", nativeQuery = true)
	public String[][] loadShiftDetailReportByDepartment(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("departmentId") String departmentId,
			@Param("companyId") String companyId);

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%Y-%m-%d\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n"
			+ "FROM ((shift_allocation\r\n" + "INNER JOIN calander ON shift_allocation.date = calander.Date AND shift_allocation.company_id = calander.CompanyID)\r\n"
			+ "INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id AND shift_allocation.company_id = employee_attendance.company_id) \r\n"
			+ "WHERE employee_attendance.date BETWEEN :startDate AND :endDate AND shift_allocation.shift_id = :shiftId  AND shift_allocation.company_id = :companyId", nativeQuery = true)
	public String[][] loadShiftDetailReportByShift(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("shiftId") String shiftId, @Param("companyId") String companyId);

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%Y-%m-%d\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n"
			+ "FROM ((shift_allocation\r\n" + "INNER JOIN calander ON shift_allocation.date = calander.Date AND shift_allocation.company_id = calander.CompanyID)\r\n"
			+ "INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id AND shift_allocation.company_id = employee_attendance.company_id) \r\n"
			+ "WHERE employee_attendance.date BETWEEN :startDate AND :endDate AND shift_allocation.department_id = :departmentId AND shift_allocation.employee_id = :employeeId AND shift_allocation.company_id = :companyId", nativeQuery = true)
	public String[][] loadShiftDetailReportByEmployee(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("departmentId") String departmentId,
			@Param("employeeId") String employeeId, @Param("companyId") String companyId);

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%Y-%m-%d\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n"
			+ "FROM ((shift_allocation\r\n" + "INNER JOIN calander ON shift_allocation.date = calander.Date AND shift_allocation.company_id = calander.CompanyID)\r\n"
			+ "INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id AND shift_allocation.company_id = employee_attendance.company_id) \r\n"
			+ "WHERE employee_attendance.date BETWEEN :startDate AND :endDate AND shift_allocation.department_id = :departmentId AND shift_allocation.shift_id = :shiftId AND shift_allocation.company_id = :companyId", nativeQuery = true)
	public String[][] loadShiftDetailReportByDepartmentAndShift(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("departmentId") String departmentId,
			@Param("shiftId") String shiftId, @Param("companyId") String companyId);

	@Query(value = "SELECT DATE_FORMAT(shift_allocation.date, \"%Y-%m-%d\") AS date, DATE_FORMAT(shift_allocation.date, \"%W\") AS weekday, calander.Description AS day_type, shift_allocation.shift_name as shift, shift_allocation.department_name AS department, shift_allocation.employee_name AS employee_name, TIME_FORMAT(shift_allocation.start_time, \"%H:%i\") AS start_time, TIME_FORMAT(shift_allocation.end_time, \"%H:%i\") AS end_time, employee_attendance.on_time AS on_time, employee_attendance.off_time AS off_time\r\n"
			+ "FROM ((shift_allocation\r\n" + "INNER JOIN calander ON shift_allocation.date = calander.Date AND shift_allocation.company_id = calander.CompanyID)\r\n"
			+ "INNER JOIN employee_attendance ON shift_allocation.date = employee_attendance.date AND shift_allocation.employee_id = employee_attendance.employee_id AND shift_allocation.shift_id = employee_attendance.shift_id AND shift_allocation.company_id = employee_attendance.company_id) \r\n"
			+ "WHERE employee_attendance.date BETWEEN :startDate AND :endDate AND shift_allocation.department_id = :departmentId AND shift_allocation.employee_id = :employeeId AND shift_allocation.shift_id = :shiftId AND shift_allocation.company_id = :companyId", nativeQuery = true)
	public String[][] loadShiftDetailReportByEmployeeAndShift(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("departmentId") String departmentId,
			@Param("employeeId") String employeeId, @Param("shiftId") String shiftId,
			@Param("companyId") String companyId);
	
	@Query(value = "SELECT sa FROM ShiftAllocation sa WHERE sa.shiftAllocationPK.date BETWEEN :startDate AND :endDate AND sa.company.comID = :companyId AND sa.shiftAllocationPK.shiftmaster.shiftId = :shiftId")
	public List<ShiftAllocation> loadShiftsByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("shiftId") String shiftId, @Param("companyId") String companyId);
	
	@Query(value = "SELECT sa FROM ShiftAllocation sa WHERE sa.company.comID = :companyId")
	public List<ShiftAllocation> loadShiftAllocationsByCompany(@Param("companyId") String companyId);
	
	@Query(value = "SELECT * FROM shift_allocation WHERE date(date)> :startDate AND date(date) <= :endDate AND employee_id=:employeeID", nativeQuery = true)
	public List<ShiftAllocation> getAllShiftAllocationBy(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("employeeID") String employeeID);

	@Query(value = "SELECT * FROM shift_allocation WHERE date(date) =:date AND employee_id=:employeeID",nativeQuery = true)
	public ShiftAllocation getShiftBy(
			@Param("date") String date, 
			@Param("employeeID") String employeeID);
}
