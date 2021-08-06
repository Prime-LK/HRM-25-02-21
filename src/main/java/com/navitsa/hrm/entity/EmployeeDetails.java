package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "employee_details")
public class EmployeeDetails {

	@EmbeddedId
	private EmployeeDetailsPK detailsPK;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Emp_Category_ID", referencedColumnName = "Emp_Category_ID")
	private EmployeeCategory category;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Emp_Type_ID", referencedColumnName = "Emp_Type_ID")
	private EmployeeType empType;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Salary_Range_ID", referencedColumnName = "Salary_Range_ID")
	private SalaryRange salaryRange;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Salary_Grade_ID", referencedColumnName = "Salary_Grade_ID")
	private SalaryGrade salaryGrade;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Designation_ID", referencedColumnName = "Designation_ID")
	private DesignationMaster designation;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Job_Profile_ID", referencedColumnName = "Job_Profile_ID")
	private JobProfileMaster jobProfile;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Department_ID", referencedColumnName = "Department_ID")
	private DepartmentMaster department;

	@Column(name = "Joined_Date")
	private String joinedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "Location", referencedColumnName = "Location_ID")
	private LocationMaster location;

	@Column(name = "Resign_Date")
	private String resignDate;

	@Column(name = "Status")
	private String status;

	@Column(name = "Direct_Reporting")
	private String reporting;

	@Column(name = "basicSalary")
	private int basicSalary;

	@Column(name = "EPF_No")
	private String epfNo;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "shift_id", referencedColumnName = "shift_id")
	private ShiftMaster shiftmaster;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Company_ID", referencedColumnName = "Company_ID")
	private CompanyMaster company;

	public EmployeeDetailsPK getDetailsPK() {
		return detailsPK;
	}

	public void setDetailsPK(EmployeeDetailsPK detailsPK) {
		this.detailsPK = detailsPK;
	}

	public EmployeeCategory getCategory() {
		return category;
	}

	public void setCategory(EmployeeCategory category) {
		this.category = category;
	}

	public EmployeeType getEmpType() {
		return empType;
	}

	public void setEmpType(EmployeeType empType) {
		this.empType = empType;
	}

	public SalaryRange getSalaryRange() {
		return salaryRange;
	}

	public void setSalaryRange(SalaryRange salaryRange) {
		this.salaryRange = salaryRange;
	}

	public SalaryGrade getSalaryGrade() {
		return salaryGrade;
	}

	public void setSalaryGrade(SalaryGrade salaryGrade) {
		this.salaryGrade = salaryGrade;
	}

	public DesignationMaster getDesignation() {
		return designation;
	}

	public void setDesignation(DesignationMaster designation) {
		this.designation = designation;
	}

	public JobProfileMaster getJobProfile() {
		return jobProfile;
	}

	public void setJobProfile(JobProfileMaster jobProfile) {
		this.jobProfile = jobProfile;
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}

	public LocationMaster getLocation() {
		return location;
	}

	public void setLocation(LocationMaster location) {
		this.location = location;
	}

	public DepartmentMaster getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentMaster department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReporting() {
		return reporting;
	}

	public void setReporting(String reporting) {
		this.reporting = reporting;
	}

	public String getResignDate() {
		return resignDate;
	}

	public void setResignDate(String resignDate) {
		this.resignDate = resignDate;
	}

	public int getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(int basicSalary) {
		this.basicSalary = basicSalary;
	}

	public String getEpfNo() {
		return epfNo;
	}

	public void setEpfNo(String epfNo) {
		this.epfNo = epfNo;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public ShiftMaster getShiftmaster() {
		return shiftmaster;
	}

	public void setShiftmaster(ShiftMaster shiftmaster) {
		this.shiftmaster = shiftmaster;
	}

	public EmployeeDetails(EmployeeDetailsPK detailsPK, EmployeeCategory category, EmployeeType empType,
			SalaryRange salaryRange, SalaryGrade salaryGrade, DesignationMaster designation,
			JobProfileMaster jobProfile, String joinedDate, String resignDate, LocationMaster location,
			DepartmentMaster department, String status, String reporting, int basicSalary, String epfNo,
			ShiftMaster shiftmaster, CompanyMaster company) {

		this.detailsPK = detailsPK;
		this.category = category;
		this.empType = empType;
		this.salaryRange = salaryRange;
		this.salaryGrade = salaryGrade;
		this.designation = designation;
		this.jobProfile = jobProfile;
		this.joinedDate = joinedDate;
		this.resignDate = resignDate;
		this.location = location;
		this.department = department;
		this.status = status;
		this.reporting = reporting;
		this.basicSalary = basicSalary;
		this.epfNo = epfNo;
		this.shiftmaster = shiftmaster;
		this.company = company;
	}

	public EmployeeDetails() {

	}
}
