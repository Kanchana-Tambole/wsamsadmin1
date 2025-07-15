package com.ws.spring.restcontroller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

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
import com.ws.spring.dto.SkillDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Skill;
import com.ws.spring.service.SkillServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/skill")
@Api(value = "Skill Management System", tags = "Skill Management System")
public class SkillRestController {

Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	SkillServiceImpl  skillServiceImpl;
	
	
	
	
	
	
	@PostMapping("/v1/createSkill")
	public ResponseEntity<ClientResponseBean> createSkill(@RequestBody SkillDto skillDto) {
	    try {
	        // Log the skill name being received in the request
	        logger.debug("createSkill SkillName : {}", skillDto.getSkillName());
	        
	        if (null != skillServiceImpl.getSkillNameExist(skillDto.getSkillName())) {
				return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),
						"Skill Already Exist"));
			}
	        
	        // Assuming you have a service method to create a Skill entity
	        Skill skillCreated = skillServiceImpl.createSkill(skillDto);
	        
	        // Log the ID and Name of the newly created Skill entity
	        logger.debug("createSkill Id : {}, SkillName : {}", 
	                     skillCreated.getSkillId(), skillCreated.getSkillName());
	        
	        // Return success response with a message
	        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
	                skillCreated.getSkillName() + " skill successfully created", ""));
	                
	    } catch (Exception e) {
	        // Log the exception error message
	        logger.error("Exception occurred : {}", e.getMessage(), e);
	        
	        // Return failure response with the error message
	        return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED", "",
	                e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
	    }
	}

	
	@PutMapping("/v1/updateSkill")
	public ResponseEntity<ClientResponseBean> updateSkill(@RequestBody SkillDto skillDto) {
	    try {
	        // Log the skill name being received in the request
	        logger.debug("updateSkill SkillName : {}", skillDto.getSkillName());
	        
	        // Assuming you have a service method to create a Skill entity
	        Skill skillCreated = skillServiceImpl.updateSkill(skillDto);
	        
	        // Log the ID and Name of the newly created Skill entity
	        logger.debug("updateSkill Id : {}, SkillName : {}", 
	                     skillCreated.getSkillId(), skillCreated.getSkillName());
	        
	        // Return success response with a message
	        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
	                skillCreated.getSkillName() + " skill successfully updated", ""));
	                
	    } catch (Exception e) {
	        // Log the exception error message
	        logger.error("Exception occurred : {}", e.getMessage(), e);
	        
	        // Return failure response with the error message
	        return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED", "",
	                e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
	    }
	}
	
	
	@DeleteMapping("/v1/deleteSkillById/{skillId}")
	public ResponseEntity<ClientResponseBean> deleteSkillById(@PathVariable long skillId) {
	    try {
			/*
			 * // Check if the skill exists before attempting to delete if
			 * (!skillServiceImpl.existsById(skillId)) { return
			 * ResponseEntity.status(HttpStatus.NOT_FOUND).body( new
			 * ClientResponseBean(HttpStatus.NOT_FOUND.value(), "FAILED", "Skill with ID " +
			 * skillId + " not found.", "")); }
			 */
	        // Log the attempt to delete
	        logger.debug("Attempting to delete skill with ID : {}", skillId);
	        
	        // Delete the skill entity
	        skillServiceImpl.deleteSkillById(skillId);
	        
	        // Return success response
	        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
	                skillId + " skill successfully deleted", ""));
	                
	    } catch (Exception e) {
	        // Log the error and return a failure response
	        logger.error("Exception occurred while deleting skill with ID {}: {}", skillId, e.getMessage(), e);
	        
	        // Return failure response with a more general error message
	        String errorMessage = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
	        return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED", "",
	                errorMessage));
	    }
	}

	
	
	@GetMapping("/v1/getSkillBySkillId/{skillId}")
	public ResponseEntity<?> getSkillBySkillId(@PathVariable long skillId) {
	    try {
	        // Check if the skillId is valid
	        if (skillId <= 0) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
	                new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED", "",
	                "Invalid skill ID."));
	        }

	        // Query the skill by ID
	        SkillDto skillDto = skillServiceImpl.getSkillBySkillId(skillId);

	        // Check if the skill exists
	        if (skillDto == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	                new ClientResponseBean(HttpStatus.NOT_FOUND.value(), "FAILED", "",
	                "Skill with ID " + skillId + " not found."));
	        }

	        // Log the successful retrieval of the skill
	        logger.info("Successfully fetched skill with ID: {}", skillId);

	        // Return the skill data if found
	        return ResponseEntity.ok().body(skillDto);
	        
	    } catch (EntityNotFoundException e) {
	        // Log the specific exception and return a 404
	        logger.error("Skill with ID {} not found: {}", skillId, e.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	            new ClientResponseBean(HttpStatus.NOT_FOUND.value(), "FAILED", "",
	            "Skill with ID " + skillId + " not found."));
	    } catch (Exception e) {
	        // Log the exception and return a failure response
	        logger.error("Exception occurred: {}", e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
	            new ClientResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), "FAILED", "",
	            "An error occurred while fetching the skill."));
	    }
	}

	
	@GetMapping("/v1/getAllSkills")
	public ResponseEntity<?> getAllSkills() {
	    try {
	        // Retrieve all skills from the service layer
	        List<SkillDto> skillDtoList = skillServiceImpl.getAllSkills();

	        // Check if the list is empty
	        if (skillDtoList.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
	                new ClientResponseBean(HttpStatus.NO_CONTENT.value(), "FAILED", "", "No skills found."));
	        }

	        // Return the list of skills with a 200 OK status
	        return ResponseEntity.ok().body(skillDtoList);
	    } catch (Exception e) {
	        // Log the exception and return a failure response with 500 status
	        logger.error("Exception occurred: {}", e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
	            new ClientResponseBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), "FAILED",
	           "", "An error occurred while fetching skills."));
	    }
	}

	
	@GetMapping("/v1/getAllSkillsByPagination/{pageNumber}/{pageSize}")
	  public  Page<SkillDto> getAllSkillsByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<SkillDto> page = skillServiceImpl.getAllSkillsByPagination(pageNumber,pageSize);
	        
	        return page;
	    }
	
	
	
	
	
	
	
}
