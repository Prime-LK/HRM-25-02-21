package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DependentPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Dependent_Type_ID", referencedColumnName = "Dependent_Type_ID")
	private DependentTypeMaster dTypeID;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	private Employee empID;

	public DependentTypeMaster getdTypeID() {
		return dTypeID;
	}

	public void setdTypeID(DependentTypeMaster dTypeID) {
		this.dTypeID = dTypeID;
	}

	public Employee getEmpID() {
		return empID;
	}

	public void setEmpID(Employee empID) {
		this.empID = empID;
	}

	
	
}
