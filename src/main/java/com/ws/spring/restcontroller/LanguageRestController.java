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

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.LanguageDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Language;
import com.ws.spring.service.LanguageServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/language")
@Api(value = "Language Management System", tags = "Language Management System")
public class LanguageRestController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	LanguageServiceImpl languageServiceImpl;
	
	
	
	@PostMapping("/v1/createLanguage")
    ResponseEntity<ClientResponseBean> createLanguage(@RequestBody LanguageDto languageDto) {
		try {
        logger.debug("createLanguage LanguageName : {}", languageDto.getLanguageName());
        
        if (null != languageServiceImpl.getLanguageNameExist(languageDto.getLanguageName())) {
			return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),
					"Language Already Exist"));
		}
        
        Language langCreated = languageServiceImpl.createLanguage(languageDto);
        logger.debug("createLanguage Id : {}, LanguageName: {}", langCreated.getLanguageId(),
        		langCreated.getLanguageName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 "Language Successfully Created", ""));
        
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	
	@PutMapping("/v1/updateLanguage")
    ResponseEntity<ClientResponseBean> updateLanguage(@RequestBody LanguageDto languageDto) {
		try {
        logger.debug("updateLanguage LanguageName : {}", languageDto.getLanguageName());
        
        Language langCreated = languageServiceImpl.updateLanguage(languageDto);
        logger.debug("updateLanguage Id : {}, LanguageName: {}", langCreated.getLanguageId(),
        		langCreated.getLanguageName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 "Language Successfully Updated", ""));
        
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	
	@GetMapping("/v1/getLanguageByLanguageId/{languageId}")
	public ResponseEntity<?> getLanguageByLanguageId(long languageId) {
		LanguageDto languageDto = languageServiceImpl.getLanguageByLanguageId(languageId);
		
        return ResponseEntity.ok().body(languageDto);
	}
	
	
	@DeleteMapping("/v1/deleteLanguageById/{languageId}")
    ResponseEntity<ClientResponseBean> deleteLanguageById(@PathVariable long languageId) {
		try {
			languageServiceImpl.deleteLanguageById(languageId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				" Language successfully deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	@GetMapping("/v1/getAllLanguageByPagination/{pageNumber}/{pageSize}")
	  public  Page<LanguageDto> getAllLanguageByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<LanguageDto> page = languageServiceImpl.getAllLanguageByPagination(pageNumber,pageSize);
	        
	        return page;
	    }
	
	@GetMapping("/v1/getAllLanguage")
    ResponseEntity<List<LanguageDto>> getAllLanguage() {
        List<LanguageDto> languageDto = languageServiceImpl.getAllLanguage();
        return ResponseEntity.ok().body(languageDto);
    }

}
