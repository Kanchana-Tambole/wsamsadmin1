package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.Role;
import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class UserProfileDto {
	
	private long userId;

    private String fullName;
    
    private String userName;
    
    private String mobileNumber;
    
    private String email; 
    
    private String password;
    
    private String newPassword;
    
    private String otp;
    
    private LocalDateTime insertedDate;
    
    private LocalDateTime updatedDate;
    
    private RoleDto roleDto; 
    
    private UserProfileDtoList createdBy;
    
    private UserProfileDtoList updatedBy;

	public UserProfileDto(long userId, String fullName, String userName, String mobileNumber) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
	}

	public UserProfileDto(long userId, String fullName, String userName, String mobileNumber, String email,
			String password, String newPassword, String otp,  LocalDateTime insertedDate,
			LocalDateTime updatedDate, RoleDto roleDto, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.newPassword = newPassword;
		this.otp = otp;
		this.insertedDate = insertedDate;
		this.updatedDate = updatedDate;
		this.roleDto = roleDto;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public UserProfileDto(long userId, String fullName, String userName, String mobileNumber, String email, String password,
			LocalDateTime insertedDate, LocalDateTime updatedDate, Role roleDto, UserProfile createdBy, UserProfile updatedBy) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.insertedDate = insertedDate;
		this.updatedDate = updatedDate;
		
		if(roleDto==null)
		{
			this.roleDto=null;
		}  else
		{
		
		this.roleDto = new RoleDto(roleDto.getRoleId(),roleDto.getRoleName(), null, null);

		}
		
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
    
    
    
    

}
