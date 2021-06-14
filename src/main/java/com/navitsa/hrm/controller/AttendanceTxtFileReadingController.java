package com.navitsa.hrm.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.navitsa.hrm.entity.AttendanceTxtFileDetail;
import com.navitsa.hrm.service.AttendanceTxtFileReadingService;

@Controller
public class AttendanceTxtFileReadingController {
	
	@Autowired
	private AttendanceTxtFileReadingService service;

	
	@GetMapping("/readAttedanceTxtFile")
	public void readAttedanceTxtFile(HttpSession session) {
		
		String companyID=(String) session.getAttribute("company.comID");
		String userID=(String) session.getAttribute("empID");
		
		String path = "C:\\Users\\Owner\\Downloads\\Attendance";
		
	      //Creating a File object for directory
	      File directoryPath = new File(path);
	      FilenameFilter textFilefilter = new FilenameFilter(){
	         public boolean accept(File dir, String name) {
	            String lowercaseName = name.toLowerCase();
	            if (lowercaseName.endsWith(".txt")) {
	               return true;
	            } else {
	               return false;
	            }
	         }
	      };
	      
	      //List of all the text files
	      String filesList[] = directoryPath.list(textFilefilter);
	      
	      for(String fileName : filesList) {
	    	  
	    	  List<AttendanceTxtFileDetail> ls = new ArrayList<AttendanceTxtFileDetail>();
	    	  char machine = 0;
	    	  int totalRecords = 0;
	    	  
    		  File myObj = new File(path +"\\"+ fileName);
    		  
    		  try {
				Scanner myReader = new Scanner(myObj);
				
				myReader.nextLine(); // skip the header row
				while (myReader.hasNextLine()) {
					
					String rs = myReader.nextLine();
	    			rs = rs.replace(" ","");
	    			rs = rs.replace("	","");

	    			machine = rs.charAt(6);
	    			String employeeNo = rs.substring(7, 17);
	    			String inout_date = rs.substring(19, 29);
	    			String inout_time = rs.substring(29, 37);
	    			
	    			//System.out.println(machine+" "+employeeNo+" "+inout_date+" "+inout_time);
	    			
	    			AttendanceTxtFileDetail objDetail = new AttendanceTxtFileDetail();
	    			objDetail.setHeaderId(fileName);
	    			objDetail.setEmployeeId(employeeNo);
	    			objDetail.setInoutDate(inout_date);
	    			objDetail.setInoutTime(inout_time);
	    			ls.add(objDetail);
	    			
	    			totalRecords++;
	    			  
				}
				
				 myReader.close();
	    		 service.saveAttendanceLogHeader(fileName,machine,fileName,totalRecords,userID,companyID);
	    		 service.saveAllAttendanceLogDetail(ls);
	    		 myObj.delete();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    	  
	      }
	}
	
	
	
}
