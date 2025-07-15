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
import com.ws.spring.dto.UpskillCourseDTo;
import com.ws.spring.dto.UpskillCourseDToList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.UpskillCourse;
import com.ws.spring.service.UpskillCourseServiceImpl;
 
import io.swagger.annotations.Api;
 
@RestController
@RequestMapping("/upskillcourse")
@Api(value = "UpskillCourse Management System", tags = "UpskillCourse Management System")
public class UpskillCourseRestController {
 
	
Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	UpskillCourseServiceImpl   upskillCourseServiceImpl;

	@PostMapping("/v1/createUpskillCourse")
    ResponseEntity<ClientResponseBean> createUpskillCourse(@RequestBody UpskillCourseDTo courseDto) {
		try {
        logger.debug("createUpskillCourse CourseName : {}", courseDto.getCourseName());
        if (null != upskillCourseServiceImpl.getCourseNameExist(courseDto.getCourseName())) {
			return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),
					"Course Already Exist"));
		}
        UpskillCourse courseCreated = upskillCourseServiceImpl.createUpskillCourse(courseDto);
        logger.debug("createUpskillCourse Id : {}, CourseName: {}", courseCreated.getCourseId(),
        		courseCreated.getCourseName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Course Successfully Created", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }

	@PutMapping("/v1/updateUpskillCourse")
    ResponseEntity<ClientResponseBean> updateUpskillCourse(@RequestBody UpskillCourseDTo courseDto) {
		try {
        logger.debug("updateUpskillCourse CourseName : {}", courseDto.getCourseName());

        UpskillCourse courseCreated = upskillCourseServiceImpl.updateUpskillCourse(courseDto);
        logger.debug("updateUpskillCourse Id : {}, CourseName: {}", courseCreated.getCourseId(),
        		courseCreated.getCourseName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Course Successfully Updated", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }

	@DeleteMapping("/v1/deleteUpskillCourseById/{courseId}")
    ResponseEntity<ClientResponseBean> deleteUpskillCourseById(@PathVariable long courseId) {
		try {
			upskillCourseServiceImpl.deleteUpskillCourseById(courseId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				" Course Successfully Deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					"",e.getCause().getCause().getMessage()));
		}
    }

	@GetMapping("/v1/getUpskillCourseByCourseId/{courseId}")
	public ResponseEntity<?> getUpskillCourseByCourseId(long courseId) {
		UpskillCourseDTo upskillCourseDTo = upskillCourseServiceImpl.getUpskillCourseByCourseId(courseId);
		if (null == upskillCourseDTo) {
			Map<String, String> noContentMessage = new HashMap<String, String>();
			noContentMessage.put("message", "Nothing found");
			return ResponseEntity.ok().body(noContentMessage);
		}
        return ResponseEntity.ok().body(upskillCourseDTo);
	}

	@GetMapping("/v1/getUpskillCoursesByCategoryId/{categoryId}")
    ResponseEntity<List<UpskillCourseDTo>> getUpskillCoursesByCategoryId(long categoryId) {
        List<UpskillCourseDTo> upskillCourseDTos = upskillCourseServiceImpl.getUpskillCoursesByCategoryId(categoryId);
        return ResponseEntity.ok().body(upskillCourseDTos);
    }
	@GetMapping("/v1/getAllUpskillCourses")
    ResponseEntity<List<UpskillCourseDToList>> getAllUpskillCourses() {
        List<UpskillCourseDToList> upskillCourseDTos = upskillCourseServiceImpl.getAllUpskillCourses();
        return ResponseEntity.ok().body(upskillCourseDTos);
    }

	@GetMapping("/v1/getAllUpskillCoursesByPagination/{pageNumber}/{pageSize}")
	  public  Page<UpskillCourseDTo> getAllUpskillCoursesByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<UpskillCourseDTo> page = upskillCourseServiceImpl.getAllUpskillCoursesByPagination(pageNumber,pageSize);
	        return page;
	    }


}