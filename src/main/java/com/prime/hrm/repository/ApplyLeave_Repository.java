package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.ApplyLeave_Entity;
import com.prime.hrm.entity.CalanderEntity;

public interface ApplyLeave_Repository extends CrudRepository<ApplyLeave_Entity, String> {

	@Query(value = "SELECT * FROM calander a\r\n" + 
			"WHERE a.Date BETWEEN :Date1 AND :Date2",nativeQuery=true)
	public List<CalanderEntity> getCalanderDetail2(@Param("Date1")String date1,@Param("Date2")String date2);
	
}
