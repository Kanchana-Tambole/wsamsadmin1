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
import com.ws.spring.dto.JobDesignationDto;
import com.ws.spring.model.JobDesignation;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.JobDesignationRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class JobDesignationServiceImpl {
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	JobDesignationRepository jobDesignationRepository;
	
	@Autowired
	UserProfileRepository userProfileRepository;

	public JobDesignation createJobDesignation(JobDesignationDto jobDesignationDto) {
		
		
		JobDesignation jobDesignation = new JobDesignation();
		BeanUtils.copyProperties(jobDesignationDto, jobDesignation);
		
		 UserProfile userProfile = userProfileRepository.findByUserId(jobDesignationDto.getCreatedBy().getUserId());
		 jobDesignation.setCreatedBy(userProfile);
		 jobDesignation.setUpdatedBy(userProfile);
		 
		return jobDesignationRepository.save(jobDesignation);
	}

	public JobDesignation updateJobDesignation(JobDesignationDto jobDesignationDto) {
		
		JobDesignation jobDesignation = jobDesignationRepository.findByDesignationId(jobDesignationDto.getDesignationId());
		
		try {
			jobDesignation.setDesignationName(jobDesignationDto.getDesignationName());
			jobDesignation.setDescription(jobDesignationDto.getDescription());
			
			
		} catch (Exception e) {
		    logger.error("Error while updating Designation {} and the Error is: {}", jobDesignationDto.getDesignationName(), e.getMessage());
		}
		
		UserProfile userProfile = userProfileRepository.findByUserId(jobDesignationDto.getUpdatedBy().getUserId());
		jobDesignation.setUpdatedBy(userProfile);
		
		
		return jobDesignationRepository.save(jobDesignation);
	}

	public JobDesignationDto queryJobDesignationByDesignationId(long designationId) {
		JobDesignation jobDesignation = jobDesignationRepository.findByDesignationId(designationId);
		return CommonBuilder.buildJobDesignationDto(jobDesignation);
	}

	public List<JobDesignationDto> queryAllJobDesignations() {
		List<JobDesignation> designationList = jobDesignationRepository
                .findAll(Sort.by(Sort.Direction.DESC, "insertedDate"));
        return CommonBuilder.buildJobDesignationDtoList(designationList);
	}

	public long getJobDesignationCount() {
		return jobDesignationRepository.count();
	}

	public void deleteJobDesignationById(long designationId) {
		jobDesignationRepository.deleteById(designationId);
		
	}

	public Page<JobDesignationDto> getAllJobDesignationByPagination(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("designationId").descending());
		
		Page<JobDesignation> cityPage = jobDesignationRepository.findAll(pageable);
		
        int totalElements = (int) cityPage.getTotalElements();
        return new PageImpl<JobDesignationDto>(cityPage
                .stream()
                .map(jobDesignation -> new JobDesignationDto(
                		jobDesignation.getDesignationId(),
                		jobDesignation.getDesignationName(), jobDesignation.getDescription(), jobDesignation.getInsertedDate(),jobDesignation.getUpdatedDate(),  jobDesignation.getCreatedBy(), jobDesignation.getUpdatedBy()))
                     
                .collect(Collectors.toList()), pageable, totalElements);
	}
	
	
}
