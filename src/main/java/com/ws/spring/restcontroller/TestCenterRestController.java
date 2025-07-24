package com.ws.spring.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.TestCenterDto;
import com.ws.spring.dto.TestCenterDtoList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.TestCenter;
import com.ws.spring.service.TestCenterServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/testcenter")
@Api(value = "TestCenter Management System", tags = "TestCenter Management System")
public class TestCenterRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private TestCenterServiceImpl testCenterServiceImpl;

    @PostMapping("/v1/createTestCenter")
    public ResponseEntity<ClientResponseBean> createTestCenter(@RequestBody TestCenterDto centerDto) {
        try {
            logger.debug("createTestCenter centerName: {}", centerDto.getCenterName());

            if (testCenterServiceImpl.getCenterNameExist(centerDto.getCenterName()) != null) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Center Already Exists"));
            }

            TestCenter created = testCenterServiceImpl.createTestCenter(centerDto);
            logger.debug("createTestCenter Id: {}, Name: {}", created.getId(), created.getCenterName());

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.CREATED.value(), "SUCCESS", "Test Center Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @PutMapping("/v1/updateTestCenter")
    public ResponseEntity<ClientResponseBean> updateTestCenter(@RequestBody TestCenterDto centerDto) {
        try {
            logger.debug("updateTestCenter centerName: {}", centerDto.getCenterName());

            TestCenter updated = testCenterServiceImpl.updateTestCenter(centerDto);
            logger.debug("updateTestCenter Id: {}, Name: {}", updated.getId(), updated.getCenterName());

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.CREATED.value(), "SUCCESS", "Test Center Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @DeleteMapping("/v1/deleteTestCenterById/{centerId}")
    public ResponseEntity<ClientResponseBean> deleteTestCenterById(@PathVariable long centerId) {
        try {
            testCenterServiceImpl.deleteTestCenterById(centerId);
            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(), "SUCCESS", "Test Center successfully deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @GetMapping("/v1/getTestCenterById/{centerId}")
    public ResponseEntity<?> getTestCenterById(@PathVariable long centerId) {
        TestCenterDto dto = testCenterServiceImpl.getTestCenterById(centerId);

        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllTestCenters")
    public ResponseEntity<List<TestCenterDtoList>> getAllTestCenters() {
        List<TestCenterDtoList> centers = testCenterServiceImpl.getAllTestCenters();
        return ResponseEntity.ok().body(centers);
    }

    @GetMapping("/v1/getAllTestCentersByPagination")
    public Page<TestCenterDto> getAllTestCentersByPagination(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        return testCenterServiceImpl.getAllTestCentersByPagination(pageNumber, pageSize);
    }
}
