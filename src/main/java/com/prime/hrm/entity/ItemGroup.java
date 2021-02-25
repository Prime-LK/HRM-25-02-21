package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="item_groups")

public class ItemGroup {
	
	@Id
	@Column(name="Item_Group_Code")
	private String itemgroupcode;
	
	@Pattern(regexp="^[0-9 ]+$",message="Please enter numbers only")
	@NotEmpty(message="Please enter order qty")
	@Column(name="Description")
	private String description;
	
	@Pattern(regexp="^[0-9 ]+$",message="Please enter numbers only")
	@NotEmpty(message="Please enter Lead time")
	@Column(name="Status")
	private String status;

	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="UOM_Code", referencedColumnName ="UOM_Code")
	private UOM  uom;
	
	
	@NotEmpty(message="Please enter order Description")
	@Column(name="Description2")
	private String description2;
	
	
	@Pattern(regexp="^[0-9 ]+$",message="Please enter numbers only")
	@NotEmpty(message="Please enter item group code")
	@Column(name="OrderInterval")
	private String orderinterval;


	public String getItemgroupcode() {
		return itemgroupcode;
	}


	public void setItemgroupcode(String itemgroupcode) {
		this.itemgroupcode = itemgroupcode;
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


	public UOM getUom() {
		return uom;
	}


	public void setUom(UOM uom) {
		this.uom = uom;
	}


	public String getDescription2() {
		return description2;
	}


	public void setDescription2(String description2) {
		this.description2 = description2;
	}


	public String getOrderinterval() {
		return orderinterval;
	}


	public void setOrderinterval(String orderinterval) {
		this.orderinterval = orderinterval;
	}


	public ItemGroup() {
		
	}


	public ItemGroup(String itemgroupcode,
			@Pattern(regexp = "^[0-9 ]+$", message = "Please enter numbers only") @NotEmpty(message = "Please enter order qty") String description,
			@Pattern(regexp = "^[0-9 ]+$", message = "Please enter numbers only") @NotEmpty(message = "Please enter Lead time") String status,
			UOM uom, @NotEmpty(message = "Please enter order Description") String description2,
			@Pattern(regexp = "^[0-9 ]+$", message = "Please enter numbers only") @NotEmpty(message = "Please enter item group code") String orderinterval) {
		super();
		this.itemgroupcode = itemgroupcode;
		this.description = description;
		this.status = status;
		this.uom = uom;
		this.description2 = description2;
		this.orderinterval = orderinterval;
	}


	

	
	
	
	
}