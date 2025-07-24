package com.ws.spring.restcontroller;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.spring.dto.AcademicYearDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.AcademicYear;
import com.ws.spring.service.AcademicYearServiceImpl;
import com.ws.common.util.ClientResponseUtil;
=======
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.AcademicYearDto;
import com.ws.spring.dto.AcademicYearDtoList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.AcademicYear;
import com.ws.spring.service.AcademicYearServiceImpl;
>>>>>>> daccd45 (Initial commit)

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

@RestController
<<<<<<< HEAD
@RequestMapping("/academicYear")
@Api(value = "Academic Year Management System", tags = "Academic Year Management System")
=======
@RequestMapping("/academicyear")
@Api(value = "AcademicYear Management System", tags = "AcademicYear Management System")
>>>>>>> daccd45 (Initial commit)
public class AcademicYearRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
<<<<<<< HEAD
    private AcademicYearServiceImpl academicYearServiceImpl;
=======
    private AcademicYearServiceImpl academicYearService;
>>>>>>> daccd45 (Initial commit)

    @PostMapping("/v1/createAcademicYear")
    public ResponseEntity<ClientResponseBean> createAcademicYear(@RequestBody AcademicYearDto dto) {
        try {
<<<<<<< HEAD
            logger.debug("createAcademicYear name: {}", dto.getName());

            if (academicYearServiceImpl.getAcademicYearById(dto.getId()) != null) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Academic Year already exists"));
            }

            AcademicYear created = academicYearServiceImpl.createAcademicYear(dto);
            logger.debug("createAcademicYear Id: {}, Name: {}", created.getId(), created.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.CREATED.value(), "SUCCESS", "Academic Year Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ?
                            e.getCause().getCause().getMessage() : e.getMessage(),
                    ""));
=======
            logger.debug("createAcademicYear Name: {}", dto.getName());
            AcademicYear created = academicYearService.createAcademicYear(dto);
            logger.debug("Created AcademicYear Id: {}, Name: {}", created.getId(), created.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Academic Year successfully created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred while creating AcademicYear: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
>>>>>>> daccd45 (Initial commit)
        }
    }

    @PutMapping("/v1/updateAcademicYear")
    public ResponseEntity<ClientResponseBean> updateAcademicYear(@RequestBody AcademicYearDto dto) {
        try {
<<<<<<< HEAD
            logger.debug("updateAcademicYear name: {}", dto.getName());

            AcademicYear updated = academicYearServiceImpl.updateAcademicYear(dto);
            logger.debug("updateAcademicYear Id: {}, Name: {}", updated.getId(), updated.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(), "SUCCESS", "Academic Year Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ?
                            e.getCause().getCause().getMessage() : e.getMessage(),
                    ""));
=======
            logger.debug("updateAcademicYear Name: {}", dto.getName());
            AcademicYear updated = academicYearService.updateAcademicYear(dto);
            logger.debug("Updated AcademicYear Id: {}, Name: {}", updated.getId(), updated.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Academic Year successfully updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred while updating AcademicYear: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
>>>>>>> daccd45 (Initial commit)
        }
    }

    @DeleteMapping("/v1/deleteAcademicYearById/{id}")
    public ResponseEntity<ClientResponseBean> deleteAcademicYearById(@PathVariable long id) {
        try {
<<<<<<< HEAD
            academicYearServiceImpl.deleteAcademicYearById(id);
            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(), "SUCCESS", "Academic Year Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ?
                            e.getCause().getCause().getMessage() : e.getMessage(),
                    ""));
=======
            academicYearService.deleteAcademicYearById(id);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Academic Year successfully deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred while deleting AcademicYear: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(ClientResponseUtil.getExceptionResponse(
                    HttpStatus.BAD_REQUEST.value(), e.getCause() != null ? e.getCause().getMessage() : e.getMessage()));
>>>>>>> daccd45 (Initial commit)
        }
    }

    @GetMapping("/v1/getAcademicYearById/{id}")
    public ResponseEntity<?> getAcademicYearById(@PathVariable long id) {
<<<<<<< HEAD
        AcademicYearDto dto = academicYearServiceImpl.getAcademicYearById(id);
        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllAcademicYears")
    public ResponseEntity<List<AcademicYearDto>> getAllAcademicYears() {
        List<AcademicYearDto> list = academicYearServiceImpl.getAllAcademicYears();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/v1/getAllAcademicYearsByPagination")
    public Page<AcademicYearDto> getAllAcademicYearsByPagination(@RequestParam int pageNumber,
                                                                  @RequestParam int pageSize) {
        return academicYearServiceImpl.getAllAcademicYearsByPagination(pageNumber, pageSize);
=======
        AcademicYearDto dto = academicYearService.getAcademicYearById(id);
        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok(noContentMessage);
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/v1/getAcademicYearByName/{name}")
    public ResponseEntity<?> getAcademicYearByName(@PathVariable String name) {
        AcademicYear year = academicYearService.getAcademicYearByName(name);
        if (year == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok(noContentMessage);
        }
        return ResponseEntity.ok(year);
    }

    @GetMapping("/v1/getAllAcademicYears")
    public ResponseEntity<List<AcademicYearDtoList>> getAllAcademicYears() {
        List<AcademicYearDtoList> yearList = academicYearService.getAllAcademicYears();
        return ResponseEntity.ok(yearList);
    }

    @GetMapping("/v1/getAllAcademicYearsByPagination/{pageNumber}/{pageSize}")
    public Page<AcademicYearDto> getAllAcademicYearsByPagination(@PathVariable int pageNumber,
                                                                 @PathVariable int pageSize) {
        return academicYearService.getAllAcademicYearsByPagination(pageNumber, pageSize);
>>>>>>> daccd45 (Initial commit)
    }
}
