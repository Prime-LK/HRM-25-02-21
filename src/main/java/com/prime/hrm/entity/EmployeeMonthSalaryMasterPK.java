package com.prime.hrm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EmployeeMonthSalaryMasterPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Emp_ID", referencedColumnName = "Employee_ID")
	private Employee empID;
	
	@Column(name="Pay_Code")
	private String payCode;
	
	@Column(name="Process_Year")
	private String proYear;
	
	@Column(name="Process_Month")
	private String proMonth;

	public Employee getEmpID() {
		return empID;
	}

	public void setEmpID(Employee empID) {
		this.empID = empID;
	}

	public String getPayCode() {
		return payCode;
	}

	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}

	public String getProYear() {
		return proYear;
	}

	public void setProYear(String proYear) {
		this.proYear = proYear;
	}

	public String getProMonth() {
		return proMonth;
	}

	public void setProMonth(String proMonth) {
		this.proMonth = proMonth;
	}
	
	

}
