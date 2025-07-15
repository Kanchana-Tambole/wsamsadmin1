package com.ws.spring.repository;

import com.ws.spring.model.CourseSubject;
import com.ws.spring.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSubjectRepository extends JpaRepository<CourseSubject, Long> {

    CourseSubject findByCourseSubjectId(Long courseSubjectId);

    List<CourseSubject> findAllByCourse(Course course);

    CourseSubject findBySubjectName(String subjectName);
}
