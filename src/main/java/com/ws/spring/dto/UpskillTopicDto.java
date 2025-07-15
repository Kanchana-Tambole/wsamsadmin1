package com.ws.spring.dto;
 
import java.time.LocalDateTime;
 
import com.ws.spring.model.UpskillModule;
import com.ws.spring.model.UserProfile;
 
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@Builder
@NoArgsConstructor
public class UpskillTopicDto {
 
	
	    private long topicId;
	    private String topicName;
		private String description;
 
		private String videoUrl;
		private LocalDateTime insertedDate;
		private LocalDateTime updatedDate;
		private UserProfileDtoList createdBy;
		private UserProfileDtoList updatedBy;
 
		private UpskillModuleDtoList upskillModuleDtoList;
 
		public UpskillTopicDto(long topicId, String topicName, String description, String videoUrl,
				LocalDateTime insertedDate, LocalDateTime updatedDate, UserProfile createdBy,
				UserProfile updatedBy, UpskillModule upskillModuleDtoList) {
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
			if(upskillModuleDtoList==null)
			{
				this.upskillModuleDtoList=null;
			}
			else
			{
				this.upskillModuleDtoList = new UpskillModuleDtoList(upskillModuleDtoList.getModuleId(), upskillModuleDtoList.getModuleName());
			}
		}
 
		public UpskillTopicDto(long topicId, String topicName, String description, String videoUrl,
				LocalDateTime insertedDate, LocalDateTime updatedDate, UserProfileDtoList createdBy,
				UserProfileDtoList updatedBy, UpskillModuleDtoList upskillModuleDtoList) {
			super();
			this.topicId = topicId;
			this.topicName = topicName;
			this.description = description;
			this.videoUrl = videoUrl;
			this.insertedDate = insertedDate;
			this.updatedDate = updatedDate;
			this.createdBy = createdBy;
			this.updatedBy = updatedBy;
			this.upskillModuleDtoList = upskillModuleDtoList;
		}

}