package com.navitsa.hrm.repository;

import org.springframework.data.repository.CrudRepository;
import com.navitsa.hrm.entity.ApplyLeaveDetail;

public interface ApplyLeaveDetailRepository extends CrudRepository<ApplyLeaveDetail, Integer> {
	
	//@Query(value="SELECT * FROM apply_leave_detail WHERE date(date) >:startDate AND date(date) <=:endDate AND apply_leave_header_id=:leaveID AND approved=true",nativeQuery = true)
	//public List<ApplyLeaveDetail> getTotalApprovedLeaveBy(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("leaveID") String leaveID);

}
