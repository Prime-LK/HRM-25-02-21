package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="process_payroll_master")
public class ProcessPayrollMaster {

	@Id
	@Column(name="Process_Payroll_Master_ID")
	private String proPayrollMaID;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Period_ID", referencedColumnName ="Pay_Period_ID")
	private PayPeriods payPeriod;
	
	@Column(name="Start_Date")
	private String startDate;
	
	@Column(name="End_Date")
	private String endDate;
	
	@Column(name="Employees")
	private String emps;
	
	@Column(name="Basic_Salary")
	private String basicSalary;
	
	@Column(name="Month_Basic")
	private String monthlyBasic;
	
	@Column(name="Gross_Month_Salary")
	private String grossMonth;
	
	@Column(name="Net_Month_Salary")
	private String netMonth;

	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	public String getProPayrollMaID() {
		return proPayrollMaID;
	}

	public void setProPayrollMaID(String proPayrollMaID) {
		this.proPayrollMaID = proPayrollMaID;
	}

	public PayPeriods getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(PayPeriods payPeriod) {
		this.payPeriod = payPeriod;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEmps() {
		return emps;
	}

	public void setEmps(String emps) {
		this.emps = emps;
	}

	public String getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}

	public String getMonthlyBasic() {
		return monthlyBasic;
	}

	public void setMonthlyBasic(String monthlyBasic) {
		this.monthlyBasic = monthlyBasic;
	}

	public String getGrossMonth() {
		return grossMonth;
	}

	public void setGrossMonth(String grossMonth) {
		this.grossMonth = grossMonth;
	}

	public String getNetMonth() {
		return netMonth;
	}

	public void setNetMonth(String netMonth) {
		this.netMonth = netMonth;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	public ProcessPayrollMaster(String proPayrollMaID, PayPeriods payPeriod, String startDate, String endDate,
			String emps, String basicSalary, String monthlyBasic, String grossMonth, String netMonth
			,CompanyMaster company) {
		this.proPayrollMaID = proPayrollMaID;
		this.payPeriod = payPeriod;
		this.startDate = startDate;
		this.endDate = endDate;
		this.emps = emps;
		this.basicSalary = basicSalary;
		this.monthlyBasic = monthlyBasic;
		this.grossMonth = grossMonth;
		this.netMonth = netMonth;
		this.company = company;
	}

	public ProcessPayrollMaster() {
	
	}
	
	
}
