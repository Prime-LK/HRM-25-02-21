package com.prime.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.DepartmentMaster;

public interface DepartmentMasterRepository extends CrudRepository<DepartmentMaster, String> {

	@Query(value="SELECT (max(dm.depID)+1) FROM DepartmentMaster dm")
	public String getMaxID();
	
	@Query(value="SELECT dm.depID , dm.department FROM DepartmentMaster dm")
	public List<DepartmentMaster> getallsaveddepartment();

}
