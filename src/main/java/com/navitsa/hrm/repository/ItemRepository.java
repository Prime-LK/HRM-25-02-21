package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.ItemGroup;

public interface ItemRepository extends CrudRepository <ItemGroup, String> {

	@Query(value = "SELECT (max(it.uom)+1) FROM ItemGroup it")
	public String getItemMaxID();
}
