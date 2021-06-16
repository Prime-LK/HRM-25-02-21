package com.navitsa.hrm.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.navitsa.hrm.entity.CalanderEntity;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.ShiftAllocation;
import com.navitsa.hrm.entity.ShiftAllocationPK;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.report.ShiftAllocationBean;
import com.navitsa.hrm.service.CalanderService;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.ShiftAllocationService;
import com.navitsa.hrm.service.ShiftMasterService;

@Controller
public class ShiftAllocationController {

	@Autowired
	private ShiftAllocationService shiftAllocationService;

	@Autowired
	private ShiftMasterService shiftMasterService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CalanderService calanderService;

	@GetMapping("/ShiftAllocation")
	public String shiftAllocationPage() {
		return "hrm/shiftAllocation";
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

	@GetMapping("/loadDepartmentNameById")
	public @ResponseBody DepartmentMaster loadDepartmentName(@RequestParam("depID") String departmentId,
			HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		DepartmentMaster department = departmentService.getDepartmentByIdAndCompany(departmentId, companyId);
		return department;
	}

	@GetMapping("/loadEmployeeIdByDepartmentId")
	public @ResponseBody List<EmployeeDetails> loadEmployeesByDepartment(@RequestParam("depID") String departmentId,
			HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		List<EmployeeDetails> department = employeeService.filterEmployeesByDepartmentAndCompany(departmentId,
				companyId);
		return department;
	}

	@GetMapping("/loadShiftById")
	public @ResponseBody ShiftMaster loadShiftById(@RequestParam("shiftId") String shiftId, HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		ShiftMaster shift = shiftMasterService.findShiftById2(shiftId, companyId);
		return shift;
	}

	/*
	@GetMapping("/loadShiftsByDateRange")
	public @ResponseBody List<ShiftAllocationBean> loadShiftsByDateRange(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("shiftId") String shiftId, HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		List<ShiftAllocationBean> list = new ArrayList<>();
		String[][] result = shiftAllocationService.loadShiftsByDateRange(startDate, endDate, shiftId, companyId);
		for (int i = 0; i < result.length; i++) {
			ShiftAllocationBean shiftAllocation = new ShiftAllocationBean();
			shiftAllocation.setDate(result[i][0]);
			shiftAllocation.setDay_type(result[i][1]);
			shiftAllocation.setShift(result[i][3]);
			shiftAllocation.setStartTime(result[i][4]);
			shiftAllocation.setEndTime(result[i][5]);
			shiftAllocation.setEmployee(result[i][7]);
			shiftAllocation.setDepartment(result[i][9]);
			list.add(shiftAllocation);
		}
		System.out.println(list.size());
		return list;
	}
	*/
	
	@GetMapping("/loadShiftsByDateRange")
	public @ResponseBody List<ShiftAllocationBean> loadShiftsByDateRange(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("shiftId") String shiftId, HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		List<ShiftAllocationBean> list = new ArrayList<>();
		List<ShiftAllocation> result = shiftAllocationService.loadShiftsByDateRange(startDate, endDate, shiftId, companyId);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HH:mm");
		for (int i = 0; i < result.size(); i++) {
			ShiftAllocationBean shiftAllocation = new ShiftAllocationBean();
			//String employeeId = result.get(i).getShiftAllocationPK().getEmployee().getEmpID();
			//System.out.println("Employee ID : " + employeeId);
			EmployeeDetails details = employeeService.findEmployeeDetailsByEmployeeIdAndCompany(result.get(i).getShiftAllocationPK().getEmployee().getEmpID(), companyId);
			shiftAllocation.setDate(result.get(i).getShiftAllocationPK().getCalander().getCalanderEntityPK().getDate());
			shiftAllocation.setDay_type(result.get(i).getShiftAllocationPK().getCalander().getType());
			shiftAllocation.setShift(result.get(i).getShiftAllocationPK().getShiftmaster().getDescription());
			shiftAllocation.setStartTime(LocalTime.parse(result.get(i).getShiftAllocationPK().getShiftmaster().getStartTime()).format(formattertime));
			shiftAllocation.setEndTime(LocalTime.parse(result.get(i).getShiftAllocationPK().getShiftmaster().getEndTime()).format(formattertime));
			shiftAllocation.setEmployee(result.get(i).getShiftAllocationPK().getEmployee().getName() + " " + result.get(i).getShiftAllocationPK().getEmployee().getLastname());
			shiftAllocation.setDepartment(details.getDepartment().getDepartment());
			list.add(shiftAllocation);
		}
		System.out.println(list.size());
		return list;
	}

	@PostMapping("/assignShift")
	public String assignShift(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("shiftId") String shiftId, @RequestParam("departmentId") String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "allEmployees", required = false) int allEmployees,
			@RequestParam(value = "companyId", required = false) String companyId) {

		ShiftMaster shift = shiftMasterService.findShiftById2(shiftId, companyId);
		List<ShiftAllocation> list = new ArrayList<>();

		Date d1;
		Date d2;

		d1 = Date.valueOf(startDate);
		d2 = Date.valueOf(endDate);

		try {

			if (allEmployees == 1) {

				List<EmployeeDetails> department = employeeService.filterEmployeesByDepartmentAndCompany(departmentId,
						companyId);

				for (int i = 0; i < department.size(); i++) {
					Employee employee = employeeService.getEmployeeByCompany(
							department.get(i).getDetailsPK().getEmpID().getEmpID().toString(), companyId);
					for (LocalDate date = d1.toLocalDate(); date
							.isBefore(d2.toLocalDate().plusDays(1)); date = date.plusDays(1)) {
						ShiftAllocationPK shiftAllocationPK = new ShiftAllocationPK();
						ShiftAllocation allocation = new ShiftAllocation();
						CalanderEntity calander = calanderService.getCalenderByCompany(date.toString(), companyId);
						shiftAllocationPK.setCalander(calander);
						shiftAllocationPK.setShiftmaster(shift);
						shiftAllocationPK.setEmployee(employee);
						allocation.setShiftAllocationPK(shiftAllocationPK);
						list.add(allocation);
					}
				}
				shiftAllocationService.saveShiftAllocations(list);
			} else if (allEmployees == 0) {

				Employee employee = employeeService.getEmployeeByCompany(employeeId, companyId);

				for (LocalDate date = d1.toLocalDate(); date
						.isBefore(d2.toLocalDate().plusDays(1)); date = date.plusDays(1)) {
					ShiftAllocationPK shiftAllocationPK = new ShiftAllocationPK();
					ShiftAllocation allocation = new ShiftAllocation();
					CalanderEntity calander = calanderService.getCalenderByCompany(date.toString(), companyId);
					shiftAllocationPK.setCalander(calander);
					shiftAllocationPK.setShiftmaster(shift);
					shiftAllocationPK.setEmployee(employee);
					allocation.setShiftAllocationPK(shiftAllocationPK);
					list.add(allocation);
				}
				shiftAllocationService.saveShiftAllocations(list);
			}
			return "redirect:/ShiftAllocation";

		} catch (Exception e) {
			System.out.println("Error");
		}
		return "ShiftAllocation";
	}
}