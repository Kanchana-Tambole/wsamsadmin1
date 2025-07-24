package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.FeesType;
import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class FeesTypeDto {

    private long id;
    private String name;
    private String description;
    private boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UserProfileDtoList createdBy;
    private UserProfileDtoList updatedBy;

    public FeesTypeDto(long id, String name, String description, boolean status, LocalDateTime createdAt,
                       LocalDateTime updatedAt, UserProfile createdBy, UserProfile updatedBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
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

    public FeesTypeDto(long id, String name, String description, boolean status, LocalDateTime createdAt,
                       LocalDateTime updatedAt, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
