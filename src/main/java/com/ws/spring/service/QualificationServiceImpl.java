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

import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.dto.QualificationDto;
import com.ws.spring.model.Qualification;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.QualificationRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class QualificationServiceImpl {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	QualificationRepository qualificationRepository;
	
	@Autowired
	UserProfileRepository userProfileRepository;
	

	public Qualification createQualification(QualificationDto qualificationDto) {
		
		
		Qualification qualification = new Qualification();
		BeanUtils.copyProperties(qualificationDto, qualification);
		
		
		 UserProfile userProfile = userProfileRepository.findByUserId(qualificationDto.getCreatedBy().getUserId());
		 qualification.setCreatedBy(userProfile);
		 qualification.setUpdatedBy(userProfile);
		 
		 
		return qualificationRepository.save(qualification);
	}

	public Qualification updateQualification(QualificationDto qualificationDto) {
		
		Qualification qualification = qualificationRepository.findByQualificationId(qualificationDto.getQualificationId());
	
		try {
			
		    qualification.setQualificationName(qualificationDto.getQualificationName());
		    qualification.setDescription(qualificationDto.getDescription());
		    
		} catch (Exception e) {
		    logger.error("Error while updating Qualification {} and the Error is: {}", qualificationDto.getQualificationName(), e.getMessage());
		}
		
		UserProfile userProfile = userProfileRepository.findByUserId(qualificationDto.getUpdatedBy().getUserId());
		qualification.setUpdatedBy(userProfile);
		
		
		return qualificationRepository.save(qualification);
	}

	public QualificationDto queryQualificationByQualificationId(long qualificationId) {
		Qualification qualification = qualificationRepository.findByQualificationId(qualificationId);
		return CommonBuilder.buildQualificationDto(qualification);
	}

	public List<QualificationDto> queryAllQualifications() {
		List<Qualification> qualificationList = qualificationRepository
                .findAll(Sort.by(Sort.Direction.DESC, "insertedDate"));
        return CommonBuilder.buildQualificationDtoList(qualificationList);
	}

	public void deleteQualificationById(long qualificationId) {
		qualificationRepository.deleteById(qualificationId);
		
	}

	public Page<QualificationDto> getAllQualificationByPagination(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("qualificationId").descending());
		
		Page<Qualification> cityPage = qualificationRepository.findAll(pageable);
		
        int totalElements = (int) cityPage.getTotalElements();
        return new PageImpl<QualificationDto>(cityPage
                .stream()
                .map(qualification -> new QualificationDto(
                		qualification.getQualificationId(),
                		qualification.getQualificationName(), qualification.getDescription(), qualification.getInsertedDate(),qualification.getUpdatedDate(),  qualification.getCreatedBy(), qualification.getUpdatedBy()))
                     
                .collect(Collectors.toList()), pageable, totalElements);
        
	}

}
