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
import com.ws.spring.dto.UpskillCategoryDto;
import com.ws.spring.dto.UpskillCategoryDtoList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.UpskillCategory;
import com.ws.spring.service.UpskillCategoryServiceImpl;
 
import io.swagger.annotations.Api;
 
@RestController
@RequestMapping("/upskillcategory")
@Api(value = "UpskillCategory Management System", tags = "UpskillCategory Management System")
public class UpskillCategoryRestController {
 
	
Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	@Autowired
	UpskillCategoryServiceImpl  upskillCategoryServiceImpl;


	@PostMapping("/v1/createUpskillCategory")
    ResponseEntity<ClientResponseBean> createUpskillCategory(@RequestBody UpskillCategoryDto categoryDto) {
		try {
        logger.debug("createUpskillCategory CategoryName : {}", categoryDto.getCategoryName());
        if (null != upskillCategoryServiceImpl.getCategoryNameExist(categoryDto.getCategoryName())) {
			return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),
					"Category Already Exist"));
		}
        UpskillCategory categoryCreated = upskillCategoryServiceImpl.createUpskillCategory(categoryDto);
        logger.debug("createUpskillCategory Id : {}, CategoryName: {}", categoryCreated.getCategoryId(),
        		categoryCreated.getCategoryName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Category Successfully Created", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }

	@PutMapping("/v1/updateUpskillCategory")
    ResponseEntity<ClientResponseBean> updateUpskillCategory(@RequestBody UpskillCategoryDto categoryDto) {
		try {
        logger.debug("updateUpskillCategory CategoryName : {}", categoryDto.getCategoryName());

        UpskillCategory categoryCreated = upskillCategoryServiceImpl.updateUpskillCategory(categoryDto);
        logger.debug("updateUpskillCategory Id : {}, CategoryName: {}", categoryCreated.getCategoryId(),
        		categoryCreated.getCategoryName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Category Successfully Updated", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }

	@DeleteMapping("/v1/deleteUpskillCategoryById/{categoryId}")
    ResponseEntity<ClientResponseBean> deleteUpskillCategoryById(@PathVariable long categoryId) {
		try {
			upskillCategoryServiceImpl.deleteUpskillCategoryById(categoryId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				" Category successfully Deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	@GetMapping("/v1/getUpskillCategoryByCategoryId/{categoryId}")
	public ResponseEntity<?> getUpskillCategoryByCategoryId(long categoryId) {
		UpskillCategoryDto categoryDto = upskillCategoryServiceImpl.getUpskillCategoryByCategoryId(categoryId);
		if (null == categoryDto) {
			Map<String, String> noContentMessage = new HashMap<String, String>();
			noContentMessage.put("message", "Nothing found");
			return ResponseEntity.ok().body(noContentMessage);
		}
        return ResponseEntity.ok().body(categoryDto);
	}

	@GetMapping("/v1/getAllUpskillCategory")
    ResponseEntity<List<UpskillCategoryDtoList>> getAllUpskillCategory() {
        List<UpskillCategoryDtoList> categoryDtos = upskillCategoryServiceImpl.getAllUpskillCategory();
        return ResponseEntity.ok().body(categoryDtos);
    }

	@GetMapping("/v1/getAllUpskillCategoryByPagination/{pageNumber}/{pageSize}")
	  public  Page<UpskillCategoryDto> getAllUpskillCategoryByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<UpskillCategoryDto> page = upskillCategoryServiceImpl.getAllUpskillCategoryByPagination(pageNumber,pageSize);
	        return page;
	    }


}