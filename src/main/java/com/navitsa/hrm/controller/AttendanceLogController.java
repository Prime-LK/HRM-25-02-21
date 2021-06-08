package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeAttendanceService;
import com.navitsa.hrm.service.ShiftMasterService;

@Controller
public class AttendanceLogController {

	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ShiftMasterService shiftMasterService;
	
	@GetMapping("/AttendanceLog")
	public String attendanceLogPage() {
		return "hrm/attendanceLog";
	}
	
	@ModelAttribute("depList")
	public List<DepartmentMaster> getAllDeps(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return departmentService.getDepartmentsByCompany(companyId);
	}

	@ModelAttribute("shiftList")
	public List<ShiftMaster> getAllShifts(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return shiftMasterService.loadAllShifts(companyId);
	}

	/*
	 * @ModelAttribute("attendanceList") public List<String>
	 * getAllAttendances(HttpSession session) { String companyId =
	 * session.getAttribute("company.comID").toString(); List<String> attendances =
	 * employeeAttendanceService.loadAttendances(companyId); return attendances; }
	 */

	@GetMapping("/loadAttendanceLog")
	public String loadAttendance(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "shiftId", required = false) String shiftId, Map<String, Object> model,
			HttpSession session) {

		String companyId = session.getAttribute("company.comID").toString();

		if ((departmentId.equals("All") || departmentId == null) && (employeeId.equals("All") || employeeId == null)
				&& (shiftId.equals("All") || shiftId == null)) {
			// Dates
			List<String> attendances = employeeAttendanceService.loadAttendancesByDate(startDate, endDate, companyId);
			model.put("attendanceList", attendances);
		} else if ((employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)) {
			// Department
			List<String> attendances = employeeAttendanceService.loadAttendancesByDepartment(startDate, endDate,
					departmentId, companyId);
			model.put("attendanceList", attendances);
		} else if ((departmentId.equals("All") || departmentId == null)
				&& (employeeId.equals("All") || employeeId == null)) {
			// Shift
			List<String> attendances = employeeAttendanceService.loadAttendancesByShift(startDate, endDate, shiftId,
					companyId);
			model.put("attendanceList", attendances);
		} else if ((employeeId.equals("All") || employeeId == null)) {
			// Department + Shift
			List<String> attendances = employeeAttendanceService.loadAttendancesByDepartmentAndShift(startDate, endDate,
					departmentId, shiftId, companyId);
			model.put("attendanceList", attendances);
		} else if ((shiftId.equals("All") || shiftId == null)) {

			if (!employeeId.equals("All") && !(employeeId == null)) {
				// Employee
				List<String> attendances = employeeAttendanceService.loadAttendancesByEmployee(startDate, endDate,
						departmentId, employeeId, companyId);
				model.put("attendanceList", attendances);
			} else {
				// Department
				System.out.println("Employee ID is All");
				List<String> attendances = employeeAttendanceService.loadAttendancesByDepartment(startDate, endDate,
						departmentId, companyId);
				model.put("attendanceList", attendances);
			}
		} else if ((!employeeId.equals("All"))) {
			// Employee + Shift
			List<String> attendances = employeeAttendanceService.loadAttendancesByEmployeeAndShift(startDate, endDate,
					departmentId, employeeId, shiftId, companyId);
			model.put("attendanceList", attendances);
		}
		return "hrm/attendanceLog";
	}
}
