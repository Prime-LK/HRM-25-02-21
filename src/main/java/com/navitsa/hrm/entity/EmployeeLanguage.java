package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="emp_languages")
public class EmployeeLanguage {

	@EmbeddedId
	private LanguagePK lanPk;
	
	@Column(name="Description")
	private String desc;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	public LanguagePK getLanPk() {
		return lanPk;
	}
	
	public void setLanPk(LanguagePK lanPk) {
		this.lanPk = lanPk;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public EmployeeLanguage(LanguagePK lanPk, String desc
			,CompanyMaster company) {
		this.lanPk = lanPk;
		this.desc = desc;
		this.company = company;
	}

	public EmployeeLanguage(LanguagePK lanPk) {
		this.lanPk = lanPk;
	}

	public EmployeeLanguage() {
	}
	
	
}
