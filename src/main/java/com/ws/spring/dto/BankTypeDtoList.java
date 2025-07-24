package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class BankTypeDtoList {

    private Long id;

    private String bankName;

    public BankTypeDtoList(Long id, String bankName) {
        this.id = id;
        this.bankName = bankName;
    }
}
