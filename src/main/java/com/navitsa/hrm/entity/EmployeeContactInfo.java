package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emp_contact_info")
public class EmployeeContactInfo {

	@EmbeddedId
	EmployeeContactInfoPK employeeContactInfoPK;

	@NotNull(message="required")
    @Min(0)
	@Column(name = "Contact_No")
	private Integer contactNo;

	public EmployeeContactInfoPK getEmployeeContactInfoPK() {
		return employeeContactInfoPK;
	}

	public void setEmployeeContactInfoPK(EmployeeContactInfoPK employeeContactInfoPK) {
		this.employeeContactInfoPK = employeeContactInfoPK;
	}

	public Integer getContactNo() {
		return contactNo;
	}

	public void setContactNo(Integer contactNo) {
		this.contactNo = contactNo;
	}

	public EmployeeContactInfo() {

	}

}
