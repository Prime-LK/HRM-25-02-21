package com.prime.hrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.prime.hrm.service.EmployeeAttendanceService;

@Controller
public class AttendanceLogController {

	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;

	@GetMapping("/AttendanceLog")
	public String attendanceLogPage() {
		return "attendanceLog";
	}

	@ModelAttribute("attendanceList")
	public List<String> getAllAttendances() {
		List<String> attendances = employeeAttendanceService.loadAttendances();
		return attendances;
	}
}
