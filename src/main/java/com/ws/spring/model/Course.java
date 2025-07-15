package com.ws.spring.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_ws_course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseSubject> courseSubjects;

    // Getters and Setters
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<CourseSubject> getCourseSubjects() {
        return courseSubjects;
    }

    public void setCourseSubjects(List<CourseSubject> courseSubjects) {
        this.courseSubjects = courseSubjects;
    }
}
