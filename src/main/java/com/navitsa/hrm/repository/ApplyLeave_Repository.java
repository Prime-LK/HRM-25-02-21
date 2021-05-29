package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.navitsa.hrm.entity.ApplyLeave_Entity;
import com.navitsa.hrm.entity.CalanderEntity;

public interface ApplyLeave_Repository extends CrudRepository<ApplyLeave_Entity, String> {

	@Query(value = "SELECT * FROM calander a\r\n" + 
			"WHERE a.Date BETWEEN :Date1 AND :Date2",nativeQuery=true)
	public List<CalanderEntity> getCalanderDetail2(@Param("Date1")String date1,@Param("Date2")String date2);
	
	@Query(value="SELECT (max(a.leaveID)+1) FROM ApplyLeave_Entity a")
	public String getMaxID();

	@Query(value="SELECT l FROM ApplyLeave_Entity l WHERE l.department.depID =:dep_id")
	public List<ApplyLeave_Entity> findAllByDepID(@Param("dep_id") String dep_id);

	@Query(value="SELECT l FROM ApplyLeave_Entity l WHERE l.employee.empID =:employeeID")
	public List<ApplyLeave_Entity> findAllByEmployeeID(@Param("employeeID") String employeeID);

	@Query(value="SELECT SUM(Days) FROM applyleaves "
			+ "WHERE Employee_ID=:employeeID "
			+ "AND LeaveCode=:leaveTypeID "
			+ "AND Type='Full' "
			+ "AND Approved=1",nativeQuery = true)
	public int getSumOfApprovedLeaves(@Param("employeeID") String employeeID,
			@Param("leaveTypeID") String leaveTypeID);
	
	@Query(value="SELECT SUM(Days) FROM applyleaves "
			+ "WHERE Employee_ID=:employeeID "
			+ "AND LeaveCode=:leaveTypeID "
			+ "AND Type='Half' "
			+ "AND Approved=1",nativeQuery = true)
	public int getSumOfApprovedLeavesHalf(@Param("employeeID") String employeeID,
			@Param("leaveTypeID") String leaveTypeID);
	
}
