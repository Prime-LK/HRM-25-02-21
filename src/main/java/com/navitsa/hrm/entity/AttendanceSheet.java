package com.navitsa.hrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attendance_sheet")
public class AttendanceSheet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendance_sheet_id")
	private int attendanceSheetId;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "shift_in")
	private String shiftIn;
	
	@Column(name = "shift_out")
	private String shiftOut;
	
	@Column(name = "time_in")
	private String timeIn;
	
	@Column(name = "time_out")
	private String timeOut;
	
	@Column(name = "is_leave")
	private Boolean isLeave;
	
	@Column(name = "lunch1_time_in")
	private String lunch1In;
	
	@Column(name = "lunch1_time_out")
	private String lunch1Out;
	
	@Column(name = "lunch2_time_in")
	private String lunch2In;
	
	@Column(name = "lunch2_time_out")
	private String lunch2Out;
	
	@Column(name = "late_min_time_in")
	private int lateMinIn;
	
	@Column(name = "late_min_time_out")
	private int lateMinOut;
	
	@Column(name = "late_min_lunch")
	private int lateMinLunch;
	
	@Column(name = "ot_hrs_normal")
	private int otHrsNormal;
	
	@Column(name = "ot_hrs_extra")
	private int otHrsExtra;
	
	@Column(name = "night_shift_alow")
	private int nightShiftAllow;
	
	@Column(name = "spc_nsa_entitlement")
	private String spcNSAEntitlement;
	
	@Column(name = "day_type")
	private String dayType;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="company_id", referencedColumnName="Company_ID")
	private CompanyMaster company;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", referencedColumnName = "Employee_ID")
	private Employee employee;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pay_period_id", referencedColumnName = "Pay_Period_ID")
	private PayPeriods payPeriod;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "shift_id", referencedColumnName = "shift_id")
	private ShiftMaster shift;

	public AttendanceSheet() {
		super();
	}

	public AttendanceSheet(int attendanceSheetId, String date, String shiftIn, String shiftOut, String timeIn,
			String timeOut, String lunch1In, String lunch1Out, String lunch2In, String lunch2Out, int lateMinIn,
			int lateMinOut, int lateMinLunch, int otHrsNormal, int otHrsExtra, int nightShiftAllow,
			String spcNSAEntitlement, String dayType, CompanyMaster company, Employee employee, PayPeriods payPeriod,
			ShiftMaster shift) {
		super();
		this.attendanceSheetId = attendanceSheetId;
		this.date = date;
		this.shiftIn = shiftIn;
		this.shiftOut = shiftOut;
		this.timeIn = timeIn;
		this.timeOut = timeOut;
		this.lunch1In = lunch1In;
		this.lunch1Out = lunch1Out;
		this.lunch2In = lunch2In;
		this.lunch2Out = lunch2Out;
		this.lateMinIn = lateMinIn;
		this.lateMinOut = lateMinOut;
		this.lateMinLunch = lateMinLunch;
		this.otHrsNormal = otHrsNormal;
		this.otHrsExtra = otHrsExtra;
		this.nightShiftAllow = nightShiftAllow;
		this.spcNSAEntitlement = spcNSAEntitlement;
		this.dayType = dayType;
		this.company = company;
		this.employee = employee;
		this.payPeriod = payPeriod;
		this.shift = shift;
	}

	public int getAttendanceSheetId() {
		return attendanceSheetId;
	}

	public void setAttendanceSheetId(int attendanceSheetId) {
		this.attendanceSheetId = attendanceSheetId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getShiftIn() {
		return shiftIn;
	}

	public void setShiftIn(String shiftIn) {
		this.shiftIn = shiftIn;
	}

	public String getShiftOut() {
		return shiftOut;
	}

	public void setShiftOut(String shiftOut) {
		this.shiftOut = shiftOut;
	}

	public String getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getLunch1In() {
		return lunch1In;
	}

	public void setLunch1In(String lunch1In) {
		this.lunch1In = lunch1In;
	}

	public String getLunch1Out() {
		return lunch1Out;
	}

	public void setLunch1Out(String lunch1Out) {
		this.lunch1Out = lunch1Out;
	}

	public String getLunch2In() {
		return lunch2In;
	}

	public void setLunch2In(String lunch2In) {
		this.lunch2In = lunch2In;
	}

	public String getLunch2Out() {
		return lunch2Out;
	}

	public void setLunch2Out(String lunch2Out) {
		this.lunch2Out = lunch2Out;
	}

	public int getLateMinIn() {
		return lateMinIn;
	}

	public void setLateMinIn(int lateMinIn) {
		this.lateMinIn = lateMinIn;
	}

	public int getLateMinOut() {
		return lateMinOut;
	}

	public void setLateMinOut(int lateMinOut) {
		this.lateMinOut = lateMinOut;
	}

	public int getLateMinLunch() {
		return lateMinLunch;
	}

	public void setLateMinLunch(int lateMinLunch) {
		this.lateMinLunch = lateMinLunch;
	}

	public int getOtHrsNormal() {
		return otHrsNormal;
	}

	public void setOtHrsNormal(int otHrsNormal) {
		this.otHrsNormal = otHrsNormal;
	}

	public int getOtHrsExtra() {
		return otHrsExtra;
	}

	public void setOtHrsExtra(int otHrsExtra) {
		this.otHrsExtra = otHrsExtra;
	}

	public int getNightShiftAllow() {
		return nightShiftAllow;
	}

	public void setNightShiftAllow(int nightShiftAllow) {
		this.nightShiftAllow = nightShiftAllow;
	}

	public String getSpcNSAEntitlement() {
		return spcNSAEntitlement;
	}

	public void setSpcNSAEntitlement(String spcNSAEntitlement) {
		this.spcNSAEntitlement = spcNSAEntitlement;
	}

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public PayPeriods getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(PayPeriods payPeriod) {
		this.payPeriod = payPeriod;
	}

	public ShiftMaster getShift() {
		return shift;
	}

	public void setShift(ShiftMaster shift) {
		this.shift = shift;
	}

	public Boolean getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(Boolean isLeave) {
		this.isLeave = isLeave;
	}



}
