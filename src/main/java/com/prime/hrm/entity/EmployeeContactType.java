package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="employee_contact_types")
public class EmployeeContactType {
	
	@Id
	@Column(name = "Employee_Contact_Type_ID")
	private String cTypeID;
	
	@NotEmpty(message = "required")
	@Column(name = "Employee_Contact_Type")
	private String contactType;
	
	@NotEmpty(message = "required")
	@Column(name = "Emagency_Contact")
	private String emContact;

	public String getcTypeID() {
		return cTypeID;
	}

	public void setcTypeID(String cTypeID) {
		this.cTypeID = cTypeID;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getEmContact() {
		return emContact;
	}

	public void setEmContact(String emContact) {
		this.emContact = emContact;
	}

	public EmployeeContactType(String cTypeID, String contactType, String emContact) {
		this.cTypeID = cTypeID;
		this.contactType = contactType;
		this.emContact = emContact;
	}

	public EmployeeContactType() {
	}
	
	

}
