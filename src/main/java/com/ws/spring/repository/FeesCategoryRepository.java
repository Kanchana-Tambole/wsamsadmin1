package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.FeesCategory;

@Repository
public interface FeesCategoryRepository extends JpaRepository<FeesCategory, Long> {

    FeesCategory findById(long id);

    FeesCategory findByName(String name);
}
