package com.navitsa.hrm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.CalanderEntity;

public interface CalanderRepository extends CrudRepository<CalanderEntity, String> {

	@Query(value = "SELECT ce FROM CalanderEntity ce WHERE ce.calanderEntityPK.date = :date AND ce.calanderEntityPK.company.comID = :companyId")
	public CalanderEntity getCalanderDetails(@Param("date") String date, @Param("companyId") String companyId);

	@Query(value = "INSERT INTO calander (Date) VALUES (:date)", nativeQuery = true)
	public CalanderEntity setCalanderDetails(@Param("date") String date);

	@Query(value = "SELECT ce FROM CalanderEntity ce WHERE ce.calanderEntityPK.date = :date AND ce.calanderEntityPK.company.comID = :companyId")
	public CalanderEntity getCalenderByCompany(@Param("date") String date, @Param("companyId") String companyId);
	
    @Query(value = "SELECT Date FROM calander WHERE date(Date) > :startDate AND date(Date) < :endDate", nativeQuery = true)
	public List<Date> getHolidays(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
