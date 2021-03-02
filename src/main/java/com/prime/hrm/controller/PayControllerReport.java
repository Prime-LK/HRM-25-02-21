package com.prime.hrm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeDetails;
import com.prime.hrm.report.FTDREmployessReportBean;
import com.prime.hrm.report.PaySlipReport;
import com.prime.hrm.report.SalaryAnalyzeReportBeanHeaderData;
import com.prime.hrm.report.processPayrollEmpAllDetails;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeService;
import com.prime.hrm.service.PayService;
import com.prime.hrm.service.ProcessPayrollMasterService;

@Controller
public class PayControllerReport {

	@Autowired
	private EmployeeService empDeRepo;

	@Autowired
	private ProcessPayrollMasterService proPaMaService;

	@Autowired
	private PayService payService;
	
	@Autowired
	private DepartmentService depService;

	@ModelAttribute("getAllEmps")
	public List<EmployeeDetails> getAllEmps() {
		return empDeRepo.getAllEmpDetails();
	}

	@GetMapping("/processPayRollReport")
	public String getPage() {
		return "processPayrollReport";
	}

	//employee report 01 data 
	@PostMapping("/generateEmpAllAllowanceReport")
	public String getReport(Model m) {
		String[][] data = proPaMaService.getReportData();
		List<processPayrollEmpAllDetails> d = new ArrayList<>();
		for (int i = 0; i < data.length; i++) {			
			processPayrollEmpAllDetails a = new processPayrollEmpAllDetails();			
			a.setEmpID(data[i][0]);
			a.setfName(data[i][1]);
			a.setlName(data[i][2]);
			a.setbSalary(data[i][3]);
			a.setBudAll(data[i][4]);
			a.setAttAll(data[i][5]);
			a.setPerAll(data[i][6]);
			a.setNiAll(data[i][7]);
			a.setTarAll(data[i][8]);
			a.setTrAll(data[i][9]);
			a.setOthAll(data[i][10]);
			a.setRiAll(data[i][11]);
			a.setSaAll(data[i][12]);
			a.setTraAll(data[i][13]);
			a.setSiteAll(data[i][14]);
			a.setNoPayAll(data[i][15]);
			a.setFestAll(data[i][16]);
			a.setInsuAll(data[i][17]);
			a.setMobAll(data[i][18]);
			a.setSalAdAll(data[i][19]);
			a.setGrossSalary(data[i][20]);	
			a.setEpfAll(data[i][21]);
			a.setWalfare(data[i][22]);
			a.setLpAll(data[i][23]);
			a.setBkAll(data[i][24]);
			a.setPmAll(data[i][25]);
			a.setNetSalary(data[i][26]);
			a.setEpf12(data[i][27]);
			a.setEpf3(data[i][28]);
			d.add(a);
		}
		m.addAttribute("empsWithAllowance", d);
		return "printProcessPayrollAllEmpsWithAllowance";
	}
	
	//end of employee report 01 data 
	
	//pay slip data report
	@PostMapping("/sampleReport")
	public String getSampleReport(@RequestParam("empID") String empID, Model model) {
		String[][] data = proPaMaService.paySlipData(empID);
		List<PaySlipReport> d = new ArrayList<>();
		for (int i = 0; i < data.length; i++) {
			PaySlipReport a1 = new PaySlipReport();
			a1.setCompanyName(data[i][0]);
			a1.setProcessDate(data[i][1]);
			a1.setfName(data[i][2]);
			a1.setlName(data[i][3]);
			a1.setEmpID(data[i][4]);
			a1.setEpfNo(data[i][5]);
			a1.setBasicSalary(data[i][6]);
			a1.setDepartment(data[i][7]);
			a1.setAllowanceDesc(data[i][8]);
			a1.setAllowanceAmt(data[i][9]);
			d.add(a1);
		}
		model.addAttribute("empWithAllowance", d);
		return "printProcessPayrollEmpWithAllowance";
	}
	
	//salary analyze report begin
	@GetMapping("/SalaryAnalyzeReport")
	public String getSalaryAnalyzeReportReport(Model model) {
		String[][] data = payService.salaryAnalizeReportData();
		List<SalaryAnalyzeReportBeanHeaderData> list = new ArrayList<>();
		for(int i = 0; i < data.length; i++) {
			SalaryAnalyzeReportBeanHeaderData a = new SalaryAnalyzeReportBeanHeaderData();
			a.setDepartment(data[i][0]);
			a.setBudgetary(data[i][1]);
			a.setAttendance(data[i][2]);
			a.setRisk(data[i][3]);
			a.setPerformance(data[i][4]);
			a.setNight(data[i][5]);
			a.setTarget(data[i][6]);
			a.setTrainee(data[i][7]);
			a.setOther(data[i][8]);
			a.setRigger(data[i][9]);
			a.setSalesCom(data[i][10]);
			a.setTransport(data[i][11]);
			a.setSite(data[i][12]);
			a.setNopay(data[i][13]);
			a.setFestival(data[i][14]);
			a.setIsurance(data[i][15]);
			a.setMobile(data[i][16]);
			a.setSa(data[i][17]);
			a.setEpf8(data[i][18]);
			a.setWelfare(data[i][19]);
			a.setLaptop(data[i][20]);
			a.setBike(data[i][21]);
			a.setPm(data[i][22]);
			a.setEpf12(data[i][23]);
			a.setEpf3(data[i][24]);
			
			list.add(a);
			model.addAttribute("salaryAnalyze", list);
		}
		return "printSalaryAnalyzeReport";
	}
	
	//salary analyze report end
	
	//begin fixed transaction details report
	
	@GetMapping("/getFTDReport")
	public String getPageOfFTD() {
		return "fixedTransactionDetailsReport";
	}	
	
	@ModelAttribute("allEmployeeForFTDR")
	public List<Employee> getAllEmloyees() {
		return empDeRepo.getAllEmp();
	}
	
	@ModelAttribute("allDepartmentForFTDR")
	public List<DepartmentMaster> getAllDepartments() {
		return depService.getAllDep();
	}
	
	@GetMapping("/empDataRelatedEmployee")
	@ResponseBody
	public String[][] getFTDataRelatedEmployee(@RequestParam("empID")String empID) {
		String[][] data = payService.getFTDataRelatedEmployee(empID);
		return data;
	}

	@GetMapping("/allEmpDataRelatedBodyData")
	@ResponseBody
	public String[][] getAllEmpFTBodyData() {
		String[][] data = payService.getAllEmpFTBodyData();
		return data;
	}
	
	@GetMapping("/GetDataToFTDRRelatedDepartment")
	@ResponseBody
	public String[][] GetDataToFTDRRelatedDepartment(@RequestParam("depID")String depID) {
		String[][] data = payService.GetDataToFTDRRelatedDepartment(depID);
		return data;
	}
	
	@GetMapping("/getDataRelatedAllDepartments")
	@ResponseBody
	public String[][] getDataRelatedAllDepartments() {
		String[][] data = payService.getDataRelatedAllDepartments();
		return data;
	}
	
	@GetMapping("/FTDREmployees")
	public String getFTDREmployeeReport(Model m) {
		String[][] data = payService.getAllEmpFTBodyData();
		List<FTDREmployessReportBean> list = new ArrayList<>();
		for(int i = 0; i < data.length; i++) {
			FTDREmployessReportBean a = new FTDREmployessReportBean();
			a.setEmpID(data[i][1]);
			a.setfName(data[i][2]);
			a.setlName(data[i][3]);
			a.setPerOrVal(data[i][4]);
			a.setAmount(data[i][5]);
			list.add(a);
			m.addAttribute("FTDREmps", list);
		}
		return "printFTDEmployeesReport";
	}
	
	@GetMapping("/FTDRDepartments")
	public String getFTDRDepartmentsReport(Model m) {
		String[][] data = payService.getDataRelatedAllDepartments();
		List<FTDREmployessReportBean> list = new ArrayList<>();
		for(int i = 0; i < data.length; i++) {
			FTDREmployessReportBean a = new FTDREmployessReportBean();
			a.setEmpID(data[i][1]);
			a.setfName(data[i][2]);
			a.setlName(data[i][3]);
			a.setPerOrVal(data[i][4]);
			a.setAmount(data[i][5]);
			list.add(a);
			m.addAttribute("FTDREmps", list);
		}
		return "printFTDEmployeesReport";
	}
	
	@GetMapping("/FTDRDepartment")
	public String getFTDRDepartmentReport(@RequestParam("depID")String depID, Model m) {
		System.out.println("hello" + depID);
		String[][] data = payService.GetDataToFTDRRelatedDepartment(depID);
		List<FTDREmployessReportBean> list = new ArrayList<>();
		for(int i = 0; i < data.length; i++) {
			FTDREmployessReportBean a = new FTDREmployessReportBean();
			a.setEmpID(data[i][1]);
			a.setfName(data[i][2]);
			a.setlName(data[i][3]);
			a.setPerOrVal(data[i][4]);
			a.setAmount(data[i][5]);
			list.add(a);
			m.addAttribute("FTDREmps", list);
		}
		return "printFTDEmployeesReport";
	}
	
	@GetMapping("/FTDREmployee")
	public String getFTDREmployeeReport(@RequestParam("empID")String empID, Model m) {
		String[][] data = payService.getFTDataRelatedEmployee(empID);
		List<FTDREmployessReportBean> list = new ArrayList<>();
		for(int i = 0; i < data.length; i++) {
			FTDREmployessReportBean a = new FTDREmployessReportBean();
			a.setEmpID(data[i][1]);
			a.setfName(data[i][2]);
			a.setlName(data[i][3]);
			a.setPerOrVal(data[i][4]);
			a.setAmount(data[i][5]);
			list.add(a);
			m.addAttribute("FTDREmps", list);
		}
		return "printFTDEmployeesReport";
	}
	//end of fixed transaction details report
	
}





















