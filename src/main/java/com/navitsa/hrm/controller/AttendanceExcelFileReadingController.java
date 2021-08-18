package com.navitsa.hrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ibm.icu.text.SimpleDateFormat;
import com.navitsa.hrm.entity.PayCode;
import com.navitsa.hrm.entity.PayPeriods;
import com.navitsa.hrm.service.PayService;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

@Controller
public class AttendanceExcelFileReadingController {

	@Autowired
	private PayService payService;

	@ModelAttribute("payPeriodsList")
	public List<PayPeriods> getPeriods() {
		return payService.getPayPeriods();
	}
	
/*	@GetMapping("/getPayCodes")
	public @ResponseBody List<PayCode> loadPayCodeData(@RequestParam("payPeriodID") String payPeriodID) {
		List<PayCode> ls = payService.loadpayCodestoGrid(payPeriodID);
		return ls;
	}
*/	
	@GetMapping("/readExcelFile")
	public String LoadForm() {
		
		return "hrm/attendanceExcelReading";
	}
	
	
	@GetMapping("/importExcelFile")
	public void readAttedanceTxtFile(@RequestParam String payCode, HttpSession session) {

		String companyID=(String) session.getAttribute("company.comID");
		
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a"); //if 24 hour format
		
        String jdbcURL = "jdbc:mysql://localhost:3306/hrm";
        String username = "root";
        String password = "peLa071it";
 
        String excelFilePath = "C:\\Users\\Owner\\Downloads\\April.xlsx";
 
        int batchSize = 20;
 
        Connection connection = null;
 
        try {
            long start = System.currentTimeMillis();
             
            FileInputStream inputStream = new FileInputStream(excelFilePath);
 
            Workbook workbook = new XSSFWorkbook(inputStream);
 
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = firstSheet.iterator();
 
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
  
            //String sql = "INSERT INTO attendance_sheet VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String sql = "INSERT INTO attendance_sheet(Company_ID, Employee_ID, Pay_Code_ID, Attendance_Date, Time_In, Time_Out, Late_Minu_In, Late_Minu_Out, Late_Minu_Int, OT_Normal, OT_Extra, Night_Shift_Alow, Nigth_Shift_Alow_Entitlement) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);    
             
            int count = 0;
             
            rowIterator.next(); // skip the header row
             
            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();
 
                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();
 
                    int columnIndex = nextCell.getColumnIndex();
 
                    switch (columnIndex) {
                    case 0:
                        int EmployeeNo = (int) nextCell.getNumericCellValue();
                        statement.setInt(2, EmployeeNo);
                        break;
                    case 1:
                        Date date = nextCell.getDateCellValue();
                        statement.setTimestamp(4, new Timestamp(date.getTime()));
                    case 3:
                        Date timeIn = nextCell.getDateCellValue();
                        //Date d1 = null;
//                        try {
//							d1 =(Date)sdf.parse(String.valueOf(timeIn));
//						} catch (ParseException e) {
//							e.printStackTrace();
//						}
                        //String formattedTime = sdf.format(d1);
                        //System.out.println(formattedTime);
                        statement.setTimestamp(5, new Timestamp(timeIn.getTime()));                 	
                    case 4:
                        Date timeOut = nextCell.getDateCellValue();
                        statement.setTimestamp(6, new Timestamp(timeOut.getTime()));
                    case 9:
                        int lateMinTimeIn = (int) nextCell.getNumericCellValue();
                        statement.setInt(7, lateMinTimeIn);
                    case 10:
                        int lateMinTimeOut = (int) nextCell.getNumericCellValue();
                        statement.setInt(8, lateMinTimeOut);
                    case 11:
                        int lateMinLunch = (int) nextCell.getNumericCellValue();
                        statement.setInt(9, lateMinLunch);
                    case 12:
                        int otNormal = (int) nextCell.getNumericCellValue();
                        statement.setInt(10, otNormal);
                    case 13:
                        int otExtra = (int) nextCell.getNumericCellValue();
                        statement.setInt(11, otExtra);
                    case 14:
                        int nightShift = (int) nextCell.getNumericCellValue();
                        statement.setInt(12, nightShift);
                    case 15:
                        //String nightShiftEntitlement = nextCell.getStringCellValue();
                        statement.setString(13, "No");

                    }
 
                }
                 
                statement.setString(1, companyID);
                statement.setString(3, payCode);
                statement.addBatch();
                 
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }              
 
            }
 
            workbook.close();
             
            // execute the remaining queries
            statement.executeBatch();
  
            connection.commit();
            connection.close();
             
            long end = System.currentTimeMillis();
            System.out.printf("Import done in %d ms\n", (end - start));
             
        } catch (IOException ex1) {
            System.out.println("Error reading file");
            ex1.printStackTrace();
        } catch (SQLException ex2) {
            System.out.println("Database error");
            ex2.printStackTrace();
        }

	}
}
