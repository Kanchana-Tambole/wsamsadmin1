package com.ws.spring.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ws.spring.dto.PromoDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Promo;
import com.ws.spring.service.PromoServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/promo")
@Api(value = "Promo Management System", tags = "Promo Management System")
public class PromoRestController {
	
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	PromoServiceImpl promoServiceImpl;
	
	
	@PostMapping("/v1/createPromo")
    ResponseEntity<ClientResponseBean> createPromo(@RequestBody PromoDto promoDto) {
		try {
        logger.debug("createPromo PromoName : {}", promoDto.getPromoName());
        
        
        Promo promoCreated = promoServiceImpl.createPromo(promoDto);
        
        logger.debug("createPromo Id : {}, PromoName: {}", promoCreated.getPromoId(),
        		promoCreated.getPromoName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Promo Successfully Created", ""));
        
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	
	
	
	@PutMapping("/v1/updatePromo")
    ResponseEntity<ClientResponseBean> updatePromo(@RequestBody PromoDto promoDto) {
		try {
        logger.debug("updatePromo PromoName : {}", promoDto.getPromoName());
        
        
        Promo promoCreated = promoServiceImpl.updatePromo(promoDto);
        
        logger.debug("updatePromo Id : {}, PromoName: {}", promoCreated.getPromoId(),
        		promoCreated.getPromoName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Promo Successfully Updated", ""));
        
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	
	
	@GetMapping("/v1/getPromoByPromoId/{promoId}")
	public ResponseEntity<?> getPromoByPromoId(long promoId) {
		
		PromoDto promoDto = promoServiceImpl.getPromoByPromoId(promoId);
	
		
        return ResponseEntity.ok().body(promoDto);
	}
	
	
	@DeleteMapping("/v1/deletePromoById/{promoId}")
	
    ResponseEntity<ClientResponseBean> deletePromoById(@PathVariable long promoId) {
		try {
			promoServiceImpl.deletePromoById(promoId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				" Promo successfully deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	
	

	@GetMapping("/v1/getAllPromoByPagination/{pageNumber}/{pageSize}")
  public  Page<PromoDto> getAllPromoByPagination(@RequestParam int pageNumber, 
		  @RequestParam int pageSize) {
        Page<PromoDto> page = promoServiceImpl.getAllPromoByPagination(pageNumber,pageSize);
        
        return page;
    }
	
}
