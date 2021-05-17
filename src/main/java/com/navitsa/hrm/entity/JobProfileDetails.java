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
@Table(name="job_profile_details")
public class JobProfileDetails {
	
	
	@Id
	@Column(name="Job_Profile_Item_ID")
	private String jobProfileID;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Job_Profile_ID", referencedColumnName ="Job_Profile_ID")
	private JobProfileMaster jobprofilemaster;
	
	@NotEmpty(message="required")
	@Column(name="Job_Profile_Item")
	private String profileItem;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Company_ID", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public String getJobProfileID() {
		return jobProfileID;
	}

	public void setJobProfileID(String jobProfileID) {
		this.jobProfileID = jobProfileID;
	}

	public JobProfileMaster getJobprofilemaster() {
		return jobprofilemaster;
	}

	public void setJobprofilemaster(JobProfileMaster jobprofilemaster) {
		this.jobprofilemaster = jobprofilemaster;
	}

	public String getProfileItem() {
		return profileItem;
	}

	public void setProfileItem(String profileItem) {
		this.profileItem = profileItem;
	}
	
	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public JobProfileDetails() {
	}

	public JobProfileDetails(String jobProfileID, JobProfileMaster jobprofilemaster, String profileItem,CompanyMaster company) {
		this.jobProfileID = jobProfileID;
		this.jobprofilemaster = jobprofilemaster;
		this.profileItem = profileItem;
		this.company = company;
	}
	
	
	
	
	
}
