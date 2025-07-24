package com.ws.spring.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.DisabilityTypeDto;
import com.ws.spring.dto.DisabilityTypeDtoList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.DisabilityType;
import com.ws.spring.service.DisabilityTypeServiceImpl;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disabilitytype")
@Api(value = "DisabilityType Management System", tags = "DisabilityType Management System")
public class DisabilityTypeRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    DisabilityTypeServiceImpl disabilityTypeServiceImpl;

    @PostMapping("/v1/createDisabilityType")
    public ResponseEntity<ClientResponseBean> createDisabilityType(@RequestBody DisabilityTypeDto dto) {
        try {
            logger.debug("createDisabilityType Name : {}", dto.getName());

            if (disabilityTypeServiceImpl.getDisabilityTypeByName(dto.getName()) != null) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                        "Disability Type Already Exist"));
            }

            DisabilityType created = disabilityTypeServiceImpl.createDisabilityType(dto);
            logger.debug("createDisabilityType Id : {}, Name: {}", created.getId(), created.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Disability Type Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getMessage(), ""));
        }
    }

    @PutMapping("/v1/updateDisabilityType")
    public ResponseEntity<ClientResponseBean> updateDisabilityType(@RequestBody DisabilityTypeDto dto) {
        try {
            logger.debug("updateDisabilityType Name : {}", dto.getName());

            DisabilityType updated = disabilityTypeServiceImpl.updateDisabilityType(dto);
            logger.debug("updateDisabilityType Id : {}, Name: {}", updated.getId(), updated.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Disability Type Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getMessage(), ""));
        }
    }

    @DeleteMapping("/v1/deleteDisabilityTypeById/{id}")
    public ResponseEntity<ClientResponseBean> deleteDisabilityTypeById(@PathVariable long id) {
        try {
            disabilityTypeServiceImpl.deleteDisabilityTypeById(id);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Disability Type Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getMessage(), ""));
        }
    }

    @GetMapping("/v1/getDisabilityTypeById/{id}")
    public ResponseEntity<?> getDisabilityTypeById(@PathVariable long id) {
        DisabilityTypeDto dto = disabilityTypeServiceImpl.getDisabilityTypeById(id);
        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllDisabilityTypes")
    public ResponseEntity<List<DisabilityTypeDtoList>> getAllDisabilityTypes() {
        List<DisabilityTypeDtoList> list = disabilityTypeServiceImpl.getAllDisabilityTypes();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/v1/getAllDisabilityTypesByPagination")
    public Page<DisabilityTypeDto> getAllDisabilityTypesByPagination(@RequestParam int pageNumber,
                                                                      @RequestParam int pageSize) {
        return disabilityTypeServiceImpl.getAllDisabilityTypesByPagination(pageNumber, pageSize);
    }
}
