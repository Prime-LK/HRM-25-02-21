


package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.Depreciationgroup;

public interface DepreciationgroupRepository extends CrudRepository<Depreciationgroup, String> {

	@Query(value="SELECT (max(bm.deprecgroupcode)+1) FROM Depreciationgroup bm")
	public String getMaxID();
}
