package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="emp_qualification")
public class EmployeeQualification {

	@EmbeddedId
	private QualificationPK quaPK;
	@NotEmpty(message = "required")
	@Column(name="Description")
	private String desc;
	
	@Column(name="Awarded_On")
	private String award;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	public QualificationPK getQuaPK() {
		return quaPK;
	}

	public void setQuaPK(QualificationPK quaPK) {
		this.quaPK = quaPK;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public EmployeeQualification(QualificationPK quaPK, String desc, String award,CompanyMaster company) {
		this.quaPK = quaPK;
		this.desc = desc;
		this.award = award;
		this.company = company;
	}

	public EmployeeQualification() {
	}
	
	
}
