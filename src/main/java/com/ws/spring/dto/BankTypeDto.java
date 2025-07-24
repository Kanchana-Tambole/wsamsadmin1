package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class BankTypeDto {

    private Long id;

    private String bankName;

    private String branchName;

    private String ifscCode;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    public BankTypeDto(Long id, String bankName, String branchName, String ifscCode,
                       Boolean status, LocalDateTime createdAt, LocalDateTime updatedAt,
                       UserProfile createdBy, UserProfile updatedBy) {
        this.id = id;
        this.bankName = bankName;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
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

    public BankTypeDto(Long id, String bankName, String branchName, String ifscCode,
                       Boolean status, LocalDateTime createdAt, LocalDateTime updatedAt,
                       UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        this.id = id;
        this.bankName = bankName;
        this.branchName = branchName;
        this.ifscCode = ifscCode;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
