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

import com.navitsa.hrm.entity.Bank;
import com.navitsa.hrm.entity.BankDetails;
import com.navitsa.hrm.entity.BankDetailsPK;
import com.navitsa.hrm.entity.BankMaster;
import com.navitsa.hrm.entity.Employee;
import com.navitsa.hrm.entity.EmployeeDependent;
import com.navitsa.hrm.service.BankDetailsService;
import com.navitsa.hrm.service.EmployeeService;

@Controller
public class BankDetailsController {

	@Autowired
	private BankDetailsService baService;

	@Autowired
	private EmployeeService empService;

	// bank details--------------------------------

	@RequestMapping(value = "/bankDetails", method = RequestMethod.GET)
	public String loadBaPage(Map<String, Object> map) {
		map.put("bankDetailsForm", new BankDetails());
		Bank bank = new Bank();
		map.put("id", bank);
		return "hrm/bankDetails";
	}

	@ModelAttribute("allBd")
	public List<BankDetails> getAllBd() {
		return baService.getAllBa();
	}

	@ModelAttribute("allEmp")
	public List<Employee> getAllEmp() {
		return baService.getAllEmp();
	}

	@RequestMapping(value = "/saveBaDetails", method = RequestMethod.POST)
	public String saveBa(@Valid @ModelAttribute("bankDetailsForm") BankDetails ba, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/bankDetails";
		} else {
			try {
				baService.saveBa(ba);
				return "redirect:/bankDetails";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/bankDetails";
	}

	@RequestMapping(value = "/updateBa", method = RequestMethod.GET)
	public ModelAndView updateDetails(@RequestParam("empID") String empID, @RequestParam("acc") String acc) {
		ModelAndView mav = new ModelAndView("hrm/bankDetails");
		BankDetails bd = baService.updateBankDetails(empID, acc);
		mav.addObject("bankDetailsForm", bd);
		return mav;
	}

	// load emp related bankdetails
	@RequestMapping(value = "/loadEmps", method = RequestMethod.GET)
	public @ResponseBody List<BankDetails> getEmps(@RequestParam String empID) {
		List<BankDetails> bd = baService.getEmps(empID);
		return bd;
	}

	// Bank page---------------------------------------------------

	// load bank jsp
	@RequestMapping(value = "/BankBranch", method = RequestMethod.GET)
	public String loadBankPage(Map<String, Object> map) {
		map.put("bank", new Bank());
		Bank bank = new Bank();
		bank.setBranchID("00000".substring(baService.getMaxID().length()) + baService.getMaxID());
		map.put("bank", bank);
		return "hrm/bank";

	}

	// save bank jsp data
	@RequestMapping(value = "/saveBankBranch", method = RequestMethod.POST)
	public String saveBankBranch(@Valid @ModelAttribute("bank") Bank bank, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/bank";
		} else {
			try {
				baService.saveBank(bank);
				return "redirect:/BankBranch";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/bank";
	}

	// show all saved bank data
	@ModelAttribute("bankLists")
	public List<Bank> getAllBankBranchByCompany(HttpSession session) {
		//String companyId = session.getAttribute("company.comID").toString();
		return baService.getAllSavedBank();
	}

	// edit saved bank data
	@RequestMapping(value = "/UpdateBankBranch", method = RequestMethod.GET)
	public ModelAndView updateBankBranch(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView("hrm/bank");
		Bank bdetails = baService.updateDetails(id);
		mav.addObject("bank", bdetails);
		return mav;
	}

	// load bank master details
	@RequestMapping(value = "/Bank", method = RequestMethod.GET)
	public String loadBank(Map<String, Object> map) {
		map.put("bankmaster", new BankMaster());
		BankMaster bm = new BankMaster();
		//bm.setBankid("00000".substring(baService.getMaxBMID().length()) + baService.getMaxBMID());
		map.put("bankmaster", bm);
		return "hrm/bankmaster";

	}

	// save bank master data
	@RequestMapping(value = "/saveBank", method = RequestMethod.POST)
	public String saveBank(@Valid @ModelAttribute("bankmaster") BankMaster bank, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/bankmaster";
		} else {
			baService.saveBankMAster(bank);
			return "redirect:/Bank";
		}
	}

	// load saved bank master data
	@ModelAttribute("bankmastertable")
	public List<BankMaster> getAllBankByCompany(HttpSession session) {
		//String companyId = session.getAttribute("company.comID").toString();
		return baService.getAllBankdata();
	}

	// update bank master data
	@RequestMapping(value = "/UpdateBank", method = RequestMethod.GET)
	public ModelAndView updateBank(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView("hrm/bankmaster");
		BankMaster bmaster = baService.getDetailsofBank(id);
		mav.addObject("bankmaster", bmaster);
		return mav;
	}

}
