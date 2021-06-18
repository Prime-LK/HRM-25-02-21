package com.navitsa.hrm.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.ShiftAllocation;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.report.ShiftAllocationBean;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.ShiftAllocationService;
import com.navitsa.hrm.service.ShiftMasterService;

@Controller
public class AllocatedShiftsController {

	@Autowired
	private ShiftAllocationService shiftAllocationService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private ShiftMasterService shiftMasterService;

	@GetMapping("/AllocatedShifts")
	public String allocatedShiftsPage() {
		return "hrm/allocatedShifts";
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
	
	@GetMapping("/loadAllocatedShifts")
	public String loadAllocatedShifts(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "shiftId", required = false) String shiftId, Map<String, Object> model,
			HttpSession session) {

		String companyId = session.getAttribute("company.comID").toString();
		List<ShiftAllocation> allocations;
		if ((departmentId.equals("All") || departmentId == null) && (employeeId.equals("All") || employeeId == null)
				&& (shiftId.equals("All") || shiftId == null)) {
			// Dates
			allocations = shiftAllocationService.loadAllocatedShiftsByDate(startDate, endDate, companyId);
		} else if ((employeeId.equals("All") || employeeId == null) && (shiftId.equals("All") || shiftId == null)) {
			// Department
			allocations = shiftAllocationService.loadAllocatedShiftsByDepartment(startDate, endDate,
					departmentId, companyId);
		} else if ((departmentId.equals("All") || departmentId == null)
				&& (employeeId.equals("All") || employeeId == null)) {
			// Shift
			allocations = shiftAllocationService.loadAllocatedShiftsByShift(startDate, endDate, shiftId,
					companyId);
		} else if ((employeeId.equals("All") || employeeId == null)) {
			// Department + Shift
			allocations = shiftAllocationService.loadAllocatedShiftsByDepartmentAndShift(startDate, endDate,
					departmentId, shiftId, companyId);
		} else if ((shiftId.equals("All") || shiftId == null)) {

			if (!employeeId.equals("All") && !(employeeId == null)) {
				// Employee
				allocations = shiftAllocationService.loadAllocatedShiftsByEmployee(startDate, endDate,
						departmentId, employeeId, companyId);
			} else {
				// Department
				System.out.println("Employee ID is All");
				allocations = shiftAllocationService.loadAllocatedShiftsByDepartment(startDate, endDate,
						departmentId, companyId);
			}
		} else  {
			// Employee + Shift
			allocations = shiftAllocationService.loadAllocatedShiftsByEmployeeAndShift(startDate, endDate,
					departmentId, employeeId, shiftId, companyId);
		}
		List<ShiftAllocationBean> list = new ArrayList<>();
		DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HH:mm");
		for (int i = 0; i < allocations.size(); i++) {
			ShiftAllocationBean shiftAllocation = new ShiftAllocationBean();
			EmployeeDetails details = employeeService.findEmployeeDetailsByEmployeeIdAndCompany(allocations.get(i).getShiftAllocationPK().getEmployee().getEmpID(), companyId);
			shiftAllocation.setDate(allocations.get(i).getShiftAllocationPK().getDate());
			shiftAllocation.setShift(allocations.get(i).getShiftAllocationPK().getShiftmaster().getDescription());
			shiftAllocation.setStartTime(LocalTime.parse(allocations.get(i).getShiftAllocationPK().getShiftmaster().getStartTime()).format(formattertime));
			shiftAllocation.setEndTime(LocalTime.parse(allocations.get(i).getShiftAllocationPK().getShiftmaster().getEndTime()).format(formattertime));
			shiftAllocation.setEmployee(allocations.get(i).getShiftAllocationPK().getEmployee().getName() + " " + allocations.get(i).getShiftAllocationPK().getEmployee().getLastname());
			shiftAllocation.setDepartment(details.getDepartment().getDepartment());
			list.add(shiftAllocation);
		}
		model.put("shiftAllocationList", list);
		return "hrm/allocatedShifts";
	}
}
