package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class PaymentModeDtoList {

    private Long id;

    private String modeName;

    public PaymentModeDtoList(Long id, String modeName) {
        this.id = id;
        this.modeName = modeName;
    }
}
