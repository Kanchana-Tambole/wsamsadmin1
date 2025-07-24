package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.GradingScheme;

@Repository
public interface GradingSchemeRepository extends JpaRepository<GradingScheme, Long> {

    GradingScheme findById(long id);

    GradingScheme findBySchemeName(String schemeName);
}
