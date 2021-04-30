package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class EmployeeContactInfoPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	Employee empID;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_Contact_Type_ID", referencedColumnName = "Employee_Contact_Type_ID")
	EmployeeContactType employeeContactType;

	public Employee getEmpID() {
		return empID;
	}

	public void setEmpID(Employee empID) {
		this.empID = empID;
	}

	public EmployeeContactType getEmployeeContactType() {
		return employeeContactType;
	}

	public void setEmployeeContactType(EmployeeContactType employeeContactType) {
		this.employeeContactType = employeeContactType;
	}
	
	
}
