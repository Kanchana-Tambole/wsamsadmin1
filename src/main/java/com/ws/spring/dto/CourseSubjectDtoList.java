package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class CourseSubjectDtoList {

    private Long courseSubjectId;
    private String subjectName;

    public CourseSubjectDtoList(Long courseSubjectId, String subjectName) {
        this.courseSubjectId = courseSubjectId;
        this.subjectName = subjectName;
    }
}
