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
@Table(name = "t_ws_language")
@DynamicUpdate(value = true)
@SequenceGenerator(name = "languageseq", sequenceName = "language_seq", allocationSize = 1, initialValue = 1)
public class Language {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "languageseq")
    private long languageId;
	
	@Column(unique = true)
    private String languageName;
	
	@Column(columnDefinition = "TEXT")
    private String description;

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

	public long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
