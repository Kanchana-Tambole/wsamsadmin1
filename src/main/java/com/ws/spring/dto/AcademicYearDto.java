package com.ws.spring.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AcademicYearDto {

    private long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean isCurrent;

    private String status;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    // Constructor using UserProfile (Entity)
    public AcademicYearDto(long id, String name, LocalDate startDate, LocalDate endDate,
                           Boolean isCurrent, String status,
                           LocalDateTime insertedDate, LocalDateTime updatedDate,
                           UserProfile createdBy, UserProfile updatedBy) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCurrent = isCurrent;
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

    // Constructor using DTO versions
    public AcademicYearDto(long id, String name, LocalDate startDate, LocalDate endDate,
                           Boolean isCurrent, String status,
                           LocalDateTime insertedDate, LocalDateTime updatedDate,
                           UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCurrent = isCurrent;
        this.status = status;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
