package com.ws.spring.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "certificate_type_master")
@DynamicUpdate(value = true)
@SequenceGenerator(
    name = "certificatetypeseq", 
    sequenceName = "certificate_type_seq", 
    allocationSize = 1, 
    initialValue = 1
)
public class CertificateType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "certificatetypeseq")
    private long typeId;

    @Column(unique = true, nullable = false)
    private String typeCode;

    @Column(nullable = false)
    private String certificateName;

    private String templatePath;

    @Column(nullable = false)
    private boolean isPrintable;

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

    // Getters and Setters

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public boolean isPrintable() {
        return isPrintable;
    }

    public void setPrintable(boolean printable) {
        isPrintable = printable;
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
