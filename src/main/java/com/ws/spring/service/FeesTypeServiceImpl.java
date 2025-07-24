package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.FeesTypeDto;
import com.ws.spring.dto.FeesTypeDtoList;
import com.ws.spring.model.FeesType;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.FeesTypeRepository;
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
public class FeesTypeServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private FeesTypeRepository feesTypeRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public FeesTypeDto getFeesTypeById(long id) {
        FeesType feesType = feesTypeRepository.findById(id);
        return CommonBuilder.buildFeesTypeDto(feesType);
    }

    public List<FeesTypeDtoList> getAllFeesType() {
        List<FeesType> list = feesTypeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return CommonBuilder.buildFeesTypeDtoList(list);
    }

    public Page<FeesTypeDto> getAllFeesTypeByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());

        Page<FeesType> page = feesTypeRepository.findAll(pageable);
        int total = (int) page.getTotalElements();

        return new PageImpl<>(
            page.getContent()
                .stream()
                .map(feesType -> new FeesTypeDto(
                    feesType.getId(),
                    feesType.getName(),
                    feesType.getDescription(),
                    feesType.isStatus(),
                    feesType.getCreatedAt(),
                    feesType.getUpdatedAt(),
                    feesType.getCreatedBy(),
                    feesType.getUpdatedBy()
                ))
                .collect(Collectors.toList()),
            pageable,
            total
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public FeesType createFeesType(FeesTypeDto dto) {
        FeesType entity = new FeesType();
        BeanUtils.copyProperties(dto, entity, "createdBy", "updatedBy");

        UserProfile user = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());

        entity.setCreatedBy(user);
        entity.setUpdatedBy(user);

        return feesTypeRepository.save(entity);
    }

    public FeesType getFeesTypeByName(String name) {
        return feesTypeRepository.findByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public FeesType updateFeesType(FeesTypeDto dto) {
        FeesType entity = feesTypeRepository.findById(dto.getId());

        try {
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setStatus(dto.isStatus());
        } catch (Exception e) {
            logger.error("Error while updating FeesType {}: {}", dto.getName(), e.getMessage());
        }

        UserProfile user = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        entity.setUpdatedBy(user);

        return feesTypeRepository.save(entity);
    }

    public void deleteFeesTypeById(long id) {
        feesTypeRepository.deleteById(id);
    }
}
