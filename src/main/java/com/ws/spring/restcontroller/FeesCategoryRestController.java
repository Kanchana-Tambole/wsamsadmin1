package com.ws.spring.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.FeesCategoryDto;
import com.ws.spring.dto.FeesCategoryDtoList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.FeesCategory;
import com.ws.spring.service.FeesCategoryServiceImpl;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feescategory")
@Api(value = "FeesCategory Management System", tags = "FeesCategory Management System")
public class FeesCategoryRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private FeesCategoryServiceImpl feesCategoryServiceImpl;

    @PostMapping("/v1/createFeesCategory")
    public ResponseEntity<ClientResponseBean> createFeesCategory(@RequestBody FeesCategoryDto categoryDto) {
        try {
            logger.debug("createFeesCategory CategoryName : {}", categoryDto.getName());

            if (null != feesCategoryServiceImpl.getCategoryByName(categoryDto.getName())) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Category Already Exist"));
            }

            FeesCategory created = feesCategoryServiceImpl.createFeesCategory(categoryDto);

            logger.debug("createFeesCategory Id : {}, CategoryName: {}", created.getId(), created.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Category Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? 
                        e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @PutMapping("/v1/updateFeesCategory")
    public ResponseEntity<ClientResponseBean> updateFeesCategory(@RequestBody FeesCategoryDto categoryDto) {
        try {
            logger.debug("updateFeesCategory CategoryName : {}", categoryDto.getName());

            FeesCategory updated = feesCategoryServiceImpl.updateFeesCategory(categoryDto);

            logger.debug("updateFeesCategory Id : {}, CategoryName: {}", updated.getId(), updated.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Category Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? 
                        e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @DeleteMapping("/v1/deleteFeesCategoryById/{categoryId}")
    public ResponseEntity<ClientResponseBean> deleteFeesCategoryById(@PathVariable long categoryId) {
        try {
            feesCategoryServiceImpl.deleteFeesCategoryById(categoryId);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Category Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? 
                        e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @GetMapping("/v1/getFeesCategoryById/{categoryId}")
    public ResponseEntity<?> getFeesCategoryById(@PathVariable long categoryId) {
        FeesCategoryDto categoryDto = feesCategoryServiceImpl.getFeesCategoryById(categoryId);

        if (categoryDto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(categoryDto);
    }

    @GetMapping("/v1/getAllFeesCategories")
    public ResponseEntity<List<FeesCategoryDtoList>> getAllFeesCategories() {
        List<FeesCategoryDtoList> categoryDtos = feesCategoryServiceImpl.getAllFeesCategories();
        return ResponseEntity.ok().body(categoryDtos);
    }

    @GetMapping("/v1/getAllFeesCategoriesByPagination")
    public Page<FeesCategoryDto> getAllFeesCategoriesByPagination(@RequestParam int pageNumber,
                                                                   @RequestParam int pageSize) {
        return feesCategoryServiceImpl.getAllFeesCategoriesByPagination(pageNumber, pageSize);
    }
}
