package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.FeesType;

@Repository
public interface FeesTypeRepository extends JpaRepository<FeesType, Long> {

    FeesType findById(long id);

    FeesType findByName(String name);

}
