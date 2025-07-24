package com.ws.spring.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.TestTypeDto;
import com.ws.spring.dto.TestTypeDtoList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.TestType;
import com.ws.spring.service.TestTypeServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/testtype")
@Api(value = "TestType Management System", tags = "TestType Management System")
public class TestTypeRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private TestTypeServiceImpl testTypeServiceImpl;

    @PostMapping("/v1/createTestType")
    public ResponseEntity<ClientResponseBean> createTestType(@RequestBody TestTypeDto testTypeDto) {
        try {
            logger.debug("createTestType TestName : {}", testTypeDto.getTestName());

            if (null != testTypeServiceImpl.getTestTypeByName(testTypeDto.getTestName())) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "TestType Already Exists"));
            }

            TestType created = testTypeServiceImpl.createTestType(testTypeDto);

            logger.debug("TestType created with ID : {}, Name: {}", created.getId(), created.getTestName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "TestType Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred while creating TestType: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @PutMapping("/v1/updateTestType")
    public ResponseEntity<ClientResponseBean> updateTestType(@RequestBody TestTypeDto testTypeDto) {
        try {
            logger.debug("updateTestType TestName : {}", testTypeDto.getTestName());

            TestType updated = testTypeServiceImpl.updateTestType(testTypeDto);

            logger.debug("TestType updated with ID : {}, Name: {}", updated.getId(), updated.getTestName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "TestType Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred while updating TestType: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @DeleteMapping("/v1/deleteTestTypeById/{id}")
    public ResponseEntity<ClientResponseBean> deleteTestTypeById(@PathVariable long id) {
        try {
            testTypeServiceImpl.deleteTestTypeById(id);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "TestType Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred while deleting TestType: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @GetMapping("/v1/getTestTypeById/{id}")
    public ResponseEntity<?> getTestTypeById(@PathVariable long id) {
        TestTypeDto dto = testTypeServiceImpl.getTestTypeById(id);

        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllTestTypes")
    public ResponseEntity<List<TestTypeDtoList>> getAllTestTypes() {
        List<TestTypeDtoList> list = testTypeServiceImpl.getAllTestTypes();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/v1/getAllTestTypesByPagination")
    public Page<TestTypeDto> getAllTestTypesByPagination(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return testTypeServiceImpl.getAllTestTypesByPagination(pageNumber, pageSize);
    }
}
