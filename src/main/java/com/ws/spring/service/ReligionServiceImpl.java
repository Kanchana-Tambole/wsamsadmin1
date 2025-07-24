package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

<<<<<<< HEAD
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

>>>>>>> daccd45 (Initial commit)
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ws.spring.dto.ReligionDto;
<<<<<<< HEAD
=======
import com.ws.spring.dto.ReligionDtoList;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.Religion;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.ReligionRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class ReligionServiceImpl {

<<<<<<< HEAD
=======
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

>>>>>>> daccd45 (Initial commit)
    @Autowired
    ReligionRepository religionRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

<<<<<<< HEAD
=======
    public ReligionDto getReligionById(long religionId) {
        Religion religion = religionRepository.findByReligionId(religionId);
        return CommonBuilder.buildReligionDto(religion);
    }

    public List<ReligionDto> getAllReligions() {
        List<Religion> religionList = religionRepository.findAll(Sort.by(Sort.Direction.DESC, "religionId"));
        return CommonBuilder.buildReligionDtoList(religionList);
    }

    public Page<ReligionDto> getAllReligionByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("religionId").descending());
        Page<Religion> religionPage = religionRepository.findAll(pageable);
        int totalElements = (int) religionPage.getTotalElements();

        return new PageImpl<>(
            religionPage.stream()
                .map(CommonBuilder::buildReligionDto)
                .collect(Collectors.toList()),
            pageable,
            totalElements
        );
    }

>>>>>>> daccd45 (Initial commit)
    @Transactional(propagation = Propagation.REQUIRED)
    public Religion createReligion(ReligionDto religionDto) {
        Religion religion = new Religion();
        BeanUtils.copyProperties(religionDto, religion, "createdBy", "updatedBy");

        UserProfile user = userProfileRepository.findByUserId(religionDto.getCreatedBy().getUserId());
        religion.setCreatedBy(user);
        religion.setUpdatedBy(user);

        return religionRepository.save(religion);
    }

<<<<<<< HEAD
    public Religion getReligionNameExist(String religionName) {
        return religionRepository.findByReligionName(religionName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Religion updateReligion(ReligionDto religionDto) {
        Religion religion = religionRepository.findByReligionId(religionDto.getReligionId());
        religion.setReligionName(religionDto.getReligionName());
=======
    @Transactional(propagation = Propagation.REQUIRED)
    public Religion updateReligion(ReligionDto religionDto) {
        Religion religion = religionRepository.findByReligionId(religionDto.getReligionId());

        try {
            religion.setReligionName(religionDto.getReligionName());
        } catch (Exception e) {
            logger.error("Error while updating Religion {}: {}", religionDto.getReligionName(), e.getMessage());
        }
>>>>>>> daccd45 (Initial commit)

        UserProfile user = userProfileRepository.findByUserId(religionDto.getUpdatedBy().getUserId());
        religion.setUpdatedBy(user);

        return religionRepository.save(religion);
    }

<<<<<<< HEAD
    public ReligionDto getReligionById(long religionId) {
        Religion religion = religionRepository.findByReligionId(religionId);
        return CommonBuilder.buildReligionDto(religion); // ✅ using builder method
    }

    public Page<ReligionDto> getAllReligionByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("religionId").descending());
        Page<Religion> page = religionRepository.findAll(pageable);

        List<ReligionDto> dtoList = page.stream()
                .map(CommonBuilder::buildReligionDto) // ✅ using builder method
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

    public List<ReligionDto> getAllReligions() {
        List<Religion> religionList = religionRepository.findAll(Sort.by(Sort.Direction.DESC, "religionId"));
        return religionList.stream()
                .map(CommonBuilder::buildReligionDto) // ✅ using builder method
                .collect(Collectors.toList());
=======
    public Religion getReligionNameExist(String religionName) {
        return religionRepository.findByReligionName(religionName);
>>>>>>> daccd45 (Initial commit)
    }

    public void deleteReligionById(long religionId) {
        religionRepository.deleteById(religionId);
    }
}
