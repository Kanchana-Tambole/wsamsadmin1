package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ReligionDtoList {

    private long religionId;
    private String religionName;

    public ReligionDtoList(long religionId, String religionName) {
        this.religionId = religionId;
        this.religionName = religionName;
    }
}
