package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.AttendanceSummary;
import com.navitsa.hrm.entity.AttendanceSummaryDetail;
import com.navitsa.hrm.repository.AttendanceSummaryDetailRepository;
import com.navitsa.hrm.repository.AttendanceSummaryRepository;

@Service
public class AttendanceSummaryService {

	@Autowired
	private AttendanceSummaryRepository attendanceSummaryRepo;

	@Autowired
	private AttendanceSummaryDetailRepository attendanceSummaryDetailRepo;
	
	public String getMaxID() {
		if(attendanceSummaryRepo.getMaxID() == null)
			return "1";
		else
			return attendanceSummaryRepo.getMaxID();
	}
	
	public void saveSummary(AttendanceSummary obj) {
		attendanceSummaryRepo.save(obj);
	}

	public void saveSummaryDetail(List<AttendanceSummaryDetail> ls) {
		attendanceSummaryDetailRepo.saveAll(ls);
	}
	
}
