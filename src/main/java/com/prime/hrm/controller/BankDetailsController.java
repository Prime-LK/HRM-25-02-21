package com.prime.hrm.controller;

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

import com.prime.hrm.entity.Bank;
import com.prime.hrm.entity.BankDetails;
import com.prime.hrm.entity.BankDetailsPK;
import com.prime.hrm.entity.BankMaster;

import com.prime.hrm.entity.Employee;
import com.prime.hrm.entity.EmployeeDependent;
import com.prime.hrm.service.BankDetailsService;
import com.prime.hrm.service.EmployeeService;

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
		return "bankDetails";
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
			return "bankDetails";
		} else {
			try {
				baService.saveBa(ba);
				return "redirect:/bankDetails";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "bankDetails";
	}

	@RequestMapping(value = "/updateBa", method = RequestMethod.GET)
	public ModelAndView updateDetails(@RequestParam("empID") String empID, @RequestParam("acc") String acc) {
		ModelAndView mav = new ModelAndView("bankDetails");
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

	//Bank page---------------------------------------------------
	
	// load bank jsp
	@RequestMapping(value = "/bankInfor", method = RequestMethod.GET)
	public String loadBankPage(Map<String, Object> map) {
		map.put("bank", new Bank());
		Bank bank = new Bank();
		bank.setBranchID("00000".substring(baService.getMaxID().length()) + baService.getMaxID());
		map.put("bank", bank);
		return "bank";

	}

	// save bank jsp data
	@RequestMapping(value = "/saveBank", method = RequestMethod.POST)
	public String saveBank(@Valid @ModelAttribute("bank") Bank bank, BindingResult br) {
		if (br.hasErrors()) {
			return "bank";
		} else {
			try {
				baService.saveBank(bank);
				return "redirect:/bankInfor";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "bank";
	}

	// show all saved bank data
	@ModelAttribute("bankLists")
	public List<Bank> showBank() {
		return baService.getAllSavedBank();
	}

	// edit saved bank data
	@RequestMapping(value = "/updateBank", method = RequestMethod.GET)
	public ModelAndView updateBankDetails(@RequestParam("branchID") String branchID) {
		ModelAndView mav = new ModelAndView("bank");
		Bank bdetails = baService.updateDetails(branchID);
		mav.addObject("bank", bdetails);
		return mav;
	}

	// load bank master details
	@RequestMapping(value = "/bank", method = RequestMethod.GET)
	public String loadBank(Map<String, Object> map) {
		map.put("bankmaster", new BankMaster());
		BankMaster bm = new BankMaster();
		bm.setBankid("00000".substring(baService.getMaxBMID().length()) + baService.getMaxBMID());
		map.put("bankmaster", bm);
		return "bankmaster";

	}

	// save bank master data
	@RequestMapping(value = "/saveBankInfor", method = RequestMethod.POST)
	public String saveBankdata(@Valid @ModelAttribute("bankmaster") BankMaster bank, BindingResult br) {
		if (br.hasErrors()) {
			return "bankmaster";
		} else {
			baService.saveBankMAster(bank);
			return "redirect:/bank";
		}
	}

	// load saved bank master data
	@ModelAttribute("bankmastertable")
	public List<BankMaster> showBankmaster() {
		return baService.getAllBankdata();
	}

	// update bank master data
	@RequestMapping(value = "/updateBankmaster", method = RequestMethod.GET)
	public ModelAndView updateBankMAsterDetails(@RequestParam("bankid") String bankid) {
		ModelAndView mav = new ModelAndView("bankmaster");
		BankMaster bmaster = baService.getDetailsofBank(bankid);
		mav.addObject("bankmaster", bmaster);
		return mav;
	}

}
