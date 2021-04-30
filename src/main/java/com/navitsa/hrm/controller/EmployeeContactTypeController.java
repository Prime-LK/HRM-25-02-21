package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

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

import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeContactInfo;
import com.navitsa.hrm.entity.EmployeeContactType;
import com.navitsa.hrm.entity.EmployeeDependent;
import com.navitsa.hrm.service.EmployeeService;

@Controller
public class EmployeeContactTypeController {

	@Autowired
	private EmployeeService cTypeService;

	@RequestMapping(value = "/employeeContactType", method = RequestMethod.GET)
	public String loadCTypePage(Map<String, Object> map) {
		map.put("saveCType", new EmployeeContactType());
		EmployeeContactType ect = new EmployeeContactType();
		ect.setcTypeID("00000".substring(cTypeService.maxCTypeID().length()) + cTypeService.maxCTypeID());
		map.put("saveCType", ect);
		return "hrm/employeeContactType";
	}

	@ModelAttribute("cTypes")
	public List<EmployeeContactType> getAllTypes() {
		return cTypeService.getAllCTypes();
	}

	@RequestMapping(value = "/saveCType", method = RequestMethod.POST)
	public String saveCType(@Valid @ModelAttribute("saveCType") EmployeeContactType cType, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/employeeContactType";
		} else {
			cTypeService.saveCType(cType);
			return "redirect:/hrm/employeeContactType";
		}

	}

	@RequestMapping(value = "/updateCType", method = RequestMethod.GET)
	public ModelAndView updateCType(@RequestParam String cTypeID) {
		ModelAndView mav = new ModelAndView("hrm/employeeContactType");
		EmployeeContactType ct = cTypeService.getCType(cTypeID);
		mav.addObject("saveCType", ct);
		return mav;
	}

	// load employee Contact info jsp
	@RequestMapping(value = "/employeeContactInfo", method = RequestMethod.GET)
	public String loadContactInfo(Map<String, Object> map) {
		map.put("employeeContactInfo", new EmployeeContactInfo());

		return "hrm/employeeContactInfo";
	}

	// save employee Contact info
	@RequestMapping(value = "/empContactACT", method = RequestMethod.POST)
	public String saveContactInfo(@Valid EmployeeContactInfo contactInfo, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/employeeContactInfo";
		} else {
			try {
				cTypeService.saveCinfo(contactInfo);
				return "redirect:/hrm/employeeContactInfo";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/employeeContactInfo";
	}

	// get saved contact info as a list
	@ModelAttribute("cinfoList")
	public List<EmployeeContactInfo> getAllcinfoList() {
		return cTypeService.getAllsavedCinfo();
	}

	// get emps
	@ModelAttribute("Emp")
	public List<Employee> getAllEmp() {
		return cTypeService.getAllEmp();
	}

	// edit data
	@RequestMapping("/updateContactInfo")
	public ModelAndView editContactInforDAta(@RequestParam("eid") String eid, @RequestParam("cTypeID") String cTypeID) {
		ModelAndView mav = new ModelAndView("hrm/employeeContactInfo");
		EmployeeContactInfo ecID = cTypeService.getEmployeeContactInfoDataByID(eid, cTypeID);
		mav.addObject("employeeContactInfo", ecID);
		return mav;
	}
	//load emp related contact
	@RequestMapping(value="/getEmp", method=RequestMethod.GET)
	public @ResponseBody List<EmployeeContactInfo> getEmps(@RequestParam String empID) {
		List<EmployeeContactInfo> emp = cTypeService.findEmps(empID);
		return emp;
	}
	

}
