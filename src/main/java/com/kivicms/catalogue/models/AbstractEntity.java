package com.kivicms.catalogue.models;

import com.kivicms.catalogue.dto.AbstractFullDto;
import com.kivicms.catalogue.dto.user.UserDto;
import com.kivicms.catalogue.models.user.User;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -36259337783984695L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {@org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Setter
    @Column(name = "deleted_at", nullable = true)
    private Date deletedAt;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Setter
    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", columnDefinition = "UUID")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", columnDefinition = "UUID")
    private User updater;

    protected AbstractEntity(AbstractFullDto dto) {
        this.id = dto.getId();
    }

    @PrePersist
    protected void prePersist() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (createdAt == null) {
            createdAt = new Date();
            author = user;
        }
        preUpdate();
    }

    @PreUpdate
    protected void preUpdate() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (createdAt == null) {
            createdAt = new Date();
            author = user;
        }
        updatedAt = new Date();
        updater = user;
    }

    @PreDestroy
    protected void preDestroy() {
        deletedAt = new Date();
    }
}
