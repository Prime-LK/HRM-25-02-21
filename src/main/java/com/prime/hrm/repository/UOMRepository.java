package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.UOM;

public interface UOMRepository extends CrudRepository<UOM, String>
{
	@Query(value="SELECT (max(bm.uom)+1) FROM ItemGroup bm")
	public String getMaxID();
	
}
