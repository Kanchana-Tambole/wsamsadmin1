package com.ws.spring.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "t_ws_feestype")
@DynamicUpdate(value = true)
@SequenceGenerator(name = "feestypeseq", sequenceName = "feestype_seq", allocationSize = 1, initialValue = 1)
public class FeesType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "feestypeseq")
    private long id;

    @Column(name = "name", unique = true, nullable = false, length = 50)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean status = true;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_user_id")
    private UserProfile createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_user_id")
    private UserProfile updatedBy;

    // --- Getters and Setters ---

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
