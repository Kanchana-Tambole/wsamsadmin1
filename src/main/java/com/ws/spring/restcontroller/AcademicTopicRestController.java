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
 
import com.ws.spring.dto.AcademicTopicDto;

import com.ws.spring.exception.ClientResponseBean;

import com.ws.spring.model.AcademicTopic;

import com.ws.spring.service.AcademicTopicServiceImpl;
 
import io.swagger.annotations.Api;
 
@RestController

@RequestMapping("/academictopic")

@Api(value = "AcademicTopic Management System", tags = "AcademicTopic Management System")

public class AcademicTopicRestController {
 
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
 
	@Autowired

	AcademicTopicServiceImpl   academicTopicServiceImpl;



	@PostMapping("/v1/createAcademicTopic")

    ResponseEntity<ClientResponseBean> createAcademicTopic(@RequestBody AcademicTopicDto topicDto) {

		try {

        logger.debug("createAcademicTopic TopicName : {}", topicDto.getTopicName());

        AcademicTopic categoryCreated = academicTopicServiceImpl.createAcademicTopic(topicDto);

        logger.debug("createAcademicTopic Id : {}, TopicName: {}", categoryCreated.getTopicId(),

        		categoryCreated.getTopicName());

        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",

        		 " Topic Successfully Created", ""));

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage(), e);

			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

					e.getCause().getCause().getMessage(), ""));

		}

    }


	@PutMapping("/v1/updateAcademicTopic")

    ResponseEntity<ClientResponseBean> updateAcademicTopic(@RequestBody AcademicTopicDto topicDto) {

		try {

        logger.debug("updateAcademicTopic TopicName : {}", topicDto.getTopicName());

        AcademicTopic categoryCreated = academicTopicServiceImpl.updateAcademicTopic(topicDto);

        logger.debug("updateAcademicTopic Id : {}, TopicName: {}", categoryCreated.getTopicId(),

        		categoryCreated.getTopicName());

        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",

        		 " Topic Successfully Updated", ""));

		} catch (Exception e) {

			logger.error("Exception occured : {}", e.getMessage(), e);

			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

					e.getCause().getCause().getMessage(), ""));

		}

    }


//	@DeleteMapping("/v1/deleteAcademicTopicById/{topicId}")

//    ResponseEntity<ClientResponseBean> deleteAcademicTopicById(@PathVariable long topicId) {

//		try {

//			academicTopicServiceImpl.deleteAcademicTopicById(topicId);

//		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",

//				" Topic Successfully Deleted", ""));

//		} catch (Exception e) {

//			logger.error("Exception occured : {}", e.getMessage(), e);

//			

//			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",

//					"",e.getCause().getCause().getMessage()));

//		}

//    }

	@DeleteMapping("/v1/deleteAcademicTopicById/{topicId}")

	public ResponseEntity<ClientResponseBean> deleteAcademicTopicById(@PathVariable long topicId) {

	    try {

	        academicTopicServiceImpl.deleteAcademicTopicById(topicId);

	        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",

	                "Topic Successfully Deleted", ""));

	    } catch (Exception e) {

	        logger.error("Exception occurred while deleting topicId {}: {}", topicId, e.getMessage(), e);

	        String errorMessage = (e.getCause() != null && e.getCause().getCause() != null)

	                ? e.getCause().getCause().getMessage()

	                : e.getMessage();

	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)

	                .body(new ClientResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), "FAILED",

	                        "Error deleting topic", errorMessage));

	    }

	}
 
	

	@GetMapping("/v1/getAcademicTopicByTopicId/{topicId}")

	public ResponseEntity<?> getAcademicTopicByTopicId(long topicId) {

		AcademicTopicDto topicDto = academicTopicServiceImpl.getAcademicTopicByTopicId(topicId);

		if (null == topicDto) {

			Map<String, String> noContentMessage = new HashMap<String, String>();

			noContentMessage.put("message", "Nothing found");

			return ResponseEntity.ok().body(noContentMessage);

		}

        return ResponseEntity.ok().body(topicDto);

	}


	@GetMapping("/v1/getAcademicTopicsByModuleId/{moduleId}")

	public ResponseEntity<?> getAcademicTopicsByModuleId(long moduleId) {

		List<AcademicTopicDto> topicDtos = academicTopicServiceImpl.getAcademicTopicsByModuleId(moduleId);

        return ResponseEntity.ok().body(topicDtos);

	}

	@GetMapping("/v1/getAllAcademicTopicsByPagination/{pageNumber}/{pageSize}")

	  public  Page<AcademicTopicDto> getAllAcademicTopicsByPagination(@RequestParam int pageNumber, 

			  @RequestParam int pageSize) {

	        Page<AcademicTopicDto> page = academicTopicServiceImpl.getAllAcademicTopicsByPagination(pageNumber,pageSize);

	        return page;

	    }

}

 