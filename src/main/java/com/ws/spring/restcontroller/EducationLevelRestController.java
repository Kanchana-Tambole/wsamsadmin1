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

import org.springframework.web.bind.annotation.*;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.EducationLevelDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.EducationLevel;
import com.ws.spring.service.EducationLevelServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/educationlevel")
@Api(value = "EducationLevel Management System", tags = "EducationLevel Management System")
public class EducationLevelRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private EducationLevelServiceImpl educationLevelServiceImpl;

    @PostMapping("/v1/createEducationLevel")
    public ResponseEntity<ClientResponseBean> createEducationLevel(@RequestBody EducationLevelDto dto) {
        try {
            logger.debug("createEducationLevel LevelName : {}", dto.getLevelName());

            if (educationLevelServiceImpl.getLevelNameExist(dto.getLevelName()) != null) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Education Level Already Exist"));
            }

            EducationLevel created = educationLevelServiceImpl.createEducationLevel(dto);

            logger.debug("createEducationLevel Id : {}, LevelName: {}", created.getId(), created.getLevelName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Education Level Successfully Created", ""));

        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }


    @PutMapping("/v1/updateEducationLevel")
    public ResponseEntity<ClientResponseBean> updateEducationLevel(@RequestBody EducationLevelDto dto) {
        try {
            logger.debug("updateEducationLevel LevelName : {}", dto.getLevelName());

            EducationLevel updated = educationLevelServiceImpl.updateEducationLevel(dto);

            logger.debug("updateEducationLevel Id : {}, LevelName: {}", updated.getId(), updated.getLevelName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Education Level Successfully Updated", ""));

        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @DeleteMapping("/v1/deleteEducationLevelById/{id}")
    public ResponseEntity<ClientResponseBean> deleteEducationLevelById(@PathVariable long id) {
        try {
            educationLevelServiceImpl.deleteEducationLevelById(id);

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Education Level successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @GetMapping("/v1/getEducationLevelById/{id}")
    public ResponseEntity<?> getEducationLevelById(@PathVariable long id) {
        EducationLevelDto dto = educationLevelServiceImpl.getEducationLevelById(id);

        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllEducationLevels")
    public ResponseEntity<List<EducationLevelDto>> getAllEducationLevels() {
        List<EducationLevelDto> dtoList = educationLevelServiceImpl.getAllEducationLevels();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/v1/getAllEducationLevelsByPagination")
    public Page<EducationLevelDto> getAllEducationLevelsByPagination(@RequestParam int pageNumber,
                                                                      @RequestParam int pageSize) {
        return educationLevelServiceImpl.getAllEducationLevelsByPagination(pageNumber, pageSize);
    }
}
