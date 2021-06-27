package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.AttendanceSummary;

public interface AttendanceSummaryRepository extends CrudRepository<AttendanceSummary, String > {

	@Query(value="SELECT (max(a.attendanceSummaryId)+1) FROM AttendanceSummary a")
	public String getMaxID();
}
