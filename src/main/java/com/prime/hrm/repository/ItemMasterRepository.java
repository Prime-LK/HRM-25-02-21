package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.prime.hrm.entity.ItemMaster;

public interface ItemMasterRepository extends CrudRepository<ItemMaster, String> {

	@Query(value = "SELECT d FROM ItemMaster d WHERE d.itemcode=:itemcode")
	public ItemMaster getDetails(@Param("itemcode")String itemcode);
}
