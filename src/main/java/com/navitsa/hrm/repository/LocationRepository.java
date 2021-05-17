package com.navitsa.hrm.repository;

import org.springframework.data.repository.CrudRepository;

import com.navitsa.hrm.entity.Location;

public interface LocationRepository extends CrudRepository <Location, String>  {

}
