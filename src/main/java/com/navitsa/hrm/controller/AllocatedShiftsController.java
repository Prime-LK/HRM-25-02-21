package com.navitsa.hrm.controller;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.ShiftAllocation;
import com.navitsa.hrm.report.ShiftAllocationBean;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.ShiftAllocationService;

@Controller
public class AllocatedShiftsController {

	@Autowired
	private ShiftAllocationService shiftAllocationService;
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/AllocatedShifts")
	public String allocatedShiftsPage() {
		return "hrm/allocatedShifts";
	}

	@ModelAttribute("shiftAllocationList")
	public List<ShiftAllocationBean> getAllShiftAllocations(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		List<ShiftAllocationBean> list = new ArrayList<>();
		List<ShiftAllocation> result = shiftAllocationService.loadShiftAllocationsByCompany(companyId);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HH:mm");
		for (int i = 0; i < result.size(); i++) {
			ShiftAllocationBean shiftAllocation = new ShiftAllocationBean();
			EmployeeDetails details = employeeService.findEmployeeDetailsByEmployeeIdAndCompany(result.get(i).getShiftAllocationPK().getEmployee().getEmpID(), companyId);
			shiftAllocation.setDate(result.get(i).getShiftAllocationPK().getDate());
			//shiftAllocation.setDay_type(result.get(i).getShiftAllocationPK().getCalander().getType());
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
}
