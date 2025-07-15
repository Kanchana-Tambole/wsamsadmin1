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

import com.ws.spring.dto.AcademicCourseDto;

import com.ws.spring.dto.AcademicCourseDtoList;

import com.ws.spring.exception.ClientResponseBean;

import com.ws.spring.model.AcademicCourse;

import com.ws.spring.service.AcademicCourseServiceImpl;
 
import io.swagger.annotations.Api;
 
@RestController

@RequestMapping("/academiccourse")

@Api(value = "AcademicCourse Management System", tags = "AcademicCourse Management System")

public class AcademicCourseRestController {
 
	

Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired

	AcademicCourseServiceImpl  academicCourseServiceImpl;


	@PostMapping("/v1/createAcademicCourse")

    ResponseEntity<ClientResponseBean> createAcademicCourse(@RequestBody AcademicCourseDto courseDto) {

		try {

        logger.debug("createAcademicCourse CourseName : {}", courseDto.getCourseName());

        if (null != academicCourseServiceImpl.getCourseNameExist(courseDto.getCourseName())) {

			return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),

					"Course Already Exist"));

		}

        AcademicCourse courseCreated = academicCourseServiceImpl.createAcademicCourse(courseDto);

        logger.debug("createAcademicCourse Id : {}, CourseName: {}", courseCreated.getCourseId(),

        		courseCreated.getCourseName());

        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",

        		 " Course Successfully Created", ""));

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage(), e);

			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

					e.getCause().getCause().getMessage(), ""));

		}

    }


	@PutMapping("/v1/updateAcademicCourse")

    ResponseEntity<ClientResponseBean> updateAcademicCourse(@RequestBody AcademicCourseDto courseDto) {

		try {

        logger.debug("updateAcademicCourse CourseName : {}", courseDto.getCourseName());


        AcademicCourse courseCreated = academicCourseServiceImpl.updateAcademicCourse(courseDto);

        logger.debug("updateAcademicCourse Id : {}, CourseName: {}", courseCreated.getCourseId(),

        		courseCreated.getCourseName());

        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",

        		 " Course Successfully Updated", ""));

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage(), e);

			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

					e.getCause().getCause().getMessage(), ""));

		}

    }


	@DeleteMapping("/v1/deleteAcademicCourseById/{courseId}")

    ResponseEntity<ClientResponseBean> deleteAcademicCourseById(@PathVariable long courseId) {

		try {

			academicCourseServiceImpl.deleteAcademicCourseById(courseId);

		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",

				" Course Successfully Deleted", ""));

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage(), e);

			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

					"",e.getCause().getCause().getMessage()));

		}

    }

	@GetMapping("/v1/getAcademicCourseByCourseId/{courseId}")

	public ResponseEntity<?> getAcademicCourseByCourseId(long courseId) {

		AcademicCourseDto academicCourseDto = academicCourseServiceImpl.getAcademicCourseByCourseId(courseId);

		if (null == academicCourseDto) {

			Map<String, String> noContentMessage = new HashMap<String, String>();

			noContentMessage.put("message", "Nothing found");

			return ResponseEntity.ok().body(noContentMessage);

		}

        return ResponseEntity.ok().body(academicCourseDto);

	}

	@GetMapping("/v1/getAllAcademicCourses")

    ResponseEntity<List<AcademicCourseDtoList>> getAllAcademicCourses() {

        List<AcademicCourseDtoList> academicCourseDtos = academicCourseServiceImpl.getAllAcademicCourses();

        return ResponseEntity.ok().body(academicCourseDtos);

    }

	@GetMapping("/v1/getAcademicCoursesByCategoryId/{categoryId}")

    ResponseEntity<List<AcademicCourseDto>> getAcademicCoursesByCategoryId(long categoryId) {

        List<AcademicCourseDto> academicCourseDtos = academicCourseServiceImpl.getAcademicCoursesByCategoryId(categoryId);

        return ResponseEntity.ok().body(academicCourseDtos);

    }

	@GetMapping("/v1/getAllAcademicCoursesByPagination/{pageNumber}/{pageSize}")

	  public  Page<AcademicCourseDto> getAllAcademicCoursesByPagination(@RequestParam int pageNumber, 

			  @RequestParam int pageSize) {

	        Page<AcademicCourseDto> page = academicCourseServiceImpl.getAllAcademicCoursesByPagination(pageNumber,pageSize);

	        return page;

	    }



}

 