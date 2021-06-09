package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.EmployeeExtraActivity;
import com.navitsa.hrm.entity.ExtraActivityType;
import com.navitsa.hrm.repository.EmployeeExtraActivityRepository;
import com.navitsa.hrm.repository.ExtraActivityTypeRepository;

@Service
public class ExtraActivityService {

	@Autowired
	private ExtraActivityTypeRepository atRepo;

	@Autowired
	private EmployeeExtraActivityRepository eetRepo;

	// extra type activity---------------------------------------

	public String maxAtID() {
		if (atRepo.maxActTypeID() == null) {
			return "1";
		} else {
			return atRepo.maxActTypeID();
		}
	}

	public void saveAType(ExtraActivityType at) {
		atRepo.save(at);
	}

	public List<ExtraActivityType> getAllAt() {
		return (List<ExtraActivityType>) atRepo.findAll();
	}

	public ExtraActivityType getAType(String id) {
		return atRepo.findById(id).get();
	}

	// employee extra activity------------------------------------------

	public void saveEet(EmployeeExtraActivity eet) {
		eetRepo.save(eet);
	}

	public List<EmployeeExtraActivity> getAllEet() {
		return (List<EmployeeExtraActivity>) eetRepo.findAll();
	}

//	public EmployeeExtraActivity getEet(String id) {
//		return eetRepo.findById(id).get();
//	}

	public EmployeeExtraActivity getEmployeeExtraActivity(String empID, String actTypeID) {
		return eetRepo.setEmployeeExtraActivityDetails(empID, actTypeID);
	}

	// load EXTRA ACTIVITY details according to employeeID to empextra activity jsp
	public List<EmployeeExtraActivity> searchEmployeeExtraActivity(String empID) {

		return eetRepo.searchEmployeeExtraActivityDetails(empID);
	}

	public List<ExtraActivityType> getAllExtraActivityTypeByCompany(String companyId) {
		return (List<ExtraActivityType>) atRepo.getAllExtraActivityTypeByCompany(companyId);
	}
}
