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
@Table(name="nationality_master")
public class NationalityMaster {

	@Id
	@Column(name="Nationality_ID")
	private String nId;
	
	@NotEmpty(message = "required")
	@Column(name="Nationality")
	private String nationality;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getnId() {
		return nId;
	}

	public void setnId(String nId) {
		this.nId = nId;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public NationalityMaster(String nId, String nationality,CompanyMaster company) {
		this.nId = nId;
		this.nationality = nationality;
		this.company = company;
	}

	public NationalityMaster() {
	}

	public NationalityMaster(String nId) {
		this.nId = nId;
	}
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	
}

