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
import com.ws.spring.dto.PromoDto;
import com.ws.spring.model.Promo;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.PromoRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class PromoServiceImpl {
	
	
        Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	     @Autowired
	     PromoRepository promoRepository;
	
	
	     @Autowired
	 	 UserProfileRepository userProfileRepository;
	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Promo createPromo(PromoDto promoDto) {
		
		
		 Promo promo = new Promo();
		 BeanUtils.copyProperties(promoDto, promo,"createdBy","updatedBy");
		 
		 
		 UserProfile userProfile = userProfileRepository.findByUserId(promoDto.getCreatedBy().getUserId());
		 promo.setCreatedBy(userProfile);
		 promo.setUpdatedBy(userProfile);
		 
		 
		return promoRepository.save(promo);
	}

	public PromoDto getPromoByPromoId(long promoId) {
		
		Promo promo = promoRepository.findByPromoId(promoId);
		return CommonBuilder.buildPromoDto(promo);
	}

	@Transactional
	public void deletePromoById(long promoId) {
	    promoRepository.deleteById(promoId);
	}


	public Page<PromoDto> getAllPromoByPagination(int pageNumber, int pageSize) {
		 
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("promoId").descending());
		
		Page<Promo> promoPage = promoRepository.findAllByIsDeleteIsFalse(pageable);
		
        int totalElements = (int) promoPage.getTotalElements();
        return new PageImpl<PromoDto>(promoPage
                .stream()
                .map(promo -> new PromoDto(
                		promo.getPromoId(),
                		promo.getPromoName(), promo.getDescription(),promo.getYouTube(), promo.getInsertedDate(), promo.getUpdatedDate(),  promo.getCreatedBy(), promo.getUpdatedBy()))
                     
                .collect(Collectors.toList()), pageable, totalElements);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public Promo updatePromo(PromoDto promoDto) {
		
		Promo promo = promoRepository.findByPromoId(promoDto.getPromoId());
		
		try {
		
			promo.setPromoName(promoDto.getPromoName());
			promo.setDescription(promoDto.getDescription());
			promo.setYouTube(promoDto.getYouTube());
		
		
		} catch (Exception e) {
			logger.error(" Error while updating Promo {} and the Error is : {}", promoDto.getPromoName(),
					e.getMessage());
		}
		
		UserProfile userProfile = userProfileRepository.findByUserId(promoDto.getUpdatedBy().getUserId());
		promo.setUpdatedBy(userProfile);
		
		return promoRepository.save(promo);
	}

	public long getPromoCount() {
		return promoRepository.countByIsDeleteIsFalse();
	}

	
	
	
	
	
	
}
