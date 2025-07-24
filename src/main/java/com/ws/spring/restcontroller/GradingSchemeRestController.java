package com.ws.spring.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.GradingSchemeDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.GradingScheme;
import com.ws.spring.service.GradingSchemeServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/gradingscheme")
@Api(value = "GradingScheme Management System", tags = "GradingScheme Management System")
public class GradingSchemeRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private GradingSchemeServiceImpl gradingSchemeServiceImpl;

    @PostMapping("/v1/createGradingScheme")
    public ResponseEntity<ClientResponseBean> createGradingScheme(@RequestBody GradingSchemeDto dto) {
        try {
            logger.debug("createGradingScheme SchemeName : {}", dto.getSchemeName());

            if (gradingSchemeServiceImpl.getSchemeNameExist(dto.getSchemeName()) != null) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Grading Scheme Already Exist"));
            }

            GradingScheme created = gradingSchemeServiceImpl.createGradingScheme(dto);

            logger.debug("createGradingScheme Id : {}, SchemeName: {}", created.getId(), created.getSchemeName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Grading Scheme Successfully Created", ""));

        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(), ""));
        }
    }

    @PutMapping("/v1/updateGradingScheme")
    public ResponseEntity<ClientResponseBean> updateGradingScheme(@RequestBody GradingSchemeDto dto) {
        try {
            logger.debug("updateGradingScheme SchemeName : {}", dto.getSchemeName());

            GradingScheme updated = gradingSchemeServiceImpl.updateGradingScheme(dto);

            logger.debug("updateGradingScheme Id : {}, SchemeName: {}", updated.getId(), updated.getSchemeName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Grading Scheme Successfully Updated", ""));

        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(), ""));
        }
    }

    @DeleteMapping("/v1/deleteGradingSchemeById/{id}")
    public ResponseEntity<ClientResponseBean> deleteGradingSchemeById(@PathVariable long id) {
        try {
            gradingSchemeServiceImpl.deleteGradingSchemeById(id);

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Grading Scheme successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(), ""));
        }
    }

    @GetMapping("/v1/getGradingSchemeById/{id}")
    public ResponseEntity<?> getGradingSchemeById(@PathVariable long id) {
        GradingSchemeDto dto = gradingSchemeServiceImpl.getGradingSchemeById(id);

        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllGradingSchemes")
    public ResponseEntity<List<GradingSchemeDto>> getAllGradingSchemes() {
        List<GradingSchemeDto> dtoList = gradingSchemeServiceImpl.getAllGradingSchemes();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/v1/getAllGradingSchemesByPagination")
    public Page<GradingSchemeDto> getAllGradingSchemesByPagination(@RequestParam int pageNumber,
                                                                    @RequestParam int pageSize) {
        return gradingSchemeServiceImpl.getAllGradingSchemesByPagination(pageNumber, pageSize);
    }
}
