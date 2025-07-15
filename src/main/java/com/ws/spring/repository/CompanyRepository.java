package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.Company;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Long> {

	Company findByCompanyId(long companyId);

}
