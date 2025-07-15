package com.ws.spring.restcontroller;

import java.util.HashMap;
import java.util.Map;

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

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.OccupationDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Occupation;
import com.ws.spring.service.OccupationServiceImpl;

import io.swagger.annotations.Api;


	@RestController
	@RequestMapping("/gotra")
	@Api(value = "Occupation Management System", tags = "Occupation Management System")
	public class OccupationRestController {
		
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
		
		@Autowired
		OccupationServiceImpl occupationServiceImpl;

		
		@PostMapping("/v1/createOccupation")
	    ResponseEntity<ClientResponseBean> createOccupation(@RequestBody OccupationDto occupationDto) {
			try {
	        logger.debug("createOccupation OccupationName : {}", occupationDto.getOccupationName());
	        
	        if (null != occupationServiceImpl.getOccupationNameExist(occupationDto.getOccupationName())) {
				return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),
						"Occupation Already Exist"));
			}
	        
	        Occupation occupationCreated = occupationServiceImpl.createOccupation(occupationDto);
	        logger.debug("createOccupation Id : {}, OccupationName: {}", occupationCreated.getOccupationId(),
	        		occupationCreated.getOccupationName());
	        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
	        		 " Gotra Successfully Created", ""));
	        
			} catch (Exception e) {
				logger.error("Exception occured : {}", e.getMessage(), e);
				
				return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
						e.getCause().getCause().getMessage(), ""));
			}
	    }

		
		@PutMapping("/v1/updateOccupation")
	    ResponseEntity<ClientResponseBean> updateOccupation(@RequestBody OccupationDto occupationDto) {
			try {
	        logger.debug("updateOccupation OccupationName : {}",occupationDto.getOccupationName());
	        
	        
	        Occupation occupationCreated = occupationServiceImpl.updateOccupation(occupationDto);
	        logger.debug("updateOccupation Id : {}, OccupationName: {}", occupationCreated.getOccupationId(),
	        		occupationCreated.getOccupationName());
	        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
	        		 " Occupation Successfully updated", ""));
			} catch (Exception e) {
				logger.error("Exception occured : {}", e.getMessage(), e);
				
				return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
						e.getCause().getCause().getMessage(), ""));
			}
	    }
		
		@GetMapping("/v1/getOccupationByOccupationId/{occupationId}")
		public ResponseEntity<?> getOccupationByOccupationId(long occupationId) {
			
			OccupationDto occupationDto = occupationServiceImpl.getOccupationByOccupationId(occupationId);
			
			if (null == occupationDto) {
				Map<String, String> noContentMessage = new HashMap<String, String>();
				noContentMessage.put("message", "Nothing found");
				return ResponseEntity.ok().body(noContentMessage);
			}
			
	        return ResponseEntity.ok().body(occupationDto);
		}
		
		@DeleteMapping("/v1/deleteOccupationById/{occupationId}")
	    ResponseEntity<ClientResponseBean> deleteOccupationById(@PathVariable long occupationId) {
			try {
				occupationServiceImpl.deleteOccupationById(occupationId);
			return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
					" Occupation successfully deleted", ""));
			} catch (Exception e) {
				logger.error("Exception occured : {}", e.getMessage(), e);
				
				return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
						e.getCause().getCause().getMessage(), ""));
			}
	    }
		
		
		@GetMapping("/v1/getAllOccupationByPagination/{pageNumber}/{pageSize}")
	  public  Page<OccupationDto> getAllOccupationByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<OccupationDto> page = occupationServiceImpl.getAllOccupationByPagination(pageNumber,pageSize);
	        
	        return page;
	    }
		
		
	    }
	

