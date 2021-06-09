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

import com.navitsa.hrm.entity.DesignationMaster;
import com.navitsa.hrm.entity.JobProfileDetails;
import com.navitsa.hrm.entity.JobProfileMaster;
import com.navitsa.hrm.entity.SalaryGrade;
import com.navitsa.hrm.entity.SalaryRange;
import com.navitsa.hrm.service.JobService;

@Controller
public class JobProfileController {

	@Autowired
	private JobService jobService;

	// job profile
	// master------------------------------------------------------------------------------

	@RequestMapping(value = "/JobProfile", method = RequestMethod.GET)
	public String load(Map<String, Object> map) {
		map.put("jobProfileForm", new JobProfileMaster());
		JobProfileMaster jpmaster = new JobProfileMaster();
		jpmaster.setProfileID(
				"00000".substring(jobService.maxprfileMasterID().length()) + jobService.maxprfileMasterID());
		map.put("jobProfileForm", jpmaster);
		return "hrm/jobProfileMaster";
	}

	// load saved data to table
	@ModelAttribute("jobList")
	public List<JobProfileMaster> getAllJobProfileByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return jobService.getAllJobProfileByCompany(companyId);
	}

	// save jobmaster data
	@RequestMapping(value = "/saveJobProfile", method = RequestMethod.POST)
	public String saveJobProfile(@Valid @ModelAttribute("jobProfileForm") JobProfileMaster ProfileMaster,
			BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/jobProfileMaster";
		} else {
			try {
				jobService.saveJobPMaster(ProfileMaster);
				System.out.println("hello " + ProfileMaster.getProfileID());
				return "redirect:/JobProfile";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/jobProfileMaster";
	}

	// edit job master data
	@RequestMapping("/UpdateJobProfile")
	public ModelAndView updateJobProfile(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/jobProfileMaster");
		JobProfileMaster ps = jobService.jobMasterGetByID(id);
		mav.addObject("jobProfileForm", ps);
		return mav;
	}

	// load salary grade jsp
	@RequestMapping(value = "/SalaryGrade", method = RequestMethod.GET)
	public String SalaryGradeload(Map<String, Object> map) {
		map.put("salaryGradeForm", new SalaryGrade());
		SalaryGrade salary = new SalaryGrade();
		salary.setGradeID("00000".substring(jobService.maxSgid().length()) + jobService.maxSgid());
		map.put("salaryGradeForm", salary);
		return "hrm/salaryGrades";
	}

	// save salary grade data
	@RequestMapping(value = "/saveSalaryGrade", method = RequestMethod.POST)
	public String saveSalaryGrade(@Valid @ModelAttribute("salaryGradeForm") SalaryGrade salarygrade, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/salaryGrades";
		} else {
			try {
				jobService.saveSalaryGrade(salarygrade);
				return "redirect:/SalaryGrade";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/salaryGrades";
	}

	// load saved salary grade data
	@ModelAttribute("salaryGradeList")
	public List<SalaryGrade> getAllSalaryGradeByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return jobService.getAllSalaryGradeByCompany(companyId);
	}

	// edit saved salary grade data
	@RequestMapping("/UpdateSalaryGrade")
	public ModelAndView updateSalaryGrade(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/salaryGrades");
		SalaryGrade salaryGrade = jobService.SalaryGradeGetByID(id);
		mav.addObject("salaryGradeForm", salaryGrade);
		return mav;
	}

	// load salary range

	@RequestMapping(value = "/SalaryRange", method = RequestMethod.GET)
	public String salaryrangeload(Map<String, Object> map) {
		map.put("salaryRangeForm", new SalaryRange());
		SalaryRange salary = new SalaryRange();
		salary.setSalaryRangeID("00000".substring(jobService.maxSRid().length()) + jobService.maxSRid());
		map.put("maxSRid", salary);
		map.put("salaryRangeForm", salary);
		return "hrm/salaryRange";
	}

	// saved salary range
	@RequestMapping(value = "/saveSalaryRange", method = RequestMethod.POST)
	public String saveSalaryRange(@Valid @ModelAttribute("salaryRangeForm") SalaryRange salaryR, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/salaryRange";
		} else {
			try {
				jobService.saveSalaryRange(salaryR);
				return "redirect:/SalaryRange";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/salaryRange";
	}

	// load sAVED SALARY RANGE DATA
	@ModelAttribute("salaryRList")
	public List<SalaryRange> getAllSalaryRangeByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return jobService.getAllSalaryRangeByCompany(companyId);
	}

	// edit saved range data
	@RequestMapping("/UpdateSalaryRange")
	public ModelAndView updateSalaryRange(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/salaryRange");
		SalaryRange salaryrange = jobService.SalaryRangeGetByID(id);
		mav.addObject("salaryRangeForm", salaryrange);
		return mav;
	}

	// load salary grade data on salary range jsp in combo box
	@ModelAttribute("salaryGrade")
	public List<SalaryGrade> getSalaryGrade() {
		List<SalaryGrade> gradelist = jobService.getlistOfSalaryGrade();
		return gradelist;
	}

	// load JOB PROFILE MASTER DETAILS

	@RequestMapping(value = "/JobProfileDetails", method = RequestMethod.GET)
	public String jobDetailsload(Map<String, Object> map) {
		map.put("jobProfileDetailsForm", new JobProfileDetails());
		JobProfileDetails JobProfile = new JobProfileDetails();
		JobProfile.setJobProfileID(
				"00000".substring(jobService.maxJobDetailsID().length()) + jobService.maxJobDetailsID());
		map.put("maxJobProfile", JobProfile);

		return "hrm/jobProfileDetails";
	}

	// job profile master combo box
	@ModelAttribute("profileMaster")
	public List<JobProfileMaster> getJobProfileMaster() {
		List<JobProfileMaster> Profilelist = jobService.JobProfileMasterlist();
		return Profilelist;
	}

	@ModelAttribute("detaillist")
	public List<JobProfileDetails> getAllJobProfileDetailsByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return jobService.getAllJobProfileDetailsByCompany(companyId);
	}

	@RequestMapping("/UpdateJobProfileDetails")
	public ModelAndView updateJobProfileDetailsDAta(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/jobProfileDetails");
		JobProfileDetails jobProfileDetailt = jobService.jobProfileGetByID(id);
		mav.addObject("jobProfileDetailsForm", jobProfileDetailt);
		return mav;
	}

	@RequestMapping(value = "/saveJobProfileDetails", method = RequestMethod.POST)
	public String saveJobProfileDetails(
			@Valid @ModelAttribute("jobProfileDetailsForm") JobProfileDetails jobProfileDetails, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/jobProfileDetails";
		} else {
			try {
				jobService.savejobProfile(jobProfileDetails);
				return "redirect:/JobProfileDetails";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/jobProfileDetails";
	}

	// load designation master jsp
	@RequestMapping(value = "/Designation", method = RequestMethod.GET)
	public String designationmasterload(Map<String, Object> map) {
		map.put("designationForm", new DesignationMaster());
		DesignationMaster dm = new DesignationMaster();
		dm.setDid("00000".substring(jobService.maxDesignationID().length()) + jobService.maxDesignationID());
		map.put("maxDesignationID", dm);

		return "hrm/DesignationMaster";
	}

	// save designation data
	@RequestMapping(value = "/saveDesignation", method = RequestMethod.POST)
	public String saveDesignation(@Valid @ModelAttribute("designationForm") DesignationMaster dmster,
			BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/DesignationMaster";
		} else {
			try {
				jobService.saveDesignation(dmster);
				return "redirect:/Designation";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/DesignationMaster";
	}

	// load data
	@ModelAttribute("listmaster")
	public List<DesignationMaster> getAllDesignationMasterByCompany(HttpSession session) {
		String companyId = session.getAttribute("company.comID").toString();
		return jobService.getAllDesignationMasterByCompany(companyId);
	}

	// edit data
	@RequestMapping("/UpdateDesignation")
	public ModelAndView updateDesignation(@RequestParam String id) {
		ModelAndView mav = new ModelAndView("hrm/DesignationMaster");
		DesignationMaster dm = jobService.MasterGetByID(id);
		mav.addObject("designationForm", dm);
		return mav;
	}
}
