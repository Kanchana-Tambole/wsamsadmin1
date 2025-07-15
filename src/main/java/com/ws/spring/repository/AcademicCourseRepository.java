package com.ws.spring.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
 
import com.ws.spring.model.AcademicCategory;

import com.ws.spring.model.AcademicCourse;
 
 
@Repository

public interface AcademicCourseRepository extends JpaRepository<AcademicCourse, Long> {
 
	AcademicCourse findByCourseId(long courseId);
 
	List<AcademicCourse> findAllByAcademicCategory(AcademicCategory category);
 
	AcademicCourse findByCourseName(String courseName);
 
	


}

 