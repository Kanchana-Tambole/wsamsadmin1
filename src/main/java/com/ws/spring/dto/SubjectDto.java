package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class SubjectDto {

    private Long id;

    private String subjectName;

    private String subjectCode;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    // Constructor with UserProfile entities
    public SubjectDto(Long id, String subjectName, String subjectCode, LocalDateTime insertedDate,
                      LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
        this.id = id;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
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

    // Constructor with UserProfileDtoList objects
    public SubjectDto(Long id, String subjectName, String subjectCode, LocalDateTime insertedDate,
                      LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        this.id = id;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
