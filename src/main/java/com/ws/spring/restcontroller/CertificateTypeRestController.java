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
import com.ws.spring.dto.CertificateTypeDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.CertificateType;
import com.ws.spring.service.CertificateTypeServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/certificatetype")
@Api(value = "CertificateType Management System", tags = "CertificateType Management System")
public class CertificateTypeRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    CertificateTypeServiceImpl certificateTypeServiceImpl;

    @PostMapping("/v1/createCertificateType")
    public ResponseEntity<ClientResponseBean> createCertificateType(@RequestBody CertificateTypeDto certificateDto) {
        try {
            logger.debug("createCertificateType TypeCode : {}", certificateDto.getTypeCode());

            if (null != certificateTypeServiceImpl.getTypeCodeExist(certificateDto.getTypeCode())) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Certificate Type Already Exist"));
            }

            CertificateType createdType = certificateTypeServiceImpl.createCertificateType(certificateDto);

            logger.debug("createCertificateType Id : {}, CertificateName: {}", createdType.getTypeId(),
                    createdType.getCertificateName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Certificate Type Successfully Created", ""));

        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);

            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause().getCause().getMessage(), ""));
        }
    }

    @PutMapping("/v1/updateCertificateType")
    public ResponseEntity<ClientResponseBean> updateCertificateType(@RequestBody CertificateTypeDto certificateDto) {
        try {
            logger.debug("updateCertificateType TypeCode : {}", certificateDto.getTypeCode());

            CertificateType updatedType = certificateTypeServiceImpl.updateCertificateType(certificateDto);

            logger.debug("updateCertificateType Id : {}, CertificateName: {}", updatedType.getTypeId(),
                    updatedType.getCertificateName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Certificate Type Successfully Updated", ""));

        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);

            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause().getCause().getMessage(), ""));
        }
    }

    @DeleteMapping("/v1/deleteCertificateTypeById/{typeId}")
    public ResponseEntity<ClientResponseBean> deleteCertificateTypeById(@PathVariable long typeId) {
        try {
            certificateTypeServiceImpl.deleteCertificateTypeById(typeId);

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Certificate Type successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);

            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause().getCause().getMessage(), ""));
        }
    }

    @GetMapping("/v1/getCertificateTypeById/{typeId}")
    public ResponseEntity<?> getCertificateTypeById(@PathVariable long typeId) {
        CertificateTypeDto dto = certificateTypeServiceImpl.getCertificateTypeById(typeId);

        if (dto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/v1/getAllCertificateTypes")
    public ResponseEntity<List<CertificateTypeDto>> getAllCertificateTypes() {
        List<CertificateTypeDto> dtoList = certificateTypeServiceImpl.getAllCertificateTypes();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/v1/getAllCertificateTypesByPagination")
    public Page<CertificateTypeDto> getAllCertificateTypesByPagination(@RequestParam int pageNumber,
                                                                        @RequestParam int pageSize) {
        return certificateTypeServiceImpl.getAllCertificateTypesByPagination(pageNumber, pageSize);
    }
}
