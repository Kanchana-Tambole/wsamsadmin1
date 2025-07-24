package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.GradingSchemeDto;
import com.ws.spring.model.GradingScheme;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.GradingSchemeRepository;
import com.ws.spring.repository.UserProfileRepository;

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

@Service
public class GradingSchemeServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private GradingSchemeRepository gradingSchemeRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public GradingSchemeDto getGradingSchemeById(long id) {
        GradingScheme scheme = gradingSchemeRepository.findById(id);
        return CommonBuilder.buildGradingSchemeDto(scheme);
    }

    public List<GradingSchemeDto> getAllGradingSchemes() {
        List<GradingScheme> schemes = gradingSchemeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return schemes.stream()
                .map(CommonBuilder::buildGradingSchemeDto)
                .collect(Collectors.toList());
    }

    public Page<GradingSchemeDto> getAllGradingSchemesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<GradingScheme> page = gradingSchemeRepository.findAll(pageable);
        int totalElements = (int) page.getTotalElements();

        return new PageImpl<>(
                page.stream()
                        .map(CommonBuilder::buildGradingSchemeDto)
                        .collect(Collectors.toList()),
                pageable,
                totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public GradingScheme createGradingScheme(GradingSchemeDto dto) {
        GradingScheme scheme = new GradingScheme();
        BeanUtils.copyProperties(dto, scheme, "createdBy", "updatedBy");

        UserProfile user = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        scheme.setCreatedBy(user);
        scheme.setUpdatedBy(user);

        return gradingSchemeRepository.save(scheme);
    }

    public GradingScheme getSchemeNameExist(String schemeName) {
        return gradingSchemeRepository.findBySchemeName(schemeName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public GradingScheme updateGradingScheme(GradingSchemeDto dto) {
        GradingScheme scheme = gradingSchemeRepository.findById(dto.getId()).orElseThrow(null);

        try {
            scheme.setSchemeName(dto.getSchemeName());
            scheme.setDescription(dto.getDescription());
        } catch (Exception e) {
            logger.error("Error while updating GradingScheme {}: {}", dto.getSchemeName(), e.getMessage());
        }

        UserProfile user = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        scheme.setUpdatedBy(user);

        return gradingSchemeRepository.save(scheme);
    }

    public void deleteGradingSchemeById(long id) {
        gradingSchemeRepository.deleteById(id);
    }
}
