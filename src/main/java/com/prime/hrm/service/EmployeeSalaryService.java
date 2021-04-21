package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeDetails;
import com.prime.hrm.entity.EmployeeMonthSalaryDetails;
import com.prime.hrm.entity.EmployeeMonthSalaryMaster;
import com.prime.hrm.entity.EmployeeSalaryDetail;
import com.prime.hrm.entity.EmployeeSalaryMaster;
import com.prime.hrm.entity.PayAddDeductTypes;
import com.prime.hrm.entity.PayCode;
import com.prime.hrm.entity.PayPeriods;
import com.prime.hrm.entity.Setting;
import com.prime.hrm.repository.EmployeeDetailsRepository;
import com.prime.hrm.repository.EmployeeMonthSalaryDetailsRepository;
import com.prime.hrm.repository.EmployeeMonthSalaryMasterRepository;
import com.prime.hrm.repository.EmployeeSalaryDetailRepository;
import com.prime.hrm.repository.EmployeeSalaryMasterReporsitory;
import com.prime.hrm.repository.PayAddDeductTypesRepository;
import com.prime.hrm.repository.PayCodeRepository;
import com.prime.hrm.repository.PayPeriodsRepository;
import com.prime.hrm.repository.SettingRepository;

@Service
public class EmployeeSalaryService {

	@Autowired
	private EmployeeSalaryMasterReporsitory empSaRepo;
	
	@Autowired
	private EmployeeMonthSalaryMasterRepository empMoSaRepo;
	
	@Autowired
	private EmployeeMonthSalaryDetailsRepository empMoDeSaRepo;
	
	@Autowired
	private PayAddDeductTypesRepository addDedRepo;
	
	@Autowired
	private EmployeeSalaryDetailRepository employeeSalaryDetailRepo;
	
	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepo;
	
	@Autowired
	private SettingRepository setRepo;
	
	@Autowired
	private PayPeriodsRepository periodRepo;
	
	@Autowired
	private PayCodeRepository codeRepo;
	
	@Autowired
	private EmployeeDetailsRepository empDeRepo;
	

	//emp salary master methods
	public String getMaxID() {
		if(empSaRepo.getMaxID()== null) {
			return "1";
		} else {
			return empSaRepo.getMaxID();
		}
	}
	
	public EmployeeSalaryMaster saveEmpSalary(EmployeeSalaryMaster empSalary) {
		return empSaRepo.save(empSalary);
	}
	
	public List<EmployeeSalaryMaster> loadGrid(String empID) {
		return empSaRepo.loadGrid(empID);
	}
	
	public List<EmployeeSalaryMaster> getAllEmpSa() {
		return (List<EmployeeSalaryMaster>) empSaRepo.findAll();
	}
	
	public EmployeeSalaryMaster updateRecord(String empID, String processID) {
		return empSaRepo.updateRecord(empID, processID);
	}
	
	
	
	//emp month salary master methods-----------------------------
	
	public String getMoMaxID() {
		if(empMoSaRepo.getMaxID() == null) {
			return "1";
		} else {
			return empMoSaRepo.getMaxID();
		}
	}
	
	public EmployeeMonthSalaryMaster saveEmpMoSaDetails(EmployeeMonthSalaryMaster m) {
		return empMoSaRepo.save(m);
	}
	
	public List<EmployeeMonthSalaryMaster> getAllDetails() {
		return (List<EmployeeMonthSalaryMaster>) empMoSaRepo.findAll();
	}
	
	//emp month salary details
	public PayAddDeductTypes getAddDedTypeRelatedSdetail(String deductTypeCode) {
		return addDedRepo.getAddDedTypeRelatedSdetail(deductTypeCode);
	}

	public List<EmployeeMonthSalaryDetails> saveListEmpMoSaDe(List<EmployeeMonthSalaryDetails> list) {
		return (List<EmployeeMonthSalaryDetails>) empMoDeSaRepo.saveAll(list);
	}

	//save employee salary details data
	public EmployeeMonthSalaryDetails saveEmpMoDe(EmployeeMonthSalaryDetails d) {
		return empMoDeSaRepo.save(d);
	}

	public EmployeeSalaryDetail saveEmpSalaryDetails(EmployeeSalaryDetail employeeSalaryDetail) {
		return employeeSalaryDetailRepo.save(employeeSalaryDetail);
	}

	public List<EmployeeMonthSalaryDetails> getAllEmpMoSaDetails() {
		return (List<EmployeeMonthSalaryDetails>) empMoDeSaRepo.findAll();
	}

	//load employee salary details according to empID
	public List<EmployeeSalaryDetail> loadrelevantsalaryDetails(String empID) {
		return employeeSalaryDetailRepo.loadempsalaryDetails(empID);
	}

	//update employee Salary details according to empID and 
	public EmployeeSalaryDetail updateEmpDetailsSalary(String empID, String deductTypeCode) {
		return employeeSalaryDetailRepo.updateEmpsalaryDetails(empID, deductTypeCode);
	}

	
	public List<Employee> saveListAllEmp() {
		return empMoDeSaRepo.getEmpsToEmpMoSaDetails();
	}
	

	//loadSelectedEmp
	public Employee loadSelectedEmp(String empID) {
		return empMoDeSaRepo.loadSelectedEmp(empID);
	}

	//load employee based on department to employee salary details jsp
	public List<EmployeeDetails> loadrelevantempTosalaryDetails(String depID) {
		return employeeDetailsRepo.filterEmpbasedONDep(depID);
		}

	//save list of empid on employee salary details
	public List<EmployeeSalaryDetail> savelistOFEmpSalaryDetails(List<EmployeeSalaryDetail> employeeSalaryDetail) {
		return (List<EmployeeSalaryDetail>) employeeSalaryDetailRepo.saveAll(employeeSalaryDetail);
	}
	
	//load employee based on location to employee salary details jsp
	public List<EmployeeDetails> loadrelevantempbasedOnLocationTosalaryDetails(String loid) {
		return employeeDetailsRepo.filterEmpbasedONLocation(loid);
		}
	

	//get employee based on category to employee salary details
	public List<EmployeeDetails> loadrelevantempbasedOnCategoryTosalaryDetails(String catgoryID) {
		return employeeDetailsRepo.filterEmpbasedONCategory(catgoryID);
		}
	
	//get employee based on category to employee salary details
	public List<EmployeeDetails> loadrelevantempbasedOnTypesTosalaryDetails(String tid) {
		return employeeDetailsRepo.filterEmpbasedONtypes(tid);
	}
	
	//get employee to employee salary details
	public List<EmployeeDetails> filterEmployee(String empID) {
		return employeeDetailsRepo.filterEmployee(empID);
	}
	
	//get all employee to emplyee salary details
	public List<EmployeeDetails> loadallEmployeeTosalaryDetails() {
		return employeeDetailsRepo.loadAllEmployee();
	}
	
	public List<EmployeeSalaryDetail> saveListOfEmpReDep(String depID, String deductTypeCode) {
		return employeeSalaryDetailRepo.getEmpToEmpMoSaDe(depID, deductTypeCode);
	}
	
	public List<EmployeeSalaryDetail> saveListOfEmpLoc(String loid, String deductTypeCode) {
		return employeeSalaryDetailRepo.getEmpRelatedLoc(loid, deductTypeCode);
	}
	
	public List<EmployeeSalaryDetail> saveListOfEmpReCat(String catgoryID, String deductTypeCode) {
		return employeeSalaryDetailRepo.getEmpRelatedCat(catgoryID, deductTypeCode);
	}
	
	public List<EmployeeSalaryDetail> saveListOfEmpReType(String tid, String deductTypeCode) {
		return employeeSalaryDetailRepo.getEmpRelatedType(tid, deductTypeCode);
	}
	
	public List<PayAddDeductTypes> getVariableType() {
		return addDedRepo.getVariableType();
	}

	//get  salary details based on depID
	public String[][] loadSalarydetailsbasedondepID( String depID , String deductTypeCode) {
		return employeeSalaryDetailRepo.getSalarydetailsbasedondepID(depID , deductTypeCode);
	}
	
	//get the salary details based on locationID
	public String[][] loadSalarydetailsbasedonloid( String loid , String deductTypeCode) {
		return employeeSalaryDetailRepo.getSalarydetailsbasedonLocation(loid , deductTypeCode);
	}
	
	//get the salary details based on employee category
	public String[][] loadSalarydetailsbasedoncategoryid( String catgoryID , String deductTypeCode) {
		return employeeSalaryDetailRepo.getSalarydetailsbasedoncatgoryID(catgoryID ,deductTypeCode );
	}
	
	//get the salary details based on employee type
	public String[][] loadSalarydetailsbasedontypeid( String tid ,  String deductTypeCode) {
		return employeeSalaryDetailRepo.getSalarydetailsbasedontypeID(tid , deductTypeCode);
	}
	
	//get salary details based on deductTypeCode
	public String[][] loadSalarydetailsbasedondeductTypeCode( String deductTypeCode) {
		return employeeSalaryDetailRepo.getSalarydetailsbasedonPay_Add_Deduct_Type_Code(deductTypeCode);
	}
	
	//get all employees 
	public String[][] loadSalarydetails() {
		return employeeSalaryDetailRepo.getAllemployeesTosalaryDetailsREPORT();
	}
	
	//loadrelateddateTypes
	public Setting loadRelatedHeader() {
		return setRepo.loadRelatedHeader();
	}
	
	//disable or enable paycode
	public Setting showOrDisablePayCode() {
		return setRepo.showOrDisablePayCode();
	}
	
	public String payCodeForAG() {
		if(codeRepo.payCodeForAG() == null) {
			return "1";
		} else {
			return codeRepo.payCodeForAG();
		}
	}
	
	//getonlyDates
	public PayPeriods getDates(String startDate,String endDate) {
		return periodRepo.getDates(startDate, endDate);
	}
	//2
	public PayCode getDates2(String startDate, String endDate) {
		return codeRepo.getDates2(startDate, endDate);
	}	
	
	//get all active and inactive employees tosalary details based on department
	public String[][] loadSalarydetailsbasedondepIDActiveandInactive( String depID , String deductTypeCode) {
		return employeeSalaryDetailRepo.getSalarydetailsbasedondepIDinactive(depID , deductTypeCode);
	}
	//get all employees to salary details based on locations
	public String[][] loadSalarydetailsbasedonloidall( String loid , String deductTypeCode) {
		return employeeSalaryDetailRepo.getSalarydetailsbasedonLocationall(loid, deductTypeCode);
	}
	
	//get all employess to salary details based on category
	public String[][] loadSalarydetailsbasedonallcategoryid( String catgoryID , String deductTypeCode) {
		return employeeSalaryDetailRepo.getSalarydetailsbasedonallcatgoryID(catgoryID, deductTypeCode);
	}
	//get all employee details based on type
	public String[][] loadallSalarydetailsbasedontypeid( String tid ,  String deductTypeCode) {
		return employeeSalaryDetailRepo.getallSalarydetailsbasedontypeID(tid , deductTypeCode);
	}	
	
	//update emp sa details
	public List<EmployeeMonthSalaryDetails> updateListDetails(String deductTypeCode, String payCodeID) {
		return empMoDeSaRepo.updateListDetails(deductTypeCode, payCodeID);
	}
	
	public List<EmployeeMonthSalaryDetails> getAllMoSaDetails() {
		return (List<EmployeeMonthSalaryDetails>) empMoDeSaRepo.findAll();
	}
	
	public List<EmployeeSalaryDetail> getAllEmpSaDe() {
		return (List<EmployeeSalaryDetail>) employeeSalaryDetailRepo.findAll();
	}
	
}
