package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.prime.hrm.entity.PayPeriods;

public interface PayPeriodsRepository extends CrudRepository<PayPeriods, String>{
	
	
	@Query(value = "SELECT (max(p.payPeriodID)+1) FROM PayPeriods p")
	public String maxpayPeriodID();

	
	//get employee based on types to employee salary details
	@Query(value="SELECT p FROM PayPeriods p WHERE p.payPeriodID =:payPeriodID")
	public PayPeriods loadPayPeriodsdata(@Param("payPeriodID") String payPeriodID);
	
	//based on strat date and end date load period ID
	@Query(value="select * from pay_periods m\r\n" + 
			"where m.Start_Date =:Start_Date and m.End_Date =:End_Date",nativeQuery=true)
	public PayPeriods loadPeriodData1(@Param("Start_Date") String startDate , @Param("End_Date") String endDate);
	
	@Query(value="select * from pay_periods m\r\n" + 
			"where YEAR(m.Start_Date) =:Start_Date and MONTH(m.End_Date) =:End_Date",nativeQuery=true)
	public PayPeriods loadPeriodData(@Param("Start_Date") String startDate , @Param("End_Date") String endDate);
	
	//getRelatedDateOnly
	@Query(value="select * from pay_periods m\r\n" + 
			"where YEAR(m.Start_Date) =:Start_Date and MONTH(m.End_Date) =:End_Date",nativeQuery=true)
	public PayPeriods getDates(@Param("Start_Date") String startDate,@Param("End_Date") String endDate);
	
	//update pay period when save empMosaDe
	@Transactional
	@Modifying
	@Query(value="UPDATE`pay_periods`\r\n" + 
			"SET\r\n" + 
			"`Start_Date` =:Start_Date,\r\n" + 
			"`End_Date` = :End_Date,\r\n" + 
			"`Pay_Date` =:Pay_Date,\r\n" + 
			"`Status` =:Status\r\n" + 
			"WHERE `Pay_Period_ID` =:Pay_Period_ID",nativeQuery=true)
	public int updatePayPeriodWhenSave(@Param("Pay_Period_ID")String periodID,
			@Param("Start_Date")String startDate,@Param("End_Date")String endDate,
			@Param("Pay_Date")String payDate,@Param("Status")String status);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
