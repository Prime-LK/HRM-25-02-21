package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="emp_address")
public class EmployeeAddress {

	@EmbeddedId
	private AddressPK addPK;
	@NotEmpty(message = "required")
	@Column(name="Emp_Add_Field")
	private String field;

	public AddressPK getAddPK() {
		return addPK;
	}

	public void setAddPK(AddressPK addPK) {
		this.addPK = addPK;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public EmployeeAddress(AddressPK addPK, String field) {
		this.addPK = addPK;
		this.field = field;
	}

	public EmployeeAddress() {
	}
	
	
}
