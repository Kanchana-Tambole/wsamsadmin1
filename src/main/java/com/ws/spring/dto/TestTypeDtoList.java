package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class TestTypeDtoList {

    private Long id;

    private String testName;

    public TestTypeDtoList(Long id, String testName) {
        this.id = id;
        this.testName = testName;
    }
}
