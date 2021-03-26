package com.prime.hrm.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.prime.hrm.entity.MonthProcessMaster;
import com.prime.hrm.entity.MonthProcessPayCode;
import com.prime.hrm.entity.MonthProcessPayCodePK;
import com.prime.hrm.entity.PayAddDeductTypes;
import com.prime.hrm.entity.PayCode;
import com.prime.hrm.entity.PayPeriods;
import com.prime.hrm.entity.ProcessPayroll;
import com.prime.hrm.entity.ProcessPayrollDetails;
import com.prime.hrm.entity.ProcessPayrollDetailsPK;
import com.prime.hrm.entity.ProcessPayrollMaster;
import com.prime.hrm.entity.SalaryAnalyze;
import com.prime.hrm.entity.SalaryAnalyzePK;
import com.prime.hrm.entity.SalaryHistoryDetails;
import com.prime.hrm.entity.SalaryHistoryDetailsPK;
import com.prime.hrm.entity.SalaryHistoryMaster;
import com.prime.hrm.entity.SalaryHistoryMasterPK;
import com.prime.hrm.entity.Setting;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeSalaryService;
import com.prime.hrm.service.MonthProcessMasterService;
import com.prime.hrm.service.MonthProcessPayCodeService;
import com.prime.hrm.service.PayAddDeductTypeService;
import com.prime.hrm.service.PayService;
import com.prime.hrm.service.ProcessPayrollDetailsService;
import com.prime.hrm.service.ProcessPayrollMasterService;

@Controller
public class PayController {

	@Autowired
	private PayService payService;

	@Autowired
	private EmployeeSalaryService empSaService;

	@Autowired
	private MonthProcessMasterService moProMaService;

	@Autowired
	private MonthProcessPayCodeService moProPCService;

	@Autowired
	private ProcessPayrollMasterService proPaMaService;

	@Autowired
	private ProcessPayrollDetailsService proPaDeService;

	@RequestMapping(value = "/createPayPeriod", method = RequestMethod.GET)
	public String gePayPeriodsPage(Map<String, Object> map) {
		map.put("createPayPeriod", new PayPeriods());
		PayPeriods p = new PayPeriods();
		p.setPayPeriodID("00000".substring(payService.maxpayPeriodID().length()) + payService.maxpayPeriodID());
		map.put("createPayPeriod", p);
		return "createPayPeriod";
	}

	// save payPeriods
	@RequestMapping(value = "/savePayPeriods", method = RequestMethod.POST)
	public String savePayPeriod(@ModelAttribute("createPayPeriod") PayPeriods payPeriods, BindingResult br, Model m) {

		if (br.hasErrors()) {
			return "createPayPeriod";
		} else {
			List<Setting> sl = payService.getSettingl();
			String yes = sl.get(0).getMultipayperiod().toString();
			System.out.println("final word------------------:" + yes);
			if (yes.equalsIgnoreCase("Yes")) {
				payService.savePayPeriods(payPeriods);
				return "redirect:/createPayPeriod";
			} else {
				m.addAttribute("mesg", "Dont allowed to Create multiple Pay Periods ");
				return "createPayPeriod";
			}
		}
	}

	// load payPeriods data
	@ModelAttribute("payPeriodsList")
	public List<PayPeriods> getPeriods() {
		return payService.getPayPeriods();
	}

	// update pay periods
	@RequestMapping(value = "/updatepayPeriods", method = RequestMethod.GET)
	public @ResponseBody ModelAndView updateSalaryMasterData(@RequestParam("payPeriodID") String payPeriodID) {

		ModelAndView mav = new ModelAndView("createPayPeriod");
		PayPeriods updatePayPeriods = payService.getPayPeriods(payPeriodID);
		mav.addObject("createPayPeriod", updatePayPeriods);
		return mav;
	}

	// load payCodes
	@RequestMapping(value = "/Paycodes", method = RequestMethod.GET)
	public String gePaycodesPage(Map<String, Object> map) {
		map.put("Paycodes", new PayCode());
		PayCode p = new PayCode();
		p.setPayCodeID("00000".substring(payService.maxpayCodeID().length()) + payService.maxpayCodeID());
		map.put("Paycodes", p);
		return "Paycodes";
	}

	// load data to paycode jsp bsed on paypeeriodData
	@GetMapping("/loadDataBasedOnpayPeriodID")
	public @ResponseBody PayPeriods loadData(@RequestParam("payPeriodID") String payPeriodID) {
		PayPeriods PayPeriodsbypayPeriodID = payService.loadPayPeriodsbypayPeriodID(payPeriodID);
		System.out.println(PayPeriodsbypayPeriodID.getStartDate());
		return PayPeriodsbypayPeriodID;
	}

	// save paycodes
	@RequestMapping(value = "/savePaycodes", method = RequestMethod.POST)
	public String savePaycode(@ModelAttribute("Paycodes") PayCode payCode, RedirectAttributes redirectAttrs, Model m) {

		List<Setting> sl = payService.getSettingl();
		String yes = sl.get(0).getMultipaycode().toString();

		if (yes.equalsIgnoreCase("Yes")) {
			payService.savePayCodes(payCode);

			return "redirect:/Paycodes";

		} else {

			m.addAttribute("mesg", "Dont allowed to Create multiple PayCodes ");
			return "Paycodes";
		}
	}

	// load saved PayCodes data based on payPeriodID
	@GetMapping("/loadDataToGrid")
	public @ResponseBody List<PayCode> loadPayCodeData(@RequestParam("payPeriodID") String payPeriodID) {
		List<PayCode> PayCodesbypayPeriodID = payService.loadpayCodestoGrid(payPeriodID);
		return PayCodesbypayPeriodID;
	}

	// load saved PayCodes data based on StartDate and EndDate
	@GetMapping("/loadDataToGridBYSDAndED")
	public @ResponseBody List<PayCode> loadPayCodedataBySDAndED(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		List<PayCode> PayCodesbypayPeriodID = payService.loadPayCodedataBySDAndED(startDate, endDate);
		return PayCodesbypayPeriodID;
	}

	// update paycode
	@RequestMapping(value = "/updatePayCode", method = RequestMethod.GET)
	public @ResponseBody ModelAndView updatePayCodeData(@RequestParam("payCodeID") String payCodeID) {
		ModelAndView mav = new ModelAndView("Paycodes");
		PayCode updatePayCode = payService.getPayCodes(payCodeID);
		mav.addObject("Paycodes", updatePayCode);
		return mav;
	}

	// begin of setting operations
	// load setting jsp
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String getsettingPage(Map<String, Object> map) {
		map.put("setting", new Setting());
		return "setting";
	}

	// save setting
	@RequestMapping(value = "/saveSetting", method = RequestMethod.POST)
	public String savePaycode(@ModelAttribute("setting") Setting setting) {
		payService.saveSetting(setting);
		return "redirect:/setting";
	}

	// get current settings
	@ModelAttribute("getCurrentStatus")
	public List<Setting> getCurrentDetails() {
		List<Setting> list = payService.getSettingl();
		return list;
	}
	// end of sessting operation

	// begin process payroll
	// load payroll process
	@GetMapping("/getProcessPayrollPage")
	public String gePayrollPage(Map<String, Object> map) {
		map.put("processPayroll", new ProcessPayroll());
		ProcessPayroll p = new ProcessPayroll();
		p.setProcessPayrollID("00000".substring(payService.maxprocesspayID().length()) + payService.maxprocesspayID());
		map.put("processPayroll", p);
		return "processPayroll";
	}

	@ModelAttribute("getAllDetailsOfPPD")
	public List<ProcessPayrollDetails> getAllRecordsOfProcessPayrollDetails() {
		return proPaDeService.getAllRecords();
	}

	@GetMapping("/getPage")
	public String getPage(Map<String, Object> map) {
		map.put("processPayrollPage1", new MonthProcessMaster());
		return "processPayrollNew1";
	}

	@PostMapping("/saveProcessPayRollData1")
	public String saveProcessPayrollData01(String payCodeID, String periodID, String processUser, String startDate,
			String endDate, RedirectAttributes ra, String comID, Model m) {

		//check already process the data
		List<ProcessPayrollDetails> ppd = proPaDeService.getAllRecords();
		if (ppd.isEmpty()) {

			// declare the date object for save
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime ldt = LocalDateTime.now();

			// declare the payPeriod
			PayPeriods pp = new PayPeriods();
			pp.setPayPeriodID(periodID);

			// declare the payCode
			PayCode pc = new PayCode();
			pc.setPayCodeID(payCodeID);

			CompanyMaster com = new CompanyMaster();
			com.setComID(comID);

			List<MonthProcessPayCode> list = new ArrayList<>();
			List<ProcessPayrollMaster> list2 = new ArrayList<>();
			List<ProcessPayrollDetails> list3 = new ArrayList<>();

			String[][] table01Data = proPaMaService.loadTable01Data(payCodeID);
			String[][] moProPcData = proPaMaService.getMoProPCTabbleData(payCodeID);
			String[][] table03Data = proPaMaService.sampleSave(payCodeID);
			String[][] othCalPri = proPaMaService.otherGrossPerCal(); // other allowances gross per
			String[][] dedCalPri = proPaMaService.dedGrossPerCal(); // deduction allowances gross per
			String[][] addCalPri = proPaMaService.addGrossPerCal();// addition allowances gross per
			String[][] empListCalPri = proPaMaService.calPriEmpList(); // emp cal pro

			// save of month process paycode
			for (int i = 0; i < table01Data.length; i++) {

				MonthProcessPayCode obj02 = new MonthProcessPayCode();
				MonthProcessPayCodePK obj02PK = new MonthProcessPayCodePK();
				obj02PK.setPayPeriod(pp);
				obj02PK.setPayCode(pc);

				obj02.setMoProPCpk(obj02PK);
				obj02.setEmps(table01Data[i][0]);
				obj02.setTotBasicSa(table01Data[i][1]);
				obj02.setTotAdd(table01Data[i][2]);
				obj02.setTotDed(table01Data[i][3]);
				obj02.setProcessDate(dtf.format(ldt));
				obj02.setProcessUser(processUser);
				obj02.setCompany(com);

				list.add(obj02);
			}
			// save of process payroll master
			for (int i = 0; i < moProPcData.length; i++) {
				ProcessPayrollMaster obj = new ProcessPayrollMaster();
				obj.setProPayrollMaID(
						"00000".substring(proPaMaService.getMaxID().length()) + proPaMaService.getMaxID());
				obj.setEmps(moProPcData[i][0]);
				obj.setBasicSalary(moProPcData[i][1]);
				obj.setEndDate(endDate);
				obj.setStartDate(startDate);
				obj.setMonthlyBasic(moProPcData[i][1]);
				obj.setPayPeriod(pp);
				obj.setGrossMonth(moProPcData[i][2]);
				obj.setNetMonth(moProPcData[i][3]);
				obj.setCompany(com);

				list2.add(obj);
			}
			// save of process payroll details
			for (int i = 0; i < table03Data.length; i++) {
				ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
				ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

				Employee emp = new Employee();
				emp.setEmpID(table03Data[i][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(table03Data[i][1]);

				obj03PK.setEmpID(emp);
				obj03PK.setPayCodeid(pc);
				obj03PK.setPayPeriod(pp);
				obj03PK.setPayType(addDed);

				obj03.setProPayDePK(obj03PK);
				obj03.setAmount(table03Data[i][2]);
				obj03.setCompany(com);

				list3.add(obj03);
			}
			// for the calculation priority of addition allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < addCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = addCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(addCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}
			// for the calculation priority of deduction allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < dedCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = dedCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(dedCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}

			// for the calculation priority of other allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < othCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = othCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(othCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}
			try {
				ra.addFlashAttribute("success", 1);
				proPaDeService.saveListOfDetails(list3);
				proPaMaService.saveDataList(list2);
				moProPCService.saveList(list);
				return "redirect:/getProcessPayrollPage";

			} catch (Exception e) {
				ra.addFlashAttribute("success", 0);
				System.out.println(e);
			}

		} else if (ppd.isEmpty() == false) {
			m.addAttribute("MsgForPPDMonth", "This Month Already Process the Details !");
			return "processPayrollNew1";
		}
		return "processPayrollNew1";
	}

	@GetMapping("/getPage2")
	public String getPage2(Map<String, Object> map) {
		map.put("processPayrollPage2", new MonthProcessMaster());
		return "processPayrollNew2";
	}

	@PostMapping("/saveProcessPayRollData2")
	public String saveProcessPayrollData02(String payCodeID, String periodID, String processUser, String startDate,
			String endDate, RedirectAttributes ra, String comID, Model m) {

		//check already process the data
		List<ProcessPayrollDetails> ppd = proPaDeService.getAllRecords();
		if (ppd.isEmpty()) {

			// declare the date object for save
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime ldt = LocalDateTime.now();

			// declare the payPeriod
			PayPeriods pp = new PayPeriods();
			pp.setPayPeriodID(periodID);

			// declare the payCode
			PayCode pc = new PayCode();
			pc.setPayCodeID(payCodeID);

			// declare the company
			CompanyMaster com = new CompanyMaster();
			com.setComID(comID);

			List<MonthProcessMaster> list = new ArrayList<>();
			List<ProcessPayrollMaster> list2 = new ArrayList<>();
			List<ProcessPayrollDetails> list3 = new ArrayList<>();

			String[][] table01Data = proPaMaService.loadTable01Data(payCodeID);
			String[][] moProPcData = proPaMaService.getMoProPCTabbleData(payCodeID);
			String[][] table03Data = proPaMaService.sampleSave(payCodeID);
			String[][] othCalPri = proPaMaService.otherGrossPerCal(); // other allowances gross per
			String[][] dedCalPri = proPaMaService.dedGrossPerCal(); // deduction allowances gross per
			String[][] addCalPri = proPaMaService.addGrossPerCal();// addition allowances gross per
			String[][] empListCalPri = proPaMaService.calPriEmpList(); // emp cal pro

			// save of month process master
			for (int i = 0; i < table01Data.length; i++) {
				MonthProcessMaster obj01 = new MonthProcessMaster();
				obj01.setMoProMasterID("00000".substring(moProMaService.getMID().length()) + moProMaService.getMID());
				obj01.setEmps(table01Data[i][0]);
				obj01.setTotalBSalary(table01Data[i][1]);
				obj01.setTotAddition(table01Data[i][2]);
				obj01.setTotDeduction(table01Data[i][3]);
				obj01.setPayPeriod(pp);
				obj01.setProcessUser(processUser);
				obj01.setProcessDate(dtf.format(ldt));
				obj01.setCompany(com);

				list.add(obj01);
			}
			// save of process payroll master
			for (int i = 0; i < moProPcData.length; i++) {
				ProcessPayrollMaster obj = new ProcessPayrollMaster();
				obj.setProPayrollMaID(
						"00000".substring(proPaMaService.getMaxID().length()) + proPaMaService.getMaxID());
				obj.setEmps(moProPcData[i][0]);
				obj.setBasicSalary(moProPcData[i][1]);
				obj.setEndDate(endDate);
				obj.setStartDate(startDate);
				obj.setMonthlyBasic(moProPcData[i][1]);
				obj.setPayPeriod(pp);
				obj.setGrossMonth(moProPcData[i][2]);
				obj.setNetMonth(moProPcData[i][3]);
				obj.setCompany(com);

				list2.add(obj);
			}
			// save of process payroll details
			for (int i = 0; i < table03Data.length; i++) {
				ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
				ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

				Employee emp = new Employee();
				emp.setEmpID(table03Data[i][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(table03Data[i][1]);

				obj03PK.setEmpID(emp);
				obj03PK.setPayCodeid(pc);
				obj03PK.setPayPeriod(pp);
				obj03PK.setPayType(addDed);

				obj03.setProPayDePK(obj03PK);
				obj03.setAmount(table03Data[i][2]);
				obj03.setCompany(com);

				list3.add(obj03);
			}

			// for the calculation priority of addition allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < addCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = addCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(addCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}

			// for the calculation priority of deduction allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < dedCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = dedCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(dedCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}

			// for the calculation priority of other allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < othCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = othCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(othCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}

			try {
				ra.addFlashAttribute("success", 1);
				proPaDeService.saveListOfDetails(list3);
				moProMaService.saveMoProMas(list);
				proPaMaService.saveDataList(list2);
				return "redirect:/getProcessPayrollPage";
			} catch (Exception e) {
				ra.addFlashAttribute("success", 0);
				System.out.println(e);
			}
		} else if (ppd.isEmpty() == false) {
			m.addAttribute("MsgForPPDMonth", "This Month Already Process the Details !");
			return "processPayrollNew2";
		}
		return "processPayrollNew2";
	}

	@GetMapping("/getPage3")
	public String getPage3(Map<String, Object> map) {
		map.put("processPayrollPage3", new MonthProcessMaster());
		return "processPayrollNew3";
	}

	@PostMapping("/saveProcessPayRollData3")
	public String saveProcessPayrollData03(String payCodeID, String periodID, String processUser, String startDate,
			String endDate, RedirectAttributes ra, String comID, Model m) {

		//check already process the data
		List<ProcessPayrollDetails> ppd = proPaDeService.getAllRecords();
		if (ppd.isEmpty()) {

			// declare the date object for save
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime ldt = LocalDateTime.now();

			// declare the payPeriod
			PayPeriods pp = new PayPeriods();
			pp.setPayPeriodID(periodID);

			// declare the payCode
			PayCode pc = new PayCode();
			pc.setPayCodeID(payCodeID);

			// declare the company
			CompanyMaster com = new CompanyMaster();
			com.setComID(comID);

			List<MonthProcessPayCode> list = new ArrayList<>();
			List<ProcessPayrollMaster> list2 = new ArrayList<>();
			List<ProcessPayrollDetails> list3 = new ArrayList<>();

			String[][] table01Data = proPaMaService.loadTable01Data(payCodeID);
			String[][] moProPcData = proPaMaService.getMoProPCTabbleData(payCodeID);
			String[][] table03Data = proPaMaService.sampleSave(payCodeID);
			String[][] othCalPri = proPaMaService.otherGrossPerCal(); // other allowances gross per
			String[][] dedCalPri = proPaMaService.dedGrossPerCal(); // deduction allowances gross per
			String[][] addCalPri = proPaMaService.addGrossPerCal();// addition allowances gross per
			String[][] empListCalPri = proPaMaService.calPriEmpList(); // emp cal pro

			for (int i = 0; i < table01Data.length; i++) {

				// save of month process paycode
				MonthProcessPayCode obj02 = new MonthProcessPayCode();
				MonthProcessPayCodePK obj02PK = new MonthProcessPayCodePK();
				obj02PK.setPayPeriod(pp);
				obj02PK.setPayCode(pc);

				obj02.setMoProPCpk(obj02PK);
				obj02.setEmps(table01Data[i][0]);
				obj02.setTotBasicSa(table01Data[i][1]);
				obj02.setTotAdd(table01Data[i][2]);
				obj02.setTotDed(table01Data[i][3]);
				obj02.setProcessDate(dtf.format(ldt));
				obj02.setProcessUser(processUser);
				obj02.setCompany(com);

				list.add(obj02);
			}
			// save of process payroll master
			for (int i = 0; i < moProPcData.length; i++) {
				ProcessPayrollMaster obj = new ProcessPayrollMaster();
				obj.setProPayrollMaID(
						"00000".substring(proPaMaService.getMaxID().length()) + proPaMaService.getMaxID());
				obj.setEmps(moProPcData[i][0]);
				obj.setBasicSalary(moProPcData[i][1]);
				obj.setEndDate(endDate);
				obj.setStartDate(startDate);
				obj.setMonthlyBasic(moProPcData[i][1]);
				obj.setPayPeriod(pp);
				obj.setGrossMonth(moProPcData[i][2]);
				obj.setNetMonth(moProPcData[i][3]);
				obj.setCompany(com);

				list2.add(obj);
			}
			// save of process payroll details
			for (int i = 0; i < table03Data.length; i++) {
				ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
				ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

				Employee emp = new Employee();
				emp.setEmpID(table03Data[i][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(table03Data[i][1]);

				obj03PK.setEmpID(emp);
				obj03PK.setPayCodeid(pc);
				obj03PK.setPayPeriod(pp);
				obj03PK.setPayType(addDed);

				obj03.setProPayDePK(obj03PK);
				obj03.setAmount(table03Data[i][2]);
				obj03.setCompany(com);

				list3.add(obj03);
			}

			// for the calculation priority of addition allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < addCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = addCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(addCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}

			// for the calculation priority of deduction allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < dedCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = dedCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(dedCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}

			// for the calculation priority of other allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < othCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = othCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(othCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}
			try {
				ra.addFlashAttribute("success", 1);
				proPaDeService.saveListOfDetails(list3);
				moProPCService.saveList(list);
				proPaMaService.saveDataList(list2);
				return "redirect:/getProcessPayrollPage";
			} catch (Exception e) {
				ra.addFlashAttribute("success", 0);
				System.out.println(e);
			}
		} else if (ppd.isEmpty() == false) {
			m.addAttribute("MsgForPPDMonth", "This Month Already Process the Details !");
			return "processPayrollNew3";
		}
		return "processPayrollNew3";
	}

	@GetMapping("/getPage4")
	public String getPage4(Map<String, Object> map) {
		map.put("processPayrollPage4", new ProcessPayrollDetails());
		return "processPayrollNew4";
	}

	@PostMapping("/saveProcessPayRollData4")
	public String saveProcessPayrollData04(String payCodeID, String periodID, String processUser, String startDate,
			String endDate, RedirectAttributes ra, String comID, Model m) {

		//check already process the data
		List<ProcessPayrollDetails> ppd = proPaDeService.getAllRecords();
		if (ppd.isEmpty()) {

			// declare the date object for save
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDateTime ldt = LocalDateTime.now();

			// declare the payPeriod
			PayPeriods pp = new PayPeriods();
			pp.setPayPeriodID(periodID);

			// declare the payCode
			PayCode pc = new PayCode();
			pc.setPayCodeID(payCodeID);

			// declare the company
			CompanyMaster com = new CompanyMaster();
			com.setComID(comID);

			List<MonthProcessMaster> list = new ArrayList<>();
			List<ProcessPayrollMaster> list2 = new ArrayList<>();
			List<ProcessPayrollDetails> list3 = new ArrayList<>();

			String[][] table01Data = proPaMaService.loadTable01Data(payCodeID);
			String[][] moProPcData = proPaMaService.getMoProPCTabbleData(payCodeID);
			String[][] table03Data = proPaMaService.sampleSave(payCodeID);
			String[][] othCalPri = proPaMaService.otherGrossPerCal(); // other allowances gross per
			String[][] dedCalPri = proPaMaService.dedGrossPerCal(); // deduction allowances gross per
			String[][] addCalPri = proPaMaService.addGrossPerCal();// addition allowances gross per
			String[][] empListCalPri = proPaMaService.calPriEmpList(); // emp cal pro

			// save of month process master
			for (int i = 0; i < table01Data.length; i++) {
				MonthProcessMaster obj01 = new MonthProcessMaster();

				obj01.setMoProMasterID("00000".substring(moProMaService.getMID().length()) + moProMaService.getMID());
				obj01.setEmps(table01Data[i][0]);
				obj01.setTotalBSalary(table01Data[i][1]);
				obj01.setTotAddition(table01Data[i][2]);
				obj01.setTotDeduction(table01Data[i][3]);
				obj01.setPayPeriod(pp);
				obj01.setProcessUser(processUser);
				obj01.setProcessDate(dtf.format(ldt));
				obj01.setCompany(com);

				list.add(obj01);
			}
			// save of process payroll master
			for (int i = 0; i < moProPcData.length; i++) {
				ProcessPayrollMaster obj = new ProcessPayrollMaster();
				obj.setProPayrollMaID(
						"00000".substring(proPaMaService.getMaxID().length()) + proPaMaService.getMaxID());
				obj.setEmps(moProPcData[i][0]);
				obj.setBasicSalary(moProPcData[i][1]);
				obj.setEndDate(endDate);
				obj.setStartDate(startDate);
				obj.setMonthlyBasic(moProPcData[i][1]);
				obj.setPayPeriod(pp);
				obj.setGrossMonth(moProPcData[i][2]);
				obj.setNetMonth(moProPcData[i][3]);
				obj.setCompany(com);

				list2.add(obj);
			}
			// save of process payroll details
			for (int i = 0; i < table03Data.length; i++) {
				ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
				ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

				Employee emp = new Employee();
				emp.setEmpID(table03Data[i][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(table03Data[i][1]);

				obj03PK.setEmpID(emp);
				obj03PK.setPayCodeid(pc);
				obj03PK.setPayPeriod(pp);
				obj03PK.setPayType(addDed);

				obj03.setProPayDePK(obj03PK);
				obj03.setAmount(table03Data[i][2]);
				obj03.setCompany(com);

				list3.add(obj03);
			}

			// for the calculation priority of addition allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < addCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = addCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(addCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}

			// for the calculation priority of deduction allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < dedCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = dedCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(dedCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}

			// for the calculation priority of other allowances
			for (int j = 0; j < empListCalPri.length; j++) {
				for (int i = 0; i < othCalPri.length; i++) {
					ProcessPayrollDetails obj03 = new ProcessPayrollDetails();
					ProcessPayrollDetailsPK obj03PK = new ProcessPayrollDetailsPK();

					String no1 = othCalPri[i][1];
					double dnum = Double.parseDouble(no1);

					String no2 = empListCalPri[j][1];
					double dnum02 = Double.parseDouble(no2);

					double amount = (dnum * dnum02) / 100;
					String newAmount = String.valueOf(amount);

					Employee emp = new Employee();
					emp.setEmpID(empListCalPri[j][0]);

					PayAddDeductTypes addDed = new PayAddDeductTypes();
					addDed.setDeductTypeCode(othCalPri[i][0]);

					obj03PK.setEmpID(emp);
					obj03PK.setPayCodeid(pc);
					obj03PK.setPayPeriod(pp);
					obj03PK.setPayType(addDed);

					obj03.setProPayDePK(obj03PK);
					obj03.setAmount(newAmount);
					obj03.setCompany(com);

					list3.add(obj03);
				}

			}
			try {
				ra.addFlashAttribute("success", 1);
				proPaDeService.saveListOfDetails(list3);
				moProMaService.saveMoProMas(list);
				proPaMaService.saveDataList(list2);
				return "redirect:/getProcessPayrollPage";
			} catch (Exception e) {
				ra.addFlashAttribute("success", 0);
				System.out.println(e);
			}

		} else if (ppd.isEmpty() == false) {
			m.addAttribute("MsgForPPDMonth", "This Month Already Process the Details !");
			return "processPayrollNew4";
		}
		return "processPayrollNew4";
	}

	// load processPayroll data table 01
	@GetMapping("/getTableData01")
	@ResponseBody
	public String[][] loadTable01Data(@RequestParam("payCodeID") String payCodeID) {
		String[][] table01Data = proPaMaService.loadTable01Data(payCodeID);
		return table01Data;
	}

	// table 02
	@GetMapping("/getTableData02")
	@ResponseBody
	public String[][] loadTable02Data(@RequestParam("payCodeID") String payCodeID) {
		String[][] table02Data = proPaMaService.loadTable02Data(payCodeID);
		return table02Data;
	}

	// table 03
	@GetMapping("/getTableData03")
	@ResponseBody
	public String[][] loadTable03Data(@RequestParam("payCodeID") String payCodeID, @RequestParam("empID") String empID,
			@RequestParam("comID") String comID) {
		String[][] table03Data = proPaMaService.loadTable03Data(payCodeID, empID, comID);
		return table03Data;
	}

	// table 03 basic data
	@GetMapping("/getTableData03BasicData")
	@ResponseBody
	public String[][] loadTable03BasicData(@RequestParam("payCodeID") String payCodeID,
			@RequestParam("empID") String empID, @RequestParam("comID") String comID) {
		String[][] table03Data = proPaMaService.loadTable03BasicData(payCodeID, empID, comID);
		return table03Data;
	}

	// calPriorityData
	@GetMapping("/calPriorityData")
	@ResponseBody
	public String calPriorityData(@RequestParam("payCodeID") String payCodeID, @RequestParam("empID") String empID) {
		String calPriorityData = proPaMaService.CalPriorotyData(payCodeID, empID);
		return calPriorityData;
	}

	// calPriorityData
	@GetMapping("/otherGrossValues")
	@ResponseBody
	public String[][] otherGrossValues(@RequestParam("empID") String empID) {
		String[][] otherGrossValues = proPaMaService.otherGrossPerValues(empID);
		return otherGrossValues;
	}

	// dedPriorityData
	@GetMapping("/dedGrossPerValues")
	@ResponseBody
	public String[][] dedGrossPerValues(@RequestParam("empID") String empID) {
		String[][] dedGrossPerValues = proPaMaService.dedGrossPerValues(empID);
		return dedGrossPerValues;
	}

	// addPriorityData
	@GetMapping("/addGrossPerValues")
	@ResponseBody
	public String[][] addGrossPerValues(@RequestParam("empID") String empID) {
		String[][] addGrossPerValues = proPaMaService.addGrossPerValues(empID);
		return addGrossPerValues;
	}

	@GetMapping("/checkPerOrNotWhenEdit")
	@ResponseBody
	public PayAddDeductTypes checkPerOrNotWhenEdit(@RequestParam("id") String id) {
		PayAddDeductTypes obj = payService.checkPerOrNotWhenEdit(id);
		return obj;
	}

	// load pay code when using period code
	@GetMapping("/getPayCodeUsingPeriond")
	public @ResponseBody PayCode getPayCodeUsingPeriond(@RequestParam("periodID") String periodID) {
		PayCode payPeriod = payService.getPayCodeUsingPeriond(periodID);
		return payPeriod;
	}

	@GetMapping("/getRePCodes")
	public @ResponseBody PayPeriods getRePCodes(@RequestParam("startDate") String pYear,
			@RequestParam("endDate") String pMonth) {
		PayPeriods pa = payService.loadPeriodIDbyDates2(pYear, pMonth);
		return pa;
	}

	@GetMapping("/loadRelatedHeader2")
	public @ResponseBody Setting loadRelatedHeader() {
		Setting s = empSaService.loadRelatedHeader();
		return s;
	}

	// load pay code
	@GetMapping("/loadpayCodeID")
	public @ResponseBody List<PayCode> loadDatatofield(@RequestParam("periodID") String periodID) {
		List<PayCode> payPeriod = payService.getpayCodestopage(periodID);
		return payPeriod;
	}

	// load periodID based on start date and end date
	@GetMapping("/loadPeriodID")
	public @ResponseBody PayPeriods loadDatatofields(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		PayPeriods payPeriodID = payService.loadPeriodIDbyDates(startDate, endDate);
		return payPeriodID;
	}

	// load paycodes
	@ModelAttribute("payCodeList")
	public List<PayCode> getPayCode() {
		return payService.getpayCodes();
	}
	// end of procecss payroll

	// begin of salary month end functions

	@GetMapping("/getSalaryMonthEnd")
	public String getPage() {
		return "salaryMonthEnd";
	}

	@GetMapping("/salaryMonthEndFor01")
	public String salaryMonthEndFor01(Map<String, Object> map) {
		map.put("salaryHisMasForm01", new SalaryHistoryMaster());
		return "salaryMonthEndFor01";
	}

	@PostMapping("/saveSalaryMonthEndForm01")
	public String saveSaHisMasDetails(String startDate, String endDate, String periodID, String processUser,
			String payCodeID, String comID) {

		// declare the date object for save
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime ldt = LocalDateTime.now();

		// declare the payPeriod
		PayPeriods pp = new PayPeriods();
		pp.setPayPeriodID(periodID);

		// declare the payCode
		PayCode pc = new PayCode();
		pc.setPayCodeID(payCodeID);

		// declare the company
		CompanyMaster com = new CompanyMaster();
		com.setComID(comID);

		List<SalaryHistoryMaster> list = new ArrayList<>();
		List<SalaryHistoryDetails> list2 = new ArrayList<>();

		String[][] table03Data = proPaMaService.sampleSave(payCodeID);
		String[][] othCalPri = proPaMaService.otherGrossPerCal(); // other allowances gross per
		String[][] dedCalPri = proPaMaService.dedGrossPerCal(); // deduction allowances gross per
		String[][] addCalPri = proPaMaService.addGrossPerCal();// addition allowances gross per
		String[][] empListCalPri = proPaMaService.calPriEmpList(); // emp cal pro
		String[][] saHisMaData = payService.saveSalaryHistoryMaster(payCodeID);

		// save salary history master
		for (int i = 0; i < saHisMaData.length; i++) {
			SalaryHistoryMaster obj01 = new SalaryHistoryMaster();
			SalaryHistoryMasterPK obj01PK = new SalaryHistoryMasterPK();

			obj01PK.setId("00000".substring(payService.getMaxID().length()) + payService.getMaxID());
			obj01PK.setPayCode(pc);

			obj01.setSaHisMaPK(obj01PK);
			obj01.setEmp(saHisMaData[i][0]);
			obj01.setProcessUserID(processUser);
			obj01.setBasicSalary(saHisMaData[i][1]);
			obj01.setTotAddition(saHisMaData[i][2]);
			obj01.setTotDeduction(saHisMaData[i][3]);
			obj01.setProcessDate(dtf.format(ldt));
			obj01.setGrossSalary(saHisMaData[i][4]);
			obj01.setNetSalary(saHisMaData[i][5]);
			obj01.setProcessYear(startDate);
			obj01.setProcessMonth(endDate);
			obj01.setCompany(com);

			list.add(obj01);
		}

		// save salary history details
		for (int i = 0; i < table03Data.length; i++) {
			SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
			SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

			Employee emp = new Employee();
			emp.setEmpID(table03Data[i][0]);

			PayAddDeductTypes addDed = new PayAddDeductTypes();
			addDed.setDeductTypeCode(table03Data[i][1]);

			obj03PK.setEmp(emp);
			obj03PK.setPayCode(pc);
			obj03PK.setAddDedType(addDed);

			obj03.setSaHiDePK(obj03PK);
			obj03.setAmount(table03Data[i][2]);
			obj03.setProcessYear(startDate);
			obj03.setProcessMonth(endDate);
			obj03.setCompany(com);

			list2.add(obj03);
		}

		// for the calculation priority of addition allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < addCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = addCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(addCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// for the calculation priority of deduction allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < dedCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = dedCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(dedCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// for the calculation priority of other allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < othCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = othCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(othCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// delete process payroll data after save the month end data
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
					moProPCService.deleteAllData();
					proPaMaService.deleteAllDataOfProcessPayrollMaster();
					proPaDeService.deleteAllProcessPayrollDetailsData();
					System.out.println("All Data Deleted Successfully");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		payService.saveSaHisMa(list);
		payService.saveSaHiDe(list2);
		thread.start();
		return "redirect:/getSalaryMonthEnd";

	}

	@GetMapping("/salaryMonthEndFor02")
	public String salaryMonthEndFor02(Map<String, Object> map) {
		map.put("salaryHisMasForm02", new SalaryHistoryMaster());
		return "salaryMonthEndFor02";
	}

	@PostMapping("/saveSalaryMonthEndForm02")
	public String saveSaHisMasDetails2(String startDate, String endDate, String periodID, String processUser,
			String payCodeID, String comID) {

		// declare the date object for save
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime ldt = LocalDateTime.now();

		// declare the payPeriod
		PayPeriods pp = new PayPeriods();
		pp.setPayPeriodID(periodID);

		// declare the payCode
		PayCode pc = new PayCode();
		pc.setPayCodeID(payCodeID);

		// declare the company
		CompanyMaster com = new CompanyMaster();
		com.setComID(comID);

		List<SalaryHistoryMaster> list = new ArrayList<>();
		List<SalaryHistoryDetails> list2 = new ArrayList<>();

		String[][] table03Data = proPaMaService.sampleSave(payCodeID);
		String[][] othCalPri = proPaMaService.otherGrossPerCal(); // other allowances gross per
		String[][] dedCalPri = proPaMaService.dedGrossPerCal(); // deduction allowances gross per
		String[][] addCalPri = proPaMaService.addGrossPerCal();// addition allowances gross per
		String[][] empListCalPri = proPaMaService.calPriEmpList(); // emp cal pro
		String[][] saHisMaData = payService.saveSalaryHistoryMaster(payCodeID);

		// save salary history master
		for (int i = 0; i < saHisMaData.length; i++) {
			SalaryHistoryMaster obj01 = new SalaryHistoryMaster();
			SalaryHistoryMasterPK obj01PK = new SalaryHistoryMasterPK();

			obj01PK.setId("00000".substring(payService.getMaxID().length()) + payService.getMaxID());
			obj01PK.setPayCode(pc);

			obj01.setSaHisMaPK(obj01PK);
			obj01.setEmp(saHisMaData[i][0]);
			obj01.setProcessUserID(processUser);
			obj01.setBasicSalary(saHisMaData[i][1]);
			obj01.setTotAddition(saHisMaData[i][2]);
			obj01.setTotDeduction(saHisMaData[i][3]);
			obj01.setProcessDate(dtf.format(ldt));
			obj01.setGrossSalary(saHisMaData[i][4]);
			obj01.setNetSalary(saHisMaData[i][5]);
			obj01.setProcessYear(startDate);
			obj01.setProcessMonth(endDate);
			obj01.setCompany(com);

			list.add(obj01);
		}

		// save salary history details
		for (int i = 0; i < table03Data.length; i++) {
			SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
			SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

			Employee emp = new Employee();
			emp.setEmpID(table03Data[i][0]);

			PayAddDeductTypes addDed = new PayAddDeductTypes();
			addDed.setDeductTypeCode(table03Data[i][1]);

			obj03PK.setEmp(emp);
			obj03PK.setPayCode(pc);
			obj03PK.setAddDedType(addDed);

			obj03.setSaHiDePK(obj03PK);
			obj03.setAmount(table03Data[i][2]);
			obj03.setProcessYear(startDate);
			obj03.setProcessMonth(endDate);
			obj03.setCompany(com);

			list2.add(obj03);
		}

		// for the calculation priority of addition allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < addCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = addCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(addCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// for the calculation priority of deduction allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < dedCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = dedCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(dedCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// for the calculation priority of other allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < othCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = othCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(othCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// delete process payroll data after save the month end data
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
					proPaMaService.deleteAllDataOfProcessPayrollMaster();
					proPaMaService.deleteAllDataOfProcessPayrollMaster();
					proPaDeService.deleteAllProcessPayrollDetailsData();
					System.out.println("All Data Deleted Successfully");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		payService.saveSaHisMa(list);
		payService.saveSaHiDe(list2);
		thread.start();
		return "redirect:/getSalaryMonthEnd";

	}

	@GetMapping("/salaryMonthEndFor03")
	public String salaryMonthEndFor03(Map<String, Object> map) {
		map.put("salaryHisMasForm03", new SalaryHistoryMaster());
		return "salaryMonthEndFor03";
	}

	@PostMapping("/saveSalaryMonthEndForm03")
	public String saveSaHisMasDetails3(String startDate, String endDate, String periodID, String processUser,
			String payCodeID, String comID) {

		// declare the date object for save
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime ldt = LocalDateTime.now();

		// declare the payPeriod
		PayPeriods pp = new PayPeriods();
		pp.setPayPeriodID(periodID);

		// declare the payCode
		PayCode pc = new PayCode();
		pc.setPayCodeID(payCodeID);

		// declare the company
		CompanyMaster com = new CompanyMaster();
		com.setComID(comID);

		List<SalaryHistoryMaster> list = new ArrayList<>();
		List<SalaryHistoryDetails> list2 = new ArrayList<>();

		String[][] table03Data = proPaMaService.sampleSave(payCodeID);
		String[][] othCalPri = proPaMaService.otherGrossPerCal(); // other allowances gross per
		String[][] dedCalPri = proPaMaService.dedGrossPerCal(); // deduction allowances gross per
		String[][] addCalPri = proPaMaService.addGrossPerCal();// addition allowances gross per
		String[][] empListCalPri = proPaMaService.calPriEmpList(); // emp cal pro
		String[][] saHisMaData = payService.saveSalaryHistoryMaster(payCodeID);

		// save salary history master
		for (int i = 0; i < saHisMaData.length; i++) {
			SalaryHistoryMaster obj01 = new SalaryHistoryMaster();
			SalaryHistoryMasterPK obj01PK = new SalaryHistoryMasterPK();

			obj01PK.setId("00000".substring(payService.getMaxID().length()) + payService.getMaxID());
			obj01PK.setPayCode(pc);

			obj01.setSaHisMaPK(obj01PK);
			obj01.setEmp(saHisMaData[i][0]);
			obj01.setProcessUserID(processUser);
			obj01.setBasicSalary(saHisMaData[i][1]);
			obj01.setTotAddition(saHisMaData[i][2]);
			obj01.setTotDeduction(saHisMaData[i][3]);
			obj01.setProcessDate(dtf.format(ldt));
			obj01.setGrossSalary(saHisMaData[i][4]);
			obj01.setNetSalary(saHisMaData[i][5]);
			obj01.setProcessYear(startDate);
			obj01.setProcessMonth(endDate);
			obj01.setCompany(com);

			list.add(obj01);
		}

		// save salary history details
		for (int i = 0; i < table03Data.length; i++) {
			SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
			SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

			Employee emp = new Employee();
			emp.setEmpID(table03Data[i][0]);

			PayAddDeductTypes addDed = new PayAddDeductTypes();
			addDed.setDeductTypeCode(table03Data[i][1]);

			obj03PK.setEmp(emp);
			obj03PK.setPayCode(pc);
			obj03PK.setAddDedType(addDed);

			obj03.setSaHiDePK(obj03PK);
			obj03.setAmount(table03Data[i][2]);
			obj03.setProcessYear(startDate);
			obj03.setProcessMonth(endDate);
			obj03.setCompany(com);

			list2.add(obj03);
		}

		// for the calculation priority of addition allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < addCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = addCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(addCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// for the calculation priority of deduction allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < dedCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = dedCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(dedCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// for the calculation priority of other allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < othCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = othCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(othCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// delete process payroll data after save the month end data
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
					moProPCService.deleteAllData();
					proPaMaService.deleteAllDataOfProcessPayrollMaster();
					proPaDeService.deleteAllProcessPayrollDetailsData();
					System.out.println("All Data Deleted Successfully");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		payService.saveSaHisMa(list);
		payService.saveSaHiDe(list2);
		thread.start();
		return "redirect:/getSalaryMonthEnd";

	}

	@GetMapping("/salaryMonthEndFor04")
	public String salaryMonthEndFor04(Map<String, Object> map) {
		map.put("salaryHisMasForm04", new SalaryHistoryMaster());
		return "salaryMonthEndFor04";
	}

	@PostMapping("/saveSalaryMonthEndForm04")
	public String saveSaHisMasDetails4(String startDate, String endDate, String periodID, String processUser,
			String payCodeID, String comID) {

		// declare the date object for save
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime ldt = LocalDateTime.now();

		// declare the payPeriod
		PayPeriods pp = new PayPeriods();
		pp.setPayPeriodID(periodID);

		// declare the payCode
		PayCode pc = new PayCode();
		pc.setPayCodeID(payCodeID);

		// declare the company
		CompanyMaster com = new CompanyMaster();
		com.setComID(comID);

		List<SalaryHistoryMaster> list = new ArrayList<>();
		List<SalaryHistoryDetails> list2 = new ArrayList<>();

		String[][] table03Data = proPaMaService.sampleSave(payCodeID);
		String[][] othCalPri = proPaMaService.otherGrossPerCal(); // other allowances gross per
		String[][] dedCalPri = proPaMaService.dedGrossPerCal(); // deduction allowances gross per
		String[][] addCalPri = proPaMaService.addGrossPerCal();// addition allowances gross per
		String[][] empListCalPri = proPaMaService.calPriEmpList(); // emp cal pro
		String[][] saHisMaData = payService.saveSalaryHistoryMaster(payCodeID);

		// save salary history master
		for (int i = 0; i < saHisMaData.length; i++) {
			SalaryHistoryMaster obj01 = new SalaryHistoryMaster();
			SalaryHistoryMasterPK obj01PK = new SalaryHistoryMasterPK();

			obj01PK.setId("00000".substring(payService.getMaxID().length()) + payService.getMaxID());
			obj01PK.setPayCode(pc);

			obj01.setSaHisMaPK(obj01PK);
			obj01.setEmp(saHisMaData[i][0]);
			obj01.setProcessUserID(processUser);
			obj01.setBasicSalary(saHisMaData[i][1]);
			obj01.setTotAddition(saHisMaData[i][2]);
			obj01.setTotDeduction(saHisMaData[i][3]);
			obj01.setProcessDate(dtf.format(ldt));
			obj01.setGrossSalary(saHisMaData[i][4]);
			obj01.setNetSalary(saHisMaData[i][5]);
			obj01.setProcessYear(startDate);
			obj01.setProcessMonth(endDate);
			obj01.setCompany(com);

			list.add(obj01);
		}

		// save salary history details
		for (int i = 0; i < table03Data.length; i++) {
			SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
			SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

			Employee emp = new Employee();
			emp.setEmpID(table03Data[i][0]);

			PayAddDeductTypes addDed = new PayAddDeductTypes();
			addDed.setDeductTypeCode(table03Data[i][1]);

			obj03PK.setEmp(emp);
			obj03PK.setPayCode(pc);
			obj03PK.setAddDedType(addDed);

			obj03.setSaHiDePK(obj03PK);
			obj03.setAmount(table03Data[i][2]);
			obj03.setProcessYear(startDate);
			obj03.setProcessMonth(endDate);
			obj03.setCompany(com);

			list2.add(obj03);
		}

		// for the calculation priority of addition allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < addCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = addCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(addCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// for the calculation priority of deduction allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < dedCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = dedCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(dedCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// for the calculation priority of other allowances
		for (int j = 0; j < empListCalPri.length; j++) {
			for (int i = 0; i < othCalPri.length; i++) {
				SalaryHistoryDetails obj03 = new SalaryHistoryDetails();
				SalaryHistoryDetailsPK obj03PK = new SalaryHistoryDetailsPK();

				String no1 = othCalPri[i][1];
				double dnum = Double.parseDouble(no1);

				String no2 = empListCalPri[j][1];
				double dnum02 = Double.parseDouble(no2);

				double amount = (dnum * dnum02) / 100;
				String newAmount = String.valueOf(amount);

				Employee emp = new Employee();
				emp.setEmpID(empListCalPri[j][0]);

				PayAddDeductTypes addDed = new PayAddDeductTypes();
				addDed.setDeductTypeCode(othCalPri[i][0]);

				obj03PK.setEmp(emp);
				obj03PK.setPayCode(pc);
				obj03PK.setAddDedType(addDed);

				obj03.setSaHiDePK(obj03PK);
				obj03.setAmount(newAmount);
				obj03.setProcessYear(startDate);
				obj03.setProcessMonth(endDate);
				obj03.setCompany(com);

				list2.add(obj03);
			}

		}

		// delete process payroll data after save the month end data
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
					proPaMaService.deleteAllDataOfProcessPayrollMaster();
					proPaMaService.deleteAllDataOfProcessPayrollMaster();
					proPaDeService.deleteAllProcessPayrollDetailsData();
					System.out.println("All Data Deleted Successfully");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		});
		payService.saveSaHisMa(list);
		payService.saveSaHiDe(list2);
		thread.start();
		return "redirect:/getSalaryMonthEnd";

	}
	// end of salary month end functions

}
