package com.kivicms.catalogue.models;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@MappedSuperclass
public abstract class AbstractCustomEntity implements Serializable {

    private static final long serialVersionUID = -4524603159559823380L;

    @Setter
    @Column(name = "deleted_at", nullable = true)
    private Date deletedAt;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    protected void prePersist() {
        createdAt = new Date();
        preUpdate();
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = new Date();
    }

    @PreDestroy
    protected void preDestroy() {
        deletedAt = new Date();
    }

    public abstract boolean equals(Object obj);

    public abstract int hashCode();
}
