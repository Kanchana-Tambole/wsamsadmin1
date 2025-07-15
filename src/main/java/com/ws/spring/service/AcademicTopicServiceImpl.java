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
 
import com.ws.spring.dto.AcademicModuleDtoList;
import com.ws.spring.dto.AcademicTopicDto;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.AcademicModule;
import com.ws.spring.model.AcademicTopic;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.AcademicModuleRepository;
import com.ws.spring.repository.AcademicTopicRepository;
import com.ws.spring.repository.UserProfileRepository;
 
 
@Service
public class AcademicTopicServiceImpl {
 
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
 
	@Autowired
	AcademicTopicRepository  academicTopicRepository;
	@Autowired
	AcademicModuleRepository  academicModuleRepository;
	@Autowired
	UserProfileRepository userProfileRepository;

 
	
	public AcademicTopicDto getAcademicTopicByTopicId(long topicId) {

		AcademicTopic academicTopic = academicTopicRepository.findByTopicId(topicId);
		return CommonBuilder.buildAcademicTopicDto(academicTopic);
	}
 
	public List<AcademicTopicDto> getAcademicTopicsByModuleId(long moduleId) {
		AcademicModule academicModule = academicModuleRepository.findByModuleId(moduleId);
		List<AcademicTopic> academicTopicList = academicTopicRepository.findAllByAcademicModule(academicModule);
		return CommonBuilder.buildAcademicTopicDtoList(academicTopicList);
	}
 
	public Page<AcademicTopicDto> getAllAcademicTopicsByPagination(int pageNumber, int pageSize) {
Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("topicId").descending());
		Page<AcademicTopic> advertisementPage = academicTopicRepository.findAll(pageable);
        int totalElements = (int) advertisementPage.getTotalElements();
        return new PageImpl<AcademicTopicDto>(advertisementPage
                .stream()
                .map(academicTopic -> new AcademicTopicDto(
                		academicTopic.getTopicId(),
                		academicTopic.getTopicName(),
                		academicTopic.getDescription(), academicTopic.getVideoUrl(), academicTopic.getInsertedDate(), academicTopic.getUpdatedDate(), academicTopic.getCreatedBy(), academicTopic.getUpdatedBy(), academicTopic.getAcademicModule()))
                .collect(Collectors.toList()), pageable, totalElements);
	}
 
	@Transactional(propagation = Propagation.REQUIRED)
	public AcademicTopic createAcademicTopic(AcademicTopicDto topicDto) {
		AcademicTopic topic = new AcademicTopic();
		BeanUtils.copyProperties(topicDto, topic,"createdBy","updatedBy");

		AcademicModuleDtoList moduleDtoList = topicDto.getAcademicModuleDtoList();
		if(null != moduleDtoList && moduleDtoList.getModuleId() != 0) {
			AcademicModule module = academicModuleRepository.findByModuleId(topicDto.getAcademicModuleDtoList().getModuleId());
		 topic.setAcademicModule(module);
		}
		UserProfile userProfile = userProfileRepository.findByUserId(topicDto.getCreatedBy().getUserId());
		topic.setCreatedBy(userProfile);
		topic.setUpdatedBy(userProfile);
		return academicTopicRepository.save(topic);
	}
 
	
	@Transactional(propagation = Propagation.REQUIRED)
	public AcademicTopic updateAcademicTopic(AcademicTopicDto topicDto) {
		AcademicTopic topic = academicTopicRepository.findByTopicId(topicDto.getTopicId());
		topic.setTopicName(topicDto.getTopicName());
		topic.setDescription(topicDto.getDescription());
		topic.setVideoUrl(topicDto.getVideoUrl());

		AcademicModuleDtoList moduleDtoList = topicDto.getAcademicModuleDtoList();
		if(null != moduleDtoList && moduleDtoList.getModuleId() != 0) {
			AcademicModule module = academicModuleRepository.findByModuleId(topicDto.getAcademicModuleDtoList().getModuleId());
		 topic.setAcademicModule(module);
		}
		UserProfile userProfile = userProfileRepository.findByUserId(topicDto.getUpdatedBy().getUserId());
		topic.setUpdatedBy(userProfile);

		return academicTopicRepository.save(topic);
	}
 
	public void deleteAcademicTopicById(long topicId) {
		academicTopicRepository.deleteById(topicId);
	}
 
}