package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="emp_categories")
public class EmployeeCategory {

	@Id
	@Column(name="Emp_Category_ID")
	private String catgoryID;
	
	@NotEmpty(message = "required")
	@Column(name="Emp_Category")
	private String category;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Company_ID", referencedColumnName = "Company_ID")
	private CompanyMaster company;
	
	@Column(name="Double_OTRate")
	private String doubleOtrRate;
	
	@Column(name = "OT_Applicable")
	private String otapplicable;
	
	@Column(name = "Applicable_time")
	private String time;
	
	@Column(name = "Late_Applicable")
	private String lateApplicable;
	
	@Column(name = "Allow_Leave")
	private String allowLeave;

	public String getCatgoryID() {
		return catgoryID;
	}

	public void setCatgoryID(String catgoryID) {
		this.catgoryID = catgoryID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDoubleOtrRate() {
		return doubleOtrRate;
	}

	public void setDoubleOtrRate(String doubleOtrRate) {
		this.doubleOtrRate = doubleOtrRate;
	}

	public String getOtapplicable() {
		return otapplicable;
	}

	public void setOtapplicable(String otapplicable) {
		this.otapplicable = otapplicable;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLateApplicable() {
		return lateApplicable;
	}

	public void setLateApplicable(String lateApplicable) {
		this.lateApplicable = lateApplicable;
	}

	public String getAllowLeave() {
		return allowLeave;
	}

	public void setAllowLeave(String allowLeave) {
		this.allowLeave = allowLeave;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	
	
	
}

