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
import com.ws.spring.dto.NewsDto;
import com.ws.spring.model.News;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.NewsRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class NewsServiceImpl {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    NewsRepository newsRepository;
    
    
     @Autowired
	 UserProfileRepository userProfileRepository;
     
     @Autowired
 	FileStorageService fileStoreService;
    
    
    @Transactional(propagation = Propagation.REQUIRED)
	public News createNews(NewsDto newsDto) {
	
		News news =  new News();
		BeanUtils.copyProperties(newsDto, news,"createdBy","updatedBy");
		
		 UserProfile userProfile = userProfileRepository.findByUserId(newsDto.getCreatedBy().getUserId());
		 news.setCreatedBy(userProfile);
		 news.setUpdatedBy(userProfile);
		 
		 newsRepository.save(news);
		 
		 String fileName = newsDto.getPhotoName();
			try {

				if (!StringUtil.checkNullOrEmpty(fileName) && (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||  fileName.endsWith(".png")|| fileName.endsWith(".gif") || fileName.endsWith(".mpeg") || fileName.endsWith(".mp4"))) {
					String fileDirectory = String.valueOf(news.getNewsId());
					String newFilePath = fileStoreService.moveFile("kwinlabnews", fileName, fileDirectory);
					logger.info("News file name : {} and file path : {}", news.getPhotoName(), newFilePath);
					if (!StringUtils.isEmpty(newFilePath)) {
						news.setPhotoPath(newFilePath);
					}
				}
				return newsRepository.save(news);

			} catch (EntityExistsException e) {
				logger.error("Exception occure : {}", e.getMessage(), e);
			} catch (Exception e) {
				logger.error("Exception occure : {}", e.getMessage(), e);
			}
			
		
		return news;
	}

    @Transactional(propagation = Propagation.REQUIRED)
	public News updateNews(NewsDto newsDto) {
    	
    	News news = newsRepository.findByNewsId(newsDto.getNewsId());
    	
    		
    		news.setNewsName(newsDto.getNewsName());
    		news.setPhotoName(newsDto.getPhotoName());
    		news.setPhotoPath(newsDto.getPhotoPath());
    		news.setDescription(newsDto.getDescription());
    		
    		UserProfile userProfile = userProfileRepository.findByUserId(newsDto.getUpdatedBy().getUserId());
    		news.setUpdatedBy(userProfile);
    		
    		newsRepository.save(news);
    		
    		 String fileName = newsDto.getPhotoName();
 			try {

 				if (!StringUtil.checkNullOrEmpty(fileName) && (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||  fileName.endsWith(".png")|| fileName.endsWith(".gif") || fileName.endsWith(".mpeg") || fileName.endsWith(".mp4"))) {
 					String fileDirectory = String.valueOf(news.getNewsId());
 					String newFilePath = fileStoreService.moveFile("kwinlabnews", fileName, fileDirectory);
 					logger.info("News file name : {} and file path : {}", news.getPhotoName(), newFilePath);
 					if (!StringUtils.isEmpty(newFilePath)) {
 						news.setPhotoPath(newFilePath);
 					}
 				}
 				return newsRepository.save(news);

 			} catch (EntityExistsException e) {
 				logger.error("Exception occure : {}", e.getMessage(), e);
 			} catch (Exception e) {
 				logger.error("Exception occure : {}", e.getMessage(), e);
 			}
		
		
    			
		return news;
	}

	public NewsDto getNewsById(long newsId) {
		News news = newsRepository.findByNewsId(newsId);
		return CommonBuilder.buildNewsDto(news);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteNewsById(long newsId) {
	    newsRepository.deleteById(newsId);
	}


	public Page<NewsDto> getAllNewsByPagination(int pageNumber, int pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("newsId").descending());
		
		Page<News> newsPage = newsRepository.findAllByIsDeleteIsFalse(pageable);
		
        int totalElements = (int) newsPage.getTotalElements();
        return new PageImpl<NewsDto>(newsPage
                .stream()
                .map(news -> new NewsDto(
                		news.getNewsId(),
                		news.getNewsName(), news.getPhotoName(),news.getPhotoPath(),news.getDescription(), news.getInsertedDate(), news.getUpdatedDate(),  news.getCreatedBy(), news.getUpdatedBy()))
                     
                .collect(Collectors.toList()), pageable, totalElements);
        
	}

	
	
	
	
	
}
