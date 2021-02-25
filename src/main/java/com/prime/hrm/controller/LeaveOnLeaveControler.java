package com.prime.hrm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prime.hrm.entity.CalanderEntity;
import com.prime.hrm.entity.LeaveOnLeaveEntity;
import com.prime.hrm.entity.leaveClass;
import com.prime.hrm.service.LeaveclassService;
import com.prime.hrm.service.leaveOnLeaveService;

@Controller
public class LeaveOnLeaveControler {
	
	@Autowired
	private leaveOnLeaveService LoLService;
	
	@RequestMapping(value = "/leaveOnLeave", method = RequestMethod.GET)
	public String leaveOnLeave(Map<String, Object> model) {
		model.put("calander", new LeaveOnLeaveEntity());
		model.put("LoLAll", LoLService.getAll());

		return "leaveOnLeave";
	}
	
	@GetMapping("/LeaveOnLeave")
	@ResponseBody
	public LeaveOnLeaveEntity loadData(@RequestParam("empID") String empID) {
		LeaveOnLeaveEntity Cal = LoLService.loadData(empID);
		return Cal;
	}
	
	@RequestMapping(value = "/saveAll", method = RequestMethod.POST)
	public String saveAll(@ModelAttribute("leaveOL") LeaveOnLeaveEntity leave) {
		LoLService.saveAll(leave);
		return "redirect:/leaveOnLeave";
	}

}

