package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.SubjectDto;
import com.ws.spring.model.Subject;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.SubjectRepository;
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
public class SubjectServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public SubjectDto getSubjectById(long id) {
        Subject subject = subjectRepository.findById(id);
        return CommonBuilder.buildSubjectDto(subject);
    }

    public List<SubjectDto> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return subjects.stream()
                .map(CommonBuilder::buildSubjectDto)
                .collect(Collectors.toList());
    }

    public Page<SubjectDto> getAllSubjectsByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<Subject> page = subjectRepository.findAll(pageable);
        int totalElements = (int) page.getTotalElements();

        return new PageImpl<>(
                page.stream()
                        .map(CommonBuilder::buildSubjectDto)
                        .collect(Collectors.toList()),
                pageable,
                totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Subject createSubject(SubjectDto dto) {
        Subject subject = new Subject();
        BeanUtils.copyProperties(dto, subject, "createdBy", "updatedBy");

        UserProfile user = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        subject.setCreatedBy(user);
        subject.setUpdatedBy(user);

        return subjectRepository.save(subject);
    }

    public Subject getSubjectByName(String subjectName) {
        return subjectRepository.findBySubjectName(subjectName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Subject updateSubject(SubjectDto dto) {
        Subject subject = subjectRepository.findById(dto.getId()).orElseThrow(() ->
                new RuntimeException("Subject not found with ID: " + dto.getId()));

        try {
            subject.setSubjectName(dto.getSubjectName());
            subject.setSubjectCode(dto.getSubjectCode());
        } catch (Exception e) {
            logger.error("Error while updating Subject {}: {}", dto.getSubjectName(), e.getMessage());
        }

        UserProfile user = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        subject.setUpdatedBy(user);

        return subjectRepository.save(subject);
    }

    public void deleteSubjectById(long id) {
        subjectRepository.deleteById(id);
    }
}
