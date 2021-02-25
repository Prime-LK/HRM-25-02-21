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
import org.springframework.web.servlet.ModelAndView;

import com.prime.hrm.entity.NationalityMaster;
import com.prime.hrm.entity.ReligionMaster;
import com.prime.hrm.service.EthnicService;

@Controller
public class EthnicController {

	@Autowired
	private EthnicService ethService;
	
	//nationality master----------------------------------------------
	
	@RequestMapping(value="/nationalityMaster", method = RequestMethod.GET)
	public String getNaPage(Map<String,Object>map) {
		map.put("saveNaMaster", new NationalityMaster());
		NationalityMaster na = new NationalityMaster();
		na.setnId("00000".substring(ethService.maxNaId().length()) + ethService.maxNaId());
		map.put("maxQmID", na);
		map.put("saveNaMaster", na);
		return "nationalityMaster";
	}
	
	@ModelAttribute("NaMaster")
	public List<NationalityMaster> getAllNa() {
		return ethService.getAllNa();
	}
	
	@RequestMapping(value="/saveNaMaster", method = RequestMethod.POST)
	public String saveNa(@Valid @ModelAttribute("saveNaMaster") NationalityMaster na ,BindingResult br) {
			if(br.hasErrors()) {
				return "nationalityMaster";	
			}else {
				ethService.saveNa(na);
				return "redirect:/nationalityMaster";	
			}
			
	}
	
	@RequestMapping(value="/updateNa", method= RequestMethod.GET)
	public ModelAndView updateNationality(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("nationalityMaster");
		NationalityMaster na = ethService.getNa(id);
		mav.addObject("saveNaMaster", na);
		return mav;
	}
	
	//religion master--------------------------------------------------
	
	@RequestMapping(value="/religionMaster", method = RequestMethod.GET)
	public String getRePage(Map<String,Object>map) {
		map.put("saveReMaster", new ReligionMaster());
		ReligionMaster re = new ReligionMaster();
		re.setRid("00000".substring(ethService.maxRmId().length()) + ethService.maxRmId());
		map.put("saveReMaster", re);
		return "religionMaster";
	}
	
	@ModelAttribute("RmMaster")
	public List<ReligionMaster> getAllRm() {
		return ethService.getAllRm();
	}
	
	@RequestMapping(value="/saveReMaster", method = RequestMethod.POST)
	public String saveNa(@Valid @ModelAttribute("saveReMaster")ReligionMaster rm,BindingResult br) {
			if(br.hasErrors()) {
				return "religionMaster";
			}else {
				ethService.saveRm(rm);
				return "redirect:/religionMaster";
			}
			
	}
	
	@RequestMapping(value="/updateRm", method= RequestMethod.GET)
	public ModelAndView updateReligion(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("religionMaster");
		ReligionMaster na = ethService.getRm(id);
		mav.addObject("saveReMaster", na);
		return mav;
	}
}
