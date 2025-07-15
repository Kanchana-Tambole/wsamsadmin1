package com.ws.spring.dto;

import com.ws.spring.model.CourseSubject;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CourseSubjectDto {

    private Long courseSubjectId;
    private String subjectName;

    private CourseDto course; // nested DTO

    public CourseSubjectDto(Long courseSubjectId, String subjectName, CourseDto course) {
        this.courseSubjectId = courseSubjectId;
        this.subjectName = subjectName;
        this.course = course;
    }

    public CourseSubjectDto(CourseSubject courseSubject) {
        this.courseSubjectId = courseSubject.getCourseSubjectId();
        this.subjectName = courseSubject.getSubjectName();
        if (courseSubject.getCourse() != null) {
            this.course = new CourseDto(courseSubject.getCourse(), false); // avoid recursive load
        }
    }
}
