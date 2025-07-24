package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ScholarshipSchemeDtoList {

    private long id;

    private String schemeName;

    public ScholarshipSchemeDtoList(long id, String schemeName) {
        this.id = id;
        this.schemeName = schemeName;
    }
}
