package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.TestCenterDto;
import com.ws.spring.dto.TestCenterDtoList;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.TestCenter;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.TestCenterRepository;
import com.ws.spring.repository.UserProfileRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestCenterServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private TestCenterRepository testCenterRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public TestCenterDto getTestCenterById(long id) {
        TestCenter center = testCenterRepository.findById(id);
        return CommonBuilder.buildTestCenterDto(center);
    }

    public List<TestCenterDtoList> getAllTestCenters() {
        List<TestCenter> centerList = testCenterRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return CommonBuilder.buildTestCenterDtoList(centerList);
    }

    public Page<TestCenterDto> getAllTestCentersByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<TestCenter> centerPage = testCenterRepository.findAll(pageable);

        int totalElements = (int) centerPage.getTotalElements();

        return new PageImpl<>(
            centerPage.stream()
                .map(center -> new TestCenterDto(
                    center.getId(),
                    center.getCenterName(),
                    center.getCity(),
                    center.getAddress(),
                    center.getContactNumber(),
                    center.getIsActive(),
                    center.getCreatedAt(),
                    center.getUpdatedAt(),
                    center.getCreatedBy(),
                    center.getUpdatedBy()
                ))
                .collect(Collectors.toList()),
            pageable,
            totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public TestCenter createTestCenter(TestCenterDto dto) {
        TestCenter center = new TestCenter();
        BeanUtils.copyProperties(dto, center, "createdBy", "updatedBy");

        UserProfile user = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        center.setCreatedBy(user);
        center.setUpdatedBy(user);

        return testCenterRepository.save(center);
    }

    public TestCenter getCenterNameExist(String centerName) {
        return testCenterRepository.findByCenterName(centerName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public TestCenter updateTestCenter(TestCenterDto dto) {
        TestCenter center = testCenterRepository.findById(dto.getId()).orElseThrow();

        try {
            center.setCenterName(dto.getCenterName());
            center.setCity(dto.getCity());
            center.setAddress(dto.getAddress());
            center.setContactNumber(dto.getContactNumber());
            center.setIsActive(dto.getIsActive());
        } catch (Exception e) {
            logger.error("Error while updating TestCenter {}: {}", dto.getCenterName(), e.getMessage());
        }

        UserProfile user = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        center.setUpdatedBy(user);

        return testCenterRepository.save(center);
    }

    public void deleteTestCenterById(long id) {
        testCenterRepository.deleteById(id);
    }
}
