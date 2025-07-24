package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.BankTypeDto;
import com.ws.spring.dto.BankTypeDtoList;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.BankType;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.BankTypeRepository;
import com.ws.spring.repository.UserProfileRepository;

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
public class BankTypeServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private BankTypeRepository bankTypeRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public BankTypeDto getBankTypeById(long id) {
        BankType bankType = bankTypeRepository.findById(id);
        return CommonBuilder.buildBankTypeDto(bankType);
    }

    public List<BankTypeDtoList> getAllBankTypes() {
        List<BankType> bankList = bankTypeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return CommonBuilder.buildBankTypeDtoList(bankList);
    }

    public Page<BankTypeDto> getAllBankTypesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<BankType> page = bankTypeRepository.findAll(pageable);
        int totalElements = (int) page.getTotalElements();

        return new PageImpl<>(
            page.stream().map(bankType -> new BankTypeDto(
                bankType.getId(),
                bankType.getBankName(),
                bankType.getBranchName(),
                bankType.getIfscCode(),
                bankType.getStatus(),
                bankType.getCreatedAt(),
                bankType.getUpdatedAt(),
                bankType.getCreatedBy(),
                bankType.getUpdatedBy()
            )).collect(Collectors.toList()),
            pageable,
            totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BankType createBankType(BankTypeDto dto) {
        BankType bankType = new BankType();
        BeanUtils.copyProperties(dto, bankType, "createdBy", "updatedBy");

        UserProfile userProfile = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        bankType.setCreatedBy(userProfile);
        bankType.setUpdatedBy(userProfile);

        return bankTypeRepository.save(bankType);
    }

    public BankType getBankByBankName(String bankName) {
        return bankTypeRepository.findByBankName(bankName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public BankType updateBankType(BankTypeDto dto) {
    	BankType bankType = bankTypeRepository.findById(dto.getId())
    	        .orElseThrow(() -> new RuntimeException("BankType not found with id: " + dto.getId()));
        try {
            bankType.setBankName(dto.getBankName());
            bankType.setBranchName(dto.getBranchName());
            bankType.setIfscCode(dto.getIfscCode());
            bankType.setStatus(dto.getStatus());
        } catch (Exception e) {
            logger.error("Error while updating BankType {}: {}", dto.getBankName(), e.getMessage());
        }

        UserProfile updatedUser = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
        bankType.setUpdatedBy(updatedUser);

        return bankTypeRepository.save(bankType);
    }

    public void deleteBankTypeById(long id) {
        bankTypeRepository.deleteById(id);
    }
}
