package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Qualification;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long>{

	Qualification findByQualificationId(long qualificationId);


}
