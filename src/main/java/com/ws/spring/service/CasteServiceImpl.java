package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.CasteDto;
<<<<<<< HEAD
=======
import com.ws.spring.dto.CasteDtoList;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.Caste;
import com.ws.spring.model.Religion;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.CasteRepository;
import com.ws.spring.repository.UserProfileRepository;
import com.ws.spring.repository.ReligionRepository;

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

@Service
public class CasteServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private CasteRepository casteRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private ReligionRepository religionRepository;

<<<<<<< HEAD
    public CasteDto getCasteById(long id) {
        Caste caste = casteRepository.findByCasteId(id);
        return CommonBuilder.buildCasteDto(caste);
    }

    public List<CasteDto> getAllCastes() {
        List<Caste> casteList = casteRepository.findAll(Sort.by(Sort.Direction.DESC, "casteId"));
        return casteList.stream()
            .map(CommonBuilder::buildCasteDto)
            .collect(Collectors.toList());
    }

    public Page<CasteDto> getAllCastesByPagination(int pageNumber, int pageSize) {
=======
    public CasteDto getCasteById(long casteId) {
        Caste caste = casteRepository.findByCasteId(casteId);
        return CommonBuilder.buildCasteDto(caste);
    }

    public List<CasteDtoList> getAllCaste() {
        List<Caste> casteList = casteRepository.findAll(Sort.by(Sort.Direction.DESC, "casteId"));
        return casteList.stream()
                .map(caste -> new CasteDtoList(caste.getCasteId(), caste.getCasteName()))
                .collect(Collectors.toList());
    }

    public Page<CasteDto> getAllCasteByPagination(int pageNumber, int pageSize) {
>>>>>>> daccd45 (Initial commit)
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("casteId").descending());
        Page<Caste> castePage = casteRepository.findAll(pageable);
        int totalElements = (int) castePage.getTotalElements();

        List<CasteDto> dtoList = castePage.stream()
<<<<<<< HEAD
            .map(caste -> new CasteDto(
                caste.getCasteId(),
                caste.getCasteName(),
                caste.getReligion(),
                caste.getInsertedDate(),
                caste.getUpdatedDate(),
                caste.getCreatedBy(),
                caste.getUpdatedBy()
            )).collect(Collectors.toList());
=======
                .map(caste -> new CasteDto(
                        caste.getCasteId(),
                        caste.getCasteName(),
                        caste.getReligion(),
                        caste.getInsertedDate(),
                        caste.getUpdatedDate(),
                        caste.getCreatedBy(),
                        caste.getUpdatedBy()
                )).collect(Collectors.toList());
>>>>>>> daccd45 (Initial commit)

        return new PageImpl<>(dtoList, pageable, totalElements);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Caste createCaste(CasteDto casteDto) {
        Caste caste = new Caste();
        BeanUtils.copyProperties(casteDto, caste, "createdBy", "updatedBy", "religion");

        UserProfile userProfile = userProfileRepository.findByUserId(casteDto.getCreatedBy().getUserId());
        caste.setCreatedBy(userProfile);
        caste.setUpdatedBy(userProfile);

        Religion religion = religionRepository.findByReligionId(casteDto.getReligion().getReligionId());
        caste.setReligion(religion);

        return casteRepository.save(caste);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Caste updateCaste(CasteDto casteDto) {
        Caste caste = casteRepository.findByCasteId(casteDto.getCasteId());

        try {
            caste.setCasteName(casteDto.getCasteName());
<<<<<<< HEAD
            Religion religion = religionRepository.findByReligionId(casteDto.getReligion().getReligionId());
            caste.setReligion(religion);
=======

            Religion religion = religionRepository.findByReligionId(casteDto.getReligion().getReligionId());
            caste.setReligion(religion);

>>>>>>> daccd45 (Initial commit)
        } catch (Exception e) {
            logger.error("Error while updating Caste {}: {}", casteDto.getCasteName(), e.getMessage());
        }

        UserProfile userProfile = userProfileRepository.findByUserId(casteDto.getUpdatedBy().getUserId());
        caste.setUpdatedBy(userProfile);

        return casteRepository.save(caste);
    }

<<<<<<< HEAD
    public void deleteCasteById(long id) {
        casteRepository.deleteById(id);
=======
    public void deleteCasteById(long casteId) {
        casteRepository.deleteById(casteId);
>>>>>>> daccd45 (Initial commit)
    }
}
