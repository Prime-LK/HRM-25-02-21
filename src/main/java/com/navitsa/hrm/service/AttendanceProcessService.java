package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.AttendanceSheet;
import com.navitsa.hrm.repository.AttendanceSheetRepository;

@Service
public class AttendanceProcessService {

	@Autowired
	private AttendanceSheetRepository attendanceSheetRepo;
	
	public void saveAll(List<AttendanceSheet> ls) {
		attendanceSheetRepo.saveAll(ls);
	}

	public List<AttendanceSheet> findBy(String payPeriodID, String employeeID) {		
		return attendanceSheetRepo.findBy(payPeriodID,employeeID);
	}

	public String getTotalLate(String payPeriodID, String employeeID, String companyID) {
		if(attendanceSheetRepo.getTotalLate(payPeriodID,employeeID,companyID)==null)
			return "0";
		else
			return attendanceSheetRepo.getTotalLate(payPeriodID,employeeID,companyID);
	}
	
	public String getTotalOT(String payPeriodID, String employeeID, String companyID) {
		if(attendanceSheetRepo.getTotalOT(payPeriodID,employeeID,companyID)==null)
			return "0";
		else
			return attendanceSheetRepo.getTotalOT(payPeriodID,employeeID,companyID);
	}
	
	public List<AttendanceSheet> getTotalLeave(String payPeriodID, String employeeID, String companyID) {
		return attendanceSheetRepo.getTotalLeave(payPeriodID,employeeID,companyID);
	}

	public String getTotalOTByDayType(String dayType, String payPeriodID, String employeeID, String companyID) {
		if(attendanceSheetRepo.getTotalOTByDayType(dayType, payPeriodID,employeeID,companyID)==null)
			return "0";
		else
			return attendanceSheetRepo.getTotalOTByDayType(dayType, payPeriodID,employeeID,companyID);
	}
}
