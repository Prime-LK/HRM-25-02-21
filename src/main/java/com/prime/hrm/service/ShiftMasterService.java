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
	
	public List<ShiftMaster> loadAllShifts() {
		return (List<ShiftMaster>) shiftMasterRepository.loadAllShifts();
	}

	public ShiftMaster findShiftById(String shiftId) {
		return shiftMasterRepository.findById(shiftId).get();
	}

	public ShiftMaster findShiftById2(String shiftId) {
		return shiftMasterRepository.findShiftById(shiftId);
	}

	public String getMaxShiftId() {
		if (shiftMasterRepository.maxShiftId() == null) {
			return "1";
		} else {
			return shiftMasterRepository.maxShiftId();
		}
	}
}
