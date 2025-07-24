package com.ws.spring.restcontroller;

<<<<<<< HEAD
import java.util.List;

import com.ws.spring.dto.BatchDto;
=======
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.BatchDto;
import com.ws.spring.dto.BatchDtoList;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Batch;
import com.ws.spring.service.BatchServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/batch")
@Api(value = "Batch Management System", tags = "Batch Management System")
public class BatchRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private BatchServiceImpl batchService;

<<<<<<< HEAD
    // Create Batch
    @PostMapping("/v1/createBatch")
    public ResponseEntity<ClientResponseBean> createBatch(@RequestBody BatchDto batchDto) {
        try {
            logger.debug("createBatch Name: {}", batchDto.getBatchName());
            Batch batchCreated = batchService.createBatch(batchDto);
            logger.debug("createBatch Id: {}, Name: {}", batchCreated.getBatchId(), batchCreated.getBatchName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    batchCreated.getBatchName() + " batch successfully created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    // Update Batch
    @PutMapping("/v1/updateBatch")
    public ResponseEntity<ClientResponseBean> updateBatch(@RequestBody BatchDto batchDto) {
        try {
            logger.debug("updateBatch Name: {}", batchDto.getBatchName());
            Batch batchUpdated = batchService.updateBatch(batchDto);
            logger.debug("updateBatch Id: {}, Name: {}", batchUpdated.getBatchId(), batchUpdated.getBatchName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    batchUpdated.getBatchName() + " batch successfully updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    // Get single batch by ID
    @GetMapping("/v1/queryBatchByBatchId/{batchId}")
    public ResponseEntity<BatchDto> queryBatchByBatchId(@PathVariable long batchId) {
        BatchDto batchDto = batchService.queryBatchByBatchId(batchId);
        return ResponseEntity.ok().body(batchDto);
    }

    // Get all batches
    @GetMapping("/v1/queryAllBatches")
    public ResponseEntity<List<BatchDto>> queryAllBatches() {
        List<BatchDto> batchList = batchService.queryAllBatches();
        return ResponseEntity.ok().body(batchList);
    }

    // Delete batch by ID
=======
    @PostMapping("/v1/createBatch")
    public ResponseEntity<ClientResponseBean> createBatch(@RequestBody BatchDto batchDto) {
        try {
            logger.debug("createBatch BatchName : {}", batchDto.getBatchName());

            if (batchService.getBatchByName(batchDto.getBatchName()) != null) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Batch Already Exists"));
            }

            Batch batchCreated = batchService.createBatch(batchDto);
            logger.debug("createBatch Id : {}, BatchName: {}", batchCreated.getBatchId(), batchCreated.getBatchName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Batch Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @PutMapping("/v1/updateBatch")
    public ResponseEntity<ClientResponseBean> updateBatch(@RequestBody BatchDto batchDto) {
        try {
            logger.debug("updateBatch BatchName : {}", batchDto.getBatchName());

            Batch batchUpdated = batchService.updateBatch(batchDto);
            logger.debug("updateBatch Id : {}, BatchName: {}", batchUpdated.getBatchId(), batchUpdated.getBatchName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Batch Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

>>>>>>> daccd45 (Initial commit)
    @DeleteMapping("/v1/deleteBatchById/{batchId}")
    public ResponseEntity<ClientResponseBean> deleteBatchById(@PathVariable long batchId) {
        try {
            batchService.deleteBatchById(batchId);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
<<<<<<< HEAD
                    batchId + " batch successfully deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    // Get batches with pagination
    @GetMapping("/v1/getAllBatchesByPagination")
    public Page<BatchDto> getAllBatchesByPagination(@RequestParam int pageNumber,
                                                    @RequestParam int pageSize) {
        return batchService.getAllBatchesByPagination(pageNumber, pageSize);
=======
                    "Batch Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @GetMapping("/v1/queryBatchByBatchId/{batchId}")
    public ResponseEntity<?> queryBatchByBatchId(@PathVariable long batchId) {
        BatchDto batchDto = batchService.getBatchById(batchId);

        if (batchDto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(batchDto);
    }

    @GetMapping("/v1/queryAllBatches")
    public ResponseEntity<List<BatchDtoList>> queryAllBatches() {
        List<BatchDtoList> batchList = batchService.getAllBatches();
        return ResponseEntity.ok().body(batchList);
    }

    @GetMapping("/v1/getAllBatchesByPagination")
    public ResponseEntity<Page<BatchDto>> getAllBatchesByPagination(@RequestParam int pageNumber,
                                                                     @RequestParam int pageSize) {
        Page<BatchDto> page = batchService.getAllBatchesByPagination(pageNumber, pageSize);
        return ResponseEntity.ok().body(page);
>>>>>>> daccd45 (Initial commit)
    }
}
