package com.navitsa.hrm.repository;

import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.ProcessPayrollDetails;
import com.navitsa.hrm.entity.ProcessPayrollDetailsPK;

public interface ProcessPayrollDetailsRepository extends CrudRepository<ProcessPayrollDetails, ProcessPayrollDetailsPK> {

}
