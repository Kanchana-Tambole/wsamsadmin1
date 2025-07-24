package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.TestType;

@Repository
public interface TestTypeRepository extends JpaRepository<TestType, Long> {

    TestType findById(long id);

    TestType findByTestName(String testName);

}
