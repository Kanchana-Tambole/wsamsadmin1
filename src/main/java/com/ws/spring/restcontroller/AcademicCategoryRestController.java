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
 
import com.ws.common.util.ClientResponseUtil;

import com.ws.spring.dto.AcademicCategoryDto;

import com.ws.spring.dto.AcademicCategoryDtoList;

import com.ws.spring.exception.ClientResponseBean;

import com.ws.spring.model.AcademicCategory;

import com.ws.spring.service.AcademicCategoryServiceImpl;
 
import io.swagger.annotations.Api;
 
@RestController

@RequestMapping("/academiccategory")

@Api(value = "AcademicCategory Management System", tags = "AcademicCategory Management System")

public class AcademicCategoryRestController {
 
	

Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired

	AcademicCategoryServiceImpl  academicCategoryServiceImpl;


	@PostMapping("/v1/createAcademicCategory")

    ResponseEntity<ClientResponseBean> createAcademicCategory(@RequestBody AcademicCategoryDto categoryDto) {

		try {

        logger.debug("createAcademicCategory CategoryName : {}", categoryDto.getCategoryName());

        if (null != academicCategoryServiceImpl.getCategoryNameExist(categoryDto.getCategoryName())) {

			return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),

					"Category Already Exist"));

		}

        AcademicCategory categoryCreated = academicCategoryServiceImpl.createAcademicCategory(categoryDto);

        logger.debug("createAcademicCategory Id : {}, CategoryName: {}", categoryCreated.getCategoryId(),

        		categoryCreated.getCategoryName());

        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",

        		 " Category Successfully Created", ""));

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage(), e);

			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

					e.getCause().getCause().getMessage(), ""));

		}

    }


	@PutMapping("/v1/updateAcademicCategory")

    ResponseEntity<ClientResponseBean> updateAcademicCategory(@RequestBody AcademicCategoryDto categoryDto) {

		try {

        logger.debug("updateAcademicCategory CategoryName : {}", categoryDto.getCategoryName());


        AcademicCategory categoryCreated = academicCategoryServiceImpl.updateAcademicCategory(categoryDto);

        logger.debug("updateAcademicCategory Id : {}, CategoryName: {}", categoryCreated.getCategoryId(),

        		categoryCreated.getCategoryName());

        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",

        		 " Category Successfully Updated", ""));

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage(), e);

			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

					e.getCause().getCause().getMessage(), ""));

		}

    }


	@DeleteMapping("/v1/deleteAcademicCategoryById/{categoryId}")

    ResponseEntity<ClientResponseBean> deleteAcademicCategoryById(@PathVariable long categoryId) {

		try {

			academicCategoryServiceImpl.deleteAcademicCategoryById(categoryId);

		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",

				" Category successfully Deleted", ""));

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage(), e);

			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

					e.getCause().getCause().getMessage(), ""));

		}

    }


	@GetMapping("/v1/getAcademicCategoryByCategoryId/{categoryId}")

	public ResponseEntity<?> getAcademicCategoryByCategoryId(long categoryId) {

		AcademicCategoryDto categoryDto = academicCategoryServiceImpl.getAcademicCategoryByCategoryId(categoryId);

		if (null == categoryDto) {

			Map<String, String> noContentMessage = new HashMap<String, String>();

			noContentMessage.put("message", "Nothing found");

			return ResponseEntity.ok().body(noContentMessage);

		}

        return ResponseEntity.ok().body(categoryDto);

	}



	@GetMapping("/v1/getAllAcademicCategory")

    ResponseEntity<List<AcademicCategoryDtoList>> getAllAcademicCategory() {

        List<AcademicCategoryDtoList> categoryDtos = academicCategoryServiceImpl.getAllAcademicCategory();

        return ResponseEntity.ok().body(categoryDtos);

    }


	@GetMapping("/v1/getAllAcademicCategoryByPagination/{pageNumber}/{pageSize}")

	  public  Page<AcademicCategoryDto> getAllAcademicCategoryByPagination(@RequestParam int pageNumber, 

			  @RequestParam int pageSize) {

	        Page<AcademicCategoryDto> page = academicCategoryServiceImpl.getAllAcademicCategoryByPagination(pageNumber,pageSize);

	        return page;

	    }


}

 