package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.repository.ShiftMasterRepository;

@Service
public class ShiftMasterService {

	@Autowired
	private ShiftMasterRepository shiftMasterRepository;

	public void saveShift(ShiftMaster shift) {
		shiftMasterRepository.save(shift);
	}

	public List<ShiftMaster> findAllShifts() {
		return (List<ShiftMaster>) shiftMasterRepository.findAll();
	}

	public List<ShiftMaster> loadAllShifts(String companyId) {
		return (List<ShiftMaster>) shiftMasterRepository.loadAllShifts(companyId);
	}
	
	public List<ShiftMaster> findAllShiftsByCompany(String companyId) {
		return (List<ShiftMaster>) shiftMasterRepository.findAllShiftsByCompany(companyId);
	}

	public ShiftMaster findShiftById(String shiftId) {
		return shiftMasterRepository.findById(shiftId).get();
	}

	public ShiftMaster findShiftByIdAndCompany(String shiftId, String companyId) {
		return shiftMasterRepository.findShiftByIdAndCompany(shiftId, companyId);
	}

	public String getMaxShiftId() {
		if (shiftMasterRepository.maxShiftId() == null) {
			return "1";
		} else {
			return shiftMasterRepository.maxShiftId();
		}
	}
}
