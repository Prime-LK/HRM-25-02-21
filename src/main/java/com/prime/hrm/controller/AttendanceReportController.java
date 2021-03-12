package com.prime.hrm.controller;

import java.io.InputStream;
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

import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.ShiftMaster;
import com.prime.hrm.report.AttendanceMainReportBean;
import com.prime.hrm.report.AttendanceSubReportBean;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeAttendanceService;
import com.prime.hrm.service.ShiftMasterService;
import com.prime.hrm.utils.ReportViewe;

@Controller
public class AttendanceReportController {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ShiftMasterService shiftMasterService;

	@Autowired
	private EmployeeAttendanceService employeeAttendanceService;

	@PersistenceContext
	EntityManager entityManager;

	@GetMapping("/AttendanceReport")
	public String AttendanceReportPage() {
		return "attendanceReport";
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
	public ModelAndView generateReport(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("employeeId") String employeeId,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		String reportFileName1 = "attendance_report";
		String reportFileName2 = "attendance_subreport.jasper";
		String fileName = "Attendance Report: " + employeeId + "-" + startDate + "-" + endDate;
		List<AttendanceMainReportBean> list = new ArrayList<>();
		List<AttendanceSubReportBean> list2 = new ArrayList<>();
		String companyId = session.getAttribute("company.comID").toString();

		String[][] result = employeeAttendanceService.loadAttedanceMainReportDetails(startDate, endDate, employeeId,
				companyId);
		for (int i = 0; i < result.length; i++) {
			AttendanceMainReportBean attendance = new AttendanceMainReportBean();
			attendance.setYear(result[i][0]);
			attendance.setMonth(result[i][1]);
			attendance.setEmployee_id(result[i][2]);
			attendance.setEmployee_name(result[i][3]);
			attendance.setTotal_over_time(result[i][4]);
			attendance.setTotal_short_time(result[i][5]);
			attendance.setTotal_worked_days(result[i][6]);
			attendance.setTotal_absent_days(result[i][7]);
			attendance.setTotal_holidays(result[i][8]);
			attendance.setTotal_rest_days(result[i][9]);
			String[][] result2 = employeeAttendanceService.loadAttendanceSubReportDetails(startDate, endDate,
					employeeId, companyId);
			for (int j = 0; j < result2.length; j++) {
				AttendanceSubReportBean subattendance = new AttendanceSubReportBean();
				subattendance.setDate(result2[j][0]);
				subattendance.setWeekday(result2[j][1]);
				subattendance.setDay_type(result2[j][2]);
				subattendance.setOn_time(result2[j][3]);
				subattendance.setOff_time(result2[j][4]);
				subattendance.setWorked_time(result2[j][5]);
				subattendance.setOver_time(result2[j][6]);
				subattendance.setShort_time(result2[j][7]);
				subattendance.setAttendance_description(result2[j][8]);
				list2.add(subattendance);
			}
			attendance.setSubReportBeanList(list2);
			list.add(attendance);
		}

		// JasperReport jasperReport =
		// GenerateJasperReport.getInstance().getCompiledFile(reportFileName1, request);
		// JasperReport jasperReport2 =
		// GenerateJasperReport.getInstance().getCompiledFile(reportFileName2, request);

		InputStream jasperStream = getClass().getResourceAsStream("/" + reportFileName2);
		System.out.println("Sub Report : " + jasperStream);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("SUBREPORT_DIR", jasperStream);
		/*
		 * String report =
		 * GenerateJasperReport.getInstance().generatePDFReportFromBeanCollection(
		 * response, params, jasperReport, new JRBeanCollectionDataSource(list),
		 * fileName);
		 */
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("attendance_report.jasper", fileName, list, params,
				response);
		ModelAndView mav = new ModelAndView("attendanceReportPreview");
		mav.addObject("pdfViewEq", report);
		return mav;
	}
}
