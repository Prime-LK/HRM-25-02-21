package com.navitsa.hrm.controller;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.report.AttendanceMainReportBean;
import com.navitsa.hrm.report.AttendanceMainSheetBean;
import com.navitsa.hrm.report.AttendanceSubReportBean;
import com.navitsa.hrm.report.AttendanceSubSheetBean;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeAttendanceService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.ShiftMasterService;
import com.navitsa.hrm.utils.GenerateJasperReport;
import com.navitsa.hrm.utils.ReportViewe;

import net.sf.jasperreports.engine.JasperReport;

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

	@GetMapping("/loadAttendanceSheet")
	@ResponseBody
	public List<AttendanceSubReportBean> loadAttendanceSheet(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("employeeId") String employeeId,
			Map<String, Object> model, HttpSession session) {
		List<AttendanceSubReportBean> list = new ArrayList<>();
		String companyId = session.getAttribute("company.comID").toString();
		String[][] result = employeeAttendanceService.loadAttendanceSubReportDetails(startDate, endDate, employeeId,
				companyId);
		for (int i = 0; i < result.length; i++) {
			AttendanceSubReportBean subattendance = new AttendanceSubReportBean();
			subattendance.setDate(result[i][0]);
			subattendance.setWeekday(result[i][1]);
			subattendance.setDay_type(result[i][2]);
			subattendance.setOn_time(result[i][3]);
			subattendance.setOff_time(result[i][4]);
			subattendance.setWorked_time(result[i][5]);
			subattendance.setOver_time(result[i][6]);
			subattendance.setShort_time(result[i][7]);
			subattendance.setAttendance_description(result[i][8]);
			list.add(subattendance);
		}
		return list;
	}

	@GetMapping("/generateReport")
	public ModelAndView generateReport(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, 
			@RequestParam("departmentId") String departmentId, @RequestParam("employeeId") String employeeId,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		String fileName = "Attendance Sheet: " + employeeId + "-" + startDate + "-" + endDate;
		List<AttendanceMainSheetBean> list = new ArrayList<>();
		List<AttendanceSubSheetBean> list2 = new ArrayList<>();
		String companyId = session.getAttribute("company.comID").toString();

		String department = employeeAttendanceService.getDepartmentByIdAndCompany(departmentId,
				companyId);
		System.out.println("Department : " + department);
		Employee employee = employeeService.getEmployeeByCompany(employeeId, companyId);
		String employeeName = employee.getName() + " " + employee.getLastname();
		System.out.println("Employee Name : " + employeeName);
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("start_date", formatter1.format(formatter.parse(startDate)));
		params.put("end_date", formatter1.format(formatter.parse(endDate)));
		params.put("department", department);
		params.put("employee_id", employeeId);
		params.put("employee_name", employeeName);
		
		
		
		String[][] result = employeeAttendanceService.loadAttendanceMainSheet(startDate, endDate, employeeId,
				companyId);
		for (int i = 0; i < result.length; i++) {
			AttendanceMainSheetBean attendance = new AttendanceMainSheetBean();
			attendance.setTotal_over_time(result[i][0]);
			attendance.setTotal_short_time(result[i][1]);
			attendance.setTotal_worked_days(result[i][2]);
			attendance.setTotal_absent_days(result[i][3]);
			attendance.setTotal_holidays(result[i][4]);
			attendance.setTotal_rest_days(result[i][5]);
			String[][] result2 = employeeAttendanceService.loadAttendanceSubSheet(startDate, endDate,
					employeeId, companyId);
			for (int j = 0; j < result2.length; j++) {
				AttendanceSubSheetBean subattendance = new AttendanceSubSheetBean();
				subattendance.setDate(result2[j][0]);
				subattendance.setWeekday(result2[j][1]);
				subattendance.setDay_type(result2[j][2]);
				subattendance.setShift(result2[j][3]);
				subattendance.setStart_time(result2[j][4]);
				subattendance.setEnd_time(result2[j][5]);
				subattendance.setOn_time(result2[j][6]);
				subattendance.setOff_time(result2[j][7]);
				subattendance.setWorked_time(result2[j][8]);
				subattendance.setOver_time(result2[j][9]);
				subattendance.setShort_time(result2[j][10]);
				subattendance.setAttendance_description(result2[j][11]);
				list2.add(subattendance);
			}
			attendance.setSubReportBeanList(list2);
			list.add(attendance);
		}
		

		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("attendance_sheet.jasper", fileName, list, params,
				response);
		ModelAndView mav = new ModelAndView("hrm/attendanceReportPreview");
		mav.addObject("pdfViewEq", report);
		return mav;
	}
}
