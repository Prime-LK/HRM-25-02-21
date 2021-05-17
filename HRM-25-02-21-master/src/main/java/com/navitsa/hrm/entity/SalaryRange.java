package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name="salary_range")
public class SalaryRange {

	@Id
	@Column(name="Salary_Range_ID")
	private String salaryRangeID;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Salary_Grade_ID", referencedColumnName ="Salary_Grade_ID")
	private SalaryGrade salaryGrade;
	
	@DecimalMin(value = "0.0", inclusive = true)
	@Column(name="Min_Salary")
	private double minSalary;
	
	@DecimalMin(value = "0.0", inclusive = true)
	@Column(name="Max_Salary")
	private double maxSalary;

	@Column(name="Salary_Range")
	private String range;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	public String getSalaryRangeID() {
		return salaryRangeID;
	}

	public void setSalaryRangeID(String salaryRangeID) {
		this.salaryRangeID = salaryRangeID;
	}

	public SalaryGrade getSalaryGrade() {
		return salaryGrade;
	}

	public void setSalaryGrade(SalaryGrade salaryGrade) {
		this.salaryGrade = salaryGrade;
	}

//	public int getMinSalary() {
//		return minSalary;
//	}
//
//	public void setMinSalary(int minSalary) {
//		this.minSalary = minSalary;
//	}
//
//	public int getMaxSalary() {
//		return maxSalary;
//	}
	
	
	public void setMaxSalary(int maxSalary) {
		this.maxSalary = maxSalary;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}

	public double getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public SalaryRange(String salaryRangeID, SalaryGrade salaryGrade, double minSalary, double maxSalary,
			String range,CompanyMaster company) {
		this.salaryRangeID = salaryRangeID;
		this.salaryGrade = salaryGrade;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.range = range;
		this.company = company;
	}

	public SalaryRange() {
	}

	public SalaryRange(String salaryRangeID) {
		this.salaryRangeID = salaryRangeID;
	}
	
	
	
	
}
