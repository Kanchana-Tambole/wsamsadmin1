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

import com.ws.spring.dto.BloodGroupDto;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.BloodGroup;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.BloodGroupRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class BloodGroupServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    BloodGroupRepository bloodGroupRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    public BloodGroupDto getBloodGroupById(long id) {
        BloodGroup group = bloodGroupRepository.findById(id);
        return CommonBuilder.buildBloodGroupDto(group);
    }

    public List<BloodGroupDto> getAllBloodGroups() {
        List<BloodGroup> groupList = bloodGroupRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return groupList.stream()
            .map(CommonBuilder::buildBloodGroupDto)
            .collect(Collectors.toList());
    }

    public Page<BloodGroupDto> getAllBloodGroupsByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<BloodGroup> bloodGroupPage = bloodGroupRepository.findAll(pageable);
        int totalElements = (int) bloodGroupPage.getTotalElements();

        List<BloodGroupDto> dtoList = bloodGroupPage.stream()
            .map(group -> new BloodGroupDto(
                group.getId(),
                group.getBloodGroup(),
                group.getInsertedDate(),
                group.getUpdatedDate(),
                group.getCreatedBy(),
                group.getUpdatedBy()
            )).collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, totalElements);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BloodGroup createBloodGroup(BloodGroupDto groupDto) {
        BloodGroup group = new BloodGroup();
        BeanUtils.copyProperties(groupDto, group, "createdBy", "updatedBy");

        UserProfile userProfile = userProfileRepository.findByUserId(groupDto.getCreatedBy().getUserId());
        group.setCreatedBy(userProfile);
        group.setUpdatedBy(userProfile);

        return bloodGroupRepository.save(group);
    }

    public BloodGroup getBloodGroupByName(String bloodGroup) {
        return bloodGroupRepository.findByBloodGroup(bloodGroup);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BloodGroup updateBloodGroup(BloodGroupDto groupDto) {
        BloodGroup group = bloodGroupRepository.findById(groupDto.getId());

        try {
            group.setBloodGroup(groupDto.getBloodGroup());
        } catch (Exception e) {
            logger.error("Error while updating BloodGroup {}: {}", groupDto.getBloodGroup(), e.getMessage());
        }

        UserProfile userProfile = userProfileRepository.findByUserId(groupDto.getUpdatedBy().getUserId());
        group.setUpdatedBy(userProfile);

        return bloodGroupRepository.save(group);
    }

    public void deleteBloodGroupById(long id) {
        bloodGroupRepository.deleteById(id);
    }
}
