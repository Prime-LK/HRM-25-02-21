package com.prime.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="good_transfer_detail")

public class AssetItem {
	
	@Id
	@NotEmpty(message="Please enter class code")
	@Column(name="Item_Code")
	private String itemcode;
	

	
	@Column(name="Serial_Number")
	private String serialnumber;

   @Column(name="Qty")
	private String qty;

public String getItemcode() {
	return itemcode;
}

public void setItemcode(String itemcode) {
	this.itemcode = itemcode;
}

public String getSerialnumber() {
	return serialnumber;
}

public void setSerialnumber(String serialnumber) {
	this.serialnumber = serialnumber;
}

public String getQty() {
	return qty;
}

public void setQty(String qty) {
	this.qty = qty;
}

public AssetItem() {
	
}
   
   
	
}