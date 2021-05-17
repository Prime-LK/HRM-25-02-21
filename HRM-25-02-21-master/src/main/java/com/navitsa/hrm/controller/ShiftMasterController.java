package com.navitsa.hrm.controller;

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

import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.service.ShiftMasterService;

@Controller
public class ShiftMasterController {

	@Autowired
	private ShiftMasterService shiftMasterService;

	@GetMapping("/ShiftMaster")
	public String loadAsPage(Map<String, Object> map, HttpSession session) {
		map.put("ShiftMaster", new ShiftMaster());
		ShiftMaster shift = new ShiftMaster();
		String companyId = session.getAttribute("company.comID").toString();
		shift.setShiftId("00000".substring(shiftMasterService.getMaxShiftId(companyId).length())
				+ shiftMasterService.getMaxShiftId(companyId));
		map.put("ShiftMaster", shift);
		return "hrm/shiftMaster";
	}

	@PostMapping("/saveShiftMaster")
	public String saveShiftMaster(@Valid @ModelAttribute("ShiftMaster") ShiftMaster shift, BindingResult br) {

		if (br.hasErrors()) {
			return "hrm/ShiftMaster";
		} else {
			try {
				shiftMasterService.saveShift(shift);
				return "redirect:/hrm/ShiftMaster";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/ShiftMaster";
	}

	@ModelAttribute("shiftList")
	public List<ShiftMaster> getAllShifts(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return shiftMasterService.loadAllShifts(companyId);
	}

	// update shift master
	@GetMapping("/updateShiftMaster")
	public ModelAndView updateShiftMaster(@RequestParam String id, HttpSession session) {
		ModelAndView mav = new ModelAndView("shiftMaster");// jsp
		String companyId = session.getAttribute("company.comID").toString();
		ShiftMaster shiftMaster = shiftMasterService.findShiftById2(id, companyId);
		mav.addObject("ShiftMaster", shiftMaster);// model attribute name and object
		return mav;
	}
}
