package com.prime.hrm.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prime.hrm.entity.AttendanceRevise;
import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.EmployeeAttendance;
import com.prime.hrm.entity.ShiftAllocation;
import com.prime.hrm.entity.ShiftMaster;
import com.prime.hrm.service.AttendanceReviseService;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeAttendanceService;
import com.prime.hrm.service.ShiftMasterService;

@Controller
public class AttendanceReviseController {

	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;
	
	@Autowired
	private AttendanceReviseService attendanceReviseService;
	
	@Autowired
	private ShiftMasterService shiftMasterService;
	
	@GetMapping("/AttendanceRevise")
	public String AttendanceReportPage() {
		return "attendanceRevise";
	}
	
	@ModelAttribute("shiftList")
	public List<ShiftMaster> getAllShifts() {
		return shiftMasterService.findAllShifts();
	}
	
	@ModelAttribute("depList")
	public List<DepartmentMaster> getAllDeps() {
		return departmentService.getAllDep();
	}
	
	@ModelAttribute("revisesList")
	public List<String> getAllAttendanceRevises() {
		List<String> revises = attendanceReviseService.loadAttendanceRevises();
		return revises;
	}

	@ModelAttribute("reviseId")
	public String setReviseCode() {
		String id = "0000000000".substring(attendanceReviseService.getMaxReviseId().length()) + attendanceReviseService.getMaxReviseId();
		return id;
	}
	
	@GetMapping("/loadEmployeeAttendanceDetails")
	public @ResponseBody EmployeeAttendance loadEmployeeAttendanceDetails(@RequestParam("date") String date,
			@RequestParam("shiftId") String shiftId, @RequestParam("employeeId") String employeeId) {
		EmployeeAttendance attendance = employeeAttendanceService.findEmployeeAttendanceByDetails(date, shiftId, employeeId);
		System.out.println("Method Loaded");
		return attendance;
	}
	
	@PostMapping("/submitAttendanceRevise")
	public String submitAttendanceRevise(@RequestParam("reviseId") String reviseId, @RequestParam("attendanceId") String attendanceId,
			@RequestParam("shiftId") String shiftId, @RequestParam("employeeId") String employeeId,
			@RequestParam("date") String date, @RequestParam("onTime") String onTime,
			@RequestParam("newOnTime") String newOnTime, @RequestParam("newOffTime") String newOffTime,
			@RequestParam("offTime") String offTime, @RequestParam("remark") String remark, 
			@RequestParam("approved") boolean approved, 
			@RequestParam(value = "companyId", required = false) String companyId) {

		AttendanceRevise revise = new AttendanceRevise();
		LocalDate submitDate = LocalDate.now();

		try {
			revise.setReviseId(reviseId);
			revise.setSubmitDate(Date.valueOf(submitDate));
			revise.setAttendanceId(attendanceId);
			revise.setShiftId(shiftId);
			revise.setEmployeeId(employeeId);
			revise.setDate(Date.valueOf(date));
			revise.setOnTime(onTime);
			revise.setOffTime(offTime);
			revise.setNewOnTime(newOnTime);
			revise.setNewOffTime(newOffTime);
			revise.setRemark(remark);
			revise.setApproved(approved);
			revise.setCompanyId(companyId);
			attendanceReviseService.saveAttendanceRevise(revise);

			return "redirect:/AttendanceRevise";

		} catch (Exception e) {
			System.out.println("Error");
		}
		return "AttendanceRevise";
	}
	
	@GetMapping("/getAttendanceSheet")
	public String getAttendanceSheet(@RequestParam("year") String year, @RequestParam("month") String month,
			@RequestParam("employeeId") String employeeId, Map<String, Object> model) {
		List<String> attendanceSheet = employeeAttendanceService.loadSubReportDetails(Integer.valueOf(year), Integer.valueOf(month), employeeId);
		model.put("employeeAttendanceSheet", attendanceSheet);
		return "attendanceRevise";
	}
}
