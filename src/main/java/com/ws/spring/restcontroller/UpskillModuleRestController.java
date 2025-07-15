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
 
import com.ws.spring.dto.UpskillModuleDto;
import com.ws.spring.dto.UpskillModuleDtoList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.UpskillModule;
import com.ws.spring.service.UpskillModuleServiceImpl;
 
import io.swagger.annotations.Api;
 
@RestController
@RequestMapping("/upskillmodule")
@Api(value = "UpskillModule Management System", tags = "UpskillModule Management System")
public class UpskillModuleRestController {
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
 
	@Autowired
	UpskillModuleServiceImpl  upskillModuleServiceImpl;

	@PostMapping("/v1/createUpskillModule")
    ResponseEntity<ClientResponseBean> createUpskillModule(@RequestBody UpskillModuleDto moduleDto) {
		try {
        logger.debug("createUpskillModule ModuleName : {}", moduleDto.getModuleName());
        UpskillModule moduleCreated = upskillModuleServiceImpl.createUpskillModule(moduleDto);
        logger.debug("createUpskillModule Id : {}, ModuleName: {}", moduleCreated.getModuleId(),
        		moduleCreated.getModuleName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Module Successfully Created", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }

	@PutMapping("/v1/updateUpskillModule")
    ResponseEntity<ClientResponseBean> updateUpskillModule(@RequestBody UpskillModuleDto moduleDto) {
		try {
        logger.debug("updateUpskillModule ModuleName : {}", moduleDto.getModuleName());
        UpskillModule moduleCreated = upskillModuleServiceImpl.updateUpskillModule(moduleDto);
        logger.debug("updateUpskillModule Id : {}, ModuleName: {}", moduleCreated.getModuleId(),
        		moduleCreated.getModuleName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Module Successfully Updated", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }

	@DeleteMapping("/v1/deleteUpskillModuleById/{moduleId}")
    ResponseEntity<ClientResponseBean> deleteUpskillModuleById(@PathVariable long moduleId) {
		try {
			upskillModuleServiceImpl.deleteUpskillModuleById(moduleId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				" Module Successfully Deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					"",e.getCause().getCause().getMessage()));
		}
    }
//	@GetMapping("/v1/getUpskillModuleByModuleId/{moduleId}")
//	public ResponseEntity<?> getUpskillModuleByModuleId(@PathVariable long moduleId) {
//	    UpskillModuleDto moduleDto = upskillModuleServiceImpl.getUpskillModuleByModuleId(moduleId);
// 
//	    if (moduleDto == null) {
//	        Map<String, String> noContentMessage = new HashMap<>();
//	        noContentMessage.put("message", "Nothing found");
//	        return ResponseEntity.ok().body(noContentMessage);
//	    }
// 
//	    return ResponseEntity.ok().body(moduleDto);
//	}
// 
	

	@GetMapping("/v1/getUpskillModulesByCourseId/{courseId}")
    ResponseEntity<List<UpskillModuleDto>> getUpskillModulesByCourseId(long courseId) {
        List<UpskillModuleDto> moduleDtos = upskillModuleServiceImpl.getUpskillModulesByCourseId(courseId);
        return ResponseEntity.ok().body(moduleDtos);
    }
	@GetMapping("/v1/getAllUpskillModules")
    ResponseEntity<List<UpskillModuleDtoList>> getAllUpskillModules() {
        List<UpskillModuleDtoList> moduleDtos = upskillModuleServiceImpl.getAllUpskillModules();
        return ResponseEntity.ok().body(moduleDtos);
    }

	@GetMapping("/v1/getAllUpskillModulesByPagination/{pageNumber}/{pageSize}")
	  public  Page<UpskillModuleDto> getAllUpskillModulesByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<UpskillModuleDto> page = upskillModuleServiceImpl.getAllUpskillModulesByPagination(pageNumber,pageSize);
	        return page;
	    }

 
}