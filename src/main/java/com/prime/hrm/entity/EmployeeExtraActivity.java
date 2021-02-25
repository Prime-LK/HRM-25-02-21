package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="emp_extra_activities")
public class EmployeeExtraActivity {

	
	@EmbeddedId
	private ExtraActivityTypePK actPK;
	
	@Column(name="Extra_Activity")
	private String act;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public ExtraActivityTypePK getActPK() {
		return actPK;
	}

	public void setActPK(ExtraActivityTypePK actPK) {
		this.actPK = actPK;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public EmployeeExtraActivity(ExtraActivityTypePK actPK, String act
			,CompanyMaster company) {
		this.actPK = actPK;
		this.act = act;
		this.company = company;
	}

	public EmployeeExtraActivity() {
	}
	
	
}
