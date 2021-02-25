package com.prime.hrm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.EmployeeDependent;
import com.prime.hrm.entity.EmployeeDetails;
import com.prime.hrm.report.EmployeeDependentReportBean;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.DependentService;

@Controller("EmployeeDependentReport")
public class EmployeeDependentReport {

	@Autowired
	private DependentService depService;
	
	@Autowired
	private DepartmentService depaService;
	
	@ModelAttribute("allEmpDep")
	public List<EmployeeDependent> getAllDep() {
		return depService.getAllEmpDep();
	}
	
	@ModelAttribute("depMaster")
	public List<DepartmentMaster> getDeps() {
		return depaService.getAllDep();
	}
	
	@GetMapping("/dependentReport")
	public String getPage() {
		return "dependentReport";
	}
	
	// print dependent summary report
	 @GetMapping("/submitDReport")
	 public String printQuaReport(@RequestParam String Department_ID, Model m)
	 {
		 String[][] result = depService.filterEmpDependents(Department_ID);
		 
		 List<EmployeeDependentReportBean> list = new ArrayList<>();
		 
		 for(int i=0; i<result.length;i++ )
		 {
			 EmployeeDependentReportBean ed = new EmployeeDependentReportBean();
			   ed.setEmpid(result[i][0]);
	    	   ed.setFname(result[i][1]);
	    	   ed.setLname(result[i][2]);
	    	   ed.setDtype(result[i][3]);
	    	   ed.setDname(result[i][4]);
	    	   ed.setdDob(result[i][5]);
	    	   ed.setContact(result[i][6]);
	    	   list.add(ed);
	    	   System.out.println(ed.getEmpid() + " " + ed.getFname()+ " " + ed.getDtype());
		 }
		 
		 m.addAttribute("dependent", list);
		 
		return "printDependentReport2";
	 }
	 
	 @GetMapping("/submitReport")
	    public String handleForexRequest(Model model) {
	        model.addAttribute("dependent", getTodayForexRates());
	        return "printDependentReport";
	    }
	    
	    private List<EmployeeDependentReportBean> getTodayForexRates() {
	        //dummy rates
	        
	       String[][] result=depService.getDepReportData();

	       List<EmployeeDependentReportBean> equipmentSummaryReportArray = new ArrayList<>();
	       
	       for(int i=0; i<result.length;i++ ) {
	    	   EmployeeDependentReportBean ed =new EmployeeDependentReportBean();
	    	   ed.setEmpid(result[i][0]);
	    	   ed.setFname(result[i][1]);
	    	   ed.setLname(result[i][2]);
	    	   ed.setDtype(result[i][3]);
	    	   ed.setDname(result[i][4]);
	    	   ed.setdDob(result[i][5]);
	    	   ed.setContact(result[i][6]);
	    	   equipmentSummaryReportArray.add(ed);
	    	   System.out.println(ed.getEmpid() + " " + ed.getFname()+ " " + ed.getDtype());
	       }
	       	       
	        return equipmentSummaryReportArray;
	    }
	    
	    @GetMapping("/loadData")
	    public @ResponseBody List<EmployeeDetails> loadFilteringData(@RequestParam("depID") String depID) {
	    	List<EmployeeDetails> detail = depService.loadFilteringData(depID);
	    	System.out.println(depID);
	    	return detail;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}
