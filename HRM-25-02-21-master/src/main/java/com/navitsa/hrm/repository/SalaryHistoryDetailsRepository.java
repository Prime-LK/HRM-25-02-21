package com.navitsa.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.navitsa.hrm.entity.SalaryHistoryDetails;
import com.navitsa.hrm.entity.SalaryHistoryDetailsPK;

public interface SalaryHistoryDetailsRepository extends JpaRepository<SalaryHistoryDetails, SalaryHistoryDetailsPK> {

}
