package com.navitsa.hrm.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.ApplyLeave_Entity;
import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.LeaveType;
import com.navitsa.hrm.service.ApplyLeave_Service;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmpEntitlementService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.LeaveTypeService;

@Controller
public class ApplyLeave_Controller {

	@Autowired
	private ApplyLeave_Service ALService;
	
	@Autowired
	private LeaveTypeService leaveClassService;
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService depService;
	
	@Autowired
	private EmpEntitlementService empEntitlementService;
	
	
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
	
//	@ModelAttribute("leaveAll")
//	public List<LeaveType> findAllLeaves(){
//		return leaveClassService.getAllLeaves();
//	}
	
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
			RedirectAttributes ra) {
		
		String[] dates;
		dates = applyleave.getDates().split(",");
		
		if(dates.length>0) {

			try {
				applyleave.setLeaveID("00000".substring(ALService.getMaxID().length()) + ALService.getMaxID());
				applyleave.setApproved(false);
				applyleave.setDays(dates.length);
				applyleave.setCreateTime(new Date());
				
				ALService.applyLeave(applyleave);
				
				ra.addFlashAttribute("success", 1);
				return "redirect:/applyLeaves";	
			} catch (Exception e) {
				System.out.println(e); 
			}
			
		}
		
		return "hrm/applyLeaves";

	}
	
	@RequestMapping(value="/getAppliedLeavesByEmployee", method=RequestMethod.GET)
	public @ResponseBody List<ApplyLeave_Entity> getAppliedLeavesByEmployee(@RequestParam String employeeID) {
		
		List<ApplyLeave_Entity> ls = ALService.getappliedLeavesByEmployee(employeeID);
		return ls;
		
	}
	
	@RequestMapping(value="/getBalanceLeaves", method=RequestMethod.GET)
	public @ResponseBody String getBalanceLeaves(@RequestParam String employeeID,
			@RequestParam String leaveTypeID) {

		Employee employee =  empService.getEmp(employeeID);
		String empCat = employee.getEmployeeCategory();
		
		String noOfDays = empEntitlementService.findByIDs(leaveTypeID,empCat);
		String msg = "";
		
		if(noOfDays == null) {
			msg = "There is no entitlements for "+empCat;
		}else {
			
			try {
				
				int sumOfApprovedLeavesFull =  ALService.getSumOfApprovedLeaves(employeeID,leaveTypeID);
				int sumOfApprovedLeavesHalf =  ALService.getSumOfApprovedLeavesHalf(employeeID,leaveTypeID);
				double sumOfAllLeaves = sumOfApprovedLeavesFull + sumOfApprovedLeavesHalf/2.0;
				
				double balanceLeaves = Integer.valueOf(noOfDays)  - sumOfAllLeaves;
				
				if(balanceLeaves%1 == 0)
					msg  = "TOTAL "+empCat+" "+leaveTypeID+" : "+noOfDays+"\n"+"Balance Leaves : "+ (int) balanceLeaves+" day(s)";
				else {
					int fullDays = (int) (balanceLeaves/1);
					msg  = "TOTAL "+empCat+" "+leaveTypeID+" : "+noOfDays+"\n"+"Balance Leaves : "+fullDays+" day(s)"+" 1 Half Day";
				}
				
			} catch (Exception e) {
				System.out.println(e);
				msg = "TOTAL "+empCat+" "+leaveTypeID+" : "+noOfDays+"\n"+"Balance Leaves : "+noOfDays;
			}
		
		}
		return msg;
		
	}
	
	
	@GetMapping("/leaveApplied")
	public String leaveApplied() {
		
		return "hrm/applyLeaveConfirmation";
	}
	
	@RequestMapping(value = "/updateApprovedStatus", method = RequestMethod.POST)
	public String updateApprovedStatus(@RequestParam String applyLeaveID,
			@RequestParam Boolean approved,
			RedirectAttributes ra) {
		try {
			
			if(approved==true)
				ALService.updateApprovedStatus(applyLeaveID);
			
			ra.addFlashAttribute("success", 1);
			return "redirect:/leaveApplied";	
		} catch (Exception e) {
			System.out.println(e); 
		}
		return "hrm/applyLeaveConfirmation";

	}
	
	@RequestMapping(value="/getEmployeeByDepartmentID", method=RequestMethod.GET)
	public @ResponseBody void getEmployeeByDepartmentID(
			@RequestParam String departmentID) {
		
		System.out.println(departmentID);
		
		//List<Employee> ls = empService.
		//return ls;
		
	}
	
	@RequestMapping(value="/getBalanceLeaveSum", method=RequestMethod.GET)
	public @ResponseBody double getBalanceLeaveSum(@RequestParam String employeeID,
			@RequestParam String leaveTypeID) {

		Employee employee =  empService.getEmp(employeeID);
		String empCat = employee.getEmployeeCategory();
		
		double balanceLeaves = 0;
		
		String noOfDays = empEntitlementService.findByIDs(leaveTypeID,empCat);
			
			try {
				
				int sumOfApprovedLeavesFull =  ALService.getSumOfApprovedLeaves(employeeID,leaveTypeID);
				int sumOfApprovedLeavesHalf =  ALService.getSumOfApprovedLeavesHalf(employeeID,leaveTypeID);
				double sumOfAllLeaves = sumOfApprovedLeavesFull + sumOfApprovedLeavesHalf/2.0;
				
				balanceLeaves = Integer.valueOf(noOfDays)  - sumOfAllLeaves;
				
				
			} catch (Exception e) {
				System.out.println(e);
			}
		
		System.out.println("Balance Leave Sum "+balanceLeaves);
		return balanceLeaves;
		
	}
	
}
