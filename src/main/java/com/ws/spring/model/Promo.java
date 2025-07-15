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
@Table(name = "t_ws_promo")
@DynamicUpdate(value = true)
@SequenceGenerator(name = "promoseq", sequenceName = "promo_seq", allocationSize = 1, initialValue = 1)
public class Promo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "promoseq")
    private long promoId;
	
    private String promoName;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "TEXT")
	private String youTube;
	
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

	public long getPromoId() {
		return promoId;
	}

	public void setPromoId(long promoId) {
		this.promoId = promoId;
	}

	public String getPromoName() {
		return promoName;
	}

	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getYouTube() {
		return youTube;
	}

	public void setYouTube(String youTube) {
		this.youTube = youTube;
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
