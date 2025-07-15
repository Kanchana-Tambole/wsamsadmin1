package com.ws.spring.service;
 
import java.util.List;
import java.util.Set;
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
 
import com.ws.spring.dto.AcademicCategoryDtoList;
import com.ws.spring.dto.AcademicCourseDto;
import com.ws.spring.dto.AcademicCourseDtoList;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.AcademicCategory;
import com.ws.spring.model.AcademicCourse;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.AcademicCategoryRepository;
import com.ws.spring.repository.AcademicCourseRepository;
import com.ws.spring.repository.UserProfileRepository;
 
@Service
public class AcademicCourseServiceImpl {
 
	
Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	AcademicCourseRepository academicCourseRepository;

	@Autowired
	AcademicCategoryRepository  academicCategoryRepository;
	@Autowired
	UserProfileRepository userProfileRepository;


	public AcademicCourseDto getAcademicCourseByCourseId(long courseId) {
		AcademicCourse academicCourse = academicCourseRepository.findByCourseId(courseId);
        return CommonBuilder.buildAcademicCourseDataDto(academicCourse);
	}
 
	public List<AcademicCourseDto> getAcademicCoursesByCategoryId(long categoryId) {
		AcademicCategory category = academicCategoryRepository.findByCategoryId(categoryId);
		List<AcademicCourse> academicCourseList = academicCourseRepository.findAllByAcademicCategory(category);
		return CommonBuilder.buildAcademicCourseDataDtoList(academicCourseList);
	}
 
	public Page<AcademicCourseDto> getAllAcademicCoursesByPagination(int pageNumber, int pageSize) {
Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("courseId").descending());
		Page<AcademicCourse> advertisementPage = academicCourseRepository.findAll(pageable);
        int totalElements = (int) advertisementPage.getTotalElements();
        return new PageImpl<AcademicCourseDto>(advertisementPage
                .stream()
                .map(academicCourse -> new AcademicCourseDto(
                		academicCourse.getCourseId(),
                		academicCourse.getCourseName(),
                		academicCourse.getDescription(), academicCourse.getCourseMrp(), academicCourse.getDiscount(), academicCourse.getSellingPrice(), academicCourse.getHandlingFee(), academicCourse.getTrailPeriod(), academicCourse.getSubscriptionDays(), academicCourse.getVideoUrl(), academicCourse.getInsertedDate(), academicCourse.getUpdatedDate(), academicCourse.getCreatedBy(), academicCourse.getUpdatedBy(), academicCourse.getAcademicCategory()))
                .collect(Collectors.toList()), pageable, totalElements);
	}
 
	
	@Transactional(propagation = Propagation.REQUIRED)
	public AcademicCourse createAcademicCourse(AcademicCourseDto courseDto) {
		 AcademicCourse course = new AcademicCourse();
		 BeanUtils.copyProperties(courseDto, course,"createdBy","updatedBy");

		 AcademicCategoryDtoList courseCategoryDto = courseDto.getAcademicCategoryDtoList();
			if(null != courseCategoryDto && courseCategoryDto.getCategoryId() != 0) {
				AcademicCategory category = academicCategoryRepository.findByCategoryId(courseDto.getAcademicCategoryDtoList().getCategoryId());
			 course.setAcademicCategory(category);
			}

			 UserProfile userProfile = userProfileRepository.findByUserId(courseDto.getCreatedBy().getUserId());
			 course.setCreatedBy(userProfile);
			 course.setUpdatedBy(userProfile);
		return academicCourseRepository.save(course);
	}
 
	public AcademicCourse getCourseNameExist(String courseName) {
		return academicCourseRepository.findByCourseName(courseName);
	}
 
	
	@Transactional(propagation = Propagation.REQUIRED)
	public AcademicCourse updateAcademicCourse(AcademicCourseDto courseDto) {
		AcademicCourse course = academicCourseRepository.findByCourseId(courseDto.getCourseId());
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
		AcademicCategoryDtoList courseCategoryDto = courseDto.getAcademicCategoryDtoList();
		if(null != courseCategoryDto && courseCategoryDto.getCategoryId() != 0) {
			AcademicCategory category = academicCategoryRepository.findByCategoryId(courseDto.getAcademicCategoryDtoList().getCategoryId());
		 course.setAcademicCategory(category);
		}

			 UserProfile userProfile = userProfileRepository.findByUserId(courseDto.getCreatedBy().getUserId());
			 course.setUpdatedBy(userProfile);
		return academicCourseRepository.save(course);
	}
 
	public void deleteAcademicCourseById(long courseId) {
		academicCourseRepository.deleteById(courseId);
	}
 
	public List<AcademicCourseDtoList> getAllAcademicCourses() {
		List<AcademicCourse> academicCourseList = academicCourseRepository.findAll();
        return CommonBuilder.buildAcademicCourseDtoList((Set<AcademicCourse>) academicCourseList);
	}
 
	






}