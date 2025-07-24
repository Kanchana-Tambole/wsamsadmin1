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
import com.ws.spring.dto.CasteDto;
<<<<<<< HEAD
=======
import com.ws.spring.dto.CasteDtoList;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Caste;
import com.ws.spring.service.CasteServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/caste")
@Api(value = "Caste Management System", tags = "Caste Management System")
public class CasteRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CasteServiceImpl casteServiceImpl;

    @PostMapping("/v1/createCaste")
    public ResponseEntity<ClientResponseBean> createCaste(@RequestBody CasteDto casteDto) {
        try {
            logger.debug("createCaste: {}", casteDto.getCasteName());

            Caste created = casteServiceImpl.createCaste(casteDto);
            logger.debug("createCaste Id: {}, Name: {}", created.getCasteId(), created.getCasteName());

            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.CREATED.value(), "SUCCESS", "Caste Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
<<<<<<< HEAD
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
=======
            return ResponseEntity.badRequest().body(
                    ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
>>>>>>> daccd45 (Initial commit)
        }
    }

    @PutMapping("/v1/updateCaste")
    public ResponseEntity<ClientResponseBean> updateCaste(@RequestBody CasteDto casteDto) {
        try {
            logger.debug("updateCaste: {}", casteDto.getCasteName());

            Caste updated = casteServiceImpl.updateCaste(casteDto);
            logger.debug("updateCaste Id: {}, Name: {}", updated.getCasteId(), updated.getCasteName());

            return ResponseEntity.ok().body(new ClientResponseBean(
<<<<<<< HEAD
                    HttpStatus.CREATED.value(), "SUCCESS", "Caste Successfully Updated", ""));
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

    @DeleteMapping("/v1/deleteCasteById/{id}")
    public ResponseEntity<ClientResponseBean> deleteCasteById(@PathVariable long id) {
        try {
            casteServiceImpl.deleteCasteById(id);
=======
                    HttpStatus.OK.value(), "SUCCESS", "Caste Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(
                    ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/v1/deleteCasteById/{casteId}")
    public ResponseEntity<ClientResponseBean> deleteCasteById(@PathVariable long casteId) {
        try {
            casteServiceImpl.deleteCasteById(casteId);
>>>>>>> daccd45 (Initial commit)
            return ResponseEntity.ok().body(new ClientResponseBean(
                    HttpStatus.OK.value(), "SUCCESS", "Caste Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred: {}", e.getMessage(), e);
<<<<<<< HEAD
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @GetMapping("/v1/getCasteById/{id}")
    public ResponseEntity<?> getCasteById(@PathVariable long id) {
        CasteDto casteDto = casteServiceImpl.getCasteById(id);
=======
            return ResponseEntity.badRequest().body(
                    ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/v1/getCasteById/{casteId}")
    public ResponseEntity<?> getCasteById(@PathVariable long casteId) {
        CasteDto casteDto = casteServiceImpl.getCasteById(casteId);
>>>>>>> daccd45 (Initial commit)
        if (casteDto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }
        return ResponseEntity.ok().body(casteDto);
    }

    @GetMapping("/v1/getAllCastes")
<<<<<<< HEAD
    public ResponseEntity<List<CasteDto>> getAllCastes() {
        List<CasteDto> casteList = casteServiceImpl.getAllCastes();
=======
    public ResponseEntity<List<CasteDtoList>> getAllCastes() {
        List<CasteDtoList> casteList = casteServiceImpl.getAllCaste();
>>>>>>> daccd45 (Initial commit)
        return ResponseEntity.ok().body(casteList);
    }

    @GetMapping("/v1/getAllCastesByPagination")
    public Page<CasteDto> getAllCastesByPagination(@RequestParam int pageNumber,
<<<<<<< HEAD
                                                    @RequestParam int pageSize) {
        return casteServiceImpl.getAllCastesByPagination(pageNumber, pageSize);
=======
                                                   @RequestParam int pageSize) {
        return casteServiceImpl.getAllCasteByPagination(pageNumber, pageSize);
>>>>>>> daccd45 (Initial commit)
    }
}
