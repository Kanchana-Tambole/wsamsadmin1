package com.ws.spring.security.jwt;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private long userId;
    private String userName;
    private String mobileNumber;

    public JwtResponse(String accessToken,long userId,String userName,String mobileNumber) {
        this.token = accessToken;
        this.userId = userId;
        this.userName = userName;
        this.mobileNumber = mobileNumber;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}