package com.prime.hrm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prime.hrm.entity.DepartmentMaster;

import com.prime.hrm.report.EmployeeContactSummaryReportBean;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeService;

@Controller("contactReportController")
public class ContactReportController {
	

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService dep;
	
	@RequestMapping("/contactReport")
	public String employee(Map<String, Object> model) {
		
		List<DepartmentMaster> listEmployee = dep.getAllDep();
	//	Collections.sort(listEquipmentMaster);
		model.put("listEmployee", listEmployee);

		return "contactReport";
	}

	
	 @GetMapping("/contactRepo")
	    public String handleForexRequest(@RequestParam("depID") String depID ,Model model) {
	      
	    
	    
	   
	    	
	    	 String[][] result=employeeService.getEmployeefilteredcontactData(depID);
	    	 
	    	 List<EmployeeContactSummaryReportBean> employeeContactSummaryReportBeanArray = new ArrayList<>();
	    	 
	    	 for(int i=0; i<result.length;i++ ) {
	    		 EmployeeContactSummaryReportBean empC=new EmployeeContactSummaryReportBean();
	    		 empC.setEmpid(result[i][0]);
	    		 empC.setFname(result[i][1]);
	    		 empC.setLname(result[i][2]);
	    		 empC.setEmpAdd(result[i][3]);
	    		 empC.setContact_h(result[i][4]);
	    		 empC.setContact_c(result[i][5]);
	    		 empC.setEmpemail(result[i][6]);
		    	 empC.setEmpcity(result[i][7]);
		    	 empC.setEmpstate(result[i][8]);
		    	   
	    		 employeeContactSummaryReportBeanArray.add(empC);
	    		 System.out.println(empC.getFname()+"-------"+ empC.getContact_h());
		       }
	    	 model.addAttribute("empcontacts",employeeContactSummaryReportBeanArray );
	    	 
			return "printContactReport";
	       
	       
	     
	       	       
	        
	    }	
	
}
