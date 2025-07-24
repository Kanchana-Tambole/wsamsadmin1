package com.ws.spring.dto;

import com.ws.spring.model.Course;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CourseDtoList {

    private Long courseId;
    private String courseName;

    public CourseDtoList(Long courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public CourseDtoList(Course course) {
        this.courseId = course.getCourseId();
        this.courseName = course.getCourseName();
    }
}
