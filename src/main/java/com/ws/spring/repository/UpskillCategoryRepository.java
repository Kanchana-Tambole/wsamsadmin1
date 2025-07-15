package com.ws.spring.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.ws.spring.model.UpskillCategory;
 
@Repository
public interface UpskillCategoryRepository extends JpaRepository<UpskillCategory, Long>{
 
	UpskillCategory findByCategoryId(long categoryId);
 
	UpskillCategory findByCategoryName(String categoryName);




 
}