package com.navitsa.hrm.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.ProcessPayrollDetails;
import com.navitsa.hrm.entity.ProcessPayrollMaster;
import com.navitsa.hrm.repository.ProcessPayrollMasterRepository;

@Service
public class ProcessPayrollMasterService {

	@Autowired
	private ProcessPayrollMasterRepository proPaMaRepo;

	
	public String getMaxID() {
		if(proPaMaRepo.maxMID() == null) {
			return "1";
		} else {
			return proPaMaRepo.maxMID();
		}
	}
	
	public void saveProcessPayrollMaster(ProcessPayrollMaster details) {
		 proPaMaRepo.save(details);
	}
	
	//employee report 01 data 	
	public String[][] getReportData() {
		return proPaMaRepo.getReportData();
	}
	
	public String[][] getAllEmpWithAllowancesReportBodyData() {
		return proPaMaRepo.getAllEmpWithAllowancesReportBodyData();
	}
	
	public String[][] getAllEmpWithAllowancesReportBodySumData(String comID) {
		return proPaMaRepo.getAllEmpWithAllowancesReportBodySumData(comID);
	}
	
	public String[][] getAllEmpWithAllowancesReportHeadData() {
		return proPaMaRepo.getAllEmpWithAllowancesReportHeadData();
	}
	
	public String[][] getAllEmpWithAllowancesReportHeaderSumData(String comID) {
		return proPaMaRepo.getAllEmpWithAllowancesReportHeaderSumData(comID);
	}
	
	//end of the employee report 01 data
	
	//employee pay slip methods
	public String[][] paySlipData(String payperodid,String dept,String empID, String comID) {
		return proPaMaRepo.paySlipData(payperodid,dept,empID,comID);
	}
	public String[][] getpaySheet(String dept,String comID,String type,String payperodid) {
		return proPaMaRepo.getpaySheet(dept,comID,type,payperodid);
	}
	
	public String loggedCompanyName(String comID) {
		return proPaMaRepo.loggedComapanyName(comID);
	}
	
	//All Employees paySlips data
	public String[][] getAllEmpsPaySlips(String comID) {
		return proPaMaRepo.getAllEmpsPaySlips(comID);
	}
	
	//related department payslip
	public String[][] getEmpRelatedDepartmentPaySlip(String comID, String depID) {
		return proPaMaRepo.getEmpRelatedDepartmentPaySlip(comID, depID);
	}
	
	//related startDate and endDate
	public String[][] getPaySlipRelatedStartDateAndEndDate(String comID,String startDate, String endDate) {
		return proPaMaRepo.getPayslipRelatedStartDateAndEndDate(comID, startDate, endDate);
	}
	
	//load proceePayorll tables data
	//table 01
	public String[][] loadTable01Data(String payCodeID, String comID) {
		return proPaMaRepo.loadTable01Data(payCodeID, comID);
	}
	
	//table 02
	public String[][] loadTable02Data(String payCodeID, String comID) {
		return proPaMaRepo.loadTable02Data(payCodeID,comID);
	}
	
	//table 03
	public String[][] loadTable03Data(String payCodeID,String empID,String comID) {
		return proPaMaRepo.loadTable03Data(payCodeID, empID,comID);
	}
	
	//table 03 basic data
	public String[][] loadTable03BasicData(String payCodeID,String empID,String comID) {
		return proPaMaRepo.loadTable03BasicData(payCodeID, empID,comID);
	}
	
	//calPrioritData
	public String CalPriorotyData(String payCodeID,String empID, String comID) {
		return proPaMaRepo.CalPriorotyData(payCodeID, empID, comID);
	}	
	//dedgrossPerValues
	public String[][] dedGrossPerValues(String empID) {
		return proPaMaRepo.dedGrossPerValues(empID);
	}
	
	//calculate calculation priority	
	public String[][] addGrossPerValues(String empID,String comID) {
		return proPaMaRepo.addGrossPerValues(empID,comID);
	}
	
	public String[][] getFixAndVariableDedGrossPercentageValues(String empID, String comID) {
		return proPaMaRepo.getFixAndVariableDedGrossPercentageValues(empID, comID);
	}

	public String[][] getFixAndVariableOthGrossPercentageValues(String empID,String comID) {
		return proPaMaRepo.getFixAndVariableOthGrossPercentageValues(empID,comID);
	}

	//depends on basic salary
	public String getBasicSalaryCalculatePercentageValues(String empID,String comID) {
		return proPaMaRepo.getBasicSalaryCalculatePercentageValues(empID,comID);
	}
	
	public String[][] getFixAndVariableAddBasicPercentageValues(String empID, String comID) {
		return proPaMaRepo.getFixAndVariableAddBasicPercentageValues(empID, comID);
	}
	
	public String[][] getFixAndVariableDedBasicPercentageValues(String empID, String comID) {
		return proPaMaRepo.getFixAndVariableDedBasicPercentageValues(empID, comID);
	}
	
	public String[][] getFixAndVariableOthBasicPercentageValues(String empID, String comID) {
		return proPaMaRepo.getFixAndVariableOthBasicPercentageValues(empID, comID);
	}
	
	public List<ProcessPayrollMaster> saveDataList(List<ProcessPayrollMaster> list) {
		return proPaMaRepo.saveAll(list);
	}
	
	//totalGross
	public String[][] getMoProPCTabbleData(String payCodeID, String comID) {
		return proPaMaRepo.getMoProPCTabbleData(payCodeID,comID);
	}
	
	//save data of process payroll
	public String[][] sampleSave(String payCodeID,String comID) {
		return proPaMaRepo.sampleSave(payCodeID,comID);
	}
	
	public String[][] saveDataMonthProcessMaster(String payCodeID, String comID) {
		return proPaMaRepo.saveDataMonthProcessMaster(payCodeID, comID);
	}
	
	public String[][] addGrossPerCal(String comID) {
		return proPaMaRepo.addGrossPerCal(comID);
	}
	
	public String[][] dedGrossPerCal(String comID) {
		return proPaMaRepo.dedGrossPerCal(comID);
	}
	
	public String[][] otherGrossPerCal(String comID) {
		return proPaMaRepo.otherGrossPerCal(comID);
	}
	
	public String[][] calPriEmpList(String comID) {
		return proPaMaRepo.calPriEmpList(comID);
	}
	
	public void deleteAllDataOfProcessPayrollMaster() {
		proPaMaRepo.deleteAll();
	}
	public String[][]  getProcessPayroll(String payperoide,String payCodeID,String comID){
		return proPaMaRepo.getProcessPayroll(payperoide,payCodeID,comID);
		
	}
}
