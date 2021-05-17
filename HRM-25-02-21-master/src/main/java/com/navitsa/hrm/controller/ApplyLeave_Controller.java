package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.navitsa.hrm.entity.ApplyLeave_Entity;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.leaveClass;
import com.navitsa.hrm.service.ApplyLeave_Service;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.LeaveclassService;

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
		
		return "hrm/ApplyCancel_Leaves";
	}
	
	@RequestMapping(value = "/ShortleaveOpen", method = RequestMethod.GET)
	public String SLOpen(Map<String, Object>model) {
		model.put("applyleave", new ApplyLeave_Entity());
		model.put("applyleaveAll" , ALService.getAll());
		
		return "hrm/ShortLeave";
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
	
	return "redirect:/hrm/ApplyleaveOpen";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
