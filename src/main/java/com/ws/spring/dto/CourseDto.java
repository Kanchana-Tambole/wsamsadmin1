package com.ws.spring.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.ws.spring.model.Course;
<<<<<<< HEAD
=======
import com.ws.spring.model.CourseSubject;
>>>>>>> daccd45 (Initial commit)

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CourseDto {

    private Long courseId;
    private String courseName;
<<<<<<< HEAD

=======
>>>>>>> daccd45 (Initial commit)
    private List<CourseSubjectDto> courseSubjects;

    public CourseDto(Long courseId, String courseName, List<CourseSubjectDto> courseSubjects) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseSubjects = courseSubjects;
    }

    public CourseDto(Course course) {
        this(course, true);
    }

    public CourseDto(Course course, boolean loadSubjects) {
        this.courseId = course.getCourseId();
        this.courseName = course.getCourseName();

        if (loadSubjects && course.getCourseSubjects() != null) {
            this.courseSubjects = course.getCourseSubjects().stream()
<<<<<<< HEAD
                    .map(subject -> new CourseSubjectDto(subject))
                    .collect(Collectors.toList());
=======
                .map(CourseSubjectDto::new)
                .collect(Collectors.toList());
>>>>>>> daccd45 (Initial commit)
        }
    }
}
