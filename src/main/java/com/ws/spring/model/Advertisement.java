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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "t_ws_advertisement")
@DynamicUpdate(value = true)
@SequenceGenerator(name = "advertisementseq", sequenceName = "advertisement_seq", allocationSize = 1, initialValue = 1)
public class Advertisement {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "advertisementseq")
    private long advertisementId;
	
	private String advertisementName;
	
	private String fileName;
	
	private String filePath;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	private boolean isDelete = Boolean.FALSE;

    @CreationTimestamp
    private LocalDateTime insertedDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "created_user_id")
    private UserProfile createdBy;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "updated_user_id")
    private UserProfile updatedBy;



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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
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
    
    
    

	
	
	

}
