package com.prime.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AddressPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Emp_AddField_ID", referencedColumnName = "Emp_AddField_ID")
	private EmployeeAddressField field;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	private Employee empID;

	public EmployeeAddressField getField() {
		return field;
	}

	public void setField(EmployeeAddressField field) {
		this.field = field;
	}

	public Employee getEmpID() {
		return empID;
	}

	public void setEmpID(Employee empID) {
		this.empID = empID;
	}
	
	
}
