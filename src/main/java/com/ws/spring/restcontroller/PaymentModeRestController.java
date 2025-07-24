package com.ws.spring.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.PaymentModeDto;
import com.ws.spring.dto.PaymentModeDtoList;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.PaymentMode;
import com.ws.spring.service.PaymentModeServiceImpl;

import io.swagger.annotations.Api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentmode")
@Api(value = "PaymentMode Management System", tags = "PaymentMode Management System")
public class PaymentModeRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    PaymentModeServiceImpl paymentModeServiceImpl;

    @PostMapping("/v1/createPaymentMode")
    public ResponseEntity<ClientResponseBean> createPaymentMode(@RequestBody PaymentModeDto modeDto) {
        try {
            logger.debug("createPaymentMode ModeName : {}", modeDto.getModeName());

            if (null != paymentModeServiceImpl.getPaymentModeByName(modeDto.getModeName())) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                        "Payment Mode Already Exist"));
            }

            PaymentMode modeCreated = paymentModeServiceImpl.createPaymentMode(modeDto);

            logger.debug("createPaymentMode Id : {}, ModeName: {}", modeCreated.getId(), modeCreated.getModeName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Payment Mode Successfully Created", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @PutMapping("/v1/updatePaymentMode")
    public ResponseEntity<ClientResponseBean> updatePaymentMode(@RequestBody PaymentModeDto modeDto) {
        try {
            logger.debug("updatePaymentMode ModeName : {}", modeDto.getModeName());

            PaymentMode modeUpdated = paymentModeServiceImpl.updatePaymentMode(modeDto);

            logger.debug("updatePaymentMode Id : {}, ModeName: {}", modeUpdated.getId(), modeUpdated.getModeName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "Payment Mode Successfully Updated", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @DeleteMapping("/v1/deletePaymentModeById/{modeId}")
    public ResponseEntity<ClientResponseBean> deletePaymentModeById(@PathVariable long modeId) {
        try {
            paymentModeServiceImpl.deletePaymentModeById(modeId);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "Payment Mode successfully Deleted", ""));
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
                    e.getCause() != null && e.getCause().getCause() != null
                            ? e.getCause().getCause().getMessage()
                            : e.getMessage(),
                    ""));
        }
    }

    @GetMapping("/v1/getPaymentModeById/{modeId}")
    public ResponseEntity<?> getPaymentModeById(@PathVariable long modeId) {
        PaymentModeDto modeDto = paymentModeServiceImpl.getPaymentModeById(modeId);
        if (modeDto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(modeDto);
    }

    @GetMapping("/v1/getAllPaymentModes")
    public ResponseEntity<List<PaymentModeDtoList>> getAllPaymentModes() {
        List<PaymentModeDtoList> modeDtos = paymentModeServiceImpl.getAllPaymentModes();
        return ResponseEntity.ok().body(modeDtos);
    }

    @GetMapping("/v1/getAllPaymentModesByPagination")
    public Page<PaymentModeDto> getAllPaymentModesByPagination(@RequestParam int pageNumber,
                                                                @RequestParam int pageSize) {
        return paymentModeServiceImpl.getAllPaymentModesByPagination(pageNumber, pageSize);
    }
}
