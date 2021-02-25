package com.prime.hrm.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.ShiftMaster;
import com.prime.hrm.report.AttendanceSubReportBean;
import com.prime.hrm.report.ShiftDetailReportBean;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.ShiftAllocationService;
import com.prime.hrm.service.ShiftMasterService;
import com.prime.hrm.utils.GenerateJasperReport;
import com.prime.hrm.utils.ReportViewe;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ShiftDetailReportController {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ShiftMasterService shiftMasterService;
	
	@Autowired
	private ShiftAllocationService shiftAllocationService;
	
	@GetMapping("/ShiftDetailReport")
	public String AttendanceReportPage() {
		return "shiftDetailReport";
	}
	
	@ModelAttribute("depList")
	public List<DepartmentMaster> getAllDeps() throws SQLException, IOException {
		return departmentService.getAllDep();
	}

	@ModelAttribute("shiftList")
	public List<ShiftMaster> getAllShifts() {
		return shiftMasterService.findAllShifts();
	}
	
	@GetMapping("/loadShiftDetails")
	@ResponseBody
	public List<ShiftDetailReportBean> loadShiftDetails(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "shiftId", required = false) String shiftId) {
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(departmentId);
		System.out.println(employeeId);
		System.out.println(shiftId);
		List<ShiftDetailReportBean> list = new ArrayList<>();
		if((departmentId.equals("All") || departmentId == null) && (employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)) {
			//Dates
			String[][] result = shiftAllocationService.loadShiftDetailReportByDate(startDate, endDate);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
			}
			return list;
		} else if ((employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)) {
			// Department
			String[][] result = shiftAllocationService.loadShiftDetailReportByDepartment(startDate, endDate, departmentId);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
			}
			return list;
		} else if ((departmentId.equals("All") || departmentId == null) && (employeeId.equals("All") || employeeId == null)) {
			// Shift
			String[][] result = shiftAllocationService.loadShiftDetailReportByShift(startDate, endDate, shiftId);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
			}
			return list;
		} else if (employeeId.equals("All") || employeeId == null) {
			// Department + Shift
			String[][] result = shiftAllocationService.loadShiftDetailReportByDepartmentAndShift(startDate, endDate, departmentId, shiftId);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
			}
			return list;
		} else if (!employeeId.equals("All") && (shiftId.equals("All") || shiftId == null)) {
			// Employee
			String[][] result = shiftAllocationService.loadShiftDetailReportByEmployee(startDate, endDate, departmentId, employeeId);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
			}
			return list;
		} else {
			// Employee + Shift
			String[][] result = shiftAllocationService.loadShiftDetailReportByEmployeeAndShift(startDate, endDate, departmentId, employeeId, shiftId);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
			}
			return list;
		}
	}
	
	@GetMapping("/generateShiftDetailReport")
	public ModelAndView generateShiftDetailReport(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "shiftId", required = false) String shiftId,
			HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, NamingException, SQLException, Exception {
		String fileName = "Shift Details Report: " + startDate + "-" + endDate;
		String reportFileName = "shift_detail_report";
		List<ShiftDetailReportBean> list = new ArrayList<>();
		System.out.println(startDate);
		System.out.println(endDate);
		System.out.println(departmentId);
		System.out.println(employeeId);
		System.out.println(shiftId);
		if((departmentId.equals("All") || departmentId == null) && (employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)) {
			//Dates
			departmentId = "ALL";
			employeeId = "ALL";
			shiftId = "ALL";
			String[][] result = shiftAllocationService.loadShiftDetailReportByDate(startDate, endDate);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
			}
		} else if ((employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)) {
			// Department

			employeeId = "ALL";
			shiftId = "ALL";
			String[][] result = shiftAllocationService.loadShiftDetailReportByDepartment(startDate, endDate, departmentId);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
				departmentId = result[i][4];
			}
		} else if ((departmentId.equals("All") || departmentId == null) && (employeeId.equals("All") || employeeId == null)) {
			// Shift
			departmentId = "ALL";
			employeeId = "ALL";
			
			String[][] result = shiftAllocationService.loadShiftDetailReportByShift(startDate, endDate, shiftId);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
				shiftId = result[i][3];
			}
		} else if (employeeId.equals("All") || employeeId == null) {
			// Department + Shift
			
			employeeId = "ALL";
			
			String[][] result = shiftAllocationService.loadShiftDetailReportByDepartmentAndShift(startDate, endDate, departmentId, shiftId);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
				departmentId = result[i][4];
				shiftId = result[i][3];
			}
		} else if ((shiftId.equals("All") || shiftId == null) && !employeeId.equals("All")) {
			// Employee
			shiftId = "ALL";
			String[][] result = shiftAllocationService.loadShiftDetailReportByEmployee(startDate, endDate, departmentId, employeeId);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
				employeeId = result[i][5];
				departmentId = result[i][4];
			}
		} else if (!employeeId.equals("All")) {
			// Employee + Shift
			String[][] result = shiftAllocationService.loadShiftDetailReportByEmployeeAndShift(startDate, endDate, departmentId, employeeId, shiftId);
			for (int i = 0; i < result.length; i++) {
				ShiftDetailReportBean shiftDetail = new ShiftDetailReportBean();
				shiftDetail.setDate(result[i][0]);
				shiftDetail.setWeekday(result[i][1]);
				shiftDetail.setDay_type(result[i][2]);
				shiftDetail.setShift(result[i][3]);
				shiftDetail.setDepartment(result[i][4]);
				shiftDetail.setEmployee_name(result[i][5]);
				shiftDetail.setStart_time(result[i][6]);
				shiftDetail.setEnd_time(result[i][7]);
				shiftDetail.setOn_time(result[i][8]);
				shiftDetail.setOff_time(result[i][9]);
				list.add(shiftDetail);
				employeeId = result[i][5];
				shiftId = result[i][3];
				departmentId = result[i][4];
			}
		} 
		 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");
        Map<String, Object> params = new HashMap<>();
 
        params.put("start_date", formatter1.format(formatter.parse(startDate)));
        params.put("end_date", formatter1.format(formatter.parse(endDate)));
        params.put("department", departmentId);
        params.put("employee", employeeId);
        params.put("shift", shiftId);
        //JasperReport jasperReport = GenerateJasperReport.getInstance().getCompiledFile(reportFileName, request);
        //String report = GenerateJasperReport.getInstance().generatePDFReportFromBeanCollection(response, params, jasperReport, new JRBeanCollectionDataSource(list), fileName);
        
       /*JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(list));
		GenerateJasperReport.getInstance().generateReportHtml(jasperPrint, request, response); // For HTML report*/
        
        //String report = GenerateJasperReport.getInstance().pdfReportViewInlineSystemOpen("shift_detail_report.jasper", fileName, list, params);
        ReportViewe review=new ReportViewe();
        String report = review.pdfReportViewInlineSystemOpen("shift_detail_report.jasper", fileName, list, params, response);
        
        ModelAndView mav = new ModelAndView("shiftDetailReportPreview");
        mav.addObject("pdfViewEq",report);
        return mav;
	}
}
