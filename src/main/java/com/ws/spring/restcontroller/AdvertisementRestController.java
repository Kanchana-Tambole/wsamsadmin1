package com.ws.spring.restcontroller;

import java.util.List;

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

import com.ws.spring.dto.AdvertisementDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Advertisement;
import com.ws.spring.service.AdvertisementServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/advertisement")
@Api(value = "Advertisement Management System", tags = "Advertisement Management System")
public class AdvertisementRestController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	AdvertisementServiceImpl advertisementServiceImpl;
	
	
	@PostMapping("/v1/createAdvertisement")
    ResponseEntity<ClientResponseBean> createAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
		try {
        logger.debug("createAdvertisement advertisementDto : {}", advertisementDto);
        
        Advertisement advertisementCreated = advertisementServiceImpl.createAdvertisement(advertisementDto);
        logger.debug("createAdvertisement Id : {}, FileName: {}", advertisementCreated.getAdvertisementId(),
        		advertisementCreated.getFileName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Advertisement Successfully Created", ""));
        
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	
	@PutMapping("/v1/updateAdvertisement")
    ResponseEntity<ClientResponseBean> updateAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
		try {
        logger.debug("updateAdvertisement advertisementDto : {}", advertisementDto);
        
        Advertisement advertisementCreated = advertisementServiceImpl.updateAdvertisement(advertisementDto);
        logger.debug("updateAdvertisement Id : {}, FileName: {}", advertisementCreated.getAdvertisementId(),
        		advertisementCreated.getFileName());
        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
        		 " Advertisement Successfully Updated", ""));
        
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	@DeleteMapping("/v1/deleteAdvertisementById/{advertisementId}")
    ResponseEntity<ClientResponseBean> deleteAdvertisementById(@PathVariable long advertisementId) {
		try {
			advertisementServiceImpl.deleteAdvertisementById(advertisementId);
			
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				" Advertisement Successfully Deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }
	
	@GetMapping("/v1/getAdvertisementByAdvertisementId/{advertisementId}")
	public ResponseEntity<?> getAdvertisementByAdvertisementId(long advertisementId) {

	    AdvertisementDto advertisementDto = advertisementServiceImpl.getAdvertisementByAdvertisementId(advertisementId);

	    return ResponseEntity.ok().body(advertisementDto);
	}

	@GetMapping("/v1/queryAllAdvertisement")
    ResponseEntity<List<AdvertisementDto>> queryAllAdvertisement() {
        List<AdvertisementDto> advertisementDtos = advertisementServiceImpl.queryAllAdvertisement();
        return ResponseEntity.ok().body(advertisementDtos);
    }
	
	
	@GetMapping("/v1/getAllAdvertisementByPagination/{pageNumber}/{pageSize}")
	  public  Page<AdvertisementDto> getAllAdvertisementByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<AdvertisementDto> page = advertisementServiceImpl.getAllAdvertisementByPagination(pageNumber,pageSize);
	        return page;
	    }
	
	
	
}
