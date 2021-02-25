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
@Table(name="asset_master")

public class ItemMaster {
	
	@Id
	
	@Column(name="Item_Code")
	private String itemcode;
	
	
	@NotEmpty(message="Please enter description")
	@Column(name="Description")
	private String description;
	
	@Column(name="Barcode")
	private String barcode;
	
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Item_Group_Code", referencedColumnName ="Item_Group_Code")
	
	private ItemGroup  itemgroupcode;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="UOM_Code", referencedColumnName ="UOM_Code")
	
	private UOM  uom;
	
	
	@Column(name="Unit_Price")
	private Double unitprice;
	
	@Column(name="Serial_Number")
	private String serialnumber;
	

	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Location_Code", referencedColumnName ="Location_Code")
	
	private Location locationcode;
	
	@Column(name="Status")
	private String status;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Class_Code", referencedColumnName ="Class_Code")
	
	private Assestclass Class_Code;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="Deprec_Group_Code", referencedColumnName ="Deprec_Group_Code")
	
	private Depreciationgroup deprecgroupcode;
	
	@Column(name="Capitalize_Date")
	private String capitalizedate;
	
	@Column(name="Cost")
	private Double cost;
	
	@Column(name="NBV")
	private Double nbv;
	
	@Column(name="Depreciation")
	private Double depreciation;
	
	@Column(name="Writeup_Value")
	private Double writeupvalue;
	
	
	@Column(name="Salvage_Value")
	private Double salvagevalue;
	
	@Column(name="Intagible")
	private String intagible;

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public ItemGroup getItemgroupcode() {
		return itemgroupcode;
	}

	public void setItemgroupcode(ItemGroup itemgroupcode) {
		this.itemgroupcode = itemgroupcode;
	}

	public UOM getUom() {
		return uom;
	}

	public void setUom(UOM uom) {
		this.uom = uom;
	}

	public Double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public Location getLocationcode() {
		return locationcode;
	}

	public void setLocationcode(Location locationcode) {
		this.locationcode = locationcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Assestclass getClass_Code() {
		return Class_Code;
	}

	public void setClass_Code(Assestclass class_Code) {
		Class_Code = class_Code;
	}

	public Depreciationgroup getDeprecgroupcode() {
		return deprecgroupcode;
	}

	public void setDeprecgroupcode(Depreciationgroup deprecgroupcode) {
		this.deprecgroupcode = deprecgroupcode;
	}

	public String getCapitalizedate() {
		return capitalizedate;
	}

	public void setCapitalizedate(String capitalizedate) {
		this.capitalizedate = capitalizedate;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getNbv() {
		return nbv;
	}

	public void setNbv(Double nbv) {
		this.nbv = nbv;
	}

	public Double getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(Double depreciation) {
		this.depreciation = depreciation;
	}

	public Double getWriteupvalue() {
		return writeupvalue;
	}

	public void setWriteupvalue(Double writeupvalue) {
		this.writeupvalue = writeupvalue;
	}

	public Double getSalvagevalue() {
		return salvagevalue;
	}

	public void setSalvagevalue(Double salvagevalue) {
		this.salvagevalue = salvagevalue;
	}

	public String getIntagible() {
		return intagible;
	}

	public void setIntagible(String intagible) {
		this.intagible = intagible;
	}

	public ItemMaster() {
	
	}

	
}