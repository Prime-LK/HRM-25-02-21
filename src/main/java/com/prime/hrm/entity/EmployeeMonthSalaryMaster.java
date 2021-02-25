package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="emp_month_salary_master")
public class EmployeeMonthSalaryMaster {

	@EmbeddedId
	private EmployeeMonthSalaryMasterPK sMasterPK;
	
	@Column(name="Basic_Salary")
	private String basicSalary;
	
	@Column(name="Total_Addition")
	private String totalAddition;
	
	@Column(name="Total_Deduction")
	private String totalDeduction;
	
	@Column(name="Gross_Salary")
	private String grossSalary;
	
	@Column(name="Net_Salary")
	private String netSalary;
	
	@Column(name="Process_Date")
	private String processDate;
	
	@Column(name="Process_User_ID")
	private String processUserID;

	public EmployeeMonthSalaryMasterPK getsMasterPK() {
		return sMasterPK;
	}

	public void setsMasterPK(EmployeeMonthSalaryMasterPK sMasterPK) {
		this.sMasterPK = sMasterPK;
	}

	public String getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}

	public String getTotalAddition() {
		return totalAddition;
	}

	public void setTotalAddition(String totalAddition) {
		this.totalAddition = totalAddition;
	}

	public String getTotalDeduction() {
		return totalDeduction;
	}

	public void setTotalDeduction(String totalDeduction) {
		this.totalDeduction = totalDeduction;
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

	public String getProcessUserID() {
		return processUserID;
	}

	public void setProcessUserID(String processUserID) {
		this.processUserID = processUserID;
	}

	public EmployeeMonthSalaryMaster(EmployeeMonthSalaryMasterPK sMasterPK, String basicSalary, String totalAddition,
			String totalDeduction, String grossSalary, String netSalary, String processDate, String processUserID) {
		this.sMasterPK = sMasterPK;
		this.basicSalary = basicSalary;
		this.totalAddition = totalAddition;
		this.totalDeduction = totalDeduction;
		this.grossSalary = grossSalary;
		this.netSalary = netSalary;
		this.processDate = processDate;
		this.processUserID = processUserID;
	}

	public EmployeeMonthSalaryMaster() {
		
	}
	
	
}
