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

import com.ws.spring.dto.CourseSubjectDto;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.Course;
import com.ws.spring.model.CourseSubject;
import com.ws.spring.repository.CourseRepository;
import com.ws.spring.repository.CourseSubjectRepository;

@Service
public class CourseSubjectServiceImpl {

    @Autowired
    private CourseSubjectRepository courseSubjectRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public CourseSubject createCourseSubject(CourseSubjectDto dto) {
        CourseSubject subject = new CourseSubject();
        subject.setSubjectName(dto.getSubjectName());

        if (dto.getCourse() != null && dto.getCourse().getCourseId() != null) {
            Course course = courseRepository.findByCourseId(dto.getCourse().getCourseId());
            subject.setCourse(course);
        }

        return courseSubjectRepository.save(subject);
    }

    @Transactional
    public CourseSubject updateCourseSubject(CourseSubjectDto dto) {
        CourseSubject subject = courseSubjectRepository.findByCourseSubjectId(dto.getCourseSubjectId());
        subject.setSubjectName(dto.getSubjectName());

        if (dto.getCourse() != null && dto.getCourse().getCourseId() != null) {
            Course course = courseRepository.findByCourseId(dto.getCourse().getCourseId());
            subject.setCourse(course);
        }

        return courseSubjectRepository.save(subject);
    }

    public void deleteCourseSubjectById(long id) {
        courseSubjectRepository.deleteById(id);
    }

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
}
