package com.ws.spring.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.ws.spring.model.UpskillModule;
import com.ws.spring.model.UpskillTopic;
 
@Repository
public interface UpskillTopicRepository extends JpaRepository<UpskillTopic, Long> {
 
	UpskillTopic findByTopicId(long topicId);
 
	List<UpskillTopic> findAllByUpskillModule(UpskillModule upskillModule);
 
	

}