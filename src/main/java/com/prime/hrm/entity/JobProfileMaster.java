package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="job_profile_master")
public class JobProfileMaster {

	@Id
	@Column(name="Job_Profile_ID")
	private String profileID;
	
	@NotEmpty(message="required")
	@Column(name="Job_Profile")
	private String profile;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getProfileID() {
		return profileID;
	}

	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public JobProfileMaster(String profileID, String profile,CompanyMaster company) {
		this.profileID = profileID;
		this.profile = profile;
		this.company = company;
	}

	public JobProfileMaster() {
	}

	public JobProfileMaster(String profileID) {
		this.profileID = profileID;
	}
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}
	
}
