package com.navitsa.hrm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.EmployeeAttendance;
import com.navitsa.hrm.entity.ShiftAllocation;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.report.AttendanceRecordBean;
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

	@GetMapping("/loadAttendanceLog")
	public String loadAttendance(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "shiftId", required = false) String shiftId, Map<String, Object> model,
			HttpSession session) {

		String companyId = session.getAttribute("company.comID").toString();
		List<EmployeeAttendance> attendances;
		if ((departmentId.equals("all") || departmentId == null) && (employeeId.equals("all") || employeeId == null)
				&& (shiftId.equals("all") || shiftId == null)) {
			// Dates
			attendances = employeeAttendanceService.loadAttendancesByDate(startDate, endDate, companyId);
		} else if ((employeeId.equals("all") || employeeId == null) && (shiftId.equals("all") || shiftId == null)) {
			// Department
			attendances = employeeAttendanceService.loadAttendancesByDepartment(startDate, endDate, departmentId,
					companyId);
		} else if ((departmentId.equals("all") || departmentId == null)
				&& (employeeId.equals("all") || employeeId == null)) {
			// Shift
			attendances = employeeAttendanceService.loadAttendancesByShift(startDate, endDate, shiftId, companyId);
		} else if ((employeeId.equals("all") || employeeId == null)) {
			// Department + Shift
			attendances = employeeAttendanceService.loadAttendancesByDepartmentAndShift(startDate, endDate,
					departmentId, shiftId, companyId);
		} else if ((shiftId.equals("all") || shiftId == null)) {

			if (!employeeId.equals("all") && !(employeeId == null)) {
				// Employee
				attendances = employeeAttendanceService.loadAttendancesByEmployee(startDate, endDate, departmentId,
						employeeId, companyId);
			} else {
				// Department
				System.out.println("Employee ID is all");
				attendances = employeeAttendanceService.loadAttendancesByDepartment(startDate, endDate, departmentId,
						companyId);
			}
		} else {
			// Employee + Shift
			attendances = employeeAttendanceService.loadAttendancesByEmployeeAndShift(startDate, endDate, departmentId,
					employeeId, shiftId, companyId);
		}
		List<AttendanceRecordBean> list = new ArrayList<>();
		for (int i = 0; i < attendances.size(); i++) {
			AttendanceRecordBean arb = new AttendanceRecordBean();
			DepartmentMaster department = departmentService
					.getDepartmentByIdAndCompany(attendances.get(i).getDepartmentId(), companyId);
			arb.setDate(attendances.get(i).getDate());
			arb.setEmployee(
					attendances.get(i).getEmployee().getName() + " " + attendances.get(i).getEmployee().getLastname());
			arb.setDepartment(department.getDepartment());
			arb.setShift(attendances.get(i).getShiftmaster().getDescription());
			arb.setOnTime(attendances.get(i).getOnTime());
			arb.setOffTime(attendances.get(i).getOffTime());
			arb.setEmployeeId(attendances.get(i).getEmployee().getEmpID());
			arb.setShiftId(attendances.get(i).getShiftmaster().getShiftId());
			arb.setAttendanceId(attendances.get(i).getAttendanceId());
			boolean a = attendances.get(i).isApproved();
			if (a == true) {
				arb.setApprovalStatus("Yes");
			} else {
				arb.setApprovalStatus("No");
			}
			list.add(arb);
		}
		model.put("attendanceList", list);
		return "hrm/attendanceLog";
	}

	@GetMapping("/getEmployeeAttendance")
	public ModelAndView updateEmployeeAttendance(@RequestParam String attendanceId, HttpSession session) {
		ModelAndView mav = new ModelAndView("hrm/updateEmployeeAttendance");// jsp
		String companyId = session.getAttribute("company.comID").toString();
		EmployeeAttendance employeeAttendance = employeeAttendanceService.findAttendanceByIdAndCompany(attendanceId, companyId);
		mav.addObject("updateEmployeeAttendance", employeeAttendance);
		return mav;
	}

	@GetMapping("/deleteEmployeeAttendance")
	public String deleteEmployeeAttendance(@RequestParam("date") String date, @RequestParam("shiftId") String shiftId,
			@RequestParam("employeeId") String employeeId, RedirectAttributes redirectAttributes) {
		try {
			employeeAttendanceService.deleteEmployeeAttendance(date, employeeId, shiftId);
			redirectAttributes.addFlashAttribute("success", 1);
			return "redirect:/AllocatedShifts";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("success", 0);
			System.out.println(e);
		}
		return "hrm/allocatedShifts";
	}

	@PostMapping("/updateEmployeeAttendance")
	public String saveEmployeeAttendance(
			@Valid @ModelAttribute("updateEmployeeAttendance") EmployeeAttendance employeeAttendance, BindingResult br,
			RedirectAttributes redirectAttributes) {

		if (br.hasErrors()) {
			return "hrm/updateEmployeeAttendance";
		} else {
			try {
				EmployeeAttendance attendance = employeeAttendance;
				employeeAttendanceService.deleteEmployeeAttendance(employeeAttendance.getDate(),
						employeeAttendance.getEmployee().getEmpID(), employeeAttendance.getShiftmaster().getShiftId());
				employeeAttendanceService.saveEmployeeAttendance(attendance);
				redirectAttributes.addFlashAttribute("success", 1);
				return "redirect:/AllocatedShifts";
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("success", 0);
				System.out.println(e);
			}
		}
		return "hrm/updateEmployeeAttendance";
	}
}
