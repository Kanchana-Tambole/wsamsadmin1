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

@Table(name = "t_ws_academiccourse")

@DynamicUpdate(value = true)

@SequenceGenerator(name = "academiccourseseq", sequenceName = "academiccourse_seq", allocationSize = 1, initialValue = 1)

public class AcademicCourse {


	@Id

    @GeneratedValue(strategy = GenerationType.AUTO, generator = "academiccourseseq")

    private long courseId;

	@Column(unique = true)

    private String courseName;

	@Column(columnDefinition = "TEXT")

	private String description;

	private Double courseMrp;

	private Double discount;

	private Double sellingPrice;

    private Double handlingFee;

	private long trailPeriod;

	private long subscriptionDays;

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

    @JoinColumn(name = "academic_category_id")

    private AcademicCategory academicCategory;
 
	public long getCourseId() {

		return courseId;

	}
 
	public void setCourseId(long courseId) {

		this.courseId = courseId;

	}
 
	public String getCourseName() {

		return courseName;

	}
 
	public void setCourseName(String courseName) {

		this.courseName = courseName;

	}
 
	public String getDescription() {

		return description;

	}
 
	public void setDescription(String description) {

		this.description = description;

	}
 
	public Double getCourseMrp() {

		return courseMrp;

	}
 
	public void setCourseMrp(Double courseMrp) {

		this.courseMrp = courseMrp;

	}
 
	public Double getDiscount() {

		return discount;

	}
 
	public void setDiscount(Double discount) {

		this.discount = discount;

	}
 
	public Double getSellingPrice() {

		return sellingPrice;

	}
 
	public void setSellingPrice(Double sellingPrice) {

		this.sellingPrice = sellingPrice;

	}
 
	public Double getHandlingFee() {

		return handlingFee;

	}
 
	public void setHandlingFee(Double handlingFee) {

		this.handlingFee = handlingFee;

	}
 
	public long getTrailPeriod() {

		return trailPeriod;

	}
 
	public void setTrailPeriod(long trailPeriod) {

		this.trailPeriod = trailPeriod;

	}
 
	public long getSubscriptionDays() {

		return subscriptionDays;

	}
 
	public void setSubscriptionDays(long subscriptionDays) {

		this.subscriptionDays = subscriptionDays;

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
 
	public AcademicCategory getAcademicCategory() {

		return academicCategory;

	}
 
	public void setAcademicCategory(AcademicCategory academicCategory) {

		this.academicCategory = academicCategory;

	}
 
	


}

 