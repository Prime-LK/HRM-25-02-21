package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ProcessPayrollDetailsPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Pay_Add_Deduct_Type_Code", referencedColumnName = "Pay_Add_Deduct_Type_Code")
	private PayAddDeductTypes payType;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Code_ID", referencedColumnName ="Pay_Code_ID")
	private PayCode payCodeid;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Pay_Period_ID", referencedColumnName = "Pay_Period_ID")
	private PayPeriods payPeriod;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	private Employee empID;
	
	public PayAddDeductTypes getPayType() {
		return payType;
	}

	public void setPayType(PayAddDeductTypes payType) {
		this.payType = payType;
	}

	public PayCode getPayCodeid() {
		return payCodeid;
	}

	public void setPayCodeid(PayCode payCodeid) {
		this.payCodeid = payCodeid;
	}

	public PayPeriods getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(PayPeriods payPeriod) {
		this.payPeriod = payPeriod;
	}
	
	public Employee getEmpID() {
		return empID;
	}

	public void setEmpID(Employee empID) {
		this.empID = empID;
	}
	
	public ProcessPayrollDetailsPK(PayAddDeductTypes payType, PayCode payCodeid, PayPeriods payPeriod, Employee empID) {
		this.payType = payType;
		this.payCodeid = payCodeid;
		this.payPeriod = payPeriod;
		this.empID = empID;
	}

	public ProcessPayrollDetailsPK() {
	}
	
	
	

}
