package com.navitsa.hrm.controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.CompanyMaster;
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
	public String loadAllocatedShifts(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate,
			@RequestParam(value = "departmentId", required = false) String departmentId,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "shiftId", required = false) String shiftId, Map<String, Object> model,
			HttpSession session) {

		String companyId = session.getAttribute("company.comID").toString();
		List<ShiftAllocation> allocations;
		if ((departmentId.equals("all") || departmentId == null) && (employeeId.equals("all") || employeeId == null)
				&& (shiftId.equals("all") || shiftId == null)) {
			// Dates
			allocations = shiftAllocationService.loadAllocatedShiftsByDate(startDate, endDate, companyId);
		} else if ((employeeId.equals("all") || employeeId == null) && (shiftId.equals("all") || shiftId == null)) {
			// Department
			allocations = shiftAllocationService.loadAllocatedShiftsByDepartment(startDate, endDate, departmentId,
					companyId);
		} else if ((departmentId.equals("all") || departmentId == null)
				&& (employeeId.equals("all") || employeeId == null)) {
			// Shift
			allocations = shiftAllocationService.loadAllocatedShiftsByShift(startDate, endDate, shiftId, companyId);
		} else if ((employeeId.equals("all") || employeeId == null)) {
			// Department + Shift
			allocations = shiftAllocationService.loadAllocatedShiftsByDepartmentAndShift(startDate, endDate,
					departmentId, shiftId, companyId);
		} else if ((shiftId.equals("all") || shiftId == null)) {

			if (!employeeId.equals("all") && !(employeeId == null)) {
				// Employee
				allocations = shiftAllocationService.loadAllocatedShiftsByEmployee(startDate, endDate, departmentId,
						employeeId, companyId);
			} else {
				// Department
				System.out.println("Employee ID is all");
				allocations = shiftAllocationService.loadAllocatedShiftsByDepartment(startDate, endDate, departmentId,
						companyId);
			}
		} else {
			// Employee + Shift
			allocations = shiftAllocationService.loadAllocatedShiftsByEmployeeAndShift(startDate, endDate, departmentId,
					employeeId, shiftId, companyId);
		}
		List<ShiftAllocationBean> list = new ArrayList<>();
		DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HH:mm");
		for (int i = 0; i < allocations.size(); i++) {
			ShiftAllocationBean shiftAllocation = new ShiftAllocationBean();
			EmployeeDetails details = employeeService.findEmployeeDetailsByEmployeeIdAndCompany(
					allocations.get(i).getShiftAllocationPK().getEmployee().getEmpID(), companyId);
			shiftAllocation.setDate(allocations.get(i).getShiftAllocationPK().getDate());
			shiftAllocation.setShift(allocations.get(i).getShiftAllocationPK().getShiftmaster().getDescription());
			shiftAllocation.setStartTime(
					LocalTime.parse(allocations.get(i).getShiftAllocationPK().getShiftmaster().getStartTime())
							.format(formattertime));
			shiftAllocation
					.setEndTime(LocalTime.parse(allocations.get(i).getShiftAllocationPK().getShiftmaster().getEndTime())
							.format(formattertime));
			shiftAllocation.setEmployee(allocations.get(i).getShiftAllocationPK().getEmployee().getName() + " "
					+ allocations.get(i).getShiftAllocationPK().getEmployee().getLastname());
			shiftAllocation.setDepartment(details.getDepartment().getDepartment());
			shiftAllocation.setShiftId(allocations.get(i).getShiftAllocationPK().getShiftmaster().getShiftId());
			shiftAllocation.setEmployeeId(allocations.get(i).getShiftAllocationPK().getEmployee().getEmpID());
			list.add(shiftAllocation);
		}
		model.put("shiftAllocationList", list);
		return "hrm/allocatedShifts";
	}

	@GetMapping("/getShiftAllocation")
	public ModelAndView updateShift(@RequestParam String employeeId, @RequestParam String date,
			@RequestParam String shiftId, HttpSession session) {
		ModelAndView mav = new ModelAndView("hrm/updateShiftAllocation");// jsp
		String companyId = session.getAttribute("company.comID").toString();
		ShiftAllocation shiftAllocation = shiftAllocationService.findShiftAllocationById(date, shiftId, employeeId,
				companyId);
		mav.addObject("updateShiftAllocation", shiftAllocation);
		return mav;
	}

	@GetMapping("/deleteShiftAllocation")
	public String deleteShiftAllocation(@RequestParam("date") String date, @RequestParam("shiftId") String shiftId,
			@RequestParam("employeeId") String employeeId, RedirectAttributes redirectAttributes) {
		try {
			shiftAllocationService.deleteShiftAllocation(date, employeeId, shiftId);
			redirectAttributes.addFlashAttribute("success", 1);
			return "redirect:/AllocatedShifts";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("success", 0);
			System.out.println(e);
		}
		return "hrm/allocatedShifts";
	}

	@PostMapping("/updateShiftAllocation")
	public String saveShiftMaster(@Valid @ModelAttribute("ShiftAllocation") ShiftAllocation shiftAllocation,
			@RequestParam("shiftId") String shiftId, BindingResult br, RedirectAttributes redirectAttributes) {

		if (br.hasErrors()) {
			return "hrm/updateShiftAllocation";
		} else {
			try {
				shiftAllocationService.deleteShiftAllocation(shiftAllocation.getShiftAllocationPK().getDate(),
						shiftAllocation.getShiftAllocationPK().getEmployee().getEmpID(),
						shiftAllocation.getShiftAllocationPK().getShiftmaster().getShiftId());
				ShiftMaster shift = shiftMasterService.findShiftById(shiftId);
				shiftAllocation.getShiftAllocationPK().setShiftmaster(shift);
				shiftAllocationService.saveShiftAllocation(shiftAllocation);
				redirectAttributes.addFlashAttribute("success", 1);
				return "redirect:/AllocatedShifts";
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("success", 0);
				System.out.println(e);
			}
		}
		return "hrm/updateShiftAllocation";
	}
}
