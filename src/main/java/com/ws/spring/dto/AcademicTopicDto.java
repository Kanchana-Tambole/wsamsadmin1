package com.ws.spring.dto;
 
import java.time.LocalDateTime;
 
import com.ws.spring.model.AcademicModule;

import com.ws.spring.model.UserProfile;
 
import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;
 
@Data

@Builder

@NoArgsConstructor

public class AcademicTopicDto {
 
	

	    private long topicId;

	    private String topicName;

		private String description;
 
		private String videoUrl;

        private LocalDateTime insertedDate;

		private LocalDateTime updatedDate;

		private UserProfileDtoList createdBy;

		private UserProfileDtoList updatedBy;
 
		private AcademicModuleDtoList academicModuleDtoList;
 
		public AcademicTopicDto(long topicId, String topicName, String description, String videoUrl,

				LocalDateTime insertedDate, LocalDateTime updatedDate, UserProfile createdBy,

				UserProfile updatedBy, AcademicModule academicModuleDtoList) {

			super();

			this.topicId = topicId;

			this.topicName = topicName;

			this.description = description;

			this.videoUrl = videoUrl;

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

			if(academicModuleDtoList==null)

			{

				this.academicModuleDtoList=null;

			}

			else

			{

				this.academicModuleDtoList = new AcademicModuleDtoList(academicModuleDtoList.getModuleId(), academicModuleDtoList.getModuleName());

			}

		}
 
		public AcademicTopicDto(long topicId, String topicName, String description, String videoUrl,

				LocalDateTime insertedDate, LocalDateTime updatedDate, UserProfileDtoList createdBy,

				UserProfileDtoList updatedBy, AcademicModuleDtoList academicModuleDtoList) {

			super();

			this.topicId = topicId;

			this.topicName = topicName;

			this.description = description;

			this.videoUrl = videoUrl;

			this.insertedDate = insertedDate;

			this.updatedDate = updatedDate;

			this.createdBy = createdBy;

			this.updatedBy = updatedBy;

			this.academicModuleDtoList = academicModuleDtoList;

		}
 
		



}

 