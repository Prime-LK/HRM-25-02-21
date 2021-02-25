package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.DesignationMaster;
import com.prime.hrm.entity.JobProfileDetails;
import com.prime.hrm.entity.JobProfileMaster;
import com.prime.hrm.entity.SalaryGrade;
import com.prime.hrm.entity.SalaryRange;
import com.prime.hrm.repository.DesignationMasterRepository;
import com.prime.hrm.repository.JobProfileDetailsRepository;
import com.prime.hrm.repository.JobProfileMasterRepository;
import com.prime.hrm.repository.SalaryGradeRepository;
import com.prime.hrm.repository.SalaryRangeRepository;

@Service
public class JobService {
		
	@Autowired
	private JobProfileMasterRepository jobrepo;
	
	@Autowired
	SalaryGradeRepository salaryGradeRepo;
	
	@Autowired
	SalaryRangeRepository salaryRangeRepo;
	
	@Autowired
	JobProfileDetailsRepository jobprofileDetailsRepo;
	
	@Autowired
	DesignationMasterRepository designationRepo;
	
	
	//save job master data
		public void saveJobPMaster(JobProfileMaster jobProfileMaster) {
			jobrepo.save(jobProfileMaster);
		}

		
		//get max id for profile master
		public String maxprfileMasterID() {
			if (jobrepo.profileID() == null) {
				return "1";
			} else {
				return jobrepo.profileID();
			}
		}
		
		//get list of job master saved data
		public List<JobProfileMaster> JobProfileMasterlist(){
			return (List<JobProfileMaster>) jobrepo.findAll();
		}
		
		//edit job master data
		public JobProfileMaster jobMasterGetByID(String profileID) {
			JobProfileMaster getJob=jobrepo.findById(profileID).get();
			return getJob;
		}
		
		//save salary grade data
		public void saveSalaryGrade(SalaryGrade salaryGrade) {
			salaryGradeRepo.save(salaryGrade);
		}
		
		//get max id on salary grade jsp
		public String maxSgid() {
			if (salaryGradeRepo.maxSgid() == null) {
				return "1";
			} else {

				return salaryGradeRepo.maxSgid();
			}

		}
		
		//get salary grade data as list
		public List<SalaryGrade> getlistOfSalaryGrade(){
			return (List<SalaryGrade>) salaryGradeRepo.findAll();
		}
		
		//edit salary grade data
		public SalaryGrade SalaryGradeGetByID(String profileID) {
			SalaryGrade getSalaryGrade=salaryGradeRepo.findById(profileID).get();
			return getSalaryGrade;
		}
		
		//save salary range data
		public void saveSalaryRange(SalaryRange salaryRange) {
			salaryRangeRepo.save(salaryRange);
			
		}
		
		//load salary range data to jsp as a list
		public List<SalaryRange> getlistOfSalaryRange(){
			return (List<SalaryRange>) salaryRangeRepo.findAll();
		}
		
		//edit salary range
		public SalaryRange SalaryRangeGetByID(String salaryRangeID) {
			SalaryRange getSalaryRange=salaryRangeRepo.findById(salaryRangeID).get();
			return getSalaryRange;
		}
		
		//get max id on salary range jsp
			public String maxSRid() {
				if (salaryRangeRepo.maxSRid() == null) {
					return "1";
				} else {

					return salaryRangeRepo.maxSRid();
				}

			}
		
		//save job profile
			
		public void savejobProfile(JobProfileDetails jobProfileDetails) {
			jobprofileDetailsRepo.save(jobProfileDetails);
		}
		
		// get nextmaxid
		public String maxJobDetailsID() {
			if (jobprofileDetailsRepo.maxJobDetailsID() == null) {
				return "1";
			} else {

				return jobprofileDetailsRepo.maxJobDetailsID();
			}

		}
		
		// get list of job profile
		public List<JobProfileDetails> getlistOfjobProfile(){
			return (List<JobProfileDetails>) jobprofileDetailsRepo.findAll();
		}
		
		//edit
		public JobProfileDetails jobProfileGetByID(String jobProfileID) {
			JobProfileDetails jobProfileDetails=jobprofileDetailsRepo.findById(jobProfileID).get();
			return jobProfileDetails;
		}
		
		//save designation master data
		public void saveDesignation(DesignationMaster dm) {
			designationRepo.save(dm);
		}
		
		// get list of designation master data
		public List<DesignationMaster> getlistOfDesignationMaster(){
			return (List<DesignationMaster>) designationRepo.findAll();
		}
		
		//edit
		public DesignationMaster MasterGetByID(String did) {
			DesignationMaster dm=designationRepo.findById(did).get();
			return dm;
		}
		
		//get Maxid
		public String maxDesignationID() {
			if (designationRepo.maxDesignationID() == null) {
				return "1";
			} else {

				return designationRepo.maxDesignationID();
			}

		}
		
	
}
