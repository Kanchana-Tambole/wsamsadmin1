package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.Religion;
import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CasteDto {

    private long casteId;

    private String casteName;

    private ReligionDto religion;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

<<<<<<< HEAD
    // Constructor that maps from entity (with UserProfile and Religion models)
=======
    // Constructor with model entities
>>>>>>> daccd45 (Initial commit)
    public CasteDto(long casteId, String casteName, Religion religion, LocalDateTime insertedDate,
                    LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
        this.casteId = casteId;
        this.casteName = casteName;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;

        if (religion == null) {
            this.religion = null;
        } else {
            this.religion = new ReligionDto(
                religion.getReligionId(),
                religion.getReligionName(),
                religion.getInsertedDate(),
                religion.getUpdatedDate(),
                religion.getCreatedBy(),
                religion.getUpdatedBy()
            );
        }

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
    // Constructor with prebuilt DTOs
=======
    // Constructor with DTOs
>>>>>>> daccd45 (Initial commit)
    public CasteDto(long casteId, String casteName, ReligionDto religion,
                    LocalDateTime insertedDate, LocalDateTime updatedDate,
                    UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        this.casteId = casteId;
        this.casteName = casteName;
        this.religion = religion;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
