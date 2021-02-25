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
import com.prime.hrm.report.EmployeeReportBean;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeService;

@Controller("EmployeeReportController")
public class EmployeeReportController {

	@Autowired
	private EmployeeService empService;
	
	@Autowired
	
	private DepartmentService depservive;
	
	@GetMapping("employeeReport")
	public String loadPage() {
		return "employeeReport";
	}
	
	@ModelAttribute("getEmps")
	public List<Employee> getAllEmp() {
		return empService.getAllEmp();
	}
	
	@ModelAttribute("getEmpDe")
	public List<Employee> getAllEmpDe() {
	
		return empService.getAllEmp();
	}
	
	// print Employee summary report
	 @GetMapping("/submitEmpReport")
	 public String print1(@RequestParam String empID,Model m)
	 {
		 String[][] result = empService.getEmployeeReportData(empID);
		 
		 List<EmployeeReportBean> list = new ArrayList<>();
		 
		 for(int i=0; i<result.length;i++ )
		 {
			 EmployeeReportBean ed = new EmployeeReportBean();
			 
			 ed.setEmpid(result[i][0]);
	    	   ed.setNa(result[i][1]);
	    	   ed.setRe(result[i][2]);
	    	   ed.setMarital(result[i][3]);
	    	   ed.setFname(result[i][4]);
	    	   ed.setDob(result[i][5]);
	    	   ed.setGender(result[i][6]);
//	    	   ed.setImg(result[i][7]);
	    	  
	    	   ed.setAdd(result[i][7]);
	    	   ed.setCity(result[i][8]);
	    	   ed.setState(result[i][9]);
	    	   ed.setIdNumber(result[i][10]);
	    	   ed.setDlNumber(result[i][11]);
	    	   ed.setPassNumber(result[i][12]);
	    	   ed.setEmeNo(result[i][13]);
	    	   ed.setBg(result[i][14]);
	    	   ed.setLname(result[i][15]);
	    	   ed.setContact1(result[i][16]);
	    	   ed.setContact2(result[i][17]);
	    	   ed.setEmail_emp(result[i][18]);
	    	   ed.setCategory(result[i][19]);
	    	   ed.seteType(result[i][20]);
	    	   ed.setsRange(result[i][21]);
	    	   ed.setsGrade(result[i][22]);
	    	   ed.setDesignation(result[i][23]);
	    	   ed.setjProfile(result[i][24]);
	    	   ed.setjDate(result[i][25]);
	    	   ed.setLocation(result[i][26]);
	    	   ed.setDep(result[i][27]);
	    	   ed.setdStatus(result[i][28]);
	    	   ed.setReporter(result[i][29]);
	    	   ed.setEmpBank(result[i][30]);
	    	   ed.setEmpBranch(result[i][31]);
	    	   ed.setEmpAccount(result[i][32]);
	    	   
	    	   list.add(ed);
	    	   System.out.println(ed.getEmpBank() + " " + ed.getEmpAccount()+ " " + ed.getCategory());
		 }
		 
		 m.addAttribute("employee", list);
		 
		return "printEmployeeReport";

	 }
	
//	 @GetMapping("/submitEmpReport")
//	    public String handleForexRequest( Model model) {
//	        model.addAttribute("employee", getTodayForexRates(empID));
//	        return "ghi";
//	    }
//	    
//	    private List<EmployeeReportBean> getTodayForexRates(@RequestParam String empID) {
//	        //dummy rates
//	        
//	       String[][] result= empService.getEmployeeReportData(empID);
//
//	       List<EmployeeReportBean> employeeSummaryReportArray = new ArrayList<>();
//	       
//	       for(int i=0; i<result.length;i++ ) {
//	    	   EmployeeReportBean ed =new EmployeeReportBean();
//	    	   ed.setEmpid(result[i][0]);
//	    	   ed.setNa(result[i][1]);
//	    	   ed.setRe(result[i][2]);
//	    	   ed.setMarital(result[i][3]);
//	    	   ed.setFname(result[i][4]);
//	    	   ed.setDob(result[i][5]);
//	    	   ed.setGender(result[i][6]);
//	    	   ed.setImg(result[i][7]);
//	    	   ed.setAdd(result[i][8]);
//	    	   ed.setCity(result[i][9]);
//	    	   ed.setState(result[i][10]);
//	    	   ed.setIdNumber(result[i][11]);
//	    	   ed.setDlNumber(result[i][12]);
//	    	   ed.setPassNumber(result[i][13]);
//	    	   ed.setEmeNo(result[i][14]);
//	    	   ed.setBg(result[i][15]);
//	    	   ed.setBank(result[i][16]);
//	    	   ed.setBranch(result[i][17]);
//	    	   ed.setAccount(result[i][18]);
//	    	   ed.setLname(result[i][19]);
//	    	   ed.setContact1(result[i][20]);
//	    	   ed.setContact2(result[i][21]);
//	    	   ed.setEmail(result[i][22]);
//	    	   ed.setCategory(result[i][23]);
//	    	   ed.seteType(result[i][24]);
//	    	   ed.setsRange(result[i][25]);
//	    	   ed.setsGrade(result[i][26]);
//	    	   ed.setDesignation(result[i][27]);
//	    	   ed.setjProfile(result[i][28]);
//	    	   ed.setjDate(result[i][29]);
//	    	   ed.setLocation(result[i][30]);
//	    	   ed.setDep(result[i][31]);
//	    	   ed.setdStatus(result[i][32]);
//	    	   ed.setReporter(result[i][33]);
//	    	   
//	    	   employeeSummaryReportArray.add(ed);
//	    	   System.out.println(ed.getEmpid() + " " + ed.getFname()+ " " + ed.getCategory());
//	       }
//	       	       
//	        return employeeSummaryReportArray;
//	    }	
	 
	 
	 //get department 
	 @ModelAttribute("getdep")
		public List<DepartmentMaster> getAlldep() {
			return depservive.getAllDep();
		}
	 
	 //get relevant employee
	 @GetMapping("/getemptable")
	    public @ResponseBody List<EmployeeDetails> loadFilteringData(@RequestParam("depID") String depID) {
	    	List<EmployeeDetails> detail = empService.getEmployeefilteredData(depID);
	    	
	    	return detail;
	    }
	    
}
