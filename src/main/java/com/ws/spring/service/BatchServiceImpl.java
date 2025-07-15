package com.ws.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.dto.BatchDto;
import com.ws.spring.dto.CommonBuilder;
import com.ws.spring.model.Batch;
import com.ws.spring.model.UserProfile;
import com.ws.spring.repository.BatchRepository;
import com.ws.spring.repository.UserProfileRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class BatchServiceImpl {

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    // Create new batch
    public Batch createBatch(BatchDto batchDto) {
        Batch batch = new Batch();
        BeanUtils.copyProperties(batchDto, batch);

        UserProfile userProfile = userProfileRepository.findByUserId(batchDto.getCreatedBy().getUserId());
        batch.setCreatedBy(userProfile);
        batch.setUpdatedBy(userProfile);

        return batchRepository.save(batch);
    }

    // Update existing batch
    public Batch updateBatch(BatchDto batchDto) {
        Batch batch = batchRepository.findByBatchId(batchDto.getBatchId());

        try {
            batch.setBatchName(batchDto.getBatchName());
            batch.setDescription(batchDto.getDescription());
        } catch (Exception e) {
            logger.error("Error while updating Batch {} and the Error is: {}", batchDto.getBatchName(), e.getMessage());
        }

        UserProfile updatedUser = userProfileRepository.findByUserId(batchDto.getUpdatedBy().getUserId());
        batch.setUpdatedBy(updatedUser);

        return batchRepository.save(batch);
    }

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
    public void deleteBatchById(long batchId) {
        batchRepository.deleteById(batchId);
    }

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
    }
}
