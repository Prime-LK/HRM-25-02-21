package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SalaryHistoryDetailsPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Employee_ID", referencedColumnName ="Employee_ID")
	private Employee emp;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Code_ID", referencedColumnName ="Pay_Code_ID")
	private PayCode payCode;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Add_Deduct_Type_Code", referencedColumnName ="Pay_Add_Deduct_Type_Code")
	private PayAddDeductTypes addDedType;

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public PayCode getPayCode() {
		return payCode;
	}

	public void setPayCode(PayCode payCode) {
		this.payCode = payCode;
	}

	public PayAddDeductTypes getAddDedType() {
		return addDedType;
	}

	public void setAddDedType(PayAddDeductTypes addDedType) {
		this.addDedType = addDedType;
	}
}
