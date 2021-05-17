package com.navitsa.hrm.repository;

import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.LeaveOnLeaveEntity;
import com.navitsa.hrm.entity.LeaveOnLeavePK;

public interface LeaveOnLeaveRepository extends CrudRepository<LeaveOnLeaveEntity, LeaveOnLeavePK> {

}
