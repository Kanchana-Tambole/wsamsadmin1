package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CertificateTypeDto {

    private long typeId;

    private String typeCode;

    private String certificateName;

    private String templatePath;

    private boolean isPrintable;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    public CertificateTypeDto(long typeId, String typeCode, String certificateName, String templatePath, boolean isPrintable,
                              LocalDateTime insertedDate, LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
        super();
        this.typeId = typeId;
        this.typeCode = typeCode;
        this.certificateName = certificateName;
        this.templatePath = templatePath;
        this.isPrintable = isPrintable;
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

    public CertificateTypeDto(long typeId, String typeCode, String certificateName, String templatePath, boolean isPrintable,
                              LocalDateTime insertedDate, LocalDateTime updatedDate,
                              UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        super();
        this.typeId = typeId;
        this.typeCode = typeCode;
        this.certificateName = certificateName;
        this.templatePath = templatePath;
        this.isPrintable = isPrintable;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
