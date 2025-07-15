package com.ws.spring.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ws.spring.dto.LocationDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.Location;
import com.ws.spring.service.LocationServiceImpl;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/location")
@Api(value = "Location Management System", tags = "Location Management System")
public class LocationRestController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	
	@Autowired
	LocationServiceImpl locationServiceImpl;
	
	
	@PostMapping("/v1/createLocation")
	ResponseEntity<ClientResponseBean> createLocation(@RequestBody LocationDto locationDto) {
		try {
		logger.debug("createLocation LocationName : {}" , locationDto.getLocationName());
		Location locationCreated = locationServiceImpl.createLocation(locationDto);
		logger.debug("createLocation Id : {}, LocationName : {}" , locationCreated.getLocationId(),locationCreated.getLocationName());
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
				locationCreated.getLocationName() + " location successfully created", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
	}
	
	@PutMapping("/v1/updateLocation")
	ResponseEntity<ClientResponseBean> updateLocation(@RequestBody LocationDto locationDto) {
		try {
		logger.debug("updateLocation LocationName : {}" , locationDto.getLocationName());
		Location locationCreated = locationServiceImpl.updateLocation(locationDto);
		logger.debug("updateLocation Id : {}, LocationName : {}" , locationCreated.getLocationId(),locationCreated.getLocationName());
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
				locationCreated.getLocationName() + " location successfully updated", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
	}
	
	@GetMapping("/v1/queryLocationByLocationId/{locationId}")
	ResponseEntity<LocationDto> queryLocationByLocationId(@PathVariable long locationId) {
		LocationDto locationDto = locationServiceImpl.queryLocationByLocationId(locationId);
		return ResponseEntity.ok().body(locationDto);
	}
	
	@GetMapping("/v1/queryAllLocations")
    ResponseEntity<List<LocationDto>> queryAllLocations() {
        List<LocationDto> locationDto = locationServiceImpl.queryAllLocations();
        return ResponseEntity.ok().body(locationDto);
    }
	
	@DeleteMapping("/v1/deleteLocationById/{locationId}")
    ResponseEntity<ClientResponseBean> deleteLocationById(@PathVariable long locationId) {
		try {
			locationServiceImpl.deleteLocationById(locationId);
		return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
				locationId + " location successfully deleted", ""));
		} catch (Exception e) {
			logger.error("Exception occured : {}", e.getMessage(), e);
			
			return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
					e.getCause().getCause().getMessage(), ""));
		}
    }

}
