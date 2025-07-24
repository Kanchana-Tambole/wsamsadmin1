package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class DisabilityTypeDto {

    private long id;
    private String name;
    private String description;
    private boolean status;
    private LocalDateTime insertedDate;
    private LocalDateTime updatedDate;
    private UserProfileDtoList createdBy;
    private UserProfileDtoList updatedBy;

    public DisabilityTypeDto(long id, String name, String description, boolean status, LocalDateTime insertedDate,
                              LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;

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

    public DisabilityTypeDto(long id, String name, String description, boolean status, LocalDateTime insertedDate,
                              LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
