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
import com.ws.spring.dto.UpskillModuleDtoList;
import com.ws.spring.dto.UpskillTopicDto;
import com.ws.spring.model.UpskillModule;
import com.ws.spring.model.UpskillTopic;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.UpskillModuleRepository;
import com.ws.spring.repository.UpskillTopicRepository;
import com.ws.spring.repository.UserProfileRepository;
 
 
@Service
public class UpskillTopicServiceImpl {
 
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
 
	@Autowired
	UpskillTopicRepository  upskillTopicRepository;
	@Autowired
	UpskillModuleRepository  upskillModuleRepository;
	@Autowired
	UserProfileRepository userProfileRepository;
 
	public UpskillTopicDto getUpskillTopicByTopicId(long topicId) {
		UpskillTopic upskillTopic = upskillTopicRepository.findByTopicId(topicId);
		return CommonBuilder.buildUpskillTopicDto(upskillTopic);
	}
 
	public List<UpskillTopicDto> getUpskillTopicsByModuleId(long moduleId) {
		UpskillModule upskillModule = upskillModuleRepository.findByModuleId(moduleId);
		List<UpskillTopic> upskillTopicList = upskillTopicRepository.findAllByUpskillModule(upskillModule);
		return CommonBuilder.buildUpskillTopicDtoList(upskillTopicList);
	}
 
	public Page<UpskillTopicDto> getAllUpskillTopicsByPagination(int pageNumber, int pageSize) {
Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("topicId").descending());
		Page<UpskillTopic> advertisementPage = upskillTopicRepository.findAll(pageable);
        int totalElements = (int) advertisementPage.getTotalElements();
        return new PageImpl<UpskillTopicDto>(advertisementPage
                .stream()
                .map(upskillTopic -> new UpskillTopicDto(
                		upskillTopic.getTopicId(),
                		upskillTopic.getTopicName(),
                		upskillTopic.getDescription(), upskillTopic.getVideoUrl(), upskillTopic.getInsertedDate(), upskillTopic.getUpdatedDate(), upskillTopic.getCreatedBy(), upskillTopic.getUpdatedBy(), upskillTopic.getUpskillModule()))
                .collect(Collectors.toList()), pageable, totalElements);
	}
 
	@Transactional(propagation = Propagation.REQUIRED)
	public UpskillTopic createUpskillTopic(UpskillTopicDto topicDto) {
		UpskillTopic topic = new UpskillTopic();
		BeanUtils.copyProperties(topicDto, topic,"createdBy","updatedBy");

		UpskillModuleDtoList moduleDtoList = topicDto.getUpskillModuleDtoList();
		if(null != moduleDtoList && moduleDtoList.getModuleId() != 0) {
		 UpskillModule module = upskillModuleRepository.findByModuleId(topicDto.getUpskillModuleDtoList().getModuleId());
		 topic.setUpskillModule(module);
		}
		UserProfile userProfile = userProfileRepository.findByUserId(topicDto.getCreatedBy().getUserId());
		topic.setCreatedBy(userProfile);
		topic.setUpdatedBy(userProfile);
		return upskillTopicRepository.save(topic);
	}
 
	@Transactional(propagation = Propagation.REQUIRED)
	public UpskillTopic updateUpskillTopic(UpskillTopicDto topicDto) {
		UpskillTopic topic = upskillTopicRepository.findByTopicId(topicDto.getTopicId());
		topic.setTopicName(topicDto.getTopicName());
		topic.setDescription(topicDto.getDescription());
		topic.setVideoUrl(topicDto.getVideoUrl());

		UpskillModuleDtoList moduleDtoList = topicDto.getUpskillModuleDtoList();
		if(null != moduleDtoList && moduleDtoList.getModuleId() != 0) {
		 UpskillModule module = upskillModuleRepository.findByModuleId(topicDto.getUpskillModuleDtoList().getModuleId());
		 topic.setUpskillModule(module);
		}
		UserProfile userProfile = userProfileRepository.findByUserId(topicDto.getUpdatedBy().getUserId());
		topic.setUpdatedBy(userProfile);

		return upskillTopicRepository.save(topic);
	}
 
	public void deleteUpskillTopicById(long topicId) {
		upskillTopicRepository.deleteById(topicId);
	}
}