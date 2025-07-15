package com.ws.spring.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserOtpBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String userName;

    private String emailId;

    private String mobileNumber;

    private String otp;

    private LocalDateTime generatedTime;

    private String activity;

    public UserOtpBean() {

    }

    public UserOtpBean(String mobileNumber, String otp, LocalDateTime generatedTime,
			String activity) {
		super();
		this.mobileNumber = mobileNumber;
		this.otp = otp;
		this.generatedTime = generatedTime;
		this.activity = activity;
	}

    @Override
    public String toString() {
        return String.format(
                "UserOtpBean [userName=%s, mailId=%s, mobileNumber=%s, otp=%s, generatedTime=%s, activity=%s]",
                userName, emailId, mobileNumber, otp, generatedTime, activity);
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getGeneratedTime() {
		return generatedTime;
	}

	public void setGeneratedTime(LocalDateTime generatedTime) {
		this.generatedTime = generatedTime;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

    

}
