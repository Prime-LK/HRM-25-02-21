package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="salary_analyze")
public class SalaryAnalyze {

	@Id
	@Column(name="Salary_Analyze_ID")
	private String saID;
	
	@Column(name="Year")
	private String year;
	
	@Column(name="Month")
	private String month;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Department_ID", referencedColumnName="Department_ID")
	private DepartmentMaster depatment;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Pay_Add_Deduct_Type_Code", referencedColumnName ="Pay_Add_Deduct_Type_Code")
	private PayAddDeductTypes addDedType;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getSaID() {
		return saID;
	}

	public void setSaID(String saID) {
		this.saID = saID;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public DepartmentMaster getDepatment() {
		return depatment;
	}

	public void setDepatment(DepartmentMaster depatment) {
		this.depatment = depatment;
	}

	public PayAddDeductTypes getAddDedType() {
		return addDedType;
	}

	public void setAddDedType(PayAddDeductTypes addDedType) {
		this.addDedType = addDedType;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public SalaryAnalyze(String saID, String year, String month, DepartmentMaster depatment,
			PayAddDeductTypes addDedType, CompanyMaster company) {
		this.saID = saID;
		this.year = year;
		this.month = month;
		this.depatment = depatment;
		this.addDedType = addDedType;
		this.company = company;
	}

	public SalaryAnalyze() {
	}
	
	
}
