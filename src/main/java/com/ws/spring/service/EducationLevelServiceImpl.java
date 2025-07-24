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

import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.EducationLevelDto;
import com.ws.spring.model.EducationLevel;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.EducationLevelRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class EducationLevelServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private EducationLevelRepository educationLevelRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public EducationLevelDto getEducationLevelById(long id) {
        EducationLevel level = educationLevelRepository.findById(id);
        return CommonBuilder.buildEducationLevelDto(level);
    }

    public List<EducationLevelDto> getAllEducationLevels() {
        List<EducationLevel> levels = educationLevelRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return levels.stream()
                .map(CommonBuilder::buildEducationLevelDto)
                .collect(Collectors.toList());
    }

    public Page<EducationLevelDto> getAllEducationLevelsByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<EducationLevel> page = educationLevelRepository.findAll(pageable);
        int totalElements = (int) page.getTotalElements();

        return new PageImpl<>(
                page.stream()
                        .map(CommonBuilder::buildEducationLevelDto)
                        .collect(Collectors.toList()),
                pageable,
                totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public EducationLevel createEducationLevel(EducationLevelDto dto) {
        EducationLevel level = new EducationLevel();
        BeanUtils.copyProperties(dto, level, "createdBy", "updatedBy");

        UserProfile user = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        level.setCreatedBy(user);
        level.setUpdatedBy(user);

        return educationLevelRepository.save(level);
    }

    public EducationLevel getLevelNameExist(String levelName) {
        return educationLevelRepository.findByLevelName(levelName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public EducationLevel updateEducationLevel(EducationLevelDto dto) {
        EducationLevel level = educationLevelRepository.findById(dto.getId()).orElseThrow(null);

        try {
            level.setLevelName(dto.getLevelName());
            level.setDescription(dto.getDescription());
        } catch (Exception e) {
            logger.error("Error while updating EducationLevel {}: {}", dto.getLevelName(), e.getMessage());
        }

        UserProfile user = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        level.setUpdatedBy(user);

        return educationLevelRepository.save(level);
    }

    public void deleteEducationLevelById(long id) {
        educationLevelRepository.deleteById(id);
    }
}
