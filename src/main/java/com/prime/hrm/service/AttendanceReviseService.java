package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.AttendanceRevise;
import com.prime.hrm.entity.EmployeeAttendance;
import com.prime.hrm.entity.ShiftAllocation;
import com.prime.hrm.repository.AttendanceReviseRepository;
import com.prime.hrm.repository.EmployeeAttendanceRepository;

@Service
public class AttendanceReviseService {
	
	@Autowired
	private AttendanceReviseRepository attendanceReviseRepository;
	
	@Autowired
	private EmployeeAttendanceRepository employeeAttendanceRepository;

	public String getMaxReviseId() {
		if (attendanceReviseRepository.maxReviseId() == null) {
			return "1";
		} else {
			return attendanceReviseRepository.maxReviseId();
		}
	}
	
	public void saveAttendanceRevise(AttendanceRevise attendanceRevise) {
		attendanceReviseRepository.save(attendanceRevise);
	}
	
	public void saveAttendanceRevise(List<AttendanceRevise> attendanceRevise) {
		attendanceReviseRepository.saveAll(attendanceRevise);
	}
	
	public List<String> loadAttendanceRevises() {
		return attendanceReviseRepository.loadAttendanceRevises();
	}
	
	public List<String> loadAttendancesByDate(String startDate, String endDate) {
		return attendanceReviseRepository.loadAttendanceRevisesByDate(startDate, endDate);
	}
	
	public List<String> loadAttendanceRevisesByApprovalStatus(String startDate, String endDate, int approvalStatus) {
		return attendanceReviseRepository.loadAttendanceRevisesByApprovalStatus(startDate, endDate, approvalStatus);
	}
}
