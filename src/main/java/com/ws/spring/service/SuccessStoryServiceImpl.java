package com.ws.spring.service;

import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

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
import org.springframework.util.StringUtils;

import com.ws.common.util.StringUtil;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.SuccessStoryDto;
import com.ws.spring.model.SuccessStory;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.SuccessStoryRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class SuccessStoryServiceImpl {
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
    @Autowired
    SuccessStoryRepository successStoryRepository;


    @Autowired
	 UserProfileRepository userProfileRepository;
	
    @Autowired
	FileStorageService fileStoreService;
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public SuccessStory createSuccessStory(SuccessStoryDto successStoryDto) {
		
		 SuccessStory successStory = new SuccessStory();
		 BeanUtils.copyProperties(successStoryDto, successStory,"createdBy","updatedBy");
		
		 UserProfile userProfile = userProfileRepository.findByUserId(successStoryDto.getCreatedBy().getUserId());
		 successStory.setCreatedBy(userProfile);
		 successStory.setUpdatedBy(userProfile);
		 
		 successStoryRepository.save(successStory);
		 
		 String fileName = successStoryDto.getPhotoName();
			try {

				if (!StringUtil.checkNullOrEmpty(fileName) && (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||  fileName.endsWith(".png")|| fileName.endsWith(".gif") || fileName.endsWith(".mpeg") || fileName.endsWith(".mp4"))) {
					String fileDirectory = String.valueOf(successStory.getSuccessstoryId());
					String newFilePath = fileStoreService.moveFile("kwinlabsuccessStory", fileName, fileDirectory);
					logger.info("Advertisement file name : {} and file path : {}", successStory.getPhotoName(), newFilePath);
					if (!StringUtils.isEmpty(newFilePath)) {
						successStory.setPhotoPath(newFilePath);
					}
				}
				return successStoryRepository.save(successStory);

			} catch (EntityExistsException e) {
				logger.error("Exception occure : {}", e.getMessage(), e);
			} catch (Exception e) {
				logger.error("Exception occure : {}", e.getMessage(), e);
			}
		 
		return successStory;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SuccessStory updateSuccessStory(SuccessStoryDto successStoryDto) {
		
		SuccessStory successStory = successStoryRepository.findBySuccessstoryId(successStoryDto.getSuccessstoryId());
			successStory.setSuccessstoryName(successStoryDto.getSuccessstoryName());
			successStory.setPhotoName(successStoryDto.getPhotoName());
			successStory.setDescription(successStoryDto.getDescription());
			
			UserProfile userProfile = userProfileRepository.findByUserId(successStoryDto.getUpdatedBy().getUserId());
			successStory.setUpdatedBy(userProfile);

			successStoryRepository.save(successStory);
			
			 String fileName = successStoryDto.getPhotoName();
				try {

					if (!StringUtil.checkNullOrEmpty(fileName) && (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||  fileName.endsWith(".png")|| fileName.endsWith(".gif") || fileName.endsWith(".mpeg") || fileName.endsWith(".mp4"))) {
						String fileDirectory = String.valueOf(successStory.getSuccessstoryId());
						String newFilePath = fileStoreService.moveFile("kwinlabsuccessStory", fileName, fileDirectory);
						logger.info("successStory file name : {} and file path : {}", successStory.getPhotoName(), newFilePath);
						if (!StringUtils.isEmpty(newFilePath)) {
							successStory.setPhotoPath(newFilePath);
						}
					}
					return successStoryRepository.save(successStory);

				} catch (EntityExistsException e) {
					logger.error("Exception occure : {}", e.getMessage(), e);
				} catch (Exception e) {
					logger.error("Exception occure : {}", e.getMessage(), e);
				}
		
		return successStory;
	}

	public SuccessStoryDto getSuccessStoryById(long successstoryId) {
		SuccessStory successStory = successStoryRepository.findBySuccessstoryId(successstoryId);
		return CommonBuilder.buildSuccessStoryDto(successStory);
	}

	@Transactional
	public void deleteSuccessStoryById(long successstoryId) {
	    successStoryRepository.deleteById(successstoryId); // this deletes the row from DB
	}


	public Page<SuccessStoryDto> getAllSuccessStoryByPagination(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("successstoryId").descending());
			
			Page<SuccessStory> successStoryPage = successStoryRepository.findAllByIsDeleteIsFalse(pageable);
			
	        int totalElements = (int) successStoryPage.getTotalElements();
	        return new PageImpl<SuccessStoryDto>(successStoryPage
	                .stream()
	                .map(successStory -> new SuccessStoryDto(
	                		successStory.getSuccessstoryId(),
	                		successStory.getSuccessstoryName(), successStory.getPhotoName(),successStory.getPhotoPath(),successStory.getDescription(), successStory.getInsertedDate(), successStory.getUpdatedDate(),  successStory.getCreatedBy(), successStory.getUpdatedBy()))
	                     
	                .collect(Collectors.toList()), pageable, totalElements);
	}

}
