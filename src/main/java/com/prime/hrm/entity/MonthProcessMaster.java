package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="month_process_master")
public class MonthProcessMaster {

	@Id
	@Column(name="Month_Process_Master_ID")
	private String MoProMasterID;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Period_ID", referencedColumnName ="Pay_Period_ID")
	private PayPeriods payPeriod;
	
	@Column(name="Process_Date")
	private String processDate;
	
	@Column(name="User_ID")
	private String processUser;
	
	@Column(name="Employees")
	private String emps;
	
	@Column(name="Total_Basic_Salary")
	private String totalBSalary;

	@Column(name="Totoal_Addition")
	private String totAddition;
	
	@Column(name="Totoal_Deduction")
	private String totDeduction;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	public PayPeriods getPayPeriod() {
		return payPeriod;
	}

	public String getMoProMasterID() {
		return MoProMasterID;
	}

	public void setMoProMasterID(String moProMasterID) {
		MoProMasterID = moProMasterID;
	}
	
	public void setPayPeriod(PayPeriods payPeriod) {
		this.payPeriod = payPeriod;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public String getProcessUser() {
		return processUser;
	}

	public void setProcessUser(String processUser) {
		this.processUser = processUser;
	}

	public String getEmps() {
		return emps;
	}

	public void setEmps(String emps) {
		this.emps = emps;
	}

	public String getTotalBSalary() {
		return totalBSalary;
	}

	public void setTotalBSalary(String totalBSalary) {
		this.totalBSalary = totalBSalary;
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
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	public MonthProcessMaster(String moProMasterID, PayPeriods payPeriod, String processDate, String processUser,
			String emps, String totalBSalary, String totAddition, String totDeduction, CompanyMaster company) {
		MoProMasterID = moProMasterID;
		this.payPeriod = payPeriod;
		this.processDate = processDate;
		this.processUser = processUser;
		this.emps = emps;
		this.totalBSalary = totalBSalary;
		this.totAddition = totAddition;
		this.totDeduction = totDeduction;
		this.company = company;
	}

	public MonthProcessMaster() {
	}
	
	

}
