package com.prime.hrm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prime.hrm.entity.CompanyMaster;
import com.prime.hrm.entity.DepartmentMaster;
import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeDetails;
import com.prime.hrm.entity.PayAddDeductTypes;
import com.prime.hrm.entity.SalaryAnalyze;
import com.prime.hrm.report.FTDREmployessReportBean;
import com.prime.hrm.report.PaySlipAllEmployees;
import com.prime.hrm.report.PaySlipReportPerEmployeeBean;
import com.prime.hrm.report.SalaryAnalyzeReportBeanHeaderData;
import com.prime.hrm.report.processPayrollEmpAllDetails;
import com.prime.hrm.service.DepartmentService;
import com.prime.hrm.service.EmployeeService;
import com.prime.hrm.service.PayAddDeductTypeService;
import com.prime.hrm.service.PayService;
import com.prime.hrm.service.ProcessPayrollMasterService;
import com.prime.hrm.utils.ReportViewe;

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

	@Autowired
	private PayAddDeductTypeService allowanceService;

	@ModelAttribute("getAllEmps")
	public List<EmployeeDetails> getAllEmps() {
		return empDeRepo.getAllEmpDetails();
	}

	@GetMapping("/processPayRollReport")
	public String getPage() {
		return "processPayrollReport";
	}

	// employee report 01 data
	@PostMapping("/generateEmpAllAllowanceReport")
	public ModelAndView getReport(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String comID = (String) session.getAttribute("company.comID");
		String[][] data = proPaMaService.getAllEmpWithAllowancesReportBodyData();
		String[][] data11 = proPaMaService.getAllEmpWithAllowancesReportHeaderSumData(comID);
		String[][] data2 = proPaMaService.getAllEmpWithAllowancesReportHeadData();
		String[][] data22 = proPaMaService.getAllEmpWithAllowancesReportBodySumData(comID);
		String fileName = "All Allowance with Employee Report";
		List<processPayrollEmpAllDetails> d = new ArrayList<>();
		// set table header values
		for (int j = 0, l = 0; j < data2.length && l < data11.length; j++, l++) {
			for (int i = 0, k = 0; i < data.length && k < data22.length; i++, k++) {
				processPayrollEmpAllDetails a = new processPayrollEmpAllDetails();
				a.setEmpIDLBL(data2[j][0]);
				a.setEmpNameLBL(data2[j][1]);
				a.setBasicSalaryLBL(data2[j][2]);
				a.setAllowance01LBL(data2[j][3]);
				if (4 >= data2[j].length) {
					a.setAllowance02LBL("-");
				} else {
					a.setAllowance02LBL(data2[j][4]);
				}
				if (5 >= data2[j].length) {
					a.setAllowance03LBL("-");
				} else {
					a.setAllowance03LBL(data2[j][5]);
				}
				if (6 >= data2[j].length) {
					a.setAllowance04LBL("-");
				} else {
					a.setAllowance04LBL(data2[j][6]);
				}
				if (7 >= data2[j].length) {
					a.setAllowance05LBL("-");
				} else {
					a.setAllowance05LBL(data2[j][7]);
				}
				if (8 >= data2[j].length) {
					a.setAllowance06LBL("-");
				} else {
					a.setAllowance06LBL(data2[j][8]);
				}
				if (9 >= data2[j].length) {
					a.setAllowance07LBL("-");
				} else {
					a.setAllowance07LBL(data2[j][9]);
				}
				if (10 >= data2[j].length) {
					a.setAllowance08LBL("-");
				} else {
					a.setAllowance08LBL(data2[j][10]);
				}
				if (11 >= data2[j].length) {
					a.setAllowance09LBL("-");
				} else {
					a.setAllowance09LBL(data2[j][11]);
				}
				if (12 >= data2[j].length) {
					a.setAllowance10LBL("-");
				} else {
					a.setAllowance10LBL(data2[j][12]);
				}
				if (13 >= data2[j].length) {
					a.setAllowance11LBL("-");
				} else {
					a.setAllowance11LBL(data2[j][13]);
				}
				if (14 >= data2[j].length) {
					a.setAllowance12LBL("-");
				} else {
					a.setAllowance12LBL(data2[j][14]);
				}
				if (15 >= data2[j].length) {
					a.setAllowance13LBL("-");
				} else {
					a.setAllowance13LBL(data2[j][15]);
				}
				if (16 >= data2[j].length) {
					a.setAllowance14LBL("-");
				} else {
					a.setAllowance14LBL(data2[j][16]);
				}
				if (17 >= data2[j].length) {
					a.setAllowance15LBL("-");
				} else {
					a.setAllowance15LBL(data2[j][17]);
				}
				if (18 >= data2[j].length) {
					a.setAllowance16LBL("-");
				} else {
					a.setAllowance16LBL(data2[j][18]);
				}
				if (19 >= data2[j].length) {
					a.setAllowance17LBL("-");
				} else {
					a.setAllowance17LBL(data2[j][19]);
				}
				if (20 >= data2[j].length) {
					a.setAllowance18LBL("-");
				} else {
					a.setAllowance18LBL(data2[j][20]);
				}
				if (21 >= data2[j].length) {
					a.setAllowance19LBL("-");
				} else {
					a.setAllowance19LBL(data2[j][21]);
				}
				if (22 >= data2[j].length) {
					a.setAllowance20LBL("-");
				} else {
					a.setAllowance20LBL(data2[j][22]);
				}
				if (23 >= data2[j].length) {
					a.setAllowance21LBL("-");
				} else {
					a.setAllowance21LBL(data2[j][23]);
				}
				if (24 >= data2[j].length) {
					a.setAllowance22LBL("-");
				} else {
					a.setAllowance22LBL(data2[j][24]);
				}
				if (25 >= data2[j].length) {
					a.setAllowance23LBL("-");
				} else {
					a.setAllowance23LBL(data2[j][25]);
				}
				if (26 >= data2[j].length) {
					a.setAllowance24LBL("-");
				} else {
					a.setAllowance24LBL(data2[j][26]);
				}
				if (27 >= data2[j].length) {
					a.setAllowance25LBL("-");
				} else {
					a.setAllowance25LBL(data2[j][27]);
				}
				if (28 >= data2[j].length) {
					a.setAllowance26LBL("-");
				} else {
					a.setAllowance26LBL(data2[j][28]);
				}
				if (29 >= data2[j].length) {
					a.setAllowance27LBL("-");
				} else {
					a.setAllowance27LBL(data2[j][29]);
				}
				if (1 >= data11[l].length) {
					a.setTotalBasicSalaryLBL("-");
				} else {
					a.setTotalBasicSalaryLBL(data11[l][1]);
				}
				if (2 >= data11[l].length) {
					a.setTotalEpfSalaryLBL("-");
				} else {
					a.setTotalEpfSalaryLBL(data11[l][2]);
				}
				if (3 >= data11[l].length) {
					a.setGrossSalaryLBL("-");
				} else {
					a.setGrossSalaryLBL(data11[l][3]);
				}
				if (4 >= data11[l].length) {
					a.setTotalDeductionsLBL("-");
				} else {
					a.setTotalDeductionsLBL(data11[l][4]);
				}
				if (5 >= data11[l].length) {
					a.setNetSalaryLBL("-");
				} else {
					a.setNetSalaryLBL(data11[l][5]);
				}
				// set table body values
				a.setEmpID(data[i][0]);
				a.setFname(data[i][1]);
				a.setLname(data[i][2]);
				a.setBasicSalary(data[i][3]);
				a.setAllowance01(data[i][4]);
				if (5 >= data[i].length) {
					a.setAllowance02("-");
				} else {
					a.setAllowance02(data[i][5]);
				}
				if (6 >= data[i].length) {
					a.setAllowance03("-");
				} else {
					a.setAllowance03(data[i][6]);
				}
				if (7 >= data[i].length) {
					a.setAllowance04("-");
				} else {
					a.setAllowance04(data[i][7]);
				}
				if (8 >= data[i].length) {
					a.setAllowance05("-");
				} else {
					a.setAllowance05(data[i][8]);
				}
				if (9 >= data[i].length) {
					a.setAllowance06("-");
				} else {
					a.setAllowance06(data[i][9]);
				}
				if (10 >= data[i].length) {
					a.setAllowance07("-");
				} else {
					a.setAllowance07(data[i][10]);
				}
				if (11 >= data[i].length) {
					a.setAllowance08("-");
				} else {
					a.setAllowance08(data[i][11]);
				}
				if (12 >= data[i].length) {
					a.setAllowance09("-");
				} else {
					a.setAllowance09(data[i][12]);
				}
				if (13 >= data[i].length) {
					a.setAllowance09("-");
				} else {
					a.setAllowance09(data[i][13]);
				}
				if (14 >= data[i].length) {
					a.setAllowance09("-");
				} else {
					a.setAllowance09(data[i][14]);
				}
				if (15 >= data[i].length) {
					a.setAllowance12("-");
				} else {
					a.setAllowance12(data[i][15]);
				}
				if (16 >= data[i].length) {
					a.setAllowance13("-");
				} else {
					a.setAllowance13(data[i][16]);
				}
				if (17 >= data[i].length) {
					a.setAllowance14("-");
				} else {
					a.setAllowance14(data[i][17]);
				}
				if (18 >= data[i].length) {
					a.setAllowance15("-");
				} else {
					a.setAllowance15(data[i][18]);
				}
				if (19 >= data[i].length) {
					a.setAllowance16("-");
				} else {
					a.setAllowance16(data[i][19]);
				}
				if (20 >= data[i].length) {
					a.setAllowance17("-");
				} else {
					a.setAllowance17(data[i][20]);
				}
				if (21 >= data[i].length) {
					a.setAllowance18("-");
				} else {
					a.setAllowance18(data[i][21]);
				}
				if (22 >= data[i].length) {
					a.setAllowance19("-");
				} else {
					a.setAllowance19(data[i][22]);
				}
				if (23 >= data[i].length) {
					a.setAllowance20("-");
				} else {
					a.setAllowance20(data[i][23]);
				}
				if (24 >= data[i].length) {
					a.setAllowance21("-");
				} else {
					a.setAllowance21(data[i][24]);
				}
				if (25 >= data[i].length) {
					a.setAllowance22("-");
				} else {
					a.setAllowance22(data[i][25]);
				}
				if (26 >= data[i].length) {
					a.setAllowance23("-");
				} else {
					a.setAllowance23(data[i][26]);
				}
				if (27 >= data[i].length) {
					a.setAllowance24("-");
				} else {
					a.setAllowance24(data[i][27]);
				}
				if (28 >= data[i].length) {
					a.setAllowance25("-");
				} else {
					a.setAllowance25(data[i][28]);
				}
				if (29 >= data[i].length) {
					a.setAllowance26("-");
				} else {
					a.setAllowance26(data[i][29]);
				}
				if (30 >= data[i].length) {
					a.setAllowance27("-");
				} else {
					a.setAllowance27(data[i][30]);
				}
				if (1 >= data22[k].length) {
					a.setTotalBasicSalary("-");
				} else {
					a.setTotalBasicSalary(data22[k][1]);
				}
				if (2 >= data22[k].length) {
					a.setTotalEpfSalary("-");
				} else {
					a.setTotalEpfSalary(data22[k][2]);
				}
				if (3 >= data22[k].length) {
					a.setGrossSalary("-");
				} else {
					a.setGrossSalary(data22[k][3]);
				}
				if (4 >= data22[k].length) {
					a.setTotalDeductions("-");
				} else {
					a.setTotalDeductions(data22[k][4]);
				}
				if (5 >= data22[k].length) {
					a.setNetSalary("-");
				} else {
					a.setNetSalary(data22[k][5]);
				}
				d.add(a);
			}
		}
//		}

		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("processPayrollAllEmpsWithAllowanceReport.jasper",
				fileName, d, null, response);
		ModelAndView mav = new ModelAndView("processPayrollAllEmpsWithAllowance");
		mav.addObject("pdfViewPPAEWA", report);
		return mav;
	}

	// pay slip data report
	@PostMapping("/sampleReport")
	public ModelAndView getSampleReport(@RequestParam("empID") String empID, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		String comID = (String) session.getAttribute("company.comID");
		String fileName = "PaySlip : " + empID;
		String loggedComDetails = proPaMaService.loggedCompanyName(comID);
		String[][] data = proPaMaService.paySlipData(empID, comID);
		List<PaySlipReportPerEmployeeBean> d = new ArrayList<>();
		for (int i = 0; i < data.length; i++) {
			PaySlipReportPerEmployeeBean a1 = new PaySlipReportPerEmployeeBean();
			a1.setCompanyName(loggedComDetails);
			a1.setEmpID(data[i][4]);
			a1.setEpfNo(data[i][5]);
			a1.setYear(data[i][12]);
			a1.setMonth(data[i][11]);
			a1.setfName(data[i][2]);
			a1.setlName(data[i][3]);
			a1.setDepartment(data[i][7]);
			a1.setDesignation(data[i][8]);
			a1.setAllowanceDesc(data[i][9]);
			a1.setAllowanceAmt(data[i][10]);
			d.add(a1);
		}

		Map<String, Object> params = new HashMap<>();
		params.put("empID", empID);
		params.put("comID", comID);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("paySlipPerEmployee.jasper", fileName, d, params,
				response);
		ModelAndView mav = new ModelAndView("paySlipReport");
		mav.addObject("pdfViewEPSR", report);
		return mav;
	}

	// All emps payslip
	@PostMapping("/generateAllEmployeesPaySlips")
	public ModelAndView AllEmpsPaySlip(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String comID = (String) session.getAttribute("company.comID");
		String fileName = "All Employees PaySlips";
		String loggedComDetails = proPaMaService.loggedCompanyName(comID);
		String[][] reportData = proPaMaService.getAllEmpsPaySlips(comID);
		List<PaySlipAllEmployees> list = new ArrayList<>();
		for (int i = 0; i < reportData.length; i++) {
			PaySlipAllEmployees a = new PaySlipAllEmployees();
			a.setLabelColumnOne(reportData[i][2]);
			a.setLabelColumnOneData(reportData[i][3]);
			a.setLabelColumnTwo(reportData[i][4]);
			a.setLabelColumnTwoData(reportData[i][5]);
			a.setLabelColumnThree(reportData[i][6]);
			a.setLabelColumnThreeData(reportData[i][7]);
			a.setTitle(loggedComDetails);
			list.add(a);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("comID", comID);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("paySlipAllEmployees.jasper", fileName, list, params,
				response);
		ModelAndView mav = new ModelAndView("paySlipAllEmployeesReportView");
		mav.addObject("pdfViewPSAE", report);
		return mav;
	}

	// payslip related dep
	@PostMapping("/generatePaySlipRelatedDepartment")
	public ModelAndView paySlipRelatedDepartment(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestParam("depID") String depID) throws Exception {
		String comID = (String) session.getAttribute("company.comID");
		String fileName = "PaySlip " + depID;
		String loggedComDetails = proPaMaService.loggedCompanyName(comID);
		String[][] reportData = proPaMaService.getEmpRelatedDepartmentPaySlip(comID, depID);
		List<PaySlipAllEmployees> list = new ArrayList<>();
		for (int i = 0; i < reportData.length; i++) {
			PaySlipAllEmployees a = new PaySlipAllEmployees();
			a.setLabelColumnOne(reportData[i][2]);
			a.setLabelColumnOneData(reportData[i][3]);
			a.setLabelColumnTwo(reportData[i][4]);
			a.setLabelColumnTwoData(reportData[i][5]);
			a.setLabelColumnThree(reportData[i][6]);
			a.setLabelColumnThreeData(reportData[i][7]);
			a.setTitle(loggedComDetails);
			list.add(a);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("comID", comID);
		params.put("depID", depID);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("paySlipRelatedDepartment.jasper", fileName, list, params,
				response);
		ModelAndView mav = new ModelAndView("paySlipRelatedDepartmentReportView");
		mav.addObject("pdfViewPSRD", report);
		return mav;
	}

//	//paySlip Related startDate and endDate
//	@PostMapping("/generatePaySlipRelatedDepartment")
//	public ModelAndView paySlipRelatedDepartment(HttpSession session,HttpServletRequest request,
//			HttpServletResponse response, @RequestParam("depID")String depID) throws Exception {
//		String comID = (String) session.getAttribute("company.comID");
//		String fileName = "PaySlip " + depID;
//		String loggedComDetails = proPaMaService.loggedCompanyName(comID);
//		String[][] reportData = proPaMaService.getEmpRelatedDepartmentPaySlip(comID,depID);
//		List<PaySlipAllEmployees> list = new ArrayList<>();
//		for(int i = 0; i < reportData.length; i++) {
//			PaySlipAllEmployees a = new PaySlipAllEmployees();
//			a.setLabelColumnOne(reportData[i][2]);
//			a.setLabelColumnOneData(reportData[i][3]);
//			a.setLabelColumnTwo(reportData[i][4]);
//			a.setLabelColumnTwoData(reportData[i][5]);
//			a.setLabelColumnThree(reportData[i][6]);
//			a.setLabelColumnThreeData(reportData[i][7]);
//			a.setTitle(loggedComDetails);
//			list.add(a);
//		}
//		Map<String, Object> params = new HashMap<>();
//		params.put("comID", comID);
//		params.put("depID", depID);
//		ReportViewe review = new ReportViewe();
//		String report = review.pdfReportViewInlineSystemOpen("paySlipRelatedDepartment.jasper", fileName, list, params,
//				response);
//		ModelAndView mav = new ModelAndView("paySlipRelatedDepartmentReportView");
//		mav.addObject("pdfViewPSRD", report);
//		return mav;	
//	}

	@GetMapping("/getSalaryAnalyzer")
	public String getAnalyzeSalaryPage(Map<String, Object> map) {
		map.put("salaryAnalyze", new SalaryAnalyze());
		SalaryAnalyze sa = new SalaryAnalyze();
		sa.setSaID("00000".substring(payService.getMaxSaID().length()) + payService.getMaxSaID());
		map.put("salaryAnalyze", sa);
		return "salaryAnalyzer";
	}

	@PostMapping("/saveSalaryAnalyze")
	public String saveOneObjectSA(@ModelAttribute("salaryAnalyze") SalaryAnalyze sa) {
		payService.saveSaDetails(sa);
		return "redirect:/getSalaryAnalyzer";
	}

	@PostMapping("/saveSalaryAnalyzeList")
	@ResponseBody
	public String saveOneObjectSA(@RequestParam("year") String year, @RequestParam("month") String month,
			@RequestParam("depatment.depID") String depID, @RequestParam("company.comID") String comID) {

		String[][] data = payService.getListOfAllowances();
		List<SalaryAnalyze> list = new ArrayList<>();

		for (int i = 0; i < data.length; i++) {
			SalaryAnalyze sa = new SalaryAnalyze();

			sa.setSaID("00000".substring(payService.getMaxSaID().length()) + payService.getMaxSaID());

			PayAddDeductTypes adt = new PayAddDeductTypes();
			adt.setDeductTypeCode(data[i][0]);

			CompanyMaster com = new CompanyMaster();
			com.setComID(comID);

			DepartmentMaster dep = new DepartmentMaster();
			dep.setDepID(depID);

			sa.setYear(year);
			sa.setMonth(month);
			sa.setDepatment(dep);
			sa.setAddDedType(adt);
			sa.setCompany(com);
			list.add(sa);
		}
		payService.saveAllSaDetails(list);
		return "redirect:/getSalaryAnalyzer";
	}

	@GetMapping("/salaryAnalyzeTableHeaderData")
	@ResponseBody
	public String[][] getSalaryAnalyzeTableHeaderData() {
		String[][] data = payService.getSalaryAnalyzeTableHeaderData();
		return data;
	}

	@GetMapping("/salaryAnalyzeTableBodyData")
	@ResponseBody
	public String[][] getSalaryAnalyzeTableBodyData() {
		String[][] data = payService.getSalaryAnalyzeTableBodyData();
		return data;
	}

	@DeleteMapping("/deleteAllSAdetails")
	@ResponseBody
	public String deleteAll() {
		payService.deleteAllDataOfSalaryAnalyze();
		return "redirect:/getSalaryAnalyzer";
	}

	@ModelAttribute("departmentsList")
	public List<DepartmentMaster> getAllDepas() {
		return depService.getAllDep();
	}

	@ModelAttribute("payAddDeductTypeList")
	public List<PayAddDeductTypes> getAllAllowances() {
		return allowanceService.getAllDetails();
	}

	@GetMapping("allSalaryAnalize")
	@ResponseBody
	public List<SalaryAnalyze> getAllSADetails() {
		return payService.getAllDetails();
	}

	@ModelAttribute("allSalaryAnalizes")
	public List<SalaryAnalyze> getAllSADetailes() {
		return payService.getAllDetails();
	}

	// salary analyze report begin
	@GetMapping("/SalaryAnalyzeReport")
	public ModelAndView getSalaryAnalyzeReportReport(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName = "Salary Analyze Report";
		String[][] data2 = payService.getSalaryAnalyzeTableHeaderData();
		String[][] data = payService.getSalaryAnalyzeTableBodyData();
		List<SalaryAnalyzeReportBeanHeaderData> list = new ArrayList<>();
		// set table header values
		for (int j = 0; j < data2.length; j++) {
			for (int i = 0; i < data.length; i++) {
				SalaryAnalyzeReportBeanHeaderData a = new SalaryAnalyzeReportBeanHeaderData();
				if (0 >= data2[j].length) {
					a.setDepartmentLBL("-");
				} else {
					a.setDepartmentLBL(data2[j][0]);
				}
				if (1 >= data2[j].length) {
					a.setAllowance01LBL("-");
				} else {
					a.setAllowance01LBL(data2[j][1]);
				}
				if (2 >= data2[j].length) {
					a.setAllowance02LBL("-");
				} else {
					a.setAllowance02LBL(data2[j][2]);
				}
				if (3 >= data2[j].length) {
					a.setAllowance03LBL("-");
				} else {
					a.setAllowance03LBL(data2[j][3]);
				}
				if (4 >= data2[j].length) {
					a.setAllowance04LBL("-");
				} else {
					a.setAllowance04LBL(data2[j][4]);
				}
				if (5 >= data2[j].length) {
					a.setAllowance05LBL("-");
				} else {
					a.setAllowance05LBL(data2[j][5]);
				}
				if (6 >= data2[j].length) {
					a.setAllowance06LBL("-");
				} else {
					a.setAllowance06LBL(data2[j][6]);
				}
				if (7 >= data2[j].length) {
					a.setAllowance07LBL("-");
				} else {
					a.setAllowance07LBL(data2[j][7]);
				}
				if (8 >= data2[j].length) {
					a.setAllowance08LBL("-");
				} else {
					a.setAllowance08LBL(data2[j][8]);
				}
				if (9 >= data2[j].length) {
					a.setAllowance09LBL("-");
				} else {
					a.setAllowance09LBL(data2[j][9]);
				}
				if (10 >= data2[j].length) {
					a.setAllowance10LBL("-");
				} else {
					a.setAllowance10LBL(data2[j][10]);
				}
				if (11 >= data2[j].length) {
					a.setAllowance11LBL("-");
				} else {
					a.setAllowance11LBL(data2[j][11]);
				}
				if (12 >= data2[j].length) {
					a.setAllowance12LBL("-");
				} else {
					a.setAllowance12LBL(data2[j][12]);
				}
				if (13 >= data2[j].length) {
					a.setAllowance13LBL("-");
				} else {
					a.setAllowance13LBL(data2[j][13]);
				}
				if (14 >= data2[j].length) {
					a.setAllowance14LBL("-");
				} else {
					a.setAllowance14LBL(data2[j][14]);
				}
				if (15 >= data2[j].length) {
					a.setAllowance15LBL("-");
				} else {
					a.setAllowance15LBL(data2[j][15]);
				}
				if (16 >= data2[j].length) {
					a.setAllowance16LBL("-");
				} else {
					a.setAllowance16LBL(data2[j][16]);
				}
				// set table body values
				if (0 >= data[i].length) {
					a.setDepartment("-");
				} else {
					a.setDepartment(data[i][0]);
				}
				if (1 >= data[i].length) {
					a.setAllowance01("-");
				} else {
					a.setAllowance01(data[i][1]);
				}
				if (2 >= data[i].length) {
					a.setAllowance02("-");
				} else {
					a.setAllowance02(data[i][2]);
				}
				if (3 >= data[i].length) {
					a.setAllowance03("-");
				} else {
					a.setAllowance03(data[i][3]);
				}
				if (4 >= data[i].length) {
					a.setAllowance04("-");
				} else {
					a.setAllowance04(data[i][4]);
				}
				if (5 >= data[i].length) {
					a.setAllowance05("-");
				} else {
					a.setAllowance05(data[i][5]);
				}
				if (6 >= data[i].length) {
					a.setAllowance06("-");
				} else {
					a.setAllowance06(data[i][6]);
				}
				if (7 >= data[i].length) {
					a.setAllowance07("-");
				} else {
					a.setAllowance07(data[i][7]);
				}
				if (8 >= data[i].length) {
					a.setAllowance08("-");
				} else {
					a.setAllowance08(data[i][8]);
				}
				if (9 >= data[i].length) {
					a.setAllowance09("-");
				} else {
					a.setAllowance09(data[i][9]);
				}
				if (10 >= data[i].length) {
					a.setAllowance10("-");
				} else {
					a.setAllowance10(data[i][10]);
				}
				if (11 >= data[i].length) {
					a.setAllowance11("-");
				} else {
					a.setAllowance11(data[i][11]);
				}
				if (12 >= data[i].length) {
					a.setAllowance12("-");
				} else {
					a.setAllowance12(data[i][12]);
				}
				if (13 >= data[i].length) {
					a.setAllowance13("-");
				} else {
					a.setAllowance13(data[i][13]);
				}
				if (14 >= data[i].length) {
					a.setAllowance14("-");
				} else {
					a.setAllowance14(data[i][14]);
				}
				if (15 >= data[i].length) {
					a.setAllowance15("-");
				} else {
					a.setAllowance15(data[i][15]);
				}
				if (16 >= data[i].length) {
					a.setAllowance16("-");
				} else {
					a.setAllowance16(data[i][16]);
				}
				list.add(a);
			}
		}
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("salaryAnalyze.jasper", fileName, list, null, response);
		ModelAndView mav = new ModelAndView("salaryAnalyzeReportView.jsp");
		mav.addObject("pdfViewSAR", report);
		return mav;
//		return "printSalaryAnalyzeReport";
	}

	// salary analyze report end

	// begin fixed transaction details report
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
	public String[][] getFTDataRelatedEmployee(HttpSession session, @RequestParam("empID") String empID) {
		String comID = (String) session.getAttribute("company.comID");
		String[][] data = payService.getFTDataRelatedEmployee(empID, comID);
		return data;
	}

	@GetMapping("/allEmpDataRelatedBodyData")
	@ResponseBody
	public String[][] getAllEmpFTBodyData(HttpSession session) {
		String comID = (String) session.getAttribute("company.comID");
		String[][] data = payService.getAllEmpFTBodyData(comID);
		return data;
	}

	@GetMapping("/GetDataToFTDRRelatedDepartment")
	@ResponseBody
	public String[][] GetDataToFTDRRelatedDepartment(HttpSession session, @RequestParam("depID") String depID) {
		String comID = (String) session.getAttribute("company.comID");
		String[][] data = payService.GetDataToFTDRRelatedDepartment(depID, comID);
		return data;
	}

	@GetMapping("/getDataRelatedAllDepartments")
	@ResponseBody
	public String[][] getDataRelatedAllDepartments(HttpSession session) {
		String comID = (String) session.getAttribute("company.comID");
		String[][] data = payService.getDataRelatedAllDepartments(comID);
		return data;
	}

	@PostMapping("/FTDRDepartments")
	public ModelAndView getFTDRDepartmentsReport(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String comID = (String) session.getAttribute("company.comID");
		String[][] data = payService.getDataRelatedAllDepartments(comID);
		String fileName = "Fixed Transactional Details Report All Departments";
		List<FTDREmployessReportBean> list = new ArrayList<>();
		for (int i = 0; i < data.length; i++) {
			FTDREmployessReportBean a = new FTDREmployessReportBean();
			a.setEmpID(data[i][1]);
			a.setfName(data[i][3]);
			a.setlName(data[i][4]);
			a.setPerOrVal(data[i][5]);
			a.setAmount(data[i][6]);
			list.add(a);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("company.comID", comID);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("FTDRAllDepartments.jasper", fileName, list, params,
				response);
		ModelAndView mav = new ModelAndView("fixedTransactionalDetailReportAllDeps");
		mav.addObject("pdfViewFTDRAD", report);
		return mav;
	}

	@PostMapping("/FTDRDepartment")
	public ModelAndView getFTDRDepartmentReport(HttpSession session, @RequestParam("depid") String depID,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String comID = (String) session.getAttribute("company.comID");
		String fileName = "Fixed Transactional Details Report Related Department :" + depID;
		String[][] data = payService.GetDataToFTDRRelatedDepartment(depID, comID);
		List<FTDREmployessReportBean> list = new ArrayList<>();
		for (int i = 0; i < data.length; i++) {
			FTDREmployessReportBean a = new FTDREmployessReportBean();
			a.setEmpID(data[i][1]);
			a.setfName(data[i][3]);
			a.setlName(data[i][4]);
			a.setPerOrVal(data[i][5]);
			a.setAmount(data[i][6]);
			list.add(a);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("depid", depID);
		params.put("company.comID", comID);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("FTDRRelatedDepartment.jasper", fileName, list, params,
				response);
		ModelAndView mav = new ModelAndView("fixedTransactionalDetailReportRelatedDeps");
		mav.addObject("pdfViewFTDRRD", report);
		return mav;
	}

	@PostMapping("/FTDREmployees")
	public ModelAndView getFTDREmployeeReport(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String comID = (String) session.getAttribute("company.comID");
		String fileName = "Fixed Transactional Details Report All Employees";
		String[][] data = payService.getAllEmpFTBodyData(comID);
		List<FTDREmployessReportBean> list = new ArrayList<>();
		for (int i = 0; i < data.length; i++) {
			FTDREmployessReportBean a = new FTDREmployessReportBean();
			a.setEmpID(data[i][1]);
			a.setfName(data[i][3]);
			a.setlName(data[i][4]);
			a.setPerOrVal(data[i][5]);
			a.setAmount(data[i][6]);
			list.add(a);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("company.comID", comID);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("FTDREmployees.jasper", fileName, list, params, response);
		ModelAndView mav = new ModelAndView("fixedTransactionalDetailReportAllEmp");
		mav.addObject("pdfViewFTDRRE", report);
		return mav;
//		return "printFTDEmployeesReport";
	}

	@PostMapping("/FTDREmployee")
	public ModelAndView getFTDREmployeeReport(HttpSession session, @RequestParam("empID") String empID,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String comID = (String) session.getAttribute("company.comID");
		String fileName = "Fixed Transactional Details Report Related Employee :" + empID;
		String[][] data = payService.getFTDataRelatedEmployee(empID, comID);
		List<FTDREmployessReportBean> list = new ArrayList<>();
		for (int i = 0; i < data.length; i++) {
			FTDREmployessReportBean a = new FTDREmployessReportBean();
			a.setEmpID(data[i][1]);
			a.setfName(data[i][2]);
			a.setlName(data[i][3]);
			a.setPerOrVal(data[i][4]);
			a.setAmount(data[i][5]);
			list.add(a);
		}
		Map<String, Object> params = new HashMap<>();
		params.put("empID", empID);
		params.put("company.comID", comID);
		ReportViewe review = new ReportViewe();
		String report = review.pdfReportViewInlineSystemOpen("FTDRRelatedEmployee.jasper", fileName, list, params,
				response);
		ModelAndView mav = new ModelAndView("fixedTransactionalDetailReportRelatedEmp");
		mav.addObject("pdfViewFTDRRE", report);
		return mav;
	}
	// end of fixed transaction details report

}
