package com.navitsa.hrm.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.PayAddDeductTypes;
import com.navitsa.hrm.entity.PayPeriods;
import com.navitsa.hrm.report.EmployeeReportBean;
import com.navitsa.hrm.report.EmployeeSummaryReportBeen;
import com.navitsa.hrm.service.CompanyService;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.PayAddDeductTypeService;
import com.navitsa.hrm.service.PayService;
import com.navitsa.hrm.utils.ReportViewe;

@Controller("EmployeeReportController")
public class EmployeeReportController {

	@Autowired
	private EmployeeService empService;
	
	@Autowired	
	private DepartmentService depservive;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private PayAddDeductTypeService addDedService;
	
	@Autowired
	PayService payService;
	
	@GetMapping("employeeReport")
	public String loadPage() {
		return "hrm/employeeReport";
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
	 @PostMapping("/submitEmpReport")
	 public ModelAndView print1(@RequestParam String empID,HttpServletRequest request, 
			 HttpServletResponse response) throws Exception {
		 String fileName = "Employee Report: " + empID;
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
//	    	   System.out.println(ed.getEmpBank() + " " + ed.getEmpAccount()+ " " + ed.getCategory());
		 }
		 	Map<String, Object> params = new HashMap<>();
		 	params.put("empID", empID);
		 	ReportViewe review=new ReportViewe();
	        String report = review.pdfReportViewInlineSystemOpen("employeeReport.jasper", fileName, list, params, response);
	        ModelAndView mav = new ModelAndView("hrm/employeeReportView");
	        mav.addObject("pdfViewEq",report);
	        return mav;
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
	    
	 
	 
	 @GetMapping("/employeeSummaryReport")
	 public ModelAndView employeeSummaryReport(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		
		String companyId=session.getAttribute("company.comID")+"";	
		String[][] result=empService.getEmployeeSummaryReport(companyId);
				
		 List<EmployeeSummaryReportBeen> listemp = new ArrayList<>(); 
		 
		 for(int i=0; i<result.length;i++)
		 {
			 EmployeeSummaryReportBeen employeeSummaryReport=new EmployeeSummaryReportBeen();
			employeeSummaryReport.setEpfno(result[i][0]);
			employeeSummaryReport.setName(result[i][1]);
			employeeSummaryReport.setNic(result[i][2]);
			employeeSummaryReport.setBankname(result[i][3]);
			employeeSummaryReport.setAccount(result[i][4]);
			employeeSummaryReport.setBasicsal(Double.parseDouble(result[i][5]));
			employeeSummaryReport.setType(result[i][6]);
			employeeSummaryReport.setAmount(Double.parseDouble(result[i][7]));
 
			 
			listemp.add(employeeSummaryReport);

		 }
		 	//Map<String, Object> params = new HashMap<>();
		 	//params.put("empID", empID);companny address
			CompanyMaster companyMaster = companyService.findbyCompanyid(companyId);
	      	Map<String,Object> params = new HashMap<>();

	    	params.put("companny",companyMaster.getComName());
	      	params.put("address",companyMaster.getConNo());
		 	
		 	
		 	ReportViewe review=new ReportViewe();
	        String report = review.pdfReportViewInlineSystemOpen("employeeSummaryReport.jasper", "", listemp, params, response);
	        ModelAndView mav = new ModelAndView("hrm/employeeReportView");
	        mav.addObject("pdfViewEq",report);
	        return mav;
	 }
	 
	 @GetMapping("/employeeMonthlyAllocate")
	 public String employeeAllocateMonthlyAllRpt(HttpServletRequest request, HttpServletResponse response,HttpSession session)  {
	
	   
	        return "hrm/employeeAllocateMonthlyAllRpt";
	 }
		@ModelAttribute("addDedTypeRpts")
		public List<PayAddDeductTypes> getAddDed(HttpSession session) {
			String comID = (String) session.getAttribute("company.comID");
			return addDedService.getAllouncetypeMonthly(comID);
		}
		@ModelAttribute("payPerioadrpt")
		public List<PayPeriods> getPayPeriods(HttpSession session){
			String companyId=session.getAttribute("company.comID")+"";
			List<PayPeriods> payPeriods = payService.getPayPeriodsBycompanyid(companyId);
			return payPeriods;
		}
	 @GetMapping("/employeeAllocateMonthlyAllowances")
	 public ModelAndView employeeAllocateMonthlyAllowances(@RequestParam("paycode") String paycode,@RequestParam("paytype") String paytype,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
		
		 PayPeriods payPeriods=payService.loadPayPeriodsbypayPeriodID(paycode);
		 
		 
		 
		String companyId=session.getAttribute("company.comID")+"";	
		String[][] result=empService.employeeAllocateMonthlyAllowances( companyId,  paytype,paycode);
				
		 List<EmployeeSummaryReportBeen> listemp = new ArrayList<>(); 
	 
		 for(int i=0; i<result.length;i++)
		 {
			 EmployeeSummaryReportBeen employeeSummaryReport=new EmployeeSummaryReportBeen();
			employeeSummaryReport.setEpfno(result[i][0]);
			employeeSummaryReport.setName(result[i][1]);
			employeeSummaryReport.setNic(result[i][2]);
			employeeSummaryReport.setBankname(result[i][3]);
			employeeSummaryReport.setAccount(result[i][4]);
			employeeSummaryReport.setBasicsal(Double.parseDouble(result[i][5]));
			employeeSummaryReport.setType(result[i][6]);
			employeeSummaryReport.setAmount(Double.parseDouble(result[i][7]));
			employeeSummaryReport.setAddedstatus(result[i][8]);
			 
			listemp.add(employeeSummaryReport);

		 }
		 	//Map<String, Object> params = new HashMap<>();
		 	//params.put("empID", empID);companny address
			CompanyMaster companyMaster = companyService.findbyCompanyid(companyId);
	      	Map<String,Object> params = new HashMap<>();

	    	params.put("companny",companyMaster.getComName());
	      	params.put("address",companyMaster.getConNo());
	      	params.put("paydate",payPeriods.getPayDate());
		 	
		 	ReportViewe review=new ReportViewe();
	       String report = review.pdfReportViewInlineSystemOpen("employeeAllocateMonthlyAllowance.jasper", "", listemp, params, response);
	      //  review.pdfReportViewfileDownload("employeeAllocateMonthlyAllowance.jasper","", listemp, params,  response);
	        
	       ModelAndView mav = new ModelAndView("hrm/employeeAllocateMonthlyAllRpt");
	        mav.addObject("pdfViewEq",report);
	        return mav;
	 }
}
