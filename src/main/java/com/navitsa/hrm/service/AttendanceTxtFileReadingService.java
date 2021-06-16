package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.AttendanceTxtFileDetail;
import com.navitsa.hrm.entity.AttendanceTxtFileHeader;
import com.navitsa.hrm.repository.AttendanceTxtFileDetailRepo;
import com.navitsa.hrm.repository.AttendanceTxtFileHeaderRepo;

@Service
public class AttendanceTxtFileReadingService {
	
	@Autowired
	private AttendanceTxtFileHeaderRepo repo;
	
	@Autowired
	private AttendanceTxtFileDetailRepo repo1;
	

	public void saveAttendanceLogHeader(String no, char machine, String fileName, int totalRecords, String employeeNo,
			String companyId) {
		AttendanceTxtFileHeader obj = new AttendanceTxtFileHeader(no, machine, fileName, totalRecords, employeeNo, companyId);
		repo.save(obj);
		
	}

	public void saveAttendanceLogDetail(String no, String inout_date, String inout_time) {
		AttendanceTxtFileDetail obj = new AttendanceTxtFileDetail(no, inout_date, inout_time);
		repo1.save(obj);
	}

//	public AttendanceTxtFileHeader findByEmployeeNo(String employeeNo) {
//		return repo.findByEmployeeNo(employeeNo);
//		
//	}
	
	public void saveAllAttendanceLogDetail(List<AttendanceTxtFileDetail> ls) {
		repo1.saveAll(ls);
	}

	public List<AttendanceTxtFileDetail> getAttendanceRecords(String startDate, String endDate, String employeeID) {
		return repo1.getAttendanceRecords(startDate,endDate,employeeID);
		
	}

}
