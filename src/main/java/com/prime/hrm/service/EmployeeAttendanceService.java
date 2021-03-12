package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.EmployeeAttendance;
import com.prime.hrm.repository.EmployeeAttendanceRepository;

@Service
public class EmployeeAttendanceService {

	@Autowired
	private EmployeeAttendanceRepository employeeAttendanceRepository;

	public String getMaxAttendanceId(String companyId) {
		if (employeeAttendanceRepository.maxAttendanceId(companyId) == null) {
			return "1";
		} else {
			return employeeAttendanceRepository.maxAttendanceId(companyId);
		}
	}

	public void saveEmployeeAttendance(EmployeeAttendance employeeAttendance) {
		employeeAttendanceRepository.save(employeeAttendance);
	}

	public void saveEmployeeAttendance(List<EmployeeAttendance> employeeAttendance) {
		employeeAttendanceRepository.saveAll(employeeAttendance);
	}

	public List<String> loadAttendances(String companyId) {
		return employeeAttendanceRepository.loadAttendances(companyId);
	}

	public EmployeeAttendance findAttendanceById(String attendanceId) {
		return employeeAttendanceRepository.findById(attendanceId).get();

	}

	public EmployeeAttendance findAttendanceByIdAndCompany(String attendanceId, String companyId) {
		return employeeAttendanceRepository.findAttendanceByIdAndCompany(attendanceId, companyId);
	}

	public List<String> loadAttendancesByDate(String startDate, String endDate, String companyId) {
		return employeeAttendanceRepository.loadAttendancesByDate(startDate, endDate, companyId);
	}

	public List<String> loadAttendancesByDepartment(String startDate, String endDate, String departmentId,
			String companyId) {
		return employeeAttendanceRepository.loadAttendancesByDepartment(startDate, endDate, departmentId, companyId);
	}

	public List<String> loadAttendancesByDepartmentAndShiftAndApprovalStatus(String startDate, String endDate,
			String departmentId, String shiftId, int approvalStatus, String companyId) {
		return employeeAttendanceRepository.loadAttendancesByDepartmentAndShiftAndApprovalStatus(startDate, endDate,
				departmentId, shiftId, approvalStatus, companyId);
	}

	public List<String> loadAttendancesByDepartmentAndShift(String startDate, String endDate, String departmentId,
			String shiftId, String companyId) {
		return employeeAttendanceRepository.loadAttendancesByDepartmentAndShift(startDate, endDate, departmentId,
				shiftId, companyId);
	}

	public List<String> loadAttendancesByDepartmentAndApprovalStatus(String startDate, String endDate,
			String departmentId, int approvalStatus, String companyId) {
		return employeeAttendanceRepository.loadAttendancesByDepartmentAndApprovalStatus(startDate, endDate,
				departmentId, approvalStatus, companyId);
	}

	public List<String> loadAttendancesByEmployee(String startDate, String endDate, String departmentId,
			String employeeId, String companyId) {
		return employeeAttendanceRepository.loadAttendancesByEmployee(startDate, endDate, departmentId, employeeId,
				companyId);
	}

	public List<String> loadAttendancesByEmployeeAndShift(String startDate, String endDate, String departmentId,
			String employeeId, String shiftId, String companyId) {
		return employeeAttendanceRepository.loadAttendancesByEmployeeAndShift(startDate, endDate, departmentId,
				employeeId, shiftId, companyId);
	}

	public List<String> loadAttendancesByEmployeeAndApprovalStatus(String startDate, String endDate,
			String departmentId, String employeeId, int approvalStatus, String companyId) {
		return employeeAttendanceRepository.loadAttendancesByEmployeeAndApprovalStatus(startDate, endDate, departmentId,
				employeeId, approvalStatus, companyId);
	}

	public List<String> loadAttendancesByShift(String startDate, String endDate, String shiftId, String companyId) {
		return employeeAttendanceRepository.loadAttendancesByShift(startDate, endDate, shiftId, companyId);
	}

	public List<String> loadAttendancesByApprovalStatus(String startDate, String endDate, int approvalStatus,
			String companyId) {
		return employeeAttendanceRepository.loadAttendancesByApprovalStatus(startDate, endDate, approvalStatus,
				companyId);
	}

	public List<String> loadAttendancesByShiftAndApprovalStatus(String startDate, String endDate, String shiftId,
			int approvalStatus, String companyId) {
		return employeeAttendanceRepository.loadAttendancesByShiftAndApprovalStatus(startDate, endDate, shiftId,
				approvalStatus, companyId);
	}

	public List<String> loadAttendancesByEmployeeAndShiftAndApprovalStatus(String startDate, String endDate,
			String departmentId, String employeeId, String shiftId, int approvalStatus, String companyId) {
		return employeeAttendanceRepository.loadAttendancesByEmployeeAndShiftAndApprovalStatus(startDate, endDate,
				departmentId, employeeId, shiftId, approvalStatus, companyId);
	}

	public String[][] loadMainReportDetails3(int year, int month, String employeeId, String companyId) {
		return employeeAttendanceRepository.loadMainReportDetails3(year, month, employeeId, companyId);
	}

	public List<String> loadSubReportDetails(int year, int month, String employeeId, String companyId) {
		return employeeAttendanceRepository.loadSubReportDetails(year, month, employeeId, companyId);
	}

	public String[][] loadSubReportDetails3(int year, int month, String employeeId, String companyId) {
		return employeeAttendanceRepository.loadSubReportDetails3(year, month, employeeId, companyId);
	}

	public EmployeeAttendance findEmployeeAttendanceByDetails(String date, String shiftId, String employeeId,
			String companyId) {
		return employeeAttendanceRepository.findEmployeeAttendanceByDetails(date, shiftId, employeeId, companyId);
	}

	public String[][] loadAttendanceRecordsByDate(String date, String companyId) {
		return employeeAttendanceRepository.loadAttendanceRecordsByDate(date, companyId);
	}

	public String[][] loadAttendanceRecordsByDepartment(String date, String departmentId, String companyId) {
		return employeeAttendanceRepository.loadAttendanceRecordsByDepartment(date, departmentId, companyId);
	}

	public String[][] loadAttendanceRecordsByShift(String date, String shiftId, String companyId) {
		return employeeAttendanceRepository.loadAttendanceRecordsByShift(date, shiftId, companyId);
	}

	public String[][] loadAttendanceRecordsByDepartmentAndShift(String date, String departmentId, String shiftId,
			String companyId) {
		return employeeAttendanceRepository.loadAttendanceRecordsByDepartmentAndShift(date, departmentId, shiftId,
				companyId);
	}

	public String[][] loadAttendanceRecordsByEmployee(String date, String departmentId, String employeeId,
			String companyId) {
		return employeeAttendanceRepository.loadAttendanceRecordsByEmployee(date, departmentId, employeeId, companyId);
	}

	public String[][] loadAttendanceRecordsByEmployeeAndShift(String date, String departmentId, String employeeId,
			String shiftId, String companyId) {
		return employeeAttendanceRepository.loadAttendanceRecordsByEmployeeAndShift(date, departmentId, employeeId,
				shiftId, companyId);
	}

	public String[][] loadAttedanceMainReportDetails(String startDate, String endDate, String employeeId,
			String companyId) {
		return employeeAttendanceRepository.loadAttedanceMainReportDetails(startDate, endDate, employeeId, companyId);
	}

	public String[][] loadAttendanceSubReportDetails(String startDate, String endDate, String employeeId,
			String companyId) {
		return employeeAttendanceRepository.loadAttendanceSubReportDetails(startDate, endDate, employeeId, companyId);
	}
}
