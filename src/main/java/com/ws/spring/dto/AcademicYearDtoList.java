package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AcademicYearDtoList {

    private long id;

    private String name;

    public AcademicYearDtoList(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
