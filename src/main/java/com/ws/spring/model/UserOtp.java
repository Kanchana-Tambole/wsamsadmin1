package com.ws.spring.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "t_ws_userotp")
@DynamicUpdate(value = true)
@SequenceGenerator(name = "userotpseq", sequenceName = "userotp_seq", allocationSize = 1, initialValue = 1)
public class UserOtp implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3623941922607530091L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "studentseq")
    private long userOtpId;
	
	private String mobileNumber;

	private String otp;
	
	// private String smsResponse;

	private String activity;
	
	private String status;
	
	private boolean isExpired = Boolean.FALSE;
	
	@CreationTimestamp
    private LocalDateTime insertedDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

	public long getUserOtpId() {
		return userOtpId;
	}

	public void setUserOtpId(long userOtpId) {
		this.userOtpId = userOtpId;
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

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isExpired() {
		return isExpired;
	}

	public void setExpired(boolean isExpired) {
		this.isExpired = isExpired;
	}

	public LocalDateTime getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(LocalDateTime insertedDate) {
		this.insertedDate = insertedDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}
