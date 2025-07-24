package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class BloodGroupDtoList {

    private long id;
    private String bloodGroup;

    public BloodGroupDtoList(long id, String bloodGroup) {
        super();
        this.id = id;
        this.bloodGroup = bloodGroup;
    }
}
