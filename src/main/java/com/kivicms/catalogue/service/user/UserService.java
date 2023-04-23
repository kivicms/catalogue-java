package com.kivicms.catalogue.service.user;

import com.kivicms.catalogue.filters.SearchCriteria;
import com.kivicms.catalogue.models.user.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> update(User $user);

    List<User> searchUser(List<SearchCriteria> params);

    List<User> generate(Integer count);
}
