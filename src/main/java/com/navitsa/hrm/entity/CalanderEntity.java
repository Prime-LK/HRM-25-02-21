package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "calander")
public class CalanderEntity {

	@EmbeddedId
	private CalanderEntityPK calanderEntityPK;

	@Column(name = "description")
	private String description;

	@Column(name = "type")
	private String type;

	@Column(name = "status")
	private String status;

	public CalanderEntity() {

	}

	public CalanderEntity(CalanderEntityPK calanderEntityPK, String description, String type, String status) {
		this.calanderEntityPK = calanderEntityPK;
		this.description = description;
		this.type = type;
		this.status = status;
	}

	public CalanderEntity(String date, CompanyMaster company, String description, String type, String status) {
		this.calanderEntityPK = new CalanderEntityPK(date, company);
		this.description = description;
		this.type = type;
		this.status = status;
	}

	public CalanderEntityPK getCalanderEntityPK() {
		return calanderEntityPK;
	}

	public void setCalanderEntityPK(CalanderEntityPK calanderEntityPK) {
		this.calanderEntityPK = calanderEntityPK;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CalanderEntity [calanderEntityPK=" + calanderEntityPK + ", description=" + description + ", type="
				+ type + ", status=" + status + "]";
	}
}