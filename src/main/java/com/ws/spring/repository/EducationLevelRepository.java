package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.EducationLevel;

@Repository
public interface EducationLevelRepository extends JpaRepository<EducationLevel, Long> {

    EducationLevel findById(long id);

    EducationLevel findByLevelName(String levelName);
}
