package com.prime.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prime.hrm.entity.ShiftMaster;
import com.prime.hrm.service.ShiftMasterService;

@Controller
public class ShiftMasterController {

	@Autowired
	private ShiftMasterService shiftMasterService;

	@GetMapping("/ShiftMaster")
	public String loadAsPage(Map<String, Object> map) {
		map.put("ShiftMaster", new ShiftMaster());
		ShiftMaster shift = new ShiftMaster();
		shift.setShiftId(
				"00000".substring(shiftMasterService.getMaxShiftId().length()) + shiftMasterService.getMaxShiftId());
		map.put("ShiftMaster", shift);
		return "shiftMaster";
	}

	@PostMapping("/saveShiftMaster")
	public String saveShiftMaster(@Valid @ModelAttribute("ShiftMaster") ShiftMaster shift, BindingResult br) {

		if (br.hasErrors()) {
			return "ShiftMaster";
		} else {
			try {
				shiftMasterService.saveShift(shift);
				return "redirect:/ShiftMaster";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "ShiftMaster";
	}

	@ModelAttribute("shiftList")
	public List<ShiftMaster> getAllShifts() {
		return shiftMasterService.loadAllShifts();
	}

	// update shift master
	@GetMapping("/updateShiftMaster")
	public ModelAndView updateShiftMaster(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("shiftMaster");// jsp
		ShiftMaster shiftMaster = shiftMasterService.findShiftById(id);
		mav.addObject("ShiftMaster", shiftMaster);// model attribute name and object
		return mav;
	}
}
