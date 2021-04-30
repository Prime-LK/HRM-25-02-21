package com.navitsa.hrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.repository.SettingRepository;

@Service
public class LeaveAndAttendanceChartServive {

	@Autowired
	private SettingRepository setRepo;
	
	public String[][] getDepartmentNameToChart(String date) {
		return setRepo.getDepartmentNameToChart(date);
	}
	
	public String[][] getChartDateRelatedDep(String date) {
		return setRepo.getChartDateRelatedDep(date);
	}
	
	public String[][] getChartMoreData(String date, String depID) {
		return setRepo.getChartMoreData(date, depID);
	}

}
