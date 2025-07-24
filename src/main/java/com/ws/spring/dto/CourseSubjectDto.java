package com.ws.spring.dto;

import com.ws.spring.model.CourseSubject;
<<<<<<< HEAD

=======
>>>>>>> daccd45 (Initial commit)
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CourseSubjectDto {

    private Long courseSubjectId;
    private String subjectName;
<<<<<<< HEAD

    private CourseDto course; // nested DTO

    public CourseSubjectDto(Long courseSubjectId, String subjectName, CourseDto course) {
=======
    private CourseDtoList course; // simplified nested DTO

    public CourseSubjectDto(Long courseSubjectId, String subjectName, CourseDtoList course) {
>>>>>>> daccd45 (Initial commit)
        this.courseSubjectId = courseSubjectId;
        this.subjectName = subjectName;
        this.course = course;
    }

    public CourseSubjectDto(CourseSubject courseSubject) {
        this.courseSubjectId = courseSubject.getCourseSubjectId();
        this.subjectName = courseSubject.getSubjectName();
        if (courseSubject.getCourse() != null) {
<<<<<<< HEAD
            this.course = new CourseDto(courseSubject.getCourse(), false); // avoid recursive load
=======
            this.course = new CourseDtoList(courseSubject.getCourse().getCourseId(), courseSubject.getCourse().getCourseName());
>>>>>>> daccd45 (Initial commit)
        }
    }
}
