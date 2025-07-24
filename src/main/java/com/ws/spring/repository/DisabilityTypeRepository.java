package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.DisabilityType;

@Repository
public interface DisabilityTypeRepository extends JpaRepository<DisabilityType, Long> {

    DisabilityType findById(long id);

    DisabilityType findByName(String name);
}
