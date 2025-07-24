package com.ws.spring.model;

import java.time.LocalDateTime;
<<<<<<< HEAD

=======
>>>>>>> daccd45 (Initial commit)
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "t_ws_student_profile")
@DynamicUpdate(value = true)
<<<<<<< HEAD
@SequenceGenerator(name = "studentprofileseq", sequenceName = "student_profile_seq", allocationSize = 1, initialValue = 1)
=======
@SequenceGenerator(
    name = "studentprofileseq",
    sequenceName = "student_profile_seq",
    allocationSize = 1,
    initialValue = 1
)
>>>>>>> daccd45 (Initial commit)
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "studentprofileseq")
    private long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String fatherName;
    private String motherName;

<<<<<<< HEAD
=======
    private String fileName;   // ✅ added
    private String filePath;   // ✅ added

>>>>>>> daccd45 (Initial commit)
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "course_subject_id")
    private CourseSubject courseSubject;

<<<<<<< HEAD
=======
    @ManyToOne
    @JoinColumn(name = "blood_group_id")
    private BloodGroup bloodGroup;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "academic_year_id")
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "religion_id")
    private Religion religion;

    @ManyToOne
    @JoinColumn(name = "caste_id")
    private Caste caste;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

>>>>>>> daccd45 (Initial commit)
    @CreationTimestamp
    private LocalDateTime insertedDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @ManyToOne
    @JoinColumn(name = "created_user_id")
    private UserProfile createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_user_id")
    private UserProfile updatedBy;

<<<<<<< HEAD
=======
    // Constructors
    public StudentProfile() {}

>>>>>>> daccd45 (Initial commit)
    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

<<<<<<< HEAD
=======
    public String getFileName() {  // ✅ added
        return fileName;
    }

    public void setFileName(String fileName) {  // ✅ added
        this.fileName = fileName;
    }

    public String getFilePath() {  // ✅ added
        return filePath;
    }

    public void setFilePath(String filePath) {  // ✅ added
        this.filePath = filePath;
    }

>>>>>>> daccd45 (Initial commit)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseSubject getCourseSubject() {
        return courseSubject;
    }

    public void setCourseSubject(CourseSubject courseSubject) {
        this.courseSubject = courseSubject;
    }

<<<<<<< HEAD
=======
    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public Caste getCaste() {
        return caste;
    }

    public void setCaste(Caste caste) {
        this.caste = caste;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

>>>>>>> daccd45 (Initial commit)
    public LocalDateTime getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(LocalDateTime insertedDate) {
        this.insertedDate = insertedDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public UserProfile getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserProfile createdBy) {
        this.createdBy = createdBy;
    }

    public UserProfile getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UserProfile updatedBy) {
        this.updatedBy = updatedBy;
    }
}
