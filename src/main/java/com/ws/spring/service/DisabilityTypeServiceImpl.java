package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.DisabilityTypeDto;
import com.ws.spring.dto.DisabilityTypeDtoList;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.DisabilityType;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.DisabilityTypeRepository;
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
public class DisabilityTypeServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    DisabilityTypeRepository disabilityTypeRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    public DisabilityTypeDto getDisabilityTypeById(long id) {
        DisabilityType type = disabilityTypeRepository.findById(id);
        return CommonBuilder.buildDisabilityTypeDto(type);
    }

    public List<DisabilityTypeDtoList> getAllDisabilityTypes() {
        List<DisabilityType> types = disabilityTypeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return CommonBuilder.buildDisabilityTypeDtoList(types);
    }

    public Page<DisabilityTypeDto> getAllDisabilityTypesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<DisabilityType> page = disabilityTypeRepository.findAll(pageable);
        int totalElements = (int) page.getTotalElements();

        return new PageImpl<>(
            page.stream()
                .map(disabilityType -> new DisabilityTypeDto(
                    disabilityType.getId(),
                    disabilityType.getName(),
                    disabilityType.getDescription(),
                    disabilityType.isStatus(),
                    disabilityType.getInsertedDate(),
                    disabilityType.getUpdatedDate(),
                    disabilityType.getCreatedBy(),
                    disabilityType.getUpdatedBy()))
                .collect(Collectors.toList()),
            pageable,
            totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public DisabilityType createDisabilityType(DisabilityTypeDto dto) {
        DisabilityType type = new DisabilityType();
        BeanUtils.copyProperties(dto, type, "createdBy", "updatedBy");

        UserProfile userProfile = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        type.setCreatedBy(userProfile);
        type.setUpdatedBy(userProfile);

        return disabilityTypeRepository.save(type);
    }

    public DisabilityType getDisabilityTypeByName(String name) {
        return disabilityTypeRepository.findByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public DisabilityType updateDisabilityType(DisabilityTypeDto dto) {
        DisabilityType type = disabilityTypeRepository.findById(dto.getId());

        try {
            type.setName(dto.getName());
            type.setDescription(dto.getDescription());
            type.setStatus(dto.isStatus());
        } catch (Exception e) {
            logger.error("Error updating DisabilityType {} - {}", dto.getName(), e.getMessage());
        }

        UserProfile userProfile = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        type.setUpdatedBy(userProfile);

        return disabilityTypeRepository.save(type);
    }

    public void deleteDisabilityTypeById(long id) {
        disabilityTypeRepository.deleteById(id);
    }
}
