package com.ws.spring.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.TestTypeDto;
import com.ws.spring.dto.TestTypeDtoList;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.TestType;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.TestTypeRepository;
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
public class TestTypeServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private TestTypeRepository testTypeRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public TestTypeDto getTestTypeById(long id) {
        TestType testType = testTypeRepository.findById(id);
        return CommonBuilder.buildTestTypeDto(testType);
    }

    public List<TestTypeDtoList> getAllTestTypes() {
        List<TestType> testTypeList = testTypeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return CommonBuilder.buildTestTypeDtoList(testTypeList);
    }

    public Page<TestTypeDto> getAllTestTypesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<TestType> testTypePage = testTypeRepository.findAll(pageable);
        int totalElements = (int) testTypePage.getTotalElements();

        return new PageImpl<>(
            testTypePage
                .stream()
                .map(testType -> new TestTypeDto(
                    testType.getId(),
                    testType.getTestName(),
                    testType.getDescription(),
                    testType.getIsActive(),
                    testType.getCreatedAt(),
                    testType.getUpdatedAt(),
                    testType.getCreatedBy(),
                    testType.getUpdatedBy()))
                .collect(Collectors.toList()),
            pageable,
            totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public TestType createTestType(TestTypeDto dto) {
        TestType testType = new TestType();
        BeanUtils.copyProperties(dto, testType, "createdBy", "updatedBy");

        UserProfile userProfile = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());

        testType.setCreatedBy(userProfile);
        testType.setUpdatedBy(userProfile);

        return testTypeRepository.save(testType);
    }

    public TestType getTestTypeByName(String testName) {
        return testTypeRepository.findByTestName(testName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public TestType updateTestType(TestTypeDto dto) {
        TestType testType = testTypeRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("TestType not found with ID: " + dto.getId()));

        try {
            testType.setTestName(dto.getTestName());
            testType.setDescription(dto.getDescription());
            testType.setIsActive(dto.getIsActive());

            UserProfile userProfile = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
            if (userProfile == null) {
                throw new RuntimeException("UserProfile not found with userId: " + dto.getUpdatedBy().getUserId());
            }

            testType.setUpdatedBy(userProfile);
            testType.setUpdatedAt(LocalDateTime.now());

            return testTypeRepository.save(testType);

        } catch (Exception e) {
            logger.error("Error while updating TestType [{}]: {}", dto.getTestName(), e.getMessage(), e);
            throw new RuntimeException("Failed to update TestType: " + dto.getTestName(), e);
        }
    }


    public void deleteTestTypeById(long id) {
        testTypeRepository.deleteById(id);
    }
}
