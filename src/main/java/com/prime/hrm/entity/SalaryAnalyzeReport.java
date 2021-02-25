package com.prime.hrm.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="salary_analyze_report")
public class SalaryAnalyzeReport {
	
	@EmbeddedId
	private SalaryAnalyzeReportPK saPK;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public SalaryAnalyzeReportPK getSaPK() {
		return saPK;
	}

	public void setSaPK(SalaryAnalyzeReportPK saPK) {
		this.saPK = saPK;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public SalaryAnalyzeReport(SalaryAnalyzeReportPK saPK, CompanyMaster company) {
		this.saPK = saPK;
		this.company = company;
	}

	public SalaryAnalyzeReport() {
	
	}
	
	

}
