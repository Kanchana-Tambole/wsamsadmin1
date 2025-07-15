package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {

    Batch findByBatchId(long batchId);

    Batch findByBatchName(String batchName);
}
