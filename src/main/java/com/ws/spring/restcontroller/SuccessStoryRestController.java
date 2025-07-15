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

import com.ws.spring.dto.SuccessStoryDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.SuccessStory;
import com.ws.spring.service.SuccessStoryServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/success")
@Api(value = "SuccessStory Management System", tags = "SuccessStory Management System")
public class SuccessStoryRestController {
	
             Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	SuccessStoryServiceImpl successStoryServiceImpl;
	
	
	
	@PostMapping("/v1/createSuccessStory")
    ResponseEntity<ClientResponseBean> createSuccessStory(@RequestBody SuccessStoryDto successStoryDto) {
		try {
        logger.debug("createSuccessStory SuccessstoryName : {}", successStoryDto.getSuccessstoryName());
        
        
        SuccessStory successStoryCreated = successStoryServiceImpl.createSuccessStory(successStoryDto);
        
        logger.debug("createSuccessStory Id : {}, SuccessstoryName: {}", successStoryCreated.getSuccessstoryId(),
        		successStoryCreated.getSuccessstoryName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " SuccessStory Successfully Created", ""));
        
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	
	@PutMapping("/v1/updateSuccessStory")
    ResponseEntity<ClientResponseBean> updateSuccessStory(@RequestBody SuccessStoryDto successStoryDto) {
		try {
        logger.debug("updateSuccessStory SuccessstoryName : {}", successStoryDto.getSuccessstoryName());
        
        
        SuccessStory successStoryCreated = successStoryServiceImpl.updateSuccessStory(successStoryDto);
        
        logger.debug("updateSuccessStory Id : {}, SuccessstoryName: {}", successStoryCreated.getSuccessstoryId(),
        		successStoryCreated.getSuccessstoryName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " SuccessStory Successfully Updated", ""));
        
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	
	@GetMapping("/v1/getSuccessStoryById/{successstoryId}")
	public ResponseEntity<?> getSuccessStoryById(long successstoryId) {
		
		SuccessStoryDto successStoryDto = successStoryServiceImpl.getSuccessStoryById(successstoryId);
	
		
        return ResponseEntity.ok().body(successStoryDto);
	}
	
	
  @DeleteMapping("/v1/deleteSuccessStoryById/{successstoryId}")
	
    ResponseEntity<ClientResponseBean> deleteSuccessStoryById(@PathVariable long successstoryId) {
		try {
			successStoryServiceImpl.deleteSuccessStoryById(successstoryId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				" SuccessStory successfully deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	
  @GetMapping("/v1/getAllSuccessStoryByPagination/{pageNumber}/{pageSize}")
  public  Page<SuccessStoryDto> getAllSuccessStoryByPagination(@RequestParam int pageNumber, 
		  @RequestParam int pageSize) {
        Page<SuccessStoryDto> page = successStoryServiceImpl.getAllSuccessStoryByPagination(pageNumber,pageSize);
        
        return page;
    }
	
	
	
	
	
}
