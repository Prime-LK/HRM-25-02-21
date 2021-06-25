package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.navitsa.hrm.entity.ApplyLeave_Entity;
import com.navitsa.hrm.entity.CalanderEntity;

public interface ApplyLeave_Repository extends CrudRepository<ApplyLeave_Entity, String> {

	@Query(value = "SELECT * FROM calander a\r\n" + 
			"WHERE a.Date BETWEEN :Date1 AND :Date2",nativeQuery=true)
	public List<CalanderEntity> getCalanderDetail2(@Param("Date1")String date1,@Param("Date2")String date2);
	
	@Query(value="SELECT (max(a.leaveID)+1) FROM ApplyLeave_Entity a")
	public String getMaxID();

	@Query(value="SELECT l FROM ApplyLeave_Entity l WHERE l.department.depID =:dep_id "
			+ "AND l.employee.empID=:employee_id "
			+ "AND l.approved=true")
	public List<ApplyLeave_Entity> findAllByDepID(@Param("dep_id") String dep_id,
			@Param("employee_id") String employee_id);

	@Query(value="SELECT l FROM ApplyLeave_Entity l WHERE l.employee.empID =:employeeID")
	public List<ApplyLeave_Entity> findAllByEmployeeID(@Param("employeeID") String employeeID);

	@Query(value="SELECT SUM(Days) FROM applyleaves "
			+ "WHERE Employee_ID=:employeeID "
			+ "AND LeaveCode=:leaveTypeID "
			+ "AND Type='Full'",nativeQuery = true)
	public String getSumOfApprovedLeaves(@Param("employeeID") String employeeID,
			@Param("leaveTypeID") String leaveTypeID);
	
	@Query(value="SELECT SUM(Days) FROM applyleaves "
			+ "WHERE Employee_ID=:employeeID "
			+ "AND LeaveCode=:leaveTypeID "
			+ "AND Type='Half'",nativeQuery = true)
	public String getSumOfApprovedLeavesHalf(@Param("employeeID") String employeeID,
			@Param("leaveTypeID") String leaveTypeID);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE ApplyLeave_Entity l SET l.approved=true WHERE l.leaveID=:applyLeaveID")
	public void updateApprovedStatus(@Param("applyLeaveID") String applyLeaveID);
	
	@Query(value="SELECT l FROM ApplyLeave_Entity l WHERE l.employee.empID =:employeeID")
	public ApplyLeave_Entity getByEmployeeID(@Param("employeeID") String employeeID);

	@Query(value="SELECT COUNT(apply_leave_detail_id) FROM apply_leave_detail WHERE date(date) > '2021-03-21' AND date(date)<='2021-04-20' AND apply_leave_header_id=00001 AND approved=true",nativeQuery = true)
	public int getTotalApprovedLeaveBy(String startDate, String endDate, String leaveID);
	
}
