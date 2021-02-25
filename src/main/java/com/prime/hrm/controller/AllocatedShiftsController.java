package com.prime.hrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.prime.hrm.service.ShiftAllocationService;

@Controller
public class AllocatedShiftsController {

	@Autowired
	private ShiftAllocationService shiftAllocationService;
	
	@GetMapping("/AllocatedShifts")
	public String allocatedShiftsPage() {
		return "allocatedShifts";
	}
	
	@ModelAttribute("shiftAllocationList")
	public List<String> getAllShiftAllocations() {
		List<String> allocations = shiftAllocationService.loadShiftAllocation();
		return allocations;
	}
}
