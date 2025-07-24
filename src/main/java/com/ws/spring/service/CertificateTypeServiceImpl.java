package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ws.spring.dto.CertificateTypeDto;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.UserProfileDtoList;

import com.ws.spring.model.CertificateType;
import com.ws.spring.model.UserProfile;

import com.ws.spring.repository.CertificateTypeRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class CertificateTypeServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CertificateTypeRepository certificateTypeRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public CertificateTypeDto getCertificateTypeById(long typeId) {
        CertificateType type = certificateTypeRepository.findByTypeId(typeId);
        return CommonBuilder.buildCertificateTypeDto(type);
    }

    public List<CertificateTypeDto> getAllCertificateTypes() {
        List<CertificateType> typeList = certificateTypeRepository.findAll(Sort.by(Sort.Direction.DESC, "typeId"));
        return typeList.stream()
                .map(CommonBuilder::buildCertificateTypeDto)
                .collect(Collectors.toList());
    }

    public Page<CertificateTypeDto> getAllCertificateTypesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("typeId").descending());
        Page<CertificateType> page = certificateTypeRepository.findAll(pageable);
        int totalElements = (int) page.getTotalElements();

        return new PageImpl<>(
                page.stream()
                        .map(CommonBuilder::buildCertificateTypeDto)
                        .collect(Collectors.toList()),
                pageable,
                totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CertificateType createCertificateType(CertificateTypeDto dto) {
        CertificateType type = new CertificateType();
        BeanUtils.copyProperties(dto, type, "createdBy", "updatedBy");

        UserProfile user = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        type.setCreatedBy(user);
        type.setUpdatedBy(user);

        return certificateTypeRepository.save(type);
    }

    public CertificateType getTypeCodeExist(String typeCode) {
        return certificateTypeRepository.findByTypeCode(typeCode);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CertificateType updateCertificateType(CertificateTypeDto dto) {
        CertificateType type = certificateTypeRepository.findByTypeId(dto.getTypeId());

        try {
            type.setTypeCode(dto.getTypeCode());
            type.setCertificateName(dto.getCertificateName());
            type.setTemplatePath(dto.getTemplatePath());
            type.setPrintable(dto.isPrintable());
        } catch (Exception e) {
            logger.error("Error while updating CertificateType {}: {}", dto.getCertificateName(), e.getMessage());
        }

        UserProfile user = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        type.setUpdatedBy(user);

        return certificateTypeRepository.save(type);
    }

    public void deleteCertificateTypeById(long typeId) {
        certificateTypeRepository.deleteById(typeId);
    }
}
