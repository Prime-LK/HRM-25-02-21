package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navitsa.hrm.entity.ApplyLeave;
import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.service.ApplyLeave_Service;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmpEntitlementService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.LeaveTypeService;

@Controller
public class ApplyLeave_Controller {

	@Autowired
	private DepartmentService depService;
	@Autowired
	private LeaveTypeService leaveTypeService;	
	@Autowired
	private ApplyLeave_Service ALService;
	@Autowired
	private EmployeeService empService;
	@Autowired
	private EmpEntitlementService empEntitlementService;
	
	
	@RequestMapping(value = "/applyLeave", method = RequestMethod.GET)
	public String openForm(Map<String, Object>model,HttpSession session) {
		
		String companyID=(String) session.getAttribute("company.comID");
		CompanyMaster cm = new CompanyMaster();
		cm.setComID(companyID);	
		ApplyLeave obj = new ApplyLeave();
		obj.setCompany(cm);
		model.put("applyleave", obj);
		
		try {
			model.put("allDepartment", depService.getAllDepartmentByCompany(companyID));
			model.put("allLeaveType", leaveTypeService.getLeaveTypesByCompany(companyID));
			model.put("allEmployee",empService.getEmployeeDetailsByCompanyID(companyID));
		} catch (Exception e) {
			
		}
			
		return "hrm/applyLeaves";
	}
	
	@RequestMapping(value = "/saveLeave", method = RequestMethod.POST)
	public String saveLeave(@ModelAttribute("applyleave") ApplyLeave applyleave, 
			RedirectAttributes ra) {
		
		String[] dates;
		dates = applyleave.getDate().split(",");
		
		if(dates.length>0) {

			try {
				
				for (int i = 0; i < dates.length; i++) {
					applyleave.setDate(dates[i]);
					applyleave.setApproved(false);
					ALService.applyLeave(applyleave);
				}
						
				ra.addFlashAttribute("success", 1);
				return "redirect:/applyLeave";	
			} catch (Exception e) {
				System.out.println(e); 
			}
			
		}
		
		return "hrm/applyLeaves";

	}

	@RequestMapping(value="/getAppliedLeaveByEmployee", method=RequestMethod.GET)
	public @ResponseBody List<ApplyLeave> getAppliedLeaveByEmployee(
			@RequestParam String employeeID,HttpSession session) {
		
		String companyID=(String) session.getAttribute("company.comID");
		List<ApplyLeave> ls = ALService.getappliedLeaveByEmployee(employeeID, companyID);
		return ls;
		
	}
	
	@RequestMapping(value="/getEmployeeByEPFNo", method=RequestMethod.GET)
	public @ResponseBody EmployeeDetails getEmployeeByEPFNo(
			@RequestParam String epfNo,HttpSession session) {

		String companyId=(String) session.getAttribute("company.comID");
		EmployeeDetails ed = empService.findEmployeeByEpfNo02(epfNo, companyId);
		return ed;
		
	}
	
	@RequestMapping(value="/getBalanceLeaves", method=RequestMethod.GET)
	public @ResponseBody String getBalanceLeaves(@RequestParam String employeeID,
			@RequestParam String leaveTypeID, @RequestParam String companyID) {

		EmployeeDetails ed =  empService.findEmployeeDetailsByEmployeeIdAndCompany(employeeID, companyID);
		String empType = ed.getEmpType().getType();
		String empTypeID = ed.getEmpType().getTid();
		
		String noOfDays = empEntitlementService.findByLeaveTypeEmployeeType(leaveTypeID,empTypeID,companyID);
		String msg = "";
		
		if(noOfDays == null) {
			msg = "There is no entitlements for "+empType;
		}else {
			
			try {
				
				int sumOfApprovedLeavesFull =  ALService.getSumOfApprovedLeaves(employeeID,leaveTypeID,companyID);
				int sumOfApprovedLeavesHalf =  ALService.getSumOfApprovedLeavesHalf(employeeID,leaveTypeID,companyID);
				double sumOfAllLeaves = sumOfApprovedLeavesFull + sumOfApprovedLeavesHalf/2.0;			
				double balanceLeaves = Double.valueOf(noOfDays)  - sumOfAllLeaves;
				
				if(balanceLeaves%1 == 0)
					msg  = "TOTAL :"+noOfDays+" Balance Leave : "+ (int) balanceLeaves+" day(s)";
				else {
					int fullDays = (int) (balanceLeaves/1);
					msg  = "TOTAL :"+noOfDays+" Balance Leave : "+fullDays+" day(s)"+" 1 Half Day";
				}
				
			} catch (Exception e) {
				System.out.println(e);
				//msg = "TOTAL :"+noOfDays+" "+"Balance Leaves : "+noOfDays;
				msg = "System error, please try again later";
			}
		
		}
		return msg;
		
	}

	
	@GetMapping("/leaveApplied")
	public String leaveApplied(Map<String, Object>model,HttpSession session) {

		try {
			String companyID=(String) session.getAttribute("company.comID");
			model.put("allDepartment", depService.getAllDepartmentByCompany(companyID));
		} catch (Exception e) {
			
		}
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

	
	@RequestMapping(value="/getBalanceLeaveSum", method=RequestMethod.GET)
	public @ResponseBody double getBalanceLeaveSum(@RequestParam String employeeID,
			@RequestParam String leaveTypeID, @RequestParam String companyID) {
		
		EmployeeDetails ed =  empService.findEmployeeDetailsByEmployeeIdAndCompany(employeeID, companyID);
		String empType = ed.getEmpType().getType();
		String empTypeID = ed.getEmpType().getTid();
		String noOfDays = empEntitlementService.findByLeaveTypeEmployeeType(leaveTypeID,empTypeID,companyID);
		
		double balanceLeaves = 0;
			
			try {				
				int sumOfApprovedLeavesFull =  ALService.getSumOfApprovedLeaves(employeeID,leaveTypeID,companyID);
				int sumOfApprovedLeavesHalf =  ALService.getSumOfApprovedLeavesHalf(employeeID,leaveTypeID,companyID);
				double sumOfAllLeaves = sumOfApprovedLeavesFull + sumOfApprovedLeavesHalf/2.0;
				
				balanceLeaves = Double.valueOf(noOfDays)  - sumOfAllLeaves;
				
				
			} catch (Exception e) {
				System.out.println(e);
			}
		
		//System.out.println("Balance Leave Sum "+balanceLeaves);
		return balanceLeaves;
		
	}
	
	 // Edit leave
	
	@RequestMapping(value="/editLeave", method=RequestMethod.GET)
	public @ResponseBody ApplyLeave editLeave(@RequestParam String leaveID) {
		 
		ApplyLeave obj = null;
	     try {
	    	 
	    	 Optional<ApplyLeave> leave = ALService.findLeaveById(leaveID);
	    	 obj = leave.get();
	    	 
	     }catch (Exception e) {
			System.out.println(e);
		}

	     return obj;
	 }
	
	 // Delete leave
	
	@RequestMapping(value="/deleteLeave", method=RequestMethod.GET)
	public @ResponseBody String deleteLeave(@RequestParam String leaveID) {
		 
	     try {
	    	 
	    	 ALService.deleteLeaveById(leaveID);
	    	 
	     }catch (Exception e) {
			System.out.println(e);
		}
	  
	     return "success";
	 }
	
	
}
