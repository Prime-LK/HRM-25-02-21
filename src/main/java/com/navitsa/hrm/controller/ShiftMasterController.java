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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.ShiftAllocation;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.service.CompanyService;
import com.navitsa.hrm.service.ShiftMasterService;

@Controller
public class ShiftMasterController {

	@Autowired
	private ShiftMasterService shiftMasterService;

	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/Shift")
	public String loadAsPage(Map<String, Object> map) {
		map.put("ShiftMaster", new ShiftMaster());
		ShiftMaster shift = new ShiftMaster();
		shift.setShiftId("00000".substring(shiftMasterService.getMaxShiftId().length())
				+ shiftMasterService.getMaxShiftId());
		map.put("ShiftMaster", shift);
		return "hrm/shiftMaster";
	}

	@PostMapping("/saveShift")
	public String saveShiftMaster(@Valid @ModelAttribute("ShiftMaster") ShiftMaster shift, BindingResult br, @RequestParam("companyId") String companyId,
			RedirectAttributes redirectAttributes) {
		CompanyMaster company = companyService.findbyCompanyid(companyId);
		shift.setCompany(company);
		if (br.hasErrors()) {
			return "hrm/ShiftMaster";
		} else {
			try {
				shiftMasterService.saveShift(shift);
				redirectAttributes.addFlashAttribute("success", 1);
				return "redirect:/Shift";
			} catch (Exception e) {
				redirectAttributes.addFlashAttribute("success", 0);
				System.out.println(e);
			}
		}
		return "hrm/ShiftMaster";
	}

	@ModelAttribute("shiftList")
	public List<ShiftMaster> findAllShiftsByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		List<ShiftMaster> list = shiftMasterService.findAllShiftsByCompany(companyId);
		List<ShiftMaster> shiftList  = new ArrayList<>();
		DateTimeFormatter formattertime = DateTimeFormatter.ofPattern("HH:mm");
		for(int i = 0; i < list.size(); i++) {
			ShiftMaster shift = new ShiftMaster();
			shift.setShiftId(list.get(i).getShiftId());
			shift.setDescription(list.get(i).getDescription());
			shift.setStartTime(LocalTime.parse(list.get(i).getStartTime()).format(formattertime));
			shift.setEndTime(LocalTime.parse(list.get(i).getEndTime()).format(formattertime));
			shift.setContinuing(list.get(i).isContinuing());
			shift.setCompany(list.get(i).getCompany());
			shiftList.add(shift);
		}
		return shiftList;
	}

	// update shift master
	@GetMapping("/updateShift")
	public ModelAndView updateShift(@RequestParam String id, HttpSession session) {
		ModelAndView mav = new ModelAndView("hrm/shiftMaster");// jsp
		String companyId = session.getAttribute("company.comID").toString();
		ShiftMaster shiftMaster = shiftMasterService.findShiftByIdAndCompany(id, companyId);
		mav.addObject("ShiftMaster", shiftMaster);// model attribute name and object
		return mav;
	}
	
	@GetMapping("/findShiftByIdAndCompany")
	public @ResponseBody ShiftMaster findShiftByIdAndCompany(@RequestParam("shiftId") String shiftId, HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		ShiftMaster shiftMaster = shiftMasterService.findShiftByIdAndCompany(shiftId, companyId);
		return shiftMaster;
	}
}
