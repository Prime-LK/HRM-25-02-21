package com.prime.hrm.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.EmployeeAttendance;
import com.prime.hrm.entity.EmployeeDetails;
import com.prime.hrm.entity.ShiftAllocation;
import com.prime.hrm.entity.ShiftMaster;
import com.prime.hrm.report.AttendanceRecordBean;
import com.prime.hrm.report.ShiftDetailReportBean;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeAttendanceService;
import com.prime.hrm.service.EmployeeService;
import com.prime.hrm.service.ShiftAllocationService;
import com.prime.hrm.service.ShiftMasterService;

@Controller
public class EmployeeAttendanceController {

	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ShiftMasterService shiftMasterService;

	@Autowired
	private ShiftAllocationService shiftAllocationService;

	@GetMapping("/EmployeeAttendance")
	public String employeeAttendancePage() {
		return "employeeAttendance";
	}

	@ModelAttribute("depList")
	public List<DepartmentMaster> getAllDeps() {
		return departmentService.getAllDep();
	}

	@ModelAttribute("shiftList")
	public List<ShiftMaster> getAllShifts() {
		return shiftMasterService.findAllShifts();
	}

	/*
	@ModelAttribute("attendanceList")
	public List<String> getAllAttendances() {
		List<String> attendances = employeeAttendanceService.loadAttendances();
		return attendances;
	}
	*/

	@ModelAttribute("attendanceId")
	public String setAttendanceCode() {
		String id = "0000000000".substring(employeeAttendanceService.getMaxAttendanceId().length()) + employeeAttendanceService.getMaxAttendanceId();
		return id;
	}

	@GetMapping("/loadEmployeesByDepartment")
	public @ResponseBody List<EmployeeDetails> loadEmployeesByDepartment(@RequestParam("depID") String departmentId) {
		List<EmployeeDetails> department = employeeService.filterRelatedData(departmentId);
		return department;
	}

	@GetMapping("/loadEmployeeShiftDetails")
	public @ResponseBody ShiftAllocation loadEmployeeShiftDetails(@RequestParam("date") String date,
			@RequestParam("shiftId") String shiftId, @RequestParam("employeeId") String employeeId) {
		ShiftAllocation allocation = shiftAllocationService.findShiftAllocationByDetails(date, shiftId, employeeId);
		System.out.println("Method Loaded");
		return allocation;
	}

	@GetMapping("/updateEmployeeAttendance")
	public @ResponseBody EmployeeAttendance updateEmployeeAttendance(
			@RequestParam("attendanceId") String attendanceId) {
		EmployeeAttendance attendance = employeeAttendanceService.findAttendanceById(attendanceId);
		return attendance;
	}

	@PostMapping("/saveAttendance")
	public String saveAttendance(@RequestParam("attendanceId") String attendanceId,
			@RequestParam("employeeId") String employeeId, @RequestParam("shiftId") String shiftId,
			@RequestParam("date") String date, @RequestParam("onTime") String onTime,
			@RequestParam("offTime") String offTime, @RequestParam("approved") boolean approved,
			@RequestParam(value = "companyId", required = false) String companyId) {

		EmployeeAttendance attendance = new EmployeeAttendance();
		Date date1 = Date.valueOf(date);

		try {

			attendance.setAttendanceId(attendanceId);
			attendance.setEmployeeId(employeeId);
			attendance.setShiftId(shiftId);
			attendance.setDate(date1);
			attendance.setOnTime(onTime);
			attendance.setOffTime(offTime);
			attendance.setApproved(approved);
			attendance.setCompanyId(companyId);
			employeeAttendanceService.saveEmployeeAttendance(attendance);

			return "redirect:/EmployeeAttendance";

		} catch (Exception e) {
			System.out.println("Error");
		}
		return "EmployeeAttendance";
	}
	
	@GetMapping("/loadAttendanceRecords")
	@ResponseBody
	public List<AttendanceRecordBean> loadShiftDetails(@RequestParam("date") String date,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "shiftId", required = false) String shiftId) {
		System.out.println(date);
		System.out.println(departmentId);
		System.out.println(employeeId);
		System.out.println(shiftId);
		List<AttendanceRecordBean> list = new ArrayList<>();
		if(departmentId == null && employeeId == null && shiftId == null) {
			//Dates
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByDate(date);
			for (int i = 0; i < result.length; i++) {
				AttendanceRecordBean attendanceRecordBean = new AttendanceRecordBean();
				attendanceRecordBean.setAttendanceId(result[i][0]);
				attendanceRecordBean.setDate(result[i][1]);
				attendanceRecordBean.setShiftId(result[i][2]);
				attendanceRecordBean.setShift(result[i][3]);
				attendanceRecordBean.setDepartmentId(result[i][4]);
				attendanceRecordBean.setDepartment(result[i][5]);
				attendanceRecordBean.setEmployeeId(result[i][6]);
				attendanceRecordBean.setEmployee(result[i][7]);
				attendanceRecordBean.setStartTime(result[i][8]);
				attendanceRecordBean.setEndTime(result[i][9]);
				attendanceRecordBean.setOnTime(result[i][10]);
				attendanceRecordBean.setOffTime(result[i][11]);
				attendanceRecordBean.setApprovalStatus(result[i][12]);
				list.add(attendanceRecordBean);
			}
			return list;
		} else if ((employeeId == null || employeeId.equals("All")) && shiftId == null) {
			// Department
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByDepartment(date, departmentId);
			for (int i = 0; i < result.length; i++) {
				AttendanceRecordBean attendanceRecordBean = new AttendanceRecordBean();
				attendanceRecordBean.setAttendanceId(result[i][0]);
				attendanceRecordBean.setDate(result[i][1]);
				attendanceRecordBean.setShiftId(result[i][2]);
				attendanceRecordBean.setShift(result[i][3]);
				attendanceRecordBean.setDepartmentId(result[i][4]);
				attendanceRecordBean.setDepartment(result[i][5]);
				attendanceRecordBean.setEmployeeId(result[i][6]);
				attendanceRecordBean.setEmployee(result[i][7]);
				attendanceRecordBean.setStartTime(result[i][8]);
				attendanceRecordBean.setEndTime(result[i][9]);
				attendanceRecordBean.setOnTime(result[i][7]);
				attendanceRecordBean.setOffTime(result[i][8]);
				attendanceRecordBean.setApprovalStatus(result[i][9]);
				list.add(attendanceRecordBean);
			}
			return list;
		} else if (departmentId == null && (employeeId == null || employeeId.equals("All"))) {
			// Shift
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByShift(date, shiftId);
			for (int i = 0; i < result.length; i++) {
				AttendanceRecordBean attendanceRecordBean = new AttendanceRecordBean();
				attendanceRecordBean.setAttendanceId(result[i][0]);
				attendanceRecordBean.setDate(result[i][1]);
				attendanceRecordBean.setShiftId(result[i][2]);
				attendanceRecordBean.setShift(result[i][3]);
				attendanceRecordBean.setDepartmentId(result[i][4]);
				attendanceRecordBean.setDepartment(result[i][5]);
				attendanceRecordBean.setEmployeeId(result[i][6]);
				attendanceRecordBean.setEmployee(result[i][7]);
				attendanceRecordBean.setStartTime(result[i][8]);
				attendanceRecordBean.setEndTime(result[i][9]);
				attendanceRecordBean.setOnTime(result[i][7]);
				attendanceRecordBean.setOffTime(result[i][8]);
				attendanceRecordBean.setApprovalStatus(result[i][9]);
				list.add(attendanceRecordBean);
			}
			return list;
		} else if (employeeId == null || employeeId.equals("All")) {
			// Department + Shift
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByDepartmentAndShift(date, departmentId, shiftId);
			for (int i = 0; i < result.length; i++) {
				AttendanceRecordBean attendanceRecordBean = new AttendanceRecordBean();
				attendanceRecordBean.setAttendanceId(result[i][0]);
				attendanceRecordBean.setDate(result[i][1]);
				attendanceRecordBean.setShiftId(result[i][2]);
				attendanceRecordBean.setShift(result[i][3]);
				attendanceRecordBean.setDepartmentId(result[i][4]);
				attendanceRecordBean.setDepartment(result[i][5]);
				attendanceRecordBean.setEmployeeId(result[i][6]);
				attendanceRecordBean.setEmployee(result[i][7]);
				attendanceRecordBean.setStartTime(result[i][8]);
				attendanceRecordBean.setEndTime(result[i][9]);
				attendanceRecordBean.setOnTime(result[i][7]);
				attendanceRecordBean.setOffTime(result[i][8]);
				attendanceRecordBean.setApprovalStatus(result[i][9]);
				list.add(attendanceRecordBean);
			}
			return list;
		} else if (!employeeId.equals("All") && shiftId == null) {
			// Employee
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByEmployee(date, departmentId, employeeId);
			for (int i = 0; i < result.length; i++) {
				AttendanceRecordBean attendanceRecordBean = new AttendanceRecordBean();
				attendanceRecordBean.setAttendanceId(result[i][0]);
				attendanceRecordBean.setDate(result[i][1]);
				attendanceRecordBean.setShiftId(result[i][2]);
				attendanceRecordBean.setShift(result[i][3]);
				attendanceRecordBean.setDepartmentId(result[i][4]);
				attendanceRecordBean.setDepartment(result[i][5]);
				attendanceRecordBean.setEmployeeId(result[i][6]);
				attendanceRecordBean.setEmployee(result[i][7]);
				attendanceRecordBean.setStartTime(result[i][8]);
				attendanceRecordBean.setEndTime(result[i][9]);
				attendanceRecordBean.setOnTime(result[i][7]);
				attendanceRecordBean.setOffTime(result[i][8]);
				attendanceRecordBean.setApprovalStatus(result[i][9]);
				list.add(attendanceRecordBean);
			}
			return list;
		} else {
			// Employee + Shift
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByEmployeeAndShift(date, departmentId, employeeId, shiftId);
			for (int i = 0; i < result.length; i++) {
				AttendanceRecordBean attendanceRecordBean = new AttendanceRecordBean();
				attendanceRecordBean.setAttendanceId(result[i][0]);
				attendanceRecordBean.setDate(result[i][1]);
				attendanceRecordBean.setShiftId(result[i][2]);
				attendanceRecordBean.setShift(result[i][3]);
				attendanceRecordBean.setDepartmentId(result[i][4]);
				attendanceRecordBean.setDepartment(result[i][5]);
				attendanceRecordBean.setEmployeeId(result[i][6]);
				attendanceRecordBean.setEmployee(result[i][7]);
				attendanceRecordBean.setStartTime(result[i][8]);
				attendanceRecordBean.setEndTime(result[i][9]);
				attendanceRecordBean.setOnTime(result[i][7]);
				attendanceRecordBean.setOffTime(result[i][8]);
				attendanceRecordBean.setApprovalStatus(result[i][9]);
				list.add(attendanceRecordBean);
			}
			return list;
		}
	}
}