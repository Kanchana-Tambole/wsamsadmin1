package com.ws.spring.restcontroller;

import com.ws.spring.dto.FacultyProfileDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.FacultyProfile;
import com.ws.spring.service.FacultyProfileServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import java.util.List;

@RestController
@RequestMapping("/facultyProfile")
@Api(value = "FacultyProfile Management System", tags = "FacultyProfile Management System")
public class FacultyProfileRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private FacultyProfileServiceImpl facultyProfileService;

    @PostMapping("/v1/createFacultyProfile")
    public ResponseEntity<ClientResponseBean> createFacultyProfile(@RequestBody FacultyProfileDto dto) {
        try {
            logger.debug("Creating Faculty: {}", dto.getFirstName());
            FacultyProfile profile = facultyProfileService.createFacultyProfile(dto);
            logger.debug("Faculty created with ID: {}", profile.getId());
            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS", "Faculty successfully created", ""));
        } catch (Exception e) {
            logger.error("Error creating faculty: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                        e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), "")
            );
        }
    }

    @PutMapping("/v1/updateFacultyProfile")
    public ResponseEntity<ClientResponseBean> updateFacultyProfile(@RequestBody FacultyProfileDto dto) {
        try {
            logger.debug("Updating Faculty ID: {}", dto.getId());
            FacultyProfile updated = facultyProfileService.updateFacultyProfile(dto);
            logger.debug("Faculty updated with ID: {}", updated.getId());
            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "Faculty successfully updated", ""));
        } catch (Exception e) {
            logger.error("Error updating faculty: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                        e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), "")
            );
        }
    }

    @DeleteMapping("/v1/deleteFacultyProfileById/{id}")
    public ResponseEntity<ClientResponseBean> deleteFacultyProfileById(@PathVariable long id) {
        try {
            facultyProfileService.deleteFacultyProfileById(id);
            return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "Faculty successfully deleted", ""));
        } catch (Exception e) {
            logger.error("Error deleting faculty: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                        e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), "")
            );
        }
    }

    @GetMapping("/v1/queryFacultyProfileById/{id}")
    public ResponseEntity<FacultyProfileDto> queryFacultyProfileById(@PathVariable long id) {
        FacultyProfileDto dto = facultyProfileService.getFacultyProfileById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/v1/queryAllFacultyProfiles")
    public ResponseEntity<List<FacultyProfileDto>> queryAllFacultyProfiles() {
        List<FacultyProfileDto> list = facultyProfileService.getAllFacultyProfiles();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/v1/getAllFacultyProfilesByPagination/{pageNumber}/{pageSize}")
    public Page<FacultyProfileDto> getAllFacultyProfilesByPagination(@RequestParam int pageNumber,
                                                                      @RequestParam int pageSize) {
        return facultyProfileService.getAllFacultyProfilesByPagination(pageNumber, pageSize);
    }
}
