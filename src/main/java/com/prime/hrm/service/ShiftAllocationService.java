package com.prime.hrm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.ShiftAllocation;
import com.prime.hrm.repository.ShiftAllocationRepository;

@Transactional
@Service
public class ShiftAllocationService {

	@Autowired
	private ShiftAllocationRepository shiftAllocationRepository;

	public void saveShiftAllocation(ShiftAllocation shiftAllocation) {
		shiftAllocationRepository.save(shiftAllocation);
	}

	public List<String> loadShiftAllocation() {
		return shiftAllocationRepository.loadShiftAllocation();
	}

	public List<ShiftAllocation> saveShiftAllocations(List<ShiftAllocation> shiftAllocation) {
		return (List<ShiftAllocation>) shiftAllocationRepository.saveAll(shiftAllocation);
	}
	
	public ShiftAllocation findShiftAllocationByDetails(String date, String shiftId, String employeeId) {
		return shiftAllocationRepository.findShiftAllocationByDetails(date, shiftId, employeeId);
	}

	public String[][] loadShiftDetailReportByDate(String startDate, String endDate) {
		return shiftAllocationRepository.loadShiftDetailReportByDate(startDate, endDate);
	}

	public String[][] loadShiftDetailReportByDepartment(String startDate, String endDate, String departmentId) {
		return shiftAllocationRepository.loadShiftDetailReportByDepartment(startDate, endDate, departmentId);
	}

	public String[][] loadShiftDetailReportByShift(String startDate, String endDate, String shiftId) {
		return shiftAllocationRepository.loadShiftDetailReportByShift(startDate, endDate, shiftId);
	}

	public String[][] loadShiftDetailReportByEmployee(String startDate, String endDate, String departmentId, String employeeId) {
		return shiftAllocationRepository.loadShiftDetailReportByEmployee(startDate, endDate, departmentId, employeeId);
	}

	public String[][] loadShiftDetailReportByDepartmentAndShift(String startDate, String endDate, String departmentId, String shiftId) {
		return shiftAllocationRepository.loadShiftDetailReportByDepartmentAndShift(startDate, endDate, departmentId, shiftId);
	}

	public String[][] loadShiftDetailReportByEmployeeAndShift(String startDate, String endDate, String departmentId, String employeeId, String shiftId) {
		return shiftAllocationRepository.loadShiftDetailReportByEmployeeAndShift(startDate, endDate, departmentId, employeeId, shiftId);
	}
	
	public String[][] loadShiftsByDateRange(String startDate, String endDate, String shiftId) {
		return shiftAllocationRepository.loadShiftsByDateRange(startDate, endDate, shiftId);
	}
}
