package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.AttendanceRevise;
import com.navitsa.hrm.repository.AttendanceReviseRepository;

@Service
public class AttendanceReviseService {

	@Autowired
	private AttendanceReviseRepository attendanceReviseRepository;

	public String getMaxReviseId(String companyId) {
		if (attendanceReviseRepository.maxReviseId(companyId) == null) {
			return "1";
		} else {
			return attendanceReviseRepository.maxReviseId(companyId);
		}
	}

	public void saveAttendanceRevise(AttendanceRevise attendanceRevise) {
		attendanceReviseRepository.save(attendanceRevise);
	}

	public void saveAttendanceRevise(List<AttendanceRevise> attendanceRevise) {
		attendanceReviseRepository.saveAll(attendanceRevise);
	}

	public List<String> loadAttendanceRevises(String companyId) {
		return attendanceReviseRepository.loadAttendanceRevises(companyId);
	}

	public List<String> loadAttendancesByDate(String startDate, String endDate, String companyId) {
		return attendanceReviseRepository.loadAttendanceRevisesByDate(startDate, endDate, companyId);
	}

	public List<String> loadAttendanceRevisesByApprovalStatus(String startDate, String endDate, int approvalStatus,
			String companyId) {
		return attendanceReviseRepository.loadAttendanceRevisesByApprovalStatus(startDate, endDate, approvalStatus,
				companyId);
	}
}
