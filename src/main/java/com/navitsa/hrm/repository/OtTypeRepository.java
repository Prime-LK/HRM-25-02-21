package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.OtType;

public interface OtTypeRepository extends CrudRepository<OtType, String > {

	@Query(value="SELECT t FROM OtType t WHERE t.dayType=:dayType AND t.company.comID=:companyID")
	List<OtType> findByDayType(@Param("dayType") String dayType,@Param("companyID") String companyID);

}
