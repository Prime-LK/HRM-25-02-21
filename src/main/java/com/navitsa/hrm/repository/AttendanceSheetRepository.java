package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.AttendanceSheet;


public interface AttendanceSheetRepository extends CrudRepository<AttendanceSheet, Integer> {

	@Query(value = "SELECT * FROM attendance_sheet WHERE pay_period_id=:payPeriodID AND employee_id=:employeeID", nativeQuery = true)
	public List<AttendanceSheet> findBy(
			@Param("payPeriodID") String payPeriodID,
			@Param("employeeID") String employeeID);

	@Query(value = "SELECT SUM(late_min_time_in)+SUM(late_min_time_out) FROM attendance_sheet WHERE pay_period_id=:payPeriodID AND employee_id=:employeeID AND company_id=:companyID", nativeQuery = true)
	public String getTotalLate(
			@Param("payPeriodID") String payPeriodID,
			@Param("employeeID") String employeeID, @Param("companyID") String companyID);
	
	@Query(value = "SELECT SUM(ot_hrs_normal)+SUM(ot_hrs_extra) FROM attendance_sheet WHERE pay_period_id=:payPeriodID AND employee_id=:employeeID AND company_id=:companyID", nativeQuery = true)
	public String getTotalOT(
			@Param("payPeriodID") String payPeriodID,
			@Param("employeeID") String employeeID,@Param("companyID") String companyID);
	
	@Query(value = "SELECT * FROM attendance_sheet WHERE shift_in IS NOT NULL AND time_in IS NULL AND time_out IS NULL AND pay_period_id=:payPeriodID AND employee_id=:employeeID AND company_id=:companyID", nativeQuery = true)
	public List<AttendanceSheet> getTotalLeave(
			@Param("payPeriodID") String payPeriodID,
			@Param("employeeID") String employeeID,@Param("companyID") String companyID);

	@Query(value = "SELECT SUM(ot_hrs_normal)+SUM(ot_hrs_extra) FROM attendance_sheet WHERE (ot_hrs_normal>0 OR ot_hrs_extra >0) AND day_type=:dayType AND pay_period_id=:payPeriodID AND employee_id=:employeeID AND company_id=:companyID", nativeQuery = true)
	public String getTotalOTByDayType(
			@Param("dayType") String dayType,
			@Param("payPeriodID") String payPeriodID,
			@Param("employeeID") String employeeID, @Param("companyID") String companyID);
	
	@Query(value = "SELECT * FROM attendance_sheet WHERE (ot_hrs_normal>0 OR ot_hrs_extra >0) AND day_type=:dayType AND pay_period_id=:payPeriodID AND employee_id=:employeeID AND company_id=:companyID", nativeQuery = true)
	public List<AttendanceSheet> getOTByDayType(
			@Param("dayType") String dayType,
			@Param("payPeriodID") String payPeriodID,
			@Param("employeeID") String employeeID, @Param("companyID") String companyID);
	
	
	@Query(value = "SELECT a FROM AttendanceSheet a WHERE a.payPeriod.payPeriodID=:payPeriod AND a.employee.empID =:employeeId AND a.company.comID =:companyId")
	public List<AttendanceSheet> getAttendanceReportBy(@Param("payPeriod")String payPeriod, @Param("employeeId")String employeeId, @Param("companyId")String companyId);
	
	
	
	@Query(value = "SELECT a FROM AttendanceSheet a WHERE a.payPeriod.payPeriodID=:payPeriod AND a.company.comID =:companyId")
	public List<AttendanceSheet> getAttendanceReportByPayPeriod(@Param("payPeriod")String payPeriod, @Param("companyId")String companyId);

}
