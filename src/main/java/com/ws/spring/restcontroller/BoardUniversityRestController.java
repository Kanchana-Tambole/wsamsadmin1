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
import com.ws.spring.dto.BoardUniversityDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.BoardUniversity;
import com.ws.spring.service.BoardUniversityServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/boarduniversity")
@Api(value = "Board/University Management System", tags = "Board/University Management System")
public class BoardUniversityRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private BoardUniversityServiceImpl boardUniversityServiceImpl;

    @PostMapping("/v1/createBoardUniversity")
    public ResponseEntity<ClientResponseBean> createBoardUniversity(@RequestBody BoardUniversityDto dto) {
        try {
            logger.debug("createBoardUniversity Name : {}", dto.getName());

            if (boardUniversityServiceImpl.getBoardUniversityByName(dto.getName()) != null) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Board/University Already Exists"));
            }

            BoardUniversity created = boardUniversityServiceImpl.createBoardUniversity(dto);

            logger.debug("createBoardUniversity Id : {}, Name: {}", created.getId(), created.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Board/University Successfully Created", ""));

        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @PutMapping("/v1/updateBoardUniversity")
    public ResponseEntity<ClientResponseBean> updateBoardUniversity(@RequestBody BoardUniversityDto dto) {
        try {
            logger.debug("updateBoardUniversity Name : {}", dto.getName());

            BoardUniversity updated = boardUniversityServiceImpl.updateBoardUniversity(dto);

            logger.debug("updateBoardUniversity Id : {}, Name: {}", updated.getId(), updated.getName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Board/University Successfully Updated", ""));

        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @DeleteMapping("/v1/deleteBoardUniversityById/{id}")
    public ResponseEntity<ClientResponseBean> deleteBoardUniversityById(@PathVariable long id) {
        try {
            boardUniversityServiceImpl.deleteBoardUniversityById(id);

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Board/University Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @GetMapping("/v1/getBoardUniversityById/{id}")
    public ResponseEntity<?> getBoardUniversityById(@PathVariable long id) {
        BoardUniversityDto dto = boardUniversityServiceImpl.getBoardUniversityById(id);

        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllBoardUniversities")
    public ResponseEntity<List<BoardUniversityDto>> getAllBoardUniversities() {
        List<BoardUniversityDto> dtoList = boardUniversityServiceImpl.getAllBoardUniversities();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/v1/getAllBoardUniversitiesByPagination")
    public Page<BoardUniversityDto> getAllBoardUniversitiesByPagination(@RequestParam int pageNumber,
                                                                         @RequestParam int pageSize) {
        return boardUniversityServiceImpl.getAllBoardUniversitiesByPagination(pageNumber, pageSize);
    }
}
