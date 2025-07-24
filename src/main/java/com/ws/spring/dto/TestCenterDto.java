package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class TestCenterDto {

    private Long id;
    private String centerName;
    private String city;
    private String address;
    private String contactNumber;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UserProfileDtoList createdBy;
    private UserProfileDtoList updatedBy;

    public TestCenterDto(Long id, String centerName, String city, String address, String contactNumber, Boolean isActive,
                         LocalDateTime createdAt, LocalDateTime updatedAt,
                         UserProfile createdBy, UserProfile updatedBy) {
        this.id = id;
        this.centerName = centerName;
        this.city = city;
        this.address = address;
        this.contactNumber = contactNumber;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.createdBy = (createdBy == null) ? null :
                new UserProfileDtoList(createdBy.getUserId(), createdBy.getFullName(), createdBy.getUserName(), createdBy.getMobileNumber());

        this.updatedBy = (updatedBy == null) ? null :
                new UserProfileDtoList(updatedBy.getUserId(), updatedBy.getFullName(), updatedBy.getUserName(), updatedBy.getMobileNumber());
    }

    public TestCenterDto(Long id, String centerName, String city, String address, String contactNumber, Boolean isActive,
                         LocalDateTime createdAt, LocalDateTime updatedAt,
                         UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        this.id = id;
        this.centerName = centerName;
        this.city = city;
        this.address = address;
        this.contactNumber = contactNumber;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
