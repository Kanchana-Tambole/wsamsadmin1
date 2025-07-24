package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.BankType;

@Repository
public interface BankTypeRepository extends JpaRepository<BankType, Long> {

    BankType findById(long id);

    BankType findByBankName(String bankName);
}
