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
import com.ws.spring.dto.OccupationDto;
import com.ws.spring.model.Occupation;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.OccupationRepository;
import com.ws.spring.repository.UserProfileRepository;



	@Service
	public class OccupationServiceImpl {
		
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
		
		@Autowired
		OccupationRepository occupationRepository;
		
		@Autowired
		UserProfileRepository userProfileRepository;
		
		
		@Transactional(propagation = Propagation.REQUIRED)
		public Occupation createOccupation(OccupationDto occupationDto) {
		
			Occupation occupation = new Occupation();
			  BeanUtils.copyProperties(occupationDto, occupation,"createdBy","updatedBy");
			
			 UserProfile userProfile = userProfileRepository.findByUserId(occupationDto.getCreatedBy().getUserId());
			 occupation.setCreatedBy(userProfile);
			 occupation.setUpdatedBy(userProfile);
					
			 return occupationRepository.save(occupation);
				
		}



		public Occupation getOccupationNameExist(String occupationName) {
			return occupationRepository.findByOccupationName(occupationName);
		}

		
		@Transactional(propagation = Propagation.REQUIRED)
		public Occupation updateOccupation(OccupationDto occupationDto) {
			Occupation occupation = occupationRepository.findByOccupationId(occupationDto.getOccupationId());
		
			try {
				occupation.setOccupationName(occupationDto.getOccupationName());
				occupation.setDescription(occupationDto.getDescription());
					
			} catch (Exception e) {
				logger.error(" Error while updating Occupation {} and the Error is : {}", occupationDto.getOccupationName(),
						e.getMessage());
			}
			
			UserProfile userProfile = userProfileRepository.findByUserId(occupationDto.getUpdatedBy().getUserId());
			occupation.setUpdatedBy(userProfile);

			
			return occupationRepository.save(occupation);
		}


		public OccupationDto getOccupationByOccupationId(long occupationId) {
			Occupation occupation = occupationRepository.findByOccupationId(occupationId);
	        return CommonBuilder.buildOccupationDto(occupation);
		}


		

		


		

		public Page<OccupationDto> getAllOccupationByPagination(int pageNumber, int pageSize) {
			
			//Pageable pageable = PageRequest.of(pageNumber, pageSize);
			
			Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("occupationId").descending());
			
			Page<Occupation> occupationPage = occupationRepository.findAll(pageable);
			
	        int totalElements = (int) occupationPage.getTotalElements();
	        return new PageImpl<OccupationDto>(occupationPage
	                .stream()
	                .map(occupation -> new OccupationDto(
	                		occupation.getOccupationId(),
	                		occupation.getOccupationName(), occupation.getDescription(), occupation.getInsertedDate(),occupation.getUpdatedDate(),  occupation.getCreatedBy(), occupation.getUpdatedBy()))
	                     
	                .collect(Collectors.toList()), pageable, totalElements);
			
		}



		public void deleteOccupationById(long occupationId) {
			occupationRepository.deleteById(occupationId);
			
		}



		

}

