package com.ws.spring.dto;
 
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@Builder
@NoArgsConstructor
public class AcademicCourseDtoList {
 
	
	private long courseId;
 
    private String courseName;
 
	public AcademicCourseDtoList(long courseId, String courseName) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
	}

}