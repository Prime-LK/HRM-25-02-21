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
import com.navitsa.hrm.report.AttendanceRecordBean;
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
	public String employeeAttendanceApprovalPage(Model model) {
		//model.addAttribute("approveForm", new EmployeeAttendanceApproveForm());
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
		
		boolean status = false;
		
		if(approvalStatus == "1") {
			 status = true;
		}
		
		List<EmployeeAttendance> attendances = new ArrayList<>();
		if ((departmentId.equals("All") || departmentId == null) && (employeeId.equals("All") || employeeId == null)
				&& (shiftId.equals("All") || shiftId == null) && (approvalStatus == null)) {
			// Dates
			attendances = employeeAttendanceService.loadAttendancesByApprovalStatus(startDate, endDate, status,
					companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByDate(startDate, endDate,
			 * companyId);
			 */
		} else if ((employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)
				&& (approvalStatus == null)) {
			// Department
			attendances = employeeAttendanceService.loadAttendancesByDepartmentAndApprovalStatus(startDate,
					endDate, departmentId, status, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByDepartment(startDate, endDate,
			 * departmentId, companyId);
			 */
		} else if ((departmentId.equals("All") || departmentId == null)
				&& (employeeId.equals("All") || employeeId == null) && (approvalStatus == null)) {
			// Shift
			attendances = employeeAttendanceService.loadAttendancesByShiftAndApprovalStatus(startDate,
					endDate, shiftId, status, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByShift(startDate, endDate, shiftId,
			 * companyId);
			 */
		} else if ((departmentId.equals("All") || departmentId == null)
				&& (employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)) {
			// Approval Status
			attendances = employeeAttendanceService.loadAttendancesByApprovalStatus(startDate, endDate,
					status, companyId);
		} else if ((employeeId.equals("All") || employeeId == null) && (approvalStatus == null)) {
			// Department + Shift
			attendances = employeeAttendanceService.loadAttendancesByDepartmentAndShiftAndApprovalStatus(startDate,
					endDate, departmentId, shiftId, status, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByDepartmentAndShift(startDate,
			 * endDate, departmentId, shiftId, companyId);
			 */
		} else if ((employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)) {
			// Department + Approval Status
			attendances = employeeAttendanceService.loadAttendancesByDepartmentAndApprovalStatus(startDate,
					endDate, departmentId, status, companyId);
		} else if ((departmentId.equals("All") || departmentId == null)
				&& (employeeId.equals("All") || employeeId == null)) {
			// Shift + Approval Status
			attendances = employeeAttendanceService.loadAttendancesByShiftAndApprovalStatus(startDate,
					endDate, shiftId, status, companyId);
		} else if ((employeeId.equals("All") || employeeId == null)) {
			// Department + Shift + Approval Status
			attendances = employeeAttendanceService.loadAttendancesByDepartmentAndShiftAndApprovalStatus(
					startDate, endDate, departmentId, shiftId, status, companyId);
		} else if ((shiftId.equals("All") || shiftId == null) && (approvalStatus == null)) {
			
			if (!employeeId.equals("All") && !(employeeId == null)) {
				// Employee
				System.out.println("Employee ID is not All " + employeeId);
				attendances = employeeAttendanceService.loadAttendancesByEmployeeAndApprovalStatus(
						startDate, endDate, departmentId, employeeId, status, companyId);
				/*
				 * List<String> attendances =
				 * employeeAttendanceService.loadAttendancesByEmployee(startDate, endDate,
				 * departmentId, employeeId, companyId);
				 */
			} else {
				// Department
				System.out.println("Employee ID is All");
				attendances = employeeAttendanceService
						.loadAttendancesByDepartmentAndApprovalStatus(startDate, endDate, departmentId, status, companyId);
				/*
				 * List<String> attendances =
				 * employeeAttendanceService.loadAttendancesByDepartment(startDate, endDate,
				 * departmentId, companyId);
				 */
			}
		} else if ((!employeeId.equals("All")) && (approvalStatus == null)) {
			// Employee + Shift
			attendances = employeeAttendanceService.loadAttendancesByEmployeeAndShiftAndApprovalStatus(
					startDate, endDate, departmentId, employeeId, shiftId, status, companyId);
			/*
			 * List<String> attendances =
			 * employeeAttendanceService.loadAttendancesByEmployeeAndShift(startDate,
			 * endDate, departmentId, employeeId, shiftId, companyId);
			 */
		} else if ((!employeeId.equals("All")) && (shiftId.equals("All") || shiftId == null)) {
			// Employee + Approval Status
			attendances = employeeAttendanceService.loadAttendancesByEmployeeAndApprovalStatus(startDate,
					endDate, departmentId, employeeId, status, companyId);
		} else if ((!employeeId.equals("All"))) {
			// Employee + Shift + Approval Status
			attendances = employeeAttendanceService.loadAttendancesByEmployeeAndShiftAndApprovalStatus(
					startDate, endDate, departmentId, employeeId, shiftId, status, companyId);
		}
		List<AttendanceRecordBean> list = new ArrayList<>();
		for (int i = 0; i < attendances.size(); i++) {
			AttendanceRecordBean arb = new AttendanceRecordBean();
			DepartmentMaster department = departmentService.getDepartmentByIdAndCompany(attendances.get(i).getDepartmentId(), companyId);
			arb.setAttendanceId(attendances.get(i).getAttendanceId());
			arb.setDate(attendances.get(i).getDate());
			arb.setShiftId(attendances.get(i).getShiftmaster().getShiftId());
			arb.setShift(attendances.get(i).getShiftmaster().getDescription());
			arb.setDepartmentId(department.getDepID());
			arb.setDepartment(department.getDepartment());
			arb.setEmployeeId(attendances.get(i).getEmployee().getEmpID());
			arb.setEmployee(attendances.get(i).getEmployee().getName() + " " + attendances.get(i).getEmployee().getLastname());
			arb.setShift(attendances.get(i).getShiftmaster().getDescription());
			arb.setOnTime(attendances.get(i).getOnTime());
			arb.setOffTime(attendances.get(i).getOffTime());
			arb.setStatus(attendances.get(i).isApproved());
			arb.setCompanyId(attendances.get(i).getCompany().getComID());
			list.add(arb);
		}
		model.put("filteredAttendanceList", attendances);
		return "hrm/employeeAttendanceApproval";
	}

	@PostMapping("/approveAttendance")
	public String approveAttendance(@ModelAttribute("approveForm") EmployeeAttendanceApproveForm approveForm) {
		System.out.println("Called");
		List<EmployeeAttendance> attendances = approveForm.getAttendances();

		if (null != attendances && attendances.size() > 0) {
			EmployeeAttendanceApprovalController.attendances = attendances;
			/*List<EmployeeAttendance> list = new ArrayList<>();
			for(int i = 0; i < attendances.size(); i++) {
				EmployeeAttendance ea = new EmployeeAttendance();
				ea.setAttendanceId(attendances.get(i).getAttendanceId());
				ea.setEmployee(attendances.get(i).getEmployee());
				ea.setShiftmaster(attendances.get(i).getShiftmaster());
				ea.setDate(attendances.get(i).getDate());
				ea.setOnTime(attendances.get(i).getOnTime());
				ea.setOffTime(attendances.get(i).getOffTime());
				ea.setDepartmentId(attendances.get(i).getDepartmentId());
				ea.setCompany(attendances.get(i).getCompany());
				list.add(ea);
			}*/
			employeeAttendanceService.saveEmployeeAttendance(attendances);
		}
		return "redirect:/EmployeeAttendanceApproval";
	}
}
