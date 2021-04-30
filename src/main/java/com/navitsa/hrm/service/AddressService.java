package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.AddressPK;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeAddress;
import com.navitsa.hrm.entity.EmployeeAddressField;
import com.navitsa.hrm.repository.EmployeeAddressFieldRepository;
import com.navitsa.hrm.repository.EmployeeAddressRepository;
import com.navitsa.hrm.repository.EmployeeRepository;

@Service
public class AddressService {

	@Autowired
	private EmployeeAddressFieldRepository afRepo;
	
	@Autowired
	private EmployeeAddressRepository eaRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	//employee address field-----------------------------------------------
	
	public String maxAfID() {
		if(afRepo.maxAfID() == null) {
			return "1";
		} else {
			return afRepo.maxAfID();
		}
	}
	
	public List<EmployeeAddressField> getAllAddF() {
		return (List<EmployeeAddressField>) afRepo.findAll();
	}
	
	public void saveAddress(EmployeeAddressField af) {
		afRepo.save(af);
	}
	
	public EmployeeAddressField getAddress(String fieldId) {
		return afRepo.findById(fieldId).get();
	}
	
	//employee address--------------------------------------------------
	
	public List<EmployeeAddress> getAllEa() {
		return (List<EmployeeAddress>) eaRepo.findAll();
	}
	
	public List<Employee> getAllEmp() {
		return (List<Employee>) empRepo.findAll();
	}
	
	public void saveEAddress(EmployeeAddress ea) {
		eaRepo.save(ea);
	}
	
	public EmployeeAddress getEAddress(String eid,String fieldId) {
		return eaRepo.setEmployeeAddressDetails(eid, fieldId);
	}
	
	//load address details according to employeeID to empaddress jsp
	public List<EmployeeAddress> searchaddDEtails(String empID) {
		
		return eaRepo.searchaddressDetails(empID);
	}
}
