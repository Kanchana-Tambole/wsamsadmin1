package com.ws.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ws.spring.model.CertificateType;

@Repository
public interface CertificateTypeRepository extends JpaRepository<CertificateType, Long> {

    CertificateType findByTypeId(long typeId);

    CertificateType findByTypeCode(String typeCode);

}
