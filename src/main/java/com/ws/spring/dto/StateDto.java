package com.ws.spring.dto;

import java.time.LocalDateTime;

<<<<<<< HEAD
=======
import com.ws.spring.model.Country;
import com.ws.spring.model.State;
>>>>>>> daccd45 (Initial commit)
import com.ws.spring.model.UserProfile;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD


	@Data
	@Builder
	@NoArgsConstructor
	public class StateDto {
		
	    private long stateId;
		
	    private String stateName;
	    
	    private String description;
	        
	    private LocalDateTime insertedDate;
	    
	    private LocalDateTime updatedDate;
	    
	    private UserProfileDtoList createdBy;
	    
	    private UserProfileDtoList updatedBy;
	    
	    
	    public StateDto(long stateId, String stateName, String description, LocalDateTime insertedDate,
				LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
			super();
			this.stateId = stateId;
			this.stateName = stateName;
			this.description = description;
			this.insertedDate = insertedDate;
			this.updatedDate = updatedDate;
			if(createdBy==null)
			{
				this.createdBy=null;
			}  else
			{
			
			this.createdBy = new UserProfileDtoList(createdBy.getUserId(),createdBy.getFullName(),createdBy.getUserName(),createdBy.getMobileNumber());

			}
		
			if(updatedBy==null)
			{
				this.updatedBy=null;
			}
			else
			{
				this.updatedBy = new UserProfileDtoList(updatedBy.getUserId(), updatedBy.getFullName(), updatedBy.getUserName(), updatedBy.getMobileNumber());
			}
		}

		public StateDto(long stateId, String stateName, String description, LocalDateTime insertedDate,
				LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
			super();
			this.stateId = stateId;
			this.stateName = stateName;
			this.description = description;
			this.insertedDate = insertedDate;
			this.updatedDate = updatedDate;
			this.createdBy = createdBy;
			this.updatedBy = updatedBy;
		}
	    
	    

	}

=======
@Data
@Builder
@NoArgsConstructor
public class StateDto {

    private long stateId;

    private String stateName;

    private String description;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    private CountryDtoList country; // ✅ Added country association

    // Constructor using State entity
    public StateDto(State state) {
        if (state != null) {
            this.stateId = state.getStateId();
            this.stateName = state.getStateName();
            this.description = state.getDescription();
            this.insertedDate = state.getInsertedDate();
            this.updatedDate = state.getUpdatedDate();

            UserProfile createdBy = state.getCreatedBy();
            UserProfile updatedBy = state.getUpdatedBy();
            Country country = state.getCountry(); // ✅

            this.createdBy = createdBy != null
                ? new UserProfileDtoList(
                    createdBy.getUserId(),
                    createdBy.getFullName(),
                    createdBy.getUserName(),
                    createdBy.getMobileNumber()
                )
                : null;

            this.updatedBy = updatedBy != null
                ? new UserProfileDtoList(
                    updatedBy.getUserId(),
                    updatedBy.getFullName(),
                    updatedBy.getUserName(),
                    updatedBy.getMobileNumber()
                )
                : null;

            this.country = country != null
                ? new CountryDtoList(
                    country.getCountryId(),
                    country.getCountryName()
                )
                : null;
        }
    }

    // Constructor using UserProfile entities directly
    public StateDto(long stateId, String stateName, String description, LocalDateTime insertedDate,
                    LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy, Country country) {

        this.stateId = stateId;
        this.stateName = stateName;
        this.description = description;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;

        this.createdBy = createdBy != null
            ? new UserProfileDtoList(createdBy.getUserId(), createdBy.getFullName(), createdBy.getUserName(), createdBy.getMobileNumber())
            : null;

        this.updatedBy = updatedBy != null
            ? new UserProfileDtoList(updatedBy.getUserId(), updatedBy.getFullName(), updatedBy.getUserName(), updatedBy.getMobileNumber())
            : null;

        this.country = country != null
            ? new CountryDtoList(country.getCountryId(), country.getCountryName())
            : null;
    }

    // Constructor using only DTOs
    public StateDto(long stateId, String stateName, String description, LocalDateTime insertedDate,
                    LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy,
                    CountryDtoList country) {

        this.stateId = stateId;
        this.stateName = stateName;
        this.description = description;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.country = country;
    }
}
>>>>>>> daccd45 (Initial commit)
