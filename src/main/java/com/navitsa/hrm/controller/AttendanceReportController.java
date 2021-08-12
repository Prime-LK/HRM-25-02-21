package com.navitsa.hrm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeAttendance;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.report.AttendanceRecordBean;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeAttendanceService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.ShiftMasterService;
import com.navitsa.hrm.utils.ReportViewe;

@Controller
public class AttendanceReportController {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ShiftMasterService shiftMasterService;

	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/AttendanceReport")
	public String AttendanceReportPage() {
		return "hrm/attendanceReport";
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

	@GetMapping("/generateAttendanceReport")
	public ModelAndView generateAttendanceReport(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "shiftId", required = false) String shiftId, Map<String, Object> model,
			HttpServletResponse response, HttpSession session) {

		String companyId = session.getAttribute("company.comID").toString();
		String shiftName, departmentName, employeeName;
		List<EmployeeAttendance> attendances;
		if (shiftId.equals("all")) {
			shiftName = "ALL";
		} else {
			shiftName = shiftMasterService.findShiftByIdAndCompany(shiftId, companyId).getDescription();
		}

		if (departmentId.equals("all")) {
			departmentName = "ALL";
		} else {
			departmentName = departmentService.getDepartmentByIdAndCompany(departmentId, companyId).getDepartment();
		}

		if (employeeId.equals("all")) {
			employeeName = "ALL";
		} else {
			Employee emp = employeeService.getEmployeeByCompany(employeeId, companyId);
			employeeName = emp.getName() + " " + emp.getLastname();
		}

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
			arb.setEmployeeName(
					attendances.get(i).getEmployee().getName() + " " + attendances.get(i).getEmployee().getLastname());
			arb.setDepartment(department.getDepartment());
			if(attendances.get(i).getShiftmaster() != null) {
				arb.setShift(attendances.get(i).getShiftmaster().getDescription());
				arb.setShiftId(attendances.get(i).getShiftmaster().getShiftId());
			} else {
				arb.setShift("");
				arb.setShiftId("");
			}
			arb.setOnTime(attendances.get(i).getOnTime());
			arb.setOffTime(attendances.get(i).getOffTime());
			arb.setEmployeeId(attendances.get(i).getEmployee().getEmpID());
			arb.setAttendanceId(attendances.get(i).getAttendanceId());
			arb.setEpfNo(attendances.get(i).getEmployee().getEpfNo());
			boolean a = attendances.get(i).isApproved();
			if (a == true) {
				arb.setApproved("Yes");
			} else {
				arb.setApproved("No");
			}
			list.add(arb);
		}

		ModelAndView mav = new ModelAndView("hrm/attendanceReport");

		String pdf_result = null;
		ReportViewe view = new ReportViewe();
		Map<String, Object> params = new HashMap<>();
		params.put("fromDate", startDate);
		params.put("toDate", endDate);
		params.put("shift", shiftName);
		params.put("department", departmentName);
		params.put("employeeName", employeeName);
		try {
			pdf_result = view.pdfReportViewInlineSystemOpen("manual_attendance_report.jasper",
					"Manual Attendance Report", list, params, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("pdfViewEq", pdf_result);
		return mav;
	}
}
