package com.ws.spring.dto;

import java.time.LocalDateTime;

<<<<<<< HEAD
import com.ws.spring.model.Course;
import com.ws.spring.model.CourseSubject;
import com.ws.spring.model.StudentProfile;
import com.ws.spring.model.UserProfile;

=======
import com.ws.spring.model.StudentProfile;
>>>>>>> daccd45 (Initial commit)
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class StudentProfileDto {

    private long id;
<<<<<<< HEAD

=======
>>>>>>> daccd45 (Initial commit)
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String fatherName;
    private String motherName;

<<<<<<< HEAD
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
=======
    private String fileName;  // ✅ Added
    private String filePath;  // ✅ Added

    private CourseDtoList course;
    private CourseSubjectDtoList courseSubject;
    private BloodGroupDtoList bloodGroup;
    private BatchDtoList batch;
    private AcademicYearDtoList academicYear;
    private CountryDtoList country;
    private ReligionDtoList religion;
    private CasteDtoList caste;
    private StateDtoList state;
    private CityDtoList city;

    private LocalDateTime insertedDate;
    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;
    private UserProfileDtoList updatedBy;

    // ✅ Constructor using StudentProfile entity
    public StudentProfileDto(long id, String firstName, String middleName, String lastName, String gender,
                             String fatherName, String motherName, StudentProfile profile) {
>>>>>>> daccd45 (Initial commit)

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherName = fatherName;
        this.motherName = motherName;
<<<<<<< HEAD
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
=======

        this.fileName = profile.getFileName();  // ✅ Added
        this.filePath = profile.getFilePath();  // ✅ Added

        this.insertedDate = profile.getInsertedDate();
        this.updatedDate = profile.getUpdatedDate();

        this.course = profile.getCourse() == null ? null :
            new CourseDtoList(profile.getCourse().getCourseId(), profile.getCourse().getCourseName());

        this.courseSubject = profile.getCourseSubject() == null ? null :
            new CourseSubjectDtoList(profile.getCourseSubject().getCourseSubjectId(), profile.getCourseSubject().getSubjectName());

        this.bloodGroup = profile.getBloodGroup() == null ? null :
            new BloodGroupDtoList(profile.getBloodGroup().getId(), profile.getBloodGroup().getBloodGroup());

        this.batch = profile.getBatch() == null ? null :
            new BatchDtoList(profile.getBatch().getBatchId(), profile.getBatch().getBatchName());

        this.academicYear = profile.getAcademicYear() == null ? null :
            new AcademicYearDtoList(profile.getAcademicYear().getId(), profile.getAcademicYear().getName());

        this.country = profile.getCountry() == null ? null :
            new CountryDtoList(profile.getCountry().getCountryId(), profile.getCountry().getCountryName());

        this.religion = profile.getReligion() == null ? null :
            new ReligionDtoList(profile.getReligion().getReligionId(), profile.getReligion().getReligionName());

        this.caste = profile.getCaste() == null ? null :
            new CasteDtoList(profile.getCaste().getCasteId(), profile.getCaste().getCasteName());

        this.state = profile.getState() == null ? null :
            new StateDtoList(profile.getState().getStateId(), profile.getState().getStateName());

        this.city = profile.getCity() == null ? null :
            new CityDtoList(profile.getCity().getCityId(), profile.getCity().getCityName(), profile.getCity().getPincode());

        this.createdBy = profile.getCreatedBy() == null ? null :
            new UserProfileDtoList(profile.getCreatedBy().getUserId(), profile.getCreatedBy().getFullName(), profile.getCreatedBy().getUserName(), profile.getCreatedBy().getMobileNumber());

        this.updatedBy = profile.getUpdatedBy() == null ? null :
            new UserProfileDtoList(profile.getUpdatedBy().getUserId(), profile.getUpdatedBy().getFullName(), profile.getUpdatedBy().getUserName(), profile.getUpdatedBy().getMobileNumber());
    }

    // ✅ Full constructor
    public StudentProfileDto(long id, String firstName, String middleName, String lastName, String gender,
                             String fatherName, String motherName,
                             String fileName, String filePath, // ✅ Added
                             CourseDtoList course,
                             CourseSubjectDtoList courseSubject,
                             BloodGroupDtoList bloodGroup,
                             BatchDtoList batch,
                             AcademicYearDtoList academicYear,
                             CountryDtoList country,
                             ReligionDtoList religion,
                             CasteDtoList caste,
                             StateDtoList state,
                             CityDtoList city,
                             LocalDateTime insertedDate,
                             LocalDateTime updatedDate,
                             UserProfileDtoList createdBy,
                             UserProfileDtoList updatedBy) {
>>>>>>> daccd45 (Initial commit)

        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherName = fatherName;
        this.motherName = motherName;
<<<<<<< HEAD
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.course = course;
        this.courseSubject = courseSubject;
=======

        this.fileName = fileName;  // ✅ Added
        this.filePath = filePath;  // ✅ Added

        this.course = course;
        this.courseSubject = courseSubject;
        this.bloodGroup = bloodGroup;
        this.batch = batch;
        this.academicYear = academicYear;
        this.country = country;
        this.religion = religion;
        this.caste = caste;
        this.state = state;
        this.city = city;

        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
>>>>>>> daccd45 (Initial commit)
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
