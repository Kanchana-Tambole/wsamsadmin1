package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.EntranceTestSchedule;

@Repository
public interface EntranceTestScheduleRepository extends JpaRepository<EntranceTestSchedule, Long> {

    EntranceTestSchedule findById(long id);
}
