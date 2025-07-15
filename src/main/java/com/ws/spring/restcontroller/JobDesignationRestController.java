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

import com.ws.spring.dto.JobDesignationDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.JobDesignation;
import com.ws.spring.service.JobDesignationServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/designation")
@Api(value = "JobDesignation Management System", tags = "JobDesignation Management System")
public class JobDesignationRestController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	JobDesignationServiceImpl jobDesignationService;
	
	@PostMapping("/v1/createJobDesignation")
	ResponseEntity<ClientResponseBean> createJobDesignation(@RequestBody JobDesignationDto jobDesignationDto) {
		try {
		logger.debug("createJobDesignation DesignationName : {}" , jobDesignationDto.getDesignationName());
		JobDesignation jobCreated = jobDesignationService.createJobDesignation(jobDesignationDto);
		logger.debug("createJobDesignation Id : {}, DesignationName : {}" , jobCreated.getDesignationId(),jobCreated.getDesignationName());
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
				jobCreated.getDesignationName() + " job designation successfully created", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
	}
	
	@PutMapping("/v1/updateJobDesignation")
	ResponseEntity<ClientResponseBean> updateJobDesignation(@RequestBody JobDesignationDto jobDesignationDto) {
		try {
		logger.debug("updateJobDesignation DesignationName : {}" , jobDesignationDto.getDesignationName());
		JobDesignation jobCreated = jobDesignationService.updateJobDesignation(jobDesignationDto);
		logger.debug("updateJobDesignation Id : {}, DesignationName : {}" , jobCreated.getDesignationId(),jobCreated.getDesignationName());
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
				jobCreated.getDesignationName() + " job designation successfully updated", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
	}
	
	@GetMapping("/v1/queryJobDesignationByDesignationId/{designationId}")
	ResponseEntity<JobDesignationDto> queryJobDesignationByDesignationId(@PathVariable long designationId) {
		JobDesignationDto jobDesignationDto = jobDesignationService.queryJobDesignationByDesignationId(designationId);
		return ResponseEntity.ok().body(jobDesignationDto);
	}
	
	@GetMapping("/v1/queryAllJobDesignations")
    ResponseEntity<List<JobDesignationDto>> queryAllJobDesignations() {
        List<JobDesignationDto> jobDesignations = jobDesignationService.queryAllJobDesignations();
        return ResponseEntity.ok().body(jobDesignations);
    }
	
	@DeleteMapping("/v1/deleteJobDesignationById/{designationId}")
    ResponseEntity<ClientResponseBean> deleteJobDesignationById(@PathVariable long designationId) {
		try {
			jobDesignationService.deleteJobDesignationById(designationId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				designationId + " job designation successfully deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	
	@GetMapping("/v1/getAllJobDesignationByPagination/{pageNumber}/{pageSize}")
	  public  Page<JobDesignationDto> getAllJobDesignationByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<JobDesignationDto> page = jobDesignationService.getAllJobDesignationByPagination(pageNumber,pageSize);
	        
	        return page;
	    }

}
