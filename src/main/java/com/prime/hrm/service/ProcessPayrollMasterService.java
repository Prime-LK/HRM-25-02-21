package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.ProcessPayrollMaster;
import com.prime.hrm.repository.ProcessPayrollMasterRepository;

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
	//end of the employee report 01 data
	
	//employee pay slip methods
	public String[][] paySlipData(String empID) {
		return proPaMaRepo.paySlipData(empID);
	}
	// end payslip data
	
	//load proceePayorll tables data
	//table 01
	public String[][] loadTable01Data(String payCodeID) {
		return proPaMaRepo.loadTable01Data(payCodeID);
	}
	
	//table 02
	public String[][] loadTable02Data(String payCodeID) {
		return proPaMaRepo.loadTable02Data(payCodeID);
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
	public String CalPriorotyData(String payCodeID,String empID) {
		return proPaMaRepo.CalPriorotyData(payCodeID, empID);
	}
	
	//otherGrossValues
	public String[][] otherGrossPerValues(String empID) {
		return proPaMaRepo.otherGrossPerValues(empID);
	}
	
	//dedgrossPerValues
	public String[][] dedGrossPerValues(String empID) {
		return proPaMaRepo.dedGrossPerValues(empID);
	}
	
	//addgrossPerValues
	public String[][] addGrossPerValues(String empID) {
		return proPaMaRepo.addGrossPerValues(empID);
	}
	
	public List<ProcessPayrollMaster> saveDataList(List<ProcessPayrollMaster> list) {
		return proPaMaRepo.saveAll(list);
	}
	
	//totalGross
	public String[][] getMoProPCTabbleData(String payCodeID) {
		return proPaMaRepo.getMoProPCTabbleData(payCodeID);
	}
	
	//save data of process payroll
	public String[][] sampleSave(String payCodeID) {
		return proPaMaRepo.sampleSave(payCodeID);
	}
	
	public String[][] addGrossPerCal() {
		return proPaMaRepo.addGrossPerCal();
	}
	
	public String[][] dedGrossPerCal() {
		return proPaMaRepo.dedGrossPerCal();
	}
	
	public String[][] otherGrossPerCal() {
		return proPaMaRepo.otherGrossPerCal();
	}
	
	public String[][] calPriEmpList() {
		return proPaMaRepo.calPriEmpList();
	}
	
	public void deleteAllDataOfProcessPayrollMaster() {
		proPaMaRepo.deleteAll();
	}

}
