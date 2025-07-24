package com.ws.spring.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.spring.dto.SubjectDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Subject;
import com.ws.spring.service.SubjectServiceImpl;
import com.ws.common.util.ClientResponseUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/subject")
@Api(value = "Subject Management System", tags = "Subject Management System")
public class SubjectRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private SubjectServiceImpl subjectServiceImpl;

    @PostMapping("/v1/createSubject")
    public ResponseEntity<ClientResponseBean> createSubject(@RequestBody SubjectDto dto) {
        try {
            logger.debug("createSubject subjectName : {}", dto.getSubjectName());

            if (subjectServiceImpl.getSubjectByName(dto.getSubjectName()) != null) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Subject Already Exists"));
            }

            Subject created = subjectServiceImpl.createSubject(dto);

            logger.debug("createSubject Id : {}, subjectName: {}", created.getId(), created.getSubjectName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Subject Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? 
                        e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @PutMapping("/v1/updateSubject")
    public ResponseEntity<ClientResponseBean> updateSubject(@RequestBody SubjectDto dto) {
        try {
            logger.debug("updateSubject subjectName : {}", dto.getSubjectName());

            Subject updated = subjectServiceImpl.updateSubject(dto);

            logger.debug("updateSubject Id : {}, subjectName: {}", updated.getId(), updated.getSubjectName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Subject Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ?
                        e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @DeleteMapping("/v1/deleteSubjectById/{id}")
    public ResponseEntity<ClientResponseBean> deleteSubjectById(@PathVariable long id) {
        try {
            subjectServiceImpl.deleteSubjectById(id);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Subject Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ?
                        e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @GetMapping("/v1/getSubjectById/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable long id) {
        SubjectDto dto = subjectServiceImpl.getSubjectById(id);

        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllSubjects")
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        List<SubjectDto> dtoList = subjectServiceImpl.getAllSubjects();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/v1/getAllSubjectsByPagination")
    public Page<SubjectDto> getAllSubjectsByPagination(@RequestParam int pageNumber,
                                                       @RequestParam int pageSize) {
        return subjectServiceImpl.getAllSubjectsByPagination(pageNumber, pageSize);
    }
}
