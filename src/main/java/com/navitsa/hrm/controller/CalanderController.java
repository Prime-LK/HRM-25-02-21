package com.navitsa.hrm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.CalanderEntity;
import com.navitsa.hrm.entity.CalanderEntityPK;
import com.navitsa.hrm.entity.CompanyMaster;
import com.navitsa.hrm.service.CalanderService;
import com.navitsa.hrm.service.CompanyService;

@Controller
public class CalanderController {

	@Autowired
	private CalanderService calanderService;

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/calanderOpen", method = RequestMethod.GET)
	public String calanderOpen(Map<String, Object> model) {
		model.put("calander", new CalanderEntity());
		model.put("calanderAll", calanderService.getAll());

		return "hrm/calendar";
	}

	@GetMapping("/calenderDetails")
	@ResponseBody
	public CalanderEntity getCalenderDetails(@RequestParam("date") String date, HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		CalanderEntity Cal = calanderService.getCalenderDetails(date, companyId);
		return Cal;
	}

	@PostMapping("/postcalendar")
	@ResponseBody
	public CalanderEntity setCalenderDetails(@RequestParam("date") String date, HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		CalanderEntity year = calanderService.setCalenderDetails(date, companyId);
		return year;
	}

	/*
	 * @RequestMapping(value = "/postcalendar", method = RequestMethod.POST) public
	 * CalanderEntity setCalenderDetails(@RequestParam("date") String date) {
	 * CalanderEntity year = calanderService.setCalenderDetails(date); return year;
	 * 
	 * }
	 */

	@RequestMapping(value = "/saveCalander", method = RequestMethod.POST)
	public String savecalander(@ModelAttribute("calander") CalanderEntity calander,
			/* @RequestParam("ID") Integer ID, */
			@RequestParam("date") ArrayList<String> date, @RequestParam("calander_ID") ArrayList<String> calander_ID,
			@RequestParam("description") ArrayList<String> description, @RequestParam("types") ArrayList<String> types,
			@RequestParam(value = "company_ID", required = false) String company_ID,
			@RequestParam(value = "status", required = false) String status, HttpSession session) {

		String companyId = session.getAttribute("company.comID").toString();
		CompanyMaster company = companyService.findbyCompanyid(companyId);
		List<CalanderEntity> list = new ArrayList<CalanderEntity>();

		for (int dat = 1, cID = 0, dec = 0, type = 0; dat < date.size() && cID < calander_ID.size()
				&& dec < description.size() && type < types.size(); dat++, cID++, dec++, type++) {

			CalanderEntity d = new CalanderEntity();
			CalanderEntityPK pk = new CalanderEntityPK();
			pk.setDate(date.get(dat));
			pk.setCompany(company);
			d.setCalanderEntityPK(pk);
			d.setDescription(description.get(dec));
			d.setType(types.get(type));
			d.setStatus("");
			list.add(d);

		}

		calanderService.saveEmpMoDe(list);

		return "redirect:/calanderOpen";
	}

	@RequestMapping(value = "/UpdateCalander", method = RequestMethod.POST)
	public ModelAndView updatecalander(@RequestParam String date, HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		ModelAndView mav = new ModelAndView("hrm/Calander");
		CalanderEntity Calander = calanderService.getCalenderByCompany(date, companyId);
		mav.addObject("calander", Calander);
		return mav;
	}
}
