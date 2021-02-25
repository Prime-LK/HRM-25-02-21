package com.prime.hrm.repository;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.CalanderEntity;

public interface CalanderRepository extends CrudRepository <CalanderEntity, String>  {


	@Query(value = "SELECT e FROM CalanderEntity e WHERE e.date.date=:date")
	public CalanderEntity getCalanderDetails(@Param("date")String date);

  
    @Query(value = "INSERT INTO calander (Date) VALUES (:date)", nativeQuery = true)
    public CalanderEntity setCalanderDetails(@Param("date")String date);
 
	
	
	
	
	
}
