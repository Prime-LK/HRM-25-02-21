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
@Table(name="qualification_master")
public class QualificationMaster {

	@Id
	@Column(name="Qualification_Type_ID")
	private String qid;
	
	@NotEmpty(message="required")
	@Column(name="Qualification_Type")
	private String qualification;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public QualificationMaster(String qid, String qualification,CompanyMaster company) {
		this.qid = qid;
		this.qualification = qualification;
		this.company = company;
	}

	public QualificationMaster() {
	}
	
	
}
