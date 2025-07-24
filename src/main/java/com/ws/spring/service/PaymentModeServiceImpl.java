package com.ws.spring.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ws.spring.model.PaymentMode;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.PaymentModeRepository;
import com.ws.spring.repository.UserProfileRepository;
import com.ws.spring.dto.PaymentModeDto;
import com.ws.spring.dto.PaymentModeDtoList;
import com.ws.spring.dto.CommonBuilder;

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
public class PaymentModeServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private PaymentModeRepository paymentModeRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public PaymentModeDto getPaymentModeById(Long id) {
        Optional<PaymentMode> optionalMode = paymentModeRepository.findById(id);
        return optionalMode.map(CommonBuilder::buildPaymentModeDto).orElse(null);
    }

    public List<PaymentModeDtoList> getAllPaymentModes() {
        List<PaymentMode> modeList = paymentModeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return CommonBuilder.buildPaymentModeDtoList(modeList);
    }

    public Page<PaymentModeDto> getAllPaymentModesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        Page<PaymentMode> modePage = paymentModeRepository.findAll(pageable);

        List<PaymentModeDto> dtoList = modePage.stream()
                .map(mode -> new PaymentModeDto(
                        mode.getId(),
                        mode.getModeName(),
                        mode.getDescription(),
                        mode.getStatus(),
                        mode.getInsertedDate(),
                        mode.getUpdatedDate(),
                        mode.getCreatedBy(),
                        mode.getUpdatedBy()
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, modePage.getTotalElements());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PaymentMode createPaymentMode(PaymentModeDto dto) {
        PaymentMode mode = new PaymentMode();
        BeanUtils.copyProperties(dto, mode, "createdBy", "updatedBy");

        UserProfile user = userProfileRepository.findByUserId(dto.getCreatedBy().getUserId());
        mode.setCreatedBy(user);
        mode.setUpdatedBy(user);

        return paymentModeRepository.save(mode);
    }

    public PaymentMode getPaymentModeByName(String modeName) {
        return paymentModeRepository.findByModeName(modeName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public PaymentMode updatePaymentMode(PaymentModeDto dto) {
        Optional<PaymentMode> optionalMode = paymentModeRepository.findById(dto.getId());

        if (!optionalMode.isPresent()) {
            throw new RuntimeException("PaymentMode not found with ID: " + dto.getId());
        }

        PaymentMode mode = optionalMode.get();

        try {
            mode.setModeName(dto.getModeName());
            mode.setDescription(dto.getDescription());
            mode.setStatus(dto.getStatus());

            UserProfile user = userProfileRepository.findByUserId(dto.getUpdatedBy().getUserId());
            mode.setUpdatedBy(user);

        } catch (Exception e) {
            logger.error("Error while updating PaymentMode {}: {}", dto.getModeName(), e.getMessage());
        }

        return paymentModeRepository.save(mode);
    }

    public void deletePaymentModeById(Long id) {
        paymentModeRepository.deleteById(id);
    }
}
