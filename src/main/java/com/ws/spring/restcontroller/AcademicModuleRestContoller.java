package com.ws.spring.restcontroller;
 
import java.util.HashMap;

import java.util.List;

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
 
import com.ws.spring.dto.AcademicModuleDto;

import com.ws.spring.dto.AcademicModuleDtoList;

import com.ws.spring.exception.ClientResponseBean;

import com.ws.spring.model.AcademicModule;

import com.ws.spring.service.AcademicModuleServiceImpl;
 
import io.swagger.annotations.Api;
 
@RestController

@RequestMapping("/academicmodule")

@Api(value = "AcademicModule Management System", tags = "AcademicModule Management System")

public class AcademicModuleRestContoller {
 
	

Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired

	AcademicModuleServiceImpl   academicModuleServiceImpl;


	@PostMapping("/v1/createAcademicModule")

    ResponseEntity<ClientResponseBean> createAcademicModule(@RequestBody AcademicModuleDto moduleDto) {

		try {

        logger.debug("createAcademicModule ModuleName : {}", moduleDto.getModuleName());

        AcademicModule moduleCreated = academicModuleServiceImpl.createAcademicModule(moduleDto);

        logger.debug("createAcademicModule Id : {}, ModuleName: {}", moduleCreated.getModuleId(),

        		moduleCreated.getModuleName());

        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",

        		 " Module Successfully Created", ""));

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage(), e);

			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

					e.getCause().getCause().getMessage(), ""));

		}

    }


	@PutMapping("/v1/updateAcademicModule")

    ResponseEntity<ClientResponseBean> updateAcademicModule(@RequestBody AcademicModuleDto moduleDto) {

		try {

        logger.debug("updateAcademicModule ModuleName : {}", moduleDto.getModuleName());

        AcademicModule moduleCreated = academicModuleServiceImpl.updateAcademicModule(moduleDto);

        logger.debug("updateAcademicModule Id : {}, ModuleName: {}", moduleCreated.getModuleId(),

        		moduleCreated.getModuleName());

        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",

        		 " Module Successfully Created", ""));

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage(), e);

			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

					e.getCause().getCause().getMessage(), ""));

		}

    }

	@DeleteMapping("/v1/deleteAcademicModuleById/{moduleId}")

    ResponseEntity<ClientResponseBean> deleteAcademicModuleById(@PathVariable long moduleId) {

		try {

			academicModuleServiceImpl.deleteAcademicModuleById(moduleId);

		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",

				" Module Successfully Deleted", ""));

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage(), e);

			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

					"",e.getCause().getCause().getMessage()));

		}

    }

	@GetMapping("/v1/getAcademicModuleByModuleId/{moduleId}")

	public ResponseEntity<?> getUpskillModuleByModuleId(long moduleId) {

		AcademicModuleDto moduleDto = academicModuleServiceImpl.getAcademicModuleByModuleId(moduleId);

		if (null == moduleDto) {

			Map<String, String> noContentMessage = new HashMap<String, String>();

			noContentMessage.put("message", "Nothing found");

			return ResponseEntity.ok().body(noContentMessage);

		}

        return ResponseEntity.ok().body(moduleDto);

	}


	@GetMapping("/v1/getAcademicModulesByCourseId/{courseId}")

    ResponseEntity<List<AcademicModuleDto>> getAcademicModulesByCourseId(long courseId) {

        List<AcademicModuleDto> moduleDtos = academicModuleServiceImpl.getAcademicModulesByCourseId(courseId);

        return ResponseEntity.ok().body(moduleDtos);

    }

	@GetMapping("/v1/getAllAcademicModules")

    ResponseEntity<List<AcademicModuleDtoList>> getAllAcademicModules() {

        List<AcademicModuleDtoList> moduleDtos = academicModuleServiceImpl.getAllAcademicModules();

        return ResponseEntity.ok().body(moduleDtos);

    }


	@GetMapping("/v1/getAllAcademicModulesByPagination/{pageNumber}/{pageSize}")

	  public  Page<AcademicModuleDto> getAllAcademicModulesByPagination(@RequestParam int pageNumber, 

			  @RequestParam int pageSize) {

	        Page<AcademicModuleDto> page = academicModuleServiceImpl.getAllAcademicModulesByPagination(pageNumber,pageSize);

	        return page;

	    }

}

 