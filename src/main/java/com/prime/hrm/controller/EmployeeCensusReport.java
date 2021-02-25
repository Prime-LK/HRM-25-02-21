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
import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeDetails;
import com.prime.hrm.report.CensusReportBean;
import com.prime.hrm.report.CensusReportNewBean;
import com.prime.hrm.report.SkillReportBean;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeService;

@Controller("EmployeeCensusReport")
public class EmployeeCensusReport {
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService depService;
	
	@GetMapping("/censusReport")
	public String getPage() {
		return "censusReport";
	}
	
	@ModelAttribute("allEmp")
	public List<Employee> getAllEmployee() {
		return empService.getAllEmp();
	}
	
	@ModelAttribute("allEmpDetails")
	public List<EmployeeDetails> getAllEmployeeDe() {
		return empService.getAllEmpDetails();
	}
	
	@ModelAttribute("deps")
	public List<DepartmentMaster> getAllDeps() {
		return depService.getAllDep();
	}
	
	// print Employee summary report
	 @GetMapping("/submitCReport")
	 public String printQuaReport(@RequestParam String depID,Model m)
	 {
		 String[][] result = empService.filterEemployee(depID);
		 
		 List<CensusReportBean> list = new ArrayList<>();
		 
		 for(int i=0; i<result.length;i++ )
		 {
			 CensusReportBean emp = new CensusReportBean();
			   emp.setEmpid(result[i][0]);
	    	   emp.setFname(result[i][1]);
	    	   emp.setLname(result[i][2]);
	    	   emp.setContact(result[i][3]);
	    	   emp.setEmail(result[i][4]);
	    	   emp.setDob(result[i][5]);
	    	   emp.setDep(result[i][6]);
	    	   emp.setContact2(result[i][7]);
	    	   emp.setContact3(result[i][8]);
	    	   emp.setEmpDesignation(result[i][9]);
	    	   emp.setEmpJoinedDate(result[i][10]);
	    	   emp.setEmpResignedDate(result[i][11]);
	    	   emp.setEstatus(result[i][12]);
			 
	    	   list.add(emp);
	    	   System.out.println(emp.getEmpid() + " " + emp.getContact2()+ " " + emp.getContact3());
		 }
		 
		 m.addAttribute("count", list);
		 
		return "printCensusReport2";

	 }
	
	 @GetMapping("/submitC2Report")
	    public String handleForexRequest(Model model) {
	        model.addAttribute("count", getTodayForexRates());
	        return "printCensusReport";
	    }
	    
	    private List<CensusReportNewBean> getTodayForexRates() {
	        //dummy rates
	        
	       String[][] result=empService.getData();

	       List<CensusReportNewBean> empSummaryReportArray = new ArrayList<>();
	       
	       for(int i=0; i<result.length;i++ ) {
	    	   CensusReportNewBean emp =new CensusReportNewBean();
	    	   emp.setEmpid(result[i][0]);
	    	   emp.setFname(result[i][1]);
	    	   emp.setLname(result[i][2]);
	    	   emp.setContact(result[i][3]);
	    	   emp.setEmail(result[i][4]);
	    	   emp.setDob(result[i][5]);
	    	   emp.setEmpDesignation(result[i][6]);
	    	   emp.setEmpJoinedDate(result[i][7]);
	    	   emp.setEmpResignedDate(result[i][8]);
	    	   emp.setEstatus(result[i][9]);
	    	   empSummaryReportArray.add(emp);
	    	   System.out.println(emp.getEmpid() + " " + emp.getFname()+ " " + emp.getLname());
	       }
	       	       
	        return empSummaryReportArray;
	    }
	    
	    @GetMapping("/loadGrip")
	    public @ResponseBody List<EmployeeDetails> loadGrid(@RequestParam("dep") String dep) {
	    	List<EmployeeDetails> de = empService.filterRelatedData(dep);
	    	System.out.println(dep);
	    	return de;
	    } 
	    
	    //get employee based on joined date
	    @GetMapping("/loadGrid")
	    public @ResponseBody List<EmployeeDetails> loadGridusingjoinedDate(@RequestParam("joinedDate") String joinedDate , @RequestParam("joinedDate2") String joinedDate2  ) {
	    	List<EmployeeDetails> joined = empService.filterRelatedempbasedonjoinedDate(joinedDate , joinedDate2);
	    	System.out.println(joinedDate);
	    	
	    	return joined;
	    } 
	    
	    //joined date based report
	    @GetMapping("/submitjoinedDateBasedReport")
		 public String printjoinedbasedReport(@RequestParam("joinedDate") String joinedDate,@RequestParam("joinedDate2") String joinedDate2, Model m)
		 {
			 String[][] result = empService.getCensusReportDatabyfilteringemployeebasedonjoinedDate(joinedDate, joinedDate2);
			 
			 List<CensusReportBean> list = new ArrayList<>();
			 
			 for(int i=0; i<result.length;i++ )
			 {
				 CensusReportBean emp = new CensusReportBean();
				   emp.setEmpid(result[i][0]);
		    	   emp.setFname(result[i][1]);
		    	   emp.setLname(result[i][2]);
		    	   emp.setContact(result[i][3]);
		    	   emp.setEmail(result[i][4]);
		    	   emp.setDob(result[i][5]);
		    	   emp.setDep(result[i][6]);
		    	   emp.setContact2(result[i][7]);
		    	   emp.setContact3(result[i][8]);
		    	   emp.setEmpDesignation(result[i][9]);
		    	   emp.setEmpJoinedDate(result[i][10]);
		    	   emp.setEmpResignedDate(result[i][11]);
		    	   emp.setEstatus(result[i][12]); 
		    	   
		    	   list.add(emp);
		    	   System.out.println(emp.getEmpid() + " " + emp.getContact2()+ " " + emp.getContact3());
			 }
			 
			 m.addAttribute("empCensus", list);
			 
			return "printCensusReport3";

		 }
	    
	    @GetMapping("/resigndateBasedloadGrid")
	    public @ResponseBody List<EmployeeDetails> loadGridusingresignDate(@RequestParam("resignDate") String resignDate , @RequestParam("resignDate2") String resignDate2  ) {
	    	List<EmployeeDetails> resigned = empService.filterRelatedempbasedonresignDate(resignDate, resignDate2);
	    	System.out.println(resignDate);
	    	
	    	return resigned;
	    } 
	    
	    //get report based on resign date 
	    @GetMapping("/submitresignDateBasedReport")
		 public String printresignDatebasedReport(@RequestParam("resignDate") String resignDate,@RequestParam("resignDate2") String resignDate2, Model m)
		 {
			 String[][] result = empService.getCensusReportDatabyfilteringemployeebasedonresignDate(resignDate, resignDate2);
			 
			 List<CensusReportBean> listOfResignEmp = new ArrayList<>();
			 
			 for(int i=0; i<result.length;i++ )
			 {
				 CensusReportBean emp = new CensusReportBean();
				   emp.setEmpid(result[i][0]);
		    	   emp.setFname(result[i][1]);
		    	   emp.setLname(result[i][2]);
		    	   emp.setContact(result[i][3]);
		    	   emp.setEmail(result[i][4]);
		    	   emp.setDob(result[i][5]);
		    	   emp.setDep(result[i][6]);
		    	   emp.setContact2(result[i][7]);
		    	   emp.setContact3(result[i][8]);
		    	   emp.setEmpDesignation(result[i][9]);
		    	   emp.setEmpJoinedDate(result[i][10]);
		    	   emp.setEmpResignedDate(result[i][11]);
		    	   emp.setEstatus(result[i][12]); 
		    	   
		    	   listOfResignEmp.add(emp);
		    	   System.out.println(emp.getEmpid() + " " + emp.getContact2()+ " " + emp.getContact3());
			 }
			 
			 m.addAttribute("empCensusbasedOnResignDate", listOfResignEmp);
			 
			return "printCensusReport4";

		 }
	    
}
