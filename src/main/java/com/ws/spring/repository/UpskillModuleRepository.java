package com.ws.spring.repository;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.ws.spring.model.UpskillCourse;
import com.ws.spring.model.UpskillModule;
 
@Repository
public interface UpskillModuleRepository extends JpaRepository<UpskillModule, Long>{
 
	UpskillModule findByModuleId(long moduleId);
 
	List<UpskillModule> findAllByUpskillCourses(UpskillCourse upskillCourse);
 
}