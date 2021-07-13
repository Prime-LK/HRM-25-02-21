/**
 * 
 */
package com.navitsa.hrm.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.LeaveType;
import com.navitsa.hrm.service.LeaveTypeService;


@SuppressWarnings("unused")
@Controller
public class LeaveTypeController {
	
	@Autowired
	private LeaveTypeService LeaveTypeService;
	
	@RequestMapping(value = "/leaveTypes", method = RequestMethod.GET)
	public String loadForm(Map<String, Object> model, HttpSession session) {
		String companyId = (String) session.getAttribute("company.comID");
		model.put("leaveTypeForm", new LeaveType());
		model.put("typeList", LeaveTypeService.getLeaveTypesByCompany(companyId));
		
		return "hrm/leaveTypes";
	}

	@RequestMapping(value = "/saveLeaveType", method = RequestMethod.POST)
	public String saveLeave(@ModelAttribute("leaveTypeForm") LeaveType leaveType,
			RedirectAttributes redirectAttributes,HttpSession session) {
		
		String companyId = (String) session.getAttribute("company.comID");
		CompanyMaster cm = new CompanyMaster();
		cm.setComID(companyId);
		try {
			leaveType.setCompany(cm);
			LeaveTypeService.saveLeaveType(leaveType);;
			redirectAttributes.addFlashAttribute("success", 1);
			return "redirect:/leaveTypes";
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return "hrm/leaveTypes";
	}
	
	@RequestMapping(value="/editLeaveType", method= RequestMethod.GET)
	public ModelAndView updatename(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/leaveTypes");
		try {
			LeaveType leaveType = LeaveTypeService.getLeaveTypeByCode(id);
			mav.addObject("leaveTypeForm", leaveType);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return mav;
	}
		

}


