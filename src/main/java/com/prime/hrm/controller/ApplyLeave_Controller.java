package com.prime.hrm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.prime.hrm.entity.ApplyLeave_Entity;
import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.leaveClass;
import com.prime.hrm.service.ApplyLeave_Service;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeService;
import com.prime.hrm.service.LeaveclassService;

@Controller
public class ApplyLeave_Controller {

	@Autowired
	private ApplyLeave_Service ALService;
	
	@Autowired
	private LeaveclassService leaveClassService;
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService depService;
	
	
	@RequestMapping(value = "/ApplyleaveOpen", method = RequestMethod.GET)
	public String ALOpen(Map<String, Object>model) {
		model.put("applyleave", new ApplyLeave_Entity());
		model.put("applyleaveAll" , ALService.getAll());
		
		return "ApplyCancel_Leaves";
	}
	
	@RequestMapping(value = "/ShortleaveOpen", method = RequestMethod.GET)
	public String SLOpen(Map<String, Object>model) {
		model.put("applyleave", new ApplyLeave_Entity());
		model.put("applyleaveAll" , ALService.getAll());
		
		return "ShortLeave";
	}
	
	@ModelAttribute("leaveAll")
	public List<leaveClass>showleaves(){
		return leaveClassService.getAllLeaves();
	}
	
	@ModelAttribute("EmpAll")
	public List<Employee>showEmp(){
		return empService.getAllEmp();
	}
	
	@ModelAttribute("DepAll")
	public List<DepartmentMaster>shoeDep(){
		return depService.getAllDep();
	}
	
	@RequestMapping(value = "/savePage", method = RequestMethod.POST)
	public String savepage(@ModelAttribute("applyleave")ApplyLeave_Entity applyleave) {
	ALService.savepage(applyleave);
	
	return "redirect:/ApplyleaveOpen";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
