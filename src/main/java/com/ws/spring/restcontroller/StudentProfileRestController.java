package com.ws.spring.restcontroller;

<<<<<<< HEAD
import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.StudentProfileDto;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.StudentProfileDto;
import com.ws.spring.dto.StudentProfileDtoList;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.StudentProfile;
import com.ws.spring.service.StudentProfileServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
=======

>>>>>>> daccd45 (Initial commit)
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;

=======
>>>>>>> daccd45 (Initial commit)
@RestController
@RequestMapping("/studentProfile")
@Api(value = "StudentProfile Management System", tags = "StudentProfile Management System")
public class StudentProfileRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private StudentProfileServiceImpl studentProfileService;

<<<<<<< HEAD
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
=======
    @PostMapping("/v1/createStudentProfile")
    public ResponseEntity<ClientResponseBean> createStudentProfile(@RequestBody StudentProfileDto dto) {
        try {
            logger.debug("createStudentProfile FirstName : {}", dto.getFirstName());
            StudentProfile profile = studentProfileService.createStudentProfile(dto);
            logger.debug("createStudentProfile Id : {}, Name: {}", profile.getId(), profile.getFirstName());
            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.CREATED.value(),
                    "SUCCESS",
                    "Student Profile Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                    ClientResponseUtil.getExceptionResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage()));
        }
    }

    @PutMapping("/v1/updateStudentProfile")
    public ResponseEntity<ClientResponseBean> updateStudentProfile(@RequestBody StudentProfileDto dto) {
        try {
            logger.debug("updateStudentProfile ID : {}", dto.getId());
            StudentProfile updated = studentProfileService.updateStudentProfile(dto);
            logger.debug("updateStudentProfile ID : {}, Name: {}", updated.getId(), updated.getFirstName());
            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(),
                    "SUCCESS",
                    "Student Profile Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                    ClientResponseUtil.getExceptionResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage()));
        }
    }

    @DeleteMapping("/v1/deleteStudentProfileById/{id}")
    public ResponseEntity<ClientResponseBean> deleteStudentProfileById(@PathVariable long id) {
        try {
            studentProfileService.deleteStudentProfileById(id);
            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(),
                    "SUCCESS",
                    "Student Profile Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                    ClientResponseUtil.getExceptionResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage()));
        }
    }

    @GetMapping("/v1/getStudentProfileById/{id}")
    public ResponseEntity<?> getStudentProfileById(@PathVariable long id) {
>>>>>>> daccd45 (Initial commit)
        StudentProfileDto dto = studentProfileService.getStudentProfileById(id);
        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok(noContentMessage);
        }
        return ResponseEntity.ok(dto);
    }

<<<<<<< HEAD
    @GetMapping("/v1/list")
    public ResponseEntity<List<StudentProfileDto>> listAll() {
        List<StudentProfileDto> list = studentProfileService.getAllStudentProfiles();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/v1/paginated")
    public Page<StudentProfileDto> paginated(@RequestParam int pageNumber, @RequestParam int pageSize) {
=======
    @GetMapping("/v1/getAllStudentProfiles")
    public ResponseEntity<List<StudentProfileDtoList>> getAllStudentProfiles() {
        List<StudentProfileDtoList> list = studentProfileService.getAllStudentProfiles();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/v1/getAllStudentProfilesByPagination")
    public Page<StudentProfileDto> getAllStudentProfilesByPagination(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
>>>>>>> daccd45 (Initial commit)
        return studentProfileService.getAllStudentProfilesByPagination(pageNumber, pageSize);
    }
}
