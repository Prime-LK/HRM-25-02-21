package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.navitsa.hrm.entity.DesignationMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeCategory;
import com.navitsa.hrm.entity.EmployeeContactInfo;
import com.navitsa.hrm.entity.EmployeeContactType;
import com.navitsa.hrm.entity.EmployeeDetails;
import com.navitsa.hrm.entity.EmployeeType;
import com.navitsa.hrm.entity.JobProfileMaster;
import com.navitsa.hrm.entity.NationalityMaster;
import com.navitsa.hrm.entity.ReligionMaster;
import com.navitsa.hrm.entity.SalaryRange;
import com.navitsa.hrm.repository.DesignationMasterRepository;
import com.navitsa.hrm.repository.EmployeeCategoryRepository;
import com.navitsa.hrm.repository.EmployeeContactInfoRepository;
import com.navitsa.hrm.repository.EmployeeContactTypeRepository;
import com.navitsa.hrm.repository.EmployeeDetailsRepository;
import com.navitsa.hrm.repository.EmployeeRepository;
import com.navitsa.hrm.repository.EmployeeTypeRepository;
import com.navitsa.hrm.repository.JobProfileMasterRepository;
import com.navitsa.hrm.repository.NationalityMasterRepository;
import com.navitsa.hrm.repository.ReligionMasterRepository;
import com.navitsa.hrm.repository.SalaryRangeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private EmployeeCategoryRepository catRepo;

	@Autowired
	private NationalityMasterRepository naRepo;

	@Autowired
	private SalaryRangeRepository rangeRepo;

	@Autowired
	private ReligionMasterRepository religionRepo;

	@Autowired
	private DesignationMasterRepository designationRepo;

	@Autowired
	private JobProfileMasterRepository profileRepo;

	@Autowired
	private EmployeeContactTypeRepository cTypeRepo;

	@Autowired
	private EmployeeContactInfoRepository employeeContactInfoRepo;

	@Autowired
	private EmployeeTypeRepository empTypeRepo;

	@Autowired
	private EmployeeDetailsRepository empDeRepo;

	// employee master-----------------------------
//	public List<Employee> findEmp(String name,String password) {
//		return empRepo.findUser(name,password);
//	}

	public List<Employee> findEmp(String name, String password, String comID) {
		return empRepo.findUser(name, password, comID);
	}

	public List<EmployeeCategory> getAllCategories() {
		return (List<EmployeeCategory>) catRepo.findAll();
	}

	public List<SalaryRange> getAllRanges() {
		return (List<SalaryRange>) rangeRepo.findAll();
	}

	public List<NationalityMaster> getAllNationalities() {
		return (List<NationalityMaster>) naRepo.findAll();
	}

	public List<ReligionMaster> getAllRiligions() {
		return (List<ReligionMaster>) religionRepo.findAll();
	}

	public List<DesignationMaster> getAllDesignations() {
		return (List<DesignationMaster>) designationRepo.findAll();
	}

	public List<JobProfileMaster> getAllProfiles() {
		return (List<JobProfileMaster>) profileRepo.findAll();
	}

	public List<EmployeeType> getAllTypes() {
		return (List<EmployeeType>) empTypeRepo.findAll();
	}

	public List<Employee> getAllEmp() {
		return (List<Employee>) empRepo.findAll();
	}

	public void saveEmp(Employee emp) {
		empRepo.save(emp);
	}

	public Employee getEmp(String id) {
		return empRepo.findById(id).get();
	}

	// employee contact type-------------------------------------

	public String maxCTypeID() {
		if (cTypeRepo.maxcTypeID() == null) {
			return "1";
		} else {
			return cTypeRepo.maxcTypeID();
		}
	}

	public void saveCType(EmployeeContactType cType) {
		cTypeRepo.save(cType);
	}

	public EmployeeContactType getCType(String cTypeID) {
		return cTypeRepo.findById(cTypeID).get();
	}

	public List<EmployeeContactType> getAllCTypes() {
		return (List<EmployeeContactType>) cTypeRepo.findAll();
	}

	// employee Contact Type Info----------
	// save
	public void saveCinfo(EmployeeContactInfo cinfo) {
		employeeContactInfoRepo.save(cinfo);
	}

	// get saved data
	public List<EmployeeContactInfo> getAllsavedCinfo() {
		return (List<EmployeeContactInfo>) employeeContactInfoRepo.findAll();
	}

	// edit employee Contact infor
	public EmployeeContactInfo getEmployeeContactInfoDataByID(String eid, String cTypeID) {
		return employeeContactInfoRepo.setEmployeeContactDetails(eid, cTypeID);
	}

	// getEmps
	public List<EmployeeContactInfo> findEmps(String empID) {
		return employeeContactInfoRepo.getEmps(empID);
	}

	// get userImage to loging page
	public List<Employee> getlogingImg(String name) {
		return (List<Employee>) empRepo.findImg(name);

	}

	// get emp empMoDeTable
	public List<Employee> loadEmpToTable(String empID) {
		return empRepo.loadTableToEmp(empID);
	}

	public List<EmployeeDetails> filterRelatedData(String depID) {
		return empDeRepo.filterEmp(depID);
	}

	// employee details
	// operation---------------------------------------------------------------------------
	// save
	public void saveEmplDetails(EmployeeDetails empDetails) {
		empDeRepo.save(empDetails);
	}

	// getAllDetails
	public List<EmployeeDetails> getAllEmpDetails() {
		return (List<EmployeeDetails>) empDeRepo.findAll();
	}

	// getID
	public String getID() {
		if (empDeRepo.maxDetailsID() == null) {
			return "1";
		} else {
			return empDeRepo.maxDetailsID();
		}
	}

	// update data
	public EmployeeDetails updateDetails(String empID) {
		return empDeRepo.editDetails(empID);
	}

	// get emps related location
	public List<EmployeeDetails> getEmpRelatedLocation(String loid) {
		return empDeRepo.getEMpRelatedLocation(loid);
	}

	// get emps related type
	public List<EmployeeDetails> getEmpRelatedType(String tid) {
		return empDeRepo.getEmpRelatedType(tid);
	}

	// get emps related category
	public List<EmployeeDetails> getEmpRelatedCategory(String catgoryID) {
		return empDeRepo.getEmpRelatedCategory(catgoryID);
	}

	public List<EmployeeDetails> filterEmpRelatedDep(String depID) {
		return empDeRepo.passEmpRelatedDep(depID);
	}

	// employee census report------------------------------------
	public String[][] getData() {
		return empDeRepo.getReportData();
	}

	// empMonth salary details load emps
	public String[][] getEmpsToEmpMoSaDetails() {
		return empRepo.getEmpsToEmpMoSaDetails();
	}

	public String[][] filterEemployee(String depID) {
		return empDeRepo.getCensusReportDetails(depID);
	}

	// get data for jasper employee contact report
	public String[][] getEmpContatReportData() {
		return empRepo.getempContactSummaryReportData();

	}

	// employee report
	public String[][] getEmployeeReportData(String empID) {
		return empRepo.getEmployeeReportData(empID);
	}

	// get data to filtered data
	public String[][] getEmployeefilteredcontactData(String depID) {
		return empDeRepo.filterContact(depID);
	}

	// get filtered employee
	public List<EmployeeDetails> getEmployeefilteredData(String depID) {
		return empDeRepo.filteremp(depID);
	}

	public List<EmployeeDetails> filterRelatedempbasedonjoinedDate(String joinedDate, String joinedDate2) {
		return empDeRepo.filterEmpdoj(joinedDate, joinedDate2);
	}

	// filtered cencues report based on joined date
	public String[][] getCensusReportDatabyfilteringemployeebasedonjoinedDate(String joinedDate, String joinedDate2) {
		return empDeRepo.filterEmpdojforReport(joinedDate, joinedDate2);
	}

	// filtered cencues jsp based on resignDate
	public List<EmployeeDetails> filterRelatedempbasedonresignDate(String resignDate, String resignDate2) {
		return empDeRepo.filterEmpdateofresign(resignDate, resignDate2);

	}

	// filtered cencues report based on joined date
	public String[][] getCensusReportDatabyfilteringemployeebasedonresignDate(String resignDate, String resignDate2) {
		return empDeRepo.filterEmpdateOfResignDate(resignDate, resignDate2);
	}

	public List<Employee> getSearchDetails() {
		return empRepo.getSearchDetails();
	}

	public Employee updateDetailsUsingEmpName(String name) {
		return empRepo.updateDetailsUsingEmpName(name);
	}

	public List<EmployeeDetails> getAllEmpDeData() {
		return (List<EmployeeDetails>) empDeRepo.findAll();
	}

	public List<EmployeeDetails> filterEmployeesByDepartmentAndCompany(String departmentId, String companyId) {
		return empDeRepo.filterEmployeesByDepartmentAndCompany(departmentId, companyId);
	}

	public List<DesignationMaster> getAllDesignationsByCompany(String companyId) {
		return (List<DesignationMaster>) designationRepo.getAllDesignationsByCompany(companyId);
	}

	public Employee getEmployeeByCompany(String employeeId, String companyId) {
		return empRepo.getEmployeeByCompany(employeeId, companyId);
	}
	public List<EmployeeType> getAllTypesByCompanny(String companyId) {
		return empTypeRepo.getAllTypesByCompanny(companyId);
	}
	
	public List<Employee> getEmployeeListrpt(String dep,String dis,String emptyp, String empid,String empcat,String religion, String civista,String companyid){
		return empRepo.getEmployeeListrpt(dep,dis,emptyp,empid,empcat,religion,civista,companyid);
	}
	public String[][] getEmployeeListrptPrivew(String dep,String dis,String emptyp, String empid,String companyid){
		return empDeRepo.getEmployeeListrptPrivew(dep,dis,emptyp,empid,companyid);
	}
	public List<EmployeeCategory> getAllCategoriesBycompanyID(String companyId) {
		return catRepo.getAllCategoriesBycompanyID(companyId);
	}
	public List<ReligionMaster> getAllReligionBycompanyID(String companyId) {
		return religionRepo.getAllReligionBycompanyID(companyId);
	}
	public String[][] getEmployeeListrptPrivewbyreligion(String dep,String dis,String emptyp, String empid,String empcat, String religion, String civista,String companyid){
		return empDeRepo.getEmployeeListrptPrivewbyreligion(dep,dis,emptyp,empid,empcat,religion,civista,companyid);
	}
	
	public EmployeeDetails getEmployeeDetailsByEmployeeID(String employeeID) {
		return empDeRepo.getEmployeeDetailsByEmployeeID(employeeID);
	}
	
	public EmployeeDetails findEmployeeDetailsByEmployeeIdAndCompany(String employeeId, String companyId) {
		return empDeRepo.findEmployeeDetailsByEmployeeIdAndCompany(employeeId, companyId);
	}
}

