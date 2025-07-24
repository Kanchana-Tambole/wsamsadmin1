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

import com.ws.spring.dto.ScholarshipSchemeDto;
import com.ws.spring.dto.ScholarshipSchemeDtoList;
import com.ws.spring.dto.CommonBuilder;

import com.ws.spring.model.ScholarshipScheme;
import com.ws.spring.model.UserProfile;

import com.ws.spring.repository.ScholarshipSchemeRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class ScholarshipSchemeServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private ScholarshipSchemeRepository scholarshipSchemeRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public ScholarshipSchemeDto getScholarshipSchemeById(long id) {
        ScholarshipScheme scheme = scholarshipSchemeRepository.findById(id);
        return CommonBuilder.buildScholarshipSchemeDto(scheme);
    }

    public List<ScholarshipSchemeDtoList> getAllScholarshipSchemes() {
        List<ScholarshipScheme> schemeList = scholarshipSchemeRepository.findAll(
            Sort.by(Sort.Direction.DESC, "id")
        );
        return CommonBuilder.buildScholarshipSchemeDataDtoList(schemeList);
    }

    public Page<ScholarshipSchemeDto> getAllScholarshipSchemesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<ScholarshipScheme> schemePage = scholarshipSchemeRepository.findAll(pageable);

        int totalElements = (int) schemePage.getTotalElements();

        return new PageImpl<>(
            schemePage.stream()
                .map(scheme -> new ScholarshipSchemeDto(
                    scheme.getId(),
                    scheme.getSchemeName(),
                    scheme.getType(),
                    scheme.getAmount(),
                    scheme.getEligibility(),
                    scheme.getStatus(),
                    scheme.getCreatedAt(),
                    scheme.getUpdatedAt(),
                    scheme.getCreatedBy(),
                    scheme.getUpdatedBy()
                ))
                .collect(Collectors.toList()),
            pageable,
            totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ScholarshipScheme createScholarshipScheme(ScholarshipSchemeDto dto) {
        ScholarshipScheme scheme = new ScholarshipScheme();

        BeanUtils.copyProperties(dto, scheme, "createdBy", "updatedBy");

        UserProfile userProfile = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        scheme.setCreatedBy(userProfile);
        scheme.setUpdatedBy(userProfile);

        return scholarshipSchemeRepository.save(scheme);
    }

    public ScholarshipScheme getSchemeByName(String schemeName) {
        return scholarshipSchemeRepository.findBySchemeName(schemeName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ScholarshipScheme updateScholarshipScheme(ScholarshipSchemeDto dto) {
        ScholarshipScheme scheme = scholarshipSchemeRepository.findById(dto.getId());

        try {
            scheme.setSchemeName(dto.getSchemeName());
            scheme.setType(dto.getType());
            scheme.setAmount(dto.getAmount());
            scheme.setEligibility(dto.getEligibility());
            scheme.setStatus(dto.getStatus());
        } catch (Exception e) {
            logger.error("Error while updating ScholarshipScheme {} : {}", dto.getSchemeName(), e.getMessage());
        }

        UserProfile userProfile = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        scheme.setUpdatedBy(userProfile);

        return scholarshipSchemeRepository.save(scheme);
    }

    public void deleteScholarshipSchemeById(long id) {
        scholarshipSchemeRepository.deleteById(id);
    }
}
