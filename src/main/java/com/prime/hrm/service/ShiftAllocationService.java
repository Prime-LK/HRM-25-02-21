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

	public List<String> loadShiftAllocationsByCompany(String companyId) {
		return shiftAllocationRepository.loadShiftAllocationsByCompany(companyId);
	}

	public List<ShiftAllocation> saveShiftAllocations(List<ShiftAllocation> shiftAllocation) {
		return (List<ShiftAllocation>) shiftAllocationRepository.saveAll(shiftAllocation);
	}

	public ShiftAllocation findShiftAllocationByDetails(String date, String shiftId, String employeeId) {
		return shiftAllocationRepository.findShiftAllocationByDetails(date, shiftId, employeeId);
	}

	public ShiftAllocation findShiftAllocationByDetailsByCompany(String date, String shiftId, String employeeId,
			String companyId) {
		return shiftAllocationRepository.findShiftAllocationByDetailsByCompany(date, shiftId, employeeId, companyId);
	}

	public String[][] loadShiftDetailReportByDate(String startDate, String endDate, String companyId) {
		return shiftAllocationRepository.loadShiftDetailReportByDate(startDate, endDate, companyId);
	}

	public String[][] loadShiftDetailReportByDepartment(String startDate, String endDate, String departmentId,
			String companyId) {
		return shiftAllocationRepository.loadShiftDetailReportByDepartment(startDate, endDate, departmentId, companyId);
	}

	public String[][] loadShiftDetailReportByShift(String startDate, String endDate, String shiftId, String companyId) {
		return shiftAllocationRepository.loadShiftDetailReportByShift(startDate, endDate, shiftId, companyId);
	}

	public String[][] loadShiftDetailReportByEmployee(String startDate, String endDate, String departmentId,
			String employeeId, String companyId) {
		return shiftAllocationRepository.loadShiftDetailReportByEmployee(startDate, endDate, departmentId, employeeId,
				companyId);
	}

	public String[][] loadShiftDetailReportByDepartmentAndShift(String startDate, String endDate, String departmentId,
			String shiftId, String companyId) {
		return shiftAllocationRepository.loadShiftDetailReportByDepartmentAndShift(startDate, endDate, departmentId,
				shiftId, companyId);
	}

	public String[][] loadShiftDetailReportByEmployeeAndShift(String startDate, String endDate, String departmentId,
			String employeeId, String shiftId, String companyId) {
		return shiftAllocationRepository.loadShiftDetailReportByEmployeeAndShift(startDate, endDate, departmentId,
				employeeId, shiftId, companyId);
	}

	public String[][] loadShiftsByDateRange(String startDate, String endDate, String shiftId, String companyId) {
		return shiftAllocationRepository.loadShiftsByDateRange(startDate, endDate, shiftId, companyId);
	}
}
