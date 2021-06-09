package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.DependentTypeMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeDependent;
import com.navitsa.hrm.service.DependentService;

@Controller
public class DependentController {

	@Autowired
	private DependentService deService;

	// depndent type master-----------------------

	@RequestMapping(value = "/DependentType", method = RequestMethod.GET)
	public String loadPage(Map<String, Object> map) {
		map.put("saveDependentType", new DependentTypeMaster());
		DependentTypeMaster dtm = new DependentTypeMaster();
		dtm.setdTypeID("00000".substring(deService.dTypeID().length()) + deService.dTypeID());
		map.put("saveDependentType", dtm);
		return "hrm/dependentTypeMaster";
	}

	@ModelAttribute("deTypes")
	public List<DependentTypeMaster> getAllDependentTypeByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return deService.getAllDependentTypeByCompany(companyId);
	}

	@RequestMapping(value = "/saveDependentType", method = RequestMethod.POST)
	public String saveDType(@Valid @ModelAttribute("saveDependentType") DependentTypeMaster dType, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/dependentTypeMaster";
		} else {
			deService.saveDType(dType);
			return "redirect:/DependentType";
		}

	}

	@RequestMapping(value = "/UpdateDependentType", method = RequestMethod.GET)
	public ModelAndView updateDependentType(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/dependentTypeMaster");
		DependentTypeMaster dtm = deService.getdType(id);
		mav.addObject("saveDependentType", dtm);
		return mav;
	}

	// emp dependents-----------------------------------------------------

	@RequestMapping(value = "/employeeDependent", method = RequestMethod.GET)
	public String loadEDPage(Map<String, Object> map) {
		map.put("saveDependent", new DependentTypeMaster());
		EmployeeDependent ed = new EmployeeDependent();
		map.put("saveDependent", ed);

		return "hrm/employeeDependent";
	}

	@ModelAttribute("Emp")
	public List<Employee> getAllEmp() {
		return deService.getAllEmp();
	}

	@ModelAttribute("dType")
	public List<DependentTypeMaster> getAllDType() {
		return deService.getAllDType();
	}

	@ModelAttribute("eDep")
	public List<EmployeeDependent> getAllEmpDep() {
		return deService.getAllEmpDep();
	}

	@RequestMapping(value = "/updateEDep", method = RequestMethod.GET)
	public ModelAndView updateEDep(@RequestParam("empID") String empID, @RequestParam("dTypeID") String dTypeID) {
		ModelAndView mav = new ModelAndView("hrm/employeeDependent");
		EmployeeDependent ed = deService.getEdDataByID(empID, dTypeID);
		mav.addObject("saveDependent", ed);
		return mav;
	}

	@RequestMapping(value = "/saveDependent", method = RequestMethod.POST)
	public String saveEmpDep(@Valid @ModelAttribute("saveDependent") EmployeeDependent ed, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/employeeDependent";
		} else {
			try {
				deService.saveEmpDep(ed);
				return "redirect:/hrm/employeeDependent";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/employeeDependent";
	}

	// load emp dependents with id
//	@RequestMapping(value="/employeeDependentwithid", method = RequestMethod.GET)
//	public String loadEDPagewithid(Map<String,Object> map, @RequestParam("eid") String eid) {
//		map.put("saveDependent", new DependentTypeMaster());
//		EmployeeDependent ed = new EmployeeDependent();
//		map.put("saveDependent", ed);
//		map.put("eid", eid);
//		return "employeeDependent";
//	}

	// load emp related dependent
	@RequestMapping(value = "/findEmps", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeDependent> getEmps(@RequestParam("empID") String empID) {
		List<EmployeeDependent> ed = deService.getEmps(empID);
		return ed;
	}
}
