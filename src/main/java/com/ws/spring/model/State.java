package com.ws.spring.model;

import java.time.LocalDateTime;
<<<<<<< HEAD

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
=======
import javax.persistence.*;
>>>>>>> daccd45 (Initial commit)

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

<<<<<<< HEAD


	@Entity
	@Table(name="t_ws_state")
	@DynamicUpdate(value=true)
	@SequenceGenerator(name="stateseq",sequenceName ="state_seq", allocationSize = 1, initialValue = 1)
	public class State {
		
		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gotraseq")
	    private long stateId;
		
	    @Column(unique = true)
	    private String stateName;
	    
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

		public long getStateId() {
			return stateId;
		}

		public void setStateId(long stateId) {
			this.stateId = stateId;
		}

		public String getStateName() {
			return stateName;
		}

		public void setStateName(String stateName) {
			this.stateName = stateName;
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
=======
@Entity
@Table(name = "t_ws_state")
@DynamicUpdate(value = true)
@SequenceGenerator(name = "stateseq", sequenceName = "state_seq", allocationSize = 1, initialValue = 1)
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "stateseq") // fixed generator name
    private long stateId;

    @Column(unique = true)
    private String stateName;

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

    // ✅ Association to Country
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    // ------------------------
    // Getters and Setters
    // ------------------------

    public long getStateId() {
        return stateId;
    }

    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
>>>>>>> daccd45 (Initial commit)
}
