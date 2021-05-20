package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.ApplyLeave_Entity;
import com.navitsa.hrm.entity.CalanderEntity;

public interface ApplyLeave_Repository extends CrudRepository<ApplyLeave_Entity, String> {

	@Query(value = "SELECT * FROM calander a\r\n" + 
			"WHERE a.Date BETWEEN :Date1 AND :Date2",nativeQuery=true)
	public List<CalanderEntity> getCalanderDetail2(@Param("Date1")String date1,@Param("Date2")String date2);
	
	@Query(value="SELECT (max(a.leaveID)+1) FROM ApplyLeave_Entity a")
	public String getMaxID();
	
}
