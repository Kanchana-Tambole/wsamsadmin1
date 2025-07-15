package com.ws.spring.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.ws.spring.model.UpskillCategory;
import com.ws.spring.model.UpskillCourse;
 
@Repository
public interface UpskillCourseRepository extends JpaRepository<UpskillCourse, Long>{
 
	UpskillCourse findByCourseId(long courseId);
 
	List<UpskillCourse> findAllByUpskillCategory(UpskillCategory category);
 
	UpskillCourse findByCourseName(String courseName);
 
}