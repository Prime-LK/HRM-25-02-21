package com.navitsa.hrm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CalanderEntityPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "date")
	private String date;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", referencedColumnName = "Company_ID")
	private CompanyMaster company;

	public CalanderEntityPK() {

	}

	public CalanderEntityPK(String date, CompanyMaster company) {
		this.date = date;
		this.company = company;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "CalanderEntityPK [date=" + date + ", company=" + company + "]";
	}
}