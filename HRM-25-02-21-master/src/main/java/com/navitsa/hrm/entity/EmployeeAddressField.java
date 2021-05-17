package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="Employee_Address_Fields")
public class EmployeeAddressField {

	@Id
	@Column(name="Emp_AddField_ID")
	private String fieldId;
	
	@NotEmpty(message = "required")
	@Column(name="Emp_AddField")
	private String field;

	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public EmployeeAddressField(String fieldId, String field) {
		this.fieldId = fieldId;
		this.field = field;
	}

	public EmployeeAddressField() {
	}
	
	
}
