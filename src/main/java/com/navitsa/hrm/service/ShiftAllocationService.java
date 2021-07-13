package com.navitsa.hrm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.ShiftAllocation;
import com.navitsa.hrm.repository.ShiftAllocationRepository;

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

	/*
	public List<String> loadShiftAllocationsByCompany(String companyId) {
		return shiftAllocationRepository.loadShiftAllocationsByCompany(companyId);
	}
	*/
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

	/*
	public String[][] loadShiftsByDateRange(String startDate, String endDate, String shiftId, String companyId) {
		return shiftAllocationRepository.loadShiftsByDateRange(startDate, endDate, shiftId, companyId);
	}
	*/
	
	public List<ShiftAllocation> getAllShiftAllocationBy(String startDate, String endDate, String employeeID) {
		
		return shiftAllocationRepository.getAllShiftAllocationBy(startDate,endDate,employeeID);
	}

	public ShiftAllocation getShiftBy(String date, String employeeID, String companyID) {
		return shiftAllocationRepository.getShiftBy(date,employeeID,companyID);
	}
	
	public ShiftAllocation findShiftAllocationByCompany(String date, String shiftId, String employeeId,
			String companyId) {
		return shiftAllocationRepository.findShiftAllocationByCompany(date, shiftId, employeeId, companyId);
	}
	
	public List<ShiftAllocation> loadShiftAllocationsByCompany(String companyId) {
		return shiftAllocationRepository.loadShiftAllocationsByCompany(companyId);
	}
	
	public List<ShiftAllocation> loadShiftsByDateRange(String startDate, String endDate, String shiftId, String companyId) {
		return shiftAllocationRepository.loadShiftsByDateRange(startDate, endDate, shiftId, companyId);
	}
	
	public List<ShiftAllocation> loadAllocatedShiftsByDate(String startDate, String endDate, String companyId) {
		return shiftAllocationRepository.loadAllocatedShiftsByDate(startDate, endDate, companyId);
	}

	public List<ShiftAllocation> loadAllocatedShiftsByDepartment(String startDate, String endDate, String departmentId,
			String companyId) {
		return shiftAllocationRepository.loadAllocatedShiftsByDepartment(startDate, endDate, departmentId, companyId);
	}

	public List<ShiftAllocation> loadAllocatedShiftsByShift(String startDate, String endDate, String shiftId,
			String companyId) {
		return shiftAllocationRepository.loadAllocatedShiftsByShift(startDate, endDate, shiftId, companyId);
	}

	public List<ShiftAllocation> loadAllocatedShiftsByDepartmentAndShift(String startDate, String endDate,
			String departmentId, String shiftId, String companyId) {
		return shiftAllocationRepository.loadAllocatedShiftsByDepartmentAndShift(startDate, endDate, departmentId,
				shiftId, companyId);
	}

	public List<ShiftAllocation> loadAllocatedShiftsByEmployee(String startDate, String endDate, String departmentId,
			String employeeId, String companyId) {
		return shiftAllocationRepository.loadAllocatedShiftsByEmployee(startDate, endDate, departmentId, employeeId,
				companyId);
	}

	public List<ShiftAllocation> loadAllocatedShiftsByEmployeeAndShift(String startDate, String endDate,
			String departmentId, String employeeId, String shiftId, String companyId) {
		return shiftAllocationRepository.loadAllocatedShiftsByEmployeeAndShift(startDate, endDate, departmentId,
				employeeId, shiftId, companyId);
	}
}
