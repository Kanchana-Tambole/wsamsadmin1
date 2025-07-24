package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class FeesCategoryDtoList {

    private long id;

    private String name;

    public FeesCategoryDtoList(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
