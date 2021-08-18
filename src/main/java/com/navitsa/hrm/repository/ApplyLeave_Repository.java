package com.navitsa.hrm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.navitsa.hrm.entity.ApplyLeave;
import com.navitsa.hrm.entity.CalanderEntity;
import com.navitsa.hrm.entity.LeaveType;

public interface ApplyLeave_Repository extends CrudRepository<ApplyLeave, String> {

	@Query(value = "SELECT * FROM calander a\r\n" + 
			"WHERE a.Date BETWEEN :Date1 AND :Date2",nativeQuery=true)
	public List<CalanderEntity> getCalanderDetail2(@Param("Date1")String date1,@Param("Date2")String date2);
	
	//@Query(value="SELECT (max(a.leaveID)+1) FROM ApplyLeave a")
	//public String getMaxID();

	@Query(value="SELECT l FROM ApplyLeave l WHERE l.department.depID =:dep_id "
			+ "AND l.employee.empID=:employee_id "
			+ "AND l.approved=true")
	public List<ApplyLeave> findAllByDepID(@Param("dep_id") String dep_id,
			@Param("employee_id") String employee_id);

	@Query(value="SELECT l FROM ApplyLeave l WHERE l.employee.empID =:employeeID AND l.company.comID=:companyID")
	public List<ApplyLeave> findAllByEmployeeID(@Param("employeeID") String employeeID, @Param("companyID") String companyID);

	@Query(value="SELECT COUNT(date) FROM apply_leave "
			+ "WHERE employee_id=:employeeID "
			+ "AND leave_type_id=:leaveTypeID "
			+ "AND full_half='Full' AND company_id=:companyID",nativeQuery = true)
	public String getSumOfApprovedLeaves(@Param("employeeID") String employeeID,
			@Param("leaveTypeID") String leaveTypeID,
			@Param("companyID") String companyID);
	
	@Query(value="SELECT COUNT(date) FROM apply_leave "
			+ "WHERE employee_id=:employeeID "
			+ "AND leave_type_id=:leaveTypeID "
			+ "AND full_half='Half' AND company_id=:companyID",nativeQuery = true)
	public String getSumOfApprovedLeavesHalf(@Param("employeeID") String employeeID,
			@Param("leaveTypeID") String leaveTypeID,
			@Param("companyID") String companyID);

	
	@Transactional
	@Modifying
	@Query(value = "UPDATE ApplyLeave l SET l.approved=true WHERE l.leaveID=:applyLeaveID")
	public void updateApprovedStatus(@Param("applyLeaveID") String applyLeaveID);
	
	@Query(value="SELECT * FROM apply_leave WHERE date(date) >:fromDate AND date(date)<=:toDate AND approved=true AND employee_id=:employeeID AND company_id=:companyID",nativeQuery = true)
	public List<ApplyLeave> getByEmployeeID(
			@Param("employeeID") String employeeID, 
			@Param("companyID") String companyID,
			@Param("fromDate") String fromDate,
			@Param("toDate") String toDate);
	
	@Query(value="SELECT * FROM apply_leave WHERE date=:date AND approved=true AND employee_id=:employeeID AND company_id=:companyID",nativeQuery = true)
	public ApplyLeave getLeaveBy(
			@Param("employeeID") String employeeID, 
			@Param("companyID") String companyID,
			@Param("date") String date);

	//@Query(value="SELECT * FROM apply_leave_detail WHERE date(date) >:startDate AND date(date) <=:endDate AND apply_leave_header_id=:leaveID AND approved=true",nativeQuery = true)
	//public List<ApplyLeaveDetail> getTotalApprovedLeaveBy(@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("leaveID") String leaveID);
	
	@Query(value="SELECT m FROM ApplyLeave m WHERE m.employee.empID like :employeeId and m.company.comID=:comid and m.approved like :approve order by m.leaveType.leaveTypeID,m.employee.empID,m.date ")
	public List<ApplyLeave> getleavefreport(@Param("employeeId")String employeeId,@Param("comid")String comid,@Param("approve")boolean approve);
	


	
}
