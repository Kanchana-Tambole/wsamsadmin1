package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.spring.dto.CourseDto;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.Course;
import com.ws.spring.model.CourseSubject;
import com.ws.spring.repository.CourseRepository;
import com.ws.spring.repository.CourseSubjectRepository;

@Service
public class CourseServiceImpl {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseSubjectRepository courseSubjectRepository;

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
        }

        return courseRepository.save(course);
    }

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
        }

        return courseRepository.save(course);
    }

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
}
