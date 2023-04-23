package com.kivicms.catalogue.repository.user;

import com.kivicms.catalogue.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    User findByEmailIgnoreCase(String email);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
