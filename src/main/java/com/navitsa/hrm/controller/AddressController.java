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
import com.navitsa.hrm.entity.EmployeeAddress;
import com.navitsa.hrm.entity.EmployeeAddressField;
import com.navitsa.hrm.service.AddressService;
import com.navitsa.hrm.service.EmployeeService;

@Controller
public class AddressController {

	@Autowired
	private AddressService adService;
	
	@Autowired
	private EmployeeService empService;
	
	

	//employee address fields--------------------------------
	
	@RequestMapping(value="/employeeAddressFields", method = RequestMethod.GET)
	public String loadPage(Map<String,Object> map) {
		map.put("addressFieldForm", new EmployeeAddressField());
		EmployeeAddressField eaf = new EmployeeAddressField();
		eaf.setFieldId("00000".substring(adService.maxAfID().length()) + adService.maxAfID());
		map.put("addressFieldForm", eaf);
		return "hrm/employeeAddressFields";
	}
	
	@ModelAttribute("allAdd")
	public List<EmployeeAddressField> getAllAdd() {
		return adService.getAllAddF();
	}
	
	@RequestMapping(value = "/saveAddfiels", method = RequestMethod.POST)
	public String saveAddField(@Valid @ModelAttribute("addressFieldForm")EmployeeAddressField af,BindingResult br) {
		if(br.hasErrors()) {
			return "hrm/employeeAddressFields";
		}else {
		adService.saveAddress(af);
		return "redirect:/hrm/employeeAddressFields";
	}
	}	
	
	@RequestMapping(value = "/updateAdd", method = RequestMethod.GET)
	public ModelAndView updateAdrress(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/employeeAddressFields");
		EmployeeAddressField af = adService.getAddress(id);
		mav.addObject("addressFieldForm", af);
		return mav;
	}
	
	//employee address---------------------------------------------------
	
//	@RequestMapping(value="/employeeAddressID", method = RequestMethod.GET)
//	public String loadEaPagewithid(Map<String,Object> map ) {		
//		map.put("EmpAddressForm", new EmployeeAddress());
//		new AddressPK();
//		EmployeeAddress ea = new EmployeeAddress();		
//		map.put("EmpAddressForm", ea);
//		
//		return "employeeAddress";
//	}
	
	@ModelAttribute("allEmp")
	public List<Employee> getAllEmp() {
		return adService.getAllEmp();
	}
	
	@ModelAttribute("allEmpAdd")
	public List<EmployeeAddress> getAllEmpAdd() {
		return adService.getAllEa();
	}
	
	@RequestMapping(value = "/saveEmpAdd", method = RequestMethod.POST)
	public String saveAddField(@Valid @ModelAttribute("EmpAddressForm")EmployeeAddress ea,BindingResult br) {
		
		if(br.hasErrors()) {
			return "hrm/employeeAddress";
		}else {
			adService.saveEAddress(ea);
			return "redirect:/hrm/employeeAddress";
		}
	
	}
	
	@RequestMapping(value = "/updateEAdd", method = RequestMethod.GET)
	public ModelAndView updateEAdrress(@RequestParam("empID") String empID, @RequestParam("fieldId") String fieldId) {
		ModelAndView mav = new ModelAndView("hrm/employeeAddress");
		EmployeeAddress ea = adService.getEAddress(empID, fieldId);
		mav.addObject("EmpAddressForm", ea);
		return mav;
	}
	//load employeeAddress jsp 
	@RequestMapping(value="/employeeAddress", method = RequestMethod.GET)
	public String loadEaPage(Map<String,Object> map ) {
		
		map.put("EmpAddressForm", new EmployeeAddress());
		return "hrm/employeeAddress";
	}
	
//	@RequestMapping(value="/findEmpId", method = RequestMethod.GET)
//	public @ResponseBody Employee  findEmpId(@RequestParam String empID) {
//		Employee emp = empService.findEmpId(empID);
//		System.out.println(emp);
//		return emp;
//	}
	
	//LOAD SAVED EMPLOYEE ADDRESS  DETAILS TO GRID ACCORDING TO EMPLOYEE ID
	@RequestMapping(value="/getAddressDtails", method=RequestMethod.GET)
	public   @ResponseBody List<EmployeeAddress> comboTable(@RequestParam String empID ) {
	List<EmployeeAddress> listAddress = adService.searchaddDEtails(empID);
	return listAddress;
	}	
}
