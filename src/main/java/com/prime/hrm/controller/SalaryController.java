package com.prime.hrm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prime.hrm.entity.CompanyMaster;
import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeCategory;
import com.prime.hrm.entity.EmployeeDetails;
import com.prime.hrm.entity.EmployeeMonthSalaryDetails;
import com.prime.hrm.entity.EmployeeMonthSalaryDetailsPK;
import com.prime.hrm.entity.EmployeeSalaryDetail;
import com.prime.hrm.entity.EmployeeSalaryDetailPK;
import com.prime.hrm.entity.EmployeeSalaryMaster;
import com.prime.hrm.entity.EmployeeSalaryMasterPK;
import com.prime.hrm.entity.EmployeeType;
import com.prime.hrm.entity.LocationMaster;
import com.prime.hrm.entity.PayAddDeductTypes;
import com.prime.hrm.entity.PayCode;
import com.prime.hrm.entity.PayPeriods;
import com.prime.hrm.entity.Setting;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeSalaryService;
import com.prime.hrm.service.EmployeeService;
import com.prime.hrm.service.LocationService;
import com.prime.hrm.service.PayAddDeductTypeService;
import com.prime.hrm.service.PayService;

@Controller
public class SalaryController {

	@Autowired
	private EmployeeSalaryService employeeSalaryService;

	@Autowired
	private EmployeeService empService;

	@Autowired
	private DepartmentService depService;

	@Autowired
	private LocationService locService;

	@Autowired
	private PayAddDeductTypeService addDedService;

	@Autowired
	PayAddDeductTypeService payAddDeductTypeService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	LocationService locationService;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	PayService payService;

	// load employee salary master jsp
	@RequestMapping(value = "/employeeSalaryMaster", method = RequestMethod.GET)
	public String getemployeeSalaryMasterPage(Map<String, Object> map) {
		map.put("empSalaryMaster", new EmployeeSalaryMaster());
		EmployeeSalaryMasterPK sm = new EmployeeSalaryMasterPK();
		sm.setProcessID(
				"00000".substring(employeeSalaryService.getMaxID().length()) + employeeSalaryService.getMaxID());
		map.put("id", sm);
		return "employeeSalaryMaster";
	}

	// save employee salary master data
	@RequestMapping(value = "/saveEmpSalaryMaster", method = RequestMethod.POST)
	public String saveemployeeSalaryMaster(@ModelAttribute("empSalaryMaster") EmployeeSalaryMaster sm) {
		employeeSalaryService.saveEmpSalary(sm);
		return "redirect:/employeeSalaryMaster";
	}
	
	@ModelAttribute("getAllEmpSaMaster")
	public List<EmployeeSalaryMaster> getAll() {
		return employeeSalaryService.getAllEmpSa();
	}

	// load employee salary master data
	@RequestMapping(value = "/loadtable", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeSalaryMaster> loadEmployeeSalaryMasterData(@RequestParam("empID") String empID) {
		List<EmployeeSalaryMaster> sm = employeeSalaryService.loadGrid(empID);
		return sm;
	}

	// load employee salary master data to edit
	@RequestMapping(value = "/updateSalaryMaster", method = RequestMethod.GET)
	public @ResponseBody ModelAndView updateSalaryMasterData(@RequestParam("empID") String empID,
			@RequestParam("processID") String processID) {
		ModelAndView mav = new ModelAndView("employeeSalaryMaster");
		EmployeeSalaryMaster updateSalaryMaster = employeeSalaryService.updateRecord(empID, processID);
		mav.addObject("empSalaryMaster", updateSalaryMaster);
		return mav;
	}

	// emp month salary details-----------------------------------------

	// load base jsp
	@GetMapping("/getEmpMonthSalaryDetailsPage")
	public String loadPage(Map<String, Object> map) {
		map.put("formMonthSalary", new EmployeeMonthSalaryDetails());
		return "empMonthSalaryDetails";
	}

	@ModelAttribute("categories")
	public List<EmployeeCategory> getAllCategories() {
		return empService.getAllCategories();
	}

	@ModelAttribute("types")
	public List<EmployeeType> getAllTypes() {
		return empService.getAllTypes();
	}

	@ModelAttribute("departments")
	public List<DepartmentMaster> getAllDepas() {
		return depService.getAllDep();
	}

	@ModelAttribute("allLocs")
	public List<LocationMaster> getAllDlocs() {
		return locService.getAllLocations();
	}

	@ModelAttribute("employees")
	public List<Employee> getAllEmps() {
		return empService.getAllEmp();
	}

	@ModelAttribute("addDedTypes")
	public List<PayAddDeductTypes> getAddDed() {
		return addDedService.getAllDetails();
	}

	@ModelAttribute("empMoMaAll")
	public List<PayCode> getEmpMoMaAll() {
		return payService.getpayCodes();
	}

	// loadEmpRelatedDep
	@GetMapping("/loadReDep")
	public @ResponseBody List<EmployeeSalaryDetail> loadGrid(@RequestParam("dep") String dep,
			@RequestParam("deductTypeCode") String deductTypeCode) {
		List<EmployeeSalaryDetail> de = employeeSalaryService.saveListOfEmpReDep(dep, deductTypeCode);
		return de;
	}

	@GetMapping("/loadEmpRelatedLocation")
	public @ResponseBody List<EmployeeSalaryDetail> loadEmpRelatedLocation(@RequestParam("loid") String loid,
			@RequestParam("deductTypeCode") String deductTypeCode) {
		List<EmployeeSalaryDetail> de = employeeSalaryService.saveListOfEmpLoc(loid, deductTypeCode);
		return de;
	}

	@GetMapping("/loadEmpRelatedType")
	public @ResponseBody List<EmployeeSalaryDetail> loadEmpRelatedType(@RequestParam("tid") String tid,
			@RequestParam("deductTypeCode") String deductTypeCode) {
		List<EmployeeSalaryDetail> de = employeeSalaryService.saveListOfEmpReType(tid, deductTypeCode);
		return de;
	}

	@GetMapping("/loadEmpRelatedCat")
	public @ResponseBody List<EmployeeSalaryDetail> loadEmpRelatedCat(@RequestParam("catgoryID") String catgoryID,
			@RequestParam("deductTypeCode") String deductTypeCode) {
		List<EmployeeSalaryDetail> de = employeeSalaryService.saveListOfEmpReCat(catgoryID, deductTypeCode);
		return de;
	}

	@GetMapping("/loadEmpTable")
	public @ResponseBody List<Employee> loadEmpTable(@RequestParam("empID") String empID) {
		List<Employee> emp = empService.loadEmpToTable(empID);
		return emp;
	}

	@GetMapping("/loadSelectedEmp")
	public @ResponseBody Employee loadSelectedEmp(@RequestParam("empID") String empID) {
		Employee emp = employeeSalaryService.loadSelectedEmp(empID);
		System.out.println(empID);
		return emp;
	}

	@GetMapping("/loadAddDedType")
	public @ResponseBody PayAddDeductTypes getAddDedTypeRelatedSdetail(
			@RequestParam("deductTypeCode") String deductTypeCode) {
		PayAddDeductTypes de = employeeSalaryService.getAddDedTypeRelatedSdetail(deductTypeCode);
		return de;
	}

	@GetMapping("/getAllVTypes")
	@ResponseBody
	public List<PayAddDeductTypes> getVariableTypes() {
		List<PayAddDeductTypes> vTypes = employeeSalaryService.getVariableType();
		return vTypes;
	}

	// getDatesRelatedCode
	@GetMapping("/getDateReCode")
	public @ResponseBody PayPeriods loadData(@RequestParam("payPeriodID") String payPeriodID) {
		PayPeriods PayPeriodsbypayPeriodID = payService.loadPayPeriodsbypayPeriodID(payPeriodID);
		return PayPeriodsbypayPeriodID;
	}

	// getDatesRelatedCode
	@GetMapping("/getPayCodeUsingPeriod")
	public @ResponseBody PayCode getPayCodeUsingPeriond(@RequestParam("payPeriodID") String payPeriodID) {
		PayCode pc = payService.getPayCodeUsingPeriond(payPeriodID);
		return pc;
	}

	// getPayCodeRelatedDates
	@GetMapping("/getPayCodeReDates")
	public @ResponseBody PayPeriods loadDatatofields(@RequestParam("Start_Date") String startDate,
			@RequestParam("End_Date") String endDate) {
		PayPeriods payPeriodID = payService.loadPeriodIDbyDates(startDate, endDate);
		return payPeriodID;
	}

	@GetMapping("/getPayCodesRelatedPeriod")
	public @ResponseBody List<PayCode> getpayCodestopage(@RequestParam("periodID") String periodID) {
		List<PayCode> pa = payService.getpayCodestopage(periodID);
		return pa;
	}

	// getPayCodeRelatedPeriodCode
	@GetMapping("/getRePeriodCode")
	public @ResponseBody PayPeriods loadDataPeriods(@RequestParam("endDate") String endDate,
			@RequestParam("startDate") String startDate) {
		PayPeriods payPeriodID = payService.loadPeriodIDbyDates(startDate, endDate);
		return payPeriodID;
	}

	@GetMapping("/getOnlyDates")
	public @ResponseBody PayPeriods loadDates(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		PayPeriods pa = employeeSalaryService.getDates(startDate, endDate);
		return pa;
	}

	@GetMapping("/getOnlyDates2")
	public @ResponseBody PayCode loadDates2(@RequestParam("endDate") String endDate,
			@RequestParam("startDate") String startDate, Model model) {
		PayCode pa = employeeSalaryService.getDates2(startDate, endDate);
		model.addAttribute("Start_Date", startDate);
		return pa;
	}
	
	//month salary getPage01
	@GetMapping("/getEmpMonthSalaryDetailsPage01")
	public String loadPage01(Map<String, Object> map) {
		map.put("formMonthSalary01", new EmployeeMonthSalaryDetails());
		return "empMonthSalaryDetails01";
	}
	
	//month salary save 01
	@PostMapping("/saveEmpMoSaDetails01")
	public String saveEmpMoSaDe01(@ModelAttribute("formMonthSalary") EmployeeMonthSalaryDetails empMoSaDe,
			@RequestParam("monthDePk.empID.empID") ArrayList<String> empID,
			@RequestParam("monthDePk.payType.deductTypeCode") String deductTypeCode,
			@RequestParam("company.comID")String comID,
			@RequestParam(value = "monthDePk.payCodeid.payCodeID", required = false) String payCode,
			@RequestParam("pYear") String pYear, @RequestParam("pMonth") String pMonth,
			@RequestParam(value = "amount", required = false) ArrayList<Integer> amount1, RedirectAttributes ra,
			String empidTable,int amount) {

		List<EmployeeMonthSalaryDetails> list = new ArrayList<>();

		PayPeriods pp = new PayPeriods();
		pp.setPayPeriodID("00000".substring(payService.maxpayPeriodID().length()) + payService.maxpayPeriodID());
		pp.setStartDate(pYear);
		pp.setEndDate(pMonth);
		pp.setPayDate(pMonth);
		pp.setStatus("Open");

		PayCode pc = new PayCode();
		pc.setPayCodeID("00000".substring(payService.maxpayCodeID().length()) + payService.maxpayCodeID());
		pc.setPayCode("Generate Code " + "00000".substring(employeeSalaryService.payCodeForAG().length())
				+ employeeSalaryService.payCodeForAG());
		pc.setStartDate(pYear);
		pc.setEndDate(pMonth);
		pc.setRemarks("good");
		pc.setStatus("Open");
		pc.setPeriodID(pp);

		if (payCode.contentEquals("undefined")) {
				for (int i = 1, p = 0; i < empID.size() && p < amount1.size(); i++, p++) {
					EmployeeMonthSalaryDetails h = new EmployeeMonthSalaryDetails();
					EmployeeMonthSalaryDetailsPK j = new EmployeeMonthSalaryDetailsPK();

					Employee emp = new Employee();
					emp.setEmpID(empID.get(i));
					j.setEmpID(emp);

					PayAddDeductTypes k = new PayAddDeductTypes();
					k.setDeductTypeCode(deductTypeCode);
					j.setPayType(k);
					
					CompanyMaster com = new CompanyMaster();
					com.setComID(comID);

					j.setPayCodeid(pc);

					h.setMonthDePk(j);
					h.setpYear(pYear);
					h.setpMonth(pMonth);
					h.setAmount(amount1.get(p));
					h.setCompany(com);

					list.add(h);

				}

		} else {
			for (int i = 1, p = 0; i < empID.size() && p < amount1.size(); i++, p++) {
				EmployeeMonthSalaryDetails h = new EmployeeMonthSalaryDetails();
				EmployeeMonthSalaryDetailsPK j = new EmployeeMonthSalaryDetailsPK();

				Employee emp = new Employee();
				emp.setEmpID(empID.get(i));
				j.setEmpID(emp);

				PayAddDeductTypes k = new PayAddDeductTypes();
				k.setDeductTypeCode(deductTypeCode);
				j.setPayType(k);

				PayCode payCodeObj = new PayCode();
				payCodeObj.setPayCodeID(payCode);
				j.setPayCodeid(payCodeObj);
				
				CompanyMaster com = new CompanyMaster();
				com.setComID(comID);

				h.setMonthDePk(j);
				h.setpYear(pYear);
				h.setpMonth(pMonth);
				h.setAmount(amount1.get(p));
				h.setCompany(com);

				list.add(h);

			}
		}

		try {
			if (list.isEmpty()) {
				if (payCode.contentEquals("undefined")) {

						ra.addFlashAttribute("success", 1);
						
						Employee e = new Employee(empidTable);
						PayAddDeductTypes q = new PayAddDeductTypes(deductTypeCode);
						PayCode r = new PayCode(pc.getPayCodeID());
						CompanyMaster c = new CompanyMaster(comID);
		
						EmployeeMonthSalaryDetailsPK n = new EmployeeMonthSalaryDetailsPK(q, r, e);
		
						EmployeeMonthSalaryDetails m = new EmployeeMonthSalaryDetails(n, pYear, pMonth, amount, c);
		
						payService.savePayPeriods(pp);
						payService.savePayCodes(pc);
						employeeSalaryService.saveEmpMoDe(m);
						
						return "redirect:/getEmpMonthSalaryDetailsPage";
						
						

				} else {
					
					ra.addFlashAttribute("success", 1);
					
					Employee e = new Employee(empidTable);
					PayAddDeductTypes q = new PayAddDeductTypes(deductTypeCode);
					PayCode r = new PayCode(payCode);
					CompanyMaster c = new CompanyMaster(comID);
	
					EmployeeMonthSalaryDetailsPK n = new EmployeeMonthSalaryDetailsPK(q, r, e);
	
					EmployeeMonthSalaryDetails m = new EmployeeMonthSalaryDetails(n, pYear, pMonth, amount, c);
										
					employeeSalaryService.saveEmpMoDe(m);
					
					return "redirect:/getEmpMonthSalaryDetailsPage";
					
				}

			} else {
				if (payCode.contentEquals("undefined")) {
					
						ra.addFlashAttribute("success", 1);
						payService.savePayPeriods(pp);
						payService.savePayCodes(pc);
						employeeSalaryService.saveListEmpMoSaDe(list);
						return "redirect:/getEmpMonthSalaryDetailsPage";

				} else {

					ra.addFlashAttribute("success", 1);
					employeeSalaryService.saveListEmpMoSaDe(list);
					return "redirect:/getEmpMonthSalaryDetailsPage";
				}
			}

		} catch (Exception e) {
			ra.addFlashAttribute("success", 0);
			System.out.println(e);
		}
		return "redirect:/empMonthSalaryDetails";
	}
	
	//month salary getPage02
	@GetMapping("/getEmpMonthSalaryDetailsPage02")
	public String loadPage02(Map<String, Object> map) {
		map.put("formMonthSalary02", new EmployeeMonthSalaryDetails());
		return "empMonthSalaryDetails02";
	}
	
	//month salary save 02
	@PostMapping("/saveEmpMoSaDetails02")
	public String saveEmpMoSaDe02(@ModelAttribute("formMonthSalary02") EmployeeMonthSalaryDetails empMoSaDe,
			@RequestParam("monthDePk.empID.empID") ArrayList<String> empID,
			@RequestParam("monthDePk.payType.deductTypeCode") String deductTypeCode,
			@RequestParam("company.comID")String comID,
			@RequestParam(value = "monthDePk.payCodeid.payCodeID", required = false) String payCode,
			@RequestParam("pYear") String pYear, @RequestParam("pMonth") String pMonth,
			@RequestParam(value = "amount", required = false) ArrayList<Integer> amount1, RedirectAttributes ra,
			String empidTable,int amount) {

		List<EmployeeMonthSalaryDetails> list = new ArrayList<>();

		PayPeriods pp = new PayPeriods();
		pp.setPayPeriodID("00000".substring(payService.maxpayPeriodID().length()) + payService.maxpayPeriodID());
		pp.setStartDate(pYear);
		pp.setEndDate(pMonth);
		pp.setPayDate(pMonth);
		pp.setStatus("Open");

		PayCode pc = new PayCode();
		pc.setPayCodeID("00000".substring(payService.maxpayCodeID().length()) + payService.maxpayCodeID());
		pc.setPayCode("Generate Code " + "00000".substring(employeeSalaryService.payCodeForAG().length())
				+ employeeSalaryService.payCodeForAG());
		pc.setStartDate(pYear);
		pc.setEndDate(pMonth);
		pc.setRemarks("good");
		pc.setStatus("Open");
		pc.setPeriodID(pp);

		if (payCode.contentEquals("undefined")) {
				for (int i = 1, p = 0; i < empID.size() && p < amount1.size(); i++, p++) {
					EmployeeMonthSalaryDetails h = new EmployeeMonthSalaryDetails();
					EmployeeMonthSalaryDetailsPK j = new EmployeeMonthSalaryDetailsPK();

					Employee emp = new Employee();
					emp.setEmpID(empID.get(i));
					j.setEmpID(emp);

					PayAddDeductTypes k = new PayAddDeductTypes();
					k.setDeductTypeCode(deductTypeCode);
					j.setPayType(k);
					
					CompanyMaster com = new CompanyMaster();
					com.setComID(comID);

					j.setPayCodeid(pc);

					h.setMonthDePk(j);
					h.setpYear(pYear);
					h.setpMonth(pMonth);
					h.setAmount(amount1.get(p));
					h.setCompany(com);

					list.add(h);

				}

		} else {
			for (int i = 1, p = 0; i < empID.size() && p < amount1.size(); i++, p++) {
				EmployeeMonthSalaryDetails h = new EmployeeMonthSalaryDetails();
				EmployeeMonthSalaryDetailsPK j = new EmployeeMonthSalaryDetailsPK();

				Employee emp = new Employee();
				emp.setEmpID(empID.get(i));
				j.setEmpID(emp);

				PayAddDeductTypes k = new PayAddDeductTypes();
				k.setDeductTypeCode(deductTypeCode);
				j.setPayType(k);

				PayCode payCodeObj = new PayCode();
				payCodeObj.setPayCodeID(payCode);
				j.setPayCodeid(payCodeObj);
				
				CompanyMaster com = new CompanyMaster();
				com.setComID(comID);

				h.setMonthDePk(j);
				h.setpYear(pYear);
				h.setpMonth(pMonth);
				h.setAmount(amount1.get(p));
				h.setCompany(com);

				list.add(h);

			}
		}

		try {
			if (list.isEmpty()) {
				if (payCode.contentEquals("undefined")) {

						ra.addFlashAttribute("success", 1);
						
						Employee e = new Employee(empidTable);
						PayAddDeductTypes q = new PayAddDeductTypes(deductTypeCode);
						PayCode r = new PayCode(pc.getPayCodeID());
						CompanyMaster c = new CompanyMaster(comID);
		
						EmployeeMonthSalaryDetailsPK n = new EmployeeMonthSalaryDetailsPK(q, r, e);
		
						EmployeeMonthSalaryDetails m = new EmployeeMonthSalaryDetails(n, pYear, pMonth, amount, c);
		
						payService.savePayPeriods(pp);
						payService.savePayCodes(pc);
						employeeSalaryService.saveEmpMoDe(m);
						
						return "redirect:/getEmpMonthSalaryDetailsPage";
						
						

				} else {
					
					ra.addFlashAttribute("success", 1);
					
					Employee e = new Employee(empidTable);
					PayAddDeductTypes q = new PayAddDeductTypes(deductTypeCode);
					PayCode r = new PayCode(payCode);
					CompanyMaster c = new CompanyMaster(comID);
	
					EmployeeMonthSalaryDetailsPK n = new EmployeeMonthSalaryDetailsPK(q, r, e);
	
					EmployeeMonthSalaryDetails m = new EmployeeMonthSalaryDetails(n, pYear, pMonth, amount, c);
										
					employeeSalaryService.saveEmpMoDe(m);
					
					return "redirect:/getEmpMonthSalaryDetailsPage";
					
				}

			} else {
				if (payCode.contentEquals("undefined")) {
					
						ra.addFlashAttribute("success", 1);
						payService.savePayPeriods(pp);
						payService.savePayCodes(pc);
						employeeSalaryService.saveListEmpMoSaDe(list);
						return "redirect:/getEmpMonthSalaryDetailsPage";

				} else {

					ra.addFlashAttribute("success", 1);
					employeeSalaryService.saveListEmpMoSaDe(list);
					return "redirect:/getEmpMonthSalaryDetailsPage";
				}
			}

		} catch (Exception e) {
			ra.addFlashAttribute("success", 0);
			System.out.println(e);
		}
		return "redirect:/empMonthSalaryDetails";
	}
	
	//month salary getPage03
	@GetMapping("/getEmpMonthSalaryDetailsPage03")
	public String loadPage03(Map<String, Object> map) {
		map.put("formMonthSalary03", new EmployeeMonthSalaryDetails());
		return "empMonthSalaryDetails03";
	}
	
	//month salary save 02
		@PostMapping("/saveEmpMoSaDetails03")
		public String saveEmpMoSaDe03(@ModelAttribute("formMonthSalary02") EmployeeMonthSalaryDetails empMoSaDe,
				@RequestParam("monthDePk.empID.empID") ArrayList<String> empID,
				@RequestParam("monthDePk.payType.deductTypeCode") String deductTypeCode,
				@RequestParam("company.comID")String comID,
				@RequestParam(value = "monthDePk.payCodeid.payCodeID", required = false) String payCode,
				@RequestParam("pYear") String pYear, @RequestParam("pMonth") String pMonth,
				@RequestParam(value = "amount", required = false) ArrayList<Integer> amount1, RedirectAttributes ra,
				String empidTable,int amount) {

			List<EmployeeMonthSalaryDetails> list = new ArrayList<>();

			PayPeriods pp = new PayPeriods();
			pp.setPayPeriodID("00000".substring(payService.maxpayPeriodID().length()) + payService.maxpayPeriodID());
			pp.setStartDate(pYear);
			pp.setEndDate(pMonth);
			pp.setPayDate(pMonth);
			pp.setStatus("Open");

			PayCode pc = new PayCode();
			pc.setPayCodeID("00000".substring(payService.maxpayCodeID().length()) + payService.maxpayCodeID());
			pc.setPayCode("Generate Code " + "00000".substring(employeeSalaryService.payCodeForAG().length())
					+ employeeSalaryService.payCodeForAG());
			pc.setStartDate(pYear);
			pc.setEndDate(pMonth);
			pc.setRemarks("good");
			pc.setStatus("Open");
			pc.setPeriodID(pp);

			if (payCode.contentEquals("undefined")) {
					for (int i = 1, p = 0; i < empID.size() && p < amount1.size(); i++, p++) {
						EmployeeMonthSalaryDetails h = new EmployeeMonthSalaryDetails();
						EmployeeMonthSalaryDetailsPK j = new EmployeeMonthSalaryDetailsPK();

						Employee emp = new Employee();
						emp.setEmpID(empID.get(i));
						j.setEmpID(emp);

						PayAddDeductTypes k = new PayAddDeductTypes();
						k.setDeductTypeCode(deductTypeCode);
						j.setPayType(k);
						
						CompanyMaster com = new CompanyMaster();
						com.setComID(comID);

						j.setPayCodeid(pc);

						h.setMonthDePk(j);
						h.setpYear(pYear);
						h.setpMonth(pMonth);
						h.setAmount(amount1.get(p));
						h.setCompany(com);

						list.add(h);

					}

			} else {
				for (int i = 1, p = 0; i < empID.size() && p < amount1.size(); i++, p++) {
					EmployeeMonthSalaryDetails h = new EmployeeMonthSalaryDetails();
					EmployeeMonthSalaryDetailsPK j = new EmployeeMonthSalaryDetailsPK();

					Employee emp = new Employee();
					emp.setEmpID(empID.get(i));
					j.setEmpID(emp);

					PayAddDeductTypes k = new PayAddDeductTypes();
					k.setDeductTypeCode(deductTypeCode);
					j.setPayType(k);

					PayCode payCodeObj = new PayCode();
					payCodeObj.setPayCodeID(payCode);
					j.setPayCodeid(payCodeObj);
					
					CompanyMaster com = new CompanyMaster();
					com.setComID(comID);

					h.setMonthDePk(j);
					h.setpYear(pYear);
					h.setpMonth(pMonth);
					h.setAmount(amount1.get(p));
					h.setCompany(com);

					list.add(h);

				}
			}

			try {
				if (list.isEmpty()) {
					if (payCode.contentEquals("undefined")) {

							ra.addFlashAttribute("success", 1);
							
							Employee e = new Employee(empidTable);
							PayAddDeductTypes q = new PayAddDeductTypes(deductTypeCode);
							PayCode r = new PayCode(pc.getPayCodeID());
							CompanyMaster c = new CompanyMaster(comID);
			
							EmployeeMonthSalaryDetailsPK n = new EmployeeMonthSalaryDetailsPK(q, r, e);
			
							EmployeeMonthSalaryDetails m = new EmployeeMonthSalaryDetails(n, pYear, pMonth, amount, c);
			
							payService.savePayPeriods(pp);
							payService.savePayCodes(pc);
							employeeSalaryService.saveEmpMoDe(m);
							
							return "redirect:/getEmpMonthSalaryDetailsPage";
							
							

					} else {
						
						ra.addFlashAttribute("success", 1);
						
						Employee e = new Employee(empidTable);
						PayAddDeductTypes q = new PayAddDeductTypes(deductTypeCode);
						PayCode r = new PayCode(payCode);
						CompanyMaster c = new CompanyMaster(comID);
		
						EmployeeMonthSalaryDetailsPK n = new EmployeeMonthSalaryDetailsPK(q, r, e);
		
						EmployeeMonthSalaryDetails m = new EmployeeMonthSalaryDetails(n, pYear, pMonth, amount, c);
											
						employeeSalaryService.saveEmpMoDe(m);
						
						return "redirect:/getEmpMonthSalaryDetailsPage";
						
					}

				} else {
					if (payCode.contentEquals("undefined")) {
						
							ra.addFlashAttribute("success", 1);
							payService.savePayPeriods(pp);
							payService.savePayCodes(pc);
							employeeSalaryService.saveListEmpMoSaDe(list);
							return "redirect:/getEmpMonthSalaryDetailsPage";

					} else {

						ra.addFlashAttribute("success", 1);
						employeeSalaryService.saveListEmpMoSaDe(list);
						return "redirect:/getEmpMonthSalaryDetailsPage";
					}
				}

			} catch (Exception e) {
				ra.addFlashAttribute("success", 0);
				System.out.println(e);
			}
			return "redirect:/empMonthSalaryDetails";
		}
	
	//month salary getPage04
	@GetMapping("/getEmpMonthSalaryDetailsPage04")
	public String loadPage04(Map<String, Object> map) {
		map.put("formMonthSalary04", new EmployeeMonthSalaryDetails());
		return "empMonthSalaryDetails04";
	}
	
	//month salary save 02
		@PostMapping("/saveEmpMoSaDetails04")
		public String saveEmpMoSaDe04(@ModelAttribute("formMonthSalary02") EmployeeMonthSalaryDetails empMoSaDe,
				@RequestParam("monthDePk.empID.empID") ArrayList<String> empID,
				@RequestParam("monthDePk.payType.deductTypeCode") String deductTypeCode,
				@RequestParam("company.comID")String comID,
				@RequestParam(value = "monthDePk.payCodeid.payCodeID", required = false) String payCode,
				@RequestParam("pYear") String pYear, @RequestParam("pMonth") String pMonth,
				@RequestParam(value = "amount", required = false) ArrayList<Integer> amount1, RedirectAttributes ra,
				String empidTable,int amount) {

			List<EmployeeMonthSalaryDetails> list = new ArrayList<>();

			PayPeriods pp = new PayPeriods();
			pp.setPayPeriodID("00000".substring(payService.maxpayPeriodID().length()) + payService.maxpayPeriodID());
			pp.setStartDate(pYear);
			pp.setEndDate(pMonth);
			pp.setPayDate(pMonth);
			pp.setStatus("Open");

			PayCode pc = new PayCode();
			pc.setPayCodeID("00000".substring(payService.maxpayCodeID().length()) + payService.maxpayCodeID());
			pc.setPayCode("Generate Code " + "00000".substring(employeeSalaryService.payCodeForAG().length())
					+ employeeSalaryService.payCodeForAG());
			pc.setStartDate(pYear);
			pc.setEndDate(pMonth);
			pc.setRemarks("good");
			pc.setStatus("Open");
			pc.setPeriodID(pp);

			if (payCode.contentEquals("undefined")) {
					for (int i = 1, p = 0; i < empID.size() && p < amount1.size(); i++, p++) {
						EmployeeMonthSalaryDetails h = new EmployeeMonthSalaryDetails();
						EmployeeMonthSalaryDetailsPK j = new EmployeeMonthSalaryDetailsPK();

						Employee emp = new Employee();
						emp.setEmpID(empID.get(i));
						j.setEmpID(emp);

						PayAddDeductTypes k = new PayAddDeductTypes();
						k.setDeductTypeCode(deductTypeCode);
						j.setPayType(k);
						
						CompanyMaster com = new CompanyMaster();
						com.setComID(comID);

						j.setPayCodeid(pc);

						h.setMonthDePk(j);
						h.setpYear(pYear);
						h.setpMonth(pMonth);
						h.setAmount(amount1.get(p));
						h.setCompany(com);

						list.add(h);

					}

			} else {
				for (int i = 1, p = 0; i < empID.size() && p < amount1.size(); i++, p++) {
					EmployeeMonthSalaryDetails h = new EmployeeMonthSalaryDetails();
					EmployeeMonthSalaryDetailsPK j = new EmployeeMonthSalaryDetailsPK();

					Employee emp = new Employee();
					emp.setEmpID(empID.get(i));
					j.setEmpID(emp);

					PayAddDeductTypes k = new PayAddDeductTypes();
					k.setDeductTypeCode(deductTypeCode);
					j.setPayType(k);

					PayCode payCodeObj = new PayCode();
					payCodeObj.setPayCodeID(payCode);
					j.setPayCodeid(payCodeObj);
					
					CompanyMaster com = new CompanyMaster();
					com.setComID(comID);

					h.setMonthDePk(j);
					h.setpYear(pYear);
					h.setpMonth(pMonth);
					h.setAmount(amount1.get(p));
					h.setCompany(com);

					list.add(h);

				}
			}

			try {
				if (list.isEmpty()) {
					if (payCode.contentEquals("undefined")) {

							ra.addFlashAttribute("success", 1);
							
							Employee e = new Employee(empidTable);
							PayAddDeductTypes q = new PayAddDeductTypes(deductTypeCode);
							PayCode r = new PayCode(pc.getPayCodeID());
							CompanyMaster c = new CompanyMaster(comID);
			
							EmployeeMonthSalaryDetailsPK n = new EmployeeMonthSalaryDetailsPK(q, r, e);
			
							EmployeeMonthSalaryDetails m = new EmployeeMonthSalaryDetails(n, pYear, pMonth, amount, c);
			
							payService.savePayPeriods(pp);
							payService.savePayCodes(pc);
							employeeSalaryService.saveEmpMoDe(m);
							
							return "redirect:/getEmpMonthSalaryDetailsPage";
							
							

					} else {
						
						ra.addFlashAttribute("success", 1);
						
						Employee e = new Employee(empidTable);
						PayAddDeductTypes q = new PayAddDeductTypes(deductTypeCode);
						PayCode r = new PayCode(payCode);
						CompanyMaster c = new CompanyMaster(comID);
		
						EmployeeMonthSalaryDetailsPK n = new EmployeeMonthSalaryDetailsPK(q, r, e);
		
						EmployeeMonthSalaryDetails m = new EmployeeMonthSalaryDetails(n, pYear, pMonth, amount, c);
											
						employeeSalaryService.saveEmpMoDe(m);
						
						return "redirect:/getEmpMonthSalaryDetailsPage";
						
					}

				} else {
					if (payCode.contentEquals("undefined")) {
						
							ra.addFlashAttribute("success", 1);
							payService.savePayPeriods(pp);
							payService.savePayCodes(pc);
							employeeSalaryService.saveListEmpMoSaDe(list);
							return "redirect:/getEmpMonthSalaryDetailsPage";

					} else {

						ra.addFlashAttribute("success", 1);
						employeeSalaryService.saveListEmpMoSaDe(list);
						return "redirect:/getEmpMonthSalaryDetailsPage";
					}
				}

			} catch (Exception e) {
				ra.addFlashAttribute("success", 0);
				System.out.println(e);
			}
			return "redirect:/empMonthSalaryDetails";
		}
	
	@GetMapping("/getMoSaDePage")
	public ModelAndView getMonthSalryDetailsPage() {
		ModelAndView mav = new ModelAndView("empMonthSalaryDetailsPage");
		return mav;
	}
	
	@ModelAttribute("findAllEmpMoSaDetails")
	public List<EmployeeMonthSalaryDetails> getAllMoSaDetails() {
		 return employeeSalaryService.getAllMoSaDetails();
	}

	@GetMapping("/loadAllEmployeesToGrid")
	public @ResponseBody List<Employee> getTodayForexRates() {
		// dummy rates

		String[][] result = empService.getEmpsToEmpMoSaDetails();

		List<Employee> empSummaryReportArray = new ArrayList<>();

		for (int i = 0; i < result.length; i++) {
			Employee emp = new Employee();
			emp.setEmpID(result[i][0]);
			emp.setName(result[i][1]);
			emp.setLastname(result[i][2]);
			emp.setContact_num1(result[i][3]);
			emp.setEmail(result[i][4]);
			emp.setDob(result[i][5]);

			empSummaryReportArray.add(emp);
		}

		return empSummaryReportArray;
	}

	// load PAyAddDetuctType
	@ModelAttribute("payAddDetuctType")
	public List<PayAddDeductTypes> getAllPayAddDeductTypes() {
		return payAddDeductTypeService.getAllDetails();
	}

	// load employee salary details jsp
	@RequestMapping(value = "/getEmployeeSalaryDetailsPage", method = RequestMethod.GET)
	public String getemployeeSalaryDetailsPage(Map<String, Object> map) {
		map.put("SalaryDetail", new EmployeeSalaryDetail());
		return "employeeSalaryDetails";
	}

	// save employee salary details
	@RequestMapping(value = "/saveEmpSalaryDetails", method = RequestMethod.POST)
	public String saveEmpSalaryDetails(@ModelAttribute("SalaryDetail") EmployeeSalaryDetail emp,
			@RequestParam("empdetailPK.payAddeductTypes.deductTypeCode") String DeductType,
			@RequestParam("effective_Date") String effective_Date,
			@RequestParam(value = "added_Date", required = false) String added_Date,
			@RequestParam(value = "inactive_User", required = false) String inactive_User,
			@RequestParam(value = "inactive_From", required = false) String inactive_From,
			@RequestParam("isActive") String isActive,
			@RequestParam(value = "added_User", required = false) String added_User,
			@RequestParam("empdetailPK.empID.empID") ArrayList<String> empID,
			@RequestParam("company.comID")String comID,
			RedirectAttributes ra) {

		List<EmployeeSalaryDetail> list = new ArrayList<>();
		
		for (int i = 0; i < empID.size(); i++) {
			EmployeeSalaryDetail empSalaryDetails = new EmployeeSalaryDetail();
			EmployeeSalaryDetailPK eSpk = new EmployeeSalaryDetailPK();

			Employee empObj = new Employee();
			empObj.setEmpID(empID.get(i));
			
			CompanyMaster company = new CompanyMaster();
			company.setComID(comID);

			eSpk.setEmpID(empObj);
			PayAddDeductTypes payObj = new PayAddDeductTypes();
			payObj.setDeductTypeCode(DeductType);

			eSpk.setPayAddeductTypes(payObj);

			empSalaryDetails.setEmpdetailPK(eSpk);
			empSalaryDetails.setAdded_User(added_User);

			empSalaryDetails.setEffective_Date(effective_Date);
			empSalaryDetails.setAdded_Date(added_Date);
			empSalaryDetails.setInactive_User(inactive_User);
			empSalaryDetails.setInactive_From(inactive_From);
			empSalaryDetails.setIsActive(isActive);
			empSalaryDetails.setCompany(company);

			list.add(empSalaryDetails);

		}
		
		try {		
			ra.addFlashAttribute("success", 1);
			employeeSalaryService.savelistOFEmpSalaryDetails(list);
			return "redirect:/getEmployeeSalaryDetailsPage";
		}
		catch(Exception e) {
			ra.addFlashAttribute("success", 0);
			System.out.println(e);
		}
		return "employeeSalaryDetails";
	}
	
	@ModelAttribute("getAllEmpSaDetails")
	public List<EmployeeSalaryDetail> getAllEmpSaDetails() {
		return employeeSalaryService.getAllEmpSaDe();
	}
	
	//begin emp salary details functions
	// load employee salary details data
	@RequestMapping(value = "/loadrelavantsalaryData", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeSalaryDetail> loadrelevantsalaryDetails(@RequestParam("empID") String empID) {
		List<EmployeeSalaryDetail> sm = employeeSalaryService.loadrelevantsalaryDetails(empID);
		return sm;
	}

	// update salary details data according to empid and deductTypeCode
	@RequestMapping(value = "/updateSalaryDetails", method = RequestMethod.GET)
	public @ResponseBody ModelAndView updateSalaryDetailsData(@RequestParam("empID") String empID,
			@RequestParam("deductTypeCode") String deductTypeCode) {

		ModelAndView mav = new ModelAndView("employeeSalaryDetail");
		EmployeeSalaryDetail updateEmployeeSalaryDetail = employeeSalaryService.updateEmpDetailsSalary(empID,
				deductTypeCode);
		mav.addObject("SalaryDetail", updateEmployeeSalaryDetail);
		return mav;
	}

	// load department master data
	@ModelAttribute("loadDep")

	public List<DepartmentMaster> getAllDeps() {
		return departmentService.getAllDep();
	}

	// load related employee Based on department ID
	@RequestMapping(value = "/loadEmprelatedDepartment", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeDetails> loadrelevantemployeeTOsalaryDetails(@RequestParam("depID") String depID,
			ModelMap m) {

		List<EmployeeDetails> relatedEmployee = employeeSalaryService.loadrelevantempTosalaryDetails(depID);
		System.out.println(depID);
		// ModelAndView mav = new ModelAndView("employeeSalaryDetail");
		m.put("getIDS", relatedEmployee);

		return relatedEmployee;
	}

	// load locations
	@ModelAttribute("loadlocations")

	public List<LocationMaster> getAllLocations() {
		return locationService.getAllLocations();
	}

	// load related employee based on location ID
	@RequestMapping(value = "/loadEmprelatedLocation", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeDetails> loadrelevantemployeeTOsalaryDetailsbasedonLocation(
			@RequestParam("loid") String loid) {

		List<EmployeeDetails> relatedEmployee = employeeSalaryService
				.loadrelevantempbasedOnLocationTosalaryDetails(loid);
		System.out.println(loid);

		return relatedEmployee;
	}

	// load employee category
	@ModelAttribute("loadcategory")

	public List<EmployeeCategory> getAllempCategory() {
		return employeeService.getAllCategories();
	}

	// load employee based on category
	@RequestMapping(value = "/loadrelatedEmpBasedOnCatgory", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeDetails> loadrelevantemployeeTOsalaryDetailsbasedonCategory(
			@RequestParam("catgoryID") String catgoryID) {

		List<EmployeeDetails> relatedEmployee = employeeSalaryService
				.loadrelevantempbasedOnCategoryTosalaryDetails(catgoryID);
		System.out.println(catgoryID);

		return relatedEmployee;
	}

	// load employee types

	@ModelAttribute("loadcatypes")

	public List<EmployeeType> getAllempTypes() {
		return employeeService.getAllTypes();
	}

	// load employee based on employee types
	@RequestMapping(value = "/loadrelatedEmpBasedOnType", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeDetails> loadrelevantemployeeTOsalaryDetailsbasedonTypes(
			@RequestParam("tid") String tid) {

		List<EmployeeDetails> relatedEmployee = employeeSalaryService.loadrelevantempbasedOnTypesTosalaryDetails(tid);
		System.out.println(tid);

		return relatedEmployee;
	}

	// load all employee to employee salary details
	@RequestMapping(value = "/loadallEmp", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeDetails> loadAllEmptoAJAX() {

		List<EmployeeDetails> relatedEmployee1 = employeeSalaryService.loadallEmployeeTosalaryDetails();

		return relatedEmployee1;
	}

	@GetMapping("/loadRelatedHeader")
	@ResponseBody
	public Setting loadRelatedHeader() {
		Setting s = employeeSalaryService.loadRelatedHeader();
		return s;
	}

	@GetMapping("/showOrDisablePayCode")
	@ResponseBody
	public Setting showOrDisablePayCode() {
		Setting s = employeeSalaryService.showOrDisablePayCode();
		return s;
	}

	//load fixed type only
	@GetMapping("/loadFixedTypeOnly")
	@ResponseBody
	public List<PayAddDeductTypes> loadFixedTypeOnly() {
		List<PayAddDeductTypes> s = employeeSalaryService.loadFixedTypeOnly();
		return s;
	}
	
	
	
}
