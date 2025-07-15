package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.StudentProfileDto;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.StudentProfile;
import com.ws.spring.model.Course;
import com.ws.spring.model.CourseSubject;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.CourseRepository;
import com.ws.spring.repository.CourseSubjectRepository;
import com.ws.spring.repository.StudentProfileRepository;
import com.ws.spring.repository.UserProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentProfileServiceImpl {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseSubjectRepository courseSubjectRepository;

    public StudentProfileDto getStudentProfileById(long id) {
        StudentProfile profile = studentProfileRepository.findById(id);
        return CommonBuilder.buildStudentProfileDto(profile);
    }

    public List<StudentProfileDto> getAllStudentProfiles() {
        List<StudentProfile> profileList = studentProfileRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return profileList.stream()
                .map(CommonBuilder::buildStudentProfileDto)
                .collect(Collectors.toList());
    }

    public Page<StudentProfileDto> getAllStudentProfilesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<StudentProfile> page = studentProfileRepository.findAll(pageable);

        List<StudentProfileDto> dtoList = page.getContent().stream()
                .map(CommonBuilder::buildStudentProfileDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

    @Transactional
    public StudentProfile createStudentProfile(StudentProfileDto dto) {
        StudentProfile profile = new StudentProfile();
        profile.setFirstName(dto.getFirstName());
        profile.setMiddleName(dto.getMiddleName());
        profile.setLastName(dto.getLastName());
        profile.setGender(dto.getGender());
        profile.setFatherName(dto.getFatherName());
        profile.setMotherName(dto.getMotherName());

        if (dto.getCourse() != null && dto.getCourse().getCourseId() != null) {
            Course course = courseRepository.findByCourseId(dto.getCourse().getCourseId());
            profile.setCourse(course);
        }

        if (dto.getCourseSubject() != null && dto.getCourseSubject().getCourseSubjectId() != null) {
            CourseSubject subject = courseSubjectRepository.findByCourseSubjectId(dto.getCourseSubject().getCourseSubjectId());
            profile.setCourseSubject(subject);
        }

        UserProfile createdBy = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        profile.setCreatedBy(createdBy);
        profile.setUpdatedBy(createdBy);

        return studentProfileRepository.save(profile);
    }

    @Transactional
    public StudentProfile updateStudentProfile(StudentProfileDto dto) {
        StudentProfile profile = studentProfileRepository.findById(dto.getId());

        profile.setFirstName(dto.getFirstName());
        profile.setMiddleName(dto.getMiddleName());
        profile.setLastName(dto.getLastName());
        profile.setGender(dto.getGender());
        profile.setFatherName(dto.getFatherName());
        profile.setMotherName(dto.getMotherName());

        if (dto.getCourse() != null && dto.getCourse().getCourseId() != null) {
            Course course = courseRepository.findByCourseId(dto.getCourse().getCourseId());
            profile.setCourse(course);
        }

        if (dto.getCourseSubject() != null && dto.getCourseSubject().getCourseSubjectId() != null) {
            CourseSubject subject = courseSubjectRepository.findByCourseSubjectId(dto.getCourseSubject().getCourseSubjectId());
            profile.setCourseSubject(subject);
        }

        UserProfile updatedBy = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        profile.setUpdatedBy(updatedBy);

        return studentProfileRepository.save(profile);
    }

    public void deleteStudentProfileById(long id) {
        studentProfileRepository.deleteById(id);
    }
}
