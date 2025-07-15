package com.ws.spring.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_ws_course_subject")
public class CourseSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseSubjectId;

    private String subjectName;

    // Many subjects can belong to one course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Getters and Setters
    public Long getCourseSubjectId() {
        return courseSubjectId;
    }

    public void setCourseSubjectId(Long courseSubjectId) {
        this.courseSubjectId = courseSubjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
