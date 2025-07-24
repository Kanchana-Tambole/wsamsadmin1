package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.TestCenter;

@Repository
public interface TestCenterRepository extends JpaRepository<TestCenter, Long> {

    TestCenter findById(long id);

    TestCenter findByCenterName(String centerName);
}
