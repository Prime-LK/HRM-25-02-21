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
@Table(name = "attendance_summary")
public class AttendanceSummary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendance_summary_id")
	private int attendanceSummaryId;
	
	@Column(name = "total_ot")
	private double totalOtHours;
	
	@Column(name = "total_late_hours")
	private double totalLateMinutes;
	
	@Column(name = "total_leave")
	private int totalLeave;
	
	@Column(name = "no_pay_days")
	private int noPayDays;
	
	@Column(name = "no_pay_hours")
	private double noPayHours;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id", referencedColumnName = "Employee_ID")
	private Employee employee;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "pay_period_id", referencedColumnName = "Pay_Period_ID")
	private PayPeriods payPeriod;
	
	@ManyToOne(optional=false, fetch = FetchType.EAGER)
	@JoinColumn(name="company_id", referencedColumnName="Company_ID")
	private CompanyMaster company;

	public int getAttendanceSummaryId() {
		return attendanceSummaryId;
	}

	public void setAttendanceSummaryId(int attendanceSummaryId) {
		this.attendanceSummaryId = attendanceSummaryId;
	}

	public double getTotalOtHours() {
		return totalOtHours;
	}

	public void setTotalOtHours(double totalOtHours) {
		this.totalOtHours = totalOtHours;
	}

	public double getTotalLateMinutes() {
		return totalLateMinutes;
	}

	public void setTotalLateMinutes(double totalLateMinutes) {
		this.totalLateMinutes = totalLateMinutes;
	}

	public int getTotalLeave() {
		return totalLeave;
	}

	public void setTotalLeave(int totalLeave) {
		this.totalLeave = totalLeave;
	}

	public int getNoPayDays() {
		return noPayDays;
	}

	public void setNoPayDays(int noPayDays) {
		this.noPayDays = noPayDays;
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

	public CompanyMaster getCompany() {
		return company;
	}

	public void setCompany(CompanyMaster company) {
		this.company = company;
	}

	public double getNoPayHours() {
		return noPayHours;
	}

	public void setNoPayHours(double noPayHours) {
		this.noPayHours = noPayHours;
	}

	public AttendanceSummary(int attendanceSummaryId, double totalOtHours, double totalLateMinutes, int totalLeave,
			int noPayDays, double noPayHours, Employee employee, PayPeriods payPeriod, CompanyMaster company) {
		super();
		this.attendanceSummaryId = attendanceSummaryId;
		this.totalOtHours = totalOtHours;
		this.totalLateMinutes = totalLateMinutes;
		this.totalLeave = totalLeave;
		this.noPayDays = noPayDays;
		this.noPayHours = noPayHours;
		this.employee = employee;
		this.payPeriod = payPeriod;
		this.company = company;
	}

	public AttendanceSummary() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
