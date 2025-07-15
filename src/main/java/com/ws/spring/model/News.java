package com.ws.spring.model;

import java.time.LocalDateTime;

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
@Table(name = "t_ws_news")
@DynamicUpdate(value = true)
@SequenceGenerator(name = "newsseq", sequenceName = "news_seq", allocationSize = 1, initialValue = 1)
public class News {

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "newsseq")
    private long newsId;
	
    private String newsName;
    
    private String photoName;
    
    private String photoPath;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	private boolean isDelete = Boolean.FALSE;

    @CreationTimestamp
    private LocalDateTime insertedDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;
    
    @ManyToOne
    @JoinColumn(name = "created_user_id")
    private UserProfile createdBy;
    
    @ManyToOne
    @JoinColumn(name = "updated_user_id")
    private UserProfile updatedBy;

	public long getNewsId() {
		return newsId;
	}

	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}

	public String getNewsName() {
		return newsName;
	}

	public void setNewsName(String newsName) {
		this.newsName = newsName;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
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
