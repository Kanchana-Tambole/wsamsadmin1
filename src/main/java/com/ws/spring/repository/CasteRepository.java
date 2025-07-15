package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Caste;

@Repository
public interface CasteRepository extends JpaRepository<Caste, Long> {

    Caste findByCasteName(String casteName);

    Caste findByCasteId(long casteId);
}
