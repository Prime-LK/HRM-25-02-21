package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.navitsa.hrm.entity.PayCode;

public interface PayCodeRepository extends CrudRepository<PayCode, String>{
	
	@Query(value = "SELECT (max(p.payCodeID)+1) FROM PayCode p")
	public String maxPayCodeID();
	
	@Query(value = "SELECT (max(p.payCodeID)+1) FROM PayCode p")
	public String payCodeForAG();
	
	@Query(value="SELECT p FROM PayCode p WHERE p.periodID.payPeriodID =:payPeriodID and p.company.comID=:comID ")
	public List<PayCode> loadPayCodedata(@Param("payPeriodID") String payPeriodID,@Param("comID")String comID);
	
	@Query(value="SELECT p FROM PayCode p WHERE p.periodID.startDate =:startDate AND p.periodID.endDate=:endDate")
	public List<PayCode> loadPayCodedataBySDAndED(@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//based on period id load pay code 
	@Query(value="SELECT p FROM PayCode p WHERE p.periodID.payPeriodID =:periodID and p.company.comID=:comID")
	public List<PayCode> loadPayCode(@Param("periodID") String periodID,@Param("comID") String comID);
	
	@Query(value="SELECT p FROM PayCode p WHERE p.endDate =:endDate and p.startDate =:startDate")
	public PayCode getReDates(@Param("startDate") String startDate , @Param("endDate") String endDate);
	
	//based on period id load pay code 
	@Query(value="SELECT p FROM PayCode p WHERE p.periodID.payPeriodID =:periodID")
	public PayCode getPayCodeUsingPeriond(@Param("periodID") String periodID);
	
	//update payCode when save month salary details
	@Transactional
	@Modifying
	@Query(value = "UPDATE `pay_codes` SET" + 
			"`Pay_Code_ID` =:Pay_Code_ID,\r\n" + 
			"`Pay_Code` =:Pay_Code,\r\n" + 
			"`Start_Date` = :Start_Date,\r\n" + 
			"`End_Date` =:End_Date,\r\n" + 
			"`Status` =:Status,\r\n" + 
			"`Remarks` =:Remarks,\r\n" + 
			"`Pay_Period_ID` =:Pay_Period_ID\r\n" + 
			"WHERE `Pay_Code_ID` =:Pay_Code_ID",nativeQuery=true)
	public int updateExistsData(@Param("Pay_Code")String payCode,@Param("Start_Date")String startDate,
			@Param("End_Date")String endDate,@Param("Status")String status,
			@Param("Remarks")String remarks,@Param("Pay_Period_ID")String payPeriodID,
			@Param("Pay_Code_ID")String payCodeID);
	
	//getRelatedDateOnly2
	@Query(value="select * from pay_codes m\r\n" + 
			"where YEAR(m.Start_Date) =:Start_Date and MONTH(m.End_Date) =:End_Date",nativeQuery=true)
	public PayCode getDates2(@Param("Start_Date") String startDate,@Param("End_Date") String endDate);

	@Query(value="SELECT p FROM PayCode p WHERE p.company.comID=:comID ")
	public List<PayCode> loadPAllayCodeBycomp(@Param("comID")String comID);
}
