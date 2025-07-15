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
import com.ws.spring.dto.LanguageDto;
import com.ws.spring.model.Language;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.LanguageRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class LanguageServiceImpl {
	
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	LanguageRepository languageRepository;
	
	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Language createLanguage(LanguageDto languageDto) {
		 
		 Language language = new Language();
		 BeanUtils.copyProperties(languageDto, language,"createdBy","updatedBy");
		
		 UserProfile userProfile = userProfileRepository.findByUserId(languageDto.getCreatedBy().getUserId());
		 language.setCreatedBy(userProfile);
		 language.setUpdatedBy(userProfile);
		 
		 	 
		return languageRepository.save(language) ;
		
	}

	public Language getLanguageNameExist(String languageName) {
		return languageRepository.findByLanguageName(languageName);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public Language updateLanguage(LanguageDto languageDto) {
		Language language = languageRepository.findByLanguageId(languageDto.getLanguageId());
		
		try {
			language.setLanguageName(languageDto.getLanguageName());
			language.setDescription(languageDto.getDescription());
		UserProfile userProfile = userProfileRepository.findByUserId(languageDto.getUpdatedBy().getUserId());
		language.setUpdatedBy(userProfile);
		
		} catch (Exception e) {
			logger.error(" Error while updating Language {} and the Error is : {}", languageDto.getLanguageName(),
					e.getMessage());
		}
		return languageRepository.save(language);
	}

	public LanguageDto getLanguageByLanguageId(long languageId) {
		Language language = languageRepository.findByLanguageId(languageId);
        return CommonBuilder.buildLanguageDto(language);
	}

	public void deleteLanguageById(long languageId) {
     languageRepository.deleteById(languageId);
	
		
	}

	public Page<LanguageDto> getAllLanguageByPagination(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("languageId").descending());
		
		Page<Language> languagePage = languageRepository.findAll(pageable);
		
        int totalElements = (int) languagePage.getTotalElements();
        return new PageImpl<LanguageDto>(languagePage
                .stream()
                .map(language -> new LanguageDto(
                		language.getLanguageId(),
                		language.getLanguageName(), language.getDescription(), language.getInsertedDate(), language.getUpdatedDate(), language.getCreatedBy(), language.getUpdatedBy()))
                     
                .collect(Collectors.toList()), pageable, totalElements);
	}

	public long getLanguageCount() {
		return languageRepository.count();
	}

	public List<LanguageDto> getAllLanguage() {
		List<Language> languageList = languageRepository.findAll(Sort.by(Sort.Direction.DESC, "languageId"));
        return CommonBuilder.buildLanguageDtoList(languageList);
	}

}
