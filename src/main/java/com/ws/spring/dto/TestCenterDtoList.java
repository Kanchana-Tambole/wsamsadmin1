package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class TestCenterDtoList {

    private Long id;
    private String centerName;

    public TestCenterDtoList(Long id, String centerName) {
        this.id = id;
        this.centerName = centerName;
    }
}
