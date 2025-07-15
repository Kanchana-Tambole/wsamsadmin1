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
import com.ws.spring.dto.UpskillCategoryDto;
import com.ws.spring.dto.UpskillCategoryDtoList;
import com.ws.spring.model.UpskillCategory;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.UpskillCategoryRepository;
import com.ws.spring.repository.UserProfileRepository;
 
@Service
public class UpskillCategoryServiceImpl {
 
	
Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	UpskillCategoryRepository  upskillCategoryRepository;

	@Autowired
	UserProfileRepository userProfileRepository;

	public UpskillCategoryDto getUpskillCategoryByCategoryId(long categoryId) {
		UpskillCategory category = upskillCategoryRepository.findByCategoryId(categoryId);
        return CommonBuilder.buildUpskillCategoryDto(category);
	}
 
	public List<UpskillCategoryDtoList> getAllUpskillCategory() {
		List<UpskillCategory> categoryList = upskillCategoryRepository.findAll(Sort.by(Sort.Direction.DESC, "categoryId"));
        return CommonBuilder.buildUpskillCategoryList(categoryList);
	}
 
	public Page<UpskillCategoryDto> getAllUpskillCategoryByPagination(int pageNumber, int pageSize) {
Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("categoryId").descending());
		Page<UpskillCategory> advertisementPage = upskillCategoryRepository.findAll(pageable);
        int totalElements = (int) advertisementPage.getTotalElements();
        return new PageImpl<UpskillCategoryDto>(advertisementPage
                .stream()
                .map(upskillCategory -> new UpskillCategoryDto(
                		upskillCategory.getCategoryId(),
                		upskillCategory.getCategoryName(),
                		 upskillCategory.getDescription(), upskillCategory.getInsertedDate(), upskillCategory.getUpdatedDate(), upskillCategory.getCreatedBy(), upskillCategory.getUpdatedBy()))
                .collect(Collectors.toList()), pageable, totalElements);
	}
 
	
	@Transactional(propagation = Propagation.REQUIRED)
	public UpskillCategory createUpskillCategory(UpskillCategoryDto categoryDto) {
		UpskillCategory category = new UpskillCategory();
		 BeanUtils.copyProperties(categoryDto, category,"createdBy","updatedBy");
		 UserProfile userProfile = userProfileRepository.findByUserId(categoryDto.getCreatedBy().getUserId());
		 category.setCreatedBy(userProfile);
		 category.setUpdatedBy(userProfile);
		return upskillCategoryRepository.save(category);
	}
 
	public UpskillCategory getCategoryNameExist(String categoryName) {
		return upskillCategoryRepository.findByCategoryName(categoryName);
	}
 
	
	@Transactional(propagation = Propagation.REQUIRED)
	public UpskillCategory updateUpskillCategory(UpskillCategoryDto categoryDto) {
		UpskillCategory category = upskillCategoryRepository.findByCategoryId(categoryDto.getCategoryId());
		try {
		category.setCategoryName(categoryDto.getCategoryName());
		category.setDescription(categoryDto.getDescription());

		} catch (Exception e) {
			logger.error(" Error while updating UpskillCategory {} and the Error is : {}", categoryDto.getCategoryName(),
					e.getMessage());
		}
		UserProfile userProfile = userProfileRepository.findByUserId(categoryDto.getUpdatedBy().getUserId());
		category.setUpdatedBy(userProfile);
		return upskillCategoryRepository.save(category);
	}
 
	public void deleteUpskillCategoryById(long categoryId) {
		upskillCategoryRepository.deleteById(categoryId);
	}
 
	





}