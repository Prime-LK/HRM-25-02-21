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

import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.EmployeeAttendance;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.report.EmployeeAttendanceApproveForm;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeAttendanceService;
import com.navitsa.hrm.service.ShiftMasterService;

@Controller
public class EmployeeAttendanceApprovalController {

	@Autowired
	private static List<EmployeeAttendance> attendances = new ArrayList<EmployeeAttendance>();

	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ShiftMasterService shiftMasterService;

	@GetMapping("/EmployeeAttendanceApproval")
	public String employeeAttendanceApprovalPage() {
		return "hrm/employeeAttendanceApproval";
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

	@GetMapping("/loadAttendance")
	public String loadAttendance(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "shiftId", required = false) String shiftId,
			@RequestParam(value = "approvalStatus", required = false) String approvalStatus, Map<String, Object> model,
			HttpSession session) {

		String companyId = session.getAttribute("company.comID").toString();

		if ((departmentId.equals("All") || departmentId == null) && (employeeId.equals("All") || employeeId == null)
				&& (shiftId.equals("All") || shiftId == null) && (approvalStatus == null)) {
			// Dates
			List<String> attendances = employeeAttendanceService.loadAttendancesByApprovalStatus(startDate, endDate, 0,
					companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByDate(startDate, endDate,
			 * companyId);
			 */
			model.put("filteredAttendanceList", attendances);
		} else if ((employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)
				&& (approvalStatus == null)) {
			// Department
			List<String> attendances = employeeAttendanceService.loadAttendancesByDepartmentAndApprovalStatus(startDate,
					endDate, departmentId, 0, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByDepartment(startDate, endDate,
			 * departmentId, companyId);
			 */
			model.put("filteredAttendanceList", attendances);
		} else if ((departmentId.equals("All") || departmentId == null)
				&& (employeeId.equals("All") || employeeId == null) && (approvalStatus == null)) {
			// Shift
			List<String> attendances = employeeAttendanceService.loadAttendancesByShiftAndApprovalStatus(startDate,
					endDate, shiftId, 0, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByShift(startDate, endDate, shiftId,
			 * companyId);
			 */
			model.put("filteredAttendanceList", attendances);
		} else if ((departmentId.equals("All") || departmentId == null)
				&& (employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)) {
			// Approval Status
			List<String> attendances = employeeAttendanceService.loadAttendancesByApprovalStatus(startDate, endDate,
					Integer.valueOf(approvalStatus), companyId);
			model.put("filteredAttendanceList", attendances);
		} else if ((employeeId.equals("All") || employeeId == null) && (approvalStatus == null)) {
			// Department + Shift
			List<String> attendances = employeeAttendanceService.loadAttendancesByDepartmentAndApprovalStatus(startDate,
					endDate, departmentId, 0, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByDepartmentAndShift(startDate,
			 * endDate, departmentId, shiftId, companyId);
			 */
			model.put("filteredAttendanceList", attendances);
		} else if ((employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)) {
			// Department + Approval Status
			List<String> attendances = employeeAttendanceService.loadAttendancesByDepartmentAndApprovalStatus(startDate,
					endDate, departmentId, Integer.valueOf(approvalStatus), companyId);
			model.put("filteredAttendanceList", attendances);
		} else if ((departmentId.equals("All") || departmentId == null)
				&& (employeeId.equals("All") || employeeId == null)) {
			// Shift + Approval Status
			List<String> attendances = employeeAttendanceService.loadAttendancesByShiftAndApprovalStatus(startDate,
					endDate, shiftId, Integer.valueOf(approvalStatus), companyId);
			model.put("filteredAttendanceList", attendances);
		} else if ((employeeId.equals("All") || employeeId == null)) {
			// Department + Shift + Approval Status
			List<String> attendances = employeeAttendanceService.loadAttendancesByDepartmentAndShiftAndApprovalStatus(
					startDate, endDate, departmentId, shiftId, Integer.valueOf(approvalStatus), companyId);
			model.put("filteredAttendanceList", attendances);
		} else if ((shiftId.equals("All") || shiftId == null) && (approvalStatus == null)) {
			System.out.println("Dep ID: " + departmentId);
			System.out.println("Emp ID: " + employeeId);
			if (!employeeId.equals("All") && !(employeeId == null)) {
				// Employee
				System.out.println("Employee ID is not All " + employeeId);
				List<String> attendances = employeeAttendanceService.loadAttendancesByEmployeeAndApprovalStatus(
						startDate, endDate, departmentId, employeeId, 0, companyId);
				/*
				 * List<String> attendances =
				 * employeeAttendanceService.loadAttendancesByEmployee(startDate, endDate,
				 * departmentId, employeeId, companyId);
				 */
				model.put("filteredAttendanceList", attendances);
			} else {
				// Department
				System.out.println("Employee ID is All");
				List<String> attendances = employeeAttendanceService
						.loadAttendancesByDepartmentAndApprovalStatus(startDate, endDate, departmentId, 0, companyId);
				/*
				 * List<String> attendances =
				 * employeeAttendanceService.loadAttendancesByDepartment(startDate, endDate,
				 * departmentId, companyId);
				 */
				model.put("filteredAttendanceList", attendances);
			}
		} else if ((!employeeId.equals("All")) && (approvalStatus == null)) {
			// Employee + Shift
			List<String> attendances = employeeAttendanceService.loadAttendancesByEmployeeAndShiftAndApprovalStatus(
					startDate, endDate, departmentId, employeeId, shiftId, 0, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByEmployeeAndShift(startDate,
			 * endDate, departmentId, employeeId, shiftId, companyId);
			 */
			model.put("filteredAttendanceList", attendances);
		} else if ((!employeeId.equals("All")) && (shiftId.equals("All") || shiftId == null)) {
			// Employee + Approval Status
			List<String> attendances = employeeAttendanceService.loadAttendancesByEmployeeAndApprovalStatus(startDate,
					endDate, departmentId, employeeId, Integer.valueOf(approvalStatus), companyId);
			model.put("filteredAttendanceList", attendances);
		} else if ((!employeeId.equals("All"))) {
			// Employee + Shift + Approval Status
			List<String> attendances = employeeAttendanceService.loadAttendancesByEmployeeAndShiftAndApprovalStatus(
					startDate, endDate, departmentId, employeeId, shiftId, Integer.valueOf(approvalStatus), companyId);
			model.put("filteredAttendanceList", attendances);
		}
		return "hrm/employeeAttendanceApproval";
	}

	@PostMapping("/approveAttendance")
	public String approveAttendance(@ModelAttribute("approveForm") EmployeeAttendanceApproveForm approveForm) {
		System.out.println("Called");
		List<EmployeeAttendance> attendances = approveForm.getAttendances();

		if (null != attendances && attendances.size() > 0) {
			EmployeeAttendanceApprovalController.attendances = attendances;
			employeeAttendanceService.saveEmployeeAttendance(attendances);
		}
		return "redirect:/EmployeeAttendanceApproval";
	}
}
