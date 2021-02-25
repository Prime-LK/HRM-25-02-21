package com.prime.hrm.repository;

import org.springframework.data.repository.CrudRepository;

import com.prime.hrm.entity.Location;

public interface LocationRepository extends CrudRepository <Location, String>  {

}
