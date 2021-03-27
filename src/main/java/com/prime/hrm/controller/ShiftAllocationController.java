package com.prime.hrm.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeDetails;
import com.prime.hrm.entity.ShiftAllocation;
import com.prime.hrm.entity.ShiftAllocationPK;
import com.prime.hrm.entity.ShiftMaster;
import com.prime.hrm.report.AttendanceSubReportBean;
import com.prime.hrm.report.ShiftAllocationBean;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeService;
import com.prime.hrm.service.ShiftAllocationService;
import com.prime.hrm.service.ShiftMasterService;

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

	@GetMapping("/ShiftAllocation")
	public String shiftAllocationPage() {
		return "shiftAllocation";
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
		return list;
	}

//	@Async
//	@PostMapping("/assignShift")
//	public String assignShift(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
//			@RequestParam("shiftId") String shiftId, @RequestParam("shiftName") String shiftName,
//			@RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime,
//			@RequestParam("departmentId") String departmentId, @RequestParam("departmentName") String departmentName,
//			@RequestParam(value = "employeeId", required = false) String employeeId,
//			@RequestParam(value = "allEmployees", required = false) int allEmployees,
//			@RequestParam(value = "companyId", required = false) String companyId) {
//
//		String empId;
//		String employeeName;
//		List<ShiftAllocation> list = new ArrayList<>();
//
//		Date d1;
//		Date d2;
//
//		d1 = Date.valueOf(startDate);
//		d2 = Date.valueOf(endDate);
//
//		try {
//
//			if (allEmployees == 1) {
//
//				//List<EmployeeDetails> department = employeeService.filterRelatedData(departmentId);
//				List<EmployeeDetails> department = employeeService.filterEmployeesByDepartmentAndCompany(departmentId,
//						companyId);
//
//				for (int i = 0; i < department.size(); i++) {
//					empId = department.get(i).getDetailsPK().getEmpID().getEmpID().toString();
//					employeeName = department.get(i).getDetailsPK().getEmpID().getName().toString() + " "
//							+ department.get(i).getDetailsPK().getEmpID().getLastname();
//					System.out.println(empId);
//					for (LocalDate date = d1.toLocalDate(); date
//							.isBefore(d2.toLocalDate().plusDays(1)); date = date.plusDays(1)) {
//						ShiftAllocationPK shiftAllocationPK = new ShiftAllocationPK();
//						ShiftAllocation allocation = new ShiftAllocation();
//						Date shiftDate = Date.valueOf(date);
//						shiftAllocationPK.setDate(shiftDate);
//						shiftAllocationPK.setShiftId(shiftId);
//						shiftAllocationPK.setEmployeeId(empId);
//						allocation.setShiftAllocationPK(shiftAllocationPK);
//						allocation.setShiftName(shiftName);
//						allocation.setStartTime(startTime);
//						allocation.setEndTime(endTime);
//						allocation.setEmployeeName(employeeName);
//						allocation.setDepartmentId(departmentId);
//						allocation.setDepartmentName(departmentName);
//						allocation.setCompanyId(companyId);
//						list.add(allocation);
//					}
//				}
//				shiftAllocationService.saveShiftAllocations(list);
//			} else if (allEmployees == 0) {
//
//				Employee employee = employeeService.getEmployeeByCompany(employeeId, companyId);
//				employeeName = employee.getName() + " " + employee.getLastname();
//
//				for (LocalDate date = d1.toLocalDate(); date
//						.isBefore(d2.toLocalDate().plusDays(1)); date = date.plusDays(1)) {
//					ShiftAllocationPK shiftAllocationPK = new ShiftAllocationPK();
//					ShiftAllocation allocation = new ShiftAllocation();
//					Date shiftDate = Date.valueOf(date);
//					shiftAllocationPK.setDate(shiftDate);
//					shiftAllocationPK.setShiftId(shiftId);
//					shiftAllocationPK.setEmployeeId(employeeId);
//					allocation.setShiftAllocationPK(shiftAllocationPK);
//					allocation.setShiftName(shiftName);
//					allocation.setStartTime(startTime);
//					allocation.setEndTime(endTime);
//					allocation.setEmployeeName(employeeName);
//					allocation.setDepartmentId(departmentId);
//					allocation.setDepartmentName(departmentName);
//					allocation.setCompanyId(companyId);
//					list.add(allocation);
//				}
//				shiftAllocationService.saveShiftAllocations(list);
//			}
//			return "redirect:/ShiftAllocation";
//
//		} catch (Exception e) {
//			System.out.println("Error");
//		}
//		return "ShiftAllocation";
//	}
}
