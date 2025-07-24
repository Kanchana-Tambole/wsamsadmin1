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

import com.ws.spring.dto.CourseDto;
=======

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ws.spring.dto.CourseDto;
import com.ws.spring.dto.CourseDtoList;
import com.ws.spring.dto.CourseSubjectDto;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.Course;
import com.ws.spring.model.CourseSubject;
import com.ws.spring.repository.CourseRepository;
import com.ws.spring.repository.CourseSubjectRepository;

@Service
public class CourseServiceImpl {

<<<<<<< HEAD
=======
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

>>>>>>> daccd45 (Initial commit)
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseSubjectRepository courseSubjectRepository;

<<<<<<< HEAD
    @Transactional
    public Course createCourse(CourseDto dto) {
        Course course = new Course();
        course.setCourseName(dto.getCourseName());

        if (dto.getCourseSubjects() != null && !dto.getCourseSubjects().isEmpty()) {
            List<CourseSubject> subjects = dto.getCourseSubjects().stream()
                    .map(subjectDto -> {
                        CourseSubject subject = new CourseSubject();
                        subject.setSubjectName(subjectDto.getSubjectName());
                        subject.setCourse(course); // associate back-reference
                        return subject;
                    })
                    .collect(Collectors.toList());

            course.setCourseSubjects(subjects);
=======
    public CourseDto getCourseById(long courseId) {
        Course course = courseRepository.findByCourseId(courseId);
        return CommonBuilder.buildCourseDto(course);
    }

    public List<CourseDtoList> getAllCourses() {
        List<Course> courseList = courseRepository.findAll(Sort.by(Sort.Direction.DESC, "courseId"));
        return CommonBuilder.buildCourseDtoList(courseList);
    }

    public Page<CourseDto> getAllCoursesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("courseId").descending());
        Page<Course> coursePage = courseRepository.findAll(pageable);

        int totalElements = (int) coursePage.getTotalElements();

        return new PageImpl<>(
                coursePage.stream()
                        .map(course -> new CourseDto(course, true))
                        .collect(Collectors.toList()),
                pageable,
                totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Course createCourse(CourseDto courseDto) {
        Course course = new Course();
        BeanUtils.copyProperties(courseDto, course, "courseSubjects");

        if (courseDto.getCourseSubjects() != null && !courseDto.getCourseSubjects().isEmpty()) {
            List<CourseSubject> subjectList = courseDto.getCourseSubjects().stream()
                    .map(dto -> {
                        CourseSubject subject = new CourseSubject();
                        subject.setSubjectName(dto.getSubjectName());
                        subject.setCourse(course); // back-reference
                        return subject;
                    }).collect(Collectors.toList());

            course.setCourseSubjects(subjectList);
>>>>>>> daccd45 (Initial commit)
        }

        return courseRepository.save(course);
    }

<<<<<<< HEAD
    @Transactional
    public Course updateCourse(CourseDto dto) {
        Course course = courseRepository.findByCourseId(dto.getCourseId());

        course.setCourseName(dto.getCourseName());

        // Optional: update or replace courseSubjects
        if (dto.getCourseSubjects() != null) {
            course.getCourseSubjects().clear(); // remove existing ones if needed

            List<CourseSubject> updatedSubjects = dto.getCourseSubjects().stream()
                    .map(subjectDto -> {
                        CourseSubject subject = new CourseSubject();
                        subject.setSubjectName(subjectDto.getSubjectName());
                        subject.setCourse(course); // re-assign the course
                        return subject;
                    })
                    .collect(Collectors.toList());

            course.getCourseSubjects().addAll(updatedSubjects);
=======
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Course updateCourse(CourseDto courseDto) {
        Course course = courseRepository.findByCourseId(courseDto.getCourseId());

        try {
            course.setCourseName(courseDto.getCourseName());

            course.getCourseSubjects().clear();

            if (courseDto.getCourseSubjects() != null) {
                List<CourseSubject> updatedSubjects = courseDto.getCourseSubjects().stream()
                        .map(dto -> {
                            CourseSubject subject = new CourseSubject();
                            subject.setSubjectName(dto.getSubjectName());
                            subject.setCourse(course); // back-reference
                            return subject;
                        }).collect(Collectors.toList());

                course.getCourseSubjects().addAll(updatedSubjects);
            }

        } catch (Exception e) {
            logger.error("Error while updating Course {} and the Error is : {}", courseDto.getCourseName(), e.getMessage());
>>>>>>> daccd45 (Initial commit)
        }

        return courseRepository.save(course);
    }

<<<<<<< HEAD
    public void deleteCourseById(long id) {
        courseRepository.deleteById(id);
    }

    public CourseDto getCourseById(long id) {
        return CommonBuilder.buildCourseDto(courseRepository.findByCourseId(id));
    }

    public List<CourseDto> getAllCourses() {
        List<Course> list = courseRepository.findAll(Sort.by(Sort.Direction.DESC, "courseId"));
        return CommonBuilder.buildCourseDtoList(list);
    }

    public Page<CourseDto> getAllCoursesByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("courseId").descending());
        Page<Course> entityPage = courseRepository.findAll(pageable);

        List<CourseDto> dtoList = entityPage.getContent().stream()
                .map(CommonBuilder::buildCourseDto)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, entityPage.getTotalElements());
    }
=======
    public void deleteCourseById(long courseId) {
        courseRepository.deleteById(courseId);
    }

>>>>>>> daccd45 (Initial commit)
}
