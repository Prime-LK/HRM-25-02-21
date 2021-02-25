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

import com.prime.hrm.entity.DesignationMaster;
import com.prime.hrm.entity.JobProfileDetails;
import com.prime.hrm.entity.JobProfileMaster;
import com.prime.hrm.entity.SalaryGrade;
import com.prime.hrm.entity.SalaryRange;
import com.prime.hrm.service.JobService;

@Controller
public class JobProfileController {

	@Autowired
	private JobService jobService;

	// job profile
	// master------------------------------------------------------------------------------

	@RequestMapping(value = "/jobPmaster", method = RequestMethod.GET)
	public String load(Map<String, Object> map) {
		map.put("jobProfileMaster", new JobProfileMaster());
		JobProfileMaster jpmaster = new JobProfileMaster();
		jpmaster.setProfileID(
				"00000".substring(jobService.maxprfileMasterID().length()) + jobService.maxprfileMasterID());
		map.put("jobProfileMaster", jpmaster);
		return "jobProfileMaster";
	}

	// load saved data to table
	@ModelAttribute("jobList")
	public List<JobProfileMaster> getjobList() {
		List<JobProfileMaster> joblist = jobService.JobProfileMasterlist();
		return joblist;
	}

	// save jobmaster data
	@RequestMapping(value = "/jobPmasterAct", method = RequestMethod.POST)
	public String savePmaster(@Valid @ModelAttribute("jobProfileMaster") JobProfileMaster ProfileMaster,
			BindingResult br) {
		if (br.hasErrors()) {
			return "jobProfileMaster";
		} else {
			try {
				jobService.saveJobPMaster(ProfileMaster);
				System.out.println("hello " + ProfileMaster.getProfileID());
				return "redirect:/jobPmaster";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "jobProfileMaster";
	}

	// edit job master data
	@RequestMapping("/editJOBmaster")
	public ModelAndView editjobMaster(@RequestParam String profileID) {
		ModelAndView mav = new ModelAndView("jobProfileMaster");
		JobProfileMaster ps = jobService.jobMasterGetByID(profileID);
		mav.addObject("jobProfileMaster", ps);
		return mav;
	}

	// load salary grade jsp
	@RequestMapping(value = "/salaryGrades", method = RequestMethod.GET)
	public String SalaryGradeload(Map<String, Object> map) {
		map.put("salaryGrades", new SalaryGrade());
		SalaryGrade salary = new SalaryGrade();
		salary.setGradeID("00000".substring(jobService.maxSgid().length()) + jobService.maxSgid());
		map.put("salaryGrades", salary);
		return "salaryGrades";
	}

	// save salary grade data
	@RequestMapping(value = "/salaryGradeAct", method = RequestMethod.POST)
	public String saveSalaryGrade(@Valid @ModelAttribute("salaryGrades") SalaryGrade salarygrade, BindingResult br) {
		if (br.hasErrors()) {
			return "salaryGrades";
		} else {
			try {
				jobService.saveSalaryGrade(salarygrade);
				return "redirect:/salaryGrades";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "salaryGrades";
	}

	// load saved salary grade data
	@ModelAttribute("salaryGradeList")
	public List<SalaryGrade> getSalaryGradeList() {

		List<SalaryGrade> salaryGradeList = jobService.getlistOfSalaryGrade();
		return salaryGradeList;
	}

	// edit saved salary grade data
	@RequestMapping("/editSalaryGrade")
	public ModelAndView editSalaryGradeDAta(@RequestParam String gradeID) {
		ModelAndView mav = new ModelAndView("salaryGrades");
		SalaryGrade salaryGrade = jobService.SalaryGradeGetByID(gradeID);
		mav.addObject("salaryGrades", salaryGrade);
		return mav;
	}

	// load salary range

	@RequestMapping(value = "/salaryRange", method = RequestMethod.GET)
	public String salaryrangeload(Map<String, Object> map) {
		map.put("salaryRange", new SalaryRange());
		SalaryRange salary = new SalaryRange();
		salary.setSalaryRangeID("00000".substring(jobService.maxSRid().length()) + jobService.maxSRid());
		map.put("maxSRid", salary);
		map.put("salaryRange", salary);
		return "salaryRange";
	}

	// saved salary range
	@RequestMapping(value = "/salaryRangeAct", method = RequestMethod.POST)
	public String saveSalaryRange(@Valid @ModelAttribute("salaryRange") SalaryRange salaryR, BindingResult br) {
		if (br.hasErrors()) {
			return "salaryRange";
		} else {
			try {
				jobService.saveSalaryRange(salaryR);
				return "redirect:/salaryRange";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "salaryRange";
	}

	// load sAVED SALARY RANGE DATA
	@ModelAttribute("salaryRList")
	public List<SalaryRange> getSalaryRangeList() {

		List<SalaryRange> salaryRangeList = jobService.getlistOfSalaryRange();
		return salaryRangeList;
	}

	// edit saved range data
	@RequestMapping("/editSalaryRange")
	public ModelAndView editsalaryrangeDAta(@RequestParam String salaryRangeID) {
		ModelAndView mav = new ModelAndView("salaryRange");
		SalaryRange salaryrange = jobService.SalaryRangeGetByID(salaryRangeID);
		mav.addObject("salaryRange", salaryrange);
		return mav;
	}

	// load salary grade data on salary range jsp in combo box
	@ModelAttribute("salaryGrade")
	public List<SalaryGrade> getSalaryGrade() {
		List<SalaryGrade> gradelist = jobService.getlistOfSalaryGrade();
		return gradelist;
	}

	// load JOB PROFILE MASTER DETAILS

	@RequestMapping(value = "/jobDetails", method = RequestMethod.GET)
	public String jobDetailsload(Map<String, Object> map) {
		map.put("jobProfileDetails", new JobProfileDetails());
		JobProfileDetails JobProfile = new JobProfileDetails();
		JobProfile.setJobProfileID(
				"00000".substring(jobService.maxJobDetailsID().length()) + jobService.maxJobDetailsID());
		map.put("maxJobProfile", JobProfile);

		return "jobProfileDetails";
	}

	// job profile master combo box
	@ModelAttribute("profileMaster")
	public List<JobProfileMaster> getJobProfileMaster() {
		List<JobProfileMaster> Profilelist = jobService.JobProfileMasterlist();
		return Profilelist;
	}

	@ModelAttribute("detaillist")
	public List<JobProfileDetails> getJobProfileDetailsList() {

		List<JobProfileDetails> jobProfileDetailsList = jobService.getlistOfjobProfile();
		return jobProfileDetailsList;
	}

	@RequestMapping("/editjobProfile")
	public ModelAndView editjobProfileDAta(@RequestParam String jobProfileID) {
		ModelAndView mav = new ModelAndView("jobProfileDetails");
		JobProfileDetails jobProfileDetailt = jobService.jobProfileGetByID(jobProfileID);
		mav.addObject("jobProfileDetails", jobProfileDetailt);
		return mav;
	}

	@RequestMapping(value = "/jobProfileDetailsAct", method = RequestMethod.POST)
	public String saveProfileDetailse(@Valid @ModelAttribute("jobProfileDetails") JobProfileDetails jobProfileDetails,
			BindingResult br) {
		if (br.hasErrors()) {
			return "jobProfileDetails";
		} else {
			try {
				jobService.savejobProfile(jobProfileDetails);
				return "redirect:/jobDetails";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "jobProfileDetails";
	}

	// load designation master jsp
	@RequestMapping(value = "/designationmaster", method = RequestMethod.GET)
	public String designationmasterload(Map<String, Object> map) {
		map.put("DesignationMaster", new DesignationMaster());
		DesignationMaster dm = new DesignationMaster();
		dm.setDid("00000".substring(jobService.maxDesignationID().length()) + jobService.maxDesignationID());
		map.put("maxDesignationID", dm);

		return "DesignationMaster";
	}

	// save designation data
	@RequestMapping(value = "/designationMasterAct", method = RequestMethod.POST)
	public String savedesignationmaster(@Valid @ModelAttribute("DesignationMaster") DesignationMaster dmster,
			BindingResult br) {
		if (br.hasErrors()) {
			return "DesignationMaster";
		} else {
			try {
				jobService.saveDesignation(dmster);
				return "redirect:/designationmaster";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "DesignationMaster";
	}

	// load data
	@ModelAttribute("listmaster")
	public List<DesignationMaster> getJDetailsList() {

		List<DesignationMaster> DetailsList = jobService.getlistOfDesignationMaster();
		return DetailsList;
	}

	// edit data
	@RequestMapping("/editDesignation")
	public ModelAndView editDesignationDAta(@RequestParam String did) {
		ModelAndView mav = new ModelAndView("DesignationMaster");
		DesignationMaster dm = jobService.MasterGetByID(did);
		mav.addObject("DesignationMaster", dm);
		return mav;
	}
}
