package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ExtraActivityTypePK implements Serializable{

	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Extra_Activity_Type_ID", referencedColumnName = "Extra_Activity_Type_ID")
	private ExtraActivityType eType;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	private Employee empID;

	public ExtraActivityType geteType() {
		return eType;
	}

	public void seteType(ExtraActivityType eType) {
		this.eType = eType;
	}

	public Employee getEmpID() {
		return empID;
	}

	public void setEmpID(Employee empID) {
		this.empID = empID;
	}
	
	

}
