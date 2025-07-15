package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.FacultyProfileDto;
import com.ws.spring.model.Department;
import com.ws.spring.model.FacultyProfile;
import com.ws.spring.model.JobDesignation;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.DepartmentRepository;
import com.ws.spring.repository.FacultyProfileRepository;
import com.ws.spring.repository.JobDesignationRepository;
import com.ws.spring.repository.UserProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacultyProfileServiceImpl {

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private JobDesignationRepository jobDesignationRepository;

    public FacultyProfileDto getFacultyProfileById(long id) {
        FacultyProfile profile = facultyProfileRepository.findById(id);
        return CommonBuilder.buildFacultyProfileDto(profile);
    }

    public List<FacultyProfileDto> getAllFacultyProfiles() {
        List<FacultyProfile> profileList = facultyProfileRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return profileList.stream()
                .map(CommonBuilder::buildFacultyProfileDto)
                .collect(Collectors.toList());
    }

    public Page<FacultyProfileDto> getAllFacultyProfilesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<FacultyProfile> page = facultyProfileRepository.findAll(pageable);

        List<FacultyProfileDto> dtoList = page.getContent().stream()
                .map(CommonBuilder::buildFacultyProfileDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

    @Transactional
    public FacultyProfile createFacultyProfile(FacultyProfileDto dto) {
        FacultyProfile profile = new FacultyProfile();

        profile.setFirstName(dto.getFirstName());
        profile.setMiddleName(dto.getMiddleName());
        profile.setLastName(dto.getLastName());
        profile.setGender(dto.getGender());
        profile.setQualification(dto.getQualification());
        profile.setEmail(dto.getEmail());
        profile.setFatherName(dto.getFatherName());
        profile.setMotherName(dto.getMotherName());

        if (dto.getDepartment() != null && dto.getDepartment().getDepartmentId() != 0) {
            Department department = departmentRepository.findByDepartmentId(dto.getDepartment().getDepartmentId());
            profile.setDepartment(department);
        }

        if (dto.getDesignation() != null && dto.getDesignation().getDesignationId() != 0) {
            JobDesignation designation = jobDesignationRepository.findByDesignationId(dto.getDesignation().getDesignationId());
            profile.setDesignation(designation);
        }

        UserProfile createdBy = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        profile.setCreatedBy(createdBy);
        profile.setUpdatedBy(createdBy);

        return facultyProfileRepository.save(profile);
    }

    @Transactional
    public FacultyProfile updateFacultyProfile(FacultyProfileDto dto) {
        FacultyProfile profile = facultyProfileRepository.findById(dto.getId());

        profile.setFirstName(dto.getFirstName());
        profile.setMiddleName(dto.getMiddleName());
        profile.setLastName(dto.getLastName());
        profile.setGender(dto.getGender());
        profile.setQualification(dto.getQualification());
        profile.setEmail(dto.getEmail());
        profile.setFatherName(dto.getFatherName());
        profile.setMotherName(dto.getMotherName());

        if (dto.getDepartment() != null && dto.getDepartment().getDepartmentId() != 0) {
            Department department = departmentRepository.findByDepartmentId(dto.getDepartment().getDepartmentId());
            profile.setDepartment(department);
        }

        if (dto.getDesignation() != null && dto.getDesignation().getDesignationId() != 0) {
            JobDesignation designation = jobDesignationRepository.findByDesignationId(dto.getDesignation().getDesignationId());
            profile.setDesignation(designation);
        }

        UserProfile updatedBy = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        profile.setUpdatedBy(updatedBy);

        return facultyProfileRepository.save(profile);
    }

    public void deleteFacultyProfileById(long id) {
        facultyProfileRepository.deleteById(id);
    }
}
