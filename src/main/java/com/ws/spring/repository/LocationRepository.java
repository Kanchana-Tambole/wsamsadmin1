package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	Location findByLocationId(long locationId);


}
