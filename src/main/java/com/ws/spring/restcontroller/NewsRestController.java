package com.ws.spring.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ws.spring.dto.NewsDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.News;
import com.ws.spring.service.NewsServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/news")
@Api(value = "News Management System", tags = "News Management System")
public class NewsRestController {
	

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    NewsServiceImpl  newsServiceImpl;

    
    @PostMapping("/v1/createNews")
    ResponseEntity<ClientResponseBean> createNews(@RequestBody NewsDto newsDto) {
    	
    	
		try {
        logger.debug("createNews NewsName : {}", newsDto.getNewsName());
        
        
        News newsCreated = newsServiceImpl.createNews(newsDto);
        
        logger.debug("createNews Id : {}, NewsName: {}", newsCreated.getNewsId(),
        		newsCreated.getNewsName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " News Successfully Created", ""));
        
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
    
    
    @PutMapping("/v1/updateNews")
    ResponseEntity<ClientResponseBean> updateNews(@RequestBody NewsDto newsDto) {
    	
    	
		try {
        logger.debug("updateNews NewsName : {}", newsDto.getNewsName());
        
        
        News newsCreated = newsServiceImpl.updateNews(newsDto);
        
        logger.debug("updateNews Id : {}, NewsName: {}", newsCreated.getNewsId(),
        		newsCreated.getNewsName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " News Successfully Updated", ""));
        
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
    
    
    @GetMapping("/v1/getNewsById/{newsId}")
	public ResponseEntity<?> getNewsById(long newsId) {
		
    	NewsDto newsDto = newsServiceImpl.getNewsById(newsId);
	
		
        return ResponseEntity.ok().body(newsDto);
	}
    
@DeleteMapping("/v1/deleteNewsById/{newsId}")
	
    ResponseEntity<ClientResponseBean> deleteNewsById(@PathVariable long newsId) {
		try {
			newsServiceImpl.deleteNewsById(newsId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				" News Successfully Deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }


@GetMapping("/v1/getAllNewsByPagination/{pageNumber}/{pageSize}")
public  Page<NewsDto> getAllNewsByPagination(@RequestParam int pageNumber, 
		  @RequestParam int pageSize) {
      Page<NewsDto> page = newsServiceImpl.getAllNewsByPagination(pageNumber,pageSize);
      
      return page;
  }

}
