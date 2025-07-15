package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.Course;
import com.ws.spring.model.CourseSubject;
import com.ws.spring.model.StudentProfile;
import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class StudentProfileDto {

    private long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String fatherName;
    private String motherName;

    private LocalDateTime insertedDate;
    private LocalDateTime updatedDate;

    private CourseDto course;
    private CourseSubjectDto courseSubject;

    private UserProfileDtoList createdBy;
    private UserProfileDtoList updatedBy;

    // Constructor using model entities
    public StudentProfileDto(long id, String firstName, String middleName, String lastName, String gender,
                             String fatherName, String motherName,
                             LocalDateTime insertedDate, LocalDateTime updatedDate,
                             Course course, CourseSubject courseSubject,
                             UserProfile createdBy, UserProfile updatedBy) {

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;

        this.course = course != null ? new CourseDto(course, false) : null;
        this.courseSubject = courseSubject != null ? new CourseSubjectDto(courseSubject) : null;

        if (createdBy == null) {
            this.createdBy = null;
        } else {
            this.createdBy = new UserProfileDtoList(
                    createdBy.getUserId(),
                    createdBy.getFullName(),
                    createdBy.getUserName(),
                    createdBy.getMobileNumber());
        }

        if (updatedBy == null) {
            this.updatedBy = null;
        } else {
            this.updatedBy = new UserProfileDtoList(
                    updatedBy.getUserId(),
                    updatedBy.getFullName(),
                    updatedBy.getUserName(),
                    updatedBy.getMobileNumber());
        }
    }

    // Constructor using DTOs directly
    public StudentProfileDto(long id, String firstName, String middleName, String lastName, String gender,
                             String fatherName, String motherName,
                             LocalDateTime insertedDate, LocalDateTime updatedDate,
                             CourseDto course, CourseSubjectDto courseSubject,
                             UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.course = course;
        this.courseSubject = courseSubject;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
