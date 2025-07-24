package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.PaymentMode;

@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode, Long> {

    PaymentMode findById(long id);

    PaymentMode findByModeName(String modeName);
}
