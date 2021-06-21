package com.navitsa.hrm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.CalanderEntity;
import com.navitsa.hrm.repository.CalanderRepository;

@Service
public class CalanderService {

	@Autowired
	private CalanderRepository calanderRepo;

	public List<CalanderEntity> getAll() {
		return (List<CalanderEntity>) calanderRepo.findAll();
	}

	public void savecalander(CalanderEntity calander) {
		calanderRepo.save(calander);
	}

	public CalanderEntity getRm(String id) {
		return calanderRepo.findById(id).get();
	}

	public CalanderEntity getCalenderDetails(String date, String companyId) {
		return calanderRepo.getCalanderDetails(date, companyId);
	}

	public CalanderEntity setCalenderDetails(String date, String companyId) {
		return calanderRepo.getCalanderDetails(date, companyId);
	}

	public List<CalanderEntity> saveEmpMoDe(List<CalanderEntity> list) {
		return (List<CalanderEntity>) calanderRepo.saveAll(list);
	}

	public CalanderEntity update(String date) {

		return calanderRepo.findById(date).get();
	}

	public List<CalanderEntity> saveAll(List<CalanderEntity> date) {
		return (List<CalanderEntity>) calanderRepo.saveAll(date);
	}

	public CalanderEntity getCalenderByCompany(String date, String companyId) {
		return calanderRepo.getCalenderByCompany(date, companyId);
	}

	public List<Date> getHolidays(String startDate, String endDate) {
		
		return calanderRepo.getHolidays(startDate,endDate);
	}

	public CalanderEntity isHoliday(String date) {
		return calanderRepo.isHoliday(date);
		
	}
}