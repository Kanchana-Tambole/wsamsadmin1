package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class PaymentModeDto {

    private Long id;

    private String modeName;

    private String description;

    private Boolean status;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    public PaymentModeDto(Long id, String modeName, String description, Boolean status,
                          LocalDateTime insertedDate, LocalDateTime updatedDate,
                          UserProfile createdBy, UserProfile updatedBy) {
        this.id = id;
        this.modeName = modeName;
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

    public PaymentModeDto(Long id, String modeName, String description, Boolean status,
                          LocalDateTime insertedDate, LocalDateTime updatedDate,
                          UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        this.id = id;
        this.modeName = modeName;
        this.description = description;
        this.status = status;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
