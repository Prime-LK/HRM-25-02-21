package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.AssetTransfer;

public interface AssetTransferRepository extends CrudRepository<AssetTransfer, String>
{
	@Query(value = "SELECT (max(gth.tranferno)+1) FROM AssetTransfer gth")
	public String getTnMaxID();

}
