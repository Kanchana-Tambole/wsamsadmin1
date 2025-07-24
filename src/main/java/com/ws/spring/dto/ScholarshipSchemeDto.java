package com.ws.spring.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ScholarshipSchemeDto {

    private long id;

    private String schemeName;

    private String type;

    private BigDecimal amount;

    private String eligibility;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    public ScholarshipSchemeDto(long id, String schemeName, String type, BigDecimal amount, String eligibility,
                                 Boolean status, LocalDateTime createdAt, LocalDateTime updatedAt,
                                 UserProfile createdBy, UserProfile updatedBy) {
        this.id = id;
        this.schemeName = schemeName;
        this.type = type;
        this.amount = amount;
        this.eligibility = eligibility;
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

    public ScholarshipSchemeDto(long id, String schemeName, String type, BigDecimal amount, String eligibility,
                                 Boolean status, LocalDateTime createdAt, LocalDateTime updatedAt,
                                 UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        this.id = id;
        this.schemeName = schemeName;
        this.type = type;
        this.amount = amount;
        this.eligibility = eligibility;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
