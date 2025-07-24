package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

<<<<<<< HEAD
import com.ws.spring.dto.BatchDto;
import com.ws.spring.dto.CommonBuilder;
=======
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

import com.ws.spring.dto.BatchDto;
import com.ws.spring.dto.BatchDtoList;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.model.Batch;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.BatchRepository;
import com.ws.spring.repository.UserProfileRepository;

<<<<<<< HEAD
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

=======
>>>>>>> daccd45 (Initial commit)
@Service
public class BatchServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

<<<<<<< HEAD
    // Create new batch
    public Batch createBatch(BatchDto batchDto) {
        Batch batch = new Batch();
        BeanUtils.copyProperties(batchDto, batch);
=======
    public BatchDto getBatchById(long batchId) {
        Batch batch = batchRepository.findByBatchId(batchId);
        return new BatchDto(
                batch.getBatchId(),
                batch.getBatchName(),
                batch.getDescription(),
                batch.getInsertedDate(),
                batch.getUpdatedDate(),
                batch.getCreatedBy(),
                batch.getUpdatedBy()
        );
    }

    public List<BatchDtoList> getAllBatches() {
        List<Batch> batchList = batchRepository.findAll(Sort.by(Sort.Direction.DESC, "batchId"));
        return batchList.stream()
                .map(batch -> new BatchDtoList(batch.getBatchId(), batch.getBatchName()))
                .collect(Collectors.toList());
    }

    public Page<BatchDto> getAllBatchesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("batchId").descending());
        Page<Batch> batchPage = batchRepository.findAll(pageable);
        int totalElements = (int) batchPage.getTotalElements();

        return new PageImpl<>(
                batchPage.stream()
                        .map(batch -> new BatchDto(
                                batch.getBatchId(),
                                batch.getBatchName(),
                                batch.getDescription(),
                                batch.getInsertedDate(),
                                batch.getUpdatedDate(),
                                batch.getCreatedBy(),
                                batch.getUpdatedBy()))
                        .collect(Collectors.toList()),
                pageable,
                totalElements
        );
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Batch createBatch(BatchDto batchDto) {
        Batch batch = new Batch();
        BeanUtils.copyProperties(batchDto, batch, "createdBy", "updatedBy");
>>>>>>> daccd45 (Initial commit)

        UserProfile userProfile = userProfileRepository.findByUserId(batchDto.getCreatedBy().getUserId());
        batch.setCreatedBy(userProfile);
        batch.setUpdatedBy(userProfile);

        return batchRepository.save(batch);
    }

<<<<<<< HEAD
    // Update existing batch
=======
    @Transactional(propagation = Propagation.REQUIRED)
>>>>>>> daccd45 (Initial commit)
    public Batch updateBatch(BatchDto batchDto) {
        Batch batch = batchRepository.findByBatchId(batchDto.getBatchId());

        try {
            batch.setBatchName(batchDto.getBatchName());
            batch.setDescription(batchDto.getDescription());
        } catch (Exception e) {
<<<<<<< HEAD
            logger.error("Error while updating Batch {} and the Error is: {}", batchDto.getBatchName(), e.getMessage());
        }

        UserProfile updatedUser = userProfileRepository.findByUserId(batchDto.getUpdatedBy().getUserId());
        batch.setUpdatedBy(updatedUser);
=======
            logger.error("Error while updating Batch {}: {}", batchDto.getBatchName(), e.getMessage());
        }

        UserProfile userProfile = userProfileRepository.findByUserId(batchDto.getUpdatedBy().getUserId());
        batch.setUpdatedBy(userProfile);
>>>>>>> daccd45 (Initial commit)

        return batchRepository.save(batch);
    }

<<<<<<< HEAD
    // Get single batch by ID
    public BatchDto queryBatchByBatchId(long batchId) {
        Batch batch = batchRepository.findByBatchId(batchId);
        return CommonBuilder.buildBatchDto(batch);
    }

    // Get all batches sorted by insertedDate descending
    public List<BatchDto> queryAllBatches() {
        List<Batch> batchList = batchRepository.findAll(Sort.by(Sort.Direction.DESC, "insertedDate"));
        return CommonBuilder.buildBatchDtoList(batchList);
    }

    // Get batch count
    public long getBatchCount() {
        return batchRepository.count();
    }

    // Delete batch by ID
=======
>>>>>>> daccd45 (Initial commit)
    public void deleteBatchById(long batchId) {
        batchRepository.deleteById(batchId);
    }

<<<<<<< HEAD
    // Get paginated list of batches
    public Page<BatchDto> getAllBatchesByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("batchId").descending());
        Page<Batch> batchPage = batchRepository.findAll(pageable);

        int totalElements = (int) batchPage.getTotalElements();

        return new PageImpl<>(
            batchPage.stream()
                     .map(batch -> new BatchDto(
                         batch.getBatchId(),
                         batch.getBatchName(),
                         batch.getDescription(),
                         batch.getInsertedDate(),
                         batch.getUpdatedDate(),
                         batch.getCreatedBy(),
                         batch.getUpdatedBy()))
                     .collect(Collectors.toList()),
            pageable,
            totalElements
        );
=======
    public Batch getBatchByName(String batchName) {
        return batchRepository.findByBatchName(batchName);
>>>>>>> daccd45 (Initial commit)
    }
}
