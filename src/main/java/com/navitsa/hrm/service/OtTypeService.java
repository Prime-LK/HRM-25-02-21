package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.OtType;
import com.navitsa.hrm.repository.OtTypeRepository;

@Service
public class OtTypeService {

	@Autowired
	private OtTypeRepository otTypeRepo;

	public List<OtType> findByDayType(String dayType, String companyID) {

		return otTypeRepo.findByDayType(dayType,companyID);
	}
}
