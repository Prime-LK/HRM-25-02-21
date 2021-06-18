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

import com.navitsa.hrm.entity.AttendanceRevise;
import com.navitsa.hrm.entity.EmployeeAttendance;
import com.navitsa.hrm.report.AttendanceReviseApproveForm;
import com.navitsa.hrm.report.EmployeeAttendanceApproveForm;
import com.navitsa.hrm.service.AttendanceReviseService;
import com.navitsa.hrm.service.EmployeeAttendanceService;

@Controller
public class AttendanceReviseApprovalController {

	@Autowired
	private AttendanceReviseService attendanceReviseService;

	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;

	@Autowired
	private static List<AttendanceRevise> revises = new ArrayList<AttendanceRevise>();

	@GetMapping("/AttendanceReviseApproval")
	public String AttendanceReviseApprovalPage() {
		return "hrm/attendanceReviseApproval";
	}

	@GetMapping("/loadAttendanceRevises")
	public String loadAttendanceRevises(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam(value = "approvalStatus", required = false) String approvalStatus, Map<String, Object> model,
			HttpSession session) {

		String companyId = session.getAttribute("company.comID").toString();

		if ((approvalStatus == null)) {
			// Dates
			List<String> revises = attendanceReviseService.loadAttendancesByDate(startDate, endDate, companyId);
			model.put("filteredRevisesList", revises);
		} else {
			// Approval Status
			List<String> revises = attendanceReviseService.loadAttendanceRevisesByApprovalStatus(startDate, endDate,
					Integer.valueOf(approvalStatus), companyId);
			model.put("filteredRevisesList", revises);
		}
		return "hrm/attendanceReviseApproval";
	}
	
	/*
	@PostMapping("/approveAttendanceRevises")
	public String approveAttendanceRevises(
			@ModelAttribute("approveAttendanceRevisesForm") AttendanceReviseApproveForm approveForm) {

		List<AttendanceRevise> attendanceRevises = approveForm.getAttendanceRevises();
		List<EmployeeAttendance> approvedRevises = new ArrayList<EmployeeAttendance>();
		List<EmployeeAttendance> unApprovedRevises = new ArrayList<EmployeeAttendance>();

		for (int i = 0; i < attendanceRevises.size(); i++) {
			EmployeeAttendance attendance = new EmployeeAttendance();
			if (attendanceRevises.get(i).isApproved() == true) {
				attendance.setAttendanceId(attendanceRevises.get(i).getAttendanceId());
				attendance.setEmployeeId(attendanceRevises.get(i).getEmployeeId());
				attendance.setShiftId(attendanceRevises.get(i).getShiftId());
				attendance.setDate(attendanceRevises.get(i).getDate());
				attendance.setOnTime(attendanceRevises.get(i).getNewOnTime());
				attendance.setOffTime(attendanceRevises.get(i).getNewOffTime());
				attendance.setApproved(true);
				attendance.setCompanyId(attendanceRevises.get(i).getCompanyId());
				approvedRevises.add(attendance);
			} else if (attendanceRevises.get(i).isApproved() == false) {
				attendance.setAttendanceId(attendanceRevises.get(i).getAttendanceId());
				attendance.setEmployeeId(attendanceRevises.get(i).getEmployeeId());
				attendance.setShiftId(attendanceRevises.get(i).getShiftId());
				attendance.setDate(attendanceRevises.get(i).getDate());
				attendance.setOnTime(attendanceRevises.get(i).getOnTime());
				attendance.setOffTime(attendanceRevises.get(i).getOffTime());
				attendance.setApproved(true);
				attendance.setCompanyId(attendanceRevises.get(i).getCompanyId());
				unApprovedRevises.add(attendance);
			}
		}

		if (null != attendanceRevises && attendanceRevises.size() > 0) {
			AttendanceReviseApprovalController.revises = attendanceRevises;
			attendanceReviseService.saveAttendanceRevise(attendanceRevises);
			employeeAttendanceService.saveEmployeeAttendance(approvedRevises);
			employeeAttendanceService.saveEmployeeAttendance(unApprovedRevises);

		}
		return "redirect:/AttendanceReviseApproval";
	}
	*/
}
