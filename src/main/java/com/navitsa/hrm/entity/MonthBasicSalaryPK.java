package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class MonthBasicSalaryPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	private Employee employeeID;
	
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Pay_Period_ID", referencedColumnName = "Pay_Period_ID")
	private PayPeriods payPeriodID;

	public Employee getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Employee employeeID) {
		this.employeeID = employeeID;
	}

	public PayPeriods getPayPeriodID() {
		return payPeriodID;
	}

	public void setPayPeriodID(PayPeriods payPeriodID) {
		this.payPeriodID = payPeriodID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
