package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.BloodGroup;

@Repository
public interface BloodGroupRepository extends JpaRepository<BloodGroup, Long> {

    BloodGroup findById(long id);

    BloodGroup findByBloodGroup(String bloodGroup);
}
