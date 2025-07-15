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
 
import com.ws.spring.dto.AcademicCategoryDto;

import com.ws.spring.dto.AcademicCategoryDtoList;

import com.ws.spring.dto.CommonBuilder;

import com.ws.spring.model.AcademicCategory;

import com.ws.spring.model.UserProfile;

import com.ws.spring.repository.AcademicCategoryRepository;

import com.ws.spring.repository.UserProfileRepository;
 
 
@Service

public class AcademicCategoryServiceImpl {
 
Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired

	AcademicCategoryRepository  academicCategoryRepository;

	@Autowired

	UserProfileRepository userProfileRepository;


	public AcademicCategoryDto getAcademicCategoryByCategoryId(long categoryId) {

		AcademicCategory category = academicCategoryRepository.findByCategoryId(categoryId);

        return CommonBuilder.buildAcademicCategoryDto(category);

	}
 
	public List<AcademicCategoryDtoList> getAllAcademicCategory() {

		List<AcademicCategory> categoryList = academicCategoryRepository.findAll(Sort.by(Sort.Direction.DESC, "categoryId"));

        return CommonBuilder.buildAcademicCategoryDtoList(categoryList);

	}
 
	public Page<AcademicCategoryDto> getAllAcademicCategoryByPagination(int pageNumber, int pageSize) {

Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("categoryId").descending());

		Page<AcademicCategory> advertisementPage = academicCategoryRepository.findAll(pageable);

        int totalElements = (int) advertisementPage.getTotalElements();

        return new PageImpl<AcademicCategoryDto>(advertisementPage

                .stream()

                .map(academicCategory -> new AcademicCategoryDto(

                		academicCategory.getCategoryId(),

                		academicCategory.getCategoryName(),

                		academicCategory.getDescription(), academicCategory.getInsertedDate(), academicCategory.getUpdatedDate(), academicCategory.getCreatedBy(), academicCategory.getUpdatedBy()))

                .collect(Collectors.toList()), pageable, totalElements);

	}
 
	

	@Transactional(propagation = Propagation.REQUIRED)

	public AcademicCategory createAcademicCategory(AcademicCategoryDto categoryDto) {


		 AcademicCategory category = new AcademicCategory();

		 BeanUtils.copyProperties(categoryDto, category,"createdBy","updatedBy");

		 UserProfile userProfile = userProfileRepository.findByUserId(categoryDto.getCreatedBy().getUserId());

		 category.setCreatedBy(userProfile);

		 category.setUpdatedBy(userProfile);


		return academicCategoryRepository.save(category);

	}
 
	public AcademicCategory getCategoryNameExist(String categoryName) {

		return academicCategoryRepository.findByCategoryName(categoryName);

	}
 
	

	@Transactional(propagation = Propagation.REQUIRED)

	public AcademicCategory updateAcademicCategory(AcademicCategoryDto categoryDto) {

		AcademicCategory category = academicCategoryRepository.findByCategoryId(categoryDto.getCategoryId());

		try {

		category.setCategoryName(categoryDto.getCategoryName());

		category.setDescription(categoryDto.getDescription());


		} catch (Exception e) {

			logger.error(" Error while updating AcademicCategory {} and the Error is : {}", categoryDto.getCategoryName(),

					e.getMessage());

		}

		UserProfile userProfile = userProfileRepository.findByUserId(categoryDto.getUpdatedBy().getUserId());

		category.setUpdatedBy(userProfile);

		return academicCategoryRepository.save(category);

	}
 
	public void deleteAcademicCategoryById(long categoryId) {

		academicCategoryRepository.deleteById(categoryId);

	}
 
	





 
}

 