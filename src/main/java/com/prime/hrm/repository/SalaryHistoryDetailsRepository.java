package com.prime.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prime.hrm.entity.SalaryHistoryDetails;
import com.prime.hrm.entity.SalaryHistoryDetailsPK;

public interface SalaryHistoryDetailsRepository extends JpaRepository<SalaryHistoryDetails, SalaryHistoryDetailsPK> {

}
