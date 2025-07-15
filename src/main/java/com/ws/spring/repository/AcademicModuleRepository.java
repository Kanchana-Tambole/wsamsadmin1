package com.ws.spring.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
 
import com.ws.spring.model.AcademicCourse;

import com.ws.spring.model.AcademicModule;

import com.ws.spring.model.AcademicTopic;
 
@Repository

public interface AcademicModuleRepository extends JpaRepository<AcademicModule, Long> {
 
	AcademicModule findByModuleId(long moduleId);
 
	

	List<AcademicModule> findAllByAcademicCourses(AcademicCourse academicCourse);
 
 
}

 