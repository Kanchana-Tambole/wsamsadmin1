package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ReligionDto {

    private long religionId;
<<<<<<< HEAD

    private String religionName;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    // ✅ Constructor accepting model.UserProfile (same as BloodGroupDto)
    public ReligionDto(long religionId, String religionName, LocalDateTime insertedDate,
                       LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
        this.religionId = religionId; // ✅ simplified assignments like BloodGroupDto
=======
    private String religionName;
    private LocalDateTime insertedDate;
    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;
    private UserProfileDtoList updatedBy;

    // Constructor using entity UserProfile
    public ReligionDto(long religionId, String religionName, LocalDateTime insertedDate,
                       LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
        this.religionId = religionId;
>>>>>>> daccd45 (Initial commit)
        this.religionName = religionName;
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
    // ✅ Constructor accepting DTOs directly (same as BloodGroupDto)
=======
    // Constructor using UserProfileDtoList
>>>>>>> daccd45 (Initial commit)
    public ReligionDto(long religionId, String religionName, LocalDateTime insertedDate,
                       LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        this.religionId = religionId;
        this.religionName = religionName;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
