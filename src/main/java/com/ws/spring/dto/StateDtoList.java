package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class StateDtoList {

    private long stateId;

    private String stateName;

    public StateDtoList(long stateId, String stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
    }
}
