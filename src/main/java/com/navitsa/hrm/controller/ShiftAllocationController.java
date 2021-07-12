package com.navitsa.hrm.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.CalanderEntity;
import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.ShiftAllocation;
import com.navitsa.hrm.entity.ShiftAllocationPK;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.report.ShiftAllocationBean;
import com.navitsa.hrm.service.CalanderService;
import com.navitsa.hrm.service.CompanyService;
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
	private CompanyService companyService;

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
		ShiftMaster shift = shiftMasterService.findShiftByIdAndCompany(shiftId, companyId);
		return shift;
	}

	@GetMapping("/loadShiftsByDateRange")
	public @ResponseBody List<ShiftAllocationBean> loadShiftsByDateRange(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate, @RequestParam("shiftId") String shiftId, HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		List<ShiftAllocationBean> list = new ArrayList<>();
		List<ShiftAllocation> result = shiftAllocationService.loadShiftsByDateRange(startDate, endDate, shiftId,
				companyId);
		DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HH:mm");
		for (int i = 0; i < result.size(); i++) {
			ShiftAllocationBean shiftAllocation = new ShiftAllocationBean();
			EmployeeDetails details = employeeService.findEmployeeDetailsByEmployeeIdAndCompany(
					result.get(i).getShiftAllocationPK().getEmployee().getEmpID(), companyId);
			shiftAllocation.setDate(result.get(i).getShiftAllocationPK().getDate());
			// shiftAllocation.setDay_type(result.get(i).getShiftAllocationPK().getCalander().getType());
			shiftAllocation.setShift(result.get(i).getShiftAllocationPK().getShiftmaster().getDescription());
			shiftAllocation.setStartTime(LocalTime
					.parse(result.get(i).getShiftAllocationPK().getShiftmaster().getStartTime()).format(formattertime));
			shiftAllocation.setEndTime(LocalTime
					.parse(result.get(i).getShiftAllocationPK().getShiftmaster().getEndTime()).format(formattertime));
			shiftAllocation.setEmployee(result.get(i).getShiftAllocationPK().getEmployee().getName() + " "
					+ result.get(i).getShiftAllocationPK().getEmployee().getLastname());
			shiftAllocation.setDepartment(details.getDepartment().getDepartment());
			list.add(shiftAllocation);
		}
		System.out.println(list.size());
		return list;
	}

	@PostMapping("/allocateShift")
	public String assignShift(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam("shiftId") String shiftId, @RequestParam("departmentId") String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "includeHolidays", required = false) String includeHolidays,
			@RequestParam(value = "companyId", required = false) String companyId,
			RedirectAttributes redirectAttributes) {

		int includeHoliday = Integer.valueOf(includeHolidays);
		CompanyMaster company = companyService.findbyCompanyid(companyId);
		ShiftMaster shift = shiftMasterService.findShiftByIdAndCompany(shiftId, companyId);
		List<ShiftAllocation> list = new ArrayList<>();

		Date d1;
		Date d2;

		d1 = Date.valueOf(startDate);
		d2 = Date.valueOf(endDate);

		try {

			if (employeeId.equals("all")) {

				List<EmployeeDetails> department = employeeService.filterEmployeesByDepartmentAndCompany(departmentId,
						companyId);

				for (int i = 0; i < department.size(); i++) {
					Employee employee = employeeService.getEmployeeByCompany(
							department.get(i).getDetailsPK().getEmpID().getEmpID().toString(), companyId);
					for (LocalDate date = d1.toLocalDate(); date
							.isBefore(d2.toLocalDate().plusDays(1)); date = date.plusDays(1)) {
						Date shiftDate = Date.valueOf(date);
						CalanderEntity calander = calanderService.getCalenderByCompany(shiftDate.toString(), companyId);
						if (includeHoliday == 0 && calander.getStatus().equals("Holiday")) {
							continue;
						}
						ShiftAllocationPK shiftAllocationPK = new ShiftAllocationPK();
						ShiftAllocation allocation = new ShiftAllocation();
						shiftAllocationPK.setDate(shiftDate.toString());
						shiftAllocationPK.setShiftmaster(shift);
						shiftAllocationPK.setEmployee(employee);
						allocation.setShiftAllocationPK(shiftAllocationPK);
						allocation.setDepartmentId(departmentId);
						allocation.setCompany(company);
						list.add(allocation);
					}
				}
				shiftAllocationService.saveShiftAllocations(list);
			} else {

				Employee employee = employeeService.getEmployeeByCompany(employeeId, companyId);

				for (LocalDate date = d1.toLocalDate(); date
						.isBefore(d2.toLocalDate().plusDays(1)); date = date.plusDays(1)) {
					Date shiftDate = Date.valueOf(date);
					CalanderEntity calander = calanderService.getCalenderByCompany(shiftDate.toString(), companyId);
					if (includeHoliday == 0 && calander.getStatus().equals("Holiday")) {
						continue;
					}
					ShiftAllocationPK shiftAllocationPK = new ShiftAllocationPK();
					ShiftAllocation allocation = new ShiftAllocation();
					shiftAllocationPK.setDate(shiftDate.toString());
					shiftAllocationPK.setShiftmaster(shift);
					shiftAllocationPK.setEmployee(employee);
					allocation.setShiftAllocationPK(shiftAllocationPK);
					allocation.setDepartmentId(departmentId);
					allocation.setCompany(company);
					list.add(allocation);
				}
				shiftAllocationService.saveShiftAllocations(list);
			}
			redirectAttributes.addFlashAttribute("success", 1);
			return "redirect:/ShiftAllocation";

		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("success", 0);
			System.out.println(e);
		}
		return "hrm/shiftAllocation";
	}
}