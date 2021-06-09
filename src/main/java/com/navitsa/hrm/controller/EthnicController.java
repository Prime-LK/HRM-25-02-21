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
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.NationalityMaster;
import com.navitsa.hrm.entity.ReligionMaster;
import com.navitsa.hrm.service.EthnicService;

@Controller
public class EthnicController {

	@Autowired
	private EthnicService ethService;

	// nationality master----------------------------------------------

	@RequestMapping(value = "/Nationality", method = RequestMethod.GET)
	public String getNaPage(Map<String, Object> map) {
		map.put("saveNationality", new NationalityMaster());
		NationalityMaster na = new NationalityMaster();
		na.setnId("00000".substring(ethService.maxNaId().length()) + ethService.maxNaId());
		map.put("maxQmID", na);
		map.put("saveNationality", na);
		return "hrm/nationalityMaster";
	}

	@ModelAttribute("NaMaster")
	public List<NationalityMaster> getAllNationalityByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return ethService.getAllNationalityByCompany(companyId);
	}

	@RequestMapping(value = "/saveNationality", method = RequestMethod.POST)
	public String saveNa(@Valid @ModelAttribute("saveNationality") NationalityMaster na, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/nationalityMaster";
		} else {
			ethService.saveNa(na);
			return "redirect:/Nationality";
		}

	}

	@RequestMapping(value = "/UpdateNationality", method = RequestMethod.GET)
	public ModelAndView updateNationality(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/nationalityMaster");
		NationalityMaster na = ethService.getNa(id);
		mav.addObject("saveNationality", na);
		return mav;
	}

	// religion master--------------------------------------------------

	@RequestMapping(value = "/Religion", method = RequestMethod.GET)
	public String getRePage(Map<String, Object> map) {
		map.put("saveReligion", new ReligionMaster());
		ReligionMaster re = new ReligionMaster();
		re.setRid("00000".substring(ethService.maxRmId().length()) + ethService.maxRmId());
		map.put("saveReligion", re);
		return "hrm/religionMaster";
	}

	@ModelAttribute("RmMaster")
	public List<ReligionMaster> getAllReligionByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return ethService.getAllReligionByCompany(companyId);
	}

	@RequestMapping(value = "/saveReligion", method = RequestMethod.POST)
	public String saveReligion(@Valid @ModelAttribute("saveReligion") ReligionMaster rm, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/religionMaster";
		} else {
			ethService.saveRm(rm);
			return "redirect:/Religion";
		}

	}

	@RequestMapping(value = "/UpdateReligion", method = RequestMethod.GET)
	public ModelAndView updateReligion(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/religionMaster");
		ReligionMaster na = ethService.getRm(id);
		mav.addObject("saveReligion", na);
		return mav;
	}
}
