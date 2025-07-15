package com.ws.spring.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
 
import com.ws.spring.model.AcademicModule;

import com.ws.spring.model.AcademicTopic;
 
@Repository

public interface AcademicTopicRepository extends JpaRepository<AcademicTopic, Long> {
 
	AcademicTopic findByTopicId(long topicId);
 
	List<AcademicTopic> findAllByAcademicModule(AcademicModule academicModule);



 
}

 