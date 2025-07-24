package com.ws.spring.dto;

import java.time.LocalDateTime;

import com.ws.spring.model.UserProfile;
<<<<<<< HEAD
=======
import com.ws.spring.model.State;
>>>>>>> daccd45 (Initial commit)

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD

	@Data
	@Builder
	@NoArgsConstructor
	public class CityDto {
		
	    private long cityId;
		
	    private String cityName;
	    
	    private String pincode;
	    
	    private String description;
	        
	    private LocalDateTime insertedDate;
	    
	    private LocalDateTime updatedDate;
	    
	    private UserProfileDtoList createdBy;
	    
	    private UserProfileDtoList updatedBy;
	    
	    
	    public CityDto(long cityId, String cityName, String pincode, String description, LocalDateTime insertedDate,
				LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy) {
			super();
			this.cityId = cityId;
			this.cityName = cityName;
			this.pincode = pincode;
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

		public CityDto(long cityId, String cityName, String pincode,String description, LocalDateTime insertedDate,
				LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy) {
			super();
			this.cityId = cityId;
			this.cityName = cityName;
			this.pincode = pincode;
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
public class CityDto {

    private long cityId;

    private String cityName;

    private String pincode;

    private String description;

    private LocalDateTime insertedDate;

    private LocalDateTime updatedDate;

    private UserProfileDtoList createdBy;

    private UserProfileDtoList updatedBy;

    private StateDtoList state; // ✅ added field for State

    public CityDto(long cityId, String cityName, String pincode, String description, LocalDateTime insertedDate,
                   LocalDateTime updatedDate, UserProfile createdBy, UserProfile updatedBy, State state) {
        super();
        this.cityId = cityId;
        this.cityName = cityName;
        this.pincode = pincode;
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

        if (state == null) {
            this.state = null;
        } else {
            this.state = new StateDtoList(
                state.getStateId(),
                state.getStateName()
            );
        }
    }

    public CityDto(long cityId, String cityName, String pincode, String description, LocalDateTime insertedDate,
                   LocalDateTime updatedDate, UserProfileDtoList createdBy, UserProfileDtoList updatedBy, StateDtoList state) {
        super();
        this.cityId = cityId;
        this.cityName = cityName;
        this.pincode = pincode;
        this.description = description;
        this.insertedDate = insertedDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.state = state;
    }
}
>>>>>>> daccd45 (Initial commit)
