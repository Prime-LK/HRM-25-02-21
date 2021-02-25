package com.prime.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeAttendanceService;

@Controller
public class AttendanceSheetController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;
	
	@GetMapping("/AttendanceSheet")
	public String AttendanceSheetPage() {
		return "attendanceSheet";
	}

	@ModelAttribute("depList")
	public List<DepartmentMaster> getAllDeps() {
		return departmentService.getAllDep();
	}
	
	@GetMapping("/getSheet")
	public String getSheet(@RequestParam("year") String year, @RequestParam("month") String month,
			@RequestParam("employeeId") String employeeId, Map<String, Object> model) {
		List<String> sheet = employeeAttendanceService.loadSubReportDetails(Integer.valueOf(year), Integer.valueOf(month), employeeId);
		model.put("attendanceSheet", sheet);
		return "attendanceSheet";
	}
}
