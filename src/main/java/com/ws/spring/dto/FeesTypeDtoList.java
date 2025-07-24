package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class FeesTypeDtoList {

    private long id;
    private String name;

    public FeesTypeDtoList(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
