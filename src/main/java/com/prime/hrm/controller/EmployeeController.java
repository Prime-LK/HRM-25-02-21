package com.prime.hrm.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.prime.hrm.entity.Bank;
import com.prime.hrm.entity.BankMaster;
import com.prime.hrm.entity.CompanyMaster;
import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.DesignationMaster;
import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeCategory;
import com.prime.hrm.entity.EmployeeDetails;
import com.prime.hrm.entity.EmployeeDetailsPK;
import com.prime.hrm.entity.EmployeeType;
import com.prime.hrm.entity.JobProfileMaster;
import com.prime.hrm.entity.LocationMaster;
import com.prime.hrm.entity.NationalityMaster;
import com.prime.hrm.entity.ReligionMaster;
import com.prime.hrm.entity.SalaryGrade;
import com.prime.hrm.entity.SalaryRange;
import com.prime.hrm.service.BankDetailsService;
import com.prime.hrm.service.CompanyService;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeService;
import com.prime.hrm.service.JobService;
import com.prime.hrm.service.LocationService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@Autowired
	private JobService jobService;

	@Autowired
	private BankDetailsService bankDetailsService;

	@Autowired
	private DepartmentService depService;

	@Autowired
	private LocationService locService;

	@Autowired
	private CompanyService comService;



	// get register page
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String createrNewUser(Map<String, Object> model) {
		model.put("saveRegister", new Employee());
		Employee emp = new Employee();
		model.put("maxEmpID", emp);
		return "register";
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public String saveEmployee(@Valid @ModelAttribute("saveRegister") Employee e, HttpSession session,
			HttpServletRequest request, BindingResult br) throws IOException {
		if (br.hasErrors()) {
			return "register";
		} else {
			empService.saveEmp(e);
			session = request.getSession();
			session.setAttribute("eid", e.getEmpID());
			session.setAttribute("ename", e.getName());
			session.setAttribute("eImg", e.getProfileImgView());
			session.setAttribute("lastName", e.getLastname());
			session.setAttribute("addLine01", e.getAddress());
			session.setAttribute("addLine02", e.getCity());
			return "redirect:/register";
		}
	}

	@ModelAttribute("allComMas")
	public List<CompanyMaster> gettAllDetails() {
		return comService.getAllComDetails();
	}

	@ModelAttribute("salaryGrade")
	public List<SalaryGrade> getAllSGrade() {
		return jobService.getlistOfSalaryGrade();
	}

	@ModelAttribute("empCategories")
	public List<EmployeeCategory> getAllCategories() {
		return empService.getAllCategories();
	}

	@ModelAttribute("salaryRange")
	public List<SalaryRange> getAllSalaryRange() {
		return empService.getAllRanges();
	}

	@ModelAttribute("nationalities")
	public List<NationalityMaster> getAllNationalities() {
		return empService.getAllNationalities();
	}

	@ModelAttribute("religion")
	public List<ReligionMaster> getAllReligions() {
		return empService.getAllRiligions();
	}

	@ModelAttribute("designations")
	public List<DesignationMaster> getAllDesignations() {
		return empService.getAllDesignations();
	}

	@ModelAttribute("jobProfile")
	public List<JobProfileMaster> getAllProfiles() {
		return empService.getAllProfiles();
	}

	@ModelAttribute("emps")
	public List<Employee> getAllEmps() {
		return empService.getAllEmp();
	}

	@ModelAttribute("allEmpTypes")
	public List<EmployeeType> getAllTypes() {
		return empService.getAllTypes();
	}

	// load bank
	@ModelAttribute("bankmastertable")
	public List<BankMaster> showBankmaster() {
		return bankDetailsService.getAllBankdata();
	}

	// load bank branch
	@ModelAttribute("bankBranch")
	public List<Bank> showBankBrsnch() {
		return bankDetailsService.getAllSavedBank();
	}

	@ModelAttribute("locations")
	public List<LocationMaster> getAlllocs() {
		return locService.getAllLocations();
	}

	@RequestMapping(value = "/updateEmp", method = RequestMethod.GET)
	public ModelAndView updateEmp(@RequestParam String id, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("register");
		Employee emp = null;
		try {
			emp = empService.getEmp(id);
			session = request.getSession();
			session.setAttribute("eid", emp.getEmpID());
			session.setAttribute("ename", emp.getName());
			session.setAttribute("eImg", emp.getProfileImgView());
			session.setAttribute("lastName", emp.getLastname());
			session.setAttribute("addLine01", emp.getAddress());
			session.setAttribute("addLine02", emp.getCity());
			mav.addObject("saveRegister", emp);
		} catch (Exception e) {
			System.out.println("Employee Details Not Found");
		}
		try {
			String empImg = emp.getProfileImgView();
			mav.addObject("EImg", empImg);
		} catch (Exception e) {
			System.out.println("Employee Image Not Found");
		}
		return mav;
	}

	@RequestMapping(value = "/updateEmpUsingName", method = RequestMethod.GET)
	public ModelAndView updateEmpUsingName(@RequestParam String name, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("register");
		Employee emp = null;
		try {
			emp = empService.updateDetailsUsingEmpName(name);
			session = request.getSession();
			session.setAttribute("eid", emp.getEmpID());
			session.setAttribute("ename", emp.getName());
			session.setAttribute("eImg", emp.getProfileImgView());
			session.setAttribute("lastName", emp.getLastname());
			session.setAttribute("addLine01", emp.getAddress());
			session.setAttribute("addLine02", emp.getCity());
			mav.addObject("saveRegister", emp);
		} catch (Exception e) {
			System.out.println("Employee Details Not Found");
		}
		try {
			String empImg = emp.getProfileImgView();
			mav.addObject("EImg", empImg);
		} catch (Exception e) {
			System.out.println("Employee Image Not Found");
		}
		return mav;
	}
	
	// get loging user image according to userID to loging jsp
	@RequestMapping(value = "/logingimage", method = RequestMethod.GET)
	public @ResponseBody String searchUserImage(@RequestParam String name) {
		List<Employee> empImage = empService.getlogingImg(name);
		return empImage.get(0).getProfileImgView();
	}

	// delete session attribute using in emp edit
	@RequestMapping("/logout")
	public String logout(Employee emp, WebRequest request, HttpSession session, HttpServletRequest r) {
		// ModelAndView mav=new ModelAndView("login");
		session.setAttribute("eid", "empty");
		session.removeAttribute("eid");
		session.setAttribute("ename", "empty");
		session.removeAttribute("ename");
		session.setAttribute("eImg", "empty");
		session.removeAttribute("eImg");
		return "redirect:/";
	}

	// employee details
	// operation----------------------------------------------------

	// get
	@RequestMapping(value = "/employeeDetails", method = RequestMethod.GET)
	public String getEmpDetailsPage(Map<String, Object> map) {
		map.put("EmpDetails", new EmployeeDetails());
		EmployeeDetailsPK empDe = new EmployeeDetailsPK();
		empDe.setDetailID("00000".substring(empService.getID().length()) + empService.getID());
		map.put("id", empDe);
		return "employeeDetails";
	}

	// save
	@RequestMapping(value = "/saveEmpDetails", method = RequestMethod.POST)
	public String saveValues(@ModelAttribute("EmpDetails") EmployeeDetails empDe) {
		try {
			empService.saveEmplDetails(empDe);
			System.out.println("Details Saved Successfully");
			return "redirect:/employeeDetails";
		} catch (Exception e) {
			System.out.println(e);
		}
		return "employeeDetails";
	}

	@GetMapping("/getRelatedTableData")
	@ResponseBody
	public EmployeeDetails getRelatedEmpToTbl(@RequestParam("empID") String empID) {
		EmployeeDetails data = empService.updateDetails(empID);
		return data;
	}
	
	@GetMapping("/updateDetails")
	public ModelAndView updateDetails(@RequestParam("empID") String empID) {
		ModelAndView mav = new ModelAndView("employeeDetails");
		EmployeeDetails detail = null;
		try {
			detail = empService.updateDetails(empID);
			mav.addObject("EmpDetails", detail);
		} catch (Exception e) {
			System.out.println("Employee ID Not Found");
		}
		return mav;
	}

	//search
	@GetMapping("/getSearchData")
	@ResponseBody
	public List<Employee> getSearchDetails() {
		List<Employee> sDetails = empService.getSearchDetails();
		return sDetails;
	}
	
	@ModelAttribute("dAll")
	public List<DepartmentMaster> getAllDeps() {
		return depService.getAllDep();
	}

	// get branch according to bank

	@RequestMapping(value = "/getbranchcombo", method = RequestMethod.GET)
	public @ResponseBody List<Bank> searcheqmodelcombo(@RequestParam("bank_Code") String bank_Code) {
		List<Bank> listbranch = bankDetailsService.getbranchBybank(bank_Code);
		return listbranch;

	}

}
