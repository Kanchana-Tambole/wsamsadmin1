package com.ws.spring.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "t_ws_scholarship_scheme")
@DynamicUpdate(value = true)
@SequenceGenerator(name = "scholarshipschemeseq", sequenceName = "scholarship_scheme_seq", allocationSize = 1, initialValue = 1)
public class ScholarshipScheme {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "scholarshipschemeseq")
    private long id;

    @Column(name = "scheme_name", length = 100, nullable = false, unique = true)
    private String schemeName;

    @Column(name = "type", nullable = false)
    private String type; // Should be validated in service or DTO: 'fixed' or 'percentage'

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "eligibility", columnDefinition = "TEXT")
    private String eligibility;

    @Column(name = "status")
    private Boolean status = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_user_id")
    private UserProfile createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_user_id")
    private UserProfile updatedBy;

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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
