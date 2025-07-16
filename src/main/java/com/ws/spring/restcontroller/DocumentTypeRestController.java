package com.ws.spring.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.ws.spring.dto.DocumentTypeDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.DocumentType;
import com.ws.spring.service.DocumentTypeServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/documentType")
@Api(value = "Document Type Management System", tags = "Document Type Management System")
public class DocumentTypeRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private DocumentTypeServiceImpl documentTypeServiceImpl;

    @PostMapping("/v1/createDocumentType")
    public ResponseEntity<ClientResponseBean> createDocumentType(@RequestBody DocumentTypeDto dto) {
        try {
            logger.debug("createDocumentType dto: {}", dto);
            DocumentType saved = documentTypeServiceImpl.createDocumentType(dto);
            logger.debug("DocumentType Created: ID={}, TypeCode={}", saved.getDocumentTypeId(), saved.getTypeCode());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Document Type Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred during createDocumentType: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @PutMapping("/v1/updateDocumentType")
    public ResponseEntity<ClientResponseBean> updateDocumentType(@RequestBody DocumentTypeDto dto) {
        try {
            logger.debug("updateDocumentType dto: {}", dto);
            DocumentType updated = documentTypeServiceImpl.updateDocumentType(dto);
            logger.debug("DocumentType Updated: ID={}, TypeCode={}", updated.getDocumentTypeId(), updated.getTypeCode());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Document Type Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred during updateDocumentType: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @DeleteMapping("/v1/deleteDocumentTypeById/{documentTypeId}")
    public ResponseEntity<ClientResponseBean> deleteDocumentTypeById(@PathVariable long documentTypeId) {
        try {
            documentTypeServiceImpl.deleteDocumentTypeById(documentTypeId);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Document Type Successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred during deleteDocumentTypeById: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null ? e.getCause().getCause().getMessage() : e.getMessage(), ""));
        }
    }

    @GetMapping("/v1/getDocumentTypeById/{documentTypeId}")
    public ResponseEntity<DocumentTypeDto> getDocumentTypeById(@PathVariable long documentTypeId) {
        DocumentTypeDto dto = documentTypeServiceImpl.getDocumentTypeById(documentTypeId);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/queryAllDocumentType")
    public ResponseEntity<List<DocumentTypeDto>> queryAllDocumentType() {
        List<DocumentTypeDto> dtos = documentTypeServiceImpl.getAllDocumentTypes();
        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/v1/getAllDocumentTypeByPagination")
    public Page<DocumentTypeDto> getAllDocumentTypeByPagination(@RequestParam int pageNumber,
                                                                @RequestParam int pageSize) {
        return documentTypeServiceImpl.getAllDocumentTypesByPagination(pageNumber, pageSize);
    }
}
