package com.ws.spring.restcontroller;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.CourseSubjectDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.CourseSubject;
import com.ws.spring.service.CourseSubjectServiceImpl;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseSubject")
@Api(value = "Course Subject Management System", tags = "Course Subject Management System")
public class CourseSubjectRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CourseSubjectServiceImpl courseSubjectService;

    @PostMapping("/v1/create")
    public ResponseEntity<ClientResponseBean> create(@RequestBody CourseSubjectDto dto) {
        try {
            logger.debug("createCourseSubject: {}", dto.getSubjectName());
            CourseSubject created = courseSubjectService.createCourseSubject(dto);
            logger.debug("CourseSubject created with ID: {}", created.getCourseSubjectId());
            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS", "Course subject created", ""));
        } catch (Exception e) {
            logger.error("Exception in createCourseSubject: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                HttpStatus.BAD_REQUEST.value(), e.getCause().getCause().getMessage()));
        }
    }

    @PutMapping("/v1/update")
    public ResponseEntity<ClientResponseBean> update(@RequestBody CourseSubjectDto dto) {
        try {
            logger.debug("updateCourseSubject: {}", dto.getSubjectName());
            CourseSubject updated = courseSubjectService.updateCourseSubject(dto);
            logger.debug("CourseSubject updated with ID: {}", updated.getCourseSubjectId());
            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "Course subject updated", ""));
        } catch (Exception e) {
            logger.error("Exception in updateCourseSubject: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                HttpStatus.BAD_REQUEST.value(), e.getCause().getCause().getMessage()));
        }
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<ClientResponseBean> delete(@PathVariable long id) {
        try {
            courseSubjectService.deleteCourseSubjectById(id);
            logger.debug("CourseSubject deleted with ID: {}", id);
            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "Course subject deleted", ""));
        } catch (Exception e) {
            logger.error("Exception in deleteCourseSubject: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                HttpStatus.BAD_REQUEST.value(), e.getCause().getCause().getMessage()));
        }
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {
        try {
            CourseSubjectDto dto = courseSubjectService.getCourseSubjectById(id);
            if (dto == null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Nothing found");
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            logger.error("Exception in getCourseSubjectById: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                HttpStatus.BAD_REQUEST.value(), e.getCause().getCause().getMessage()));
        }
    }

    @GetMapping("/v1/list")
    public ResponseEntity<List<CourseSubjectDto>> list() {
        List<CourseSubjectDto> subjects = courseSubjectService.getAllCourseSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/v1/paginated")
    public Page<CourseSubjectDto> paginated(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return courseSubjectService.getAllCourseSubjectsByPagination(pageNumber, pageSize);
    }
}
