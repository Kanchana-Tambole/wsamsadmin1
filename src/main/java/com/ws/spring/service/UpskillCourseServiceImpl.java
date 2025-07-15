package com.ws.spring.service;
 
import java.util.List;
import java.util.stream.Collectors;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
 
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.UpskillCategoryDtoList;
import com.ws.spring.dto.UpskillCourseDTo;
import com.ws.spring.dto.UpskillCourseDToList;
import com.ws.spring.model.UpskillCategory;
import com.ws.spring.model.UpskillCourse;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.UpskillCategoryRepository;
import com.ws.spring.repository.UpskillCourseRepository;
import com.ws.spring.repository.UserProfileRepository;
 
@Service
public class UpskillCourseServiceImpl {
 
	
Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	UpskillCourseRepository  upskillCourseRepository;

	@Autowired
	UpskillCategoryRepository  upskillCategoryRepository;
	@Autowired
	UserProfileRepository userProfileRepository;


	public UpskillCourseDTo getUpskillCourseByCourseId(long courseId) {
		UpskillCourse upskillCourse = upskillCourseRepository.findByCourseId(courseId);
        return CommonBuilder.buildUpskillCourseDto(upskillCourse);
	}
 
	public List<UpskillCourseDTo> getUpskillCoursesByCategoryId(long categoryId) {
		UpskillCategory category = upskillCategoryRepository.findByCategoryId(categoryId);
		List<UpskillCourse> upskillCourseList = upskillCourseRepository.findAllByUpskillCategory(category);
        return CommonBuilder.buildUpskillCourseDtoList(upskillCourseList);
	}
 
	public Page<UpskillCourseDTo> getAllUpskillCoursesByPagination(int pageNumber, int pageSize) {
Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("courseId").descending());
		Page<UpskillCourse> advertisementPage = upskillCourseRepository.findAll(pageable);
        int totalElements = (int) advertisementPage.getTotalElements();
        return new PageImpl<UpskillCourseDTo>(advertisementPage
                .stream()
                .map(upskillCourse -> new UpskillCourseDTo(
                		upskillCourse.getCourseId(),
                		upskillCourse.getCourseName(),
                		upskillCourse.getDescription(), upskillCourse.getCourseMrp(), upskillCourse.getDiscount(), upskillCourse.getSellingPrice(), upskillCourse.getHandlingFee(), upskillCourse.getTrailPeriod(), upskillCourse.getSubscriptionDays(), upskillCourse.getVideoUrl(), upskillCourse.getInsertedDate(), upskillCourse.getUpdatedDate(), upskillCourse.getCreatedBy(), upskillCourse.getUpdatedBy(), upskillCourse.getUpskillCategory()))
                .collect(Collectors.toList()), pageable, totalElements);
	}
 
	
	@Transactional(propagation = Propagation.REQUIRED)
	public UpskillCourse createUpskillCourse(UpskillCourseDTo courseDto) {
		UpskillCourse course = new UpskillCourse();
		 BeanUtils.copyProperties(courseDto, course,"createdBy","updatedBy");

		 UpskillCategoryDtoList courseCategoryDto = courseDto.getUpskillCategoryDtoList();
			if(null != courseCategoryDto && courseCategoryDto.getCategoryId() != 0) {
				UpskillCategory category = upskillCategoryRepository.findByCategoryId(courseDto.getUpskillCategoryDtoList().getCategoryId());
			 course.setUpskillCategory(category);
			}

			 UserProfile userProfile = userProfileRepository.findByUserId(courseDto.getCreatedBy().getUserId());
			 course.setCreatedBy(userProfile);
			 course.setUpdatedBy(userProfile);
		return upskillCourseRepository.save(course);
	}
 
	public UpskillCourse getCourseNameExist(String courseName) {
		return upskillCourseRepository.findByCourseName(courseName);
	}
 
	
	@Transactional(propagation = Propagation.REQUIRED)
	public UpskillCourse updateUpskillCourse(UpskillCourseDTo courseDto) {
		UpskillCourse course = upskillCourseRepository.findByCourseId(courseDto.getCourseId());
		try {
		course.setCourseName(courseDto.getCourseName());
		course.setDescription(courseDto.getDescription());
		course.setCourseMrp(courseDto.getCourseMrp());
		course.setDiscount(courseDto.getDiscount());
		course.setSellingPrice(courseDto.getSellingPrice());
		course.setVideoUrl(courseDto.getVideoUrl());


		} catch (Exception e) {
			logger.error(" Error while updating Course {} and the Error is : {}", courseDto.getCourseName(),
					e.getMessage());
		}
		 UpskillCategoryDtoList courseCategoryDto = courseDto.getUpskillCategoryDtoList();
			if(null != courseCategoryDto && courseCategoryDto.getCategoryId() != 0) {
				UpskillCategory category = upskillCategoryRepository.findByCategoryId(courseDto.getUpskillCategoryDtoList().getCategoryId());
			 course.setUpskillCategory(category);
			}

			 UserProfile userProfile = userProfileRepository.findByUserId(courseDto.getCreatedBy().getUserId());
			 course.setUpdatedBy(userProfile);
		return upskillCourseRepository.save(course);
	}
 
	public void deleteUpskillCourseById(long courseId) {
		upskillCourseRepository.deleteById(courseId);
	}
 
	public List<UpskillCourseDToList> getAllUpskillCourses() {
    List<UpskillCourse> upskillCourseList = upskillCourseRepository.findAll();
        return CommonBuilder.buildUpskillCourseDataDToList(upskillCourseList);
	}
 
	









}