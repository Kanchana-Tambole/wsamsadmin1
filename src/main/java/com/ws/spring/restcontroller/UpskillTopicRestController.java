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
 
import com.ws.spring.dto.UpskillTopicDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.UpskillTopic;
import com.ws.spring.service.UpskillTopicServiceImpl;
 
import io.swagger.annotations.Api;
 
@RestController
@RequestMapping("/upskilltopic")
@Api(value = "UpskillTopic Management System", tags = "UpskillTopic Management System")
public class UpskillTopicRestController {
 
	
 
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
 
	@Autowired
	UpskillTopicServiceImpl  upskillTopicServiceImpl;

	@PostMapping("/v1/createUpskillTopic")
    ResponseEntity<ClientResponseBean> createUpskillTopic(@RequestBody UpskillTopicDto topicDto) {
		try {
        logger.debug("createUpskillTopic TopicName : {}", topicDto.getTopicName());
        UpskillTopic categoryCreated = upskillTopicServiceImpl.createUpskillTopic(topicDto);
        logger.debug("createUpskillTopic Id : {}, TopicName: {}", categoryCreated.getTopicId(),
        		categoryCreated.getTopicName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Topic Successfully Created", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }

	@PutMapping("/v1/updateUpskillTopic")
    ResponseEntity<ClientResponseBean> updateUpskillTopic(@RequestBody UpskillTopicDto topicDto) {
		try {
        logger.debug("updateUpskillTopic TopicName : {}", topicDto.getTopicName());
        UpskillTopic categoryCreated = upskillTopicServiceImpl.updateUpskillTopic(topicDto);
        logger.debug("updateUpskillTopic Id : {}, TopicName: {}", categoryCreated.getTopicId(),
        		categoryCreated.getTopicName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Topic Successfully Updated", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }

	@DeleteMapping("/v1/deleteUpskillTopicById/{topicId}")
    ResponseEntity<ClientResponseBean> deleteUpskillTopicById(@PathVariable long topicId) {
		try {
			upskillTopicServiceImpl.deleteUpskillTopicById(topicId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				" Topic Successfully Deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					"",e.getCause().getCause().getMessage()));
		}
    }
	@GetMapping("/v1/getUpskillTopicByTopicId/{topicId}")
	public ResponseEntity<?> getUpskillTopicByTopicId(long topicId) {
		UpskillTopicDto topicDto = upskillTopicServiceImpl.getUpskillTopicByTopicId(topicId);
		if (null == topicDto) {
			Map<String, String> noContentMessage = new HashMap<String, String>();
			noContentMessage.put("message", "Nothing found");
			return ResponseEntity.ok().body(noContentMessage);
		}
        return ResponseEntity.ok().body(topicDto);
	}

	@GetMapping("/v1/getUpskillTopicsByModuleId/{moduleId}")
	public ResponseEntity<?> getUpskillTopicsByModuleId(long moduleId) {
		List<UpskillTopicDto> topicDtos = upskillTopicServiceImpl.getUpskillTopicsByModuleId(moduleId);
        return ResponseEntity.ok().body(topicDtos);
	}

	@GetMapping("/v1/getAllUpskillTopicsByPagination/{pageNumber}/{pageSize}")
	  public  Page<UpskillTopicDto> getAllUpskillTopicsByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<UpskillTopicDto> page = upskillTopicServiceImpl.getAllUpskillTopicsByPagination(pageNumber,pageSize);
	        return page;
	    }
}