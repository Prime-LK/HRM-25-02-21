package com.navitsa.hrm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.EmployeeCategory;
import com.navitsa.hrm.entity.EmployeeSalaryDetail;
import com.navitsa.hrm.entity.EmployeeType;
import com.navitsa.hrm.entity.LocationMaster;
import com.navitsa.hrm.entity.PayAddDeductTypes;
import com.navitsa.hrm.report.CensusReportBean;
import com.navitsa.hrm.report.SalaryDetailsReportBean;
import com.navitsa.hrm.report.SalaryDetailsReportBean1;
import com.navitsa.hrm.report.SalaryDetailsReportBean2;
import com.navitsa.hrm.report.SalaryDetailsReportBean3;
import com.navitsa.hrm.report.SalaryDetailsReportBean4;
import com.navitsa.hrm.report.SalaryDetailsReportBean5;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeSalaryService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.LocationService;
import com.navitsa.hrm.service.PayAddDeductTypeService;

@Controller("EmployeeSalaryDetailsReport")
public class EmployeeSalaryDetailsReport {
	
	@Autowired
	EmployeeSalaryService employeeSalaryService;
	
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	PayAddDeductTypeService payAddDeductTypeService;
	
	@GetMapping("/addDeductReport")
	public String getPage() {
		return "hrm/addDeductReport";
	}
	
	//load all departments
	@ModelAttribute("deps")
	public List<DepartmentMaster> getAllDeps() {
		return departmentService.getAllDep();
	}

	//load employee types
	@ModelAttribute("loadtypes")
	public List<EmployeeType> getAllempTypes() {
			return empService.getAllTypes();
	}
	
	//load locations
	@ModelAttribute("loadlocations")

	public List<LocationMaster> getAllLocations() {
			return locationService.getAllLocations();
	}
	
	//load categories
	@ModelAttribute("categories")
	public List<EmployeeCategory> getAllCategories() {
		return empService.getAllCategories();
	}
	
	// print Employee salary report based on depID
		 @GetMapping("/submitEmpSalaryDetails1")
		 public String printEmpSalaryDetails1Report(@RequestParam("depID") String depID ,@RequestParam("deductTypeCode")String deductTypeCode,Model m)
		 {
			 String[][] result = employeeSalaryService.loadSalarydetailsbasedondepID(depID , deductTypeCode);
			 
			 List<SalaryDetailsReportBean> list = new ArrayList<>();
			 
			 for(int i=0; i< result.length;i++ )
			 {
				 SalaryDetailsReportBean emp = new SalaryDetailsReportBean();
				   emp.setEmpiD(result[i][0]);
				   emp.setInactive_form(result[i][1]);
				   emp.setAdded_date(result[i][2]);
				   emp.setAdded_user(result[i][3]);
				   emp.setEfectiveDate(result[i][4]);
				   emp.setInactiveUser(result[i][5]);
				   emp.setActive(result[i][6]);
		    	   emp.setAddDeduct(result[i][7]);
		    	   emp.setEmpDep(result[i][8]);
		    	   emp.setEmpFname(result[i][9]);
		    	   emp.setEmpLname(result[i][10]);
		    	   emp.setAddDeductType(result[i][11]);
		    	   emp.setAddDedStatus(result[i][12]);
		    	   emp.setaDsvalue(result[i][13]);
		    	  
		    	  
		    	   list.add(emp);
		    	  
			 }
			 
			 m.addAttribute("salarydetailbasedonDep", list);
			 
			return "hrm/printSalaryDetailsReport1";

		 }
		 
		// print Employee salary report based on depID
		 
		 @GetMapping("/submitEmpSalaryDetails2")
		 public String printEmpSalaryDetail2Report(@RequestParam("loid") String loid,@RequestParam("deductTypeCode") String deductTypeCode,Model m)
		 {
			 String[][] result = employeeSalaryService.loadSalarydetailsbasedonloid(loid , deductTypeCode);
			 
			 List<SalaryDetailsReportBean1> list = new ArrayList<>();
			 
			 for(int i=0; i< result.length;i++ )
			 {
				 SalaryDetailsReportBean1 emp = new SalaryDetailsReportBean1();
				   emp.setEmpiD(result[i][0]);
				   emp.setInactive_form(result[i][1]);
				   emp.setAdded_date(result[i][2]);
				   emp.setAdded_user(result[i][3]);
				   emp.setEfectiveDate(result[i][4]);
				   emp.setInactiveUser(result[i][5]);
				   emp.setActive(result[i][6]);
		    	   emp.setAddDeduct(result[i][7]);
		    	   emp.setEmplocation(result[i][8]);
		    	   emp.setEmpFname(result[i][9]);
		    	   emp.setEmpLname(result[i][10]);
		    	   emp.setAddDeductType(result[i][11]);
		    	   emp.setAddDedStatus(result[i][12]);
		    	   emp.setaDsvalue(result[i][13]);
		    	  
		    	   list.add(emp);
		    	  
			 }
			 
			 m.addAttribute("salarydetailbasedonloid", list);
			 
			return "printSalaryDetailsReport2";

		 }
		 
		 //get employee salary details data based on category id
		 
		 @GetMapping("/submitEmpSalaryDetails3")
		 public String printEmpSalaryDetail3Report(@RequestParam("catgoryID") String catgoryID,@RequestParam("deductTypeCode") String deductTypeCode,Model m)
		 {
			 String[][] result = employeeSalaryService.loadSalarydetailsbasedoncategoryid(catgoryID , deductTypeCode);
			 List<SalaryDetailsReportBean2> list = new ArrayList<>();
			 
			 for(int i=0; i< result.length;i++ )
			 {
				 SalaryDetailsReportBean2 emp = new SalaryDetailsReportBean2();
				   emp.setEmpiD(result[i][0]);
				   emp.setInactive_form(result[i][1]);
				   emp.setAdded_date(result[i][2]);
				   emp.setAdded_user(result[i][3]);
				   emp.setEfectiveDate(result[i][4]);
				   emp.setInactiveUser(result[i][5]);
				   emp.setActive(result[i][6]);
		    	   emp.setAddDeduct(result[i][7]);
		    	   emp.setEmpCategory(result[i][8]);
		    	   emp.setEmpFname(result[i][9]);
		    	   emp.setEmpLname(result[i][10]);
		    	   emp.setAddDeductType(result[i][11]);
		    	   emp.setAddDedStatus(result[i][12]);
		    	   emp.setaDsvalue(result[i][13]);
		    	  
		    	   list.add(emp);
		    	  
			 }
			 
			 m.addAttribute("salarydetailbasedoncatgoryID", list);
			 
			return "printSalaryDetailsReport3";

		 }
		 
		 //get all salary details to report based on employee type
		 @GetMapping("/submitEmpSalaryDetails4")
		 public String printEmpSalaryDetail4Report(@RequestParam("tid") String tid, @RequestParam("deductTypeCode")String deductTypeCode,Model m)
		 {
			 String[][] result = employeeSalaryService.loadSalarydetailsbasedontypeid(tid ,deductTypeCode);
			 List<SalaryDetailsReportBean3> list = new ArrayList<>();
			 
			 for(int i=0; i< result.length;i++ )
			 {
				 SalaryDetailsReportBean3 emp = new SalaryDetailsReportBean3();
				   emp.setEmpiD(result[i][0]);
				   emp.setInactive_form(result[i][1]);
				   emp.setAdded_date(result[i][2]);
				   emp.setAdded_user(result[i][3]);
				   emp.setEfectiveDate(result[i][4]);
				   emp.setInactiveUser(result[i][5]);
				   emp.setActive(result[i][6]);
		    	   emp.setAddDeduct(result[i][7]);
		    	   emp.setEmpType(result[i][8]);
		    	   emp.setEmpFname(result[i][9]);
		    	   emp.setEmpLname(result[i][10]);
		    	   emp.setAddDeductType(result[i][11]);
		    	   emp.setAddDedStatus(result[i][12]);
		    	   emp.setaDsvalue(result[i][13]);
		    	  
		    	  
		    	   list.add(emp);
		    	  
			 }
			 
			 m.addAttribute("salarydetailbasedontid", list);
			 
			return "printSalaryDetailsReport4";

		 }
		 // get all add deduct type
		 
		//load all departments
			@ModelAttribute("payAddDeductTypes")
			public List<PayAddDeductTypes> getAlladdDeduct() {
				return payAddDeductTypeService.getAllDetails();
			}
		 
		 
		 
		 //get all salary details based on add deduct type
		 @GetMapping("/submitEmpSalaryDetails5")
		 public String printEmpSalaryDetail5Report(@RequestParam("deductTypeCode") String deductTypeCode,Model m)
		 {
			 String[][] result = employeeSalaryService.loadSalarydetailsbasedondeductTypeCode(deductTypeCode);
			 List<SalaryDetailsReportBean4> list = new ArrayList<>();
			 
			 for(int i=0; i< result.length;i++ )
			 {
				 SalaryDetailsReportBean4 emp = new SalaryDetailsReportBean4();
				   emp.setEmpiD(result[i][0]);
				   emp.setInactive_form(result[i][1]);
				   emp.setAdded_date(result[i][2]);
				   emp.setAdded_user(result[i][3]);
				   emp.setEfectiveDate(result[i][4]);
				   emp.setInactiveUser(result[i][5]);
				   emp.setActive(result[i][6]);
		    	   emp.setAddDeduct(result[i][7]);
		    	   emp.setEmptype(result[i][8]);
		    	   emp.setEmpCategory(result[i][9]);
		    	   emp.setEmpDep(result[i][10]);
		    	   emp.setEmpFname(result[i][11]);
		    	   emp.setEmpLname(result[i][12]);
		    	   emp.setAddDeductType(result[i][13]);
		    	   emp.setAddDedStatus(result[i][14]);
		    	   emp.setaDsvalue(result[i][15]);
		    	   list.add(emp);
		    	  
			 }
			 
			 m.addAttribute("salarydetailbasedondeductTypeCode", list);
			 
			return "printSalaryDetailsReport5";

		 }
		 
		 //load all employee
		 @GetMapping("/submitEmpSalaryDetails")
		 public String printEmpSalaryDetailReport(Model m)
		 {
			 String[][] result = employeeSalaryService.loadSalarydetails();
			 List<SalaryDetailsReportBean5> list = new ArrayList<>();
			 
			 for(int i=0; i< result.length;i++ )
			 {
				 SalaryDetailsReportBean5 emp = new SalaryDetailsReportBean5();
				   emp.setEmpiD(result[i][0]);
				   emp.setInactive_form(result[i][1]);
				   emp.setAdded_date(result[i][2]);
				   emp.setAdded_user(result[i][3]);
				   emp.setEfectiveDate(result[i][4]);
				   emp.setInactiveUser(result[i][5]);
				   emp.setActive(result[i][6]);
		    	   emp.setAddDeduct(result[i][7]);
		    	   emp.setEmpFname(result[i][8]);
		    	   emp.setEmpLname(result[i][9]);
		    	   emp.setAddDeductType(result[i][10]);
		    	   emp.setAddDedStatus(result[i][11]);
		    	   emp.setaDsvalue(result[i][12]);
		    	   list.add(emp);
		    	  
			 }
			 
			 m.addAttribute("salarydetails", list);
			 
			return "printSalaryDetailsReport6";

		 }
		 
		 
		 //get all active and inactive employees based on department
		 @GetMapping("/submitEmpSalaryDetails1ActiveAndInactive")
		 public String printEmpSalaryDetails1ReportActiveAndInactive(@RequestParam("depID") String depID ,@RequestParam("deductTypeCode")String deductTypeCode,Model m)
		 {
			 String[][] result = employeeSalaryService.loadSalarydetailsbasedondepIDActiveandInactive(depID, deductTypeCode);
			 
			 List<SalaryDetailsReportBean> list = new ArrayList<>();
			 
			 for(int i=0; i< result.length;i++ )
			 {
				 SalaryDetailsReportBean emp = new SalaryDetailsReportBean();
				   emp.setEmpiD(result[i][0]);
				   emp.setInactive_form(result[i][1]);
				   emp.setAdded_date(result[i][2]);
				   emp.setAdded_user(result[i][3]);
				   emp.setEfectiveDate(result[i][4]);
				   emp.setInactiveUser(result[i][5]);
				   emp.setActive(result[i][6]);
		    	   emp.setAddDeduct(result[i][7]);
		    	   emp.setEmpDep(result[i][8]);
		    	   emp.setEmpFname(result[i][9]);
		    	   emp.setEmpLname(result[i][10]);
		    	   emp.setAddDeductType(result[i][11]);
		    	   emp.setAddDedStatus(result[i][12]);
		    	   emp.setaDsvalue(result[i][13]);
		    	  
		    	   list.add(emp);
		    	  
			 }
			 
			 m.addAttribute("salarydetailbasedonDep", list);
			 
			return "printSalaryDetailsReport1";

		 }
		 //load active and inactive report
		 @GetMapping("/submitEmpSalaryDetails2locarion")
		 public String printEmpSalaryDetail2Reportlocation(@RequestParam("loid") String loid,@RequestParam("deductTypeCode") String deductTypeCode,Model m)
		 {
			 String[][] result = employeeSalaryService.loadSalarydetailsbasedonloidall(loid, deductTypeCode);
			 
			 List<SalaryDetailsReportBean1> list = new ArrayList<>();
			 
			 for(int i=0; i< result.length;i++ )
			 {
				 SalaryDetailsReportBean1 emp = new SalaryDetailsReportBean1();
				   emp.setEmpiD(result[i][0]);
				   emp.setInactive_form(result[i][1]);
				   emp.setAdded_date(result[i][2]);
				   emp.setAdded_user(result[i][3]);
				   emp.setEfectiveDate(result[i][4]);
				   emp.setInactiveUser(result[i][5]);
				   emp.setActive(result[i][6]);
		    	   emp.setAddDeduct(result[i][7]);
		    	   emp.setEmplocation(result[i][8]);
		    	   emp.setEmpFname(result[i][9]);
		    	   emp.setEmpLname(result[i][10]);
		    	   emp.setAddDeductType(result[i][11]);
		    	   emp.setAddDedStatus(result[i][12]);
		    	   emp.setaDsvalue(result[i][13]);
		    	  
		    	   list.add(emp);
		    	  
			 }
			 
			 m.addAttribute("salarydetailbasedonloid", list);
			 
			return "printSalaryDetailsReport2";

		 }
		 
		 
		 //get all employee salary details data based on category id
		 
		 @GetMapping("/submitEmpSalaryDetails3all")
		 public String printEmpSalaryDetail3Reportall(@RequestParam("catgoryID") String catgoryID,@RequestParam("deductTypeCode") String deductTypeCode,Model m)
		 {
			 String[][] result = employeeSalaryService.loadSalarydetailsbasedonallcategoryid(catgoryID , deductTypeCode);
			 List<SalaryDetailsReportBean2> list = new ArrayList<>();
			 
			 for(int i=0; i< result.length;i++ )
			 {
				 SalaryDetailsReportBean2 emp = new SalaryDetailsReportBean2();
				   emp.setEmpiD(result[i][0]);
				   emp.setInactive_form(result[i][1]);
				   emp.setAdded_date(result[i][2]);
				   emp.setAdded_user(result[i][3]);
				   emp.setEfectiveDate(result[i][4]);
				   emp.setInactiveUser(result[i][5]);
				   emp.setActive(result[i][6]);
		    	   emp.setAddDeduct(result[i][7]);
		    	   emp.setEmpCategory(result[i][8]);
		    	   emp.setEmpFname(result[i][9]);
		    	   emp.setEmpLname(result[i][10]);
		    	   emp.setAddDeductType(result[i][11]);
		    	   emp.setAddDedStatus(result[i][12]);
		    	   emp.setaDsvalue(result[i][13]);
		    	  
		    	   list.add(emp);
		    	  
			 }
			 
			 m.addAttribute("salarydetailbasedoncatgoryID", list);
			 
			return "printSalaryDetailsReport3";

		 }
		 
		//get all salary details to report based on employee type
		 @GetMapping("/submitEmpSalaryDetails4all")
		 public String printEmpSalaryDetail4Reportall(@RequestParam("tid") String tid, @RequestParam("deductTypeCode")String deductTypeCode,Model m)
		 {
			 String[][] result = employeeSalaryService.loadallSalarydetailsbasedontypeid(tid ,deductTypeCode);
			 List<SalaryDetailsReportBean3> list = new ArrayList<>();
			 
			 for(int i=0; i< result.length;i++ )
			 {
				 SalaryDetailsReportBean3 emp = new SalaryDetailsReportBean3();
				   emp.setEmpiD(result[i][0]);
				   emp.setInactive_form(result[i][1]);
				   emp.setAdded_date(result[i][2]);
				   emp.setAdded_user(result[i][3]);
				   emp.setEfectiveDate(result[i][4]);
				   emp.setInactiveUser(result[i][5]);
				   emp.setActive(result[i][6]);
		    	   emp.setAddDeduct(result[i][7]);
		    	   emp.setEmpType(result[i][8]);
		    	   emp.setEmpFname(result[i][9]);
		    	   emp.setEmpLname(result[i][10]);
		    	   emp.setAddDeductType(result[i][11]);
		    	   emp.setAddDedStatus(result[i][12]);
		    	   emp.setaDsvalue(result[i][13]);
		    	  
		    	   list.add(emp);
		    	  
			 }
			 
			 m.addAttribute("salarydetailbasedontid", list);
			 
			return "printSalaryDetailsReport4";

		 }
		 
}
