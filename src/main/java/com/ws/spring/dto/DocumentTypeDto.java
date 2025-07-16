package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class DocumentTypeDto {

    private long documentTypeId;

    private String typeCode;

    private String documentName;

    private boolean isMandatory;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    public DocumentTypeDto(long documentTypeId, String typeCode, String documentName, boolean isMandatory,
                           LocalDateTime insertedDate, LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
        super();
        this.documentTypeId = documentTypeId;
        this.typeCode = typeCode;
        this.documentName = documentName;
        this.isMandatory = isMandatory;
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

    public DocumentTypeDto(long documentTypeId, String typeCode, String documentName, boolean isMandatory,
                           LocalDateTime insertedDate, LocalDateTime updatedDate,
                           UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        super();
        this.documentTypeId = documentTypeId;
        this.typeCode = typeCode;
        this.documentName = documentName;
        this.isMandatory = isMandatory;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }
}
