package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeAttendanceService;

@Controller
public class AttendanceSheetController {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;

	@GetMapping("/AttendanceSheet")
	public String AttendanceSheetPage() {
		return "hrm/attendanceSheet";
	}

	@ModelAttribute("depList")
	public List<DepartmentMaster> getAllDeps(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return departmentService.getDepartmentsByCompany(companyId);
	}

	@GetMapping("/getSheet")
	public String getSheet(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, 
			@RequestParam("employeeId") String employeeId, Map<String, Object> model, HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		List<String> sheet = employeeAttendanceService.loadAttendanceSubReportDetails2(startDate, endDate,
				employeeId, companyId);
		model.put("attendanceSheet", sheet);
		return "hrm/attendanceSheet";
	}
}
