package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.Department;
import com.ws.spring.model.FacultyProfile;
import com.ws.spring.model.JobDesignation;
import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class FacultyProfileDto {

    private long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String qualification;
    private String email;
    private String fatherName;
    private String motherName;

    private LocalDateTime insertedDate;
    private LocalDateTime updatedDate;

    private DepartmentDto department;
    private JobDesignationDto designation;

    private UserProfileDtoList createdBy;
    private UserProfileDtoList updatedBy;

    // Constructor using entity objects
    public FacultyProfileDto(long id, String firstName, String middleName, String lastName, String gender,
                             String qualification, String email, String fatherName, String motherName,
                             LocalDateTime insertedDate, LocalDateTime updatedDate,
                             Department department, JobDesignation designation,
                             UserProfile createdBy, UserProfile updatedBy) {

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.qualification = qualification;
        this.email = email;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;

        this.department = department != null ? new DepartmentDto(
                department.getDepartmentId(),
                department.getDepartmentName(),
                department.getDescription(),
                department.getInsertedDate(),
                department.getUpdatedDate(),
                department.getCreatedBy(),
                department.getUpdatedBy()
        ) : null;

        this.designation = designation != null ? new JobDesignationDto(
                designation.getDesignationId(),
                designation.getDesignationName(),
                designation.getDescription(),
                designation.getInsertedDate(),
                designation.getUpdatedDate(),
                designation.getCreatedBy(),
                designation.getUpdatedBy()
        ) : null;

        if (createdBy != null) {
            this.createdBy = new UserProfileDtoList(
                    createdBy.getUserId(),
                    createdBy.getFullName(),
                    createdBy.getUserName(),
                    createdBy.getMobileNumber()
            );
        }

        if (updatedBy != null) {
            this.updatedBy = new UserProfileDtoList(
                    updatedBy.getUserId(),
                    updatedBy.getFullName(),
                    updatedBy.getUserName(),
                    updatedBy.getMobileNumber()
            );
        }
    }

    // Constructor using DTOs directly
    public FacultyProfileDto(long id, String firstName, String middleName, String lastName, String gender,
                             String qualification, String email, String fatherName, String motherName,
                             LocalDateTime insertedDate, LocalDateTime updatedDate,
                             DepartmentDto department, JobDesignationDto designation,
                             UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.qualification = qualification;
        this.email = email;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.department = department;
        this.designation = designation;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
