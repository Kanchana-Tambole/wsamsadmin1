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
import com.ws.spring.dto.DepartmentDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Department;
import com.ws.spring.service.DepartmentServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/department")
@Api(value = "Department Management System", tags = "Department Management System")
public class DepartmentRestController {
	
Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	DepartmentServiceImpl departmentServiceImpl;
	
	@PostMapping("/v1/createDepartment")
    ResponseEntity<ClientResponseBean> createDepartment(@RequestBody DepartmentDto departmentDto) {
		try {
        logger.debug("createDepartment DepartmentName : {}", departmentDto.getDepartmentName());
        
        if (null != departmentServiceImpl.queryByDepartmentName(departmentDto.getDepartmentName())) {
			return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),
					"Department Already Exist"));
		}
        
        Department departmentCreated = departmentServiceImpl.createDepartment(departmentDto);
        logger.debug("createDepartment Id : {}, DepartmentName: {}", departmentCreated.getDepartmentId(),
        		departmentCreated.getDepartmentName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " department successfully created", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	@PutMapping("/v1/updateDepartment")
    ResponseEntity<ClientResponseBean> updateDepartment(@RequestBody DepartmentDto departmentDto) {
		try {
        logger.debug("updateDepartment DepartmentName : {}", departmentDto.getDepartmentName());
        
        Department departmentCreated = departmentServiceImpl.updateDepartment(departmentDto);
        logger.debug("updateDepartment Id : {}, DepartmentName: {}", departmentCreated.getDepartmentId(),
        		departmentCreated.getDepartmentName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		" department successfully updated", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	@DeleteMapping("/v1/deleteDepartmentById/{departmentId}")
    ResponseEntity<ClientResponseBean> deleteDepartmentById(@PathVariable long departmentId) {
		try {
		departmentServiceImpl.deleteDepartmentById(departmentId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				" Department Successfully Deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	@GetMapping("/v1/queryDepartmentByDepartmentId/{departmentId}")
    ResponseEntity<DepartmentDto> queryDepartmentByDepartmentId(@PathVariable long departmentId) {
		DepartmentDto departmentDto = departmentServiceImpl.queryDepartmentByDepartmentId(departmentId);
        return ResponseEntity.ok().body(departmentDto);
    }
	
	@GetMapping("/v1/queryAllDepartments")
    ResponseEntity<List<DepartmentDto>> queryAllDepartments() {
        List<DepartmentDto> departmentDtos = departmentServiceImpl.queryAllDepartments();
        return ResponseEntity.ok().body(departmentDtos);
    }

//	@GetMapping("/checkdepartmentname")
		public boolean checkFocusExists(String departmentName){
			return  departmentServiceImpl.checkDepartNameExists(departmentName);
}
		
		@GetMapping("/v1/getAllDepartmentsByPagination/{pageNumber}/{pageSize}")
		  public  Page<DepartmentDto> getAllDepartmentsByPagination(@RequestParam int pageNumber, 
				  @RequestParam int pageSize) {
		        Page<DepartmentDto> page = departmentServiceImpl.getAllDepartmentsByPagination(pageNumber,pageSize);
		        
		        return page;
		    }
	
}
