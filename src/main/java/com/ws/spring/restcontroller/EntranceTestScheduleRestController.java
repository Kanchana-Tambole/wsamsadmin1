package com.ws.spring.restcontroller;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.EntranceTestScheduleDto;
import com.ws.spring.dto.EntranceTestScheduleDtoList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.EntranceTestSchedule;
import com.ws.spring.service.EntranceTestScheduleServiceImpl;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/entrancetest")
@Api(value = "EntranceTestSchedule Management System", tags = "EntranceTestSchedule Management")
public class EntranceTestScheduleRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private EntranceTestScheduleServiceImpl entranceTestScheduleServiceImpl;

    @PostMapping("/v1/createEntranceTestSchedule")
    public ResponseEntity<ClientResponseBean> createEntranceTestSchedule(@RequestBody EntranceTestScheduleDto dto) {
        try {
            logger.debug("Creating EntranceTestSchedule: {}", dto);
            EntranceTestSchedule created = entranceTestScheduleServiceImpl.createEntranceTestSchedule(dto);

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.CREATED.value(),
                    "SUCCESS",
                    "Entrance Test Schedule Created Successfully",
                    ""));
        } catch (Exception e) {
            logger.error("Error creating EntranceTestSchedule: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    e.getCause() != null && e.getCause().getCause() != null ?
                            e.getCause().getCause().getMessage() : e.getMessage()));
        }
    }

    @PutMapping("/v1/updateEntranceTestSchedule")
    public ResponseEntity<ClientResponseBean> updateEntranceTestSchedule(@RequestBody EntranceTestScheduleDto dto) {
        try {
            logger.debug("Updating EntranceTestSchedule ID: {}", dto.getId());
            EntranceTestSchedule updated = entranceTestScheduleServiceImpl.updateEntranceTestSchedule(dto);

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(),
                    "SUCCESS",
                    "Entrance Test Schedule Updated Successfully",
                    ""));
        } catch (Exception e) {
            logger.error("Error updating EntranceTestSchedule: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    e.getCause() != null && e.getCause().getCause() != null ?
                            e.getCause().getCause().getMessage() : e.getMessage()));
        }
    }

    @DeleteMapping("/v1/deleteEntranceTestScheduleById/{id}")
    public ResponseEntity<ClientResponseBean> deleteEntranceTestScheduleById(@PathVariable Long id) {
        try {
            entranceTestScheduleServiceImpl.deleteEntranceTestScheduleById(id);
            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(),
                    "SUCCESS",
                    "Entrance Test Schedule Deleted Successfully",
                    ""));
        } catch (Exception e) {
            logger.error("Error deleting EntranceTestSchedule: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    e.getCause() != null && e.getCause().getCause() != null ?
                            e.getCause().getCause().getMessage() : e.getMessage()));
        }
    }

    @GetMapping("/v1/getEntranceTestScheduleById/{id}")
    public ResponseEntity<?> getEntranceTestScheduleById(@PathVariable Long id) {
        EntranceTestScheduleDto dto = entranceTestScheduleServiceImpl.getEntranceTestById(id);

        if (dto == null) {
            Map<String, String> noContent = new HashMap<>();
            noContent.put("message", "Entrance Test Schedule not found");
            return ResponseEntity.ok().body(noContent);
        }

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllEntranceTestSchedules")
    public ResponseEntity<List<EntranceTestScheduleDtoList>> getAllEntranceTestSchedules() {
        List<EntranceTestScheduleDtoList> list = entranceTestScheduleServiceImpl.getAllEntranceTests();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/v1/getAllEntranceTestSchedulesByPagination")
    public Page<EntranceTestScheduleDto> getAllEntranceTestSchedulesByPagination(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {

        return entranceTestScheduleServiceImpl.getAllEntranceTestsByPagination(pageNumber, pageSize);
    }
}
