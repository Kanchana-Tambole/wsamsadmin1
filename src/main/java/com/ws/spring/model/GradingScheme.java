package com.ws.spring.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "t_ws_grading_scheme", uniqueConstraints = {
    @UniqueConstraint(columnNames = "scheme_name")
})
@DynamicUpdate
@SequenceGenerator(name = "gradingschemeseq", sequenceName = "grading_scheme_seq", allocationSize = 1, initialValue = 1)
public class GradingScheme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gradingschemeseq")
    private Long id;

    @Column(name = "scheme_name", nullable = false, unique = true)
    private String schemeName;

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

    // === Getters & Setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
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

    // Optional: Enum validation in setter or service layer
    public enum ValidSchemes {
        Marks, CGPA, Grade
    }
}
