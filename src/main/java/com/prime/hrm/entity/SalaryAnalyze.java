package com.prime.hrm.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Table;

@Entity
@Table(name="salary_analyze")
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name="firstQuery", procedureName="salaryAnalyzeReportData")})
public class SalaryAnalyze {

	@EmbeddedId
	private SalaryAnalyzePK saPK;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public SalaryAnalyzePK getSaPK() {
		return saPK;
	}

	public void setSaPK(SalaryAnalyzePK saPK) {
		this.saPK = saPK;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public SalaryAnalyze(SalaryAnalyzePK saPK, CompanyMaster company) {
		this.saPK = saPK;
		this.company = company;
	}

	public SalaryAnalyze() {
	}
	
	
	
	
}
