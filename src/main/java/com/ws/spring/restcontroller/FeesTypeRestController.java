package com.ws.spring.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.FeesTypeDto;
import com.ws.spring.dto.FeesTypeDtoList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.FeesType;
import com.ws.spring.service.FeesTypeServiceImpl;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feestype")
@Api(value = "FeesType Management System", tags = "FeesType Management System")
public class FeesTypeRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    FeesTypeServiceImpl feesTypeService;

    // 🔹 Create
    @PostMapping("/v1/createFeesType")
    public ResponseEntity<ClientResponseBean> createFeesType(@RequestBody FeesTypeDto feesTypeDto) {
        try {
            logger.debug("createFeesType Name : {}", feesTypeDto.getName());

            if (null != feesTypeService.getFeesTypeByName(feesTypeDto.getName())) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Fees Type Already Exists"));
            }

            FeesType created = feesTypeService.createFeesType(feesTypeDto);
            logger.debug("createFeesType Id : {}, Name: {}", created.getId(), created.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.CREATED.value(), "SUCCESS", "Fees Type Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED", e.getCause().getCause().getMessage(), ""));
        }
    }

    // 🔹 Update
    @PutMapping("/v1/updateFeesType")
    public ResponseEntity<ClientResponseBean> updateFeesType(@RequestBody FeesTypeDto feesTypeDto) {
        try {
            logger.debug("updateFeesType Name : {}", feesTypeDto.getName());

            FeesType updated = feesTypeService.updateFeesType(feesTypeDto);
            logger.debug("updateFeesType Id : {}, Name: {}", updated.getId(), updated.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(), "SUCCESS", "Fees Type Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED", e.getCause().getCause().getMessage(), ""));
        }
    }

    // 🔹 Delete
    @DeleteMapping("/v1/deleteFeesTypeById/{id}")
    public ResponseEntity<ClientResponseBean> deleteFeesTypeById(@PathVariable long id) {
        try {
            feesTypeService.deleteFeesTypeById(id);
            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(), "SUCCESS", "Fees Type Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED", e.getCause().getCause().getMessage(), ""));
        }
    }

    // 🔹 Get By ID
    @GetMapping("/v1/getFeesTypeById/{id}")
    public ResponseEntity<?> getFeesTypeById(@PathVariable long id) {
        FeesTypeDto dto = feesTypeService.getFeesTypeById(id);
        if (null == dto) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }
        return ResponseEntity.ok().body(dto);
    }

    // 🔹 Get All (List)
    @GetMapping("/v1/getAllFeesType")
    public ResponseEntity<List<FeesTypeDtoList>> getAllFeesType() {
        List<FeesTypeDtoList> list = feesTypeService.getAllFeesType();
        return ResponseEntity.ok().body(list);
    }

    // 🔹 Get All (Paginated)
    @GetMapping("/v1/getAllFeesTypeByPagination")
    public Page<FeesTypeDto> getAllFeesTypeByPagination(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        return feesTypeService.getAllFeesTypeByPagination(pageNumber, pageSize);
    }

}
