package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.CityDto;
import com.ws.spring.model.City;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.CityRepository;
import com.ws.spring.repository.UserProfileRepository;


	@Service
	public class CityServiceImpl {
		
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
		
		@Autowired
		CityRepository cityRepository;
		
		@Autowired
		UserProfileRepository userProfileRepository;
		
		
		@Transactional(propagation = Propagation.REQUIRED)
		public City createCity(CityDto cityDto) {
		
			City city = new City();
			  BeanUtils.copyProperties(cityDto, city,"createdBy","updatedBy");
			
			 UserProfile userProfile = userProfileRepository.findByUserId(cityDto.getCreatedBy().getUserId());
			 city.setCreatedBy(userProfile);
			 city.setUpdatedBy(userProfile);
					
			 return cityRepository.save(city);
				
		}



		public City getCityNameExist(String cityName) {
			return cityRepository.findByCityName(cityName);
		}

		
		@Transactional(propagation = Propagation.REQUIRED)
		public City updateCity(CityDto cityDto) {
			City city = cityRepository.findByCityId(cityDto.getCityId());
		
			try {
				city.setCityName(cityDto.getCityName());
				city.setPincode(cityDto.getPincode());
				city.setDescription(cityDto.getDescription());
					
			} catch (Exception e) {
				logger.error(" Error while updating City {} and the Error is : {}", cityDto.getCityName(),
						e.getMessage());
			}
			
			UserProfile userProfile = userProfileRepository.findByUserId(cityDto.getUpdatedBy().getUserId());
			city.setUpdatedBy(userProfile);

			
			return cityRepository.save(city);
		}


		public CityDto getCityByCityId(long cityId) {
			City city = cityRepository.findByCityId(cityId);
	        return CommonBuilder.buildCityDto(city);
		}


		

		public Page<CityDto> getAllCityByPagination(int pageNumber, int pageSize) {
			
			//Pageable pageable = PageRequest.of(pageNumber, pageSize);
			
			Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("cityId").descending());
			
			Page<City> cityPage = cityRepository.findAll(pageable);
			
	        int totalElements = (int) cityPage.getTotalElements();
	        return new PageImpl<CityDto>(cityPage
	                .stream()
	                .map(city -> new CityDto(
	                		city.getCityId(),
	                		city.getCityName(),city.getPincode(), city.getDescription(), city.getInsertedDate(),city.getUpdatedDate(),  city.getCreatedBy(), city.getUpdatedBy()))
	                     
	                .collect(Collectors.toList()), pageable, totalElements);
			
		}



		public List<CityDto> getAllCities() {
			List<City> cityList = cityRepository.findAll();
	        return CommonBuilder.buildCityDtoList(cityList);
		}



		public void deleteCityById(long cityId) {
			cityRepository.deleteById(cityId);
			
		}



		

}
