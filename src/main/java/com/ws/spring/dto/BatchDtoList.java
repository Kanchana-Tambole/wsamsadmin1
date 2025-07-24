package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class BatchDtoList {

    private long batchId;

    private String batchName;

    public BatchDtoList(long batchId, String batchName) {
        this.batchId = batchId;
        this.batchName = batchName;
    }
}
