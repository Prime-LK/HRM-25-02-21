package com.navitsa.hrm.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.PayAddDeductTypes;
import com.navitsa.hrm.entity.PayCode;
import com.navitsa.hrm.entity.PayPeriods;
import com.navitsa.hrm.entity.ProcessPayroll;
import com.navitsa.hrm.entity.SalaryAnalyze;
import com.navitsa.hrm.entity.SalaryHistoryDetails;
import com.navitsa.hrm.entity.SalaryHistoryMaster;
import com.navitsa.hrm.entity.Setting;
import com.navitsa.hrm.repository.EmployeeDetailsRepository;
import com.navitsa.hrm.repository.PayAddDeductTypesRepository;
import com.navitsa.hrm.repository.PayCodeRepository;
import com.navitsa.hrm.repository.PayPeriodsRepository;
import com.navitsa.hrm.repository.ProcessPayrollDetailsRepository;
import com.navitsa.hrm.repository.ProcessPayrollMasterRepository;
import com.navitsa.hrm.repository.ProcessPayrollRepository;
import com.navitsa.hrm.repository.SalaryAnalyzeRepository;
import com.navitsa.hrm.repository.SalaryHistoryDetailsRepository;
import com.navitsa.hrm.repository.SalaryHistoryMasterRepository;
import com.navitsa.hrm.repository.SettingRepository;

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
	private ProcessPayrollDetailsRepository saReRepo;
	
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
	
	//salary analyze methods begin	
	public List<PayAddDeductTypes> getAllAllowancesTypes() {
		return addDedRepo.getAllAllowanceTypes();
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
	
	public String getMaxSaID() {
		if(saRepo.getMaxSaID() == null) {
			return "1";
		} else {
			return saRepo.getMaxSaID();
		}
	} 
	
	public String[][] getListOfAllowances() {
		return saRepo.getListOfAllowances();
	}
	
	public String[][] getSalaryAnalyzeTableHeaderData() {
		return saRepo.getSalaryAnalyzeTableHeaderData();
	}
	
	public String[][] getSalaryAnalyzeTableBodyData() {
		return saRepo.getSalaryAnalyzeTableBodyData();
	} 
	
	// begin of fixed transactional details report
	public String[][] getFTDataRelatedEmployee(String empID,String comID) {
		return saRepo.getFTDataRelatedEmployee(empID,comID);
	}
	
	public String[][] getAllEmpFTBodyData(String comID) {
		return saRepo.getAllEmpFTBodyData(comID);
	}
	
	public String[][] GetDataToFTDRRelatedDepartment(String depID,String comID) {
		return saRepo.GetDataToFTDRRelatedDepartment(depID,comID);
	}
	
	public String[][] getDataRelatedAllDepartments(String comID) {
		return saRepo.getDataRelatedAllDepartments(comID);
	}
	
	// end of fixed transactional details report
	
}
