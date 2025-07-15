package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;

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
import org.springframework.util.StringUtils;

import com.ws.common.util.StringUtil;
import com.ws.spring.dto.AdvertisementDto;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.Advertisement;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.AdvertisementRepository;
import com.ws.spring.repository.UserProfileRepository;

@Service
public class AdvertisementServiceImpl {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    FileStorageService fileStoreService;

    @Transactional(propagation = Propagation.REQUIRED)
    public Advertisement createAdvertisement(AdvertisementDto advertisementDto) {
        Advertisement advertisement = new Advertisement();
        BeanUtils.copyProperties(advertisementDto, advertisement, "createdBy", "updatedBy");

        UserProfile userProfile = userProfileRepository.findByUserId(advertisementDto.getCreatedBy().getUserId());
        advertisement.setCreatedBy(userProfile);
        advertisement.setUpdatedBy(userProfile);
        advertisementRepository.save(advertisement);

        String fileName = advertisementDto.getFileName();
        try {
            if (!StringUtil.checkNullOrEmpty(fileName) && 
                (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
              || fileName.endsWith(".gif") || fileName.endsWith(".mpeg") || fileName.endsWith(".mp4"))) {

                String fileDirectory = String.valueOf(advertisement.getAdvertisementId());
                String newFilePath = fileStoreService.moveFile("advertisement", fileName, fileDirectory);
                logger.info("Advertisement file name : {} and file path : {}", advertisement.getFileName(), newFilePath);
                if (!StringUtils.isEmpty(newFilePath)) {
                    advertisement.setFilePath(newFilePath);
                }
            }
            return advertisementRepository.save(advertisement);

        } catch (EntityExistsException e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
        }

        return advertisement;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Advertisement updateAdvertisement(AdvertisementDto advertisementDto) {
        Advertisement advertisement = advertisementRepository.findByAdvertisementId(advertisementDto.getAdvertisementId());
        advertisement.setAdvertisementName(advertisementDto.getAdvertisementName());
        advertisement.setFileName(advertisementDto.getFileName());
        advertisement.setDescription(advertisementDto.getDescription());

        UserProfile userProfile = userProfileRepository.findByUserId(advertisementDto.getUpdatedBy().getUserId());
        advertisement.setUpdatedBy(userProfile);
        advertisementRepository.save(advertisement);

        String fileName = advertisementDto.getFileName();
        try {
            if (!StringUtil.checkNullOrEmpty(fileName) && 
                (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png")
              || fileName.endsWith(".gif") || fileName.endsWith(".mpeg") || fileName.endsWith(".mp4"))) {

                String fileDirectory = String.valueOf(advertisement.getAdvertisementId());
                String newFilePath = fileStoreService.moveFile("advertisement", fileName, fileDirectory);
                logger.info("Advertisement file name : {} and file path : {}", advertisement.getFileName(), newFilePath);
                if (!StringUtils.isEmpty(newFilePath)) {
                    advertisement.setFilePath(newFilePath);
                }
            }
            return advertisementRepository.save(advertisement);

        } catch (EntityExistsException e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception occurred : {}", e.getMessage(), e);
        }
        return advertisement;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAdvertisementById(long advertisementId) {
        advertisementRepository.deleteById(advertisementId);
    }

    public AdvertisementDto getAdvertisementByAdvertisementId(long advertisementId) {
        Advertisement advertisement = advertisementRepository.findByAdvertisementId(advertisementId);
        return CommonBuilder.buildAdvertisementDto(advertisement);
    }

    public List<AdvertisementDto> queryAllAdvertisement() {
        List<Advertisement> advertisementList = advertisementRepository.findAll(Sort.by(Sort.Direction.DESC, "advertisementId"));
        return CommonBuilder.buildAdvertisementDtoList(advertisementList);
    }

    public Page<AdvertisementDto> getAllAdvertisementByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("advertisementId").descending());
        Page<Advertisement> advertisementPage = advertisementRepository.findAll(pageable);
        int totalElements = (int) advertisementPage.getTotalElements();

        return new PageImpl<>(
            advertisementPage.stream()
                .map(advertisement -> new AdvertisementDto(
                    advertisement.getAdvertisementId(),
                    advertisement.getAdvertisementName(),
                    advertisement.getFileName(),
                    advertisement.getFilePath(),
                    advertisement.getDescription(),
                    advertisement.getInsertedDate(),
                    advertisement.getUpdatedDate(),
                    advertisement.getCreatedBy(),
                    advertisement.getUpdatedBy()))
                .collect(Collectors.toList()),
            pageable,
            totalElements
        );
    }

    public long getBannerCount() {
        return advertisementRepository.count();
    }
}
