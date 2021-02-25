package com.prime.hrm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prime.hrm.entity.AttendanceRevise;
import com.prime.hrm.entity.EmployeeAttendance;
import com.prime.hrm.report.AttendanceReviseApproveForm;
import com.prime.hrm.report.EmployeeAttendanceApproveForm;
import com.prime.hrm.service.AttendanceReviseService;
import com.prime.hrm.service.EmployeeAttendanceService;

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
		return "attendanceReviseApproval";
	}
	
	@GetMapping("/loadAttendanceRevises")
	public String loadAttendanceRevises(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam(value = "approvalStatus", required = false) String approvalStatus, Map<String, Object> model) {
		
		if ((approvalStatus == null)) {
			// Dates
			List<String> revises = attendanceReviseService.loadAttendancesByDate(startDate, endDate);
			model.put("filteredRevisesList", revises);
		} else  {
			// Approval Status
			List<String> revises = attendanceReviseService.loadAttendanceRevisesByApprovalStatus(startDate, endDate, Integer.valueOf(approvalStatus));
			model.put("filteredRevisesList", revises);
		}
		return "attendanceReviseApproval";
	}
	
	@PostMapping("/approveAttendanceRevises")
	public String approveAttendanceRevises(@ModelAttribute("approveAttendanceRevisesForm") AttendanceReviseApproveForm approveForm) {

		List<AttendanceRevise> attendanceRevises = approveForm.getAttendanceRevises();
		List<EmployeeAttendance> approvedRevises = new ArrayList<EmployeeAttendance>();
		List<EmployeeAttendance> unApprovedRevises = new ArrayList<EmployeeAttendance>();
		
		for (int i = 0; i < attendanceRevises.size(); i++) {
			EmployeeAttendance attendance = new EmployeeAttendance();
				if(attendanceRevises.get(i).isApproved() == true) {
					attendance.setAttendanceId(attendanceRevises.get(i).getAttendanceId());
					attendance.setEmployeeId(attendanceRevises.get(i).getEmployeeId());
					attendance.setShiftId(attendanceRevises.get(i).getShiftId());
					attendance.setDate(attendanceRevises.get(i).getDate());
					attendance.setOnTime(attendanceRevises.get(i).getNewOnTime());
					attendance.setOffTime(attendanceRevises.get(i).getNewOffTime());
					attendance.setApproved(true);
					attendance.setCompanyId(attendanceRevises.get(i).getCompanyId());
					approvedRevises.add(attendance);
				} else if(attendanceRevises.get(i).isApproved() == false) {
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
}
