package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.data.annotation.Id;

@Embeddable
public class LeaveOnLeavePK implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EmpID")
	private String empID;


	public String getEmpID() {
		return empID;
	}


	public void setEmpID(String empID) {
		this.empID = empID;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
