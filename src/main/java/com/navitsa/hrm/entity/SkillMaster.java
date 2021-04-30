package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="skill_master")
public class SkillMaster {

	@Id
	@Column(name="Skill_ID")
	private String sid;
	
	@NotEmpty(message="required")
	@Column(name="Skill")
	private String skill;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public SkillMaster(String sid, String skill) {
		this.sid = sid;
		this.skill = skill;
	}

	public SkillMaster() {
	}
	
	
}
