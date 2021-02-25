package com.prime.hrm.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EmployeeSkillPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Skill_ID", referencedColumnName = "Skill_ID")
	private SkillMaster sid;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "Employee_ID", referencedColumnName = "Employee_ID")
	private Employee empID;

	public SkillMaster getSid() {
		return sid;
	}

	public void setSid(SkillMaster sid) {
		this.sid = sid;
	}

	public Employee getEmpID() {
		return empID;
	}

	public void setEmpID(Employee empID) {
		this.empID = empID;
	}
	
	

}
