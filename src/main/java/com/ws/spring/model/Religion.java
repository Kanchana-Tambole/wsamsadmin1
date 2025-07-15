package com.ws.spring.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "t_ws_religion")
@DynamicUpdate(value = true) // ✅ Match BloodGroup's @DynamicUpdate
@SequenceGenerator(
    name = "religionseq",
    sequenceName = "religion_seq",
    allocationSize = 1,
    initialValue = 1
) // ✅ Match BloodGroup's @SequenceGenerator
public class Religion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "religionseq") // ✅ Similar to BloodGroup
    private long religionId;

    @Column(unique = true, nullable = false) // ✅ Match BloodGroup's column constraints
    private String religionName;

    @CreationTimestamp // ✅ Auto timestamp when inserted
    private LocalDateTime insertedDate;

    @UpdateTimestamp // ✅ Auto timestamp when updated
    private LocalDateTime updatedDate;

    @ManyToOne // ✅ CreatedBy relationship
    @JoinColumn(name = "created_user_id")
    private UserProfile createdBy;

    @ManyToOne // ✅ UpdatedBy relationship
    @JoinColumn(name = "updated_user_id")
    private UserProfile updatedBy;

    @OneToMany(mappedBy = "religion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Caste> castes;

    // ✅ Standard getters and setters — consistent with BloodGroup

    public long getReligionId() {
        return religionId;
    }

    public void setReligionId(long religionId) {
        this.religionId = religionId;
    }

    public String getReligionName() {
        return religionName;
    }

    public void setReligionName(String religionName) {
        this.religionName = religionName;
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

    public List<Caste> getCastes() {
        return castes;
    }

    public void setCastes(List<Caste> castes) {
        this.castes = castes;
    }
}
