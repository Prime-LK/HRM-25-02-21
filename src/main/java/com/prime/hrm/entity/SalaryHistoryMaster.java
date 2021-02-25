package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="SalaryHistoryMaster")
@Table(name="emp_salary_history_master")
public class SalaryHistoryMaster {

	@EmbeddedId
	private SalaryHistoryMasterPK saHisMaPK;
	
	@Column(name="Employees")
	private String emp;
	
	@Column(name="Process_User_ID")
	private String processUserID;
	
	@Column(name="Process_Year")
	private String processYear;
	
	@Column(name="Process_Month")
	private String processMonth;
	
	@Column(name="Basic_Salay")
	private String basicSalary;
	
	@Column(name="Total_Addition")
	private String totAddition;
	
	@Column(name="Total_Deduction")
	private String totDeduction;
	
	@Column(name="Gross_Salary")
	private String grossSalary;
	
	@Column(name="Net_Salary")
	private String netSalary;
	
	@Column(name="Process_Date")
	private String processDate;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public SalaryHistoryMaster(SalaryHistoryMasterPK saHisMaPK, String processUserID, String processYear,
			String processMonth, String basicSalary, String totAddition, String totDeduction, String grossSalary,
			String netSalary, String processDate, CompanyMaster company) {
		this.saHisMaPK = saHisMaPK;
		this.processUserID = processUserID;
		this.processYear = processYear;
		this.processMonth = processMonth;
		this.basicSalary = basicSalary;
		this.totAddition = totAddition;
		this.totDeduction = totDeduction;
		this.grossSalary = grossSalary;
		this.netSalary = netSalary;
		this.processDate = processDate;
		this.company = company;
	}

	public SalaryHistoryMaster() {

	}

	public SalaryHistoryMasterPK getSaHisMaPK() {
		return saHisMaPK;
	}

	public void setSaHisMaPK(SalaryHistoryMasterPK saHisMaPK) {
		this.saHisMaPK = saHisMaPK;
	}

	public String getProcessUserID() {
		return processUserID;
	}

	public void setProcessUserID(String processUserID) {
		this.processUserID = processUserID;
	}

	public String getProcessYear() {
		return processYear;
	}

	public void setProcessYear(String processYear) {
		this.processYear = processYear;
	}

	public String getProcessMonth() {
		return processMonth;
	}

	public void setProcessMonth(String processMonth) {
		this.processMonth = processMonth;
	}

	public String getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}

	public String getTotAddition() {
		return totAddition;
	}

	public void setTotAddition(String totAddition) {
		this.totAddition = totAddition;
	}

	public String getTotDeduction() {
		return totDeduction;
	}

	public void setTotDeduction(String totDeduction) {
		this.totDeduction = totDeduction;
	}

	public String getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(String grossSalary) {
		this.grossSalary = grossSalary;
	}

	public String getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(String netSalary) {
		this.netSalary = netSalary;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public String getEmp() {
		return emp;
	}

	public void setEmp(String emp) {
		this.emp = emp;
	}
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
}
