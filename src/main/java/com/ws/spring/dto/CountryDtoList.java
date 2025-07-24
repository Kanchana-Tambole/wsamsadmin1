package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CountryDtoList {

    private long countryId;

    private String countryName;

    public CountryDtoList(long countryId, String countryName) {
        super();
        this.countryId = countryId;
        this.countryName = countryName;
    }
}
