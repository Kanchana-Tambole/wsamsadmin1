package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CasteDtoList {

    private long casteId;

    private String casteName;

    public CasteDtoList(long casteId, String casteName) {
        this.casteId = casteId;
        this.casteName = casteName;
    }
}
