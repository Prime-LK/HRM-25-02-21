package com.navitsa.hrm.repository;

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
}
