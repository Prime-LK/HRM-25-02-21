package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="good_transfer_head")
public class AssetTransfer {
	
	@Id
	@Column(name="TransferNo")
	private String tranferno;
	
	@Column(name="Date")
	private String date;
	
	@Column(name="Remarks")
	private String remarks;
	
	@Column(name="Journal_Remarks")
	private String journalremarks;
	
	@Column(name="FromLocationCode")
	private String locationcode1;
	
	@Column(name="ToLocationCode")
	private String locationcode2;
	
	@Column(name="UserID")
	private String userid;
	
	
	
	@Column(name="Item_Code")
	private String itemcode;
	
	@Column(name="Description")
	private String serialnumber;
	
	
	@Pattern(regexp="^[0-9 ]+$",message="Please enter numbers only")
	@NotEmpty(message="Please enter order qty")
	@Column(name="Qty")
	private String qty;

	public String getTranferno() {
		return tranferno;
	}

	public void setTranferno(String tranferno) {
		this.tranferno = tranferno;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getJournalremarks() {
		return journalremarks;
	}

	public void setJournalremarks(String journalremarks) {
		this.journalremarks = journalremarks;
	}

	
	public String getLocationcode1() {
		return locationcode1;
	}

	public void setLocationcode1(String locationcode1) {
		this.locationcode1 = locationcode1;
	}

	public String getLocationcode2() {
		return locationcode2;
	}

	public void setLocationcode2(String locationcode2) {
		this.locationcode2 = locationcode2;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

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

	public AssetTransfer() {
		
	}

	

	
}
