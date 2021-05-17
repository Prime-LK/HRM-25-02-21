package com.navitsa.hrm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.DesignationMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeCategory;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.EmployeeType;
import com.navitsa.hrm.entity.ReligionMaster;
import com.navitsa.hrm.report.EmployeeContactListingReportBeen;
import com.navitsa.hrm.report.EmployeeContactSummaryReportBean;
import com.navitsa.hrm.report.EmployeeListingReportBeen;
import com.navitsa.hrm.service.CompanyService;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.utils.ReportViewe;

@Controller("contactReportController")
public class ContactReportController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService dep;
	
	@Autowired
	CompanyService companyService;

	@RequestMapping("/contactReport")
	public String employee(Map<String, Object> model) {
		List<DepartmentMaster> listEmployee = dep.getAllDep();
		model.put("listEmployee", listEmployee);
		return "hrm/contactReport";
	}
	//contact report related department
	@PostMapping("/contactRepo")
	public ModelAndView handleForexRequest(@RequestParam("depID") String depID, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = "Employee Contact Report: " + depID;
		String[][] result = employeeService.getEmployeefilteredcontactData(depID);
		List<EmployeeContactSummaryReportBean> employeeContactSummaryReportBeanArray = new ArrayList<>();
		for (int i = 0; i < result.length; i++) {
			EmployeeContactSummaryReportBean empC = new EmployeeContactSummaryReportBean();
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
//			System.out.println(empC.getFname() + "-------" + empC.getContact_h());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("depID", depID);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("EmployeeContactSummaryReport.jasper", fileName, employeeContactSummaryReportBeanArray, params, response);
		ModelAndView mav = new ModelAndView("hrm/contactReportRelatedDepartmentView");
		mav.addObject("pdfViewCRRD", report);
		return mav;
	}
	
	
	@ModelAttribute("departmentEmpLisRpt")
	public List<DepartmentMaster> getDepartment(HttpSession session){
		String companyId=session.getAttribute("company.comID")+"";
		List<DepartmentMaster> listDept = dep.getDepartmentsByCompany(companyId);
		return listDept;
	}
	
	@ModelAttribute("employeeTypeEmpLisRpt")
	public List<EmployeeType> getEmployeeType(HttpSession session){
		String companyId=session.getAttribute("company.comID")+"";

		List<EmployeeType> listemptyp = employeeService.getAllTypesByCompanny(companyId);
		return listemptyp;
	}
	
	@ModelAttribute("employeeCategoryLisRpt")
	public List<EmployeeCategory> employeeCategoryLisRpt(HttpSession session){
		String companyId=session.getAttribute("company.comID")+"";
		List<EmployeeCategory> empcatList=employeeService.getAllCategoriesBycompanyID(companyId);
		return empcatList;
	}
	
	@ModelAttribute("employeeReligionLisRpt")
	public List<ReligionMaster> employeeReligionLisRpt(HttpSession session){
		String companyId=session.getAttribute("company.comID")+"";
		List<ReligionMaster> eeligionMastertList=employeeService.getAllReligionBycompanyID(companyId);
		return eeligionMastertList;
	}
	
	@ModelAttribute("designationMasterEmpLisRpt")
	public List<DesignationMaster> getDesignationMaster(HttpSession session){
		String companyId=session.getAttribute("company.comID")+"";

		List<DesignationMaster> listdec = employeeService.getAllDesignationsByCompany(companyId);
		return listdec;
	}
	@RequestMapping("/employeeContactListing")
	public String employeeContactListing(Map<String, Object> model) {

		return "hrm/employeeContactListingRpt";
	}
	
	@RequestMapping(value="/getEmployeeListrpt", method=RequestMethod.GET)
	public @ResponseBody List<Employee>  getEmployeeListrpt(@RequestParam String dep,@RequestParam String dis,@RequestParam String emptyp,HttpSession session) {
		String companyId=session.getAttribute("company.comID")+"";
		List<Employee> listallemploy = employeeService.getEmployeeListrpt(dep,dis,emptyp,"%","%","%","%",companyId);
		return listallemploy;
	}
	@RequestMapping(value="/employeeContactListingPreview", method=RequestMethod.GET)
	public ModelAndView employeeContactListingPreview(@RequestParam String dept,@RequestParam String desi,@RequestParam String emptype,@RequestParam String empid, HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
	
		
		String deparment="";
		String empt="";
		String design="";
		String employh="";
			
		String companyId=session.getAttribute("company.comID")+"";
		CompanyMaster companyMaster=companyService.findbyCompanyid(companyId);
		
		
		
		String[][] result = employeeService.getEmployeeListrptPrivew(dept,desi,emptype,empid,companyId);

		List<EmployeeContactListingReportBeen> employeeContactListingReportBeenArray = new ArrayList<EmployeeContactListingReportBeen>();
		for (int i = 0; i < result.length; i++) {
			EmployeeContactListingReportBeen empconLisbe = new EmployeeContactListingReportBeen();
			empconLisbe.setEmpno(result[i][0]);
			empconLisbe.setName(result[i][1]);
			empconLisbe.setAddress(result[i][2]);
			empconLisbe.setTel(result[i][3]);
			empconLisbe.setPhone(result[i][4]);
			empconLisbe.setEmail(result[i][5]);
			empconLisbe.setDepar(result[i][6]);
			empconLisbe.setEmpty(result[i][7]);
			empconLisbe.setDes(result[i][8]);
			//	System.out.println(result[i][3]+" - "+result[i][0]);
			employeeContactListingReportBeenArray.add(empconLisbe);
			
			
			
			 deparment=result[i][6];
			 empt=result[i][7];
			 design=result[i][8];
			 employh=result[i][1];
			
//			System.out.println(empC.getFname() + "-------" + empC.getContact_h());
		}
//		dept,desi,emptype,empid
		
		
		if(dept.equals("%")) {
			 deparment="All Department";
		}
		if(desi.equals("%")) {
			design="All Designations";
		}
		if(emptype.equals("%")) {
			 empt="All Types";
		}
	
		if(empid.equals("%")) {
			employh="All Employees";
		}
		
		Map<String, Object> params = new HashMap<>();
		params.put("companny", companyMaster.getComName());
		params.put("address", companyMaster.getAddress());
		params.put("deparment", deparment);
		params.put("emptype", empt);
		params.put("design", design);
		params.put("employee", employh);
		
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("EmployeeContactListingReport.jasper", "", employeeContactListingReportBeenArray, params, response);
		ModelAndView mav = new ModelAndView("hrm/employeeContactListingRpt");
		mav.addObject("pdfViewEq", report);
		return mav;
	}
	@RequestMapping("/employeeListingRpt")
	public String employeeListingRpt(Map<String, Object> model) {

		return "hrm/employeeListingRpt";
	}
	@RequestMapping(value="/getEmployeeListReport", method=RequestMethod.GET)
	public @ResponseBody List<Employee>  getEmployeeListReport(@RequestParam String dep,@RequestParam String dis,@RequestParam String emptyp,@RequestParam String empcat,@RequestParam String religion,@RequestParam String civista,HttpSession session){
		String companyId=session.getAttribute("company.comID")+"";
		List<Employee> listallemploy = employeeService.getEmployeeListrpt(dep,dis,emptyp,"%",companyId,empcat,religion,civista);
		return listallemploy;
	}
	
	 
		@RequestMapping(value="/employeeListingPreview", method=RequestMethod.GET)
		public ModelAndView employeeListingPreview(@RequestParam String dept,@RequestParam String desi,@RequestParam String emptype,@RequestParam String empid,@RequestParam String empcat,@RequestParam String religion,@RequestParam String civista, HttpServletRequest request,
				HttpServletResponse response,HttpSession session) throws Exception {
		
			
			String deparment="";
			String empt="";
			String design="";
			String employh="";
			
			String empcatP="";
			String religionP="";
			String civistaP="";
				
			String companyId=session.getAttribute("company.comID")+"";
			CompanyMaster companyMaster=companyService.findbyCompanyid(companyId);
			
			
			
			String[][] result = employeeService.getEmployeeListrptPrivewbyreligion(dept,desi,emptype,empid,empcat,religion,civista,companyId);

			List<EmployeeListingReportBeen> employeeListingReportBeenArray = new ArrayList<EmployeeListingReportBeen>();
			for (int i = 0; i < result.length; i++) {
				EmployeeListingReportBeen empconLisbe = new EmployeeListingReportBeen();
				empconLisbe.setEmpno(result[i][0]);
				empconLisbe.setName(result[i][1]);
			//	empconLisbe.setAddress(result[i][2]);
				empconLisbe.setTel(result[i][3]);
				empconLisbe.setPhone(result[i][4]);
				empconLisbe.setEmail(result[i][5]);
				empconLisbe.setDepar(result[i][6]);
				empconLisbe.setEmpty(result[i][7]);
				empconLisbe.setDes(result[i][8]);
				
				empconLisbe.setEmpcat(result[i][9]);
				empconLisbe.setReligion(result[i][10]);
				empconLisbe.setCivilstatus(result[i][11]);
				
				empconLisbe.setNic(result[i][12]);
				empconLisbe.setBasicsal(result[i][13]+".00");
				empconLisbe.setDob(result[i][14]);
				empconLisbe.setJoindate(result[i][15]);
				//	System.out.println(result[i][3]+" - "+result[i][0]);
				employeeListingReportBeenArray.add(empconLisbe);

				 deparment=result[i][6];
				 empt=result[i][7];
				 design=result[i][8];
				 employh=result[i][1];
				 
				 	empcatP=result[i][9];
				 	religionP=result[i][10];
					civistaP=result[i][11];
				 
				
//				System.out.println(empC.getFname() + "-------" + empC.getContact_h());
			}
//			dept,desi,emptype,empid
			
			
			if(dept.equals("%")) {
				 deparment="All Department";
			}
			if(desi.equals("%")) {
				design="All Designations";
			}
			if(emptype.equals("%")) {
				 empt="All Types";
			}
		
			if(empid.equals("%")) {
				employh="All Employees";
			}
						
			if(empcat.equals("%")) {
				empcatP="All Employee Category";
			}			
			if(religion.equals("%")) {
				religionP="All Religion";
			}
			if(civista.equals("%")) {
				civistaP="All Civista";
			}
			
			
			
			
			
			Map<String, Object> params = new HashMap<>();
			params.put("companny", companyMaster.getComName());
			params.put("address", companyMaster.getAddress());
			params.put("deparment", deparment);
			params.put("emptype", empt);
			params.put("design", design);
			params.put("employee", employh);
			
			params.put("empcatP", empcatP);
			params.put("religionP", religionP);
			params.put("civistaP", civistaP);
			
			
			ReportViewe review = new ReportViewe();
			String report = review.pdfReportViewInlineSystemOpen("EmployeeListingReport.jasper", "", employeeListingReportBeenArray, params, response);
			ModelAndView mav = new ModelAndView("hrm/employeeListingRpt");
			mav.addObject("pdfViewEq", report);
			return mav;
		} 
	 
	 
	 
	 
	 
	 
}
