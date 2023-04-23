package com.kivicms.catalogue.service.user;

import com.github.javafaker.Faker;
import com.kivicms.catalogue.enums.catalogue.CatalogueFormat;
import com.kivicms.catalogue.enums.catalogue.CatalogueStatus;
import com.kivicms.catalogue.enums.user.UserRole;
import com.kivicms.catalogue.enums.user.UserStatus;
import com.kivicms.catalogue.filters.SearchCriteria;
import com.kivicms.catalogue.filters.user.UserSearchQueryCriteriaConsumer;
import com.kivicms.catalogue.models.catalogue.Catalogue;
import com.kivicms.catalogue.models.user.User;
import com.kivicms.catalogue.repository.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ResponseEntity<?> update(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        userRepository.save(user);

        return ResponseEntity.ok("Verify email by the link sent on your email address");
    }

    @Override
    public List<User> searchUser(final List<SearchCriteria> params) {
        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<User> query = builder.createQuery(User.class);
        final Root<User> r = query.from(User.class);

        Predicate predicate = builder.conjunction();
        UserSearchQueryCriteriaConsumer searchConsumer = new UserSearchQueryCriteriaConsumer(predicate, builder, r);
        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<User> generate(Integer count) {
        Faker faker = new Faker();

        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setPassword(faker.internet().password());
            user.setAvatar(faker.avatar().image());
            user.setRole(UserRole.USER);
            user.setStatus(UserStatus.ACTIVE);
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            userRepository.save(user);
        }
        return null;
    }
}
