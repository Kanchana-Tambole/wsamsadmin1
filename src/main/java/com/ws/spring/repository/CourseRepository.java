package com.ws.spring.repository;

import com.ws.spring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseId(Long courseId);

    Course findByCourseName(String courseName);
}
