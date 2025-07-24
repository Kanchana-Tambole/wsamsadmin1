package com.ws.spring.restcontroller;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.CourseSubjectDto;
<<<<<<< HEAD
=======
import com.ws.spring.dto.CourseSubjectDtoList;
>>>>>>> daccd45 (Initial commit)
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
<<<<<<< HEAD
@Api(value = "Course Subject Management System", tags = "Course Subject Management System")
=======
@Api(value = "CourseSubject Management System", tags = "CourseSubject Management System")
>>>>>>> daccd45 (Initial commit)
public class CourseSubjectRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CourseSubjectServiceImpl courseSubjectService;

<<<<<<< HEAD
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
=======
    @PostMapping("/v1/createCourseSubject")
    public ResponseEntity<ClientResponseBean> createCourseSubject(@RequestBody CourseSubjectDto dto) {
        try {
            logger.debug("Creating CourseSubject with SubjectName: {}", dto.getSubjectName());

            CourseSubject created = courseSubjectService.createCourseSubject(dto);

            logger.debug("Created CourseSubject ID: {}, Name: {}", created.getCourseSubjectId(), created.getSubjectName());

            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS", "CourseSubject Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred while creating CourseSubject: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                    new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                            e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @PutMapping("/v1/updateCourseSubject")
    public ResponseEntity<ClientResponseBean> updateCourseSubject(@RequestBody CourseSubjectDto dto) {
        try {
            logger.debug("Updating CourseSubject ID: {}", dto.getCourseSubjectId());

            CourseSubject updated = courseSubjectService.updateCourseSubject(dto);

            logger.debug("Updated CourseSubject ID: {}, Name: {}", updated.getCourseSubjectId(), updated.getSubjectName());

            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "CourseSubject Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred while updating CourseSubject: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                    new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                            e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @DeleteMapping("/v1/deleteCourseSubjectById/{id}")
    public ResponseEntity<ClientResponseBean> deleteCourseSubject(@PathVariable long id) {
        try {
            courseSubjectService.deleteCourseSubjectById(id);
            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "CourseSubject successfully deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred while deleting CourseSubject: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                    new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                            e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @GetMapping("/v1/getCourseSubjectById/{id}")
    public ResponseEntity<?> getCourseSubjectById(@PathVariable long id) {
        CourseSubjectDto dto = courseSubjectService.getCourseSubjectById(id);

        if (dto == null) {
            Map<String, String> noContent = new HashMap<>();
            noContent.put("message", "No CourseSubject found");
            return ResponseEntity.ok(noContent);
        }

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/v1/getAllCourseSubjects")
    public ResponseEntity<List<CourseSubjectDtoList>> getAllCourseSubjects() {
        List<CourseSubjectDtoList> subjectList = courseSubjectService.getAllCourseSubjects();
        return ResponseEntity.ok(subjectList);
    }

    @GetMapping("/v1/getAllCourseSubjectsByPagination")
    public Page<CourseSubjectDto> getAllCourseSubjectsByPagination(@RequestParam int pageNumber,
                                                                   @RequestParam int pageSize) {
>>>>>>> daccd45 (Initial commit)
        return courseSubjectService.getAllCourseSubjectsByPagination(pageNumber, pageSize);
    }
}
