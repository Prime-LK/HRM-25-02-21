package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Embeddable
public class EmployeeIDPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	Employee empID;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Emp_ID_DocType_ID", referencedColumnName = "Emp_ID_DocType_ID")
	EmployeeIdDocument employeeIdDocument;
	
	@Column(name = "Doc_ID")
	String docid;
	
	public Employee getEmpID() {
		return empID;
	}

	public void setEmpID(Employee empID) {
		this.empID = empID;
	}

	public EmployeeIdDocument getEmployeeIdDocument() {
		return employeeIdDocument;
	}

	public void setEmployeeIdDocument(EmployeeIdDocument employeeIdDocument) {
		this.employeeIdDocument = employeeIdDocument;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}
	
	
	
	
}
