package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.navitsa.hrm.entity.ShiftMaster;

@Repository
public interface ShiftMasterRepository extends CrudRepository<ShiftMaster, String> {

	@Query(value = "SELECT (max(sm.shiftId)+1) FROM ShiftMaster sm WHERE sm.companyId=:companyId")
	public String maxShiftId(@Param("companyId") String companyId);

	@Query(value = "SELECT sm FROM ShiftMaster sm WHERE sm.shiftId=:shiftId AND sm.companyId=:companyId")
	public ShiftMaster findShiftById(@Param("shiftId") String shiftId, @Param("companyId") String companyId);

	@Query(value = "SELECT shift_id, description, TIME_FORMAT(start_time, \"%H:%i\") as start_time, TIME_FORMAT(end_time, \"%H:%i\") as end_time, recurring, company_id FROM shift_master where shift_master.company_id = :companyId", nativeQuery = true)
	public List<ShiftMaster> loadAllShifts(@Param("companyId") String companyId);

}
