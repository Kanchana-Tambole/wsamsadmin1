package com.ws.spring.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class UserProfileDtoList {
	
	
	private long userId;

    private String fullName;
    
    private String userName;
    
    private String mobileNumber;
    
    public UserProfileDtoList(long userId, String fullName, String userName, String mobileNumber) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
	}

}
