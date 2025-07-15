package com.ws.spring.restcontroller;

import java.util.List;

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

import com.ws.spring.dto.QualificationDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Qualification;
import com.ws.spring.service.QualificationServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/qualification")
@Api(value = "Qualification Management System", tags = "Qualification Management System")
public class QualificationRestController {
	
Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	
	@Autowired
	QualificationServiceImpl qualificationServiceImpl;

	
	@PostMapping("/v1/createQualification")
	ResponseEntity<ClientResponseBean> createQualification(@RequestBody QualificationDto qualificationDto) {
		try {
		logger.debug("createQualification QualificationName : {}" , qualificationDto.getQualificationName());
		Qualification qualificationCreated = qualificationServiceImpl.createQualification(qualificationDto);
		logger.debug("createQualification Id : {}, QualificationName : {}" , qualificationCreated.getQualificationId(),qualificationCreated.getQualificationName());
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
				qualificationCreated.getQualificationName()+ " qualification successfully created", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
	}
	
	@PutMapping("/v1/updateQualification")
	ResponseEntity<ClientResponseBean> updateQualification(@RequestBody QualificationDto qualificationDto) {
		try {
		logger.debug("updateQualification QualificationName : {}" , qualificationDto.getQualificationName());
		Qualification qualificationCreated = qualificationServiceImpl.updateQualification(qualificationDto);
		logger.debug("updateQualification Id : {}, QualificationName : {}" , qualificationCreated.getQualificationId(),qualificationCreated.getQualificationName());
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
				qualificationCreated.getQualificationName()+ " qualification successfully updated", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
	}
	
	@GetMapping("/v1/queryQualificationByQualificationId/{qualificationId}")
	ResponseEntity<QualificationDto> queryQualificationByQualificationId(@PathVariable long qualificationId) {
		QualificationDto qualificationDto = qualificationServiceImpl.queryQualificationByQualificationId(qualificationId);
		return ResponseEntity.ok().body(qualificationDto);
	}
	
	@GetMapping("/v1/queryAllQualifications")
    ResponseEntity<List<QualificationDto>> queryAllQualifications() {
        List<QualificationDto> qualificationDto = qualificationServiceImpl.queryAllQualifications();
        return ResponseEntity.ok().body(qualificationDto);
    }
	
	
	@DeleteMapping("/v1/deleteQualificationById/{qualificationId}")
    ResponseEntity<ClientResponseBean> deleteQualificationById(@PathVariable long qualificationId) {
		try {
			qualificationServiceImpl.deleteQualificationById(qualificationId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				qualificationId + " qualification successfully deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	
	@GetMapping("/v1/getAllQualificationByPagination/{pageNumber}/{pageSize}")
	  public  Page<QualificationDto> getAllQualificationByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<QualificationDto> page = qualificationServiceImpl.getAllQualificationByPagination(pageNumber,pageSize);
	        
	        return page;
	    }
}
