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
import com.ws.spring.dto.BloodGroupDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.BloodGroup;
import com.ws.spring.service.BloodGroupServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/bloodgroup")
@Api(value = "BloodGroup Management System", tags = "BloodGroup Management System")
public class BloodGroupRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    BloodGroupServiceImpl bloodGroupServiceImpl;

    @PostMapping("/v1/createBloodGroup")
    public ResponseEntity<ClientResponseBean> createBloodGroup(@RequestBody BloodGroupDto groupDto) {
        try {
            logger.debug("createBloodGroup BloodGroup: {}", groupDto.getBloodGroup());

            if (null != bloodGroupServiceImpl.getBloodGroupByName(groupDto.getBloodGroup())) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Blood Group Already Exists"));
            }

            BloodGroup created = bloodGroupServiceImpl.createBloodGroup(groupDto);
            logger.debug("createBloodGroup Id: {}, BloodGroup: {}", created.getId(), created.getBloodGroup());

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.CREATED.value(), "SUCCESS", "Blood Group Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(),
                    ""));
        }
    }

    @PutMapping("/v1/updateBloodGroup")
    public ResponseEntity<ClientResponseBean> updateBloodGroup(@RequestBody BloodGroupDto groupDto) {
        try {
            logger.debug("updateBloodGroup BloodGroup: {}", groupDto.getBloodGroup());

            BloodGroup updated = bloodGroupServiceImpl.updateBloodGroup(groupDto);
            logger.debug("updateBloodGroup Id: {}, BloodGroup: {}", updated.getId(), updated.getBloodGroup());

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.CREATED.value(), "SUCCESS", "Blood Group Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(),
                    ""));
        }
    }

    @DeleteMapping("/v1/deleteBloodGroupById/{id}")
    public ResponseEntity<ClientResponseBean> deleteBloodGroupById(@PathVariable long id) {
        try {
            bloodGroupServiceImpl.deleteBloodGroupById(id);
            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(), "SUCCESS", "Blood Group Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(),
                    ""));
        }
    }

    @GetMapping("/v1/getBloodGroupById/{id}")
    public ResponseEntity<?> getBloodGroupById(@PathVariable long id) {
        BloodGroupDto groupDto = bloodGroupServiceImpl.getBloodGroupById(id);
        if (groupDto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }
        return ResponseEntity.ok().body(groupDto);
    }

    @GetMapping("/v1/getAllBloodGroups")
    public ResponseEntity<List<BloodGroupDto>> getAllBloodGroups() {
        List<BloodGroupDto> groupList = bloodGroupServiceImpl.getAllBloodGroups();
        return ResponseEntity.ok().body(groupList);
    }

    @GetMapping("/v1/getAllBloodGroupsByPagination")
    public Page<BloodGroupDto> getAllBloodGroupsByPagination(@RequestParam int pageNumber,
                                                              @RequestParam int pageSize) {
        return bloodGroupServiceImpl.getAllBloodGroupsByPagination(pageNumber, pageSize);
    }
}
