package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CityDtoList {

    private long cityId;

    private String cityName;

    private String pincode;

    public CityDtoList(long cityId, String cityName, String pincode) {
        super();
        this.cityId = cityId;
        this.cityName = cityName;
        this.pincode = pincode;
    }
}
