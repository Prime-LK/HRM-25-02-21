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
@Table(name="salary_grades")
public class SalaryGrade {

	@Id
	@Column(name="Salary_Grade_ID")
	private String gradeID;
	
	@NotEmpty(message="required")
	@Column(name="Salary_Grade")
	private String grade;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getGradeID() {
		return gradeID;
	}

	public void setGradeID(String gradeID) {
		this.gradeID = gradeID;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
	public SalaryGrade(String gradeID, String grade,CompanyMaster company) {
		this.gradeID = gradeID;
		this.grade = grade;
		this.company = company;
	}

	public SalaryGrade() {
	}
	
	
	
}
