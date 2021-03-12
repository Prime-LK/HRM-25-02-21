package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prime.hrm.entity.AttendanceRevise;

@Repository
public interface AttendanceReviseRepository extends CrudRepository<AttendanceRevise, String> {

	@Query(value = "SELECT (max(ar.reviseId)+1) FROM AttendanceRevise ar WHERE ar.companyId=:companyId")
	public String maxReviseId(@Param("companyId") String companyId);

	@Query(value = "SELECT attendance_revise.revise_id, " + "DATE_FORMAT(attendance_revise.submit_date, \"%d-%m-%Y\"), "
			+ "attendance_revise.attendance_id, " + "shift_allocation.department_name, "
			+ "attendance_revise.employee_id, " + "shift_allocation.employee_name, " + "attendance_revise.shift_id, "
			+ "shift_allocation.shift_name, " + "DATE_FORMAT(attendance_revise.date, \"%d-%m-%Y\"), "
			+ "attendance_revise.on_time, " + "attendance_revise.off_time, " + "attendance_revise.new_on_time, "
			+ "attendance_revise.new_off_time, " + "attendance_revise.remark, " + "attendance_revise.approved\r\n"
			+ "FROM attendance_revise\r\n"
			+ "INNER JOIN shift_allocation ON attendance_revise.date = shift_allocation.date AND"
			+ " attendance_revise.employee_id = shift_allocation.employee_id AND attendance_revise.company_id = shift_allocation.company_id AND"
			+ " attendance_revise.shift_id = shift_allocation.shift_id WHERE attendance_revise.company_id = :companyId", nativeQuery = true)
	public List<String> loadAttendanceRevises(@Param("companyId") String companyId);

	@Query(value = "SELECT attendance_revise.revise_id, DATE_FORMAT(attendance_revise.submit_date, \"%d-%m-%Y\"), attendance_revise.attendance_id, shift_allocation.department_name, attendance_revise.employee_id, shift_allocation.employee_name, attendance_revise.shift_id, shift_allocation.shift_name, \r\n"
			+ "DATE_FORMAT(attendance_revise.date, \"%d-%m-%Y\"), attendance_revise.on_time, attendance_revise.off_time, attendance_revise.new_on_time, attendance_revise.new_off_time, attendance_revise.remark, attendance_revise.approved, attendance_revise.company_id \r\n"
			+ "FROM attendance_revise \r\n"
			+ "INNER JOIN shift_allocation ON attendance_revise.date = shift_allocation.date AND\r\n"
			+ "attendance_revise.employee_id = shift_allocation.employee_id AND\r\n"
			+ "attendance_revise.shift_id = shift_allocation.shift_id  AND attendance_revise.company_id = shift_allocation.company_id\r\n"
			+ "WHERE attendance_revise.submit_date BETWEEN :startDate AND :endDate AND attendance_revise.company_id = :companyId", nativeQuery = true)
	public List<String> loadAttendanceRevisesByDate(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("companyId") String companyId);

	@Query(value = "SELECT attendance_revise.revise_id, DATE_FORMAT(attendance_revise.submit_date, \"%d-%m-%Y\"), attendance_revise.attendance_id, shift_allocation.department_name, attendance_revise.employee_id, shift_allocation.employee_name, attendance_revise.shift_id, shift_allocation.shift_name, \r\n"
			+ "DATE_FORMAT(attendance_revise.date, \"%d-%m-%Y\"), attendance_revise.on_time, attendance_revise.off_time, attendance_revise.new_on_time, attendance_revise.new_off_time, attendance_revise.remark, attendance_revise.approved, attendance_revise.company_id \r\n"
			+ "FROM attendance_revise \r\n"
			+ "INNER JOIN shift_allocation ON attendance_revise.date = shift_allocation.date AND\r\n"
			+ "attendance_revise.employee_id = shift_allocation.employee_id AND attendance_revise.company_id = shift_allocation.company_id AND\r\n"
			+ "attendance_revise.shift_id = shift_allocation.shift_id\r\n"
			+ "WHERE attendance_revise.submit_date BETWEEN :startDate AND :endDate AND attendance_revise.approved = :approvalStatus AND attendance_revise.company_id = :companyId", nativeQuery = true)
	public List<String> loadAttendanceRevisesByApprovalStatus(@Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("approvalStatus") int approvalStatus,
			@Param("companyId") String companyId);
}
