package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AdvertisementDto {
	
    private long advertisementId;
    
    private String advertisementName;
	
	private String fileName;
	
	private String filePath;
	
	private String description;
	
	private LocalDateTime insertedDate;
	
	private LocalDateTime updatedDate;
	
    private UserProfileDtoList createdBy;
    
    private UserProfileDtoList updatedBy;

	public AdvertisementDto(long advertisementId, String advertisementName, String fileName, String filePath,
			String description, LocalDateTime insertedDate, LocalDateTime updatedDate, UserProfile createdBy,
			UserProfile updatedBy) {
		super();
		this.setAdvertisementId(advertisementId);
		this.setAdvertisementName(advertisementName);
		this.setFileName(fileName);
		this.filePath = filePath;
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

	public AdvertisementDto(long advertisementId, String advertisementName, String fileName, String filePath,
			String description, LocalDateTime insertedDate, LocalDateTime updatedDate, UserProfileDtoList createdBy,
			UserProfileDtoList updatedBy) {
		super();
		this.setAdvertisementId(advertisementId);
		this.setAdvertisementName(advertisementName);
		this.setFileName(fileName);
		this.filePath = filePath;
		this.description = description;
		this.insertedDate = insertedDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public long getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(long advertisementId) {
		this.advertisementId = advertisementId;
	}

	public String getAdvertisementName() {
		return advertisementName;
	}

	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
    
    
}
