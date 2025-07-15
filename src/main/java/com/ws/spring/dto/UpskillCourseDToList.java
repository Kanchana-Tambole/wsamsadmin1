package com.ws.spring.dto;
 
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@Builder
@NoArgsConstructor
public class UpskillCourseDToList {
 
	
	    private long courseId;
 
	    private String courseName;
 
		public UpskillCourseDToList(long courseId, String courseName) {
			super();
			this.courseId = courseId;
			this.courseName = courseName;
		}


}