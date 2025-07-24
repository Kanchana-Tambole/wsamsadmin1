package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class TestTypeDto {

    private Long id;

    private String testName;

    private String description;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    public TestTypeDto(Long id, String testName, String description, Boolean isActive,
                       LocalDateTime createdAt, LocalDateTime updatedAt,
                       UserProfile createdBy, UserProfile updatedBy) {

        this.id = id;
        this.testName = testName;
        this.description = description;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        if (createdBy == null) {
            this.createdBy = null;
        } else {
            this.createdBy = new UserProfileDtoList(
                createdBy.getUserId(),
                createdBy.getFullName(),
                createdBy.getUserName(),
                createdBy.getMobileNumber()
            );
        }

        if (updatedBy == null) {
            this.updatedBy = null;
        } else {
            this.updatedBy = new UserProfileDtoList(
                updatedBy.getUserId(),
                updatedBy.getFullName(),
                updatedBy.getUserName(),
                updatedBy.getMobileNumber()
            );
        }
    }

    public TestTypeDto(Long id, String testName, String description, Boolean isActive,
                       LocalDateTime createdAt, LocalDateTime updatedAt,
                       UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {

        this.id = id;
        this.testName = testName;
        this.description = description;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
