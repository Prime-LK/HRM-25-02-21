package com.prime.hrm.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.LeaveAndAttendanceChartServive;

@Controller
public class LeavesAndAttendanceChartController {

	@Autowired
	private LeaveAndAttendanceChartServive dashService;
	
	@Autowired
	private DepartmentService depService;
	
	@GetMapping("/getDepNameToChart")
	@ResponseBody
	public String[][] getDepartmentNameToChart() {
		// declare the date object for save
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime ldt = LocalDateTime.now();
		String[][] depData = dashService.getDepartmentNameToChart(dtf.format(ldt));
		return depData;
	}
	
	@GetMapping("/getChartDateRelatedDep")
	@ResponseBody
	public String[][] getChartDateRelatedDep() {
		// declare the date object for save
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime ldt = LocalDateTime.now();
		String[][] depData = dashService.getChartDateRelatedDep(dtf.format(ldt));
		return depData;
	}
	
	// leave and attendance chart more
	@GetMapping("/leaveAndAttMore")
	public String getPage() {
		return "leavesAndAttendanceChartMore";
	}
	
	@ModelAttribute("getAllDepName")
	public List<DepartmentMaster> getAllDeps() {
		return depService.getAllDep();
	}
	
	@GetMapping("/getChartMoreData")
	@ResponseBody
	public String[][] getChartMoreData(@RequestParam("depID")String depID) {
		// declare the date object for save
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime ldt = LocalDateTime.now();
		String[][] depData = dashService.getChartMoreData(dtf.format(ldt),depID);
		return depData;
	}

}
