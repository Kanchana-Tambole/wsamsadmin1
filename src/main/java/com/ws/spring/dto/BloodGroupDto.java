package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class BloodGroupDto {

    private long id;
<<<<<<< HEAD

    private String bloodGroup;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

=======
    private String bloodGroup;

    private LocalDateTime insertedDate;
    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;
    private UserProfileDtoList updatedBy;

    // Constructor with UserProfile model as input
>>>>>>> daccd45 (Initial commit)
    public BloodGroupDto(long id, String bloodGroup, LocalDateTime insertedDate,
                         LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
        super();
        this.id = id;
        this.bloodGroup = bloodGroup;
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
=======
    // Constructor with UserProfileDtoList as input
>>>>>>> daccd45 (Initial commit)
    public BloodGroupDto(long id, String bloodGroup, LocalDateTime insertedDate,
                         LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        super();
        this.id = id;
        this.bloodGroup = bloodGroup;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
