package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EmployeeSalaryDetailPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	private Employee empID;
	
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Pay_Add_Deduct_Type_Code", referencedColumnName = "Pay_Add_Deduct_Type_Code")
	private PayAddDeductTypes payAddeductTypes;


	public Employee getEmpID() {
		return empID;
	}


	public void setEmpID(Employee empID) {
		this.empID = empID;
	}


	public PayAddDeductTypes getPayAddeductTypes() {
		return payAddeductTypes;
	}


	public void setPayAddeductTypes(PayAddDeductTypes payAddeductTypes) {
		this.payAddeductTypes = payAddeductTypes;
	}
	
	
	

}
