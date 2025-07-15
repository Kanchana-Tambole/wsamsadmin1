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
	@Table(name="t_ws_city")
	@DynamicUpdate(value=true)
	@SequenceGenerator(name="cityseq",sequenceName ="city_seq", allocationSize = 1, initialValue = 1)
	public class City {
		
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO, generator = "cityseq")
	    private long cityId;
		
	    @Column(unique = true)
	    private String cityName;
	    
	    private String pincode;
	    
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

		public long getCityId() {
			return cityId;
		}

		public void setCityId(long cityId) {
			this.cityId = cityId;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
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
