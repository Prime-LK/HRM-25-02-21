package com.prime.hrm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prime.hrm.entity.EmployeeQualification;
import com.prime.hrm.entity.ExtraActivityType;
import com.prime.hrm.entity.QualificationMaster;
import com.prime.hrm.report.EmployeeContactSummaryReportBean;
import com.prime.hrm.report.EmployeeQualificationSummaryReportBean;
import com.prime.hrm.service.QualificationService;


@Controller("qualificationReportController")
public class QualificationReportController {
	
	@Autowired
	QualificationService qualificationService;
	
	//shown data in jsp
	@GetMapping("/qualificationReport")
	public String empqualification() {
		return "qualificationReport";
	}
	
	@ModelAttribute("allemQua")
	public List<QualificationMaster> getAllATypes() {
		return qualificationService.getAllQm();
	}

	
	//shown data in report
	//jsp butoon reference
	@GetMapping("/quaificationRepo")
    public String handleForexRequest(Model m, @RequestParam("qid") String qid) {
        //model.addAttribute("employeeQualification", getSummaryofEmpQualification(qid));
        //return "forqView";
  
    
   
    	
    	 String[][] result=qualificationService.getEmpqualificationReportData(qid);
    	 
    	 List<EmployeeQualificationSummaryReportBean> employeeQualificationSummaryReportBeanArray = new ArrayList<>();
    	 
    	 for(int i=0; i<result.length;i++ ) {
    		 EmployeeQualificationSummaryReportBean empC=new EmployeeQualificationSummaryReportBean();
    		 empC.setEmpid(result[i][0]);
    		 empC.setEmpfname(result[i][1]);
    		 empC.setEmplname(result[i][2]);
    		 empC.setQtype(result[i][3]);
    		 empC.setQdesc(result[i][4]);
    		 empC.setQaward(result[i][5]);
    		  	   
    		 employeeQualificationSummaryReportBeanArray.add(empC);
    		 System.out.println(empC.getEmpfname()+"-------"+ empC.getQtype());
	       }
    	 m.addAttribute("employeeQualification",employeeQualificationSummaryReportBeanArray );
    	 
		return "printQualificationreport";
       
       
	}
}
	
	
