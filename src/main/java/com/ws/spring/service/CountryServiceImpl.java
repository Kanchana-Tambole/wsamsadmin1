package com.ws.spring.service;

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
import com.ws.spring.dto.CountryDto;
import com.ws.spring.model.Country;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.CountryRepository;
import com.ws.spring.repository.UserProfileRepository;



	@Service
	public class CountryServiceImpl {
		
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
		
		@Autowired
		CountryRepository countryRepository;
		
		@Autowired
		UserProfileRepository userProfileRepository;
		
		
		@Transactional(propagation = Propagation.REQUIRED)
		public Country createCountry(CountryDto countryDto) {
		
			Country country = new Country();
			  BeanUtils.copyProperties(countryDto, country,"createdBy","updatedBy");
			
			 UserProfile userProfile = userProfileRepository.findByUserId(countryDto.getCreatedBy().getUserId());
			 country.setCreatedBy(userProfile);
			 country.setUpdatedBy(userProfile);
					
			 return countryRepository.save(country);
				
		}



		public Country getCountryNameExist(String countryName) {
			return countryRepository.findByCountryName(countryName);
		}

		
		@Transactional(propagation = Propagation.REQUIRED)
		public Country updateCountry(CountryDto countryDto) {
			Country country = countryRepository.findByCountryId(countryDto.getCountryId());
		
			try {
				country.setCountryName(countryDto.getCountryName());
				country.setDescription(countryDto.getDescription());
					
			} catch (Exception e) {
				logger.error(" Error while updating Country {} and the Error is : {}", countryDto.getCountryName(),
						e.getMessage());
			}
			
			UserProfile userProfile = userProfileRepository.findByUserId(countryDto.getUpdatedBy().getUserId());
			country.setUpdatedBy(userProfile);

			
			return countryRepository.save(country);
		}


		public CountryDto getCountryByCountryId(long countryId) {
			Country country = countryRepository.findByCountryId(countryId);
	        return CommonBuilder.buildCountryDto(country);
		}


		

		
		

		public Page<CountryDto> getAllCountryByPagination(int pageNumber, int pageSize) {
			
			//Pageable pageable = PageRequest.of(pageNumber, pageSize);
			
			Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("countryId").descending());
			
			Page<Country> countryPage = countryRepository.findAll(pageable);
			
	        int totalElements = (int) countryPage.getTotalElements();
	        return new PageImpl<CountryDto>(countryPage
	                .stream()
	                .map(country -> new CountryDto(
	                		country.getCountryId(),
	                		country.getCountryName(), country.getDescription(), country.getInsertedDate(),country.getUpdatedDate(),  country.getCreatedBy(), country.getUpdatedBy()))
	                     
	                .collect(Collectors.toList()), pageable, totalElements);
			
		}



		public void deleteCountryById(long countryId) {
			countryRepository.deleteById(countryId);
			
		}



		

}

