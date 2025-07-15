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
@Table(name = "t_ws_academiccategory")
@DynamicUpdate(value = true)
@SequenceGenerator(name = "academiccategoryseq", sequenceName = "academiccategory_seq", allocationSize = 1, initialValue = 1)
public class AcademicCategory {
 
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "academiccategoryseq")
    private long categoryId;
	@Column(unique = true)
    private String categoryName;
	@Column(columnDefinition = "TEXT")
	private String description;
 
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
 
	public long getCategoryId() {
		return categoryId;
	}
 
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
 
	public String getCategoryName() {
		return categoryName;
	}
 
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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