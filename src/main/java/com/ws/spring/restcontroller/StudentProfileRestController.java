package com.ws.spring.restcontroller;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.StudentProfileDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.StudentProfile;
import com.ws.spring.service.StudentProfileServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/studentProfile")
@Api(value = "StudentProfile Management System", tags = "StudentProfile Management System")
public class StudentProfileRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private StudentProfileServiceImpl studentProfileService;

    @PostMapping("/v1/create")
    public ResponseEntity<ClientResponseBean> create(@RequestBody StudentProfileDto dto) {
        try {
            logger.debug("Creating Student: {}", dto.getFirstName());
            StudentProfile profile = studentProfileService.createStudentProfile(dto);
            logger.debug("Student created with ID: {}", profile.getId());
            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS", "Student successfully created", ""));
        } catch (Exception e) {
            logger.error("Error creating student: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED", e.getCause().getCause().getMessage(), "")
            );
        }
    }

    @PutMapping("/v1/update")
    public ResponseEntity<ClientResponseBean> update(@RequestBody StudentProfileDto dto) {
        try {
            logger.debug("Updating Student ID: {}", dto.getId());
            StudentProfile updated = studentProfileService.updateStudentProfile(dto);
            logger.debug("Student updated with ID: {}", updated.getId());
            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "Student successfully updated", ""));
        } catch (Exception e) {
            logger.error("Error updating student: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED", e.getCause().getCause().getMessage(), "")
            );
        }
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<ClientResponseBean> delete(@PathVariable long id) {
        try {
            studentProfileService.deleteStudentProfileById(id);
            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "Student successfully deleted", ""));
        } catch (Exception e) {
            logger.error("Error deleting student: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED", e.getCause().getCause().getMessage(), "")
            );
        }
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        StudentProfileDto dto = studentProfileService.getStudentProfileById(id);
        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok(noContentMessage);
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/v1/list")
    public ResponseEntity<List<StudentProfileDto>> listAll() {
        List<StudentProfileDto> list = studentProfileService.getAllStudentProfiles();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/v1/paginated")
    public Page<StudentProfileDto> paginated(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return studentProfileService.getAllStudentProfilesByPagination(pageNumber, pageSize);
    }
}
