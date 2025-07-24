package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;
<<<<<<< HEAD
=======

>>>>>>> daccd45 (Initial commit)
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class BatchDto {

    private long batchId;

    private String batchName;

    private String description;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

<<<<<<< HEAD
    // Constructor using entity directly
=======
>>>>>>> daccd45 (Initial commit)
    public BatchDto(long batchId, String batchName, String description, LocalDateTime insertedDate,
                    LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.description = description;
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

<<<<<<< HEAD
    // Full constructor using DTOs directly
=======
>>>>>>> daccd45 (Initial commit)
    public BatchDto(long batchId, String batchName, String description, LocalDateTime insertedDate,
                    LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.description = description;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
