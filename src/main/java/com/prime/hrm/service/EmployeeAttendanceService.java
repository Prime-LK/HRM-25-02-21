package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.EmployeeAttendance;
import com.prime.hrm.report.AttendanceMainReportBean;
import com.prime.hrm.report.AttendanceSubReportBean;
import com.prime.hrm.repository.EmployeeAttendanceRepository;

@Service
public class EmployeeAttendanceService {

	@Autowired
	private EmployeeAttendanceRepository employeeAttendanceRepository;

	public String getMaxAttendanceId() {
		if (employeeAttendanceRepository.maxAttendanceId() == null) {
			return "1";
		} else {
			return employeeAttendanceRepository.maxAttendanceId();
		}
	}

	public void saveEmployeeAttendance(EmployeeAttendance employeeAttendance) {
		employeeAttendanceRepository.save(employeeAttendance);
	}

	public void saveEmployeeAttendance(List<EmployeeAttendance> employeeAttendance) {
		employeeAttendanceRepository.saveAll(employeeAttendance);
	}

	public List<String> loadAttendances() {
		return employeeAttendanceRepository.loadAttendances();
	}

	public EmployeeAttendance findAttendanceById(String attendanceId) {
		return employeeAttendanceRepository.findById(attendanceId).get();

	}

	public List<String> loadAttendancesByDate(String startDate, String endDate) {
		return employeeAttendanceRepository.loadAttendancesByDate(startDate, endDate);
	}

	public List<String> loadAttendancesByDepartment(String startDate, String endDate, String departmentId) {
		return employeeAttendanceRepository.loadAttendancesByDepartment(startDate, endDate, departmentId);
	}

	public List<String> loadAttendancesByDepartmentAndShiftAndApprovalStatus(String startDate, String endDate,
			String departmentId, String shiftId, int approvalStatus) {
		return employeeAttendanceRepository.loadAttendancesByDepartmentAndShiftAndApprovalStatus(startDate, endDate,
				departmentId, shiftId, approvalStatus);
	}

	public List<String> loadAttendancesByDepartmentAndShift(String startDate, String endDate, String departmentId,
			String shiftId) {
		return employeeAttendanceRepository.loadAttendancesByDepartmentAndShift(startDate, endDate, departmentId,
				shiftId);
	}

	public List<String> loadAttendancesByDepartmentAndApprovalStatus(String startDate, String endDate,
			String departmentId, int approvalStatus) {
		return employeeAttendanceRepository.loadAttendancesByDepartmentAndApprovalStatus(startDate, endDate,
				departmentId, approvalStatus);
	}

	public List<String> loadAttendancesByEmployee(String startDate, String endDate, String departmentId,
			String employeeId) {
		return employeeAttendanceRepository.loadAttendancesByEmployee(startDate, endDate, departmentId, employeeId);
	}

	public List<String> loadAttendancesByEmployeeAndShift(String startDate, String endDate, String departmentId,
			String employeeId, String shiftId) {
		return employeeAttendanceRepository.loadAttendancesByEmployeeAndShift(startDate, endDate, departmentId,
				employeeId, shiftId);
	}

	public List<String> loadAttendancesByEmployeeAndApprovalStatus(String startDate, String endDate,
			String departmentId, String employeeId, int approvalStatus) {
		return employeeAttendanceRepository.loadAttendancesByEmployeeAndApprovalStatus(startDate, endDate, departmentId,
				employeeId, approvalStatus);
	}

	public List<String> loadAttendancesByShift(String startDate, String endDate, String shiftId) {
		return employeeAttendanceRepository.loadAttendancesByShift(startDate, endDate, shiftId);
	}

	public List<String> loadAttendancesByApprovalStatus(String startDate, String endDate, int approvalStatus) {
		return employeeAttendanceRepository.loadAttendancesByApprovalStatus(startDate, endDate, approvalStatus);
	}

	public List<String> loadAttendancesByShiftAndApprovalStatus(String startDate, String endDate, String shiftId,
			int approvalStatus) {
		return employeeAttendanceRepository.loadAttendancesByShiftAndApprovalStatus(startDate, endDate, shiftId,
				approvalStatus);
	}

	public List<String> loadAttendancesByEmployeeAndShiftAndApprovalStatus(String startDate, String endDate,
			String departmentId, String employeeId, String shiftId, int approvalStatus) {
		return employeeAttendanceRepository.loadAttendancesByEmployeeAndShiftAndApprovalStatus(startDate, endDate,
				departmentId, employeeId, shiftId, approvalStatus);
	}
	
	public String[][] loadMainReportDetails3(int year, int month, String employeeId) {
		return employeeAttendanceRepository.loadMainReportDetails3(year, month, employeeId);
	}
	
	public List<String> loadSubReportDetails(int year, int month, String employeeId) {
		return employeeAttendanceRepository.loadSubReportDetails(year, month, employeeId);
	}
	
	public String[][] loadSubReportDetails3(int year, int month, String employeeId) {
		return employeeAttendanceRepository.loadSubReportDetails3(year, month, employeeId);
	}
	
	public EmployeeAttendance findEmployeeAttendanceByDetails(String date, String shiftId, String employeeId) {
		return employeeAttendanceRepository.findEmployeeAttendanceByDetails(date, shiftId, employeeId);
	}

	public String[][] loadAttendanceRecordsByDate(String date) {
		return employeeAttendanceRepository.loadAttendanceRecordsByDate(date);
	}

	public String[][] loadAttendanceRecordsByDepartment(String date, String departmentId) {
		return employeeAttendanceRepository.loadAttendanceRecordsByDepartment(date, departmentId);
	}

	public String[][] loadAttendanceRecordsByShift(String date, String shiftId) {
		return employeeAttendanceRepository.loadAttendanceRecordsByShift(date, shiftId);
	}

	public String[][] loadAttendanceRecordsByDepartmentAndShift(String date, String departmentId, String shiftId) {
		return employeeAttendanceRepository.loadAttendanceRecordsByDepartmentAndShift(date, departmentId, shiftId);
	}

	public String[][] loadAttendanceRecordsByEmployee(String date, String departmentId, String employeeId) {
		return employeeAttendanceRepository.loadAttendanceRecordsByEmployee(date, departmentId, employeeId);
	}

	public String[][] loadAttendanceRecordsByEmployeeAndShift(String date, String departmentId, String employeeId, String shiftId) {
		return employeeAttendanceRepository.loadAttendanceRecordsByEmployeeAndShift(date, departmentId, employeeId, shiftId);
	}
}
