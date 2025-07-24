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

import com.ws.spring.dto.ScholarshipSchemeDto;
import com.ws.spring.dto.ScholarshipSchemeDtoList;

import com.ws.spring.exception.ClientResponseBean;

import com.ws.spring.model.ScholarshipScheme;

import com.ws.spring.service.ScholarshipSchemeServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/scholarshipscheme")
@Api(value = "Scholarship Scheme Management System", tags = "Scholarship Scheme Management System")
public class ScholarshipSchemeRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private ScholarshipSchemeServiceImpl scholarshipSchemeService;

    @PostMapping("/v1/createScholarshipScheme")
    public ResponseEntity<ClientResponseBean> createScholarshipScheme(@RequestBody ScholarshipSchemeDto dto) {
        try {
            logger.debug("createScholarshipScheme SchemeName : {}", dto.getSchemeName());

            if (null != scholarshipSchemeService.getSchemeByName(dto.getSchemeName())) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Scholarship Scheme Already Exists"));
            }

            ScholarshipScheme created = scholarshipSchemeService.createScholarshipScheme(dto);

            logger.debug("createScholarshipScheme Id : {}, Name: {}", created.getId(), created.getSchemeName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Scholarship Scheme Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : "Unknown error", ""));
        }
    }

    @PutMapping("/v1/updateScholarshipScheme")
    public ResponseEntity<ClientResponseBean> updateScholarshipScheme(@RequestBody ScholarshipSchemeDto dto) {
        try {
            logger.debug("updateScholarshipScheme SchemeName : {}", dto.getSchemeName());

            ScholarshipScheme updated = scholarshipSchemeService.updateScholarshipScheme(dto);

            logger.debug("updateScholarshipScheme Id : {}, Name: {}", updated.getId(), updated.getSchemeName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Scholarship Scheme Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : "Unknown error", ""));
        }
    }

    @DeleteMapping("/v1/deleteScholarshipSchemeById/{id}")
    public ResponseEntity<ClientResponseBean> deleteScholarshipSchemeById(@PathVariable long id) {
        try {
            scholarshipSchemeService.deleteScholarshipSchemeById(id);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Scholarship Scheme Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : "Unknown error", ""));
        }
    }

    @GetMapping("/v1/getScholarshipSchemeById/{id}")
    public ResponseEntity<?> getScholarshipSchemeById(@PathVariable long id) {
        ScholarshipSchemeDto dto = scholarshipSchemeService.getScholarshipSchemeById(id);
        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllScholarshipSchemes")
    public ResponseEntity<List<ScholarshipSchemeDtoList>> getAllScholarshipSchemes() {
        List<ScholarshipSchemeDtoList> dtos = scholarshipSchemeService.getAllScholarshipSchemes();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/v1/getAllScholarshipSchemesByPagination")
    public Page<ScholarshipSchemeDto> getAllScholarshipSchemesByPagination(@RequestParam int pageNumber,
                                                                            @RequestParam int pageSize) {
        return scholarshipSchemeService.getAllScholarshipSchemesByPagination(pageNumber, pageSize);
    }
}
