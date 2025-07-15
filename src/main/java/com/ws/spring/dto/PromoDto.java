package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class PromoDto {
	
private long promoId;
	
    private String promoName;
	
	private String description;
	
	private String youTube;
	
    private LocalDateTime insertedDate;
	
	private LocalDateTime updatedDate;
	
    private UserProfileDtoList createdBy;
    
    private UserProfileDtoList updatedBy;

	public PromoDto(long promoId, String promoName, String description, String youTube, LocalDateTime insertedDate,
			LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
		super();
		this.promoId = promoId;
		this.promoName = promoName;
		this.description = description;
		this.youTube = youTube;
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

	public PromoDto(long promoId, String promoName, String description, String youTube, LocalDateTime insertedDate,
			LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
		super();
		this.promoId = promoId;
		this.promoName = promoName;
		this.description = description;
		this.youTube = youTube;
		this.insertedDate = insertedDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}
    
    
    

}
