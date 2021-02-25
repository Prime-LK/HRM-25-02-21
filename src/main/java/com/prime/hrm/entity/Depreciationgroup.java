package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="depreciation_groups")

public class Depreciationgroup {
	
	@Id
		
	@Column(name="Deprec_Group_Code")
	private String deprecgroupcode;
	

	@Column(name="Description")
	private String description;
	

	@Column(name="Status")
	private String status;


	public String getDeprecgroupcode() {
		return deprecgroupcode;
	}


	public void setDeprecgroupcode(String deprecgroupcode) {
		this.deprecgroupcode = deprecgroupcode;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Depreciationgroup() {
		
	}
	
	
	
	

}
