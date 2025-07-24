package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD


	@Data
	@Builder
	@NoArgsConstructor
	public class CountryDto {
		
	    private long countryId;
		
	    private String countryName;
	    
	    private String description;
	        
	    private LocalDateTime insertedDate;
	    
	    private LocalDateTime updatedDate;
	    
	    private UserProfileDtoList createdBy;
	    
	    private UserProfileDtoList updatedBy;
	    
	    
	    public CountryDto(long countryId, String countryName, String description, LocalDateTime insertedDate,
				LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
			super();
			this.setCountryId(countryId);
			this.setCountryName(countryName);
			this.setDescription(description);
			this.setInsertedDate(insertedDate);
			this.setUpdatedDate(updatedDate);
			if(createdBy==null)
			{
				this.setCreatedBy(null);
			}  else
			{
			
			this.setCreatedBy(new UserProfileDtoList(createdBy.getUserId(),createdBy.getFullName(),createdBy.getUserName(),createdBy.getMobileNumber()));

			}
		
			if(updatedBy==null)
			{
				this.setUpdatedBy(null);
			}
			else
			{
				this.setUpdatedBy(new UserProfileDtoList(updatedBy.getUserId(), updatedBy.getFullName(), updatedBy.getUserName(), updatedBy.getMobileNumber()));
			}
		}

		public CountryDto(long countryId, String countryName, String description, LocalDateTime insertedDate,
				LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
			super();
			this.setCountryId(countryId);
			this.setCountryName(countryName);
			this.setDescription(description);
			this.setInsertedDate(insertedDate);
			this.setUpdatedDate(updatedDate);
			this.setCreatedBy(createdBy);
			this.setUpdatedBy(updatedBy);
		}

		public long getCountryId() {
			return countryId;
		}

		public void setCountryId(long countryId) {
			this.countryId = countryId;
		}

		public String getCountryName() {
			return countryName;
		}

		public void setCountryName(String countryName) {
			this.countryName = countryName;
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

		public UserProfileDtoList getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(UserProfileDtoList createdBy) {
			this.createdBy = createdBy;
		}

		public UserProfileDtoList getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(UserProfileDtoList updatedBy) {
			this.updatedBy = updatedBy;
		}
	    
	    

	}

=======
@Data
@Builder
@NoArgsConstructor
public class CountryDto {

    private long countryId;

    private String countryName;

    private String description;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    public CountryDto(long countryId, String countryName, String description, LocalDateTime insertedDate,
                      LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
        super();
        this.countryId = countryId;
        this.countryName = countryName;
        this.description = description;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;

        if (createdBy == null) {
            this.createdBy = null;
        } else {
            this.createdBy = new UserProfileDtoList(
                    createdBy.getUserId(),
                    createdBy.getFullName(),
                    createdBy.getUserName(),
                    createdBy.getMobileNumber()
            );
        }

        if (updatedBy == null) {
            this.updatedBy = null;
        } else {
            this.updatedBy = new UserProfileDtoList(
                    updatedBy.getUserId(),
                    updatedBy.getFullName(),
                    updatedBy.getUserName(),
                    updatedBy.getMobileNumber()
            );
        }
    }

    public CountryDto(long countryId, String countryName, String description, LocalDateTime insertedDate,
                      LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
        super();
        this.countryId = countryId;
        this.countryName = countryName;
        this.description = description;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
>>>>>>> daccd45 (Initial commit)
