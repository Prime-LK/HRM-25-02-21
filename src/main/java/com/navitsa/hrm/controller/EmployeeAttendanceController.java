package com.navitsa.hrm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeAttendance;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.ShiftAllocation;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.report.AttendanceRecordBean;
import com.navitsa.hrm.service.CompanyService;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeAttendanceService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.ShiftAllocationService;
import com.navitsa.hrm.service.ShiftMasterService;

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

	@Autowired
	private CompanyService companyService;

	@GetMapping("/EmployeeAttendance")
	public String employeeAttendancePage(Map<String, Object> map) {
		String id = "0000000000".substring(employeeAttendanceService.getMaxAttendanceId().length())
				+ employeeAttendanceService.getMaxAttendanceId();
		map.put("attendanceId", id);
		return "hrm/employeeAttendance";
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

	@GetMapping("/loadEmployeesByDepartment")
	public @ResponseBody List<EmployeeDetails> loadEmployeesByDepartment(@RequestParam("depID") String departmentId,
			HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		List<EmployeeDetails> department = employeeService.filterEmployeesByDepartmentAndCompany(departmentId,
				companyId);
		return department;
	}

	@GetMapping("/loadEmployeeShiftDetails")
	public @ResponseBody ShiftAllocation loadEmployeeShiftDetails(@RequestParam("date") String date,
			@RequestParam("shiftId") String shiftId, @RequestParam("employeeId") String employeeId,
			HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		ShiftAllocation allocation = shiftAllocationService.findShiftAllocationByDetailsByCompany(date, shiftId,
				employeeId, companyId);
		return allocation;
	}

	@GetMapping("/updateEmployeeAttendance")
	public @ResponseBody EmployeeAttendance updateEmployeeAttendance(@RequestParam("attendanceId") String attendanceId,
			HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		EmployeeAttendance attendance = employeeAttendanceService.findAttendanceByIdAndCompany(attendanceId, companyId);
		return attendance;
	}

	@GetMapping("/checkEmployeeShiftAllocation")
	public @ResponseBody boolean checkEmployeeShiftAllocation(@RequestParam("shiftId") String shiftId,
			@RequestParam("date") String date, @RequestParam("employeeId") String employeeId, HttpSession session) {
		boolean result;
		String companyId = session.getAttribute("company.comID").toString();
		EmployeeDetails ed = employeeService.findEmployeeDetailsByEmployeeIdAndCompany(employeeId, companyId);
		ShiftAllocation sa = shiftAllocationService.findShiftAllocationByCompany(date, shiftId, employeeId, companyId);
		System.out.println("SID = " + ed.getShiftmaster().getShiftId());
		System.out.println(sa);
		if (ed.getShiftmaster().getShiftId() == shiftId) {
			result = true;
		} else if (!(sa == null)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	@PostMapping("/saveAttendance")
	public String saveAttendance(@RequestParam("attendanceId") String attendanceId, @RequestParam("departmentId") String departmentId,
			@RequestParam("employeeId") String employeeId, @RequestParam("shiftId") String shiftId,
			@RequestParam("date") String date, @RequestParam("onTime") String onTime,
			@RequestParam("offTime") String offTime, @RequestParam("approved") boolean approved,
			@RequestParam(value = "companyId", required = false) String companyId,
			RedirectAttributes redirectAttributes) {

		EmployeeAttendance attendance = new EmployeeAttendance();
		Employee employee = employeeService.getEmployeeByCompany(employeeId, companyId);
		ShiftMaster shift = shiftMasterService.findShiftByIdAndCompany(shiftId, companyId);
		CompanyMaster company = companyService.findbyCompanyid(companyId);

		try {

			attendance.setAttendanceId(attendanceId);
			attendance.setEmployee(employee);
			attendance.setShiftmaster(shift);
			attendance.setDate(date);
			attendance.setOnTime(onTime);
			attendance.setOffTime(offTime);
			attendance.setApproved(approved);
			attendance.setDepartmentId(departmentId);
			attendance.setCompany(company);
			employeeAttendanceService.saveEmployeeAttendance(attendance);
			redirectAttributes.addFlashAttribute("success", 1);
			return "redirect:/EmployeeAttendance";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("success", 0);
			System.out.println(e);
		}
		return "hrm/employeeAttendance";
	}

	@GetMapping("/loadAttendanceRecords")
	@ResponseBody
	public List<AttendanceRecordBean> loadShiftDetails(@RequestParam("date") String date,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "shiftId", required = false) String shiftId, HttpSession session) {
		System.out.println(date);
		System.out.println(departmentId);
		System.out.println(employeeId);
		System.out.println(shiftId);
		List<AttendanceRecordBean> list = new ArrayList<>();
		String companyId = session.getAttribute("company.comID").toString();
		if (departmentId == null && employeeId == null && shiftId == null) {
			// Dates
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByDate(date, companyId);
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
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByDepartment(date, departmentId,
					companyId);
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
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByShift(date, shiftId, companyId);
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
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByDepartmentAndShift(date, departmentId,
					shiftId, companyId);
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
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByEmployee(date, departmentId,
					employeeId, companyId);
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
			String[][] result = employeeAttendanceService.loadAttendanceRecordsByEmployeeAndShift(date, departmentId,
					employeeId, shiftId, companyId);
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