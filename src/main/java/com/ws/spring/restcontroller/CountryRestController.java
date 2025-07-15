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
import com.ws.spring.dto.CountryDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Country;
import com.ws.spring.service.CountryServiceImpl;

import io.swagger.annotations.Api;



	@RestController
	@RequestMapping("/country")
	@Api(value = "Country Management System", tags = "Country Management System")
	public class CountryRestController {
		
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
		
		@Autowired
		CountryServiceImpl countryServiceImpl;

		
		@PostMapping("/v1/createCountry")
	    ResponseEntity<ClientResponseBean> createCountry(@RequestBody CountryDto countryDto) {
			try {
	        logger.debug("createCountry CountryName : {}", countryDto.getCountryName());
	        
	        if (null != countryServiceImpl.getCountryNameExist(countryDto.getCountryName())) {
				return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),
						"Country Already Exist"));
			}
	        
	        Country countryCreated = countryServiceImpl.createCountry(countryDto);
	        logger.debug("createCountry Id : {}, CountryName: {}", countryCreated.getCountryId(),
	        		countryCreated.getCountryName());
	        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
	        		 " Country Successfully Created", ""));
	        
			} catch (Exception e) {
				logger.error("Exception occured : {}", e.getMessage(), e);
				
				return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
						e.getCause().getCause().getMessage(), ""));
			}
	    }

		
		@PutMapping("/v1/updateCountry")
	    ResponseEntity<ClientResponseBean> updateCountry(@RequestBody CountryDto countryDto) {
			try {
	        logger.debug("updateCountry CountryName : {}", countryDto.getCountryName());
	        
	        
	        Country countryCreated = countryServiceImpl.updateCountry(countryDto);
	        logger.debug("updateCountry Id : {}, CountryName: {}", countryCreated.getCountryId(),
	        		countryCreated.getCountryName());
	        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
	        		 " Country Successfully updated", ""));
			} catch (Exception e) {
				logger.error("Exception occured : {}", e.getMessage(), e);
				
				return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
						e.getCause().getCause().getMessage(), ""));
			}
	    }
		
		@GetMapping("/v1/getCountryByCountryId/{countryId}")
		public ResponseEntity<?> getCountryByCountryId(long countryId) {
			
			CountryDto countryDto = countryServiceImpl.getCountryByCountryId(countryId);
			
			if (null == countryDto) {
				Map<String, String> noContentMessage = new HashMap<String, String>();
				noContentMessage.put("message", "Nothing found");
				return ResponseEntity.ok().body(noContentMessage);
			}
			
	        return ResponseEntity.ok().body(countryDto);
		}
		
		@DeleteMapping("/v1/deleteCountryById/{countryId}")
	    ResponseEntity<ClientResponseBean> deleteCountryById(@PathVariable long countryId) {
			try {
				
				countryServiceImpl.deleteCountryById(countryId);
				
			return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
					" Country successfully deleted", ""));
			} catch (Exception e) {
				logger.error("Exception occured : {}", e.getMessage(), e);
				
				return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
						e.getCause().getCause().getMessage(), ""));
			}
	    }
		
		
		@GetMapping("/v1/getAllCountryByPagination/{pageNumber}/{pageSize}")
	  public  Page<CountryDto> getAllCountryByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<CountryDto> page = countryServiceImpl.getAllCountryByPagination(pageNumber,pageSize);
	        
	        return page;
	    }
		
		
	    }
	

