package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Religion;

@Repository
public interface ReligionRepository extends JpaRepository<Religion, Long> {

    Religion findByReligionName(String religionName);

    Religion findByReligionId(long religionId);
}
