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
import com.ws.spring.dto.SkillDto;
import com.ws.spring.model.Skill;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.SkillRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class SkillServiceImpl {

	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	SkillRepository  skillRepository;
	
	@Autowired
	UserProfileRepository userProfileRepository;
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Skill createSkill(SkillDto skillDto) {
		
		Skill  skill = new Skill();
		
		 BeanUtils.copyProperties(skillDto, skill,"createdBy","updatedBy");
		
		 UserProfile userProfile = userProfileRepository.findByUserId(skillDto.getCreatedBy().getUserId());
		 skill.setCreatedBy(userProfile);
		 skill.setUpdatedBy(userProfile);
		
		
		return skillRepository.save(skill);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public Skill updateSkill(SkillDto skillDto) {
		
		Skill  skill = skillRepository.findBySkillId(skillDto.getSkillId());
				
				try {
					skill.setSkillName(skillDto.getSkillName());
					skill.setDescription(skillDto.getDescription());
						
				} catch (Exception e) {
					logger.error(" Error while updating Skill {} and the Error is : {}", skillDto.getSkillName(),
							e.getMessage());
				}
				
				UserProfile userProfile = userProfileRepository.findByUserId(skillDto.getUpdatedBy().getUserId());
				skill.setUpdatedBy(userProfile);

				
				return skillRepository.save(skill);
		
	}

	public void deleteSkillById(long skillId) {
		skillRepository.deleteById(skillId);
		
	}

	public SkillDto getSkillBySkillId(long skillId) {
		Skill  skill = skillRepository.findBySkillId(skillId);
		return CommonBuilder.buildSkillDto(skill);
	}

	public List<SkillDto> getAllSkills() {
		List<Skill>  skillList = skillRepository.findAll();
		return CommonBuilder.buildSkillDtoList(skillList);
	}


	public Page<SkillDto> getAllSkillsByPagination(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("skillId").descending());

		// Fetch paginated data for Skill
		Page<Skill> skillPage = skillRepository.findAll(pageable);

		// Convert the Skill entities into SkillDto
		List<SkillDto> skillDtos = skillPage.stream()
		        .map(skill -> new SkillDto(
		                skill.getSkillId(),
		                skill.getSkillName(),
		                skill.getDescription(),
		                skill.getInsertedDate(),
		                skill.getUpdatedDate(),
		                skill.getCreatedBy(),
		                skill.getUpdatedBy()
		                ))
		        .collect(Collectors.toList());

		// Get the total number of elements
		long totalElements = skillPage.getTotalElements();

		// Return as PageImpl
		return new PageImpl<>(skillDtos, pageable, totalElements);

		
	}


	public Object getSkillNameExist(String skillName) {
		return skillRepository.findBySkillName(skillName);
	}

	
	
	
	
	
	
	
}
