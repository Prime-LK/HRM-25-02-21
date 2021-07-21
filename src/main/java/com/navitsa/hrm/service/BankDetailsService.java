package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.Bank;
import com.navitsa.hrm.entity.BankDetails;
import com.navitsa.hrm.entity.BankDetailsPK;
import com.navitsa.hrm.entity.BankMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.repository.BankDetailsRepository;
import com.navitsa.hrm.repository.BankMasterRepository;
import com.navitsa.hrm.repository.BankRepository;
import com.navitsa.hrm.repository.EmployeeRepository;

@Service
public class BankDetailsService {

	@Autowired
	private BankDetailsRepository baRepo;

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private BankRepository bankRepo;

	@Autowired
	private BankMasterRepository bankMRepo;

	// bank details-----------------------------------------------

	public String getMaxID() {
		if (bankRepo.getID() == null) {
			return "1";
		} else {
			return bankRepo.getID();
		}
	}

	public List<BankDetails> getAllBa() {
		return (List<BankDetails>) baRepo.findAll();
	}

	public void saveBa(BankDetails ba) {
		baRepo.save(ba);
	}

	public BankDetails getDetails(BankDetailsPK id) {
		return baRepo.findById(id).get();
	}

	public List<Employee> getAllEmp() {
		return (List<Employee>) empRepo.findAll();
	}

	public BankDetails updateBankDetails(String empID, String acc) {
		return baRepo.updateBankDetails(empID, acc);
	}

	// getEmps related bank
	public List<BankDetails> getEmps(String empID) {
		return baRepo.getEmps(empID);
	}

	// bank-----------------------------------------------------

	// save bank jsp data
	public void saveBank(Bank bank) {
		bankRepo.save(bank);
	}

	// load saved bank data
	public List<Bank> getAllSavedBank() {
		return (List<Bank>) bankRepo.findAll();
	}

	public Bank updateDetails(String branchID) {
		return bankRepo.findById(branchID).get();
	}

	// bank master repo

	public String getMaxBMID() {
		if (bankMRepo.getMaxID() == null) {
			return "1";
		} else {
			return bankMRepo.getMaxID();
		}
	}

	public BankMaster saveBankMAster(BankMaster bm) {
		return bankMRepo.save(bm);
	}

	public BankMaster getID(String id) {
		return bankMRepo.findById(id).get();
	}

	public List<BankMaster> getAllBankdata() {
		return (List<BankMaster>) bankMRepo.findAll();
	}

	public BankMaster getDetailsofBank(String bankid) {
		return bankMRepo.findById(bankid).get();
	}

	// load branch according to bank
	public List<Bank> getbranchBybank(String bank_Code) {
		return bankRepo.findbranch(bank_Code);
	}

	public List<BankMaster> getAllBankByCompany(String companyId) {
		return (List<BankMaster>) bankMRepo.getAllBankByCompany(companyId);
	}

	public List<Bank> getAllBankBranchByCompany(String companyId) {
		return (List<Bank>) bankRepo.getAllBankBranchByCompany(companyId);
	}
	
	public List<Bank> getAllBankBranchByBankAndCompany(String bankId, String companyId) {
		return (List<Bank>) bankRepo.getAllBankBranchByBankAndCompany(bankId, companyId);
	}
	
	public List<Bank> getAllBankBranchByBank(String bankId) {
		return (List<Bank>) bankRepo.getAllBankBranchByBank(bankId);
	}
}
