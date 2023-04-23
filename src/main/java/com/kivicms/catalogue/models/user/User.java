package com.kivicms.catalogue.models.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kivicms.catalogue.enums.user.UserRole;
import com.kivicms.catalogue.enums.user.UserStatus;
import com.kivicms.catalogue.models.AbstractCustomEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@AllArgsConstructor
@Table(name = "users")
public class User extends AbstractCustomEntity implements UserDetails {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator", parameters = {@org.hibernate.annotations.Parameter(name = "uuid_gen_strategy_class", value = "org.hibernate.id.uuid.CustomVersionOneStrategy")})
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String email;
    private Date emailVerifiedAt;
    private String password;
    private String rememberToken;
    private Date createdAt;
    private Date updatedAt;
    private String lastName;
    private String firstName;
    private String middleName;
    private String avatar;
    private UserRole role = UserRole.USER;
    private UserStatus status = UserStatus.ACTIVE;
    private double balance = 0.0;
    private UUID tariffId;
    private Date blockedAt;

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}