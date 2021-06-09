package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.DepartmentMaster;
import com.navitsa.hrm.service.DepartmentService;

@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService depService;

	@GetMapping("/Department")
	public String loadDepPage(Map<String, Object> map) {
		map.put("departmentMaster", new DepartmentMaster());
		DepartmentMaster dep = new DepartmentMaster();
		dep.setDepID("00000".substring(depService.getDID().length()) + depService.getDID());
		map.put("departmentMaster", dep);
		return "hrm/departmentMaster";
	}

	@ModelAttribute("allDeps")
	public List<DepartmentMaster> getAllDepartmentByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return depService.getAllDepartmentByCompany(companyId);
	}

	@PostMapping("/saveDepartment")
	public String saveDepartment(@Valid @ModelAttribute("departmentMaster") DepartmentMaster depMaster,
			BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/departmentMaster";
		} else {
			try {
				depService.saveDepData(depMaster);
				return "redirect:/Department";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/departmentMaster";
	}

	@GetMapping("/UpdateDepartment")
	public ModelAndView updateDepartment(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView("hrm/departmentMaster");
		DepartmentMaster dm = depService.getID(id);
		mav.addObject("departmentMaster", dm);
		return mav;
	}

}
