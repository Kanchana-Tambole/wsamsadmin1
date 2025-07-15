package com.ws.spring.model;
 
import java.time.LocalDateTime;
import java.util.Set;
 
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
 
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
 
@Entity
@Table(name = "t_ws_upskillmodule")
@DynamicUpdate(value = true)
@SequenceGenerator(name = "upskillmoduleseq", sequenceName = "upskillmodule_seq", allocationSize = 1, initialValue = 1)
public class UpskillModule {
 
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "upskillmoduleseq")
    private long moduleId;
    private String moduleName;
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
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "upskillmodule_upskillcourse", joinColumns = @JoinColumn(name = "module_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<UpskillCourse> upskillCourses;
 
	public long getModuleId() {
		return moduleId;
	}
 
	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}
 
	public String getModuleName() {
		return moduleName;
	}
 
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
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
 
	public Set<UpskillCourse> getUpskillCourses() {
		return upskillCourses;
	}
 
	public void setUpskillCourses(Set<UpskillCourse> upskillCourses) {
		this.upskillCourses = upskillCourses;
	}
 
 
	
}