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

@Table(name = "t_ws_academictopic")

@DynamicUpdate(value = true)

@SequenceGenerator(name = "academictopicseq", sequenceName = "academictopic_seq", allocationSize = 1, initialValue = 1)

public class AcademicTopic {
 
	

	@Id

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "academictopicseq")

    private long topicId;

    private String topicName;

	@Column(columnDefinition = "TEXT")

	private String description;
 
	

	@Column(columnDefinition = "TEXT")

	private String videoUrl;
 
 
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


    @ManyToOne(cascade = CascadeType.ALL)

    @JoinColumn(name = "academic_module_id")

    private AcademicModule academicModule;
 
 
	public long getTopicId() {

		return topicId;

	}
 
 
	public void setTopicId(long topicId) {

		this.topicId = topicId;

	}
 
 
	public String getTopicName() {

		return topicName;

	}
 
 
	public void setTopicName(String topicName) {

		this.topicName = topicName;

	}
 
 
	public String getDescription() {

		return description;

	}
 
 
	public void setDescription(String description) {

		this.description = description;

	}
 
 
	public String getVideoUrl() {

		return videoUrl;

	}
 
 
	public void setVideoUrl(String videoUrl) {

		this.videoUrl = videoUrl;

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
 
 
	public AcademicModule getAcademicModule() {

		return academicModule;

	}
 
 
	public void setAcademicModule(AcademicModule academicModule) {

		this.academicModule = academicModule;

	}
 
 
	


}

 