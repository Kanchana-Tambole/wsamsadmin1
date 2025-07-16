package com.ws.spring.restcontroller;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.CourseDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Course;
import com.ws.spring.service.CourseServiceImpl;

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
@RequestMapping("/course")
@Api(value = "Course Management System", tags = "Course Management System")
public class CourseRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CourseServiceImpl courseService;

    @PostMapping("/v1/create")
    public ResponseEntity<ClientResponseBean> createCourse(@RequestBody CourseDto dto) {
        try {
            logger.debug("createCourse: Course Name - {}", dto.getCourseName());
            Course createdCourse = courseService.createCourse(dto);
            logger.debug("Course created with ID: {}", createdCourse.getCourseId());
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Course successfully created", ""));
        } catch (Exception e) {
            logger.error("Exception in createCourse: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), e.getCause().getCause().getMessage()));
        }
    }

    @PutMapping("/v1/update")
    public ResponseEntity<ClientResponseBean> updateCourse(@RequestBody CourseDto dto) {
        try {
            logger.debug("updateCourse: Course ID - {}", dto.getCourseId());
            Course updatedCourse = courseService.updateCourse(dto);
            logger.debug("Course updated with ID: {}", updatedCourse.getCourseId());
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Course successfully updated", ""));
        } catch (Exception e) {
            logger.error("Exception in updateCourse: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), e.getCause().getCause().getMessage()));
        }
    }

    @DeleteMapping("/v1/delete/{courseId}")
    public ResponseEntity<ClientResponseBean> deleteCourse(@PathVariable long courseId) {
        try {
            courseService.deleteCourseById(courseId);
            logger.debug("Course deleted with ID: {}", courseId);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Course successfully deleted", ""));
        } catch (Exception e) {
            logger.error("Exception in deleteCourse: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), e.getCause().getCause().getMessage()));
        }
    }

    @GetMapping("/v1/get/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable long courseId) {
        try {
            CourseDto courseDto = courseService.getCourseById(courseId);
            if (courseDto == null) {
                Map<String, String> message = new HashMap<>();
                message.put("message", "Nothing found");
                return ResponseEntity.ok().body(message);
            }
            return ResponseEntity.ok().body(courseDto);
        } catch (Exception e) {
            logger.error("Exception in getCourseById: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), e.getCause().getCause().getMessage()));
        }
    }

    @GetMapping("/v1/list")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courseList = courseService.getAllCourses();
        return ResponseEntity.ok().body(courseList);
    }

    @GetMapping("/v1/paginated")
    public Page<CourseDto> getAllCoursesByPagination(@RequestParam int pageNumber,
                                                     @RequestParam int pageSize) {
        return courseService.getAllCoursesByPagination(pageNumber, pageSize);
    }
}
