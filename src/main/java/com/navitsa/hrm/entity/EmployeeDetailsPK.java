package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EmployeeDetailsPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	private Employee empID;
	
	@Column(name ="Emp_Details_ID")
	private String detailID;

	public Employee getEmpID() {
		return empID;
	}

	public void setEmpID(Employee empID) {
		this.empID = empID;
	}

	public String getDetailID() {
		return detailID;
	}

	public void setDetailID(String detailID) {
		this.detailID = detailID;
	}
	
	

}
