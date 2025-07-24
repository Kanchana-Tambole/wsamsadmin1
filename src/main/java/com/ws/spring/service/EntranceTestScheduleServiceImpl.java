package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.*;
import com.ws.spring.model.*;
import com.ws.spring.repository.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EntranceTestScheduleServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private EntranceTestScheduleRepository entranceTestScheduleRepository;

    @Autowired
    private TestTypeRepository testTypeRepository;

    @Autowired
    private TestCenterRepository testCenterRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public EntranceTestScheduleDto getEntranceTestById(Long id) {
        EntranceTestSchedule test = entranceTestScheduleRepository.findById(id).orElseThrow();
        return CommonBuilder.buildEntranceTestScheduleDto(test);
    }

    public List<EntranceTestScheduleDtoList> getAllEntranceTests() {
        List<EntranceTestSchedule> list = entranceTestScheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return CommonBuilder.buildEntranceTestScheduleMinimalDtoList(list);
    }


    public Page<EntranceTestScheduleDto> getAllEntranceTestsByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<EntranceTestSchedule> testPage = entranceTestScheduleRepository.findAll(pageable);
        int totalElements = (int) testPage.getTotalElements();

        return new PageImpl<>(
            testPage.stream().map(test -> new EntranceTestScheduleDto(
                test.getId(),
                test.getTestType(),
                test.getTestDate(),
                test.getStartTime(),
                test.getEndTime(),
                test.getTestCenter(),
                test.getRemarks(),
                test.getCreatedAt(),
                test.getUpdatedAt(),
                test.getCreatedBy(),
                test.getUpdatedBy()
            )).collect(Collectors.toList()), pageable, totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public EntranceTestSchedule createEntranceTestSchedule(EntranceTestScheduleDto dto) {
        EntranceTestSchedule test = new EntranceTestSchedule();

        BeanUtils.copyProperties(dto, test, "testType", "testCenter", "createdBy", "updatedBy");

        TestType testType = testTypeRepository.findById(dto.getTestType().getId()).orElse(null);
        TestCenter testCenter = testCenterRepository.findById(dto.getTestCenter().getId()).orElse(null);
        UserProfile createdBy = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());

        test.setTestType(testType);
        test.setTestCenter(testCenter);
        test.setCreatedBy(createdBy);
        test.setUpdatedBy(createdBy); // set same user on creation

        return entranceTestScheduleRepository.save(test);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public EntranceTestSchedule updateEntranceTestSchedule(EntranceTestScheduleDto dto) {
        EntranceTestSchedule test = entranceTestScheduleRepository.findById(dto.getId()).orElseThrow();

        try {
            test.setTestDate(dto.getTestDate());
            test.setStartTime(dto.getStartTime());
            test.setEndTime(dto.getEndTime());
            test.setRemarks(dto.getRemarks());

            TestType testType = testTypeRepository.findById(dto.getTestType().getId()).orElse(null);
            TestCenter testCenter = testCenterRepository.findById(dto.getTestCenter().getId()).orElse(null);
            UserProfile updatedBy = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());

            test.setTestType(testType);
            test.setTestCenter(testCenter);
            test.setUpdatedBy(updatedBy);

        } catch (Exception e) {
            logger.error("Error while updating EntranceTestSchedule {}: {}", dto.getId(), e.getMessage());
        }

        return entranceTestScheduleRepository.save(test);
    }

    public void deleteEntranceTestScheduleById(Long id) {
        entranceTestScheduleRepository.deleteById(id);
    }
}
