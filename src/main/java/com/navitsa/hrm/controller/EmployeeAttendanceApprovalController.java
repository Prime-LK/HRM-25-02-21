package com.navitsa.hrm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	private static List<EmployeeAttendance> attendances;

	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ShiftMasterService shiftMasterService;

	@GetMapping("/EmployeeAttendanceApproval")
	public String employeeAttendanceApprovalPage(Model model) {
		// model.addAttribute("approveForm", new EmployeeAttendanceApproveForm());
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

		int status = Integer.valueOf(approvalStatus);

		List<String> attendances = new ArrayList<>();
		if ((departmentId.equals("all") || departmentId == null) && (employeeId.equals("all") || employeeId == null)
				&& (shiftId.equals("all") || shiftId == null) && (approvalStatus == null)) {
			// Dates
			attendances = employeeAttendanceService.loadAttendancesByDateAndApprovalStatusNative(startDate, endDate,
					status, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByDate(startDate, endDate,
			 * companyId);
			 */
		} else if ((employeeId.equals("all") || employeeId == null) && (shiftId.equals("all") || shiftId == null)
				&& (approvalStatus == null)) {
			// Department
			attendances = employeeAttendanceService.loadAttendancesByDateAndDepartmentAndApprovalStatusNative(startDate,
					endDate, departmentId, status, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByDepartment(startDate, endDate,
			 * departmentId, companyId);
			 */
		} else if ((departmentId.equals("all") || departmentId == null)
				&& (employeeId.equals("all") || employeeId == null) && (approvalStatus == null)) {
			// Shift
			attendances = employeeAttendanceService.loadAttendancesByDateAndShiftAndApprovalStatusNative(startDate,
					endDate, shiftId, status, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByShift(startDate, endDate, shiftId,
			 * companyId);
			 */
		} else if ((departmentId.equals("all") || departmentId == null)
				&& (employeeId.equals("all") || employeeId == null) && (shiftId.equals("all") || shiftId == null)) {
			// Approval Status
			attendances = employeeAttendanceService.loadAttendancesByDateAndApprovalStatusNative(startDate, endDate,
					status, companyId);
		} else if ((employeeId.equals("all") || employeeId == null) && (approvalStatus == null)) {
			// Department + Shift
			attendances = employeeAttendanceService.loadAttendancesByDateAndDepartmentAndShiftAndApprovalStatusNative(
					startDate, endDate, departmentId, shiftId, status, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByDepartmentAndShift(startDate,
			 * endDate, departmentId, shiftId, companyId);
			 */
		} else if ((employeeId.equals("all") || employeeId == null) && (shiftId.equals("all") || shiftId == null)) {
			// Department + Approval Status
			attendances = employeeAttendanceService.loadAttendancesByDateAndDepartmentAndApprovalStatusNative(startDate,
					endDate, departmentId, status, companyId);
		} else if ((departmentId.equals("all") || departmentId == null)
				&& (employeeId.equals("all") || employeeId == null)) {
			// Shift + Approval Status
			attendances = employeeAttendanceService.loadAttendancesByDateAndShiftAndApprovalStatusNative(startDate,
					endDate, shiftId, status, companyId);
		} else if ((employeeId.equals("all") || employeeId == null)) {
			// Department + Shift + Approval Status
			attendances = employeeAttendanceService.loadAttendancesByDateAndDepartmentAndShiftAndApprovalStatusNative(
					startDate, endDate, departmentId, shiftId, status, companyId);
		} else if ((shiftId.equals("all") || shiftId == null) && (approvalStatus == null)) {

			if (!employeeId.equals("all") && !(employeeId == null)) {
				// Employee
				System.out.println("Employee ID is not all " + employeeId);
				attendances = employeeAttendanceService.loadAttendancesByEmployeeAndApprovalStatusNative(startDate,
						endDate, departmentId, employeeId, status, companyId);
				/*
				 * List<String> attendances =
				 * employeeAttendanceService.loadAttendancesByEmployee(startDate, endDate,
				 * departmentId, employeeId, companyId);
				 */
			} else {
				// Department
				System.out.println("Employee ID is all");
				attendances = employeeAttendanceService.loadAttendancesByDateAndDepartmentAndApprovalStatusNative(
						startDate, endDate, departmentId, status, companyId);
				/*
				 * List<String> attendances =
				 * employeeAttendanceService.loadAttendancesByDepartment(startDate, endDate,
				 * departmentId, companyId);
				 */
			}
		} else if ((!employeeId.equals("all")) && (approvalStatus == null)) {
			// Employee + Shift
			attendances = employeeAttendanceService.loadAttendancesByEmployeeAndShiftAndApprovalStatusNative(startDate,
					endDate, departmentId, employeeId, shiftId, status, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByEmployeeAndShift(startDate,
			 * endDate, departmentId, employeeId, shiftId, companyId);
			 */
		} else if ((!employeeId.equals("all")) && (shiftId.equals("all") || shiftId == null)) {
			// Employee + Approval Status
			attendances = employeeAttendanceService.loadAttendancesByEmployeeAndApprovalStatusNative(startDate, endDate,
					departmentId, employeeId, status, companyId);
		} else if ((!employeeId.equals("all"))) {
			// Employee + Shift + Approval Status
			attendances = employeeAttendanceService.loadAttendancesByEmployeeAndShiftAndApprovalStatusNative(startDate,
					endDate, departmentId, employeeId, shiftId, status, companyId);
		}
		model.put("filteredAttendanceList", attendances);
		return "hrm/employeeAttendanceApproval";
	}

	@PostMapping("/approveAttendance")
	public String approveAttendance(@ModelAttribute("approveForm") EmployeeAttendanceApproveForm approveForm) {
		List<EmployeeAttendance> attendances = approveForm.getAttendances();
		if (null != attendances && attendances.size() > 0) {
			EmployeeAttendanceApprovalController.attendances = attendances;
			List<EmployeeAttendance> list = new ArrayList<>();
			for (int i = 0; i < attendances.size(); i++) {
				EmployeeAttendance ea = employeeAttendanceService.findAttendanceByIdAndCompany(
						attendances.get(i).getAttendanceId(), attendances.get(i).getCompany().getComID());
				ea.setApproved(attendances.get(i).isApproved());
				list.add(ea);
			}
			employeeAttendanceService.saveEmployeeAttendance(attendances);
		}
		return "redirect:/EmployeeAttendanceApproval";
	}
}
