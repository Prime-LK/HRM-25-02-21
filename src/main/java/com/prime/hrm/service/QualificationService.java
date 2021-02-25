package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.EmployeeQualification;
import com.prime.hrm.entity.EmployeeSkill;
import com.prime.hrm.entity.QualificationMaster;
import com.prime.hrm.repository.EmployeeQualificationRepository;
import com.prime.hrm.repository.QualificationRepository;

@Service
public class QualificationService {

	@Autowired
	private QualificationRepository qmRepo;

	@Autowired
	private EmployeeQualificationRepository eqRepo;

	// employee master----------------------------------------------------

	public QualificationMaster getQa(String qid) {
		return qmRepo.findById(qid).get();
	}

	public void saveQm(QualificationMaster qm) {
		qmRepo.save(qm);
	}

	public List<QualificationMaster> getAllQm() {
		return (List<QualificationMaster>) qmRepo.findAll();
	}

	public String maxQmID() {
		if (qmRepo.maxQaID() == null) {
			return "1";
		} else {
			return qmRepo.maxQaID();
		}
	}

//	public EmployeeQualification getEq(String id) {
//		return eqRepo.findById(id).get();
//	}

	public void saveEq(EmployeeQualification eq) {
		eqRepo.save(eq);
	}

	public List<EmployeeQualification> getAllEq() {
		return (List<EmployeeQualification>) eqRepo.findAll();
	}

	// edit employee qualification jsp

	public EmployeeQualification setqdata(String empID, String qid) {
		return eqRepo.setEdit(empID, qid);
	}

	// load qualification details according to employeeID to empQualification jsp
	public List<EmployeeQualification> searchEmployeeQualification(String empID) {

		return eqRepo.getEmpQualificationData(empID);
	}	

	// get data for jasper employee qualification report
	public String[][] getEmpqualificationReportData(String  qid) {
		return eqRepo.getempQualificationSummaryReportData(qid);

	}

	// get data to employee qualification jsp
	public List<EmployeeQualification> getfindAlleq() {
		return (List<EmployeeQualification>) eqRepo.getfindAll();
	}

}
