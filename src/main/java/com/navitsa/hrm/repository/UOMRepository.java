package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.UOM;

public interface UOMRepository extends CrudRepository<UOM, String>
{
	@Query(value="SELECT (max(bm.uom)+1) FROM ItemGroup bm")
	public String getMaxID();
	
}
