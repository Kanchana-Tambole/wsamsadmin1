package com.ws.spring.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<<<<<<< HEAD
=======

>>>>>>> daccd45 (Initial commit)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.CityDto;
=======

import org.springframework.web.bind.annotation.*;

import com.ws.common.util.ClientResponseUtil;
import com.ws.spring.dto.CityDto;
import com.ws.spring.dto.CityDtoList;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.model.City;
import com.ws.spring.service.CityServiceImpl;

import io.swagger.annotations.Api;

<<<<<<< HEAD


	@RestController
	@RequestMapping("/city")
	@Api(value = "City Management System", tags = "City Management System")
	public class CityRestController {
		
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
		
		@Autowired
		CityServiceImpl cityServiceImpl;

		
		@PostMapping("/v1/createCity")
	    ResponseEntity<ClientResponseBean> createCity(@RequestBody CityDto cityDto) {
			try {
	        logger.debug("createCity CityName : {}", cityDto.getCityName());
	        
	        if (null != cityServiceImpl.getCityNameExist(cityDto.getCityName())) {
				return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(HttpStatus.BAD_REQUEST.value(),
						"City Already Exist"));
			}
	        
	        City cityCreated = cityServiceImpl.createCity(cityDto);
	        logger.debug("createCity Id : {}, CityName: {}", cityCreated.getCityId(),
	        		cityCreated.getCityName());
	        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
	        		 " City Successfully Created", ""));
	        
			} catch (Exception e) {
				logger.error("Exception occured : {}", e.getMessage(), e);
				
				return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
						e.getCause().getCause().getMessage(), ""));
			}
	    }

		
		@PutMapping("/v1/updateCity")
	    ResponseEntity<ClientResponseBean> updateCity(@RequestBody CityDto cityDto) {
			try {
	        logger.debug("updateCity CityName : {}", cityDto.getCityName());
	        
	        
	        City cityCreated = cityServiceImpl.updateCity(cityDto);
	        logger.debug("updateCity Id : {}, CityName: {}", cityCreated.getCityId(),
	        		cityCreated.getCityName());
	        return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
	        		 " City Successfully updated", ""));
			} catch (Exception e) {
				logger.error("Exception occured : {}", e.getMessage(), e);
				
				return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
						e.getCause().getCause().getMessage(), ""));
			}
	    }
		
		@GetMapping("/v1/getCityByCityId/{cityId}")
		public ResponseEntity<?> getCityByCityId(@PathVariable long cityId) {
			
			CityDto cityDto = cityServiceImpl.getCityByCityId(cityId);
			
			if (cityDto == null) {
				Map<String, String> noContentMessage = new HashMap<String, String>();
				noContentMessage.put("message", "Nothing found");
				return ResponseEntity.ok().body(noContentMessage);
			}
			
	        return ResponseEntity.ok().body(cityDto);
		}
		
		@DeleteMapping("/v1/deleteCityById/{cityId}")
	    ResponseEntity<ClientResponseBean> deleteCityById(@PathVariable long cityId) {
			try {
				
				cityServiceImpl.deleteCityById(cityId);
				
			return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
					" City successfully deleted", ""));
			} catch (Exception e) {
				logger.error("Exception occured : {}", e.getMessage(), e);
				
				return ResponseEntity.badRequest().body(new ClientResponseBean(HttpStatus.BAD_REQUEST.value(), "FAILED",
						e.getCause().getCause().getMessage(), ""));
			}
	    }
		
		
		@GetMapping("/v1/getAllCities")
	    ResponseEntity<List<CityDto>> getAllCities() {
	        List<CityDto> categoryDtos = cityServiceImpl.getAllCities();
	        return ResponseEntity.ok().body(categoryDtos);
	    }
		
		@GetMapping("/v1/getAllCityByPagination/{pageNumber}/{pageSize}")
	  public  Page<CityDto> getAllCityByPagination(@RequestParam int pageNumber, 
			  @RequestParam int pageSize) {
	        Page<CityDto> page = cityServiceImpl.getAllCityByPagination(pageNumber,pageSize);
	        
	        return page;
	    }
		
		
	    }
	
=======
@RestController
@RequestMapping("/city")
@Api(value = "City Management System", tags = "City Management System")
public class CityRestController {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    CityServiceImpl cityServiceImpl;

    @PostMapping("/v1/createCity")
    public ResponseEntity<ClientResponseBean> createCity(@RequestBody CityDto cityDto) {
        try {
            logger.debug("createCity CityName : {}", cityDto.getCityName());

            if (null != cityServiceImpl.getCityNameExist(cityDto.getCityName())) {
                return ResponseEntity.ok().body(ClientResponseUtil.getExceptionResponse(
                        HttpStatus.BAD_REQUEST.value(), "City Already Exist"));
            }

            City cityCreated = cityServiceImpl.createCity(cityDto);
            logger.debug("createCity Id : {}, CityName: {}", cityCreated.getCityId(), cityCreated.getCityName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS",
                    "City Successfully Created", ""));
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

    @PutMapping("/v1/updateCity")
    public ResponseEntity<ClientResponseBean> updateCity(@RequestBody CityDto cityDto) {
        try {
            logger.debug("updateCity CityName : {}", cityDto.getCityName());

            City cityUpdated = cityServiceImpl.updateCity(cityDto);
            logger.debug("updateCity Id : {}, CityName: {}", cityUpdated.getCityId(), cityUpdated.getCityName());

            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "City Successfully Updated", ""));
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

    @DeleteMapping("/v1/deleteCityById/{cityId}")
    public ResponseEntity<ClientResponseBean> deleteCityById(@PathVariable long cityId) {
        try {
            cityServiceImpl.deleteCityById(cityId);
            return ResponseEntity.ok().body(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS",
                    "City Successfully Deleted", ""));
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

    @GetMapping("/v1/getCityByCityId/{cityId}")
    public ResponseEntity<?> getCityByCityId(@PathVariable long cityId) {
        CityDto cityDto = cityServiceImpl.getCityByCityId(cityId);

        if (cityDto == null) {
            Map<String, String> noContentMessage = new HashMap<>();
            noContentMessage.put("message", "Nothing found");
            return ResponseEntity.ok().body(noContentMessage);
        }

        return ResponseEntity.ok().body(cityDto);
    }

    @GetMapping("/v1/getAllCities")
    public ResponseEntity<List<CityDtoList>> getAllCities() {
        List<CityDtoList> cityDtos = cityServiceImpl.getAllCities();
        return ResponseEntity.ok().body(cityDtos);
    }

    @GetMapping("/v1/getAllCityByPagination")
    public ResponseEntity<Page<CityDto>> getAllCityByPagination(
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {

        Page<CityDto> cityPage = cityServiceImpl.getAllCityByPagination(pageNumber, pageSize);
        return ResponseEntity.ok().body(cityPage);
    }
}
>>>>>>> daccd45 (Initial commit)
