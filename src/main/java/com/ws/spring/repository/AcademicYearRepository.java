package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.AcademicYear;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {

    // Custom finder methods returning entities directly
    AcademicYear findById(long id);

    AcademicYear findByName(String name);
}
