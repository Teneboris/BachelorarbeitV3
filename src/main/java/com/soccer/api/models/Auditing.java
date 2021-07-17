package com.soccer.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soccer.api.security.services.UserDetailsImpl;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy", "updatedBy", "createdAt", "updatedAt"},
        allowGetters = true
)
public class Auditing {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Column(name = "created_by", nullable = false, updatable = false)
    @CreatedBy
    private long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "updated_by", nullable = false)
    private long updatedBy;

    /**
     * set updatedBy and createdBy on insert
     */
    @PrePersist
    protected void prePersist() {
        try {
            UserDetailsImpl actuelUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            updatedBy = actuelUser.getId();
            createdBy = actuelUser.getId();
        } catch (Exception e) {

        }
    }

    /**
     * set updatedby on Update
     */
    @PreUpdate
    protected void preUpdate() {
        try {
            UserDetailsImpl actuelUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            updatedBy = actuelUser.getId();
        } catch (Exception e) {

        }
    }

    @PreRemove
    protected void preRemove() {
        updatedBy = 1;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public long getUpdatedBy() {
        return updatedBy;
    }
}

