package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="religion_master")
public class ReligionMaster {

	@Id
	@Column(name="Religion_ID")
	private String rid;
	
	@NotEmpty(message = "required")
	@Column(name="Religion")
	private String religion;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public ReligionMaster(String rid) {
		this.rid = rid;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	public ReligionMaster(String rid, String religion,CompanyMaster company) {
		this.rid = rid;
		this.religion = religion;
		this.company = company;
	}

	public ReligionMaster() {
	}
	
}
