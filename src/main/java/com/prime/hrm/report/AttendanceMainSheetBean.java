package com.prime.hrm.report;

import java.util.List;

public class AttendanceMainSheetBean {

	private String total_over_time;
	private String total_short_time;
	private String total_worked_days;
	private String total_absent_days;
	private String total_holidays;
	private String total_rest_days;
	private List<AttendanceSubSheetBean> subReportBeanList;
	
	public String getTotal_over_time() {
		return total_over_time;
	}
	public void setTotal_over_time(String total_over_time) {
		this.total_over_time = total_over_time;
	}
	public String getTotal_short_time() {
		return total_short_time;
	}
	public void setTotal_short_time(String total_short_time) {
		this.total_short_time = total_short_time;
	}
	public String getTotal_worked_days() {
		return total_worked_days;
	}
	public void setTotal_worked_days(String total_worked_days) {
		this.total_worked_days = total_worked_days;
	}
	public String getTotal_absent_days() {
		return total_absent_days;
	}
	public void setTotal_absent_days(String total_absent_days) {
		this.total_absent_days = total_absent_days;
	}
	public String getTotal_holidays() {
		return total_holidays;
	}
	public void setTotal_holidays(String total_holidays) {
		this.total_holidays = total_holidays;
	}
	public String getTotal_rest_days() {
		return total_rest_days;
	}
	public void setTotal_rest_days(String total_rest_days) {
		this.total_rest_days = total_rest_days;
	}
	public List<AttendanceSubSheetBean> getSubReportBeanList() {
		return subReportBeanList;
	}
	public void setSubReportBeanList(List<AttendanceSubSheetBean> subReportBeanList) {
		this.subReportBeanList = subReportBeanList;
	}
}
