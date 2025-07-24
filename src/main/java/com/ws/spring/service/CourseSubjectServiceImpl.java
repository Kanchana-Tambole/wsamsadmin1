package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

>>>>>>> daccd45 (Initial commit)
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
<<<<<<< HEAD
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.spring.dto.CourseSubjectDto;
import com.ws.spring.dto.CommonBuilder;
=======

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ws.spring.dto.CourseSubjectDto;
import com.ws.spring.dto.CourseSubjectDtoList;
import com.ws.spring.dto.CourseDtoList;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.model.Course;
import com.ws.spring.model.CourseSubject;
import com.ws.spring.repository.CourseRepository;
import com.ws.spring.repository.CourseSubjectRepository;

@Service
public class CourseSubjectServiceImpl {

<<<<<<< HEAD
=======
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

>>>>>>> daccd45 (Initial commit)
    @Autowired
    private CourseSubjectRepository courseSubjectRepository;

    @Autowired
    private CourseRepository courseRepository;

<<<<<<< HEAD
    @Transactional
    public CourseSubject createCourseSubject(CourseSubjectDto dto) {
        CourseSubject subject = new CourseSubject();
        subject.setSubjectName(dto.getSubjectName());
=======
    public CourseSubjectDto getCourseSubjectById(long courseSubjectId) {
        CourseSubject courseSubject = courseSubjectRepository.findByCourseSubjectId(courseSubjectId);
        return new CourseSubjectDto(courseSubject);
    }

    public List<CourseSubjectDtoList> getAllCourseSubjects() {
        List<CourseSubject> subjectList = courseSubjectRepository.findAll(Sort.by(Sort.Direction.DESC, "courseSubjectId"));
        return subjectList.stream()
                .map(subject -> new CourseSubjectDtoList(subject.getCourseSubjectId(), subject.getSubjectName()))
                .collect(Collectors.toList());
    }

    public Page<CourseSubjectDto> getAllCourseSubjectsByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("courseSubjectId").descending());
        Page<CourseSubject> subjectPage = courseSubjectRepository.findAll(pageable);

        int totalElements = (int) subjectPage.getTotalElements();

        return new PageImpl<>(
                subjectPage.stream()
                        .map(CourseSubjectDto::new)
                        .collect(Collectors.toList()),
                pageable,
                totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CourseSubject createCourseSubject(CourseSubjectDto dto) {
        CourseSubject subject = new CourseSubject();
        BeanUtils.copyProperties(dto, subject, "course");
>>>>>>> daccd45 (Initial commit)

        if (dto.getCourse() != null && dto.getCourse().getCourseId() != null) {
            Course course = courseRepository.findByCourseId(dto.getCourse().getCourseId());
            subject.setCourse(course);
        }

        return courseSubjectRepository.save(subject);
    }

<<<<<<< HEAD
    @Transactional
    public CourseSubject updateCourseSubject(CourseSubjectDto dto) {
        CourseSubject subject = courseSubjectRepository.findByCourseSubjectId(dto.getCourseSubjectId());
        subject.setSubjectName(dto.getSubjectName());

        if (dto.getCourse() != null && dto.getCourse().getCourseId() != null) {
            Course course = courseRepository.findByCourseId(dto.getCourse().getCourseId());
            subject.setCourse(course);
=======
    @Transactional(propagation = Propagation.REQUIRED)
    public CourseSubject updateCourseSubject(CourseSubjectDto dto) {
        CourseSubject subject = courseSubjectRepository.findByCourseSubjectId(dto.getCourseSubjectId());

        try {
            subject.setSubjectName(dto.getSubjectName());

            if (dto.getCourse() != null && dto.getCourse().getCourseId() != null) {
                Course course = courseRepository.findByCourseId(dto.getCourse().getCourseId());
                subject.setCourse(course);
            }

        } catch (Exception e) {
            logger.error("Error while updating CourseSubject {} - {}", dto.getSubjectName(), e.getMessage());
>>>>>>> daccd45 (Initial commit)
        }

        return courseSubjectRepository.save(subject);
    }

    public void deleteCourseSubjectById(long id) {
        courseSubjectRepository.deleteById(id);
    }
<<<<<<< HEAD

    public CourseSubjectDto getCourseSubjectById(long id) {
        return CommonBuilder.buildCourseSubjectDto(courseSubjectRepository.findByCourseSubjectId(id));
    }

    public List<CourseSubjectDto> getAllCourseSubjects() {
        List<CourseSubject> list = courseSubjectRepository.findAll(Sort.by(Sort.Direction.DESC, "courseSubjectId"));
        return CommonBuilder.buildCourseSubjectDtoList(list);
    }

    public Page<CourseSubjectDto> getAllCourseSubjectsByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("courseSubjectId").descending());
        Page<CourseSubject> entityPage = courseSubjectRepository.findAll(pageable);

        List<CourseSubjectDto> dtoList = entityPage.getContent().stream()
                .map(CommonBuilder::buildCourseSubjectDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }
=======
>>>>>>> daccd45 (Initial commit)
}
