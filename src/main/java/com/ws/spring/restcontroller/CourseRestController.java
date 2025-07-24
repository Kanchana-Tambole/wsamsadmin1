package com.ws.spring.restcontroller;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.CourseDto;
<<<<<<< HEAD
=======
import com.ws.spring.dto.CourseDtoList;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Course;
import com.ws.spring.service.CourseServiceImpl;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

>>>>>>> daccd45 (Initial commit)
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
<<<<<<< HEAD
            logger.debug("createCourse: Course Name - {}", dto.getCourseName());
            Course createdCourse = courseService.createCourse(dto);
            logger.debug("Course created with ID: {}", createdCourse.getCourseId());
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Course successfully created", ""));
        } catch (Exception e) {
            logger.error("Exception in createCourse: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), e.getCause().getCause().getMessage()));
=======
            logger.debug("createCourse CourseName : {}", dto.getCourseName());

            Course course = courseService.createCourse(dto);

            logger.debug("createCourse Id : {}, CourseName: {}", course.getCourseId(), course.getCourseName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Course Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
>>>>>>> daccd45 (Initial commit)
        }
    }

    @PutMapping("/v1/update")
    public ResponseEntity<ClientResponseBean> updateCourse(@RequestBody CourseDto dto) {
        try {
<<<<<<< HEAD
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
=======
            logger.debug("updateCourse CourseName : {}", dto.getCourseName());

            Course course = courseService.updateCourse(dto);

            logger.debug("updateCourse Id : {}, CourseName: {}", course.getCourseId(), course.getCourseName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Course Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        }
    }

    @DeleteMapping("/v1/deleteById/{courseId}")
    public ResponseEntity<ClientResponseBean> deleteCourseById(@PathVariable long courseId) {
        try {
            courseService.deleteCourseById(courseId);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Course Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
        }
    }

    @GetMapping("/v1/getById/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable long courseId) {
        CourseDto dto = courseService.getCourseById(courseId);

        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok(noContentMessage);
        }

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/v1/getAll")
    public ResponseEntity<List<CourseDtoList>> getAllCourses() {
        List<CourseDtoList> courseList = courseService.getAllCourses();
        return ResponseEntity.ok(courseList);
    }

    @GetMapping("/v1/getAllByPagination")
>>>>>>> daccd45 (Initial commit)
    public Page<CourseDto> getAllCoursesByPagination(@RequestParam int pageNumber,
                                                     @RequestParam int pageSize) {
        return courseService.getAllCoursesByPagination(pageNumber, pageSize);
    }
}
