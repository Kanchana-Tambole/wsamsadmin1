package com.ws.spring.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.BankTypeDto;
import com.ws.spring.dto.BankTypeDtoList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.BankType;
import com.ws.spring.service.BankTypeServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/banktype")
@Api(value = "BankType Management System", tags = "BankType Management System")
public class BankTypeRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private BankTypeServiceImpl bankTypeServiceImpl;

    // ✅ Create
    @PostMapping("/v1/createBankType")
    public ResponseEntity<ClientResponseBean> createBankType(@RequestBody BankTypeDto bankTypeDto) {
        try {
            logger.debug("createBankType BankName : {}", bankTypeDto.getBankName());

            if (null != bankTypeServiceImpl.getBankByBankName(bankTypeDto.getBankName())) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "Bank Already Exists"));
            }

            BankType bankTypeCreated = bankTypeServiceImpl.createBankType(bankTypeDto);
            logger.debug("createBankType Id : {}, BankName: {}", bankTypeCreated.getId(), bankTypeCreated.getBankName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    " Bank Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    // ✅ Update
    @PutMapping("/v1/updateBankType")
    public ResponseEntity<ClientResponseBean> updateBankType(@RequestBody BankTypeDto bankTypeDto) {
        try {
            logger.debug("updateBankType BankName : {}", bankTypeDto.getBankName());

            BankType bankTypeUpdated = bankTypeServiceImpl.updateBankType(bankTypeDto);
            logger.debug("updateBankType Id : {}, BankName: {}", bankTypeUpdated.getId(), bankTypeUpdated.getBankName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    " Bank Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    // ✅ Delete
    @DeleteMapping("/v1/deleteBankTypeById/{id}")
    public ResponseEntity<ClientResponseBean> deleteBankTypeById(@PathVariable long id) {
        try {
            bankTypeServiceImpl.deleteBankTypeById(id);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    " Bank successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(
                    HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    // ✅ Get by ID
    @GetMapping("/v1/getBankTypeById/{id}")
    public ResponseEntity<?> getBankTypeById(@PathVariable long id) {
        BankTypeDto bankTypeDto = bankTypeServiceImpl.getBankTypeById(id);
        if (bankTypeDto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }
        return ResponseEntity.ok().body(bankTypeDto);
    }

    // ✅ Get all list (summary)
    @GetMapping("/v1/getAllBankTypes")
    public ResponseEntity<List<BankTypeDtoList>> getAllBankTypes() {
        List<BankTypeDtoList> bankTypeDtos = bankTypeServiceImpl.getAllBankTypes();
        return ResponseEntity.ok().body(bankTypeDtos);
    }

    // ✅ Get all with pagination
    @GetMapping("/v1/getAllBankTypesByPagination")
    public Page<BankTypeDto> getAllBankTypesByPagination(@RequestParam int pageNumber,
                                                          @RequestParam int pageSize) {
        return bankTypeServiceImpl.getAllBankTypesByPagination(pageNumber, pageSize);
    }
}
