package com.prime.hrm.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.PayAddDeductTypes;
import com.prime.hrm.entity.PayCode;
import com.prime.hrm.entity.PayPeriods;
import com.prime.hrm.entity.ProcessPayroll;
import com.prime.hrm.entity.SalaryAnalyze;
import com.prime.hrm.entity.SalaryAnalyzeReport;
import com.prime.hrm.entity.SalaryHistoryDetails;
import com.prime.hrm.entity.SalaryHistoryMaster;
import com.prime.hrm.entity.Setting;
import com.prime.hrm.repository.EmployeeDetailsRepository;
import com.prime.hrm.repository.PayAddDeductTypesRepository;
import com.prime.hrm.repository.PayCodeRepository;
import com.prime.hrm.repository.PayPeriodsRepository;
import com.prime.hrm.repository.ProcessPayrollMasterRepository;
import com.prime.hrm.repository.ProcessPayrollRepository;
import com.prime.hrm.repository.SalaryAnalyzeReportRepository;
import com.prime.hrm.repository.SalaryAnalyzeRepository;
import com.prime.hrm.repository.SalaryHistoryDetailsRepository;
import com.prime.hrm.repository.SalaryHistoryMasterRepository;
import com.prime.hrm.repository.SettingRepository;

@Service
public class PayService {

	@Autowired
	private PayPeriodsRepository payPeriodsRepository;

	@Autowired
	private PayCodeRepository payCodeRepository;

	@Autowired
	private ProcessPayrollRepository processPayrollRepository;

	@Autowired
	private SettingRepository settingRepository;

	@Autowired
	private SalaryHistoryMasterRepository saHisMaRepo;
	
	@Autowired
	private EmployeeDetailsRepository empDeRepo; 
	
	@Autowired
	private PayAddDeductTypesRepository addDedRepo; 
	
	@Autowired
	private ProcessPayrollMasterRepository proPayMaRepo;
	
	@Autowired
	private SalaryHistoryDetailsRepository saHiDeService;
	
	@Autowired
	private SettingRepository setRepo;
	
	@Autowired
	private SalaryAnalyzeRepository saRepo;

	@Autowired
	private SalaryAnalyzeReportRepository saReRepo;
	
	@Autowired
	private EntityManager em;
	
	// get maxid for payPeriod
	public String maxpayPeriodID() {
		if (payPeriodsRepository.maxpayPeriodID() == null) {
			return "1";
		} else {
			return payPeriodsRepository.maxpayPeriodID();
		}
	}

	// get data using payPeriodID
	public PayPeriods getPayPeriods(String payPeriodID) {
		return payPeriodsRepository.findById(payPeriodID).get();
	}

	// save payPeriods
	public void savePayPeriods(PayPeriods payPeriods) {
		payPeriodsRepository.save(payPeriods);
	}

	// getList od saved payPeriods
	public List<PayPeriods> getPayPeriods() {
		return (List<PayPeriods>) payPeriodsRepository.findAll();
	}

	// get maxid for payCode
	public String maxpayCodeID() {
		if (payCodeRepository.maxPayCodeID() == null) {
			return "1";
		} else {
			return payCodeRepository.maxPayCodeID();
		}
	}

	// get data using payCodeID
	public PayCode getPayCodes(String PayCode) {
		return payCodeRepository.findById(PayCode).get();
	}

	// save payCodes
	public void savePayCodes(PayCode payCode) {
		payCodeRepository.save(payCode);
	}

	// get saved payCodes
	public List<PayCode> getpayCodes() {
		return (List<PayCode>) payCodeRepository.findAll();
	}

	// load payPeriodData to payCode jsp
	public PayPeriods loadPayPeriodsbypayPeriodID(String payPeriodID) {
		return payPeriodsRepository.loadPayPeriodsdata(payPeriodID);
	}

	// load payCode data based on payCodeID
	public List<PayCode> loadpayCodestoGrid(String payPeriodID) {
		return payCodeRepository.loadPayCodedata(payPeriodID);
	}

	// load payCode data based on payCodeID
	public List<PayCode> loadPayCodedataBySDAndED(String startDate, String endDate) {
		return payCodeRepository.loadPayCodedataBySDAndED(startDate, endDate);
	}
	
	// get maxid for process pay roll
	public String maxprocesspayID() {
		if (processPayrollRepository.maxProcessPayrollID() == null) {
			return "1";
		} else {
			return processPayrollRepository.maxProcessPayrollID();
		}
	}

	// save
	public void saveProcessPayroll(ProcessPayroll processPayroll) {
		processPayrollRepository.save(processPayroll);
	}

	// getByID
	public ProcessPayroll loadProcessPayrollbyID(String processPayrollID) {
		return processPayrollRepository.findById(processPayrollID).get();
	}

	// load saved ProcessPayroll data
	public List<ProcessPayroll> getProcessPayroll() {
		return (List<ProcessPayroll>) processPayrollRepository.findAll();
	}

	// load perriod id based on start date AND end date
	public PayPeriods loadPeriodIDbyDates(String startDate, String endDate) {
		return payPeriodsRepository.loadPeriodData1(startDate, endDate);
	}
	
	// load perriod id based on start date AND end date2
	public PayPeriods loadPeriodIDbyDates2(String startDate, String endDate) {
		return payPeriodsRepository.loadPeriodData(startDate, endDate);
	}

	// load paycode for combo box
	public List<PayCode> getpayCodestopage(String periodID) {
		return payCodeRepository.loadPayCode(periodID);
	}	
	
	// load paycode for combo box
	public PayCode getPayCodeUsingPeriond(String periodID) {
		return payCodeRepository.getPayCodeUsingPeriond(periodID);
	}

	// save setting details
	public void saveSetting(Setting setting) {
		settingRepository.save(setting);
	}

	// get list of setting
	public List<Setting> getSettingl() {
		return (List<Setting>) settingRepository.findAll();
	}
	
	//procesPayroll report
	public String[][] processPayRollReport(String empID) {
		return empDeRepo.processPayRollReport(empID);
	}
	
	public String[][] getCalPriForSumReport(String empID) {
		return empDeRepo.getCalPriForSumReport(empID);
	}
	
	public int updateExistsValue(String payCode,String startDate,String endDate,String status,String remarks,
			String payPeriodID,String payCodeID) {
		return payCodeRepository.updateExistsData(payCode, startDate,
				endDate, status, remarks, payPeriodID, payCodeID);
	}
	
	public int updatePayPeriodsWhenSaveDate(String periodID,String startDate,String endDate,
			String payDate,String status) {
		return payPeriodsRepository.updatePayPeriodWhenSave(periodID, startDate, endDate,
				payDate, status);
	}

	public PayCode getPayCodeRelatedPeriodDates(String startDate,String endDate) {
		return payCodeRepository.getReDates(startDate, endDate);
	}
	
	public void saveDetailsOfProcessPayroll(ProcessPayroll ppr) {
		processPayrollRepository.save(ppr);
	}
	
	public PayAddDeductTypes checkPerOrNotWhenEdit(String id) {
		return addDedRepo.getAddDedTypeRelatedSdetail(id);
	}
	
	public String loadMonthlyBasic(String payCodeID) {
		return proPayMaRepo.loadMonthlyBasic(payCodeID);
	}
	
	//proccess payroll report
	public String[][] sampleReportData() {
		return empDeRepo.sampleReportData();
	}
	
	public String[][] newPayrollReport() {
		return empDeRepo.newPayrollReport();
	}
	
	//begin of salary history master functions
	public String[][] saveSalaryHistoryMaster(String payCodeID) {
		return saHisMaRepo.saveSalaryHistoryMaster(payCodeID);
	}
	
	public List<SalaryHistoryMaster> saveSaHisMa(List<SalaryHistoryMaster> list) {
		return saHisMaRepo.saveAll(list);
	}
	
	public String getMaxID() {
		if(saHisMaRepo.getShMaxID() == null) {
			return "1";
		} else {
			return saHisMaRepo.getShMaxID();
		}
	}
	
	public String[][] getProcessYearAndMonth() {
		return saHisMaRepo.getProcessYearAndMonth();
	}
	
	//end of salary history master functions
	
	//begin of salary history details functions
	public List<SalaryHistoryDetails> saveSaHiDe(List<SalaryHistoryDetails> list) {
		return saHiDeService.saveAll(list);
	}
	//end of salary history details functions
	
	public Setting loadRelatedHeader() {
		return setRepo.loadRelatedHeader();
	}
	
	//salary analize methods begin
	
	public List<PayAddDeductTypes> getAllAllowancesTypes() {
		return addDedRepo.getAllAllowanceTypes();
	}
	
	public String[][] getSalaryAnalizerTableData02() {
		return saRepo.getSalaryAnalizerTableData02();
	}
	
	public String[][] getSalaryAnalizerTableData02Header() {
		return saRepo.getSalaryAnalizerTableData02Header();
	}
	
	public String[][] salaryAnalizeReportData() {
		return saRepo.salaryAnalizeReportData();
	}
	
	public void saveSaDetails(SalaryAnalyze detail) {
		 saRepo.save(detail);
	}
	
	public List<SalaryAnalyze> getAllDetails() {
		return saRepo.findAll();
	}
	
	public void deleteAllDataOfSalaryAnalyze() {
		saRepo.deleteAll();
	}
	
	public List<SalaryAnalyze> saveAllSaDetails(List<SalaryAnalyze> list) {
		return saRepo.saveAll(list);
	}
	
	public String[][] getAllowanceTypes() {
		return addDedRepo.getAllowanceTypes();
	}
	
	public List<SalaryAnalyzeReport> saveReportData(List<SalaryAnalyzeReport> details) {
		return saReRepo.saveAll(details);
	}
	
	@SuppressWarnings("unchecked")
	public List<SalaryAnalyze> getSalaryAnalyzeTable03Data() {
		return em.createNamedStoredProcedureQuery("firstQuery").getResultList();
	}
	
	//salary analyze methods end
	
	// begin of fixed transactional details report
	
	public String[][] getFTDataRelatedEmployee(String empID) {
		return saRepo.getFTDataRelatedEmployee(empID);
	}
	
	public String[][] getAllEmpFTBodyData() {
		return saRepo.getAllEmpFTBodyData();
	}
	
	public String[][] GetDataToFTDRRelatedDepartment(String depID) {
		return saRepo.GetDataToFTDRRelatedDepartment(depID);
	}
	
	public String[][] getDataRelatedAllDepartments() {
		return saRepo.getDataRelatedAllDepartments();
	}
	
	// end of fixed transactional details report
	
}
