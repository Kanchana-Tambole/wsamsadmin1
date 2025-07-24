package com.ws.spring.restcontroller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.ReligionDto;
<<<<<<< HEAD
=======
import com.ws.spring.dto.ReligionDtoList;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Religion;
import com.ws.spring.service.ReligionServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/religion")
@Api(value = "Religion Management System", tags = "Religion Management System")
public class ReligionRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
<<<<<<< HEAD
    ReligionServiceImpl religionService;
=======
    ReligionServiceImpl religionServiceImpl;
>>>>>>> daccd45 (Initial commit)

    @PostMapping("/v1/createReligion")
    public ResponseEntity<ClientResponseBean> createReligion(@RequestBody ReligionDto religionDto) {
        try {
<<<<<<< HEAD
            logger.debug("createReligion religionName : {}", religionDto.getReligionName()); // ✅ consistent logging

            if (religionService.getReligionNameExist(religionDto.getReligionName()) != null) {
=======
            logger.debug("createReligion religionName : {}", religionDto.getReligionName());

            if (religionServiceImpl.getReligionNameExist(religionDto.getReligionName()) != null) {
>>>>>>> daccd45 (Initial commit)
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Religion Already Exist"));
            }

<<<<<<< HEAD
            Religion created = religionService.createReligion(religionDto);
            logger.debug("createReligion Id : {}, Name: {}", created.getReligionId(), created.getReligionName()); // ✅ log output
=======
            Religion religionCreated = religionServiceImpl.createReligion(religionDto);
            logger.debug("createReligion Id : {}, religionName: {}", religionCreated.getReligionId(), religionCreated.getReligionName());
>>>>>>> daccd45 (Initial commit)

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.CREATED.value(), "SUCCESS", "Religion Successfully Created", ""));
        } catch (Exception e) {
<<<<<<< HEAD
            logger.error("Exception occurred : {}", e.getMessage(), e); // ✅ log exception with trace
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(),
                    "")); // ✅ safer null-check like in BloodGroupRestController
=======
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    (e.getCause() != null && e.getCause().getCause() != null) ? e.getCause().getCause().getMessage() : e.getMessage(),
                    ""));
>>>>>>> daccd45 (Initial commit)
        }
    }

    @PutMapping("/v1/updateReligion")
    public ResponseEntity<ClientResponseBean> updateReligion(@RequestBody ReligionDto religionDto) {
        try {
<<<<<<< HEAD
            logger.debug("updateReligion religionName : {}", religionDto.getReligionName()); // ✅ add debug logging

            Religion updated = religionService.updateReligion(religionDto);
            logger.debug("updateReligion Id : {}, Name: {}", updated.getReligionId(), updated.getReligionName());
=======
            logger.debug("updateReligion religionName : {}", religionDto.getReligionName());

            Religion religionUpdated = religionServiceImpl.updateReligion(religionDto);
            logger.debug("updateReligion Id : {}, religionName: {}", religionUpdated.getReligionId(), religionUpdated.getReligionName());
>>>>>>> daccd45 (Initial commit)

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(), "SUCCESS", "Religion Successfully Updated", ""));
        } catch (Exception e) {
<<<<<<< HEAD
            logger.error("Exception occurred : {}", e.getMessage(), e); // ✅ consistent error handling
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(),
=======
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    (e.getCause() != null && e.getCause().getCause() != null) ? e.getCause().getCause().getMessage() : e.getMessage(),
>>>>>>> daccd45 (Initial commit)
                    ""));
        }
    }

    @DeleteMapping("/v1/deleteReligionById/{religionId}")
    public ResponseEntity<ClientResponseBean> deleteReligionById(@PathVariable long religionId) {
        try {
<<<<<<< HEAD
            religionService.deleteReligionById(religionId);
            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(), "SUCCESS", "Religion Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e); // ✅ proper exception trace
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(),
=======
            religionServiceImpl.deleteReligionById(religionId);
            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(), "SUCCESS", "Religion Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    (e.getCause() != null && e.getCause().getCause() != null) ? e.getCause().getCause().getMessage() : e.getMessage(),
>>>>>>> daccd45 (Initial commit)
                    ""));
        }
    }

    @GetMapping("/v1/getReligionById/{religionId}")
    public ResponseEntity<?> getReligionById(@PathVariable long religionId) {
<<<<<<< HEAD
        ReligionDto dto = religionService.getReligionById(religionId);

        if (dto == null) {
            Map<String, String> noContent = new HashMap<>();
            noContent.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContent); // ✅ same no-content logic as in BloodGroup
        }

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllReligions")
    public ResponseEntity<List<ReligionDto>> getAllReligions() {
        List<ReligionDto> religionList = religionService.getAllReligions(); // ✅ new method added in service
        return ResponseEntity.ok().body(religionList);
=======
        ReligionDto religionDto = religionServiceImpl.getReligionById(religionId);

        if (religionDto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(religionDto);
    }

    @GetMapping("/v1/getAllReligion")
    public ResponseEntity<List<ReligionDto>> getAllReligion() {
        List<ReligionDto> religionDtoList = religionServiceImpl.getAllReligions();
        return ResponseEntity.ok().body(religionDtoList);
>>>>>>> daccd45 (Initial commit)
    }

    @GetMapping("/v1/getAllReligionByPagination")
    public Page<ReligionDto> getAllReligionByPagination(@RequestParam int pageNumber,
                                                        @RequestParam int pageSize) {
<<<<<<< HEAD
        return religionService.getAllReligionByPagination(pageNumber, pageSize); // ✅ query param style
=======
        return religionServiceImpl.getAllReligionByPagination(pageNumber, pageSize);
>>>>>>> daccd45 (Initial commit)
    }
}
