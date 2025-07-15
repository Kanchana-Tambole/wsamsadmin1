package com.ws.spring.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_ws_user")
@DynamicUpdate(value = true)
@SequenceGenerator(name = "userseq", sequenceName = "user_seq", allocationSize = 1, initialValue = 1)
public class UserProfile {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userseq")
    private long userId;

    private String fullName;
    
    @Column(unique = true)
    private String userName;
    
    @Column(unique = true)
    private String mobileNumber;
    
    
    @Column(unique = true)
    private String email;
    
    private String password;
    
    private String otp;
    
    private String imeiNumber;
    
    private String roleName;
    
    private boolean isDelete = Boolean.FALSE;
    
    @CreationTimestamp
    private LocalDateTime insertedDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;
    
    @OneToOne
	@JsonIgnore
    private Role role;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_user_id")
    private UserProfile createdBy;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "updated_user_id")
    private UserProfile updatedBy;
    
 
    public UserProfile() {
        super();
    }

    public UserProfile(UserProfile userProfile) {
        this.userId = userProfile.userId;
        this.userName = userProfile.userName;
        this.fullName = userProfile.fullName;
        this.password = userProfile.password;
        this.role = userProfile.getRole();
        this.mobileNumber = userProfile.mobileNumber;
        this.email = userProfile.email;
    }

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getImeiNumber() {
		return imeiNumber;
	}

	public void setImeiNumber(String imeiNumber) {
		this.imeiNumber = imeiNumber;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserProfile getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserProfile createdBy) {
		this.createdBy = createdBy;
	}

	public UserProfile getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserProfile updatedBy) {
		this.updatedBy = updatedBy;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	

}
