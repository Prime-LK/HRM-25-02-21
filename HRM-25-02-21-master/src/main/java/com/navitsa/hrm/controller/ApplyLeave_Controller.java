package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.ApplyLeave_Entity;
import com.navitsa.hrm.entity.CompanyMaster;
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
	
	
	@RequestMapping(value = "/applyLeaves", method = RequestMethod.GET)
	public String ALOpen(Map<String, Object>model,HttpSession session) {
		
		String companyID=(String) session.getAttribute("company.comID");
		CompanyMaster cm = new CompanyMaster();
		cm.setComID(companyID);
		
		ApplyLeave_Entity obj = new ApplyLeave_Entity();
		obj.setCompany(cm);
		
		model.put("applyleave", obj);
		
		//model.put("applyleaveAll" , ALService.getAll());
		
		return "hrm/applyLeaves";
	}
	
	@RequestMapping(value = "/ShortleaveOpen", method = RequestMethod.GET)
	public String SLOpen(Map<String, Object>model) {
		model.put("applyleave", new ApplyLeave_Entity());
		model.put("applyleaveAll" , ALService.getAll());
		
		return "hrm/ShortLeave";
	}
	
	@ModelAttribute("leaveAll")
	public List<leaveClass> findAllLeaves(){
		return leaveClassService.getAllLeaves();
	}
	
	@ModelAttribute("EmpAll")
	public List<Employee> findAllEmp(){
		return empService.getAllEmp();
	}
	
	@ModelAttribute("DepAll")
	public List<DepartmentMaster> findAllDepartments(){
		return depService.getAllDep();
	}
	
	@RequestMapping(value = "/applyLeave", method = RequestMethod.POST)
	public String applyLeave(@ModelAttribute("applyleave") ApplyLeave_Entity applyleave,
			RedirectAttributes redirectAttributes) {
		try {
			applyleave.setLeaveID("00000".substring(ALService.getMaxID().length()) + ALService.getMaxID());
			ALService.applyLeave(applyleave);
			redirectAttributes.addFlashAttribute("success", 1);
			return "redirect:/applyLeaves";	
		} catch (Exception e) {
			System.out.println(e); 
		}
		return "hrm/applyLeaves";

	}
	

}
