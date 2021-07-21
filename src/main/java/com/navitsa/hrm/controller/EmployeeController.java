package com.navitsa.hrm.controller;

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

import com.navitsa.hrm.entity.Bank;
import com.navitsa.hrm.entity.BankMaster;
import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.entity.DesignationMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeCategory;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.EmployeeDetailsPK;
import com.navitsa.hrm.entity.EmployeeType;
import com.navitsa.hrm.entity.JobProfileMaster;
import com.navitsa.hrm.entity.LocationMaster;
import com.navitsa.hrm.entity.NationalityMaster;
import com.navitsa.hrm.entity.ReligionMaster;
import com.navitsa.hrm.entity.SalaryGrade;
import com.navitsa.hrm.entity.SalaryRange;
import com.navitsa.hrm.entity.ShiftMaster;
import com.navitsa.hrm.service.BankDetailsService;
import com.navitsa.hrm.service.CompanyService;
import com.navitsa.hrm.service.DepartmentService;
import com.navitsa.hrm.service.EmployeeLevelService;
import com.navitsa.hrm.service.EmployeeService;
import com.navitsa.hrm.service.EthnicService;
import com.navitsa.hrm.service.JobService;
import com.navitsa.hrm.service.LocationService;
import com.navitsa.hrm.service.ShiftMasterService;

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

	@Autowired
	private ShiftMasterService shiftMasterService;
	
	@Autowired
	private EthnicService ethService; 
	
	@Autowired
	private EmployeeLevelService employeeLevelService;

	// get register page
	@RequestMapping(value = "/Register", method = RequestMethod.GET)
	public String createrNewUser(Map<String, Object> model) {
		model.put("saveRegister", new Employee());
		Employee emp = new Employee();
		model.put("maxEmpID", emp);
		return "hrm/register";
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public String saveEmployee(@Valid @ModelAttribute("saveRegister") Employee e, HttpSession session,
			HttpServletRequest request, BindingResult br) throws IOException {
		if (br.hasErrors()) {
			return "hrm/register";
		} else {
			try {
				empService.saveEmp(e);
				session = request.getSession();
				session.setAttribute("eid", e.getEmpID());
				session.setAttribute("ename", e.getName());
				session.setAttribute("eImg", e.getProfileImgView());
				session.setAttribute("lastName", e.getLastname());
				session.setAttribute("addLine01", e.getAddress());
				session.setAttribute("addLine02", e.getCity());
				return "redirect:/Register";
			} catch (Exception er) {
				System.out.println(er);
			}
		}
		return "hrm/register";
	}

	@ModelAttribute("allComMas")
	public List<CompanyMaster> gettAllDetails(HttpSession session) {
		return comService.getAllComDetails();
	}

	@ModelAttribute("salaryGrade")
	public List<SalaryGrade> getAllSalaryGradeByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return jobService.getAllSalaryGradeByCompany(companyId);
	}

	@ModelAttribute("empCategories")
	public List<EmployeeCategory> getAllEmployeeCategoryByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return employeeLevelService.getAllEmployeeCategoryByCompany(companyId);

	}

	@ModelAttribute("salaryRange")
	public List<SalaryRange> getAllSalaryRangeByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return jobService.getAllSalaryRangeByCompany(companyId);
	}
	
	@GetMapping("/getAllSalaryRangeByGradeAndCompany")
	@ResponseBody
	public List<SalaryRange> getAllSalaryRangeByGradeAndCompany(@RequestParam("gradeId") String gradeId, HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return jobService.getAllSalaryRangeByGradeAndCompany(gradeId,companyId);
	}

	@ModelAttribute("nationalities")
	public List<NationalityMaster> getAllNationalities(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return ethService.getAllNationalityByCompany(companyId);
	}

	@ModelAttribute("religion")
	public List<ReligionMaster> getAllReligions(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return empService.getAllReligionBycompanyID(companyId);
	}

	@ModelAttribute("designations")
	public List<DesignationMaster> getAllDesignationMasterByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return jobService.getAllDesignationMasterByCompany(companyId);
	}

	@ModelAttribute("jobProfile")
	public List<JobProfileMaster> getAllJobProfileByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return jobService.getAllJobProfileByCompany(companyId);
	}

	@ModelAttribute("emps")
	public List<Employee> getAllEmps(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return empService.getEmployeesByCompany(companyId);
	}

	@ModelAttribute("allEmpTypes")
	public List<EmployeeType> getAllEmployeeTypeByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return employeeLevelService.getAllEmployeeTypeByCompany(companyId);
	}

	// load bank
	@ModelAttribute("bankmastertable")
	public List<BankMaster> getAllBankByCompany(HttpSession session) {
		//String companyId = session.getAttribute("company.comID").toString();
		return bankDetailsService.getAllBankdata();
	}

	// load bank branch
	@ModelAttribute("bankBranch")
	public List<Bank> showBankBrsnch(HttpSession session) {
		//String companyId = session.getAttribute("company.comID").toString();
		return bankDetailsService.getAllSavedBank();
	}

	@GetMapping("/getAllBankBranchByBank")
	@ResponseBody
	public List<Bank> getAllBankBranchByBankAndCompany(@RequestParam("bankId") String bankId, HttpSession session) {
		//String companyId = session.getAttribute("company.comID").toString();
		return bankDetailsService.getAllBankBranchByBank(bankId);
	}
	
	@ModelAttribute("locations")
	public List<LocationMaster> getAlllocs() {
		return locService.getAllLocations();
	}

	@RequestMapping(value = "/updateEmp", method = RequestMethod.GET)
	public ModelAndView updateEmp(@RequestParam String id, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("hrm/register");
		Employee emp = null;
		try {
			//emp = empService.getEmp(id);
			String companyId = session.getAttribute("company.comID").toString();
			EmployeeDetails ed = empService.findEmployeeByEpfNo(id, companyId);
			emp = ed.getDetailsPK().getEmpID();
			List<Bank> branchList = bankDetailsService.getAllBankBranchByBank(emp.getBankBranch_Code().getBankid().getBankid());
			session = request.getSession();
			session.setAttribute("eid", emp.getEmpID());
			session.setAttribute("ename", emp.getName());
			session.setAttribute("eImg", emp.getProfileImgView());
			session.setAttribute("lastName", emp.getLastname());
			session.setAttribute("addLine01", emp.getAddress());
			session.setAttribute("addLine02", emp.getCity());
			mav.addObject("branchListByBank", branchList);
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
		ModelAndView mav = new ModelAndView("hrm/register");
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
	@RequestMapping(value = "/EmployeeDetails", method = RequestMethod.GET)
	public String getEmpDetailsPage(Map<String, Object> map) {
		map.put("EmpDetails", new EmployeeDetails());
		EmployeeDetailsPK empDe = new EmployeeDetailsPK();
		empDe.setDetailID("00000".substring(empService.getID().length()) + empService.getID());
		map.put("id", empDe);
		return "hrm/employeeDetails";
	}

	// save
	@RequestMapping(value = "/saveEmpDetails", method = RequestMethod.POST)
	public String saveValues(@ModelAttribute("EmpDetails") EmployeeDetails empDe) {
		try {
			empService.saveEmplDetails(empDe);
			System.out.println("Details Saved Successfully");
			return "redirect:/EmployeeDetails";
		} catch (Exception e) {
			System.out.println(e);
		}
		return "hrm/employeeDetails";
	}

	@GetMapping("/getRelatedTableData")
	@ResponseBody
	public EmployeeDetails getRelatedEmpToTbl(@RequestParam("empID") String empID) {
		EmployeeDetails data = empService.updateDetails(empID);
		return data;
	}

	@GetMapping("/updateDetails")
	public ModelAndView updateDetails(@RequestParam("empID") String empID) {
		ModelAndView mav = new ModelAndView("hrm/employeeDetails");
		EmployeeDetails detail = null;
		try {
			detail = empService.updateDetails(empID);
			mav.addObject("EmpDetails", detail);
		} catch (Exception e) {
			System.out.println("Employee ID Not Found");
		}
		return mav;
	}

	// search
	@GetMapping("/getSearchData")
	@ResponseBody
	public List<Employee> getSearchDetails() {
		List<Employee> sDetails = empService.getSearchDetails();
		return sDetails;
	}

	@ModelAttribute("dAll")
	public List<DepartmentMaster> getAllDepartmentByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return depService.getAllDepartmentByCompany(companyId);
	}

	// get branch according to bank

	@RequestMapping(value = "/getbranchcombo", method = RequestMethod.GET)
	public @ResponseBody List<Bank> searcheqmodelcombo(@RequestParam("bank_Code") String bank_Code) {
		List<Bank> listbranch = bankDetailsService.getbranchBybank(bank_Code);
		return listbranch;

	}

	@ModelAttribute("shiftList")
	public List<ShiftMaster> getAllShifts(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return shiftMasterService.findAllShiftsByCompany(companyId);
	}
}
