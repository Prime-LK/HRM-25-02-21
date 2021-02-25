package com.prime.hrm.repository;

import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.ProcessPayrollDetails;
import com.prime.hrm.entity.ProcessPayrollDetailsPK;

public interface ProcessPayrollDetailsRepository extends CrudRepository<ProcessPayrollDetails, ProcessPayrollDetailsPK> {

}
