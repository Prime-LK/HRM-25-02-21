package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "emp_skills")
public class EmployeeSkill {

	@EmbeddedId
	private EmployeeSkillPK skillPK;

	@Column(name="Skill_Proficiency")
	private String sProficiency;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public EmployeeSkillPK getSkillPK() {
		return skillPK;
	}

	public void setSkillPK(EmployeeSkillPK skillPK) {
		this.skillPK = skillPK;
	}

	public String getsProficiency() {
		return sProficiency;
	}

	public void setsProficiency(String sProficiency) {
		this.sProficiency = sProficiency;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public EmployeeSkill(EmployeeSkillPK skillPK, String sProficiency
			,CompanyMaster company) {
		this.skillPK = skillPK;
		this.sProficiency = sProficiency;
		this.company = company;
	}

	public EmployeeSkill() {
	}
	
	
	
}
