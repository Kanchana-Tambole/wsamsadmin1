package com.ws.spring.dto;
 
import java.time.LocalDateTime;

import java.util.List;

import java.util.Set;
 
import com.ws.spring.model.AcademicCourse;

import com.ws.spring.model.UserProfile;
 
import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;
 
@Data

@Builder

@NoArgsConstructor

public class AcademicModuleDto {
 
	

	    private long moduleId;

	    private String moduleName;

		private String description;
 
		private LocalDateTime insertedDate;

		private LocalDateTime updatedDate;

		private UserProfileDtoList createdBy;

		private UserProfileDtoList updatedBy;

		private List<AcademicCourseDtoList> academicCourseDtoList;
 
		public AcademicModuleDto(long moduleId, String moduleName, String description, LocalDateTime insertedDate,

				LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy,

				Set<AcademicCourse> academicCourseDtoList) {

			super();

			this.moduleId = moduleId;

			this.moduleName = moduleName;

			this.description = description;

			this.insertedDate = insertedDate;

			this.updatedDate = updatedDate;

			if(createdBy==null)

			{

				this.createdBy=null;

			}  else

			{

			this.createdBy = new UserProfileDtoList(createdBy.getUserId(),createdBy.getFullName(),createdBy.getUserName(),createdBy.getMobileNumber());
 
			}

			if(updatedBy==null)

			{

				this.updatedBy=null;

			}

			else

			{

				this.updatedBy = new UserProfileDtoList(updatedBy.getUserId(), updatedBy.getFullName(), updatedBy.getUserName(), updatedBy.getMobileNumber());

			}

			if(academicCourseDtoList==null)

			{

				this.academicCourseDtoList=null;

			}

			else

			{

				this.academicCourseDtoList = CommonBuilder.buildAcademicCourseDtoList(academicCourseDtoList);

			}

		}
 
		public AcademicModuleDto(long moduleId, String moduleName, String description, LocalDateTime insertedDate,

				LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy,

				List<AcademicCourseDtoList> academicCourseDtoList) {

			super();

			this.moduleId = moduleId;

			this.moduleName = moduleName;

			this.description = description;

			this.insertedDate = insertedDate;

			this.updatedDate = updatedDate;

			this.createdBy = createdBy;

			this.updatedBy = updatedBy;

			this.academicCourseDtoList = academicCourseDtoList;

		}
 
		



}

 