package com.navitsa.hrm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.service.CompanyService;
import com.navitsa.hrm.service.EmployeeService;

@Controller
public class LoginController {
	
	@Autowired
	private CompanyService comService;
	
	@Autowired
	private EmployeeService empService;
	
	// load login
	@RequestMapping("/")
	public ModelAndView getLoginPage() {
		ModelAndView mav = new ModelAndView("hrm/logIn");
		List<CompanyMaster> companyList = comService.getAllComDetails(); 
		mav.addObject("allComMas", companyList);
	return mav;
	}
	// delete session attribute using in emp edit
	@RequestMapping("/logout")
	public String logout(Employee emp, WebRequest request, HttpSession session, HttpServletRequest r) {
		// ModelAndView mav=new ModelAndView("login");
		session.invalidate();
//		session.setAttribute("eid", "empty");
//		session.removeAttribute("eid");
//		session.setAttribute("ename", "empty");
//		session.removeAttribute("ename");
//		session.setAttribute("eImg", "empty");
//		session.removeAttribute("eImg");
		return "redirect:/";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView validateEmp(@RequestParam("name") String name, @RequestParam("password") String password,
			@RequestParam("company.comID") String comID, HttpSession session, HttpServletRequest request,
			@ModelAttribute("login") Employee employee) {

		ModelAndView mav = null;

		List<Employee> emp = empService.findEmp(name, password, comID);
		if (emp != null && emp.size() > 0) {
			session = request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("empImg", emp.get(0).getProfileImgView());
			session.setAttribute("empID", emp.get(0).getEmpID());
			session.setAttribute("empName", emp.get(0).getName());
			session.setAttribute("password", emp.get(0).getPassword());
			session.setAttribute("company.comID", emp.get(0).getCompany().getComID());
			session.setAttribute("centerName", emp.get(0).getCompany().getComName());
			mav = new ModelAndView("hrm/hrmdashboard");
			mav.addObject("empID", emp.get(0).getEmpID());
			mav.addObject("empImg", employee.getProfileImg());
			mav.addObject("empName", employee.getName());
			mav.addObject("password", emp.get(0).getPassword());
			mav.addObject("company.comID", emp.get(0).getCompany().getComID());
		} else {
			mav = new ModelAndView("hrm/logIn");
			List<CompanyMaster> companyList = comService.getAllComDetails(); 
			mav.addObject("allComMas", companyList);
			mav.addObject("msg", "Invalid UserID Or Password");
		}
		return mav;
	}
	

}
