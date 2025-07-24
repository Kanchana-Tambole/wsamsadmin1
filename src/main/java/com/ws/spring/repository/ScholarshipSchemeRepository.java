package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.ScholarshipScheme;

@Repository
public interface ScholarshipSchemeRepository extends JpaRepository<ScholarshipScheme, Long> {

    ScholarshipScheme findById(long id);

    ScholarshipScheme findBySchemeName(String schemeName);
}
