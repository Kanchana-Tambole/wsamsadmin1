package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class SuccessStoryDto {
	
	
    private long successstoryId;
	
    private String successstoryName;
    
    private String photoName;
    
    private String photoPath;

	private String description;

	private LocalDateTime insertedDate;
	
	private LocalDateTime updatedDate;
	
    private UserProfileDtoList createdBy;
    
    private UserProfileDtoList updatedBy;

	public SuccessStoryDto(long successstoryId, String successstoryName, String photoName, String photoPath,
			String description, LocalDateTime insertedDate, LocalDateTime updatedDate, UserProfile createdBy,
			UserProfile updatedBy) {
		super();
		this.successstoryId = successstoryId;
		this.successstoryName = successstoryName;
		this.photoName = photoName;
		this.photoPath = photoPath;
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
	}

	public SuccessStoryDto(long successstoryId, String successstoryName, String photoName, String photoPath,
			String description, LocalDateTime insertedDate, LocalDateTime updatedDate, UserProfileDtoList createdBy,
			UserProfileDtoList updatedBy) {
		super();
		this.successstoryId = successstoryId;
		this.successstoryName = successstoryName;
		this.photoName = photoName;
		this.photoPath = photoPath;
		this.description = description;
		this.insertedDate = insertedDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}
    
    
    
    
    
    
    
    
    
}
