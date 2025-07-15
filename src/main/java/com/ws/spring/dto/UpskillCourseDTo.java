package com.ws.spring.dto;
 
import java.time.LocalDateTime;
 
import com.ws.spring.model.UpskillCategory;

import com.ws.spring.model.UserProfile;
 
import lombok.Builder;

import lombok.Data;

import lombok.NoArgsConstructor;
 
@Data

@Builder

@NoArgsConstructor

public class UpskillCourseDTo {
 
    private long courseId;
 
    private String courseName;
 
	private String description;

	private Double courseMrp;

	private Double discount;

	private Double sellingPrice;

    private Double handlingFee;

	private long trailPeriod;

	private long subscriptionDays;

	private String videoUrl;

	private LocalDateTime insertedDate;

	private LocalDateTime updatedDate;

	private UserProfileDtoList createdBy;

	private UserProfileDtoList updatedBy;

	private UpskillCategoryDtoList upskillCategoryDtoList;
 
	public UpskillCourseDTo(long courseId, String courseName, String description, Double courseMrp, Double discount,

			Double sellingPrice, Double handlingFee, long trailPeriod, long subscriptionDays, String videoUrl, LocalDateTime insertedDate, LocalDateTime updatedDate,

			UserProfile createdBy, UserProfile updatedBy, UpskillCategory upskillCategoryDtoList) {

		super();

		this.courseId = courseId;

		this.courseName = courseName;

		this.description = description;

		this.courseMrp = courseMrp;

		this.discount = discount;

		this.sellingPrice = sellingPrice;

		this.handlingFee = handlingFee;

		this.trailPeriod = trailPeriod;

		this.subscriptionDays = subscriptionDays;

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

		if(upskillCategoryDtoList==null)

		{

			this.upskillCategoryDtoList=null;

		}

		else

		{

			this.upskillCategoryDtoList = new UpskillCategoryDtoList(upskillCategoryDtoList.getCategoryId(), upskillCategoryDtoList.getCategoryName());

		}

	}
 
	public UpskillCourseDTo(long courseId, String courseName, String description, Double courseMrp, Double discount,

			Double sellingPrice, Double handlingFee, long trailPeriod, long subscriptionDays, String videoUrl,

			LocalDateTime insertedDate, LocalDateTime updatedDate, UserProfileDtoList createdBy,

			UserProfileDtoList updatedBy, UpskillCategoryDtoList upskillCategoryDtoList) {

		super();

		this.courseId = courseId;

		this.courseName = courseName;

		this.description = description;

		this.courseMrp = courseMrp;

		this.discount = discount;

		this.sellingPrice = sellingPrice;

		this.handlingFee = handlingFee;

		this.trailPeriod = trailPeriod;

		this.subscriptionDays = subscriptionDays;

		this.videoUrl = videoUrl;

		this.insertedDate = insertedDate;

		this.updatedDate = updatedDate;

		this.createdBy = createdBy;

		this.updatedBy = updatedBy;

		this.upskillCategoryDtoList = upskillCategoryDtoList;

	}
 
	
 
	


}

 