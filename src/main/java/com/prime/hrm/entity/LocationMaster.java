package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class LocationMaster {

	@Id
	@Column(name="Location_ID")
	private String loid;
	
	@Column(name="Location")
	private String location;

	public String getLoid() {
		return loid;
	}

	public void setLoid(String loid) {
		this.loid = loid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocationMaster(String loid, String location) {
		this.loid = loid;
		this.location = location;
	}

	public LocationMaster() {
	}
	
	
	
}
