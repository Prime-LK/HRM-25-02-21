package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeAttachments;
import com.navitsa.hrm.entity.EmployeeID;
import com.navitsa.hrm.entity.EmployeeIdDocument;
import com.navitsa.hrm.repository.EmployeeAttachmentsRepository;
import com.navitsa.hrm.repository.EmployeeIDRepository;
import com.navitsa.hrm.repository.EmployeeIdDocumentRepository;
import com.navitsa.hrm.repository.EmployeeRepository;

@Service
public class EmployeeIdDocumentService {

	@Autowired
	private EmployeeIdDocumentRepository edRepo;

	@Autowired
	private EmployeeIDRepository eidRepo;

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private EmployeeAttachmentsRepository employeeAttachmentsRepo;

	public String maxEdID() {
		if (edRepo.maxDocTypeID() == null) {
			return "1";
		} else {
			return edRepo.maxDocTypeID();
		}
	}

	public List<EmployeeIdDocument> getAllEd() {
		return (List<EmployeeIdDocument>) edRepo.findAll();
	}

	public void saveEmpDoc(EmployeeIdDocument ed) {
		edRepo.save(ed);
	}

	public EmployeeIdDocument getEmpDoc(String id) {
		return edRepo.findById(id).get();
	}

	// save empID jsp data
	public void saveEmpIDDoc(EmployeeID eid) {
		eidRepo.save(eid);
	}

	// get saved data
	public List<EmployeeID> getALLEIDDOC() {
		return (List<EmployeeID>) eidRepo.findAll();
	}

	// edit data
	public EmployeeID getEmpIDDataByID(String eid, String empdoc, String docid) {
		return eidRepo.setEmployeeIDDetails(eid, empdoc, docid);
	}

	// getempId
//	public String getId(String empID) {
//		return empRepo.findEmpId(empID);
//	}

	public Employee getID(String id) {
		return empRepo.findById(id).get();
	}

	// employee attachment jsp save data
	public void saveEmpattach(EmployeeAttachments ed) {
		employeeAttachmentsRepo.save(ed);
	}

	// get saved data as a list
	public List<EmployeeAttachments> getALLemployeeAttach() {
		return (List<EmployeeAttachments>) employeeAttachmentsRepo.findAll();
	}

	// get element by id
	public EmployeeAttachments getEmpAttachmentsDataByID(String empID, String attachmentID) {
		return employeeAttachmentsRepo.setEmployeeAttachmentsDetails(empID, attachmentID);
	}

	// getmax id of employee attachment
	public String maxEmployeeAttachmentsID() {
		if (employeeAttachmentsRepo.maxEmployeeAttachmentsID() == null) {
			return "1";
		} else {
			return employeeAttachmentsRepo.maxEmployeeAttachmentsID();
		}
	}

	// get max document id for employeeID jsp
	public String maxEmployeeDocMAXID() {
		if (eidRepo.maxEmployeeDocMAXID() == null) {
			return "1";
		} else {
			return eidRepo.maxEmployeeDocMAXID();
		}
	}

	public List<EmployeeID> searchEmployeeIDEtails(String empID) {

		return eidRepo.searchEmployeeIdDetails(empID);
	}

	public List<EmployeeAttachments> getEmps(String empID) {
		return employeeAttachmentsRepo.getEmps(empID);
	}

	public List<EmployeeIdDocument> getAllEmployeeIdDocumentByCompany(String companyId) {
		return (List<EmployeeIdDocument>) edRepo.getAllEmployeeIdDocumentByCompany(companyId);
	}
}