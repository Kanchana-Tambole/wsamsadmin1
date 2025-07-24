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

import com.ws.spring.dto.FeesCategoryDto;
import com.ws.spring.dto.FeesCategoryDtoList;
import com.ws.spring.dto.CommonBuilder;

import com.ws.spring.model.FeesCategory;
import com.ws.spring.model.UserProfile;

import com.ws.spring.repository.FeesCategoryRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class FeesCategoryServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private FeesCategoryRepository feesCategoryRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public FeesCategoryDto getFeesCategoryById(long id) {
        FeesCategory category = feesCategoryRepository.findById(id);
        return CommonBuilder.buildFeesCategoryDto(category);
    }

    public List<FeesCategoryDtoList> getAllFeesCategories() {
        List<FeesCategory> categoryList = feesCategoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return CommonBuilder.buildFeesCategoryDtoListData(categoryList);  // ✅ Correct method name
    }


    public Page<FeesCategoryDto> getAllFeesCategoriesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<FeesCategory> categoryPage = feesCategoryRepository.findAll(pageable);
        int totalElements = (int) categoryPage.getTotalElements();

        return new PageImpl<FeesCategoryDto>(
                categoryPage.stream()
                        .map(category -> new FeesCategoryDto(
                                category.getId(),
                                category.getName(),
                                category.getDescription(),
                                category.isStatus(),
                                category.getCreatedAt(),
                                category.getUpdatedAt(),
                                category.getCreatedBy(),
                                category.getUpdatedBy()))
                        .collect(Collectors.toList()),
                pageable,
                totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public FeesCategory createFeesCategory(FeesCategoryDto categoryDto) {
        FeesCategory category = new FeesCategory();
        BeanUtils.copyProperties(categoryDto, category, "createdBy", "updatedBy");

        UserProfile userProfile = userProfileRepository.findByUserId(categoryDto.getCreatedBy().getUserId());
        category.setCreatedBy(userProfile);
        category.setUpdatedBy(userProfile);

        return feesCategoryRepository.save(category);
    }

    public FeesCategory getCategoryByName(String name) {
        return feesCategoryRepository.findByName(name);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public FeesCategory updateFeesCategory(FeesCategoryDto categoryDto) {
        FeesCategory category = feesCategoryRepository.findById(categoryDto.getId());

        try {
            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            category.setStatus(categoryDto.isStatus());
        } catch (Exception e) {
            logger.error("Error while updating FeesCategory {}: {}", categoryDto.getName(), e.getMessage());
        }

        UserProfile userProfile = userProfileRepository.findByUserId(categoryDto.getUpdatedBy().getUserId());
        category.setUpdatedBy(userProfile);

        return feesCategoryRepository.save(category);
    }

    public void deleteFeesCategoryById(long id) {
        feesCategoryRepository.deleteById(id);
    }
}
