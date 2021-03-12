package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.ShiftMaster;
import com.prime.hrm.repository.ShiftMasterRepository;

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

	public ShiftMaster findShiftById(String shiftId) {
		return shiftMasterRepository.findById(shiftId).get();
	}

	public ShiftMaster findShiftById2(String shiftId, String companyId) {
		return shiftMasterRepository.findShiftById(shiftId, companyId);
	}

	public String getMaxShiftId(String companyId) {
		if (shiftMasterRepository.maxShiftId(companyId) == null) {
			return "1";
		} else {
			return shiftMasterRepository.maxShiftId(companyId);
		}
	}
}
